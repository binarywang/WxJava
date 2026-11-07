package com.binarywang.wxjava.store.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/** 站内外商品属性映射响应. */
@Data
@EqualsAndHashCode(callSuper = true)
public class ExternalProductMappingResponse extends WxStoreBaseResponse {
  private static final long serialVersionUID = -8356596972896906087L;

  @JsonProperty("external_attribute_name")
  private String externalAttributeName;
  @JsonProperty("external_attribute_value")
  private String externalAttributeValue;
  @JsonProperty("internal_attribute_name")
  private String internalAttributeName;
  @JsonProperty("internal_attribute_value")
  private List<String> internalAttributeValue;
}
