package me.chanjar.weixin.channel.bean.message.after;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.message.WxChannelMessage;

/**
 * 纠纷消息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.message.after.ComplaintMessage}。
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JacksonXmlRootElement(localName = "xml")
@Deprecated
public class ComplaintMessage extends WxChannelMessage {

  private static final long serialVersionUID = 5358093415172409157L;
  /** 状态信息 */
  @JsonProperty("finder_shop_complaint")
  @JacksonXmlProperty(localName = "finder_shop_complaint")
  private ComplaintInfo info;

}
