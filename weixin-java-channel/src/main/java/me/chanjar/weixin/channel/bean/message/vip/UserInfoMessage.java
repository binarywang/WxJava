package me.chanjar.weixin.channel.bean.message.vip;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.message.WxChannelMessage;

/**
 * 用户信息消息
 *
 * @author <a href="https://github.com/asushiye">asushiye</a>
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JacksonXmlRootElement(localName = "xml")
@Deprecated
public class UserInfoMessage extends WxChannelMessage {

  private static final long serialVersionUID = 6926608689621530622L;
  /** 用户信息 */
  @JsonProperty("user_info")
  @JacksonXmlProperty(localName = "order_info")
  private UserInfo userInfo;
}
