package me.chanjar.weixin.cp.api.impl;

import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.api.ApiTestModule;
import me.chanjar.weixin.cp.bean.intelligentrobot.*;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import javax.inject.Inject;

/**
 * 智能机器人接口测试
 *
 * @author Binary Wang
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxCpIntelligentRobotServiceImplTest {

  @Inject
  private WxCpService wxCpService;

  @Test
  public void testCreateRobot() {
    // 测试创建智能机器人请求对象创建
    WxCpIntelligentRobotCreateRequest request = new WxCpIntelligentRobotCreateRequest();
    request.setName("测试机器人");
    request.setDescription("这是一个测试的智能机器人");
    request.setAvatar("avatar_url");

    // 验证JSON序列化
    String json = request.toJson();
    assert json.contains("测试机器人");
    assert json.contains("这是一个测试的智能机器人");
    
    // 验证反序列化
    WxCpIntelligentRobotCreateRequest fromJson = WxCpIntelligentRobotCreateRequest.fromJson(json);
    assert fromJson.getName().equals("测试机器人");
  }

  @Test
  public void testChatRequest() {
    // 测试聊天请求对象创建
    WxCpIntelligentRobotChatRequest request = new WxCpIntelligentRobotChatRequest();
    request.setRobotId("robot123");
    request.setUserid("user123");
    request.setMessage("你好，机器人");
    request.setSessionId("session123");

    // 验证JSON序列化
    String json = request.toJson();
    assert json.contains("robot123");
    assert json.contains("你好，机器人");
    
    // 验证反序列化
    WxCpIntelligentRobotChatRequest fromJson = WxCpIntelligentRobotChatRequest.fromJson(json);
    assert fromJson.getRobotId().equals("robot123");
    assert fromJson.getMessage().equals("你好，机器人");
  }

  @Test
  public void testUpdateRequest() {
    // 测试更新请求对象创建
    WxCpIntelligentRobotUpdateRequest request = new WxCpIntelligentRobotUpdateRequest();
    request.setRobotId("robot123");
    request.setName("更新后的机器人");
    request.setDescription("更新后的描述");
    request.setStatus(1);

    // 验证JSON序列化
    String json = request.toJson();
    assert json.contains("robot123");
    assert json.contains("更新后的机器人");
    
    // 验证反序列化
    WxCpIntelligentRobotUpdateRequest fromJson = WxCpIntelligentRobotUpdateRequest.fromJson(json);
    assert fromJson.getRobotId().equals("robot123");
    assert fromJson.getName().equals("更新后的机器人");
    assert fromJson.getStatus().equals(1);
  }

  @Test
  public void testServiceIntegration() {
    // 验证服务可以正确获取
    assert this.wxCpService.getIntelligentRobotService() != null;
    assert this.wxCpService.getIntelligentRobotService() instanceof WxCpIntelligentRobotServiceImpl;
  }
}