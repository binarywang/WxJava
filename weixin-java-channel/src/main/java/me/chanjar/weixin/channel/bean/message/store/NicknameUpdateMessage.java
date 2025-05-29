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
 * 小店修改名称消息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JacksonXmlRootElement(localName = "xml")
public class NicknameUpdateMessage extends WxChannelMessage {

  private static final long serialVersionUID = 7619787772418774020L;

  /** appid */
  @JsonProperty("appid")
  @JacksonXmlProperty(localName = "appid")
  private String appid;

  /** 小店旧昵称 */
  @JsonProperty("old_nickname")
  @JacksonXmlProperty(localName = "old_nickname")
  private String oldNickname;

  /** 小店新昵称 */
  @JsonProperty("new_nickname")
  @JacksonXmlProperty(localName = "new_nickname")
  private String newNickname;

}
