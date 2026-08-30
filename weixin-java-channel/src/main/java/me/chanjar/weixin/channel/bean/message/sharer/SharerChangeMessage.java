package me.chanjar.weixin.channel.bean.message.sharer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.message.WxChannelMessage;

/**
 * 分享员变更消息
 * https://developers.weixin.qq.com/doc/channels/API/sharer/callback/channels_ec_sharer_change.html
 *
 * @author sd-hxf
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JacksonXmlRootElement(localName = "xml")
@Deprecated
public class SharerChangeMessage extends WxChannelMessage {

  private static final long serialVersionUID = 4219477394934480421L;

  /**
   * 分享员OpenID
   */
  @JsonProperty("openid")
  @JacksonXmlProperty(localName = "openid")
  private String openid;

  /**
   * 分享员类型：0-普通分享员，1-店铺分享员
   */
  @JsonProperty("sharer_type")
  @JacksonXmlProperty(localName = "sharer_type")
  private Integer sharerType;

  /**
   * 分享员绑定状态：1-绑定，2-解绑
   */
  @JsonProperty("bind_status")
  @JacksonXmlProperty(localName = "bind_status")
  private Integer bindStatus;



}
