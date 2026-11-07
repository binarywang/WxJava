package com.binarywang.wxjava.store.bean.supplier;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * 分配方式响应。
 *
 * @author <a href="https://github.com/github-copilot">GitHub Copilot</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DistributeTypeResponse extends WxStoreBaseResponse {
  private static final long serialVersionUID = -750860556286328053L;

  @JsonProperty("distribute_type")
  private Integer distributeType;

  @JsonProperty("supplier_info")
  private SupplierInfo supplierInfo;
}
