package me.chanjar.weixin.channel.bean.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 物流信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class OrderDeliveryInfo implements Serializable {

  private static final long serialVersionUID = -5348922760017557397L;
  /** 地址信息 */
  @JsonProperty("address_info")
  private OrderAddressInfo addressInfo;

  /** 发货物流信息 */
  @JsonProperty("delivery_product_info")
  private List<DeliveryProductInfo> deliveryProductInfos;

  /** 发货完成时间，秒级时间戳 */
  @JsonProperty("ship_done_time")
  private Long shipDoneTime;

  /** 订单发货方式，0普通物流 1虚拟发货，由商品的同名字段决定 */
  @JsonProperty("deliver_method")
  private Integer deliverMethod;

  /** 用户下单后申请修改收货地址，商家同意后该字段会覆盖订单地址信息 */
  @JsonProperty("address_under_review")
  private OrderAddressInfo addressUnderReview;

  /** 修改地址申请时间，秒级时间戳 */
  @JsonProperty("address_apply_time")
  private Long addressApplyTime;

  /** 电子面单代发时的订单密文 */
  @JsonProperty("ewaybill_order_code")
  private String ewaybillOrderCode;

  /** 订单质检类型 2生鲜类质检 1珠宝玉石类质检 0不需要；不传递本字段表示不需要 */
  @JsonProperty("quality_inspect_type")
  private String qualityInspectType;

  /** 质检信息 */
  @JsonProperty("quality_inspect_info")
  private QualityInsepctInfo qualityInspectInfo;

  /** 虚拟商品充值账户信息 */
  @JsonProperty("recharge_info")
  private RechargeInfo rechargeInfo;

}
