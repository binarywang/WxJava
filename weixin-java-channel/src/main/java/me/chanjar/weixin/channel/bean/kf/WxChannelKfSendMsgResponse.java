package me.chanjar.weixin.channel.bean.kf;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/** 发送客服消息响应。 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class WxChannelKfSendMsgResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 1L;

  /** 消息 id。 */
  @JsonProperty("msg_id")
  private String msgId;
}
