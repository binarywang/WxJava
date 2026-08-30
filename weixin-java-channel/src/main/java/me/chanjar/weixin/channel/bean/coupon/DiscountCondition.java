package me.chanjar.weixin.channel.bean.coupon;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 折扣条件
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@Deprecated
public class DiscountCondition implements Serializable {

  private static final long serialVersionUID = 3250293381093835082L;
  /** 优惠券使用条件, 满 x 件商品可用 */
  @JsonProperty("product_cnt")
  private Integer productCnt;

  /** 优惠券使用条件, 价格满 x 可用，单位分 */
  @JsonProperty("product_price")
  private Integer productPrice;

  /** 优惠券使用条件, 指定商品 id 可用 */
  @JsonProperty("product_ids")
  private List<String> productIds;
}
