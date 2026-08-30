package com.binarywang.wxjava.store.bean.message.fund;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.message.WxStoreMessage;

/**
 * 账户变更通知 消息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JacksonXmlRootElement(localName = "xml")
public class WithdrawNotifyMessage extends WxStoreMessage {

  private static final long serialVersionUID = -2504086242143523430L;
  /** 账户信息 */
  @JsonProperty("withdraw_info")
  @JacksonXmlProperty(localName = "withdraw_info")
  private WithdrawNotifyInfo withdrawInfo;
}
