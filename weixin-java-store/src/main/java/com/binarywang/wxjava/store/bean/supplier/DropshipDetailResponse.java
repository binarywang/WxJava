package com.binarywang.wxjava.store.bean.supplier;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * 代发单详情响应。
 *
 * @author <a href="https://github.com/github-copilot">GitHub Copilot</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DropshipDetailResponse extends WxStoreBaseResponse {
  private static final long serialVersionUID = 5548774863400272707L;

  @JsonProperty("dropship_info")
  private DropshipInfo dropshipInfo;
}
