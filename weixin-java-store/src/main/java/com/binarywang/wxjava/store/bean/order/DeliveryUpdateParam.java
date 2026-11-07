package com.binarywang.wxjava.store.bean.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.delivery.FreightProductInfo;

/**
 * 修改物流参数
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeliveryUpdateParam implements Serializable {

  /** 订单ID */
  @JsonProperty("order_id")
  private String orderId;

  /** 物流公司ID */
  @JsonProperty("delivery_list")
  private List<DeliveryInfo> deliveryList;

  @Data
  @NoArgsConstructor
  @JsonInclude(JsonInclude.Include.NON_NULL)
  public static class DeliveryInfo implements Serializable {

    private static final long serialVersionUID = 1348000697768633889L;
    /** 快递单号 */
    @JsonProperty("waybill_id")
    private String waybillId;

    /** 快递公司编码 */
    @JsonProperty("delivery_id")
    private String deliveryId;

    /** 配送方式，枚举值见DeliveryType {@link com.binarywang.wxjava.store.enums.DeliveryType} */
    @JsonProperty("deliver_type")
    private Integer deliverType;

    /** 包裹中商品信息 */
    @JsonProperty("product_infos")
    private List<FreightProductInfo> productInfos;
  }
}
