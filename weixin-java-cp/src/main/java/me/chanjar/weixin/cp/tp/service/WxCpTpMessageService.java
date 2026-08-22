package me.chanjar.weixin.cp.tp.service;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.message.*;

/**
 * 企业微信第三方应用消息推送接口.
 *
 * <p>第三方应用使用授权企业的 access_token 代表授权企业发送应用消息。</p>
 *
 * @author <a href="https://github.com/github-copilot">GitHub Copilot</a>
 */
public interface WxCpTpMessageService {

  /**
   * <pre>
   * 发送应用消息（代授权企业发送）.
   * 详情请见: https://work.weixin.qq.com/api/doc/90000/90135/90236
   * </pre>
   *
   * @param message 要发送的消息对象
   * @param corpId  授权企业的 corpId
   * @return 消息发送结果
   * @throws WxErrorException 微信错误异常
   */
  WxCpMessageSendResult send(WxCpMessage message, String corpId) throws WxErrorException;

  /**
   * <pre>
   * 查询应用消息发送统计.
   * 请求方式：POST（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/message/get_statistics?access_token=ACCESS_TOKEN
   * 详情请见: https://work.weixin.qq.com/api/doc/90000/90135/92369
   * </pre>
   *
   * @param timeType 查询哪天的数据，0：当天；1：昨天。默认为0。
   * @param corpId   授权企业的 corpId
   * @return 统计结果
   * @throws WxErrorException 微信错误异常
   */
  WxCpMessageSendStatistics getStatistics(int timeType, String corpId) throws WxErrorException;

  /**
   * <pre>
   * 互联企业发送应用消息.
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/linkedcorp/message/send?access_token=ACCESS_TOKEN
   * 文章地址：https://work.weixin.qq.com/api/doc/90000/90135/90250
   * </pre>
   *
   * @param message 要发送的消息对象
   * @param corpId  授权企业的 corpId
   * @return 消息发送结果
   * @throws WxErrorException 微信错误异常
   */
  WxCpLinkedCorpMessageSendResult sendLinkedCorpMessage(WxCpLinkedCorpMessage message, String corpId) throws WxErrorException;

  /**
   * <pre>
   * 发送「学校通知」.
   * https://developer.work.weixin.qq.com/document/path/92321
   * 学校可以通过此接口来给家长发送不同类型的学校通知。
   * 请求方式：POST（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/externalcontact/message/send?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param message 要发送的消息对象
   * @param corpId  授权企业的 corpId
   * @return 消息发送结果
   * @throws WxErrorException 微信错误异常
   */
  WxCpSchoolContactMessageSendResult sendSchoolContactMessage(WxCpSchoolContactMessage message, String corpId) throws WxErrorException;

  /**
   * <pre>
   * 撤回应用消息.
   * 请求地址: https://qyapi.weixin.qq.com/cgi-bin/message/recall?access_token=ACCESS_TOKEN
   * 文档地址: https://developer.work.weixin.qq.com/document/path/94867
   * </pre>
   *
   * @param msgId  消息id
   * @param corpId 授权企业的 corpId
   * @throws WxErrorException 微信错误异常
   */
  void recall(String msgId, String corpId) throws WxErrorException;

}
