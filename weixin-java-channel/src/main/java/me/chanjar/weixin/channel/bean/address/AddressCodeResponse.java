package me.chanjar.weixin.channel.bean.address;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 地址编码 响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class AddressCodeResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = -8994407971295563982L;

  /** 本行政编码地址信息 */
  @JsonProperty("addrs_msg")
  private AddressCode current;

  /** 下一级所有地址信息 */
  @JsonProperty("next_level_addrs")
  private List<AddressCode> list;
}
