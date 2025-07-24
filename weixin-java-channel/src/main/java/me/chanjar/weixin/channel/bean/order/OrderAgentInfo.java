package me.chanjar.weixin.channel.bean.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 授权账号信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class OrderAgentInfo implements Serializable {

  private static final long serialVersionUID = 6396067079343033841L;

  /**
   * 授权视频号id
   */
  @JsonProperty("agent_finder_id")
  private String agentFinderId;

  /**
   * 授权视频号昵称
   */
  @JsonProperty("agent_finder_nickname")
  private String agentFinderNickname;
}
