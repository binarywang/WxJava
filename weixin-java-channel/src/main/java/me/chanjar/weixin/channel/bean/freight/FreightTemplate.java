package me.chanjar.weixin.channel.bean.freight;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.AddressInfo;

/**
 * 运费模板
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@Deprecated
public class FreightTemplate implements Serializable {

  private static final long serialVersionUID = -7876281924385999053L;
  /** 模板id */
  @JsonProperty("template_id")
  private String templateId;

  /** 模板名称 */
  @JsonProperty("name")
  private String name;

  /** 计费类型，PIECE：按件数，WEIGHT:按重量 */
  @JsonProperty("valuation_type")
  private String valuationType;

  /** 发货时间期限 {@link me.chanjar.weixin.channel.enums.SendTime} */
  @JsonProperty("send_time")
  private String sendTime;

  /** 发货地址 */
  @JsonProperty("address_info")
  private AddressInfo addressInfo;

  /** 运输方式，EXPRESS：快递 */
  @JsonProperty("delivery_type")
  private String deliveryType;

  /** 计费方式：FREE包邮 CONDITION_FREE条件包邮 NO_FREE不包邮 */
  @JsonProperty("shipping_method")
  private String shippingMethod;

  /** 条件包邮详情 */
  @JsonProperty("all_condition_free_detail")
  private AllConditionFreeDetail allConditionFreeDetail;

  /** 具体计费方法，默认运费，指定地区运费等 */
  @JsonProperty("all_freight_calc_method")
  private AllFreightCalcMethod allFreightCalcMethod;

  /** 创建时间戳 */
  @JsonProperty("create_time")
  private Long createTime;

  /** 更新时间戳 */
  @JsonProperty("update_time")
  private Long updateTime;

  /** 是否默认模板 */
  @JsonProperty("is_default")
  private Boolean isDefault;

  /** 不发货区域 */
  @JsonProperty("not_send_area")
  private NotSendArea notSendArea;

}
