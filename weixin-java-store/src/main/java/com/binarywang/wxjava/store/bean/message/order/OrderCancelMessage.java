package com.binarywang.wxjava.store.bean.message.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.message.WxStoreMessage;

/**
 * 订单取消消息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JacksonXmlRootElement(localName = "xml")
public class OrderCancelMessage extends WxStoreMessage {

  private static final long serialVersionUID = 5389546516473919310L;
  /** 订单信息 */
  @JsonProperty("order_info")
  @JacksonXmlProperty(localName = "order_info")
  private OrderCancelInfo orderInfo;
}
