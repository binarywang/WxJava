package me.chanjar.weixin.cp.bean.oa.meetingroom;


import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import me.chanjar.weixin.common.bean.ToJson;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 预定会议室的请求类
 *
 * @author <a href="https://github.com/llw5181">小梁</a>
 * @version 1.0  Create by 2024/10/28
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxCpOaMeetingRoomBookRequest implements Serializable, ToJson {
  private static final long serialVersionUID = 2825289798463742536L;
  /**
   * 会议室Id
   */
  @SerializedName("meetingroom_id")
  private Integer meetingroomId;
  /**
   * 预定开始时间
   */
  @SerializedName("start_time")
  private Integer startTime;
  /**
   * 预定结束时间
   */
  @SerializedName("end_time")
  private Integer endTime;
  /**
   * 会议主题
   */
  @SerializedName("subject")
  private String subject;
  /**
   * 预定人的userid
   */
  @SerializedName("booker")
  private String booker;
  /**
   * 参与人的userid列表
   */
  @SerializedName("attendees")
  private List<String> attendees;

  @Override
  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
