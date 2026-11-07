package me.chanjar.weixin.channel.bean.shop;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 店铺二维码 响应
 *
 * @author <a href="https://github.com/copilot">GitHub Copilot</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.shop.ShopQrCodeResponse}。
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class ShopQrCodeResponse extends WxChannelBaseResponse {

  /** 店铺二维码链接 */
  @JsonProperty("shop_qrcode")
  private String shopQrcode;
}
