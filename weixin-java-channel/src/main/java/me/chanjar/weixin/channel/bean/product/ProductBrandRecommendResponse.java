package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 商品品牌推荐 响应
 *
 * @author <a href="https://github.com/features/copilot">GitHub Copilot</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductBrandRecommendResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = -1L;

  /** 推荐的品牌ID */
  @JsonProperty("brand_id")
  private Long brandId;

  /** 品牌中文名称 */
  @JsonProperty("brand_name_chinese")
  private String brandNameChinese;

  /** 品牌英文名称 */
  @JsonProperty("brand_name_english")
  private String brandNameEnglish;
}
