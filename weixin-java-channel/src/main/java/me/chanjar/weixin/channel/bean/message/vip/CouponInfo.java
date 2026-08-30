package me.chanjar.weixin.channel.bean.message.vip;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 优惠券信息
 *
 * @author <a href="https://github.com/asushiye">asushiye</a>
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@Deprecated
public class CouponInfo implements Serializable {

  private static final long serialVersionUID = -3659710836197413932L;
  /** 兑换的优惠券ID**/
  @JsonProperty("related_coupon_id")
  @JacksonXmlProperty(localName = "related_coupon_id")
  private Long relatedCouponId;
}
