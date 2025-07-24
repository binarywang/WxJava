package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class ExtraServiceInfo implements Serializable {

  private static final long serialVersionUID = -5517806977282063174L;

  /**
   * 是否支持七天无理由退货，0-不支持七天无理由, 1-支持七天无理由, 2-支持七天无理由(定制商品除外)。 管理规则请参见七天无理由退货管理规则。类目是否必须支持七天无理由退货，
   * 可参考文档获取类目信息中的字段attr.seven_day_return
   */
  @JsonProperty("seven_day_return")
  private Integer sevenDayReturn;

  /** 先用后付，0-不支持先用后付，1-支持先用后付。若店铺已开通先用后付，支持先用后付的类目商品将在上架后自动打开先用后付。 */
  @JsonProperty("pay_after_use")
  private Integer payAfterUse;

  /** 是否支持运费险，0-不支持运费险，1-支持运费险。需要商户开通运费险服务，且当前类目支持运费险才会生效。 */
  @JsonProperty("freight_insurance")
  private Integer freightInsurance;

  /** 是否支持假一赔三，0-不支持假一赔三，1-支持假一赔三。 */
  @JsonProperty("fake_one_pay_three")
  private Integer fakeOnePayThree;

  /** 是否支持坏损包退，0-不支持坏损包退，1-支持坏损包退。 */
  @JsonProperty("damage_guarantee")
  private Integer damageGuarantee;
}
