package me.chanjar.weixin.channel.bean.after;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 保障单信息。
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GuaranteeOrderInfo implements Serializable {

  private static final long serialVersionUID = -7360231942642541702L;

  /** 保障单号。 */
  @JsonProperty("guarantee_order_id")
  private String guaranteeOrderId;

  /** 订单号。 */
  @JsonProperty("order_id")
  private String orderId;

  /** 保障单状态。 */
  @JsonProperty("status")
  private Integer status;

  /** 申请原因。 */
  @JsonProperty("apply_reason")
  private String applyReason;

  /** 申请时间，Unix 时间戳（秒）。 */
  @JsonProperty("apply_time")
  private Long applyTime;

  /** 更新时间，Unix 时间戳（秒）。 */
  @JsonProperty("update_time")
  private Long updateTime;

  /** 实付金额，单位：分。 */
  @JsonProperty("pay_amount")
  private Integer payAmount;

  /** 商品信息。 */
  @JsonProperty("product_info")
  private ProductInfo productInfo;

  /**
   * 保障单商品信息。
   */
  @Data
  @NoArgsConstructor
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class ProductInfo implements Serializable {

    private static final long serialVersionUID = -4469201668900292237L;

    /** 商品 SPU ID。 */
    @JsonProperty("product_id")
    private String productId;

    /** 商品 SKU ID。 */
    @JsonProperty("sku_id")
    private String skuId;

    /** SKU 名称。 */
    @JsonProperty("sku_name")
    private String skuName;

    /** 商品标题。 */
    @JsonProperty("product_title")
    private String productTitle;

    /** 商品数量。 */
    @JsonProperty("count")
    private Integer count;
  }
}
