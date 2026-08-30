package me.chanjar.weixin.channel.bean.freight;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 运费计算方法
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class FreightCalcMethod extends AddressInfoList {

  private static final long serialVersionUID = -8857987538121721376L;
  /** 是否默认运费 */
  @JsonProperty("is_default")
  private Boolean isDefault;

  /** 快递公司 */
  @JsonProperty("delivery_id")
  private String deliveryId;

  /** 首段运费需要满足的数量 */
  @JsonProperty("first_val_amount")
  private Integer firstValAmount;

  /** 首段运费的金额 */
  @JsonProperty("first_price")
  private Integer firstPrice;

  /** 续费的数量 */
  @JsonProperty("second_val_amount")
  private Integer secondValAmount;

  /** 续费的金额 */
  @JsonProperty("second_price")
  private Integer secondPrice;

}
