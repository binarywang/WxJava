# 企业微信智能机器人接口

本模块提供企业微信智能机器人相关的API接口实现。

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

### 主动发送消息

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

### 接收用户消息

当用户向智能机器人发送消息时，企业微信会通过回调接口推送消息。可以使用 `WxCpXmlMessage` 接收和解析这些消息：

```java
// 在接收回调消息的接口中
WxCpXmlMessage message = WxCpXmlMessage.fromEncryptedXml(
    requestBody, wxCpConfigStorage, timestamp, nonce, msgSignature
);

// 获取智能机器人相关字段
String robotId = message.getRobotId();        // 机器人ID
String sessionId = message.getSessionId();    // 会话ID
String content = message.getContent();         // 消息内容
String fromUser = message.getFromUserName();   // 发送用户

// 处理消息并回复
// ...
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

- `WxCpXmlMessage`: 支持接收智能机器人回调消息，包含 `robotId` 和 `sessionId` 字段

### 服务接口

- `WxCpIntelligentRobotService`: 智能机器人服务接口
- `WxCpIntelligentRobotServiceImpl`: 智能机器人服务实现

## 注意事项

1. 需要确保企业微信应用具有智能机器人相关权限
2. 智能机器人功能可能需要特定的企业微信版本支持
3. 会话ID可以用于保持对话的连续性，提升用户体验
4. 机器人状态: 0表示停用，1表示启用