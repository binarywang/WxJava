package com.binarywang.wxjava.store.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/** 商品品牌推荐响应. */
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductBrandRecommendResponse extends WxStoreBaseResponse {
  private static final long serialVersionUID = 4350605866373432810L;

  @JsonProperty("brand_id")
  private Long brandId;
  @JsonProperty("brand_name_chinese")
  private String brandNameChinese;
  @JsonProperty("brand_name_english")
  private String brandNameEnglish;
}
