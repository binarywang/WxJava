package me.chanjar.weixin.channel.bean.message.fund;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 账户信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@Deprecated
public class BankNotifyInfo implements Serializable {

  private static final long serialVersionUID = 4192569196686180014L;
  /** 结算账户变更事件, 1.修改结算账户 */
  @JsonProperty("event")
  @JacksonXmlProperty(localName = "event")
  private Integer event;

}
