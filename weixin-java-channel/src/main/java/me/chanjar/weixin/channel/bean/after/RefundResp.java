package me.chanjar.weixin.channel.bean.after;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 退款结果
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@Deprecated
public class RefundResp implements Serializable {

  private static final long serialVersionUID = 6549707043779644156L;
  /** code */
  @JsonProperty("code")
  private String code;

  /** ret */
  @JsonProperty("ret")
  private Integer ret;

  /** message */
  @JsonProperty("message")
  private String message;

}
