package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/** 获取库存流水响应.
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.product.ProductStockFlowResponse}。
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class ProductStockFlowResponse extends WxChannelBaseResponse {
  private static final long serialVersionUID = 7600529379926896515L;

  private StockFlowData data;

  @Data
  public static class StockFlowData implements Serializable {
    private static final long serialVersionUID = -4963813730951045381L;

    @JsonProperty("stock_flow_info_list")
    private List<JsonNode> stockFlowInfoList;
    @JsonProperty("next_key")
    private String nextKey;
  }
}
