package me.chanjar.weixin.channel.bean.after;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.after.AfterSaleRefundPriceDiffParam}。
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Deprecated
public class AfterSaleRefundPriceDiffParam implements Serializable {
  private static final long serialVersionUID = 3875058376021518123L;

  @JsonProperty("request_id")
  private String requestId;

  @JsonProperty("order_id")
  private String orderId;

  @JsonProperty("product_id")
  private String productId;

  @JsonProperty("sku_id")
  private String skuId;

  @JsonProperty("amount")
  private Integer amount;

  @JsonProperty("reason")
  private String reason;

  @JsonProperty("desc")
  private String desc;
}
