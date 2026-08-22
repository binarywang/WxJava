package me.chanjar.weixin.channel.bean.after;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 保障单详情响应。
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GuaranteeOrderInfoResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 7354122991247317485L;

  /** 保障单详情。 */
  @JsonProperty("guarantee_order")
  private GuaranteeOrder guaranteeOrder;

  /**
   * 保障单详情。
   */
  @Data
  @NoArgsConstructor
  public static class GuaranteeOrder implements Serializable {

    private static final long serialVersionUID = -2398976447575813507L;

    /** 保障单号。 */
    @JsonProperty("guarantee_order_id")
    private String guaranteeOrderId;

    /** 保障单状态。 */
    @JsonProperty("status")
    private String status;

    /** 商品信息。 */
    @JsonProperty("product_info")
    private ProductInfo productInfo;
  }

  /**
   * 详情商品信息。
   */
  @Data
  @NoArgsConstructor
  public static class ProductInfo implements Serializable {

    private static final long serialVersionUID = -2455740986246085934L;

    /** 商品 SPU ID。 */
    @JsonProperty("product_id")
    private String productId;
  }
}
