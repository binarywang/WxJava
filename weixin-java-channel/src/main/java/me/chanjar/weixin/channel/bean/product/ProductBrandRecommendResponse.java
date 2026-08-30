package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/** 商品品牌推荐响应. */
@Data
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class ProductBrandRecommendResponse extends WxChannelBaseResponse {
  private static final long serialVersionUID = 4350605866373432810L;

  @JsonProperty("brand_id")
  private Long brandId;
  @JsonProperty("brand_name_chinese")
  private String brandNameChinese;
  @JsonProperty("brand_name_english")
  private String brandNameEnglish;
}
