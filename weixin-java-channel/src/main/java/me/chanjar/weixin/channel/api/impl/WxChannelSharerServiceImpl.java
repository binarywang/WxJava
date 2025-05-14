package me.chanjar.weixin.channel.api.impl;


import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Share.BIND_SHARER_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Share.LIST_SHARER_ORDER_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Share.LIST_SHARER_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Share.SEARCH_SHARER_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Share.UNBIND_SHARER_URL;

import com.google.gson.JsonObject;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.channel.api.WxChannelSharerService;
import me.chanjar.weixin.channel.bean.sharer.SharerBindResponse;
import me.chanjar.weixin.channel.bean.sharer.SharerInfoResponse;
import me.chanjar.weixin.channel.bean.sharer.SharerListParam;
import me.chanjar.weixin.channel.bean.sharer.SharerOrderParam;
import me.chanjar.weixin.channel.bean.sharer.SharerOrderResponse;
import me.chanjar.weixin.channel.bean.sharer.SharerSearchParam;
import me.chanjar.weixin.channel.bean.sharer.SharerSearchResponse;
import me.chanjar.weixin.channel.bean.sharer.SharerUnbindParam;
import me.chanjar.weixin.channel.bean.sharer.SharerUnbindResponse;
import me.chanjar.weixin.channel.util.ResponseUtils;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonHelper;

/**
 * 视频号小店 分享员服务实现
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Slf4j
public class WxChannelSharerServiceImpl implements WxChannelSharerService {

  /** 微信商店服务 */
  private final BaseWxChannelServiceImpl<?, ?> shopService;

  public WxChannelSharerServiceImpl(BaseWxChannelServiceImpl<?, ?> shopService) {
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
