package com.binarywang.wxjava.store.bean.message.supplier;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.message.WxStoreMessage;

/**
 * 团长商品变更 消息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JacksonXmlRootElement(localName = "xml")
public class SupplierItemMessage extends WxStoreMessage {

  private static final long serialVersionUID = -4520611382070764349L;
  /** 账户信息 */
  @JsonProperty("item_info")
  @JacksonXmlProperty(localName = "item_info")
  private SupplierItemInfo itemInfo;
}
