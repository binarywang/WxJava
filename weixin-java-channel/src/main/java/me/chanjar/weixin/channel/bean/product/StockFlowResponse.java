package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 获取库存流水 响应
 *
 * @author <a href="https://github.com/features/copilot">GitHub Copilot</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class StockFlowResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = -1L;

  /** 返回数据 */
  @JsonProperty("data")
  private StockFlowData data;

  /**
   * 库存流水数据
   */
  @Data
  @NoArgsConstructor
  public static class StockFlowData implements java.io.Serializable {

    private static final long serialVersionUID = -1L;

    /** 库存流水列表 */
    @JsonProperty("stock_flow_info_list")
    private List<StockFlowInfo> stockFlowInfoList;

    /** 本次翻页的上下文，用于请求下一页 */
    @JsonProperty("next_key")
    private String nextKey;
  }
}
