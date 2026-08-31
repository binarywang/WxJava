package me.chanjar.weixin.channel.bean.coupon;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.coupon.CouponInfo}。
 */
@Data
@NoArgsConstructor
@Deprecated
public class CouponInfo extends CouponIdInfo {

  private static final long serialVersionUID = -5862063828870424262L;
  /** 优惠券类型 **/
  @JsonProperty("type")
  private Integer type;

  /** 优惠券状态 **/
  @JsonProperty("status")
  private Integer status;

  /** 优惠券创建时间 */
  @JsonProperty("create_time")
  private Long createTime;

  /** 优惠券更新时间 */
  @JsonProperty("update_time")
  private Long updateTime;

  /** 优惠券信息 */
  @JsonProperty("coupon_info")
  private CouponDetailInfo detail;

  /** 库存信息 */
  @JsonProperty("stock_info")
  private StockInfo stockInfo;
}
