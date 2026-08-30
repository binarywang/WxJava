package me.chanjar.weixin.channel.bean.coupon;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class UserCouponResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 1434098386857953234L;
  @JsonProperty("user_coupon")
  private UserCoupon coupon;

  @JsonProperty("openid")
  private String openid;

  @JsonProperty("unionid")
  private String unionid;

}
