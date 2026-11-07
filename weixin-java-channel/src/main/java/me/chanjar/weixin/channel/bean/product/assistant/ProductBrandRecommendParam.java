package me.chanjar.weixin.channel.bean.product.assistant;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品品牌推荐参数。
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.product.assistant.ProductBrandRecommendParam}。
 */
@Data
@NoArgsConstructor
@Deprecated
public class ProductBrandRecommendParam implements Serializable {

  private static final long serialVersionUID = 4516219198778673928L;

  /** 叶子类目 ID。 */
  @JsonProperty("cat_id")
  private Long catId;

  /** 商品主图，至少一张。 */
  @JsonProperty("head_imgs")
  private List<String> headImgs;

  /** 商品详情图。 */
  @JsonProperty("detail_imgs")
  private List<String> detailImgs;

  /** 商品标题。 */
  @JsonProperty("title")
  private String title;
}
