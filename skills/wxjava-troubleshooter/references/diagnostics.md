# 诊断清单

- 配置：模块是否正确，应用标识和凭据是否配对，多账号配置是否被正确选择。
- 请求：HTTP 方法、路径、必填字段和 JSON/XML 映射是否符合微信接口契约。
- 认证：token 缓存、签名字段、时间戳、nonce、证书和密钥格式是否有效。
- 网络：DNS、代理、超时、TLS 证书校验和响应关闭是否正常。
- 回调：回调 URL 可访问、验签参数完整、幂等处理和资源释放是否存在。

从精确异常和最小请求入手，不用“重试”替代根因分析。

## 高频场景

| 症状 | 优先检查 | 依据 |
| --- | --- | --- |
| 多节点间歇性 token 失效 | 是否共享 token/config storage，是否让各节点并发刷新 | Wiki 的 MP/CP 配置存储说明与集群案例 |
| `NoClassDefFoundError`、`NoSuchMethodError` | `mvn dependency:tree`、HttpClient/commons-lang/xstream 冲突及 BOM 是否统一版本 | Wiki 的异常排查页与 HTTP 客户端升级指南 |
| 回调验签或重复处理 | 原始请求体、时间戳/nonce/签名、回调 URL、业务幂等键 | MP 合法性校验与支付回调说明 |
| HTTP 连接、超时或代理问题 | 连接/读取超时、代理、TLS、正反向代理是否混用 | Wiki 的 HttpClient 参数与代理文档 |
| 企业微信会话存档崩溃 | 是否仍手动销毁旧 SDK 实例，是否已迁移安全 API | [会话存档安全使用指南](https://github.com/binarywang/WxJava/blob/develop/docs/CP_MSG_AUDIT_SDK_SAFE_USAGE.md) |
| 视频号/小店接口 404 或字段不匹配 | 当前官方路径与请求字段、目标 SDK 版本、历史 Issue 是否已修复 | 关闭的 bug Issue，如 [#3982](https://github.com/binarywang/WxJava/issues/3982) |

## 一手资料入口

- [常见异常首页](https://github.com/binarywang/WxJava/wiki)
- [MP 消息合法性验证](https://github.com/binarywang/WxJava/wiki/MP_%E9%AA%8C%E8%AF%81%E6%B6%88%E6%81%AF%E5%90%88%E6%B3%95%E6%80%A7)
- [HttpClient 参数配置](https://github.com/binarywang/WxJava/wiki/HttpClient%E7%9B%B8%E5%85%B3%E5%8F%82%E6%95%B0%E7%9A%84%E8%AE%BE%E7%BD%AE%E6%96%B9%E6%B3%95)
- [关闭的 bug Issue](https://github.com/binarywang/WxJava/issues?q=is%3Aissue%20state%3Aclosed%20label%3Abug)
