# 会话存档SDK生命周期重构方案

## Context

当前实现（4.8.x）通过"共享SDK + 引用计数 + 7200秒过期"来管理会话存档SDK生命周期。
该方案存在以下核心问题：

1. **频繁初始化/销毁**：每次调用 `releaseSdk()` 后引用计数归零即销毁SDK。对于"拉取→解密→下载媒体"这类典型串行调用链，每步操作都会触发重新初始化。
2. **7200秒过期规则无依据**：官方文档FAQ明确说"不需要每次new/init sdk，可以在多次拉取中复用同一个sdk"，无任何7200秒过期说明。
3. **线程安全问题**：企微技术人员建议"一个线程一个SDK实例"，当前设计多线程共享同一SDK实例，存在并发安全隐患。

---

## 推荐方案：ThreadLocal SDK 模式

> **核心原则**：每个线程拥有独立SDK实例，懒初始化，生命周期与线程绑定。

### 设计要点

- 使用 `ThreadLocal<Long>` 为每个线程持有独立SDK
- SDK在线程首次调用时初始化，后续所有操作复用（无需重复初始化）
- 移除7200秒过期机制
- 移除引用计数机制（每线程独占，无需计数）
- 提供显式清理接口：`closeThreadLocalSdk()`（线程结束时调）、`closeAllSdks()`（应用关闭时调）

### 生命周期示意

```
Thread A: init SDK_A → getChatRecords → getDecryptChatData → downloadMediaFile → [任务结束后调closeThreadLocalSdk]
Thread B: init SDK_B → getChatRecords → getDecryptChatData → downloadMediaFile → ...
Thread C: init SDK_C → ...
```

---

## 涉及文件

| 文件 | 变更类型 |
|------|--------|
| `weixin-java-cp/src/main/java/me/chanjar/weixin/cp/api/impl/WxCpMsgAuditServiceImpl.java` | 主要重构 |
| `weixin-java-cp/src/main/java/me/chanjar/weixin/cp/api/WxCpMsgAuditService.java` | 新增接口方法 |
| `weixin-java-cp/src/main/java/me/chanjar/weixin/cp/config/WxCpConfigStorage.java` | 废弃旧SDK管理方法 |
| `weixin-java-cp/src/main/java/me/chanjar/weixin/cp/config/impl/WxCpDefaultConfigImpl.java` | 废弃旧字段/方法 |
| `weixin-java-cp/src/test/java/me/chanjar/weixin/cp/api/WxCpMsgAuditTest.java` | 补充测试 |
| `docs/CP_MSG_AUDIT_SDK_SAFE_USAGE.md` | 更新文档 |

---

## 详细变更

### 1. WxCpMsgAuditServiceImpl（主要变更）

**新增字段：**
```java
/** 每个线程持有独立SDK实例 */
private final ThreadLocal<Long> threadLocalSdk = new ThreadLocal<>();

/** 跟踪所有已创建SDK，用于统一清理 */
private final Set<Long> managedSdks = ConcurrentHashMap.newKeySet();
```

**废弃字段/方法：**
- 废弃常量 `SDK_EXPIRES_TIME = 7200`（无官方依据）
- 废弃 `initSdk()`（由 `getOrInitThreadLocalSdk()` 替代）
- 废弃 `acquireSdk()` / `releaseSdk()`（由ThreadLocal模式替代）

**新增核心方法：**

```java
/**
 * 获取当前线程的SDK，不存在则创建。SDK在线程内跨调用复用，无需每次重新初始化。
 */
private long getOrInitThreadLocalSdk() throws WxErrorException {
    Long sdk = threadLocalSdk.get();
    if (sdk != null && sdk > 0) {
        return sdk;
    }
    long newSdk = createSdk();
    threadLocalSdk.set(newSdk);
    managedSdks.add(newSdk);
    log.info("线程 [{}] 初始化会话存档SDK成功，sdk={}", Thread.currentThread().getName(), newSdk);
    return newSdk;
}

/**
 * 创建并初始化一个新SDK（私有，只在当前线程无SDK时调用）
 */
private long createSdk() throws WxErrorException {
    WxCpConfigStorage configStorage = cpService.getWxCpConfigStorage();
    // ... 与现有 initSdk() 内的库加载+Finance.NewSdk()+Finance.Init() 逻辑一致 ...
    // 注意：Finance.loadingLibraries() 是幂等的（System.load内部防重复），多线程调用安全
}

/**
 * 关闭当前线程持有的SDK，释放本地资源。
 * 在线程任务结束时调用（如定时任务finally块，或线程池线程销毁时）。
 */
public void closeThreadLocalSdk() {
    Long sdk = threadLocalSdk.get();
    if (sdk != null && sdk > 0) {
        Finance.DestroySdk(sdk);
        managedSdks.remove(sdk);
        threadLocalSdk.remove();
        log.info("线程 [{}] 关闭会话存档SDK，sdk={}", Thread.currentThread().getName(), sdk);
    }
}

/**
 * 关闭所有线程持有的SDK。应用关闭时调用（如Spring @PreDestroy / Shutdown Hook）。
 */
public void closeAllSdks() {
    managedSdks.forEach(sdk -> {
        Finance.DestroySdk(sdk);
        log.info("关闭会话存档SDK，sdk={}", sdk);
    });
    managedSdks.clear();
    threadLocalSdk.remove();
}
```

**更新新API方法（getChatRecords / getDecryptChatData / getChatRecordPlainText / downloadMediaFile）：**
- 调用 `getOrInitThreadLocalSdk()` 替代 `acquireSdk()`
- 移除 try-finally 中的 `releaseSdk(sdk)` 调用（SDK不再每次释放）
- 方法变得更简洁：直接使用sdk，无需包装计数

**保留旧API方法不变（getChatDatas / getDecryptData / getChatPlainText / getMediaFile）：**
- 保持 @Deprecated 标注
- 内部调用改为 `getOrInitThreadLocalSdk()` 以保持一致性（旧方法也受益于ThreadLocal）
- 移除对 `initSdk()` 的依赖

### 2. WxCpMsgAuditService（接口新增）

```java
/**
 * 关闭当前线程持有的SDK，释放native资源。
 * Finance.DestroySdk() 不会随线程结束自动执行，无论线程池还是独立线程，
 * 均应在任务结束的finally块中调用本方法，防止native内存、连接等资源泄漏。
 */
void closeThreadLocalSdk();

/**
 * 关闭所有会话存档SDK实例。
 * 适用于应用关闭时（如Spring Bean销毁阶段）统一释放资源。
 */
void closeAllSdks();
```

### 3. WxCpConfigStorage（废弃旧SDK管理API）

对以下方法标记 `@Deprecated`（保留实现，不做破坏性删除）：
- `getMsgAuditSdk()` / `updateMsgAuditSdk()` / `expireMsgAuditSdk()` / `isMsgAuditSdkExpired()`
- `acquireMsgAuditSdk()` / `releaseMsgAuditSdk()`
- `incrementMsgAuditSdkRefCount()` / `decrementMsgAuditSdkRefCount()` / `getMsgAuditSdkRefCount()`

### 4. WxCpDefaultConfigImpl（废弃旧字段）

- 将 `msgAuditSdk`、`msgAuditSdkExpiresTime`、`msgAuditSdkRefCount` 字段标记 `@Deprecated`
- 对应的 getter/setter/acquire/release 方法标记 `@Deprecated`
- 保留实现，确保向后兼容

---

## 使用示例（更新文档）

```java
// ✅ 典型用法（一次任务中串行调用，SDK在同线程内复用，无重复初始化）
WxCpMsgAuditService msgAuditService = wxCpService.getMsgAuditService();

try {
    List<WxCpChatDatas.WxCpChatData> records = msgAuditService.getChatRecords(seq, 100L, null, null, 30L);
    for (WxCpChatDatas.WxCpChatData record : records) {
        WxCpChatModel model = msgAuditService.getDecryptChatData(record, 2);
        if ("image".equals(model.getMsgType())) {
            msgAuditService.downloadMediaFile(model.getImage().getSdkFileId(), null, null, 30L, "/tmp/img.jpg");
        }
    }
} finally {
    // 无论线程池还是独立线程，均建议在 finally 中显式调用。
    // Finance.DestroySdk() 不会随线程结束自动执行，依赖 closeAllSdks() 兜底会造成
    // native 内存/连接资源的延迟泄漏，对定时任务等长期运行场景尤其有害。
    msgAuditService.closeThreadLocalSdk();
}

// 应用关闭时（Spring @PreDestroy 或 Shutdown Hook）
// msgAuditService.closeAllSdks();
```

---

## 注意事项

1. **线程池场景下必须调用 `closeThreadLocalSdk()`**：线程池中线程会被复用，如不主动清理，下次任务仍会使用旧线程的SDK。对于计划任务/批处理，建议在 finally 块中调用。
2. **独立线程同样建议显式关闭**：`Finance.DestroySdk()` 是 native 调用，不会随线程结束自动执行，JVM GC 也不会触发它。依赖 `closeAllSdks()` 兜底意味着 native 内存、网络连接等资源在整个应用运行期间一直持有，对定时任务等高频场景会持续积累，建议统一在 finally 块中调用 `closeThreadLocalSdk()`。
3. **多企业（多CorpId）场景**：`threadLocalSdk` 是实例字段（非static），不同 `WxCpMsgAuditServiceImpl` 实例（不同企业）的ThreadLocal独立，互不影响。
4. **库加载幂等性**：`Finance.loadingLibraries()` 底层调用 `System.load()`，JVM保证同一库不重复加载，多线程并发调用安全。

---

## 验证方式

1. **单元测试**：在 `WxCpMsgAuditTest` 中添加测试，验证同线程多次调用不触发重新初始化（可通过日志或mock Finance验证）
2. **多线程压测**：多线程并发调用 `getChatRecords` + `getDecryptChatData`，观察无JVM崩溃
3. **线程池复用测试**：使用固定线程池多次提交任务，验证 `closeThreadLocalSdk()` 后下次任务能正确重新初始化SDK
4. **应用关闭测试**：调用 `closeAllSdks()`，验证所有线程的SDK被正确销毁
