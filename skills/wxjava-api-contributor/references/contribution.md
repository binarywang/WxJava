# 贡献约定

以微信官方接口定义和本仓库同类实现为准，核对路径、方法、字段名、必填项和响应结构。优先复用既有 HTTP 执行器、异常、配置、Gson/Jackson/XStream 映射和 TestNG 测试模式。

公共方法需要准确 Javadoc。不要吞异常、记录敏感值或无关重构。新增公开方法和 bug 修复均应有针对性测试；涉及公共模块、BOM 或多模块时扩大验证范围。

## 接口增量检查表

1. 从微信官方文档和相关 Issue 中确认接口可用条件、HTTP 方法、URL、必填字段、签名/加密要求和响应示例。
2. 搜索同产品的相邻能力，复用其 Service 分层、Bean 命名、请求执行和错误处理模式；不要只新增 Bean 而遗漏 Service 暴露。
3. 若接口尚未支持，优先使用现有通用执行能力；MP/CP Wiki 说明通用执行器会处理 access token 刷新及 `errcode` 到异常的转换。
4. 为字段边界、空值、JSON/XML 映射和异常路径添加 TestNG 回归测试。API 路径或字段问题是历史 bug 的高频来源，例如 [#3982](https://github.com/binarywang/WxJava/issues/3982)、[#4000](https://github.com/binarywang/WxJava/issues/4000)。
5. 按 [贡献指南](../../../CONTRIBUTING.md) 使用 `develop` 作为 PR 目标；说明 Issue、兼容性影响和验证命令。

## 一手资料入口

- [如何调用 MP 未支持接口](https://github.com/binarywang/WxJava/wiki/MP_%E5%A6%82%E4%BD%95%E8%B0%83%E7%94%A8%E6%9C%AA%E6%94%AF%E6%8C%81%E7%9A%84%E6%8E%A5%E5%8F%A3)
- [如何调用 CP 未支持接口](https://github.com/binarywang/WxJava/wiki/CP_%E5%A6%82%E4%BD%95%E8%B0%83%E7%94%A8%E6%9C%AA%E6%94%AF%E6%8C%81%E7%9A%84%E6%8E%A5%E5%8F%A3)
- [关闭的新接口 Issue](https://github.com/binarywang/WxJava/issues?q=is%3Aissue%20state%3Aclosed%20label%3A%E6%96%B0%E6%8E%A5%E5%8F%A3)
