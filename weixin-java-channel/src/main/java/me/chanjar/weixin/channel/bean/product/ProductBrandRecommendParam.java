package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品品牌推荐请求参数.
 *
 * @author GitHub Copilot
 */
@Data
@NoArgsConstructor
public class ProductBrandRecommendParam implements Serializable {

  private static final long serialVersionUID = 6462717198206491138L;

  /** 叶子类目ID. */
  @JsonProperty("cat_id")
  private Long catId;

  /** 商品主图. */
  @JsonProperty("head_imgs")
  private List<String> headImgs;

  /** 商品详情图. */
  @JsonProperty("detail_imgs")
  private List<String> detailImgs;

  /** 商品标题. */
  private String title;
}
