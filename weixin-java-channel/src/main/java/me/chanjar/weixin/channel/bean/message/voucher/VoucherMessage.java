package me.chanjar.weixin.channel.bean.message.voucher;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.message.WxChannelMessage;

/**
 * 发放团购优惠成功消息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JacksonXmlRootElement(localName = "xml")
public class VoucherMessage extends WxChannelMessage {

  private static final long serialVersionUID = 975858675917036089L;

  /** 发放团购优惠成功消息 */
  @JsonProperty("voucher_list")
  @JacksonXmlProperty(localName = "voucher_list")
  private List<VoucherInfo> voucherInfo;
}
