package me.chanjar.weixin.channel.bean.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 订单商品贺卡信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class OrderGreetingCardInfo implements Serializable {
  private static final long serialVersionUID = -6391443179945240242L;

  /** 贺卡落款，用户选填 */
  @JsonProperty("giver_name")
  private String giverName;

  /** 贺卡称谓，用户选填 */
  @JsonProperty("receiver_name")
  private String receiverName;

  /** 贺卡内容，用户必填 */
  @JsonProperty("greeting_message")
  private String greetingMessage;
}
