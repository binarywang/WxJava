package me.chanjar.weixin.channel.api.impl;

import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Favorite.GET_FAVORITE_COUNT;

import me.chanjar.weixin.channel.api.WxChannelFavoriteService;
import me.chanjar.weixin.channel.bean.favorite.FavoriteCountResponse;
import me.chanjar.weixin.channel.util.ResponseUtils;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 视频号小店 收藏管理接口实现
 *
 * @author <a href="https://github.com/copilot">GitHub Copilot</a>
 */
public class WxChannelFavoriteServiceImpl implements WxChannelFavoriteService {

  /** 微信商店服务 */
  private final BaseWxChannelServiceImpl<?, ?> shopService;

  public WxChannelFavoriteServiceImpl(BaseWxChannelServiceImpl<?, ?> shopService) {
    this.shopService = shopService;
  }

  @Override
  public FavoriteCountResponse getFavoriteCount() throws WxErrorException {
    String resJson = shopService.post(GET_FAVORITE_COUNT, "{}");
    return ResponseUtils.decode(resJson, FavoriteCountResponse.class);
  }
}
