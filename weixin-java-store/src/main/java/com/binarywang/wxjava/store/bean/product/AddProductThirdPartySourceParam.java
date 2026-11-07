package com.binarywang.wxjava.store.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.Serializable;
import lombok.Data;

/** 新增第三方货源信息请求参数. */
@Data
public class AddProductThirdPartySourceParam implements Serializable {
  private static final long serialVersionUID = -5784320217481497742L;

  @JsonProperty("scene_value")
  private Integer sceneValue;
  @JsonProperty("publish_method")
  private Integer publishMethod;
  private JsonNode supplier;
  @JsonProperty("supplier_shop_performance")
  private JsonNode supplierShopPerformance;
  @JsonProperty("product_source_info")
  private JsonNode productSourceInfo;
}
