package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 站内外商品属性映射请求参数.
 *
 * @author GitHub Copilot
 */
@Data
@NoArgsConstructor
public class ExternalProductMappingParam implements Serializable {

  private static final long serialVersionUID = 3288069294712374035L;

  /** 叶子类目ID. */
  @JsonProperty("cat_id")
  private Long catId;

  /** 外部商品属性key. */
  @JsonProperty("external_attribute_name")
  private String externalAttributeName;

  /** 外部商品属性值. */
  @JsonProperty("external_attribute_value")
  private String externalAttributeValue;

  /** 外部商品类目名称. */
  @JsonProperty("external_category_name")
  private String externalCategoryName;
}
