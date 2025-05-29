package me.chanjar.weixin.channel.bean.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 解码订单包含的敏感数据响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DecodeSensitiveInfoResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 935829924760021624L;

  /** 收货信息 */
  @JsonProperty("address_info")
  private DecodeAddressInfo addressInfo;

  /** 虚拟号信息 */
  @JsonProperty("virtual_number_info")
  private VirtualNumberInfo virtualNumberInfo;
}
