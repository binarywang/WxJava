package me.chanjar.weixin.channel.bean.message.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 订单id信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.message.order.OrderIdInfo}。
 */
@Data
@NoArgsConstructor
@Deprecated
public class OrderIdInfo implements Serializable {

  private static final long serialVersionUID = 5547544436235032051L;
  /** 订单ID */
  @JsonProperty("order_id")
  @JacksonXmlProperty(localName = "order_id")
  private String orderId;
}
