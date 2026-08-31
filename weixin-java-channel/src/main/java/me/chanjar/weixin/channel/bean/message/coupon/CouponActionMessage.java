package me.chanjar.weixin.channel.bean.message.coupon;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.message.WxChannelMessage;


/**
 * 卡券操作 消息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.message.coupon.CouponActionMessage}。
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JacksonXmlRootElement(localName = "xml")
@Deprecated
public class CouponActionMessage extends WxChannelMessage {

  private static final long serialVersionUID = 4910461800721504462L;
  /** 优惠券信息 */
  @JsonProperty("coupon_info")
  @JacksonXmlProperty(localName = "coupon_info")
  private CouponActionInfo couponInfo;

}
