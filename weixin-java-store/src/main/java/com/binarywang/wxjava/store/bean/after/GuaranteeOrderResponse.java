package com.binarywang.wxjava.store.bean.after;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

@Data
@EqualsAndHashCode(callSuper = true)
public class GuaranteeOrderResponse extends WxStoreBaseResponse {
  private static final long serialVersionUID = 3977781489692530604L;

  @JsonProperty("guarantee_order")
  private JsonNode guaranteeOrder;
}
