package me.chanjar.weixin.channel.bean.coupon;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.coupon.UserCouponIdParam}。
 */
@Data
@Deprecated
public class UserCouponIdParam implements Serializable {

  private static final long serialVersionUID = 3967276158727848348L;
  /** 用户openid */
  @JsonProperty("openid")
  private String openid;

  /** 用户优惠券ID */
  @JsonProperty("user_coupon_id")
  private String userCouponId;

  public UserCouponIdParam() {
  }

  public UserCouponIdParam(String openid, String userCouponId) {
    this.openid = openid;
    this.userCouponId = userCouponId;
  }
}
