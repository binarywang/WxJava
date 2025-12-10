package me.chanjar.weixin.cp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.intelligentrobot.*;

/**
 * 企业微信智能机器人接口
 * 官方文档: https://developer.work.weixin.qq.com/document/path/101039
 *
 * @author Binary Wang
 */
public interface WxCpIntelligentRobotService {

  /**
   * 创建智能机器人
   *
   * @param request 创建请求参数
   * @return 创建结果
   * @throws WxErrorException 微信接口异常
   */
  WxCpIntelligentRobotCreateResponse createRobot(WxCpIntelligentRobotCreateRequest request) throws WxErrorException;

  /**
   * 删除智能机器人
   *
   * @param robotId 机器人ID
   * @throws WxErrorException 微信接口异常
   */
  void deleteRobot(String robotId) throws WxErrorException;

  /**
   * 更新智能机器人
   *
   * @param request 更新请求参数
   * @throws WxErrorException 微信接口异常
   */
  void updateRobot(WxCpIntelligentRobotUpdateRequest request) throws WxErrorException;

  /**
   * 查询智能机器人
   *
   * @param robotId 机器人ID
   * @return 机器人信息
   * @throws WxErrorException 微信接口异常
   */
  WxCpIntelligentRobot getRobot(String robotId) throws WxErrorException;

  /**
   * 智能机器人会话
   *
   * @param request 聊天请求参数
   * @return 聊天响应
   * @throws WxErrorException 微信接口异常
   */
  WxCpIntelligentRobotChatResponse chat(WxCpIntelligentRobotChatRequest request) throws WxErrorException;

  /**
   * 重置智能机器人会话
   *
   * @param robotId   机器人ID
   * @param userid    用户ID
   * @param sessionId 会话ID
   * @throws WxErrorException 微信接口异常
   */
  void resetSession(String robotId, String userid, String sessionId) throws WxErrorException;

}