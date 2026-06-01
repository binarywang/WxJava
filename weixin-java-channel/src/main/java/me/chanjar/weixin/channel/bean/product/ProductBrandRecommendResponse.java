package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 商品品牌推荐响应.
 *
 * @author GitHub Copilot
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductBrandRecommendResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 4350605866373432810L;

  /** 品牌ID. */
  @JsonProperty("brand_id")
  private Long brandId;

  /** 品牌中文名. */
  @JsonProperty("brand_name_chinese")
  private String brandNameChinese;

  /** 品牌英文名. */
  @JsonProperty("brand_name_english")
  private String brandNameEnglish;
}
