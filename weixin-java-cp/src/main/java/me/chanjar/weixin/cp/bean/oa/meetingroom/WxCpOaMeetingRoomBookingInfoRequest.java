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

/**
 * 查询会议室的预定信息的请求类
 *
 * @author  <a href="https://github.com/llw5181">小梁</a>
 * @version 1.0  Create by 2024/10/28
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxCpOaMeetingRoomBookingInfoRequest implements Serializable, ToJson {
  private static final long serialVersionUID = 2825289798463742534L;
  /**
   * 会议室Id
   */
  @SerializedName("meetingroom_id")
  private Integer meetingroomId;
  /**
   * 查询预定的起始时间，默认为当前时间
   */
  @SerializedName("start_time")
  private Integer startTime;
  /**
   * 查询预定的结束时间， 默认为明日0时
   */
  @SerializedName("end_time")
  private Integer endTime;
  /**
   * 会议室所在城市
   */
  @SerializedName("city")
  private String city;
  /**
   * 	会议室所在楼宇
   */
  @SerializedName("building")
  private String building;
  /**
   * 	会议室所在楼层
   */
  @SerializedName("floor")
  private String floor;

  @Override
  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
