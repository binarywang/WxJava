package me.chanjar.weixin.channel.bean.message.coupon;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.Map;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.message.WxChannelMessage;


/**
 * 用户领券 消息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JacksonXmlRootElement(localName = "xml")
@Deprecated
public class CouponReceiveMessage extends WxChannelMessage {

  private static final long serialVersionUID = 5121347165246528730L;
  /** 领取的优惠券ID */
  @JsonProperty("coupon_id")
  @JacksonXmlProperty(localName = "coupon_id")
  private String couponId;

  /** 生成的用户券ID */
  @JsonProperty("user_coupon_id")
  @JacksonXmlProperty(localName = "user_coupon_id")
  private String userCouponId;

  /** 领券时间 */
  @JsonProperty("receive_time")
  @JacksonXmlProperty(localName = "receive_time")
  private String receiveTime;

  @JsonProperty("receive_info")
  @JacksonXmlProperty(localName = "receive_info")
  private void unpackNameFromNestedObject(Map<String, Object> map) {
    if (map == null) {
      return;
    }
    Object obj = null;
    obj = map.get("coupon_id");
    if (obj != null) {
      this.couponId = (obj instanceof String ? (String) obj : String.valueOf(obj));
    }
    obj = map.get("user_coupon_id");
    if (obj != null) {
      this.userCouponId = (obj instanceof String ? (String) obj : String.valueOf(obj));
    }
    obj = map.get("receive_time");
    if (obj != null) {
      this.receiveTime = (obj instanceof String ? (String) obj : String.valueOf(obj));
    }
  }
}
