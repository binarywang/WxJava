package com.binarywang.wxjava.store.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/** 新增第三方货源信息响应. */
@Data
@EqualsAndHashCode(callSuper = true)
public class AddProductThirdPartySourceResponse extends WxStoreBaseResponse {
  private static final long serialVersionUID = -7528226120383065861L;

  @JsonProperty("third_party_source_id")
  private Long thirdPartySourceId;
}
