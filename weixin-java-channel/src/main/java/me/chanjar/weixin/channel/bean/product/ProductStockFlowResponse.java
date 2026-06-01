package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 获取库存流水响应.
 *
 * @author GitHub Copilot
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductStockFlowResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 7600529379926896515L;

  /** 库存流水数据. */
  private StockFlowData data;

  @Data
  @NoArgsConstructor
  public static class StockFlowData {
    /** 库存流水列表. */
    @JsonProperty("stock_flow_info_list")
    private List<JsonNode> stockFlowInfoList;

    /** 翻页上下文. */
    @JsonProperty("next_key")
    private String nextKey;
  }
}
