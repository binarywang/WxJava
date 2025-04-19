package me.chanjar.weixin.cp.bean.oa.meetingroom;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 预定会议室的返回结果类
 *
 * @author <a href="https://github.com/llw5181">小梁</a>
 * @version 1.0  Create by 2024/10/28
 */
@Data
public class WxCpOaMeetingRoomBookResult extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = -4993287594652231098L;

  @Override
  public String toString() {
    return WxCpGsonBuilder.create().toJson(this);
  }

  public static WxCpOaMeetingRoomBookResult fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpOaMeetingRoomBookResult.class);
  }

  /**
   * 会议室的预定id
   */
  @SerializedName("booking_id")
  private String booking_id;
  /**
   * 会议关联日程的id
   */
  @SerializedName("schedule_id")
  private String schedule_id;
  /**
   * 通过会议预定会议室 和 通过日程预定会议室 接口返回
   * <br/>
   * 会议室冲突日期列表，为当天0点的时间戳；使用重复会议预定会议室，部分日期与会议室预定情况冲突时返回
   */
  @SerializedName("conflict_date")
  private List<Integer> conflict_date;

}
