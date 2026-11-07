package me.chanjar.weixin.channel.bean.product.assistant;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品属性映射及推荐参数。
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.product.assistant.ExternalProductMappingNewParam}。
 */
@Data
@NoArgsConstructor
@Deprecated
public class ExternalProductMappingNewParam implements Serializable {

  private static final long serialVersionUID = -4942505655791636645L;

  /** 叶子类目 ID。 */
  @JsonProperty("cat_id")
  private Long catId;

  /** 外部商品类目名称。 */
  @JsonProperty("external_category_name")
  private String externalCategoryName;

  /** 商品主图，至少一张。 */
  @JsonProperty("head_imgs")
  private List<String> headImgs;

  /** 商品详情图。 */
  @JsonProperty("detail_imgs")
  private List<String> detailImgs;

  /** 商品标题。 */
  @JsonProperty("title")
  private String title;

  /** 外部商品属性列表。 */
  @JsonProperty("external_attributes")
  private List<ExternalAttribute> externalAttributes;
}
