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
 * 通过会议预定会议室
 *
 * @author <a href="https://github.com/llw5181">小梁</a>
 * @version 1.0  Create by 2024/10/28
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxCpOaMeetingRoomBookByMeetingRequest implements Serializable, ToJson {
  private static final long serialVersionUID = 2825289798463742531L;
  /**
   * 会议室Id
   */
  @SerializedName("meetingroom_id")
  private Integer meetingroomId;
  /**
   * 会议id，仅可使用同应用创建的会议
   */
  @SerializedName("meetingid")
  private String meetingid;
  /**
   * 预定人的userid
   */
  @SerializedName("booker")
  private String booker;


  @Override
  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
