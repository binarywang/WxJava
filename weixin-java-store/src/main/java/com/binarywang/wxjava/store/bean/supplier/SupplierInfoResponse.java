package com.binarywang.wxjava.store.bean.supplier;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * 供货商信息响应。
 *
 * @author <a href="https://github.com/github-copilot">GitHub Copilot</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SupplierInfoResponse extends WxStoreBaseResponse {
  private static final long serialVersionUID = -3071464065836573893L;

  @JsonProperty("supplier_info")
  private SupplierInfo supplierInfo;
}
