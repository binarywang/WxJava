package com.binarywang.wxjava.store.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/** 商品上架策略响应. */
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductAuditStrategyResponse extends WxStoreBaseResponse {
  private static final long serialVersionUID = -1074784511408331849L;

  @JsonProperty("audit_strategy")
  private ProductAuditStrategyInfo auditStrategy;
}
