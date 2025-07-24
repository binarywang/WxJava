package me.chanjar.weixin.cp.api.impl;

import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpMeetingService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.oa.meeting.WxCpMeeting;
import me.chanjar.weixin.cp.bean.oa.meeting.WxCpMeetingUpdateResult;
import me.chanjar.weixin.cp.bean.oa.meeting.WxCpUserMeetingIdResult;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.util.HashMap;
import java.util.Map;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.Oa.*;

/**
 * 企业微信日程接口.
 * 企业和开发者通过会议接口可以便捷地预定及管理会议，用于小组周会、部门例会等场景。
 * 调用接口的应用自动成为会议创建者，也可指定成员作为会议管理员辅助管理。
 * 官方文档：https://developer.work.weixin.qq.com/document/path/93626
 *
 * @author <a href="https://github.com/wangmeng3486">wangmeng3486</a> created on  2023-01-31
 */
@RequiredArgsConstructor
public class WxCpMeetingServiceImpl implements WxCpMeetingService {
  private final WxCpService cpService;

  @Override
  public String create(WxCpMeeting meeting) throws WxErrorException {
    return this.cpService.post(this.cpService.getWxCpConfigStorage().getApiUrl(MEETING_ADD),
      WxCpGsonBuilder.create().toJson(meeting));
  }

  @Override
  public WxCpMeetingUpdateResult update(WxCpMeeting meeting) throws WxErrorException {
    final String response = this.cpService.post(this.cpService.getWxCpConfigStorage().getApiUrl(MEETING_UPDATE),
      WxCpGsonBuilder.create().toJson(meeting));
    return WxCpGsonBuilder.create().fromJson(response, WxCpMeetingUpdateResult.class);
  }

  @Override
  public void cancel(String meetingId) throws WxErrorException {
    this.cpService.post(this.cpService.getWxCpConfigStorage().getApiUrl(MEETING_CANCEL),
      WxCpGsonBuilder.create().toJson(ImmutableMap.of("meetingid", meetingId)));
  }

  @Override
  public WxCpMeeting getDetail(String meetingId) throws WxErrorException {
    final String response = this.cpService.post(this.cpService.getWxCpConfigStorage().getApiUrl(MEETING_DETAIL),
      WxCpGsonBuilder.create().toJson(ImmutableMap.of("meetingid", meetingId)));
    return WxCpGsonBuilder.create().fromJson(response, WxCpMeeting.class);
  }

  @Override
  public WxCpUserMeetingIdResult getUserMeetingIds(String userId, String cursor, Integer limit,
                                                   Long beginTime, Long endTime) throws WxErrorException {
    final Map<String, Object> param = new HashMap<>(3);
    param.put("userid", userId);
    if (cursor != null) {
      param.put("cursor", cursor);
    }
    if (limit != null) {
      param.put("limit", limit);
    }
    if (beginTime != null) {
      param.put("begin_time", beginTime);
    }
    if (endTime != null) {
      param.put("end_time", endTime);
    }
    final String response = this.cpService.post(this.cpService.getWxCpConfigStorage().getApiUrl(GET_USER_MEETING_ID),
      WxCpGsonBuilder.create().toJson(param));
    return WxCpGsonBuilder.create().fromJson(response, WxCpUserMeetingIdResult.class);
  }
}
