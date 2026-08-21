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

  /**
   * 智能机器人主动发送消息
   * 官方文档: https://developer.work.weixin.qq.com/document/path/100719
   *
   * @param request 发送消息请求参数
   * @return 发送消息响应
   * @throws WxErrorException 微信接口异常
   */
  WxCpIntelligentRobotSendMessageResponse sendMessage(WxCpIntelligentRobotSendMessageRequest request) throws WxErrorException;

  /**
   * 解析智能机器人 API 模式回调消息.
   *
   * @param callbackMessageJson 回调消息JSON
   * @return 解析后的回调消息对象
   */
  WxCpIntelligentRobotMessage parseCallbackMessage(String callbackMessageJson);

  /**
   * 解密并解析智能机器人 API 模式回调消息.
   *
   * @param msgSignature   回调 URL 参数中的签名
   * @param timestamp      回调 URL 参数中的时间戳
   * @param nonce          回调 URL 参数中的随机串
   * @param encryptedJson  回调 JSON 信封中的 encrypt 字段
   * @param token          机器人后台配置的 Token
   * @param encodingAesKey 机器人后台配置的 EncodingAESKey
   * @param aiBotId        机器人 ID
   * @return 解密并解析后的回调消息
   */
  WxCpIntelligentRobotMessage parseEncryptedCallbackMessage(String msgSignature, String timestamp, String nonce,
                                                             String encryptedJson, String token, String encodingAesKey,
                                                             String aiBotId);

  /**
   * 加密并向智能机器人 API 模式的临时 response_url 回复消息.
   *
   * @param responseUrl    回调消息中的 response_url
   * @param plainJson      回复的明文 JSON
   * @param token          机器人后台配置的 Token
   * @param encodingAesKey 机器人后台配置的 EncodingAESKey
   * @param aiBotId        机器人 ID
   * @param timestamp      回复时间戳
   * @param nonce          回复随机串
   * @return 企业微信响应内容
   * @throws WxErrorException 微信接口异常
   */
  String replyMessage(String responseUrl, String plainJson, String token, String encodingAesKey, String aiBotId,
                      String timestamp, String nonce) throws WxErrorException;

}
