package me.chanjar.weixin.channel.api;

import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.coupon.CouponIdResponse;
import me.chanjar.weixin.channel.bean.coupon.CouponInfoResponse;
import me.chanjar.weixin.channel.bean.coupon.CouponListParam;
import me.chanjar.weixin.channel.bean.coupon.CouponListResponse;
import me.chanjar.weixin.channel.bean.coupon.CouponParam;
import me.chanjar.weixin.channel.bean.coupon.UserCouponListParam;
import me.chanjar.weixin.channel.bean.coupon.UserCouponListResponse;
import me.chanjar.weixin.channel.bean.coupon.UserCouponResponse;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 视频号小店 优惠券服务
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.api.WxStoreCouponService}。
 */
@Deprecated
public interface WxChannelCouponService {

  /**
   * 创建优惠券
   *
   * @param coupon 优惠券
   * @return 优惠券ID
   *
   * @throws WxErrorException 异常
   */
  CouponIdResponse createCoupon(CouponParam coupon) throws WxErrorException;

  /**
   * 更新优惠券
   *
   * @param coupon 优惠券
   * @return 优惠券ID
   *
   * @throws WxErrorException 异常
   */
  CouponIdResponse updateCoupon(CouponParam coupon) throws WxErrorException;

  /**
   * 更新优惠券状态
   *
   * @param couponId 优惠券ID
   * @param status   状态 2生效 4已作废 5删除 {@link me.chanjar.weixin.channel.enums.WxCouponStatus}
   * @return BaseResponse
   *
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse updateCouponStatus(String couponId, Integer status) throws WxErrorException;

  /**
   * 获取优惠券详情
   *
   * @param couponId 优惠券ID
   * @return CouponInfoResponse
   *
   * @throws WxErrorException 异常
   */
  CouponInfoResponse getCoupon(String couponId) throws WxErrorException;

  /**
   * 获取优惠券ID列表
   *
   * @param param 条件参数
   * @return 优惠券ID列表
   *
   * @throws WxErrorException 异常
   */
  CouponListResponse getCouponList(CouponListParam param) throws WxErrorException;

  /**
   * 获取用户优惠券
   *
   * @param openId       用户openid
   * @param userCouponId 用户优惠券ID
   * @return UserCouponResponse
   *
   * @throws WxErrorException 异常
   */
  UserCouponResponse getUserCoupon(String openId, String userCouponId) throws WxErrorException;

  /**
   * 获取用户优惠券ID列表
   *
   * @param param 条件参数
   * @return UserCouponListResponse
   *
   * @throws WxErrorException 异常
   */
  UserCouponListResponse getUserCouponList(UserCouponListParam param) throws WxErrorException;
}
