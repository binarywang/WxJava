package me.chanjar.weixin.channel.bean.coupon;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Deprecated
public class CouponStatusParam extends CouponIdInfo {

  private static final long serialVersionUID = -7108348049925634704L;
  /** 状态 */
  @JsonProperty("status")
  private Integer status;

  public CouponStatusParam() {
  }

  public CouponStatusParam(String couponId, Integer status) {
    super(couponId);
    this.status = status;
  }
}
