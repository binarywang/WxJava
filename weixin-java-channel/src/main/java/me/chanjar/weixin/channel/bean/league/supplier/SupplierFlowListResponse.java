package me.chanjar.weixin.channel.bean.league.supplier;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 资金流水列表 响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class SupplierFlowListResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = -2954427554226407544L;
  /** 流水单号列表 */
  @JsonProperty("funds_flow_ids")
  private List<String> ids;

  /** 是否还有下一页 */
  @JsonProperty("has_more")
  private Boolean hasMore;

  /** 分页参数，深翻页时使用 */
  @JsonProperty("next_key")
  private String nextKey;
}
