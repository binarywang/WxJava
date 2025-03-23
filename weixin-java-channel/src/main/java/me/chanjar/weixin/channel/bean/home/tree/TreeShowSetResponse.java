package me.chanjar.weixin.channel.bean.home.tree;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TreeShowSetResponse extends WxChannelBaseResponse {

  /** resp */
  @JsonProperty("resp")
  private TreeAuditResult resp;
}
