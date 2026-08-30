package me.chanjar.weixin.channel.bean.address;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 地址列表 响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class AddressListResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = -3997164605170764105L;

  /** 地址详情 */
  @JsonProperty("address_id_list")
  private List<String> ids;

}
