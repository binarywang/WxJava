package me.chanjar.weixin.channel.bean.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 拒绝待发货前更换SKU请求 请求参数
 *
 * @author GitHub Copilot
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class PreShipmentChangeSkuRejectParam implements Serializable {

  private static final long serialVersionUID = 1L;

  /** 订单ID */
  @JsonProperty("order_id")
  private String orderId;

  /** 拒绝原因 */
  @JsonProperty("reject_reason")
  private String rejectReason;

}
