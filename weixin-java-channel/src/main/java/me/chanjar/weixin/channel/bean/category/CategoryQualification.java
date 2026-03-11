package me.chanjar.weixin.channel.bean.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分类资质信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class CategoryQualification implements Serializable {

  private static final long serialVersionUID = 6495550078851408381L;

  /** 类目 */
  @JsonProperty("cat")
  private ShopCategory category;

  /** 资质信息 */
  @JsonProperty("qua")
  private QualificationInfo info;

  /** 商品资质信息，将废弃，使用product_qua_list代替 */
  @JsonProperty("product_qua")
  @Deprecated
  private QualificationInfo productInfo;

  /** 品牌资质信息 */
  @JsonProperty("brand_qua")
  @Deprecated
  private QualificationInfo brandQua;

  /** 商品资质列表，替代product_qua */
  @JsonProperty("product_qua_list")
  private List<QualificationInfo> productQuaList;

  /** 放心买必须打开坏损包赔 */
  @JsonProperty("is_confidence_require_bad_must_pay")
  private Boolean confidenceRequireBadMustPay;

}
