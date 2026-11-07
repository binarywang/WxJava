package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/** 商品属性映射及推荐请求参数.
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.product.ExternalProductMappingNewParam}。
*/
@Data
@Deprecated
public class ExternalProductMappingNewParam implements Serializable {
  private static final long serialVersionUID = -7982070319116550518L;

  @JsonProperty("cat_id")
  private Long catId;
  @JsonProperty("external_category_name")
  private String externalCategoryName;
  @JsonProperty("head_imgs")
  private List<String> headImgs;
  @JsonProperty("detail_imgs")
  private List<String> detailImgs;
  private String title;
  @JsonProperty("external_attributes")
  private List<ExternalAttribute> externalAttributes;

  @Data
  public static class ExternalAttribute implements Serializable {
    private static final long serialVersionUID = 300805187240781417L;
    private String key;
    private String value;
  }
}
