---
name: wxjava-integration-guide
description: 为 Java、Spring Boot 或 Solon 项目生成可验证的 WxJava 接入方案，包括模块选择、BOM、配置、最小调用代码以及单/多账号集成。适用于用户要求接入公众号、小程序、支付、企业微信、开放平台、视频号或微信小店时。
---

# WxJava 接入指南

1. 确认微信产品、框架、单/多账号和首个 API 调用。
2. 读取 [接入约束](references/integration.md)，选择模块和配置方式。
3. 输出可复制的依赖、脱敏配置和最小服务端代码；凭据一律用占位符。
4. 给出本地验证步骤与安全提醒；支付、回调和证书示例不得直接用于生产。

保持 Java 8 兼容。引用已有 Demo 或 README 作为继续阅读入口；不虚构配置键、SDK 方法或版本号。
