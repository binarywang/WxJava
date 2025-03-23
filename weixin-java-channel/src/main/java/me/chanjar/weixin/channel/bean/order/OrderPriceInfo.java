package me.chanjar.weixin.channel.bean.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 商店订单价格信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class OrderPriceInfo implements Serializable {
  private static final long serialVersionUID = 5216506688949493432L;

  /**
   * 商品总价，单位为分
   */
  @JsonProperty("product_price")
  private Integer productPrice;

  /**
   * 订单金额，单位为分
   */
  @JsonProperty("order_price")
  private Integer orderPrice;

  /**
   * 运费，单位为分
   */
  @JsonProperty("freight")
  private Integer freight;

  /**
   * 优惠金额，单位为分
   */
  @JsonProperty("discounted_price")
  private Integer discountedPrice;

  /**
   * 是否有优惠
   */
  @JsonProperty("is_discounted")
  private Boolean isDiscounted;

  /**
   * 订单原始价格，单位为分
   */
  @JsonProperty("original_order_price")
  private Integer originalOrderPrice;

  /**
   * 商品预估价格，单位为分
   */
  @JsonProperty("estimate_product_price")
  private Integer estimateProductPrice;

  /**
   * 改价后降低金额，单位为分
   */
  @JsonProperty("change_down_price")
  private Integer changeDownPrice;

  /**
   * 改价后运费，单位为分
   */
  @JsonProperty("change_freight")
  private Integer changeFreight;

  /**
   * 是否修改运费
   */
  @JsonProperty("is_change_freight")
  private Boolean changeFreighted;

  /**
   * 是否使用了会员积分抵扣
   */
  @JsonProperty("use_deduction")
  private Boolean useDeduction;

  /**
   * 会员积分抵扣金额，单位为分
   */
  @JsonProperty("deduction_price")
  private Integer deductionPrice;

  /**
   * 商家实收金额，单位为分
   * merchant_receieve_price=original_order_price-discounted_price-deduction_price-change_down_price
   */
  @JsonProperty("merchant_receieve_price")
  private Integer merchantReceivePrice;

  /**
   * 商家优惠金额，单位为分，含义同discounted_price，必填
   */
  @JsonProperty("merchant_discounted_price")
  private Integer merchantDiscountedPrice;

  /**
   * 达人优惠金额，单位为分
   */
  @JsonProperty("finder_discounted_price")
  private Integer finderDiscountedPrice;

}
