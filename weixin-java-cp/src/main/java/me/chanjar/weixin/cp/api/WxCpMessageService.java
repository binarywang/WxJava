package me.chanjar.weixin.cp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.message.*;

/**
 * 消息推送接口.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a> created on  2020 -08-30
 */
public interface WxCpMessageService {
  /**
   * <pre>
   * 发送消息
   * 详情请见: https://work.weixin.qq.com/api/doc/90000/90135/90236
   * </pre>
   *
   * @param message 要发送的消息对象
   * @return the wx cp message send result
   * @throws WxErrorException the wx error exception
   */
  WxCpMessageSendResult send(WxCpMessage message) throws WxErrorException;

  /**
   * <pre>
   * 查询应用消息发送统计
   * 请求方式：POST（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/message/get_statistics?access_token=ACCESS_TOKEN
   *
   * 详情请见: https://work.weixin.qq.com/api/doc/90000/90135/92369
   * </pre>
   *
   * @param timeType 查询哪天的数据，0：当天；1：昨天。默认为0。
   * @return 统计结果 statistics
   * @throws WxErrorException the wx error exception
   */
  WxCpMessageSendStatistics getStatistics(int timeType) throws WxErrorException;

  /**
   * <pre>
   * 互联企业的应用支持推送文本、图片、视频、文件、图文等类型。
   *
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/linkedcorp/message/send?access_token=ACCESS_TOKEN
   * 文章地址：https://work.weixin.qq.com/api/doc/90000/90135/90250
   * </pre>
   *
   * @param message 要发送的消息对象
   * @return the wx cp message send result
   * @throws WxErrorException the wx error exception
   */
  WxCpLinkedCorpMessageSendResult sendLinkedCorpMessage(WxCpLinkedCorpMessage message) throws WxErrorException;

  /**
   * 发送「学校通知」
   * https://developer.work.weixin.qq.com/document/path/92321
   * <p>
   * 学校可以通过此接口来给家长发送不同类型的学校通知，来满足多种场景下的学校通知需求。目前支持的消息类型为文本、图片、语音、视频、文件、图文。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址： https://qyapi.weixin.qq.com/cgi-bin/externalcontact/message/send?access_token=ACCESS_TOKEN
   *
   * @param message 要发送的消息对象
   * @return wx cp school contact message send result
   * @throws WxErrorException the wx error exception
   */
  WxCpSchoolContactMessageSendResult sendSchoolContactMessage(WxCpSchoolContactMessage message) throws WxErrorException;

  /**
   * <pre>
   * 撤回应用消息
   *
   * 请求地址: https://qyapi.weixin.qq.com/cgi-bin/message/recall?access_token=ACCESS_TOKEN
   * 文档地址: https://developer.work.weixin.qq.com/document/path/94867
   * </pre>
   *
   * @param msgId 消息id
   * @throws WxErrorException 异常
   */
  void recall(String msgId) throws WxErrorException;

}
