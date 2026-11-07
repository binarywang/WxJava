# 企业微信智能机器人接口

本模块提供企业微信智能机器人相关的 API 接口实现。

> `createRobot`、`chat`、`sendMessage` 等既有方法走企业应用 `access_token` 接口，
> 需要在 `WxCpConfigStorage` 中配置应用 `agentId` 和 `secret`。它们不适用于机器人后台创建的新版 API 模式。

## 官方文档

- [企业微信智能机器人接口](https://developer.work.weixin.qq.com/document/path/101039)

## 接口说明

### 获取服务实例

```java
WxCpService wxCpService = ...; // 初始化企业微信服务
WxCpIntelligentRobotService robotService = wxCpService.getIntelligentRobotService();
```

### 创建智能机器人

```java
WxCpIntelligentRobotCreateRequest request = new WxCpIntelligentRobotCreateRequest();
request.setName("我的智能机器人");
request.setDescription("这是一个智能客服机器人");
request.setAvatar("http://example.com/avatar.jpg");

WxCpIntelligentRobotCreateResponse response = robotService.createRobot(request);
String robotId = response.getRobotId();
```

### 更新智能机器人

```java
WxCpIntelligentRobotUpdateRequest request = new WxCpIntelligentRobotUpdateRequest();
request.setRobotId("robot_id_here");
request.setName("更新后的机器人名称");
request.setDescription("更新后的描述");
request.setStatus(1); // 1:启用, 0:停用

robotService.updateRobot(request);
```

### 查询智能机器人

```java
String robotId = "robot_id_here";
WxCpIntelligentRobot robot = robotService.getRobot(robotId);

System.out.println("机器人名称: " + robot.getName());
System.out.println("机器人状态: " + robot.getStatus());
```

### 智能对话

```java
WxCpIntelligentRobotChatRequest request = new WxCpIntelligentRobotChatRequest();
request.setRobotId("robot_id_here");
request.setUserid("user123");
request.setMessage("你好，请问如何使用这个功能？");
request.setSessionId("session123"); // 可选，用于保持会话连续性

WxCpIntelligentRobotChatResponse response = robotService.chat(request);
String reply = response.getReply();
String sessionId = response.getSessionId();
```

### 重置会话

```java
String robotId = "robot_id_here";
String userid = "user123";
String sessionId = "session123";

robotService.resetSession(robotId, userid, sessionId);
```

### 旧版 access_token 主动发送消息

智能机器人可以主动向用户发送消息，用于推送通知或提醒。

```java
WxCpIntelligentRobotSendMessageRequest request = new WxCpIntelligentRobotSendMessageRequest();
request.setRobotId("robot_id_here");
request.setUserid("user123");
request.setMessage("您好，这是来自智能机器人的主动消息");
request.setSessionId("session123"); // 可选，用于保持会话连续性

WxCpIntelligentRobotSendMessageResponse response = robotService.sendMessage(request);
String msgId = response.getMsgId();
String sessionId = response.getSessionId();
```

### 新版 API 模式：接收回调与回复消息

在机器人后台开启 API 模式后，配置 URL、Token、EncodingAESKey。企业微信会推送加密 JSON 回调；
它不是 XML，也不需要企业应用 `secret`。从请求参数取得 `msg_signature`、`timestamp`、`nonce`，
从请求体取得 `encrypt` 字段后，可以直接解密和解析：

```java
WxCpIntelligentRobotMessage callbackMessage =
    robotService.parseEncryptedCallbackMessage(
        msgSignature, timestamp, nonce, encryptedJson,
        token, encodingAesKey, aiBotId);

String responseUrl = callbackMessage.getResponseUrl();
String content = callbackMessage.getText().getContent();
```

回复时使用回调中的短期 `response_url`，不调用基于 `access_token` 的 `sendMessage`：

```java
String replyJson = "{\"msgtype\":\"text\",\"text\":{\"content\":\"您好\"}}";
robotService.replyMessage(
    responseUrl, replyJson, token, encodingAesKey, aiBotId,
    String.valueOf(System.currentTimeMillis() / 1000), java.util.UUID.randomUUID().toString());
```

### 删除智能机器人

```java
String robotId = "robot_id_here";
robotService.deleteRobot(robotId);
```

## 主要类说明

### 请求类

- `WxCpIntelligentRobotCreateRequest`: 创建机器人请求
- `WxCpIntelligentRobotUpdateRequest`: 更新机器人请求  
- `WxCpIntelligentRobotChatRequest`: 智能对话请求
- `WxCpIntelligentRobotSendMessageRequest`: 主动发送消息请求

### 响应类

- `WxCpIntelligentRobotCreateResponse`: 创建机器人响应
- `WxCpIntelligentRobotChatResponse`: 智能对话响应
- `WxCpIntelligentRobotSendMessageResponse`: 主动发送消息响应
- `WxCpIntelligentRobot`: 机器人信息实体

### 消息接收

- `WxCpIntelligentRobotMessage`: 智能机器人 API 模式的已解密 JSON 回调消息
- `WxCpIntelligentRobotCryptUtil`: 智能机器人 API 模式的消息加解密工具

### 服务接口

- `WxCpIntelligentRobotService`: 智能机器人服务接口
- `WxCpIntelligentRobotServiceImpl`: 智能机器人服务实现

## 注意事项

1. 新版 API 模式的 Token、EncodingAESKey 和机器人 ID 由机器人后台配置，不要填写企业应用 secret。
2. `response_url` 是回调附带的临时地址，应及时使用，且不应持久化。
3. `parseCallbackMessage` 仅用于已解密的 JSON；HTTP 回调入口应使用 `parseEncryptedCallbackMessage`。
