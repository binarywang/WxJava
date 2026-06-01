package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 站内外商品属性映射 请求参数
 *
 * @author <a href="https://github.com/features/copilot">GitHub Copilot</a>
 * @see <a href="https://developers.weixin.qq.com/doc/store/shop/API/channels-shop-product/shop/api_externalproductmapping.html">站内外商品属性映射</a>
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExternalProductMappingParam implements Serializable {

  private static final long serialVersionUID = -1L;

  /** 叶子类目id（必填） */
  @JsonProperty("cat_id")
  private Long catId;

  /** 外部商品属性key（必填） */
  @JsonProperty("external_attribute_name")
  private String externalAttributeName;

  /** 外部商品属性值 */
  @JsonProperty("external_attribute_value")
  private String externalAttributeValue;

  /** 外部商品类目名称 */
  @JsonProperty("external_category_name")
  private String externalCategoryName;
}
