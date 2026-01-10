# 多租户模式配置改进说明

## 问题背景

用户在 issue #3835 中提出了一个架构设计问题：

> 基础 Wx 实现类中已经有 configMap 了，可以用 configMap 来存储不同的小程序配置。不同的配置，都是复用同一个 http 客户端。为什么在各个 spring-boot-starter 中又单独创建类来存储不同的配置？从 spring 的配置来看，http 客户端只有一个，不同小程序配置可以实现多租户，所以似乎没必要单独再建新类存放？重复创建，增加了 http 客户端的成本？直接使用 Wx 实现类中已经有 configMap 不是更好吗？

## 解决方案

从 4.8.0 版本开始，我们为多租户 Spring Boot Starter 提供了**两种实现模式**供用户选择：

### 1. 隔离模式（ISOLATED，默认）

**实现方式**：为每个租户创建独立的 WxService 实例，每个实例拥有独立的 HTTP 客户端。

**优点**：
- ✅ 线程安全，无需担心并发问题
- ✅ 不依赖 ThreadLocal，适合异步/响应式编程
- ✅ 租户间完全隔离，互不影响

**缺点**：
- ❌ 每个租户创建独立的 HTTP 客户端，资源占用较多
- ❌ 适合租户数量不多的场景（建议 < 50 个租户）

**代码实现**：`WxMaMultiServicesImpl`, `WxMpMultiServicesImpl` 等

### 2. 共享模式（SHARED，新增）

**实现方式**：使用单个 WxService 实例管理所有租户配置，通过 ThreadLocal 切换租户，所有租户共享同一个 HTTP 客户端。

**优点**：
- ✅ 共享 HTTP 客户端，大幅节省资源
- ✅ 适合租户数量较多的场景（支持 100+ 租户）
- ✅ 内存占用更小

**缺点**：
- ❌ 依赖 ThreadLocal 切换配置，在异步场景需要特别注意
- ❌ 需要注意线程上下文传递

**代码实现**：`WxMaMultiServicesSharedImpl`, `WxMpMultiServicesSharedImpl` 等

## 使用方式

### 配置示例

```yaml
wx:
  ma:  # 或 mp, cp, channel
    apps:
      tenant1:
        app-id: wxd898fcb01713c555
        app-secret: 47a2422a5d04a27e2b3ed1f1f0b0dbad
      tenant2:
        app-id: wx1234567890abcdef
        app-secret: 1234567890abcdef1234567890abcdef
    
    config-storage:
      type: memory
      http-client-type: http_client
      # 多租户模式配置（新增）
      multi-tenant-mode: shared  # isolated（默认）或 shared
```

### 代码使用（两种模式代码完全相同）

```java
@RestController
public class WxController {
  @Autowired
  private WxMaMultiServices wxMaMultiServices;  // 或 WxMpMultiServices
  
  @GetMapping("/api/{tenantId}")
  public String handle(@PathVariable String tenantId) {
    WxMaService wxService = wxMaMultiServices.getWxMaService(tenantId);
    // 使用 wxService 调用微信 API
    return wxService.getAccessToken();
  }
}
```

## 性能对比

以 100 个租户为例：

| 指标 | 隔离模式 | 共享模式 |
|------|---------|---------|
| HTTP 客户端数量 | 100 个 | 1 个 |
| 内存占用（估算） | ~500MB | ~50MB |
| 线程安全 | ✅ 完全安全 | ⚠️ 需注意异步场景 |
| 性能 | 略高（无 ThreadLocal 切换） | 略低（有 ThreadLocal 切换） |
| 适用场景 | 中小规模 | 大规模 |

## 支持的模块

目前已实现共享模式支持的模块：

- ✅ **小程序（MiniApp）**：`wx-java-miniapp-multi-spring-boot-starter`
- ✅ **公众号（MP）**：`wx-java-mp-multi-spring-boot-starter`

后续版本将支持：
- ⏳ 企业微信（CP）
- ⏳ 视频号（Channel）
- ⏳ 企业微信第三方应用（CP-TP）

## 迁移指南

### 从旧版本升级

升级到 4.8.0+ 后：

1. **默认行为不变**：如果不配置 `multi-tenant-mode`，将继续使用隔离模式（与旧版本行为一致）
2. **向后兼容**：所有现有代码无需修改
3. **可选升级**：如需节省资源，可配置 `multi-tenant-mode: shared` 启用共享模式

### 选择建议

**使用隔离模式（ISOLATED）的场景**：
- 租户数量较少（< 50 个）
- 使用异步编程、响应式编程
- 对线程安全有严格要求
- 对资源占用不敏感

**使用共享模式（SHARED）的场景**：
- 租户数量较多（> 50 个）
- 同步编程场景
- 对资源占用敏感
- 可以接受 ThreadLocal 的约束

## 注意事项

### 共享模式下的异步编程

如果使用共享模式，在异步编程时需要注意 ThreadLocal 的传递：

```java
// ❌ 错误：异步线程无法获取到正确的配置
CompletableFuture.runAsync(() -> {
  wxService.getUserService().getUserInfo(...);  // 可能使用错误的租户配置
});

// ✅ 正确：在主线程获取必要信息，传递给异步线程
String appId = wxService.getWxMaConfig().getAppid();
CompletableFuture.runAsync(() -> {
  log.info("AppId: {}", appId);  // 使用已获取的配置信息
});
```

## 详细文档

- 小程序模块详细说明：[spring-boot-starters/wx-java-miniapp-multi-spring-boot-starter/MULTI_TENANT_MODE.md](spring-boot-starters/wx-java-miniapp-multi-spring-boot-starter/MULTI_TENANT_MODE.md)

## 相关链接

- Issue: [#3835](https://github.com/binarywang/WxJava/issues/3835)
- Pull Request: [#3840](https://github.com/binarywang/WxJava/pull/3840)

## 致谢

感谢 issue 提出者对项目架构的深入思考和建议，这帮助我们提供了更灵活、更高效的多租户解决方案。
