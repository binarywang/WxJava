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
 * 根据会议室预定ID查询预定详情请求类
 *
 * @author <a href="https://github.com/llw5181">小梁</a>
 * @version 1.0  Create by 2024/10/28
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxCpOaMeetingRoomBookingInfoByBookingIdRequest implements Serializable, ToJson {
  private static final long serialVersionUID = 2825289798463742533L;
  /**
   * 会议室id
   */
  @SerializedName("meetingroom_id")
  private Integer meetingroom_id;
  /**
   * 会议室的预定id
   */
  @SerializedName("booking_id")
  private String booking_id;


  @Override
  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
