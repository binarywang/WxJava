package me.chanjar.weixin.channel.bean.kf;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

import java.io.Serializable;

/**
 * 发送客服消息返回结果
 *
 * @author <a href="https://github.com/github-copilot">GitHub Copilot</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxChannelKfSendMsgResponse extends WxChannelBaseResponse implements Serializable {

  private static final long serialVersionUID = -4994877385473101709L;

  /** 消息ID */
  @JsonProperty("msg_id")
  private String msgId;
}
