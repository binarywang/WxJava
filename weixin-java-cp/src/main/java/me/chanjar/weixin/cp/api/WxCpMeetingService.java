package me.chanjar.weixin.cp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.oa.meeting.WxCpMeeting;
import me.chanjar.weixin.cp.bean.oa.meeting.WxCpMeetingUpdateResult;
import me.chanjar.weixin.cp.bean.oa.meeting.WxCpUserMeetingIdResult;

/**
 * 企业微信日程接口.
 * 企业和开发者通过会议接口可以便捷地预定及管理会议，用于小组周会、部门例会等场景。
 * 调用接口的应用自动成为会议创建者，也可指定成员作为会议管理员辅助管理。
 * 官方文档：https://developer.work.weixin.qq.com/document/path/93626
 *
 * @author <a href="https://github.com/wangmeng3486">wangmeng3486</a> created on  2023-01-31
 */
public interface WxCpMeetingService {
  /**
   * 创建预约会议
   * <p>
   * 该接口用于创建一个预约会议。
   * <p>
   * 请求方式： POST（HTTPS）
   * 请求地址： https://qyapi.weixin.qq.com/cgi-bin/meeting/create?access_token=ACCESS_TOKEN
   *
   * @param meeting the meeting
   * @return 会议ID string
   * @throws WxErrorException the wx error exception
   */
  String create(WxCpMeeting meeting) throws WxErrorException;

  /**
   * 修改预约会议
   * <p>
   * 该接口用于修改一个指定的预约会议。。
   * <p>
   * 请求方式： POST（HTTPS）
   * 请求地址： https://qyapi.weixin.qq.com/cgi-bin/meeting/update?access_token=ACCESS_TOKEN
   *
   * @param meeting the meeting
   * @return   wx cp meeting  update result
   * @throws WxErrorException the wx error exception
   */
  WxCpMeetingUpdateResult update(WxCpMeeting meeting) throws WxErrorException;


  /**
   * 取消预约会议
   * 该接口用于取消一个指定的预约会议。
   * <p>
   * 请求方式： POST（HTTPS）
   * 请求地址： https://qyapi.weixin.qq.com/cgi-bin/meeting/cancel?access_token=ACCESS_TOKEN
   *
   * @param meetingId 会议ID
   * @throws WxErrorException the wx error exception
   */
  void cancel(String meetingId) throws WxErrorException;

  /**
   * 获取会议详情
   * <p>
   * 该接口用于获取指定会议的详情内容。
   * <p>
   * 请求方式： POST（HTTPS）
   * 请求地址： https://qyapi.weixin.qq.com/cgi-bin/oa/meeting/get?access_token=ACCESS_TOKEN
   *
   * @param meetingId the meeting ids
   * @return the details
   * @throws WxErrorException the wx error exception
   */
  WxCpMeeting getDetail(String meetingId) throws WxErrorException;

  /**
   * 获取成员会议ID列表
   * 该接口用于获取指定成员指定时间内的会议ID列表。
   * <p>
   * 权限说明：
   * 只能拉取该应用创建的会议ID
   * 自建应用需要配置在“可调用接口的应用”列表
   * 第三方服务商创建应用的时候，需要开启“会议接口权限”
   * 代开发自建应用需要授权“会议接口权限”
   * <p>
   * 请求方式： POST（HTTPS）
   * 请求地址： https://qyapi.weixin.qq.com/cgi-bin/meeting/get_user_meetingid?access_token=ACCESS_TOKEN
   *
   * @param userId 企业成员的userid
   * @param cursor 上一次调用时返回的cursor，初次调用可以填"0"
   * @param limit 每次拉取的数据量，默认值和最大值都为100
   * @param beginTime 开始时间
   * @param endTime 结束时间，时间跨度不超过180天。如果begin_time和end_time都没填的话，默认end_time为当前时间
   * @return result of listUserMeetingIds
   * @throws WxErrorException the wx error exception
   */
  WxCpUserMeetingIdResult getUserMeetingIds(String userId, String cursor, Integer limit,
                                                   Long beginTime, Long endTime) throws WxErrorException;
}
