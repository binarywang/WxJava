package com.binarywang.wxjava.store.bean.product.assistant;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * 站内外商品属性映射响应。
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ExternalProductMappingResponse extends WxStoreBaseResponse {

  private static final long serialVersionUID = -2267639791023044849L;

  /** 外部商品属性名。 */
  @JsonProperty("external_attribute_name")
  private String externalAttributeName;

  /** 外部商品属性值。 */
  @JsonProperty("external_attribute_value")
  private String externalAttributeValue;

  /** 内部商品属性名。 */
  @JsonProperty("internal_attribute_name")
  private String internalAttributeName;

  /** 内部商品属性值。 */
  @JsonProperty("internal_attribute_value")
  private List<String> internalAttributeValue;
}
