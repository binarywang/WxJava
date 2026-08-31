package me.chanjar.weixin.channel.bean.coupon;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 优惠信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.coupon.DiscountInfo}。
 */
@Data
@NoArgsConstructor
@Deprecated
public class DiscountInfo implements Serializable {

  private static final long serialVersionUID = 3660070880545144112L;
  /** 优惠券折扣数 * 1000, 例如 5.1折-> 5100 */
  @JsonProperty("discount_num")
  private Integer discountNum;

  /** 优惠券减少金额, 单位分, 例如0.5元-> 50 */
  @JsonProperty("discount_fee")
  private Integer discountFee;

  /** 优惠条件 */
  @JsonProperty("discount_condition")
  private DiscountCondition discountCondition;
}
