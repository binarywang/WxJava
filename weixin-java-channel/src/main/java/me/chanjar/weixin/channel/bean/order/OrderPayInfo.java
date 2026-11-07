package me.chanjar.weixin.channel.bean.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 支付信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.order.OrderPayInfo}。
 */
@Data
@NoArgsConstructor
@Deprecated
public class OrderPayInfo implements Serializable {

  private static final long serialVersionUID = -5085386252699113948L;
  /** 预支付id */
  @JsonProperty("payment_method")
  private Integer paymentMethod;

  /** 支付时间，秒级时间戳 */
  @JsonProperty("pay_time")
  private Long payTime;

  /** 支付单号 */
  @JsonProperty("transaction_id")
  private String transactionId;

}
