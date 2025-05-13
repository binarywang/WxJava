package me.chanjar.weixin.channel.api.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.channel.api.WxAssistantService;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.window.request.AddWindowProductRequest;
import me.chanjar.weixin.channel.bean.window.request.GetWindowProductListRequest;
import me.chanjar.weixin.channel.bean.window.request.WindowProductRequest;
import me.chanjar.weixin.channel.bean.window.response.GetWindowProductListResponse;
import me.chanjar.weixin.channel.bean.window.response.GetWindowProductResponse;
import me.chanjar.weixin.channel.util.ResponseUtils;
import me.chanjar.weixin.common.error.WxErrorException;

import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Assistant.ADD_WINDOW_PRODUCT_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Assistant.GET_WINDOW_PRODUCT_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Assistant.LIST_WINDOW_PRODUCT_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Assistant.OFF_WINDOW_PRODUCT_URL;

/**
 * 视频号助手 橱窗管理服务
 *
 * @author <a href="https://github.com/imyzt">imyzt</a>
 */
@RequiredArgsConstructor
@Slf4j
public class WxAssistantServiceImpl implements WxAssistantService {

  /** 微信商店服务 */
  private final BaseWxChannelServiceImpl<?, ?> shopService;
  @Override
  public WxChannelBaseResponse addWindowProduct(AddWindowProductRequest req) throws WxErrorException {
    String resJson = shopService.post(ADD_WINDOW_PRODUCT_URL, "{}");
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
  }

  @Override
  public GetWindowProductResponse getWindowProduct(WindowProductRequest req) throws WxErrorException {
    String resJson = shopService.post(GET_WINDOW_PRODUCT_URL, "{}");
    return ResponseUtils.decode(resJson, GetWindowProductResponse.class);
  }

  @Override
  public GetWindowProductListResponse getWindowProductList(GetWindowProductListRequest req) throws WxErrorException {
    String resJson = shopService.post(LIST_WINDOW_PRODUCT_URL, "{}");
    return ResponseUtils.decode(resJson, GetWindowProductListResponse.class);
  }

  @Override
  public WxChannelBaseResponse offWindowProduct(WindowProductRequest req) throws WxErrorException {
    String resJson = shopService.post(OFF_WINDOW_PRODUCT_URL, "{}");
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
  }
}
