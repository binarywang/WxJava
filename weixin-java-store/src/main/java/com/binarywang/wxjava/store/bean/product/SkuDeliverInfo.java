package com.binarywang.wxjava.store.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * sku发货信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class SkuDeliverInfo implements Serializable {

  private static final long serialVersionUID = 8046963723772755406L;

  /** sku库存情况。0:现货（默认），1:全款预售。部分类目支持全款预售，具体参考文档获取类目信息中的字段attr.pre_sale */
  @JsonProperty("stock_type")
  private Integer stockType;

  /** sku发货节点，该字段仅对stock_type=1有效。0:付款后n天发货，1:预售结束后n天发货 */
  @JsonProperty("full_payment_presale_delivery_type")
  private Integer fullPaymentPresaleDeliveryType;

  /** sku预售周期开始时间，秒级时间戳，该字段仅对delivery_type=1有效。 */
  @JsonProperty("presale_begin_time")
  private Long presaleBeginTime;

  /**
   * sku预售周期结束时间，秒级时间戳，该字段仅对delivery_type=1有效。限制：预售结束时间距离现在<=30天， 即presale_end_time - now <= 2592000。预售时间区间<=15天，
   * 即presale_end_time - presale_begin_time <= 1296000
   */
  @JsonProperty("presale_end_time")
  private Long presaleEndTime;

  /**
   * sku发货时效，即付款后/预售结束后{full_payment_presale_delivery_time}天内发货， 该字段仅对stock_type=1时有效。范围是[4, 15]的整数。
   */
  @JsonProperty("full_payment_presale_delivery_time")
  private Integer fullPaymentPresaleDeliveryTime;
}
