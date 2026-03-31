package me.chanjar.weixin.cp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.living.WxCpLivingResult;
import me.chanjar.weixin.cp.bean.school.*;

import java.util.List;

/**
 * 企业微信家校应用 复学码相关接口.
 * https://developer.work.weixin.qq.com/document/path/93744
 * <p>
 * 权限说明：
 * 仅复学码应用可以调用
 *
 * @author <a href="https://github.com/0katekate0">Wang_Wong</a> created on : 2022/5/31 9:10
 */
public interface WxCpSchoolService {

  /**
   * 获取老师健康信息
   * 请求方式： POST（HTTPS）
   * 请求地址： https://qyapi.weixin.qq.com/cgi-bin/school/user/get_teacher_customize_health_info?access_token=ACCESS_TOKEN
   *
   * @param date    the date
   * @param nextKey the next key
   * @param limit   the limit
   * @return teacher customize health info
   * @throws WxErrorException the wx error exception
   */
  WxCpCustomizeHealthInfo getTeacherCustomizeHealthInfo(String date, String nextKey, Integer limit) throws WxErrorException;

  /**
   * 获取学生健康信息
   * 请求方式： POST（HTTPS）
   * 请求地址： https://qyapi.weixin.qq.com/cgi-bin/school/user/get_student_customize_health_info?access_token=ACCESS_TOKEN
   *
   * @param date    the date
   * @param nextKey the next key
   * @param limit   the limit
   * @return student customize health info
   * @throws WxErrorException the wx error exception
   */
  WxCpCustomizeHealthInfo getStudentCustomizeHealthInfo(String date, String nextKey, Integer limit) throws WxErrorException;

  /**
   * 获取师生健康码
   * 请求方式：POST（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/school/user/get_health_qrcode?access_token=ACCESS_TOKEN
   *
   * @param userIds the user ids
   * @param type    the type
   * @return health qr code
   * @throws WxErrorException the wx error exception
   */
  WxCpResultList getHealthQrCode(List<String> userIds, Integer type) throws WxErrorException;

  /**
   * 获取学生付款结果
   * 请求方式： POST（HTTPS）
   * 请求地址： https://qyapi.weixin.qq.com/cgi-bin/school/get_payment_result?access_token=ACCESS_TOKEN
   *
   * @param paymentId the payment id
   * @return payment result
   * @throws WxErrorException the wx error exception
   */
  WxCpPaymentResult getPaymentResult(String paymentId) throws WxErrorException;

  /**
   * 获取订单详情
   * 请求方式： POST（HTTPS）
   * 请求地址： https://qyapi.weixin.qq.com/cgi-bin/school/get_trade?access_token=ACCESS_TOKEN
   *
   * @param paymentId the payment id
   * @param tradeNo   the trade no
   * @return trade
   * @throws WxErrorException the wx error exception
   */
  WxCpTrade getTrade(String paymentId, String tradeNo) throws WxErrorException;

  /**
   * 获取直播详情
   * <pre>
   * 请求方式：GET（HTTPS）
   * {@code 请求地址：https://qyapi.weixin.qq.com/cgi-bin/school/living/get_living_info?access_token=ACCESS_TOKEN&livingid=LIVINGID}
   * </pre>
   *
   * @param livingId the living id
   * @return living info
   * @throws WxErrorException the wx error exception
   */
  WxCpSchoolLivingInfo getLivingInfo(String livingId) throws WxErrorException;

  /**
   * 获取老师直播ID列表
   * 通过此接口可以获取指定老师的所有直播ID
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/living/get_user_all_livingid?access_token=ACCESS_TOKEN
   *
   * @param userId the user id
   * @param cursor the cursor
   * @param limit  the limit
   * @return user all living id
   * @throws WxErrorException the wx error exception
   */
  WxCpLivingResult.LivingIdResult getUserAllLivingId(String userId, String cursor, Integer limit) throws WxErrorException;

  /**
   * 获取观看直播统计
   * 通过该接口可以获取所有观看直播的人员统计
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/school/living/get_watch_stat?access_token=ACCESS_TOKEN
   *
   * @param livingId the living id
   * @param nextKey  the next key
   * @return watch stat
   * @throws WxErrorException the wx error exception
   */
  WxCpSchoolWatchStat getWatchStat(String livingId, String nextKey) throws WxErrorException;

  /**
   * 获取未观看直播统计
   * 通过该接口可以获取未观看直播的学生统计，学生的家长必须是已经关注「学校通知」才会纳入统计范围。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/school/living/get_unwatch_stat?access_token=ACCESS_TOKEN
   *
   * @param livingId the living id
   * @param nextKey  the next key
   * @return unwatch stat
   * @throws WxErrorException the wx error exception
   */
  WxCpSchoolUnwatchStat getUnwatchStat(String livingId, String nextKey) throws WxErrorException;

  /**
   * 删除直播回放
   * 请求方式： POST（HTTPS）
   * 请求地址： https://qyapi.weixin.qq.com/cgi-bin/living/delete_replay_data?access_token=ACCESS_TOKEN
   *
   * @param livingId the living id
   * @return wx cp living result
   * @throws WxErrorException the wx error exception
   */
  WxCpLivingResult deleteReplayData(String livingId) throws WxErrorException;

}
