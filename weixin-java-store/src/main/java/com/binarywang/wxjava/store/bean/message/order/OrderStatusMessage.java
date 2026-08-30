package com.binarywang.wxjava.store.bean.message.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.Map;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.message.WxStoreMessage;

/**
 * 订单状态消息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JacksonXmlRootElement(localName = "xml")
public class OrderStatusMessage extends WxStoreMessage {

  private static final long serialVersionUID = -356717038344749283L;
  /** 订单ID */
  @JsonProperty("order_id")
  @JacksonXmlProperty(localName = "order_id")
  private String orderId;

  /** 订单状态 {@link com.binarywang.wxjava.store.enums.WxOrderStatus} */
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
