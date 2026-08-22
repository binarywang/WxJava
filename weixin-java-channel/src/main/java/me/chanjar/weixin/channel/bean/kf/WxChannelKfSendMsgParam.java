package me.chanjar.weixin.channel.bean.kf;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 发送客服消息请求参数。 */
@Data
@NoArgsConstructor
public class WxChannelKfSendMsgParam implements Serializable {

  private static final long serialVersionUID = 1L;

  /** 请求幂等标识。 */
  @JsonProperty("request_id")
  private String requestId;

  /** 接收消息的用户 openid。 */
  @JsonProperty("open_id")
  private String openId;

  /** 消息类型。 */
  @JsonProperty("msg_type")
  private String msgType;

  /** 文本消息内容。 */
  @JsonProperty("text")
  private Text text;

  @Data
  @NoArgsConstructor
  public static class Text implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("content")
    private String content;
  }
}
