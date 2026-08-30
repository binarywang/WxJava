package com.binarywang.wxjava.store.bean.delivery;

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
public class DeliveryInfo implements Serializable {

  private static final long serialVersionUID = -6205626967305385248L;
  /** 快递单号 */
  @JsonProperty("waybill_id")
  private String waybillId;

  /** 快递公司id，通过【获取快递公司列表】接口获得，非主流快递公司可以填OTHER */
  @JsonProperty("delivery_id")
  private String deliveryId;

  /** 发货方式，1:自寄快递发货，3:虚拟商品无需物流发货（只有deliver_method=1的订单可以使用虚拟发货） */
  @JsonProperty("deliver_type")
  private Integer deliverType;

  /** 包裹中商品信息 */
  @JsonProperty("product_infos")
  private List<FreightProductInfo> productInfos;
}
