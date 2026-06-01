package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品属性映射及推荐请求参数.
 *
 * @author GitHub Copilot
 */
@Data
@NoArgsConstructor
public class ExternalProductMappingNewParam implements Serializable {

  private static final long serialVersionUID = -7982070319116550518L;

  /** 叶子类目ID. */
  @JsonProperty("cat_id")
  private Long catId;

  /** 外部商品类目名称. */
  @JsonProperty("external_category_name")
  private String externalCategoryName;

  /** 商品主图. */
  @JsonProperty("head_imgs")
  private List<String> headImgs;

  /** 商品详情图. */
  @JsonProperty("detail_imgs")
  private List<String> detailImgs;

  /** 商品标题. */
  private String title;

  /** 外部属性列表. */
  @JsonProperty("external_attributes")
  private List<ExternalAttribute> externalAttributes;

  @Data
  @NoArgsConstructor
  public static class ExternalAttribute implements Serializable {
    private static final long serialVersionUID = 300805187240781417L;

    private String key;
    private String value;
  }
}
