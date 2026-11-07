package com.binarywang.wxjava.store.bean.supplier;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * 代发单分配响应。
 *
 * @author <a href="https://github.com/github-copilot">GitHub Copilot</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DropshipResponse extends WxStoreBaseResponse {
  private static final long serialVersionUID = 4376618566823584629L;

  @JsonProperty("order_id")
  private String orderId;

  @JsonProperty("supplier_id")
  private String supplierId;

  @JsonProperty("dropship_id")
  private String dropshipId;
}
