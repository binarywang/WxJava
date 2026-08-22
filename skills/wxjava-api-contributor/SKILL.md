---
name: wxjava-api-contributor
description: 按 WxJava 的 Maven 多模块、Java 8、公共 API 兼容性和 TestNG 约定，为微信官方接口新增或维护 SDK 支持。适用于新增 Service API、请求响应 Bean、序列化、HTTP 实现、Starter 配置或回归测试时。
---

# WxJava 接口贡献

1. 先搜索开放与已关闭 Issue，确认需求是否已有讨论、实现、回归用例或官方接口变动；再确认微信产品和目标模块。
2. 阅读对应 README、POM、相似接口、实现与测试，并读取 [贡献约定](references/contribution.md)。
3. 将官方接口契约映射到 Service、实现、Bean、URL、序列化和测试；列出所有可能受影响的 HTTP 客户端和 Starter。
4. 只做完成需求所需的最小改动；更新必要 Javadoc、测试与用户可见文档。
5. 执行 `mvn -pl <module> -am test`，并检查 `git diff --check`。

保持 Java 8 与公共 API、异常语义、JSON/XML 字段兼容性。涉及多 HTTP 客户端、单/多账号 Starter 或 Solon 插件时，检查等价实现是否需要同步。PR 应关联对应 Issue，目标分支为 `develop`。
