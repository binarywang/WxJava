package me.chanjar.weixin.channel.api;

import me.chanjar.weixin.channel.bean.favorite.FavoriteCountResponse;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 视频号小店 收藏管理接口
 *
 * @author <a href="https://github.com/copilot">GitHub Copilot</a>
 * @link <a href="https://developers.weixin.qq.com/doc/channels/API/favorite/shopfavorite/">收藏管理接口文档</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.api.WxStoreFavoriteService}。
 */
@Deprecated
public interface WxChannelFavoriteService {

  /**
   * 获取店铺收藏的人数
   *
   * @return 店铺收藏人数响应
   * @throws WxErrorException 异常
   */
  FavoriteCountResponse getFavoriteCount() throws WxErrorException;
}
