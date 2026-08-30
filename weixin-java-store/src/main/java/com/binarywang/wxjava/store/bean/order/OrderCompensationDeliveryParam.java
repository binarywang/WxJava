package com.binarywang.wxjava.store.bean.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.delivery.DeliveryInfo;

/**
 * 订单补发货 请求参数
 *
 * @author GitHub Copilot
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class OrderCompensationDeliveryParam implements Serializable {

  private static final long serialVersionUID = 1L;

  /** 订单ID */
  @JsonProperty("order_id")
  private String orderId;

  /** 物流信息列表 */
  @JsonProperty("delivery_list")
  private List<DeliveryInfo> deliveryList;

}
