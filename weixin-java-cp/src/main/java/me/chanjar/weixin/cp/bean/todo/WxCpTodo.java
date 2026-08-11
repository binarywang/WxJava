package me.chanjar.weixin.cp.bean.todo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.experimental.Accessors;
import me.chanjar.weixin.common.bean.ToJson;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 待办信息bean.
 * <p>
 * 官方文档：
 * <a href="https://developer.work.weixin.qq.com/document/path/101524">获取待办详情</a>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a> created on  2026-08-11
 */
@Data
@Accessors(chain = true)
public class WxCpTodo implements Serializable, ToJson {
  private static final long serialVersionUID = -1L;

  /**
   * 待办ID
   */
  @SerializedName("todo_id")
  private String todoId;
  /**
   * 待办内容
   */
  @SerializedName("content")
  private String content;
  /**
   * 待办创建人ID
   */
  @SerializedName("creator")
  private String creator;
  /**
   * 待办状态。
   * 0 - 已完成
   * 1 - 进行中
   */
  @SerializedName("status")
  private Integer status;
  /**
   * 待办创建时间戳
   */
  @SerializedName("create_time")
  private Long createTime;
  /**
   * 待办参与人列表
   */
  @SerializedName("attendees")
  private List<Attendee> attendees;
  /**
   * 待办截止时间戳
   */
  @SerializedName("end_time")
  private Long endTime;
  /**
   * 提醒列表
   */
  @SerializedName("reminders")
  private List<Reminder> reminders;

  @Override
  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

  /**
   * 待办参与人.
   */
  @Data
  @Accessors(chain = true)
  public static class Attendee implements Serializable {
    private static final long serialVersionUID = -1L;

    /**
     * 待办参与人ID
     */
    @SerializedName("userid")
    private String userid;
    /**
     * 参与人的待办状态。
     * 0 - 完成
     * 1 - 进行中
     */
    @SerializedName("status")
    private Integer status;
  }

  /**
   * 待办提醒.
   */
  @Data
  @Accessors(chain = true)
  public static class Reminder implements Serializable {
    private static final long serialVersionUID = -1L;

    /**
     * 提醒时间戳
     */
    @SerializedName("remind_time")
    private Long remindTime;
  }
}
