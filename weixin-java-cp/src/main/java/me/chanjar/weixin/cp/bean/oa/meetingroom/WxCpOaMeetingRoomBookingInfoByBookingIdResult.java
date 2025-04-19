package me.chanjar.weixin.cp.bean.oa.meetingroom;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * 根据会议室预定ID查询预定详情返回结果类
 *
 * @author <a href="https://github.com/llw5181">小梁</a>
 * @version 1.0  Create by 2024/10/28
 */
@Data
public class WxCpOaMeetingRoomBookingInfoByBookingIdResult extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = -4993287594652231097L;

  @Override
  public String toString() {
    return WxCpGsonBuilder.create().toJson(this);
  }

  public static WxCpOaMeetingRoomBookingInfoByBookingIdResult fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpOaMeetingRoomBookingInfoByBookingIdResult.class);
  }

  /**
   * 会议室ID
   */
  @SerializedName("meetingroom_id")
  private Integer meetingroomId;
  /**
   * 该会议室查询时间段内的预定情况
   */
  @SerializedName("schedule")
  private Schedule schedule;


  @Data
  public static class Schedule {
    /**
     * 会议室的预定id
     */
    @SerializedName("booking_id")
    private String bookingId;
    /**
     * 会议关联日程的id，若会议室已取消预定（未保留日历），则schedule_id将无法再获取到日程详情
     */
    @SerializedName("schedule_id")
    private String scheduleId;
    /**
     * 开始时间的时间戳
     */
    @SerializedName("start_time")
    private Integer startTime;
    /**
     * 结束时间的时间戳
     */
    @SerializedName("end_time")
    private Integer endTime;
    /**
     * 预定人的userid
     */
    @SerializedName("booker")
    private String booker;
    /**
     * 会议室的预定状态，0：已预定 、2：申请中、3：审批中
     */
    @SerializedName("status")
    private Integer status;
  }

}
