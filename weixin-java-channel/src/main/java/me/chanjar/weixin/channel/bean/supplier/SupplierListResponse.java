package me.chanjar.weixin.channel.bean.supplier;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 供货商列表响应。
 *
 * @author <a href="https://github.com/github-copilot">GitHub Copilot</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class SupplierListResponse extends WxChannelBaseResponse {
  private static final long serialVersionUID = -692609589633695295L;

  @JsonProperty("supplier_list")
  private List<SupplierInfo> supplierList;

  @JsonProperty("next_key")
  private String nextKey;

  @JsonProperty("has_more")
  private Boolean hasMore;
}
