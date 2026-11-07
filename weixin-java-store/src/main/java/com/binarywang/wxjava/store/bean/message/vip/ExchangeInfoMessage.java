package com.binarywang.wxjava.store.bean.message.vip;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.message.WxStoreMessage;

/**
 * 积分兑换消息
 *
 * @author <a href="https://github.com/asushiye">asushiye</a>
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JacksonXmlRootElement(localName = "xml")
public class ExchangeInfoMessage extends WxStoreMessage {

  private static final long serialVersionUID = 2926346100146724110L;
  /** 积分兑换信息 */
  @JsonProperty("exchange_info")
  @JacksonXmlProperty(localName = "exchange_info")
  private ExchangeInfo exchangeInfo;
}
