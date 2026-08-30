package com.binarywang.wxjava.store.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/** 商品品牌推荐请求参数. */
@Data
public class ProductBrandRecommendParam implements Serializable {
  private static final long serialVersionUID = 6462717198206491138L;

  @JsonProperty("cat_id")
  private Long catId;
  @JsonProperty("head_imgs")
  private List<String> headImgs;
  @JsonProperty("detail_imgs")
  private List<String> detailImgs;
  private String title;
}
