package me.chanjar.weixin.channel.bean.coupon;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.coupon.CouponInfoResponse}。
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class CouponInfoResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 5261320058699488529L;
  @JsonProperty("coupon")
  private CouponInfo coupon;
}
