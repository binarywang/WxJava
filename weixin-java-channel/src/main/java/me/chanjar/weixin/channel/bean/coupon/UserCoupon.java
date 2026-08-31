package me.chanjar.weixin.channel.bean.coupon;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 用户优惠券
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.coupon.UserCoupon}。
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class UserCoupon extends UserCouponIdInfo {

  private static final long serialVersionUID = -4777537717885622888L;
  /** 优惠券状态 {@link me.chanjar.weixin.channel.enums.UserCouponStatus} */
  @JsonProperty("status")
  private Integer status;

  /** 优惠券派发时间 */
  @JsonProperty("create_time")
  private Long createTime;

  /** 优惠券更新时间 */
  @JsonProperty("update_time")
  private Long updateTime;

  /** 优惠券生效时间 */
  @JsonProperty("start_time")
  private Long startTime;

  /** 优惠券失效时间 */
  @JsonProperty("end_time")
  private Long endTime;

  /** 附加信息 */
  @JsonProperty("ext_info")
  private UserExtInfo extInfo;

  /** 优惠券使用的订单id */
  @JsonProperty("order_id")
  private String orderId;

  /** 优惠券金额 */
  @JsonProperty("discount_fee")
  private Integer discountFee;
}
