package me.chanjar.weixin.channel.bean.fund;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 流水列表响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.fund.FlowListResponse}。
 */
@Data
@NoArgsConstructor
@Deprecated
public class FlowListResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 8017827444308973489L;
  /** 流水单号列表 */
  @JsonProperty("flow_ids")
  private List<String> flowIds;

  /** 是否还有下一页 */
  @JsonProperty("has_more")
  private boolean hasMore;

  /** 分页参数，深翻页时使用 */
  @JsonProperty("next_key")
  private String nextKey;
}
