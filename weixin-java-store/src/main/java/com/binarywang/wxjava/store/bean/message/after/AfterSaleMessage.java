package com.binarywang.wxjava.store.bean.message.after;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.message.WxStoreMessage;

/**
 * 售后消息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JacksonXmlRootElement(localName = "xml")
public class AfterSaleMessage extends WxStoreMessage {

  private static final long serialVersionUID = -7263404451639198126L;
  /** 状态信息 */
  @JsonProperty("finder_shop_aftersale_status_update")
  @JacksonXmlProperty(localName = "finder_shop_aftersale_status_update")
  private AfterSaleStatusInfo info;
}
