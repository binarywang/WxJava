package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 站内外商品属性映射（新版） 请求参数
 *
 * @author <a href="https://github.com/features/copilot">GitHub Copilot</a>
 * @see <a href="https://developers.weixin.qq.com/doc/store/shop/API/channels-shop-product/shop/api_externalproductmappingnew.html">站内外商品属性映射（新版）</a>
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExternalProductMappingNewParam implements Serializable {

  private static final long serialVersionUID = -1L;

  /** 叶子类目id（必填） */
  @JsonProperty("cat_id")
  private Long catId;

  /** 外部商品类目名称 */
  @JsonProperty("external_category_name")
  private String externalCategoryName;

  /** 主图列表 */
  @JsonProperty("head_imgs")
  private List<String> headImgs;

  /** 详情图列表 */
  @JsonProperty("detail_imgs")
  private List<String> detailImgs;

  /** 商品标题 */
  @JsonProperty("title")
  private String title;

  /** 外部商品属性列表 */
  @JsonProperty("external_attributes")
  private List<ExternalAttribute> externalAttributes;
}
