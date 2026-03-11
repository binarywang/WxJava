package me.chanjar.weixin.cp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.article.NewArticle;
import me.chanjar.weixin.cp.bean.message.WxCpGroupRobotMessage;

import java.util.List;

/**
 * 微信群机器人消息发送api
 * 文档地址：https://work.weixin.qq.com/help?doc_id=13376
 * 调用地址：https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=
 *
 * @author yr  created on  2020-8-20
 */
public interface WxCpGroupRobotService {

  /**
   * 发送text类型的消息
   *
   * @param content       文本内容，最长不超过2048个字节，必须是utf8编码
   * @param mentionedList userId的列表，提醒群中的指定成员(@某个成员)，@all表示提醒所有人，如果开发者获取不到userId，可以使用mentioned_mobile_list
   * @param mobileList    手机号列表，提醒手机号对应的群成员(@某个成员)，@all表示提醒所有人
   * @throws WxErrorException 异常
   */
  void sendText(String content, List<String> mentionedList, List<String> mobileList) throws WxErrorException;

  /**
   * 发送markdown类型的消息
   *
   * @param content markdown内容，最长不超过4096个字节，必须是utf8编码
   * @throws WxErrorException 异常
   */
  void sendMarkdown(String content) throws WxErrorException;

  /**
   * 发送image类型的消息
   *
   * @param base64 图片内容的base64编码
   * @param md5    图片内容（base64编码前）的md5值
   * @throws WxErrorException 异常
   */
  void sendImage(String base64, String md5) throws WxErrorException;

  /**
   * 发送news类型的消息
   *
   * @param articleList 图文消息，支持1到8条图文
   * @throws WxErrorException 异常
   */
  void sendNews(List<NewArticle> articleList) throws WxErrorException;

  /**
   * 发送text类型的消息
   *
   * @param webhookUrl    webhook地址
   * @param content       文本内容，最长不超过2048个字节，必须是utf8编码
   * @param mentionedList userId的列表，提醒群中的指定成员(@某个成员)，@all表示提醒所有人，如果开发者获取不到userId，可以使用mentioned_mobile_list
   * @param mobileList    手机号列表，提醒手机号对应的群成员(@某个成员)，@all表示提醒所有人
   * @throws WxErrorException 异常
   */
  void sendText(String webhookUrl, String content, List<String> mentionedList, List<String> mobileList) throws WxErrorException;

  /**
   * 发送markdown类型的消息
   *
   * @param webhookUrl webhook地址
   * @param content    markdown内容，最长不超过4096个字节，必须是utf8编码
   * @throws WxErrorException 异常
   */
  void sendMarkdown(String webhookUrl, String content) throws WxErrorException;

  /**
   * 发送markdown_v2类型的消息
   *
   * @param content markdown内容，最长不超过4096个字节，必须是utf8编码
   * @throws WxErrorException 异常
   */
  void sendMarkdownV2(String content) throws WxErrorException;

  /**
   * 发送markdown_v2类型的消息
   *
   * @param webhookUrl webhook地址
   * @param content    markdown内容，最长不超过4096个字节，必须是utf8编码
   * @throws WxErrorException 异常
   */
  void sendMarkdownV2(String webhookUrl, String content) throws WxErrorException;

  /**
   * 发送image类型的消息
   *
   * @param webhookUrl webhook地址
   * @param base64     图片内容的base64编码
   * @param md5        图片内容（base64编码前）的md5值
   * @throws WxErrorException 异常
   */
  void sendImage(String webhookUrl, String base64, String md5) throws WxErrorException;

  /**
   * 发送news类型的消息
   *
   * @param webhookUrl  webhook地址
   * @param articleList 图文消息，支持1到8条图文
   * @throws WxErrorException 异常
   */
  void sendNews(String webhookUrl, List<NewArticle> articleList) throws WxErrorException;

  /**
   * 发送文件类型的消息
   *
   * @param webhookUrl webhook地址
   * @param mediaId    文件id
   * @throws WxErrorException 异常
   */
  void sendFile(String webhookUrl, String mediaId) throws WxErrorException;

  /**
   * 发送文件类型的消息
   *
   * @param webhookUrl webhook地址
   * @param mediaId    语音文件id
   * @throws WxErrorException 异常
   */
  void sendVoice(String webhookUrl, String mediaId) throws WxErrorException;

  /**
   * 发送模板卡片消息
   *
   * @param webhookUrl            webhook地址
   * @param wxCpGroupRobotMessage 群机器人消息
   * @throws WxErrorException 异常
   */
  void sendTemplateCardMessage(String webhookUrl, WxCpGroupRobotMessage wxCpGroupRobotMessage) throws WxErrorException;

}
