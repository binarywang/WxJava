package me.chanjar.weixin.channel.api.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.channel.api.WxTalentService;
import me.chanjar.weixin.channel.bean.talent.TalentOrderDetailParam;
import me.chanjar.weixin.channel.bean.talent.TalentOrderDetailResponse;
import me.chanjar.weixin.channel.bean.talent.TalentOrderListParam;
import me.chanjar.weixin.channel.bean.talent.TalentOrderListResponse;
import me.chanjar.weixin.channel.bean.talent.TalentWindowProductDetailParam;
import me.chanjar.weixin.channel.bean.talent.TalentWindowProductDetailResponse;
import me.chanjar.weixin.channel.bean.talent.TalentWindowProductListParam;
import me.chanjar.weixin.channel.bean.talent.TalentWindowProductListResponse;
import me.chanjar.weixin.channel.util.ResponseUtils;
import me.chanjar.weixin.common.error.WxErrorException;

import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Talent.GET_ORDER_DETAIL_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Talent.GET_ORDER_LIST_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Talent.GET_WINDOW_PRODUCT_DETAIL_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Talent.GET_WINDOW_PRODUCT_LIST_URL;

/**
 * 微信小店-带货助手服务实现
 *
 * @author <a href="https://github.com/features/copilot">GitHub Copilot</a>
 */
@RequiredArgsConstructor
@Slf4j
public class WxTalentServiceImpl implements WxTalentService {

  /** 微信商店服务 */
  private final BaseWxChannelServiceImpl<?, ?> shopService;

  @Override
  public TalentOrderListResponse getOrderList(TalentOrderListParam param) throws WxErrorException {
    String resJson = shopService.post(GET_ORDER_LIST_URL, param);
    return ResponseUtils.decode(resJson, TalentOrderListResponse.class);
  }

  @Override
  public TalentOrderDetailResponse getOrderDetail(TalentOrderDetailParam param) throws WxErrorException {
    String resJson = shopService.post(GET_ORDER_DETAIL_URL, param);
    return ResponseUtils.decode(resJson, TalentOrderDetailResponse.class);
  }

  @Override
  public TalentWindowProductListResponse getWindowProductList(TalentWindowProductListParam param)
    throws WxErrorException {
    String resJson = shopService.post(GET_WINDOW_PRODUCT_LIST_URL, param);
    return ResponseUtils.decode(resJson, TalentWindowProductListResponse.class);
  }

  @Override
  public TalentWindowProductDetailResponse getWindowProductDetail(TalentWindowProductDetailParam param)
    throws WxErrorException {
    String resJson = shopService.post(GET_WINDOW_PRODUCT_DETAIL_URL, param);
    return ResponseUtils.decode(resJson, TalentWindowProductDetailResponse.class);
  }
}
