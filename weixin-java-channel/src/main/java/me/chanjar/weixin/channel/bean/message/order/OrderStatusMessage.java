package me.chanjar.weixin.channel.bean.message.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.Map;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.message.WxChannelMessage;

/**
 * 订单状态消息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JacksonXmlRootElement(localName = "xml")
@Deprecated
public class OrderStatusMessage extends WxChannelMessage {

  private static final long serialVersionUID = -356717038344749283L;
  /** 订单ID */
  @JsonProperty("order_id")
  @JacksonXmlProperty(localName = "order_id")
  private String orderId;

  /** 订单状态 {@link me.chanjar.weixin.channel.enums.WxOrderStatus} */
  @JsonProperty("status")
  @JacksonXmlProperty(localName = "status")
  private Integer status;

  @JsonProperty("ProductOrderStatusUpdate")
  @JacksonXmlProperty(localName = "ProductOrderStatusUpdate")
  private void unpackNameFromNestedObject(Map<String, Object> map) {
    if (map == null) {
      return;
    }
    Object obj = null;
    obj = map.get("order_id");
    if (obj != null) {
      this.orderId = obj.toString();
    }
    obj = map.get("status");
    if (obj != null) {
      if (obj instanceof Integer) {
        this.status = (Integer) obj;
      } else if (obj instanceof String) {
        this.status = Integer.parseInt((String) obj);
      }
    }
  }
}
