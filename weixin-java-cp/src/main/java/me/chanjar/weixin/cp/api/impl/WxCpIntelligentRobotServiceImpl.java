package me.chanjar.weixin.cp.api.impl;

import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpIntelligentRobotService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.intelligentrobot.*;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.IntelligentRobot.*;

/**
 * 企业微信智能机器人接口实现
 *
 * @author Binary Wang
 */
@RequiredArgsConstructor
public class WxCpIntelligentRobotServiceImpl implements WxCpIntelligentRobotService {
  
  private final WxCpService cpService;

  @Override
  public WxCpIntelligentRobotCreateResponse createRobot(WxCpIntelligentRobotCreateRequest request) throws WxErrorException {
    String responseText = this.cpService.post(CREATE_ROBOT, request.toJson());
    return WxCpIntelligentRobotCreateResponse.fromJson(responseText);
  }

  @Override
  public void deleteRobot(String robotId) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("robot_id", robotId);
    this.cpService.post(DELETE_ROBOT, jsonObject.toString());
  }

  @Override
  public void updateRobot(WxCpIntelligentRobotUpdateRequest request) throws WxErrorException {
    this.cpService.post(UPDATE_ROBOT, request.toJson());
  }

  @Override
  public WxCpIntelligentRobot getRobot(String robotId) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("robot_id", robotId);
    String responseText = this.cpService.post(GET_ROBOT, jsonObject.toString());
    return WxCpIntelligentRobot.fromJson(responseText);
  }

  @Override
  public WxCpIntelligentRobotChatResponse chat(WxCpIntelligentRobotChatRequest request) throws WxErrorException {
    String responseText = this.cpService.post(CHAT, request.toJson());
    return WxCpIntelligentRobotChatResponse.fromJson(responseText);
  }

  @Override
  public void resetSession(String robotId, String userid, String sessionId) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("robot_id", robotId);
    jsonObject.addProperty("userid", userid);
    jsonObject.addProperty("session_id", sessionId);
    this.cpService.post(RESET_SESSION, jsonObject.toString());
  }

}