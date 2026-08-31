package me.chanjar.weixin.channel.bean.shop;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 店铺H5链接 响应
 *
 * @author <a href="https://github.com/copilot">GitHub Copilot</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.shop.ShopH5UrlResponse}。
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class ShopH5UrlResponse extends WxChannelBaseResponse {

  /** 店铺H5链接 */
  @JsonProperty("shop_h5url")
  private String shopH5url;
}
