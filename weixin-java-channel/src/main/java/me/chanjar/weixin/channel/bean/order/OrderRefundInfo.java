package me.chanjar.weixin.channel.bean.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 订单退款信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class OrderRefundInfo implements Serializable {
  private static final long serialVersionUID = -7257910073388645919L;

  /** 退还运费金额，礼物订单(is_present=true)可能存在 */
  @JsonProperty("refund_freight")
  private Integer refundFreight;
}
