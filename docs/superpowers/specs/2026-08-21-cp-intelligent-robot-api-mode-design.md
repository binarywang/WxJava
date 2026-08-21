# 企业微信智能机器人 API 模式支持设计

## 目标

让 `weixin-java-cp` 能接入企业微信当前的智能机器人 API 模式：解密回调 JSON、解析消息，并将加密回复 POST 到回调给出的 `response_url`。

## 范围与边界

- 保留已有基于企业应用 `access_token` 的 `WxCpIntelligentRobotService` 方法，避免破坏兼容性；这些方法不用于新版 API 模式。
- 新增独立的 API 模式密码工具，使用机器人后台配置的 Token、EncodingAESKey 和机器人 ID（作为接收方标识），不依赖 `WxCpConfigStorage` 的 `secret`。
- 回调入口将加密 JSON 信封解密成明文 JSON，再交给已有 `WxCpIntelligentRobotMessage` 解析。
- 回复 API 接收 `response_url` 与明文业务 JSON，完成加密、签名和 POST；它不追加 `access_token`。
- 文档只展示新版 API 模式的正确链路，并明确旧服务的方法边界。

## 接口设计

新增 `WxCpIntelligentRobotCryptUtil(token, encodingAesKey, aiBotId)`：

- `decrypt(msgSignature, timestamp, nonce, encryptedJson)` 返回回调明文 JSON；
- `encrypt(plainJson, timestamp, nonce)` 返回可直接 POST 的加密 JSON 信封；
- `verifyUrl(msgSignature, timestamp, nonce, echoStr)` 验证 URL 时解密 echo 字段。

在 `WxCpIntelligentRobotService` 新增：

- `parseEncryptedCallbackMessage(...)`，解密后返回 `WxCpIntelligentRobotMessage`；
- `replyMessage(responseUrl, plainJson, ...)`，将加密响应 POST 到临时 URL。

## 验证

单测覆盖已知密文回调可解密并解析，及加密后的回复可被同一配置解密回原文；HTTP 层以本地 mock 服务验证不携带 access token、正文为加密 JSON。模块测试与格式检查通过。
