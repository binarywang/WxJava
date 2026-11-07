package me.chanjar.weixin.channel.bean.shop;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 店铺口令 响应
 *
 * @author <a href="https://github.com/copilot">GitHub Copilot</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.shop.ShopTagLinkResponse}。
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class ShopTagLinkResponse extends WxChannelBaseResponse {

  /** 店铺微信口令 */
  @JsonProperty("shop_taglink")
  private String shopTaglink;
}
