package me.chanjar.weixin.channel.bean.brand;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 品牌信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Deprecated
public class Brand extends BasicBrand {

  private static final long serialVersionUID = 4648597514861057019L;

  /** 商标分类号, 取值范围1-45 */
  @JsonProperty("classification_no")
  private String classificationNo;

  /** 商标类型, 取值1:R标; 2: TM标 */
  @JsonProperty("trade_mark_symbol")
  private Integer tradeMarkSymbol;

  /** 商标注册信息 */
  @JsonProperty("register_details")
  private BrandRegisterDetail registerDetail;

  /** 商标申请信息 */
  @JsonProperty("application_details")
  private BrandApplicationDetail applicationDetail;

  /** 商标授权信息, 取值1:自有品牌; 2: 授权品牌 */
  @JsonProperty("grant_type")
  private Integer grantType;

  /** 授权品牌信息 */
  @JsonProperty("grant_details")
  private BrandGrantDetail grantDetail;
}
