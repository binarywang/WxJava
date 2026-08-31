package me.chanjar.weixin.channel.bean.message.fund;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.message.WxChannelMessage;

/**
 * 账户变更通知 消息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.message.fund.AccountNotifyMessage}。
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JacksonXmlRootElement(localName = "xml")
@Deprecated
public class AccountNotifyMessage extends WxChannelMessage {

  private static final long serialVersionUID = 3846692537729725664L;
  /** 账户信息 */
  @JsonProperty("account_info")
  @JacksonXmlProperty(localName = "account_info")
  private BankNotifyInfo accountInfo;
}
