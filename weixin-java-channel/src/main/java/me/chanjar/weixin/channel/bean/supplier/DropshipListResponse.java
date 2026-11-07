package me.chanjar.weixin.channel.bean.supplier;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 代发单列表响应。
 *
 * @author <a href="https://github.com/github-copilot">GitHub Copilot</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.supplier.DropshipListResponse}。
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class DropshipListResponse extends WxChannelBaseResponse {
  private static final long serialVersionUID = -2850183412032417307L;

  @JsonProperty("dropship_list")
  private List<DropshipInfo> dropshipList;

  @JsonProperty("next_key")
  private String nextKey;

  @JsonProperty("has_more")
  private Boolean hasMore;
}
