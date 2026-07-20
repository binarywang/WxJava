# 迁移检查表

- 使用多个 WxJava 模块时，优先迁移到 `wx-java-bom`，避免模块版本漂移。
- 清点 MP、MiniApp、Pay、CP、Open、Channel 及框架 Starter/插件的实际依赖。
- 更新后先执行编译和受影响模块测试，再验证 token、回调、支付和关键业务链路。
- 为依赖版本保留可快速恢复的变更记录；按灰度策略验证生产环境，且不将凭据写入代码或日志。

## 升级分支

### HTTP 客户端

从 4.7.x 起，项目支持并推荐 Apache HttpClient 5.x，同时保留部分 4.x 兼容性。先按 [HTTP 客户端升级指南](https://github.com/binarywang/WxJava/blob/develop/docs/HTTPCLIENT_UPGRADE_GUIDE.md) 核对模块、依赖与 `http-client-type`，再检查代理 host、port、username、password 的完整性。历史 [#3836](https://github.com/binarywang/WxJava/issues/3836) 表明可选代理配置也需要回归。

### BOM 与依赖冲突

`wx-java-bom` 从 4.8.3.B 起提供。导入 BOM 前后均执行：

```shell
mvn help:effective-pom
mvn dependency:tree
```

特别回归由 Spring Boot BOM 管理的 Redis、HTTP 客户端和序列化依赖；[#4058](https://github.com/binarywang/WxJava/issues/4058) 是历史冲突案例。

### 企业微信会话存档

升级到 4.8.0 或更高版本时，查找旧的 `getChatDatas`、`getDecryptData`、`getChatPlainText`、`getMediaFile` 和手动 `Finance.DestroySdk()`。按 [ThreadLocal 生命周期迁移文档](https://github.com/binarywang/WxJava/blob/develop/docs/CP_MSG_AUDIT_THREADLOCAL_LIFECYCLE_REFACTOR.md) 切换至新 API，并做并发验证。

ThreadLocal SDK 不会在线程结束时自动释放。在线程池、定时任务或一次性线程中，必须在任务的 `finally` 块调用 `msgAuditService.closeThreadLocalSdk()`；应用停止时再用 `closeAllSdks()` 做全局兜底。不要在业务代码中直接调用 `Finance.DestroySdk()`。

### 回调与支付

验证不止于 HTTP 2xx：分别覆盖 token 获取、签名/证书校验、重复回调、业务状态转换和多账号路由。多账号异步任务必须显式传递 appId 或服务上下文，不能假定 ThreadLocal 自动继承。

## 验证与回滚

按“编译和单测 → 非生产凭据冒烟 → 灰度和监控”执行。出现依赖树冲突、验签/证书异常、回调错误或 native 崩溃时停止扩大灰度，恢复上一个已验证依赖组合后再以最小复现定位。
