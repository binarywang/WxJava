package me.chanjar.weixin.channel.bean.coupon;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 优惠券id
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Deprecated
public class CouponIdInfo implements Serializable {

  private static final long serialVersionUID = 6284609705855608275L;
  /** 优惠券ID */
  @JsonProperty("coupon_id")
  private String couponId;

}
