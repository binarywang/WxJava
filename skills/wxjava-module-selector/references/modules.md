# WxJava 模块映射

| 场景 | 核心模块 |
| --- | --- |
| 微信公众号 | `weixin-java-mp` |
| 微信小程序 | `weixin-java-miniapp` |
| 微信支付 | `weixin-java-pay` |
| 企业微信 | `weixin-java-cp` |
| 微信开放平台／第三方平台 | `weixin-java-open` |
| 视频号／微信小店 | `weixin-java-channel` |

多个模块并用时优先使用 `com.github.binarywang:wx-java-bom`。Spring Boot 集成从 `spring-boot-starters` 选择；Solon 集成从 `solon-plugins` 选择。只有多个独立微信应用配置时才选择名称含 `multi` 的 Starter 或插件。
