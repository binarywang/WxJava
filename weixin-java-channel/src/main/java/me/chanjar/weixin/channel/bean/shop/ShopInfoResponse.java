package me.chanjar.weixin.channel.bean.shop;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 店铺基本信息响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@Deprecated
public class ShopInfoResponse extends WxChannelBaseResponse {

  @JsonProperty("info")
  private ShopInfo info;
}
