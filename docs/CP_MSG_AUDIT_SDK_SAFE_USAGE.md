# 企业微信会话存档SDK安全使用指南

## 问题背景

在使用企业微信会话存档功能时，部分开发者遇到了JVM崩溃的问题。典型错误信息如下：

```
SIGSEGV (0xb) at pc=0x00007fcd50460d93
Problematic frame:
C  [libWeWorkFinanceSdk_Java.so+0x260d93]  WeWorkFinanceSdk::TryRefresh(std::string const&, std::string const&, int)+0x23
```

## 问题原因

旧版API设计存在以下问题：

1. **SDK生命周期管理混乱**
   - `getChatDatas()` 方法会返回SDK实例给调用方
   - 开发者需要手动调用 `Finance.DestroySdk()` 来销毁SDK
   - 但SDK在框架内部有7200秒的缓存机制

2. **手动销毁导致缓存失效**
   - 当开发者手动销毁SDK后，框架缓存中的SDK引用变为无效
   - 后续调用（如 `getMediaFile()`）仍然使用已销毁的SDK
   - 底层C++库访问无效指针，导致SIGSEGV错误

3. **多线程并发问题**
   - 在多线程环境下，一个线程销毁SDK后
   - 其他线程仍在使用该SDK，导致崩溃

## 解决方案

从 **4.8.0** 版本开始，WxJava提供了新的安全API，完全由框架管理SDK生命周期。

### 新API列表

| 旧API（已废弃） | 新API（推荐使用） | 说明 |
|----------------|------------------|------|
| `getChatDatas()` | `getChatRecords()` | 拉取聊天记录，不暴露SDK |
| `getDecryptData(sdk, ...)` | `getDecryptChatData(...)` | 解密聊天数据，无需传入SDK |
| `getChatPlainText(sdk, ...)` | `getChatRecordPlainText(...)` | 获取明文数据，无需传入SDK |
| `getMediaFile(sdk, ...)` | `downloadMediaFile(...)` | 下载媒体文件，无需传入SDK |

### 使用示例

#### 错误用法（旧API，已废弃）

```java
// ❌ 不推荐：容易导致JVM崩溃
WxCpMsgAuditService msgAuditService = wxCpService.getMsgAuditService();

// 拉取聊天记录
WxCpChatDatas chatDatas = msgAuditService.getChatDatas(seq, 1000L, null, null, 1000L);

for (WxCpChatDatas.WxCpChatData chatData : chatDatas.getChatData()) {
    // 解密数据
    WxCpChatModel model = msgAuditService.getDecryptData(chatDatas.getSdk(), chatData, 2);
    
    // 下载媒体文件
    if ("image".equals(model.getMsgType())) {
        String sdkFileId = model.getImage().getSdkFileId();
        msgAuditService.getMediaFile(chatDatas.getSdk(), sdkFileId, null, null, 1000L, targetPath);
    }
}

// ❌ 危险操作：手动销毁SDK可能导致后续调用崩溃
Finance.DestroySdk(chatDatas.getSdk());
```

#### 正确用法（新API，推荐）

```java
// ✅ 推荐：SDK生命周期由框架自动管理，安全可靠
WxCpMsgAuditService msgAuditService = wxCpService.getMsgAuditService();

// 拉取聊天记录（不返回SDK）
List<WxCpChatDatas.WxCpChatData> chatRecords = 
    msgAuditService.getChatRecords(seq, 1000L, null, null, 1000L);

for (WxCpChatDatas.WxCpChatData chatData : chatRecords) {
    // 解密数据（无需传入SDK）
    WxCpChatModel model = msgAuditService.getDecryptChatData(chatData, 2);
    
    // 下载媒体文件（无需传入SDK）
    if ("image".equals(model.getMsgType())) {
        String sdkFileId = model.getImage().getSdkFileId();
        msgAuditService.downloadMediaFile(sdkFileId, null, null, 1000L, targetPath);
    }
}

// ✅ 无需手动销毁SDK，框架会自动管理
```

### 完整示例：拉取并处理会话存档

```java
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.api.WxCpMsgAuditService;
import me.chanjar.weixin.cp.bean.msgaudit.WxCpChatDatas;
import me.chanjar.weixin.cp.bean.msgaudit.WxCpChatModel;
import me.chanjar.weixin.cp.constant.WxCpConsts;

import java.util.List;

public class MsgAuditExample {
    
    private final WxCpService wxCpService;
    
    public void processMessages(long seq) throws Exception {
        WxCpMsgAuditService msgAuditService = wxCpService.getMsgAuditService();
        
        // 拉取聊天记录
        List<WxCpChatDatas.WxCpChatData> chatRecords = 
            msgAuditService.getChatRecords(seq, 1000L, null, null, 1000L);
        
        for (WxCpChatDatas.WxCpChatData chatData : chatRecords) {
            seq = chatData.getSeq();
            
            // 获取明文数据
            String plainText = msgAuditService.getChatRecordPlainText(chatData, 2);
            WxCpChatModel model = WxCpChatModel.fromJson(plainText);
            
            // 处理不同类型的消息
            switch (model.getMsgType()) {
                case WxCpConsts.MsgAuditMediaType.TEXT:
                    processTextMessage(model);
                    break;
                    
                case WxCpConsts.MsgAuditMediaType.IMAGE:
                    processImageMessage(model, msgAuditService);
                    break;
                    
                case WxCpConsts.MsgAuditMediaType.FILE:
                    processFileMessage(model, msgAuditService);
                    break;
                    
                default:
                    // 处理其他类型消息
                    break;
            }
        }
    }
    
    private void processTextMessage(WxCpChatModel model) {
        String content = model.getText().getContent();
        System.out.println("文本消息：" + content);
    }
    
    private void processImageMessage(WxCpChatModel model, WxCpMsgAuditService msgAuditService) 
            throws Exception {
        String sdkFileId = model.getImage().getSdkFileId();
        String md5Sum = model.getImage().getMd5Sum();
        String targetPath = "/path/to/save/" + md5Sum + ".jpg";
        
        // 下载图片（无需传入SDK）
        msgAuditService.downloadMediaFile(sdkFileId, null, null, 1000L, targetPath);
        System.out.println("图片已保存：" + targetPath);
    }
    
    private void processFileMessage(WxCpChatModel model, WxCpMsgAuditService msgAuditService) 
            throws Exception {
        String sdkFileId = model.getFile().getSdkFileId();
        String fileName = model.getFile().getFileName();
        String targetPath = "/path/to/save/" + fileName;
        
        // 下载文件（无需传入SDK）
        msgAuditService.downloadMediaFile(sdkFileId, null, null, 1000L, targetPath);
        System.out.println("文件已保存：" + targetPath);
    }
}
```

### 使用Lambda处理媒体文件流

新API同样支持使用Lambda表达式处理媒体文件的数据流：

```java
msgAuditService.downloadMediaFile(sdkFileId, null, null, 1000L, data -> {
    try {
        // 处理每个数据分片（大文件会分片传输）
        // 例如：上传到云存储、写入数据库等
        uploadToCloud(data);
    } catch (Exception e) {
        e.printStackTrace();
    }
});
```

## 技术实现原理

### 引用计数机制

新API在内部实现了SDK引用计数机制：

1. **获取SDK时**：引用计数 +1
2. **使用完成后**：引用计数 -1
3. **计数归零时**：SDK被自动释放

```java
// 框架内部实现（简化版）
public void downloadMediaFile(String sdkFileId, ...) {
    long sdk = initSdk();  // 获取或初始化SDK
    configStorage.incrementMsgAuditSdkRefCount(sdk);  // 引用计数 +1
    
    try {
        // 执行实际操作
        getMediaFile(sdk, sdkFileId, ...);
    } finally {
        // 确保引用计数一定会减少
        configStorage.decrementMsgAuditSdkRefCount(sdk);  // 引用计数 -1
    }
}
```

### SDK缓存机制

SDK初始化后会缓存7200秒（企业微信官方文档规定），避免频繁初始化：

- **首次调用**：初始化新的SDK
- **7200秒内**：复用缓存的SDK
- **超过7200秒**：重新初始化SDK

新API的引用计数机制与缓存机制完美配合，确保SDK不会被提前销毁。

## 迁移指南

### 第一步：使用新API替换旧API

查找代码中的旧API调用：

```java
// 查找模式
getChatDatas(
getDecryptData(.*sdk
getChatPlainText(.*sdk
getMediaFile(.*sdk
Finance.DestroySdk(
```

替换为对应的新API（参考前面的对照表）。

### 第二步：移除手动SDK管理代码

删除所有 `Finance.DestroySdk()` 调用，SDK生命周期由框架自动管理。

### 第三步：测试验证

1. 在测试环境验证新API功能正常
2. 观察日志，确认没有SDK相关的错误
3. 进行压力测试，验证多线程环境下的稳定性

## 常见问题

### Q1: 旧代码会立即停止工作吗？

**A:** 不会。旧API被标记为 `@Deprecated`，但仍然可用，只是不推荐继续使用。建议尽快迁移到新API以避免潜在问题。

### Q2: 如何知道SDK是否被正确释放？

**A:** 框架会自动管理SDK生命周期，开发者无需关心。如果需要调试，可以查看配置存储中的引用计数。

### Q3: 多线程环境下新API安全吗？

**A:** 是的。新API使用了引用计数机制，配合 `synchronized` 关键字，确保多线程环境下的安全性。

### Q4: 性能会受影响吗？

**A:** 不会。新API在实现上增加了引用计数的开销，但这是轻量级的操作（原子操作），对性能影响可以忽略不计。SDK缓存机制保持不变。

### Q5: 可以同时使用新旧API吗？

**A:** 技术上可以，但强烈不推荐。混用可能导致SDK生命周期管理混乱，建议统一使用新API。

## 相关链接

- [企业微信会话存档官方文档](https://developer.work.weixin.qq.com/document/path/91360)
- [WxJava GitHub 仓库](https://github.com/binarywang/WxJava)
- [问题反馈](https://github.com/binarywang/WxJava/issues)

## 版本要求

- **最低版本**: 4.8.0
- **推荐版本**: 最新版本

## 反馈与支持

如果在使用过程中遇到问题，请：

1. 查看本文档的常见问题部分
2. 在 GitHub 上提交 Issue
3. 加入微信群获取社区支持

---

**最后更新时间**: 2026-01-14
