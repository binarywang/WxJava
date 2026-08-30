package com.binarywang.wxjava.store.bean.coupon;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户优惠券id
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class UserCouponIdInfo extends CouponIdInfo {

  private static final long serialVersionUID = -8285585134793264542L;
  /** 用户优惠券ID */
  @JsonProperty("user_coupon_id")
  private String userCouponId;
}
