package me.chanjar.weixin.mp.bean.message;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.common.api.WxConsts;

/**
 * 转接AI回复消息.
 * <pre>
 * 当用户发送消息给公众号时，公众号开发者服务器回复如下内容，会触发微信公众平台的AI回复。
 * 注意：需要公众号在微信公众平台上已开启AI回复功能，并且AI已学习完毕历史发表文章。
 * 官方文档：https://developers.weixin.qq.com/doc/subscription/guide/product/message/Passive_user_reply_message.html
 * </pre>
 *
 * @author copilot
 */
@Data
@XStreamAlias("xml")
@JacksonXmlRootElement(localName = "xml")
@EqualsAndHashCode(callSuper = true)
public class WxMpXmlOutTransferBizAiIvrMessage extends WxMpXmlOutMessage {
  private static final long serialVersionUID = 8275281170988017563L;

  public WxMpXmlOutTransferBizAiIvrMessage() {
    this.msgType = WxConsts.XmlMsgType.TRANSFER_BIZ_AI_IVR;
  }
}
