package com.binarywang.wxjava.store.bean.after;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

@Data
@EqualsAndHashCode(callSuper = true)
public class AfterSaleCreateResponse extends WxStoreBaseResponse {
  private static final long serialVersionUID = 2680676438284658410L;

  @JsonProperty("after_sale_order_id")
  private String afterSaleOrderId;
}
