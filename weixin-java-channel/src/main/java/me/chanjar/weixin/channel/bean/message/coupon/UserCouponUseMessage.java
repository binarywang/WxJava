package me.chanjar.weixin.channel.bean.message.coupon;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.message.WxChannelMessage;


/**
 * 用户卡券使用 消息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JacksonXmlRootElement(localName = "xml")
@Deprecated
public class UserCouponUseMessage extends WxChannelMessage {

  private static final long serialVersionUID = -1051142666438578628L;
  /** 用户优惠券信息 */
  @JsonProperty("user_info")
  @JacksonXmlProperty(localName = "user_info")
  private UserCouponActionInfo userCouponInfo;
}
