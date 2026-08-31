package me.chanjar.weixin.channel.bean.delivery;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 订单发货信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.delivery.DeliverySendParam}。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
@Deprecated
public class DeliverySendParam implements Serializable {

  private static final long serialVersionUID = 4555821308266899135L;
  /** 订单ID */
  @JsonProperty("order_id")
  private String orderId;

  /** 物流信息 */
  @JsonProperty("delivery_list")
  private List<DeliveryInfo> deliveryList;
}
