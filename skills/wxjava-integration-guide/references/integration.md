# 接入约束

优先通过 `wx-java-bom` 统一管理多个 WxJava 模块版本。核心 SDK 模块是 MP、MiniApp、Pay、CP、Open、Channel；框架集成模块位于 `spring-boot-starters` 与 `solon-plugins`。

查阅相应模块 README 和 `demo/` 的相邻示例，确认配置属性与初始化模式。所有 `appId`、`secret`、商户私钥、API v3 密钥和证书均使用占位符，不得输出到日志。
