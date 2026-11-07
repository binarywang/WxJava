package com.binarywang.wxjava.store.api.impl;

import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Favorite.GET_FAVORITE_COUNT;

import com.binarywang.wxjava.store.api.WxStoreFavoriteService;
import com.binarywang.wxjava.store.bean.favorite.FavoriteCountResponse;
import com.binarywang.wxjava.store.util.ResponseUtils;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 微信小店 收藏管理接口实现
 *
 * @author <a href="https://github.com/copilot">GitHub Copilot</a>
 */
public class WxStoreFavoriteServiceImpl implements WxStoreFavoriteService {

  /** 微信商店服务 */
  private final BaseWxStoreServiceImpl<?, ?> shopService;

  public WxStoreFavoriteServiceImpl(BaseWxStoreServiceImpl<?, ?> shopService) {
    this.shopService = shopService;
  }

  @Override
  public FavoriteCountResponse getFavoriteCount() throws WxErrorException {
    String resJson = shopService.post(GET_FAVORITE_COUNT, "{}");
    return ResponseUtils.decode(resJson, FavoriteCountResponse.class);
  }
}
