package me.chanjar.weixin.channel.api.impl;


import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Coupon.CREATE_COUPON_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Coupon.GET_COUPON_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Coupon.GET_USER_COUPON_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Coupon.LIST_COUPON_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Coupon.LIST_USER_COUPON_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Coupon.UPDATE_COUPON_STATUS_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Coupon.UPDATE_COUPON_URL;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.channel.api.WxChannelCouponService;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.coupon.CouponIdInfo;
import me.chanjar.weixin.channel.bean.coupon.CouponIdResponse;
import me.chanjar.weixin.channel.bean.coupon.CouponInfoResponse;
import me.chanjar.weixin.channel.bean.coupon.CouponListParam;
import me.chanjar.weixin.channel.bean.coupon.CouponListResponse;
import me.chanjar.weixin.channel.bean.coupon.CouponParam;
import me.chanjar.weixin.channel.bean.coupon.CouponStatusParam;
import me.chanjar.weixin.channel.bean.coupon.UserCouponIdParam;
import me.chanjar.weixin.channel.bean.coupon.UserCouponListParam;
import me.chanjar.weixin.channel.bean.coupon.UserCouponListResponse;
import me.chanjar.weixin.channel.bean.coupon.UserCouponResponse;
import me.chanjar.weixin.channel.util.ResponseUtils;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 视频号小店 优惠券服务实现
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Slf4j
public class WxChannelCouponServiceImpl implements WxChannelCouponService {

  /** 微信商店服务 */
  private final BaseWxChannelServiceImpl<?, ?> shopService;

  public WxChannelCouponServiceImpl(BaseWxChannelServiceImpl<?, ?> shopService) {
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
  public WxChannelBaseResponse updateCouponStatus(String couponId, Integer status) throws WxErrorException {
    CouponStatusParam param = new CouponStatusParam(couponId, status);
    String resJson = shopService.post(UPDATE_COUPON_STATUS_URL, param);
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
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
