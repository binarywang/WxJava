package com.binarywang.wxjava.store.api.impl;


import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Coupon.CREATE_COUPON_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Coupon.GET_COUPON_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Coupon.GET_USER_COUPON_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Coupon.LIST_COUPON_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Coupon.LIST_USER_COUPON_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Coupon.UPDATE_COUPON_STATUS_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Coupon.UPDATE_COUPON_URL;

import lombok.extern.slf4j.Slf4j;
import com.binarywang.wxjava.store.api.WxStoreCouponService;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;
import com.binarywang.wxjava.store.bean.coupon.CouponIdInfo;
import com.binarywang.wxjava.store.bean.coupon.CouponIdResponse;
import com.binarywang.wxjava.store.bean.coupon.CouponInfoResponse;
import com.binarywang.wxjava.store.bean.coupon.CouponListParam;
import com.binarywang.wxjava.store.bean.coupon.CouponListResponse;
import com.binarywang.wxjava.store.bean.coupon.CouponParam;
import com.binarywang.wxjava.store.bean.coupon.CouponStatusParam;
import com.binarywang.wxjava.store.bean.coupon.UserCouponIdParam;
import com.binarywang.wxjava.store.bean.coupon.UserCouponListParam;
import com.binarywang.wxjava.store.bean.coupon.UserCouponListResponse;
import com.binarywang.wxjava.store.bean.coupon.UserCouponResponse;
import com.binarywang.wxjava.store.util.ResponseUtils;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 微信小店 优惠券服务实现
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Slf4j
public class WxStoreCouponServiceImpl implements WxStoreCouponService {

  /** 微信商店服务 */
  private final BaseWxStoreServiceImpl<?, ?> shopService;

  public WxStoreCouponServiceImpl(BaseWxStoreServiceImpl<?, ?> shopService) {
    this.shopService = shopService;
  }

  @Override
  public CouponIdResponse createCoupon(CouponParam coupon) throws WxErrorException {
    String resJson = shopService.post(CREATE_COUPON_URL, coupon);
    return ResponseUtils.decode(resJson, CouponIdResponse.class);
  }

  @Override
  public CouponIdResponse updateCoupon(CouponParam coupon) throws WxErrorException {
    String resJson = shopService.post(UPDATE_COUPON_URL, coupon);
    return ResponseUtils.decode(resJson, CouponIdResponse.class);
  }

  @Override
  public WxStoreBaseResponse updateCouponStatus(String couponId, Integer status) throws WxErrorException {
    CouponStatusParam param = new CouponStatusParam(couponId, status);
    String resJson = shopService.post(UPDATE_COUPON_STATUS_URL, param);
    return ResponseUtils.decode(resJson, WxStoreBaseResponse.class);
  }

  @Override
  public CouponInfoResponse getCoupon(String couponId) throws WxErrorException {
    CouponIdInfo param = new CouponIdInfo(couponId);
    String resJson = shopService.post(GET_COUPON_URL, param);
    return ResponseUtils.decode(resJson, CouponInfoResponse.class);
  }

  @Override
  public CouponListResponse getCouponList(CouponListParam param) throws WxErrorException {
    String resJson = shopService.post(LIST_COUPON_URL, param);
    return ResponseUtils.decode(resJson, CouponListResponse.class);
  }

  @Override
  public UserCouponResponse getUserCoupon(String openId, String userCouponId) throws WxErrorException {
    UserCouponIdParam param = new UserCouponIdParam(openId, userCouponId);
    String resJson = shopService.post(GET_USER_COUPON_URL, param);
    return ResponseUtils.decode(resJson, UserCouponResponse.class);
  }

  @Override
  public UserCouponListResponse getUserCouponList(UserCouponListParam param) throws WxErrorException {
    String resJson = shopService.post(LIST_USER_COUPON_URL, param);
    return ResponseUtils.decode(resJson, UserCouponListResponse.class);
  }
}
