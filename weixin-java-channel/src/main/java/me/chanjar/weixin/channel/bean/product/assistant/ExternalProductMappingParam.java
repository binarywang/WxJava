package me.chanjar.weixin.channel.bean.product.assistant;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 站内外商品属性映射参数。
 */
@Data
@NoArgsConstructor
public class ExternalProductMappingParam implements Serializable {

  private static final long serialVersionUID = 1944528166283981889L;

  /** 叶子类目 ID。 */
  @JsonProperty("cat_id")
  private Long catId;

  /** 外部商品属性名。 */
  @JsonProperty("external_attribute_name")
  private String externalAttributeName;

  /** 外部商品属性值。 */
  @JsonProperty("external_attribute_value")
  private String externalAttributeValue;

  /** 外部商品类目名称。 */
  @JsonProperty("external_category_name")
  private String externalCategoryName;
}
