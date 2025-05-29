package me.chanjar.weixin.channel.bean.message.store;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.message.WxChannelMessage;

/**
 * 小店注销消息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JacksonXmlRootElement(localName = "xml")
public class CloseStoreMessage extends WxChannelMessage {

  private static final long serialVersionUID = 7619787772418774020L;

  /** appid */
  @JsonProperty("appid")
  @JacksonXmlProperty(localName = "appid")
  private String appid;

  /** Unix时间戳，即格林威治时间1970年01月01日00时00分00秒(北京时间1970年01月01日08时00分00秒)起至现在的总秒数 */
  @JsonProperty("close_timestamp")
  @JacksonXmlProperty(localName = "close_timestamp")
  private Long closeTimestamp;

}
