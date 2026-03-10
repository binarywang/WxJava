# HttpClient 升级指南

## 概述

从 WxJava 4.7.x 版本开始，项目开始支持并推荐使用 **Apache HttpClient 5.x**（HttpComponents Client 5），同时保持对 HttpClient 4.x 的向后兼容。

## 为什么升级？

1. **Apache HttpClient 5.x 是最新稳定版本**：提供更好的性能和更多的功能
2. **HttpClient 4.x 已经进入维护模式**：不再积极开发新功能
3. **更好的安全性**：HttpClient 5.x 包含最新的安全更新和改进
4. **向前兼容**：为未来的开发做好准备

## 支持的 HTTP 客户端

| HTTP 客户端 | 版本 | 配置值 | 状态 | 说明 |
|------------|------|--------|------|------|
| Apache HttpClient 5.x | 5.5 | `HttpComponents` | ⭐ 推荐 | 最新稳定版本 |
| Apache HttpClient 4.x | 4.5.13 | `HttpClient` | ✅ 支持 | 向后兼容 |
| OkHttp | 4.12.0 | `OkHttp` | ✅ 支持 | 需自行添加依赖 |
| Jodd-http | 6.3.0 | `JoddHttp` | ✅ 支持 | 需自行添加依赖 |

## 模块支持情况

| 模块 | HttpClient 5.x 支持 | 默认客户端 |
|------|-------------------|-----------|
| weixin-java-mp（公众号） | ✅ 是 | HttpComponents (5.x) |
| weixin-java-cp（企业微信） | ⚠️ 视集成方式而定 | 参考对应 starter 配置 |
| weixin-java-channel（视频号） | ✅ 是 | HttpComponents (5.x) |
| weixin-java-qidian（企点） | ✅ 是 | HttpComponents (5.x) |
| weixin-java-miniapp（小程序） | ✅ 是 | HttpComponents (5.x) |
| weixin-java-pay（支付） | ✅ 是 | HttpComponents (5.x) |
| weixin-java-open（开放平台） | ✅ 是 | HttpComponents (5.x) |

**注意**：
- **weixin-java-cp 模块**的支持情况取决于具体使用的 Starter 版本，请参考对应模块文档。

## 对现有项目的影响

### 对新项目
- **无需任何修改**，直接使用最新版本即可
- 支持 HttpClient 5.x 的模块会自动使用 HttpComponents (5.x)

### 对现有项目
- **向后兼容**：不需要修改任何代码
- 如果希望继续使用 HttpClient 4.x，只需在配置中显式指定，pay 模块会自动包含 httpclient4 依赖（因为某些接口必须使用 httpclient4）
  其他模块（mp、miniapp、cp、open、channel、qidian）如果需要使用 httpclient4，必须显式在项目中添加 httpclient4 依赖

## 迁移步骤

### 1. 更新 WxJava 版本

在 `pom.xml` 中更新版本：

```xml
<dependency>
  <groupId>com.github.binarywang</groupId>
  <artifactId>weixin-java-mp</artifactId>
  <version>最新版本</version> <!-- 请参考 Maven Central 或项目 README 获取最新版本号 -->
</dependency>
```

### 2. 检查配置（可选）

#### Spring Boot 项目

在 `application.properties` 或 `application.yml` 中：

```properties
# 使用 HttpClient 5.x（推荐，无需配置，已经是默认值）
wx.mp.config-storage.http-client-type=HttpComponents

# 或者继续使用 HttpClient 4.x
wx.mp.config-storage.http-client-type=HttpClient
```

#### 纯 Java 项目

```java
// 使用 HttpClient 5.x（推荐）
WxMpService wxMpService = new WxMpServiceHttpComponentsImpl();

// 或者继续使用 HttpClient 4.x
WxMpService wxMpService = new WxMpServiceHttpClientImpl();
```

### 3. 测试应用

升级后，建议进行全面测试以确保一切正常工作。

## 常见问题

### Q: 升级后会不会破坏现有代码？
A: 不会。项目保持完全向后兼容，HttpClient 4.x 的所有实现都保持不变。

### Q: 我需要修改代码吗？
A: 大多数情况下不需要。如果希望继续使用 HttpClient 4.x，只需在配置中指定 `http-client-type=HttpClient` ，并引入 HttpClient 4.x 依赖即可。

### Q: 我可以在同一个项目中同时使用两个版本吗？
A: 可以。不同的模块可以配置使用不同的 HTTP 客户端。例如，MP 模块使用 HttpClient 5.x，pay 模块部分接口仍使用 HttpClient 4.x，但也可以按需配置为 HttpClient 5.x。

### Q: 如何排除不需要的依赖？
A: 如果只想使用一个版本，可以在 `pom.xml` 中排除另一个：

```xml
<dependency>
  <groupId>com.github.binarywang</groupId>
  <artifactId>weixin-java-mp</artifactId>
  <version>最新版本</version>
  <exclusions>
    <!-- 排除 HttpClient 4.x -->
    <exclusion>
      <groupId>org.apache.httpcomponents</groupId>
      <artifactId>httpclient</artifactId>
    </exclusion>
    <exclusion>
      <groupId>org.apache.httpcomponents</groupId>
      <artifactId>httpmime</artifactId>
    </exclusion>
  </exclusions>
</dependency>
```

## 配置参考

### Spring Boot 完整配置示例

```properties
# 公众号配置
wx.mp.app-id=your_app_id
wx.mp.secret=your_secret
wx.mp.token=your_token
wx.mp.aes-key=your_aes_key

# HTTP 客户端配置
wx.mp.config-storage.http-client-type=HttpComponents  # HttpComponents, HttpClient, OkHttp, JoddHttp

# HTTP 代理配置（可选）
wx.mp.config-storage.http-proxy-host=proxy.example.com
wx.mp.config-storage.http-proxy-port=8080
wx.mp.config-storage.http-proxy-username=proxy_user
wx.mp.config-storage.http-proxy-password=proxy_pass

# 超时配置（可选）
wx.mp.config-storage.connection-timeout=5000
wx.mp.config-storage.so-timeout=5000
wx.mp.config-storage.connection-request-timeout=5000
```

## 技术细节

### HttpClient 4.x 与 5.x 的主要区别

1. **包名变更**：
   - HttpClient 4.x: `org.apache.http.*`
   - HttpClient 5.x: `org.apache.hc.client5.*`, `org.apache.hc.core5.*`

2. **API 改进**：
   - HttpClient 5.x 提供更现代的 API 设计
   - 更好的异步支持
   - 改进的连接池管理

3. **性能优化**：
   - HttpClient 5.x 包含多项性能优化
   - 更好的资源管理

### 项目中的实现

WxJava 项目通过策略模式支持多种 HTTP 客户端：

```
weixin-java-common/
├── util/http/
│   ├── apache/          # HttpClient 4.x 实现
│   ├── hc/              # HttpClient 5.x (HttpComponents) 实现
│   ├── okhttp/          # OkHttp 实现
│   └── jodd/            # Jodd-http 实现
```

每个模块都有对应的 Service 实现类：
- `*ServiceHttpClientImpl` - 使用 HttpClient 4.x
- `*ServiceHttpComponentsImpl` - 使用 HttpClient 5.x
- `*ServiceOkHttpImpl` - 使用 OkHttp
- `*ServiceJoddHttpImpl` - 使用 Jodd-http

## 反馈与支持

如果在升级过程中遇到问题，请：

1. 查看 [项目 Wiki](https://github.com/binarywang/WxJava/wiki)
2. 在 [GitHub Issues](https://github.com/binarywang/WxJava/issues) 中搜索或提交问题
3. 加入技术交流群（见 README.md）

## 总结

- ✅ **推荐使用 HttpClient 5.x**：性能更好，功能更强
- ✅ **向后兼容**：可以继续使用 HttpClient 4.x
- ✅ **灵活配置**：支持多种 HTTP 客户端，按需选择
- ✅ **平滑迁移**：无需修改代码，仅需配置，若不使用 HttpClient 5.x ，引入其他依赖即可
