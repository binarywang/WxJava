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

### 响应类

- `WxCpIntelligentRobotCreateResponse`: 创建机器人响应
- `WxCpIntelligentRobotChatResponse`: 智能对话响应
- `WxCpIntelligentRobot`: 机器人信息实体

### 服务接口

- `WxCpIntelligentRobotService`: 智能机器人服务接口
- `WxCpIntelligentRobotServiceImpl`: 智能机器人服务实现

## 注意事项

1. 需要确保企业微信应用具有智能机器人相关权限
2. 智能机器人功能可能需要特定的企业微信版本支持
3. 会话ID可以用于保持对话的连续性，提升用户体验
4. 机器人状态: 0表示停用，1表示启用