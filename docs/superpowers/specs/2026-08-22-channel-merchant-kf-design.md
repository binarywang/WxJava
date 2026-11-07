# 视频号小店商家客服 API 设计

## 目标

实现 Issue #3991 所列的商家客服媒体上传与消息发送 API，并使调用入口、请求模型和响应解析与现有 `weixin-java-channel` 服务保持一致。

## 设计

- 在 `WxChannelService` 暴露 `getKfService()`，由 `BaseWxChannelServiceImpl` 缓存并懒加载 `WxChannelKfServiceImpl`。
- `WxChannelKfService` 提供媒体上传（带文件名和便捷重载）以及接收强类型请求参数的消息发送方法。上传请求通过既有 `CommonUploadParam` 发送 multipart 数据。
- 消息模型用 `@JsonProperty` 显式映射微信字段，支持 text、image、video、file、product_share 和 order_share 六类内容；响应继承项目既有基础响应。
- API 常量使用官方文档确认的 `/channels/ec/commkf/cosupload` 和 `/channels/ec/commkf/sendmsg` 路径。

## 质量边界

- 保持 Java 8 兼容，不增加依赖，不变更现有公共 API。
- 使用 TestNG 覆盖请求/响应 JSON 映射、服务 URL、上传表单字段及服务入口缓存；测试不依赖真实微信凭据。
- PR 使用 `Closes #3991` 关联并关闭原始 Issue；旧 PR #4037 在新 PR 创建后以替代说明关闭。
