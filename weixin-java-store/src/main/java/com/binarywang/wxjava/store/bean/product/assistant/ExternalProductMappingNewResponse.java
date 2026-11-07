package com.binarywang.wxjava.store.bean.product.assistant;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * 商品属性映射及推荐响应。
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ExternalProductMappingNewResponse extends WxStoreBaseResponse {

  private static final long serialVersionUID = -6192580254142696913L;

  /** 映射属性结果。 */
  @JsonProperty("attributes")
  private List<ExternalAttribute> attributes;
}
