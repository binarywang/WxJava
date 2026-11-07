package me.chanjar.weixin.channel.bean.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.delivery.FreightProductInfo;

/**
 * 发货物流信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.order.DeliveryProductInfo}。
 */
@Data
@NoArgsConstructor
@Deprecated
public class DeliveryProductInfo implements Serializable {

  private static final long serialVersionUID = -8110532854439612471L;
  /** 快递单号 */
  @JsonProperty("waybill_id")
  private String waybillId;

  /** 快递公司编码 */
  @JsonProperty("delivery_id")
  private String deliveryId;

  /** 包裹中商品信息 */
  @JsonProperty("product_infos")
  private List<FreightProductInfo> productInfos;

  /** 快递公司名称 */
  @JsonProperty("delivery_name")
  private String deliveryName;

  /** 发货时间，秒级时间戳 */
  @JsonProperty("delivery_time")
  private Long deliveryTime;

  /** 配送方式，枚举值见DeliveryType {@link me.chanjar.weixin.channel.enums.DeliveryType} */
  @JsonProperty("deliver_type")
  private Integer deliverType;

  /** 发货地址 */
  @JsonProperty("delivery_address")
  private OrderAddressInfo deliveryAddress;

}
