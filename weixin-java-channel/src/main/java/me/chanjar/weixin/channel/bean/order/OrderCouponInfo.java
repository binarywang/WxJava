package me.chanjar.weixin.channel.bean.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 卡券信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class OrderCouponInfo implements Serializable {

  private static final long serialVersionUID = -2033350505767196339L;
  /** 用户优惠券id */
  @JsonProperty("user_coupon_id")
  private String userCouponId;

  /**
   * 优惠券类型
   * 1	商家优惠
   * 2	达人优惠
   * 3	平台优惠
   * 4	国家补贴
   * 5	地方补贴
   */
  @JsonProperty("coupon_type")
  private Integer couponType;

  /** 优惠金额，单位为分，该张优惠券、抵扣该商品的金额 */
  @JsonProperty("discounted_price")
  private Integer discountedPrice;

  /** 优惠券id */
  @JsonProperty("coupon_id")
  private String couponId;

}
