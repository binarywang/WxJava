package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品品牌推荐 请求参数
 *
 * @author <a href="https://github.com/features/copilot">GitHub Copilot</a>
 * @see <a href="https://developers.weixin.qq.com/doc/store/shop/API/channels-shop-product/shop/api_productbrandrecommend.html">商品品牌推荐</a>
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductBrandRecommendParam implements Serializable {

  private static final long serialVersionUID = -1L;

  /** 叶子类目id（必填） */
  @JsonProperty("cat_id")
  private Long catId;

  /** 主图列表，至少传一张（必填） */
  @JsonProperty("head_imgs")
  private List<String> headImgs;

  /** 详情图列表 */
  @JsonProperty("detail_imgs")
  private List<String> detailImgs;

  /** 商品标题（必填） */
  @JsonProperty("title")
  private String title;
}
