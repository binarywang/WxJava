package me.chanjar.weixin.channel.bean.home.tree;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 资金流水响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TreeProductListResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 4566848209585635054L;

  /** 结果 */
  @JsonProperty("resp")
  private TreeProductListResult resp;
}
