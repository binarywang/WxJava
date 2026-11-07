package me.chanjar.weixin.channel.bean.message.after;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.message.WxChannelMessage;

/**
 * 售后消息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.message.after.AfterSaleMessage}。
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JacksonXmlRootElement(localName = "xml")
@Deprecated
public class AfterSaleMessage extends WxChannelMessage {

  private static final long serialVersionUID = -7263404451639198126L;
  /** 状态信息 */
  @JsonProperty("finder_shop_aftersale_status_update")
  @JacksonXmlProperty(localName = "finder_shop_aftersale_status_update")
  private AfterSaleStatusInfo info;
}
