package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;

/** 站内外商品属性映射请求参数. */
@Data
public class ExternalProductMappingParam implements Serializable {
  private static final long serialVersionUID = 3288069294712374035L;

  @JsonProperty("cat_id")
  private Long catId;
  @JsonProperty("external_attribute_name")
  private String externalAttributeName;
  @JsonProperty("external_attribute_value")
  private String externalAttributeValue;
  @JsonProperty("external_category_name")
  private String externalCategoryName;
}
