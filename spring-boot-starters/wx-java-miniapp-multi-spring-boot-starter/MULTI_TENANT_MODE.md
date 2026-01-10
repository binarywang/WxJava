# 微信小程序多租户配置说明

## 多租户模式对比

从 4.8.0 版本开始，wx-java-miniapp-multi-spring-boot-starter 支持两种多租户实现模式：

### 1. 隔离模式（ISOLATED，默认）

每个租户创建独立的 `WxMaService` 实例，各自拥有独立的 HTTP 客户端。

**优点：**
- 线程安全，无需担心并发问题
- 不依赖 ThreadLocal，适合异步/响应式编程
- 租户间完全隔离，互不影响

**缺点：**
- 每个租户创建独立的 HTTP 客户端，资源占用较多
- 适合租户数量不多的场景（建议 < 50 个租户）

**适用场景：**
- SaaS 应用，租户数量较少
- 异步编程、响应式编程场景
- 对线程安全有严格要求

### 2. 共享模式（SHARED）

使用单个 `WxMaService` 实例管理所有租户配置，所有租户共享同一个 HTTP 客户端。

**优点：**
- 共享 HTTP 客户端，大幅节省资源
- 适合租户数量较多的场景（支持 100+ 租户）
- 内存占用更小

**缺点：**
- 依赖 ThreadLocal 切换配置，在异步场景需要特别注意
- 需要注意线程上下文传递

**适用场景：**
- 租户数量较多（> 50 个）
- 同步编程场景
- 对资源占用有严格要求

## 配置方式

### 使用隔离模式（默认）

```yaml
wx:
  ma:
    # 多租户配置
    apps:
      tenant1:
        app-id: wxd898fcb01713c555
        app-secret: 47a2422a5d04a27e2b3ed1f1f0b0dbad
        token: aBcDeFg123456
        aes-key: abcdefgh123456abcdefgh123456abc
      tenant2:
        app-id: wx1234567890abcdef
        app-secret: 1234567890abcdef1234567890abcdef
        token: token123
        aes-key: aeskey123aeskey123aeskey123aes
    
    # 配置存储（可选）
    config-storage:
      type: memory  # memory, jedis, redisson, redis_template
      http-client-type: http_client  # http_client, ok_http, jodd_http
      # multi-tenant-mode: isolated  # 默认值，可以不配置
```

### 使用共享模式

```yaml
wx:
  ma:
    # 多租户配置
    apps:
      tenant1:
        app-id: wxd898fcb01713c555
        app-secret: 47a2422a5d04a27e2b3ed1f1f0b0dbad
      tenant2:
        app-id: wx1234567890abcdef
        app-secret: 1234567890abcdef1234567890abcdef
      # ... 可配置更多租户
    
    # 配置存储
    config-storage:
      type: memory
      http-client-type: http_client
      multi-tenant-mode: shared  # 启用共享模式
```

## 代码使用

两种模式下的代码使用方式**完全相同**：

```java
@RestController
@RequestMapping("/ma")
public class MiniAppController {
  
  @Autowired
  private WxMaMultiServices wxMaMultiServices;
  
  @GetMapping("/userInfo/{tenantId}")
  public String getUserInfo(@PathVariable String tenantId, @RequestParam String code) {
    // 获取指定租户的 WxMaService
    WxMaService wxMaService = wxMaMultiServices.getWxMaService(tenantId);
    
    try {
      WxMaJscode2SessionResult session = wxMaService.jsCode2SessionInfo(code);
      return "OpenId: " + session.getOpenid();
    } catch (WxErrorException e) {
      return "错误: " + e.getMessage();
    }
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

## 注意事项

### 共享模式下的异步编程

如果使用共享模式，在异步编程时需要注意 ThreadLocal 的传递：

```java
@Service
public class MiniAppService {
  
  @Autowired
  private WxMaMultiServices wxMaMultiServices;
  
  public void asyncOperation(String tenantId) {
    WxMaService wxMaService = wxMaMultiServices.getWxMaService(tenantId);
    
    // ❌ 错误：异步线程无法获取到正确的配置
    CompletableFuture.runAsync(() -> {
      // 这里 wxMaService.getWxMaConfig() 可能返回错误的配置
      wxMaService.getUserService().getUserInfo(...);
    });
    
    // ✅ 正确：在主线程获取配置，传递给异步线程
    WxMaConfig config = wxMaService.getWxMaConfig();
    String appId = config.getAppid();
    CompletableFuture.runAsync(() -> {
      // 使用已获取的配置信息
      log.info("AppId: {}", appId);
    });
  }
}
```

### 动态添加/删除租户

两种模式都支持运行时动态添加或删除租户配置。

## 迁移指南

如果您正在使用旧版本，升级到 4.8.0+ 后：

1. **默认行为不变**：如果不配置 `multi-tenant-mode`，将继续使用隔离模式（与旧版本行为一致）
2. **向后兼容**：所有现有代码无需修改
3. **可选升级**：如需节省资源，可配置 `multi-tenant-mode: shared` 启用共享模式

## 源码分析

issue讨论地址：[#3835](https://github.com/binarywang/WxJava/issues/3835)

### 为什么有两种设计？

1. **基础实现类的 `configMap`**：
   - 位置：`BaseWxMaServiceImpl`
   - 特点：单个 Service 实例 + 多个配置 + ThreadLocal 切换
   - 设计目的：支持在一个应用中管理多个小程序账号

2. **Spring Boot Starter 的 `services` Map**：
   - 位置：`WxMaMultiServicesImpl`
   - 特点：多个 Service 实例 + 每个实例一个配置
   - 设计目的：为 Spring Boot 提供更符合依赖注入风格的多租户支持

### 新版本改进

新版本通过配置项让用户自主选择实现方式：

```
用户 → WxMaMultiServices 接口
         ↓
    ┌────┴────┐
    ↓         ↓
隔离模式    共享模式
(多Service)  (单Service+configMap)
```

这样既保留了线程安全的优势（隔离模式），又提供了资源节省的选项（共享模式）。
