package me.chanjar.weixin.channel.bean.product.stock;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 获取库存流水响应。
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class StockFlowResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = -7420844779570799705L;

  /** 本次翻页的上下文。 */
  private String nextKey;

  /** 库存流水。 */
  private List<StockFlowInfo> stockFlowInfoList;

  @JsonProperty("data")
  private void unpackData(StockFlowData data) {
    if (data == null) {
      return;
    }
    this.nextKey = data.getNextKey();
    this.stockFlowInfoList = data.getStockFlowInfoList();
  }

  @Data
  @NoArgsConstructor
  private static class StockFlowData implements Serializable {

    private static final long serialVersionUID = -5455751387420196045L;

    @JsonProperty("next_key")
    private String nextKey;

    @JsonProperty("stock_flow_info_list")
    private List<StockFlowInfo> stockFlowInfoList;
  }
}
