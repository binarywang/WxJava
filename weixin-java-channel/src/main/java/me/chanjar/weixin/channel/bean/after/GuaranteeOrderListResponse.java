package me.chanjar.weixin.channel.bean.after;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 保障单列表响应。
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class GuaranteeOrderListResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 9105476087203713187L;

  /** 保障单总数。 */
  @JsonProperty("total_num")
  private Integer totalNum;

  /** 保障单列表。 */
  @JsonProperty("guarantee_order_list")
  private List<GuaranteeOrder> guaranteeOrderList;

  /**
   * 保障单列表项。
   */
  @Data
  @NoArgsConstructor
  public static class GuaranteeOrder implements Serializable {

    private static final long serialVersionUID = 7151952524213202281L;

    /** 保障单号。 */
    @JsonProperty("guarantee_order_id")
    private String guaranteeOrderId;

    /** 保障单状态。 */
    @JsonProperty("status")
    private String status;

    /** 商品信息列表。 */
    @JsonProperty("product_info")
    private List<ProductInfo> productInfo;
  }

  /**
   * 列表商品信息。
   */
  @Data
  @NoArgsConstructor
  public static class ProductInfo implements Serializable {

    private static final long serialVersionUID = -2565763879505631638L;

    /** 商品 SPU ID。 */
    @JsonProperty("product_id")
    private String productId;
  }
}
