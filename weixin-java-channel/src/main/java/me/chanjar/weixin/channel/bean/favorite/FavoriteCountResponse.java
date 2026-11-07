package me.chanjar.weixin.channel.bean.favorite;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 店铺收藏人数 响应
 *
 * @author <a href="https://github.com/copilot">GitHub Copilot</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.favorite.FavoriteCountResponse}。
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class FavoriteCountResponse extends WxChannelBaseResponse {

  /** 店铺首页收藏用户数 */
  @JsonProperty("favor_uv_acc_shop_homepage")
  private Long favorUvAccShopHomepage;

  /** 订单详情页收藏用户数 */
  @JsonProperty("favor_uv_acc_order_detail")
  private Long favorUvAccOrderDetail;

  /** 商品详情页收藏用户数 */
  @JsonProperty("favor_uv_acc_product_detail")
  private Long favorUvAccProductDetail;

  /** 其他场景收藏用户数 */
  @JsonProperty("favor_uv_acc_other_scene")
  private Long favorUvAccOtherScene;

  /** 所有收藏用户数 */
  @JsonProperty("favor_uv_acc_all")
  private Long favorUvAccAll;
}
