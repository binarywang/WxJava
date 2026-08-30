package me.chanjar.weixin.channel.bean.message.supplier;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.message.WxChannelMessage;

/**
 * 团长商品变更 消息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JacksonXmlRootElement(localName = "xml")
@Deprecated
public class SupplierItemMessage extends WxChannelMessage {

  private static final long serialVersionUID = -4520611382070764349L;
  /** 账户信息 */
  @JsonProperty("item_info")
  @JacksonXmlProperty(localName = "item_info")
  private SupplierItemInfo itemInfo;
}
