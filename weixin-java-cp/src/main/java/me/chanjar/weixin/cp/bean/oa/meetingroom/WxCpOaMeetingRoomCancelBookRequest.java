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
 * 取消预定会议室请求类
 *
 * @author <a href="https://github.com/llw5181">小梁</a>
 * @version 1.0  Create by 2024/10/28
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxCpOaMeetingRoomCancelBookRequest implements Serializable, ToJson {
  private static final long serialVersionUID = 2825289798463742539L;
  /**
   * 会议室的预定id
   */
  @SerializedName("booking_id")
  private String booking_id;
  /**
   * 是否保留日程，0-同步删除 1-保留，仅对非重复日程有效
   */
  @SerializedName("keep_schedule")
  private Integer keep_schedule;
  /**
   * 对于重复日程，如果不填写此参数，表示取消所有重复预定；如果填写，则表示取消对应日期当天的会议室预定
   */
  @SerializedName("cancel_date")
  private Integer cancel_date;


  @Override
  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
