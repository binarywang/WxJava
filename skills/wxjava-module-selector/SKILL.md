---
name: wxjava-module-selector
description: 根据微信公众号、小程序、微信支付、企业微信、开放平台、视频号或微信小店等业务场景，为用户选择合适的 WxJava Maven 模块、BOM 和示例入口。适用于用户询问“该用哪个模块”、依赖坐标、产品边界或单/多账号 Starter 选择时。
---

# WxJava 模块选择

1. 识别微信产品、服务端框架和是否需要多账号；信息不足时只询问必要问题。
2. 读取 [模块映射](references/modules.md)，给出一个主推荐，以及组合模块的理由。
3. 优先推荐 BOM；给出准确的 `groupId`、`artifactId` 和相应 Demo 或 README。
4. 说明服务端 SDK 的边界：移动端登录、分享等能力仍需微信官方客户端 SDK。
5. 不臆测版本号；建议以 Maven Central 或项目 README 的当前版本为准。

使用“场景 → 模块 → 依赖 → 下一步”的简短结构。涉及多账号时说明单账号与 multi Starter 的区别；不要在示例中泄露凭据。
