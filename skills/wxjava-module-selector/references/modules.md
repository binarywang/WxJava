# WxJava 模块映射

| 场景 | 核心模块 |
| --- | --- |
| 微信公众号 | `weixin-java-mp` |
| 微信小程序 | `weixin-java-miniapp` |
| 微信支付 | `weixin-java-pay` |
| 企业微信 | `weixin-java-cp` |
| 微信开放平台／第三方平台 | `weixin-java-open` |
| 视频号／微信小店 | `weixin-java-channel` |
| 腾讯企点／微信客服 | `weixin-java-qidian` |
| 微信智能对话／智能语音 | `weixin-java-aispeech` |

多个模块并用时优先使用 `com.github.binarywang:wx-java-bom`。Spring Boot 集成从 `spring-boot-starters` 选择；Solon 集成从 `solon-plugins` 选择。

## 多账号集成范围

只有多个独立微信应用配置时才选择 multi 集成，并先按框架与产品核对目录是否存在：

- Spring Boot：MP、MiniApp、CP、自建/第三方 CP、Pay、Open、Channel 都有相应 multi Starter。
- Solon：仅 MP、MiniApp、CP、Channel 有 multi 插件；Pay、Open、Qidian 目前只有单账号插件，不要推荐不存在的 multi artifact。

## 选择检查点

- 公众号、小程序和企业微信的消息回调、token 与加解密配置彼此独立；不要因同属一个公司而复用不兼容的凭据或配置对象。
- 企业微信的多应用应使用独立的 `WxCpConfigStorage` 与 `WxCpServiceImpl`；Wiki 明确指出复用 token、AES key 和 URL 会造成安全边界问题。
- 支付能力通常与 MP、MiniApp 或 Open 同时使用：前者处理业务身份和消息，`weixin-java-pay` 处理商户签名、证书与支付回调。
- 视频号／微信小店接口属于 `weixin-java-channel`；不要误归入 MP 或 Pay。
- 腾讯企点与微信智能对话是独立 SDK 模块；不要将客服或智能对话需求默认归入 MP、CP 或 Channel。
- 当能力在 MP 与 Open 等模块可能重叠时，按授权主体、官方 API 域和回调场景选择，不要只按“移动端”或“登录”字样判断。先在当前源码和 Issue 中确认覆盖状态，并标注“已确认 / 待查 / 需自行调用底层接口”。
- BOM 适合同时使用多个 WxJava 模块。若项目还导入 Spring Boot 等上游 BOM，升级后执行 `mvn help:effective-pom` 与 `mvn dependency:tree`；历史 [#4058](https://github.com/binarywang/WxJava/issues/4058) 表明依赖管理顺序可能影响 Spring Data Redis 等依赖。

## 一手资料入口

- [WxJava Wiki 首页](https://github.com/binarywang/WxJava/wiki)
- [企业微信多应用配置](https://github.com/binarywang/WxJava/wiki/CP_%E5%A6%82%E4%BD%95%E6%94%AF%E6%8C%81%E5%A4%9A%E4%B8%AA%E4%BC%81%E4%B8%9A%E5%8F%B7%E5%BA%94%E7%94%A8%E6%88%96%E4%BC%81%E4%B8%9A%E5%8F%B7)
- [视频号／微信小店开发文档](https://github.com/binarywang/WxJava/wiki/0_%E8%A7%86%E9%A2%91%E5%8F%B7_%E5%BE%AE%E4%BF%A1%E5%B0%8F%E5%BA%97%E5%BC%80%E5%8F%91%E6%96%87%E6%A1%A3)
