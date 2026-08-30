package com.binarywang.wxjava.store.bean.coupon;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserCouponResponse extends WxStoreBaseResponse {

  private static final long serialVersionUID = 1434098386857953234L;
  @JsonProperty("user_coupon")
  private UserCoupon coupon;

  @JsonProperty("openid")
  private String openid;

  @JsonProperty("unionid")
  private String unionid;

}
