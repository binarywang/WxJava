package me.chanjar.weixin.channel.bean.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 虚拟号信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class VirtualNumberInfo implements Serializable {

  private static final long serialVersionUID = -372834823737476644L;

  /** 虚拟号 */
  @JsonProperty("virtual_number")
  private String virtualNumber;

  /** 分机号 */
  @JsonProperty("extension")
  private String extension;

  /** 过期时间戳 */
  @JsonProperty("expiration")
  private Long expiration;
}
