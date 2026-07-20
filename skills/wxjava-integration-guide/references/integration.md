# 接入约束

优先通过 `wx-java-bom` 统一管理多个 WxJava 模块版本。核心 SDK 模块是 MP、MiniApp、Pay、CP、Open、Channel；框架集成模块位于 `spring-boot-starters` 与 `solon-plugins`。

查阅相应模块 README 和 `demo/` 的相邻示例，确认配置属性与初始化模式。所有 `appId`、`secret`、商户私钥、API v3 密钥和证书均使用占位符，不得输出到日志。

## 实施检查表

1. 先完成一条只读或低风险 API 调用，再接入消息、支付或异步回调。
2. 单实例可使用默认配置存储；多实例或集群需使用共享的 token/config storage，避免节点各自刷新 access token。
3. 公众号、企业微信等回调必须先按平台要求校验消息合法性，再进入业务路由；支付回调还必须按商户单号实现幂等。
4. 需要代理或私有网络出口时，明确区分正向代理与反向代理。支付 V3 的签名路径不能因反向代理路径前缀而被错误改写。
5. HTTP 客户端类型、Starter 配置键和 Service 实现必须取自目标模块的当前 README、POM 或相邻 Demo；不要混用不同产品模块的配置前缀。
6. Starter 自动配置与 Demo 手动初始化必须二选一后再给示例。发生空 key、注入为空或 NPE 时，先核对启动模块、profile、配置前缀和当前 `*Properties` 类；历史 [#2177](https://github.com/binarywang/WxJava/issues/2177) 是混用两种配置模型的案例。
7. Quarkus 或 GraalVM 场景转到 [Quarkus 支持文档](../../../docs/QUARKUS_SUPPORT.md)，不要套用 Spring Boot Starter 配置。

## 一手资料入口

- [MP Quick Start](https://github.com/binarywang/WxJava/wiki/MP_Quick-Start)
- [微信支付说明](https://github.com/binarywang/WxJava/wiki/%E5%BE%AE%E4%BF%A1%E6%94%AF%E4%BB%98)
- [SDK 正反向代理支持](https://github.com/binarywang/WxJava/wiki/SDK-%E9%92%88%E5%AF%B9%E5%BE%AE%E4%BF%A1-%E6%AD%A3%E5%90%91%E4%BB%A3%E7%90%86%E5%92%8C%E5%8F%8D%E5%90%91%E4%BB%A3%E7%90%86%E6%94%AF%E6%8C%81)
- [HTTP 客户端升级指南](../../../docs/HTTPCLIENT_UPGRADE_GUIDE.md)
