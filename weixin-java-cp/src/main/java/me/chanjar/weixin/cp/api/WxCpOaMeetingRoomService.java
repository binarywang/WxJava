package me.chanjar.weixin.cp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.oa.meetingroom.*;

import java.util.List;

/**
 * 企业微信会议室接口.
 *
 * @author <a href="https://github.com/lm93129">lm93129</a> created on  2022年8月12日22:33:36
 */
public interface WxCpOaMeetingRoomService {
  /**
   * 创建会议室.
   * <pre>
   * 该接口用于通过应用在企业内创建一个会议室。
   * 请求方式： POST（HTTPS）
   * 请求地址： https://qyapi.weixin.qq.com/cgi-bin/oa/meetingroom/add?access_token=ACCESS_TOKEN
   *
   * 文档地址：https://developer.work.weixin.qq.com/document/path/93619
   * </pre>
   *
   * @param meetingRoom 会议室对象
   * @return 会议室ID string
   * @throws WxErrorException .
   */
  String addMeetingRoom(WxCpOaMeetingRoom meetingRoom) throws WxErrorException;

  /**
   * 查询会议室.
   * <pre>
   * 该接口用于通过应用在企业内查询会议室列表。
   * 请求方式： POST（HTTPS）
   * 请求地址： https://qyapi.weixin.qq.com/cgi-bin/oa/meetingroom/list?access_token=ACCESS_TOKEN
   *
   * 文档地址：https://developer.work.weixin.qq.com/document/path/93619
   * </pre>
   *
   * @param meetingRoomRequest 会议室查询对象
   * @return 会议室ID list
   * @throws WxErrorException .
   */
  List<WxCpOaMeetingRoom> listMeetingRoom(WxCpOaMeetingRoom meetingRoomRequest) throws WxErrorException;

  /**
   * 编辑会议室.
   * <pre>
   * 该接口用于通过应用在企业内编辑会议室。
   * 请求方式： POST（HTTPS）
   * 请求地址： https://qyapi.weixin.qq.com/cgi-bin/oa/meetingroom/edit?access_token=ACCESS_TOKEN
   *
   * 文档地址：https://developer.work.weixin.qq.com/document/path/93619
   * </pre>
   *
   * @param meetingRoom 会议室对象
   * @throws WxErrorException .
   */
  void editMeetingRoom(WxCpOaMeetingRoom meetingRoom) throws WxErrorException;

  /**
   * 删除会议室.
   * <pre>
   * 企业可通过此接口删除指定的会议室。
   * 请求方式： POST（HTTPS）
   * 请求地址： https://qyapi.weixin.qq.com/cgi-bin/oa/meetingroom/del?access_token=ACCESS_TOKEN
   *
   * 文档地址：https://developer.work.weixin.qq.com/document/path/93619
   * </pre>
   *
   * @param meetingRoomId 会议室ID
   * @throws WxErrorException .
   */
  void deleteMeetingRoom(Integer meetingRoomId) throws WxErrorException;

  /**
   * 查询会议室的预定信息.
   * <pre>
   * 企业可通过此接口查询相关会议室在指定时间段的预定情况，如是否已被预定，预定者的userid等信息，不支持跨天查询。
   * 请求方式： POST（HTTPS）
   * 请求地址： https://qyapi.weixin.qq.com/cgi-bin/oa/meetingroom/get_booking_info?access_token=ACCESS_TOKEN
   *
   * 文档地址：https://developer.work.weixin.qq.com/document/path/93620
   * </pre>
   *
   * @param wxCpOaMeetingRoomBookingInfoRequest 会议室预定信息查询对象
   * @throws WxErrorException .
   */
  WxCpOaMeetingRoomBookingInfoResult getMeetingRoomBookingInfo(WxCpOaMeetingRoomBookingInfoRequest wxCpOaMeetingRoomBookingInfoRequest) throws WxErrorException;

  /**
   * 预定会议室.
   * <pre>
   * 企业可通过此接口预定会议室并自动关联日程。
   * 请求方式： POST（HTTPS）
   * 请求地址： https://qyapi.weixin.qq.com/cgi-bin/oa/meetingroom/book?access_token=ACCESS_TOKEN
   *
   * 文档地址：https://developer.work.weixin.qq.com/document/path/93620
   * </pre>
   *
   * @param wxCpOaMeetingRoomBookRequest 会议室预定对象
   * @throws WxErrorException .
   */
  WxCpOaMeetingRoomBookResult bookingMeetingRoom(WxCpOaMeetingRoomBookRequest wxCpOaMeetingRoomBookRequest) throws WxErrorException;

  /**
   * 通过日程预定会议室.
   * <pre>
   * 企业可通过此接口为指定日程预定会议室，支持重复日程预定。
   * 请求方式： POST（HTTPS）
   * 请求地址： https://qyapi.weixin.qq.com/cgi-bin/oa/meetingroom/book_by_schedule?access_token=ACCESS_TOKEN
   *
   * 文档地址：https://developer.work.weixin.qq.com/document/path/93620
   * </pre>
   *
   * @param wxCpOaMeetingRoomBookByScheduleRequest 会议室预定对象
   * @throws WxErrorException .
   */
  WxCpOaMeetingRoomBookResult bookingMeetingRoomBySchedule(WxCpOaMeetingRoomBookByScheduleRequest wxCpOaMeetingRoomBookByScheduleRequest) throws WxErrorException;

  /**
   * 通过会议预定会议室.
   * <pre>
   * 企业可通过此接口为指定会议预定会议室，支持重复会议预定。
   * 请求方式： POST（HTTPS）
   * 请求地址： https://qyapi.weixin.qq.com/cgi-bin/oa/meetingroom/book_by_meeting?access_token=ACCESS_TOKEN
   *
   * 文档地址：https://developer.work.weixin.qq.com/document/path/93620
   * </pre>
   *
   * @param wxCpOaMeetingRoomBookByMeetingRequest 会议室预定对象
   * @throws WxErrorException .
   */
  WxCpOaMeetingRoomBookResult bookingMeetingRoomByMeeting(WxCpOaMeetingRoomBookByMeetingRequest wxCpOaMeetingRoomBookByMeetingRequest) throws WxErrorException;


  /**
   * 取消预定会议室.
   * <pre>
   * 企业可通过此接口取消会议室的预定
   * 请求方式： POST（HTTPS）
   * 请求地址： https://qyapi.weixin.qq.com/cgi-bin/oa/meetingroom/cancel_book?access_token=ACCESS_TOKEN
   *
   * 文档地址：https://developer.work.weixin.qq.com/document/path/93620
   * </pre>
   *
   * @param wxCpOaMeetingRoomCancelBookRequest 取消预定会议室对象
   * @throws WxErrorException .
   */
   void cancelBookMeetingRoom(WxCpOaMeetingRoomCancelBookRequest wxCpOaMeetingRoomCancelBookRequest) throws WxErrorException;


   /**
   * 根据会议室预定ID查询预定详情.
   * <pre>
   * 企业可通过此接口根据预定id查询相关会议室的预定情况
   * 请求方式： POST（HTTPS）
   * 请求地址： https://qyapi.weixin.qq.com/cgi-bin/oa/meetingroom/bookinfo/get?access_token=ACCESS_TOKEN
   *
   * 文档地址：https://developer.work.weixin.qq.com/document/path/93620
   * </pre>
   *
   * @param wxCpOaMeetingRoomBookingInfoByBookingIdRequest 根据会议室预定ID查询预定详情对象
   * @throws WxErrorException .
   */
   WxCpOaMeetingRoomBookingInfoByBookingIdResult getBookingInfoByBookingId(WxCpOaMeetingRoomBookingInfoByBookingIdRequest wxCpOaMeetingRoomBookingInfoByBookingIdRequest) throws WxErrorException;

}
