package me.chanjar.weixin.cp.api;

import lombok.NonNull;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.living.*;

/**
 * 企业微信直播接口.
 * 官方文档：https://work.weixin.qq.com/api/doc/90000/90135/93633
 *
 * @author Wang_Wong  created on  2021-12-21
 */
public interface WxCpLivingService {

  /**
   * 获取微信观看直播凭证
   * 请求方式： POST（HTTPS）
   * 请求地址： https://qyapi.weixin.qq.com/cgi-bin/living/get_living_code?access_token=ACCESS_TOKEN
   *
   * @param openId   用户openid
   * @param livingId 直播id
   * @return living_code 微信观看直播凭证
   * @throws WxErrorException the wx error exception
   */
  String getLivingCode(@NonNull String openId, @NonNull String livingId) throws WxErrorException;

  /**
   * 获取直播详情
   * 请求方式：GET（HTTPS）
   * {@code 请求地址：https://qyapi.weixin.qq.com/cgi-bin/living/get_living_info?access_token=ACCESS_TOKEN&livingid=LIVINGID}
   *
   * @param livingId 直播id
   * @return 获取的直播详情 living info
   * @throws WxErrorException the wx error exception
   */
  WxCpLivingInfo getLivingInfo(@NonNull String livingId) throws WxErrorException;

  /**
   * 获取直播观看明细
   * 通过该接口可以获取所有观看直播的人员统计
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/living/get_watch_stat?access_token=ACCESS_TOKEN
   *
   * @param livingId 直播id
   * @param nextKey  上一次调用时返回的next_key，初次调用可以填”0”
   * @return watch stat
   * @throws WxErrorException the wx error exception
   */
  WxCpWatchStat getWatchStat(@NonNull String livingId, String nextKey) throws WxErrorException;

  /**
   * 获取成员直播ID列表
   * 通过此接口可以获取指定成员的所有直播ID
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/living/get_user_all_livingid?access_token=ACCESS_TOKEN
   *
   * @param userId 企业成员的userid
   * @param cursor 上一次调用时返回的next_cursor，第一次拉取可以不填
   * @param limit  每次拉取的数据量，默认值和最大值都为100
   * @return user all living id
   * @throws WxErrorException the wx error exception
   */
  WxCpLivingResult.LivingIdResult getUserAllLivingId(@NonNull String userId, String cursor, Integer limit) throws WxErrorException;

  /**
   * 获取跳转小程序商城的直播观众信息
   * 通过此接口，开发者可获取跳转小程序商城的直播间(“推广产品”直播)观众id、邀请人id及对应直播间id，以打通卖货直播的“人货场”信息闭环。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/living/get_living_share_info?access_token=ACCESS_TOKEN
   *
   * @param wwShareCode "推广产品"直播观众跳转小程序商城时会在小程序path中带上ww_share_code=xxxxx参数
   * @return living share info
   * @throws WxErrorException the wx error exception
   */
  WxCpLivingShareInfo getLivingShareInfo(@NonNull String wwShareCode) throws WxErrorException;

  /**
   * 创建预约直播
   * 请求方式： POST（HTTPS）
   * 请求地址： https://qyapi.weixin.qq.com/cgi-bin/living/create?access_token=ACCESS_TOKEN
   *
   * @param request 创建预约直播请求参数.
   * @return livingId （直播id）
   * @throws WxErrorException the wx error exception
   */
  String livingCreate(WxCpLivingCreateRequest request) throws WxErrorException;

  /**
   * 修改预约直播
   * 请求方式： POST（HTTPS）
   * 请求地址： https://qyapi.weixin.qq.com/cgi-bin/living/modify?access_token=ACCESS_TOKEN
   *
   * @param request 修改预约直播请求参数.
   * @return wx cp living result
   * @throws WxErrorException the wx error exception
   */
  WxCpLivingResult livingModify(WxCpLivingModifyRequest request) throws WxErrorException;

  /**
   * 取消预约直播
   * 请求方式： POST（HTTPS）
   * 请求地址： https://qyapi.weixin.qq.com/cgi-bin/living/cancel?access_token=ACCESS_TOKEN
   *
   * @param livingId 直播id，仅允许取消预约状态下的直播id
   * @return wx cp living result
   * @throws WxErrorException the wx error exception
   */
  WxCpLivingResult livingCancel(@NonNull String livingId) throws WxErrorException;

  /**
   * 删除直播回放
   * 请求方式： POST（HTTPS）
   * 请求地址： https://qyapi.weixin.qq.com/cgi-bin/living/delete_replay_data?access_token=ACCESS_TOKEN
   *
   * @param livingId 直播id
   * @return wx cp living result
   * @throws WxErrorException the wx error exception
   */
  WxCpLivingResult deleteReplayData(@NonNull String livingId) throws WxErrorException;

}
