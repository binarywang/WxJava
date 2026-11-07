package com.binarywang.wxjava.store.api.impl;


import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Share.BIND_SHARER_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Share.LIST_SHARER_ORDER_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Share.LIST_SHARER_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Share.SEARCH_SHARER_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Share.UNBIND_SHARER_URL;

import com.google.gson.JsonObject;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import com.binarywang.wxjava.store.api.WxStoreSharerService;
import com.binarywang.wxjava.store.bean.sharer.SharerBindResponse;
import com.binarywang.wxjava.store.bean.sharer.SharerInfoResponse;
import com.binarywang.wxjava.store.bean.sharer.SharerListParam;
import com.binarywang.wxjava.store.bean.sharer.SharerOrderParam;
import com.binarywang.wxjava.store.bean.sharer.SharerOrderResponse;
import com.binarywang.wxjava.store.bean.sharer.SharerSearchParam;
import com.binarywang.wxjava.store.bean.sharer.SharerSearchResponse;
import com.binarywang.wxjava.store.bean.sharer.SharerUnbindParam;
import com.binarywang.wxjava.store.bean.sharer.SharerUnbindResponse;
import com.binarywang.wxjava.store.util.ResponseUtils;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonHelper;

/**
 * 微信小店 分享员服务实现
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Slf4j
public class WxStoreSharerServiceImpl implements WxStoreSharerService {

  /** 微信商店服务 */
  private final BaseWxStoreServiceImpl<?, ?> shopService;

  public WxStoreSharerServiceImpl(BaseWxStoreServiceImpl<?, ?> shopService) {
    this.shopService = shopService;
  }

  @Override
  public SharerBindResponse bindSharer(String username) throws WxErrorException {
    JsonObject jsonObject = GsonHelper.buildJsonObject("username", username);

    String resJson = shopService.post(BIND_SHARER_URL, jsonObject);
    return ResponseUtils.decode(resJson, SharerBindResponse.class);
  }

  @Override
  public SharerSearchResponse searchSharer(String openid, String username) throws WxErrorException {
    SharerSearchParam param = new SharerSearchParam(openid, username);
    String resJson = shopService.post(SEARCH_SHARER_URL, param);
    return ResponseUtils.decode(resJson, SharerSearchResponse.class);
  }

  @Override
  public SharerInfoResponse listSharer(Integer page, Integer pageSize, Integer sharerType) throws WxErrorException {
    SharerListParam param = new SharerListParam(page, pageSize, sharerType);
    String resJson = shopService.post(LIST_SHARER_URL, param);
    return ResponseUtils.decode(resJson, SharerInfoResponse.class);
  }

  @Override
  public SharerOrderResponse listSharerOrder(SharerOrderParam param) throws WxErrorException {
    String resJson = shopService.post(LIST_SHARER_ORDER_URL, param);
    return ResponseUtils.decode(resJson, SharerOrderResponse.class);
  }

  @Override
  public SharerUnbindResponse unbindSharer(List<String> openIds) throws WxErrorException {
    SharerUnbindParam param = new SharerUnbindParam(openIds);
    String resJson = shopService.post(UNBIND_SHARER_URL, param);
    return ResponseUtils.decode(resJson, SharerUnbindResponse.class);
  }
}
