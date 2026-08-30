package me.chanjar.weixin.channel.bean.address;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 地址id 响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class AddressIdResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = -9218327846685744008L;

  /** 地址id */
  @JsonProperty("address_id")
  private String addressId;
}
