package me.chanjar.weixin.channel.api.impl;

import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.League.ADD_SUPPLIER_GOODS_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.League.GET_SUPPLIER_AUTH_STATUS_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.League.GET_SUPPLIER_AUTH_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.League.GET_SUPPLIER_GOODS_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.League.LIST_SUPPLIER_GOODS_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.League.REMOVE_SUPPLIER_GOODS_URL;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.channel.api.WxLeagueWindowService;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.league.window.AuthInfoResponse;
import me.chanjar.weixin.channel.bean.league.window.AuthStatusResponse;
import me.chanjar.weixin.channel.bean.league.window.ProductSearchParam;
import me.chanjar.weixin.channel.bean.league.window.WindowProductListResponse;
import me.chanjar.weixin.channel.bean.league.window.WindowProductParam;
import me.chanjar.weixin.channel.bean.league.window.WindowProductResponse;
import me.chanjar.weixin.channel.util.ResponseUtils;
import me.chanjar.weixin.common.error.WxErrorException;


/**
 * 视频号小店 优选联盟 团长合作达人管理服务
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Slf4j
public class WxLeagueWindowServiceImpl implements WxLeagueWindowService {

  /** 微信商店服务 */
  private final BaseWxChannelServiceImpl<?, ?> shopService;

  public WxLeagueWindowServiceImpl(BaseWxChannelServiceImpl<?, ?> shopService) {
    this.shopService = shopService;
  }

  @Override
  public WxChannelBaseResponse addProduct(String appid, String openfinderid, String productId)
    throws WxErrorException {
    WindowProductParam param = new WindowProductParam(appid, openfinderid, productId);
    String resJson = shopService.post(ADD_SUPPLIER_GOODS_URL, param);
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
  }

  @Override
  public WindowProductListResponse listProduct(ProductSearchParam param) throws WxErrorException {
    String resJson = shopService.post(LIST_SUPPLIER_GOODS_URL, param);
    return ResponseUtils.decode(resJson, WindowProductListResponse.class);
  }

  @Override
  public WxChannelBaseResponse removeProduct(String appid, String openfinderid, String productId)
    throws WxErrorException {
    WindowProductParam param = new WindowProductParam(appid, openfinderid, productId);
    String resJson = shopService.post(REMOVE_SUPPLIER_GOODS_URL, param);
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
  }

  @Override
  public WindowProductResponse getProductDetail(String appid, String openfinderid, String productId)
    throws WxErrorException {
    WindowProductParam param = new WindowProductParam(appid, openfinderid, productId);
    String resJson = shopService.post(GET_SUPPLIER_GOODS_URL, param);
    return ResponseUtils.decode(resJson, WindowProductResponse.class);
  }

  @Override
  public AuthInfoResponse getWindowAuthInfo(String finderId) throws WxErrorException {
    String reqJson = "{\"finder_id\":\"" + finderId + "\"}";
    String resJson = shopService.post(GET_SUPPLIER_AUTH_URL, reqJson);
    return ResponseUtils.decode(resJson, AuthInfoResponse.class);
  }

  @Override
  public AuthStatusResponse getWindowAuthStatus(String finderId) throws WxErrorException {
    String reqJson = "{\"finder_id\":\"" + finderId + "\"}";
    String resJson = shopService.post(GET_SUPPLIER_AUTH_STATUS_URL, reqJson);
    return ResponseUtils.decode(resJson, AuthStatusResponse.class);
  }
}
