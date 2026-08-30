package me.chanjar.weixin.channel.bean.coupon;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Deprecated
public class UserCouponListParam extends CouponListParam {
  private static final long serialVersionUID = -1056132009327357435L;

  /**
   * openId
   */
  @JsonProperty("openid")
  private String openId;
}
