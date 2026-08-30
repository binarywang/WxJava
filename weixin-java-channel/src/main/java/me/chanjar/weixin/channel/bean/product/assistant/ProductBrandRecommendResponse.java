package me.chanjar.weixin.channel.bean.product.assistant;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 商品品牌推荐响应。
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class ProductBrandRecommendResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = -7903894941180639923L;

  /** 品牌 ID。 */
  @JsonProperty("brand_id")
  private Long brandId;

  /** 品牌中文名称。 */
  @JsonProperty("brand_name_chinese")
  private String brandNameChinese;

  /** 品牌英文名称。 */
  @JsonProperty("brand_name_english")
  private String brandNameEnglish;
}
