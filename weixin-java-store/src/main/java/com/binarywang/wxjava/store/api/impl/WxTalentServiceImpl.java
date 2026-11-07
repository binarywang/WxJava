package com.binarywang.wxjava.store.api.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.binarywang.wxjava.store.api.WxTalentService;
import com.binarywang.wxjava.store.bean.talent.TalentOrderDetailParam;
import com.binarywang.wxjava.store.bean.talent.TalentOrderDetailResponse;
import com.binarywang.wxjava.store.bean.talent.TalentOrderListParam;
import com.binarywang.wxjava.store.bean.talent.TalentOrderListResponse;
import com.binarywang.wxjava.store.bean.talent.TalentWindowProductDetailParam;
import com.binarywang.wxjava.store.bean.talent.TalentWindowProductDetailResponse;
import com.binarywang.wxjava.store.bean.talent.TalentWindowProductListParam;
import com.binarywang.wxjava.store.bean.talent.TalentWindowProductListResponse;
import com.binarywang.wxjava.store.util.ResponseUtils;
import me.chanjar.weixin.common.error.WxErrorException;

import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Talent.GET_ORDER_DETAIL_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Talent.GET_ORDER_LIST_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Talent.GET_WINDOW_PRODUCT_DETAIL_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Talent.GET_WINDOW_PRODUCT_LIST_URL;

/**
 * 微信小店-带货助手服务实现
 *
 * @author <a href="https://github.com/features/copilot">GitHub Copilot</a>
 */
@RequiredArgsConstructor
@Slf4j
public class WxTalentServiceImpl implements WxTalentService {

  /** 微信商店服务 */
  private final BaseWxStoreServiceImpl<?, ?> shopService;

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
