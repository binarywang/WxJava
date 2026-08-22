# 微信小店保障单接口设计

## 目标

实现 Issue #3987 所列的微信小店售后保障单六个 API，并提供与现有 `weixin-java-channel` 售后模块一致、可离线验证的 Java SDK 接口。

## 范围

- 获取保障单列表。
- 获取保障单详情。
- 同意、协商、举证及拒绝保障单申请。
- 保障单请求与响应模型、URL 常量和离线单元测试。

不包含 Issue #4003 中其他售后端点，也不改变既有售后 API。

## API 设计

`WxChannelAfterSaleService` 新增六个方法。列表和详情返回专用响应对象；四个会产生状态变更的接口返回 `WxChannelBaseResponse`，以便调用方检查微信错误码和错误信息。

保障单号在公开 API 中使用 `String`，保持与现有售后单接口的用法一致，即使微信示例中它以 JSON number 表示。每个请求模型通过 `@JsonProperty` 显式映射到官方字段名。

列表参数覆盖官方的保障单号、订单号、申请时间、状态、偏移量与限制条数。详情和列表共用完整的保障单信息模型，包含基础字段、商品信息、支付信息、送礼信息以及两类保障申请的专用信息。模型对官方返回的未知字段保持前向兼容。

协商请求按官方字段发送 `bad_level` 和 `merchant_remark`；举证请求发送 `content` 与 `pic_list`；拒绝请求发送 `reason` 与必填的 `pic_list`。这修正旧 PR 中不符合文档的 `amount`、`desc` 和缺失图片凭证的问题。

## 实现结构

- `WxChannelApiUrlConstants.AfterSale`：六个官方路径常量。
- `WxChannelAfterSaleService`：六个带 Javadoc 文档链接的公开方法。
- `WxChannelAfterSaleServiceImpl`：每个方法调用对应路径，并用 `ResponseUtils.decode` 解码所有微信响应。
- `bean.after`：请求、响应和保障单嵌套信息模型；仅承担 JSON 映射，不含业务逻辑。

## 测试策略

测试不调用真实微信接口或依赖店铺凭据。使用 Mockito mock `BaseWxChannelServiceImpl`：验证每个方法使用正确 URL 和请求对象，并由固定 JSON 验证列表/详情响应的 Jackson 映射以及操作接口的基础响应解码。每个新增行为先以失败测试建立，再写最小实现使其通过。

## 质量与交付

构建至少执行 `mvn -pl weixin-java-channel -am test`，并执行目标模块的新增测试。提交信息使用中文。新 PR 目标分支为 `develop`，描述中包含 `Closes #3987`。新 PR 创建并验证后，关闭 #4035 并留下指向替代 PR 的说明。
