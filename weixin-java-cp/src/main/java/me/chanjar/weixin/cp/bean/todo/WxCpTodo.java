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
   * 2 - 已删除
   */
  @SerializedName("todo_status")
  private Integer status;
  /**
   * 待办创建时间。
   * <p>
   * 接口可能返回时间戳或日期字符串（如 YYYY-MM-DD HH:mm:ss），统一建模为 String 以兼容两种格式。
   */
  @SerializedName("create_time")
  private String createTime;
  /**
   * 待办参与人列表（GET 响应结构）.
   * <p>
   * 注意：UPDATE 请求体使用的是顶层 attendees[].userid/status 结构，
   * 与本字段（follower_list.followers[].follower_id/follower_status）不同，
   * 这是企业微信待办接口 GET 与 UPDATE 的字段差异。
   */
  @SerializedName("follower_list")
  private FollowerList followerList;
  /**
   * 待办截止时间。
   * <p>
   * 接口可能返回时间戳或日期字符串（如 YYYY-MM-DD HH:mm:ss），统一建模为 String 以兼容两种格式。
   */
  @SerializedName("end_time")
  private String endTime;
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
   * 待办参与人列表（GET 响应中的 follower_list 节点）.
   */
  @Data
  @Accessors(chain = true)
  public static class FollowerList implements Serializable {
    private static final long serialVersionUID = -1L;

    /**
     * 参与人列表
     */
    @SerializedName("followers")
    private List<Follower> followers;
  }

  /**
   * 待办参与人（GET 响应中的 follower 结构）.
   */
  @Data
  @Accessors(chain = true)
  public static class Follower implements Serializable {
    private static final long serialVersionUID = -1L;

    /**
     * 参与人ID
     */
    @SerializedName("follower_id")
    private String followerId;
    /**
     * 参与人的待办状态。
     * 0 - 完成
     * 1 - 进行中
     */
    @SerializedName("follower_status")
    private Integer followerStatus;
  }

  /**
   * 待办参与人（UPDATE 请求体中的 attendees 元素结构）.
   * <p>
   * 仅用于 {@code WxCpTodoService.update()} 入参序列化，
   * 字段名为 userid/status，与 GET 响应的 Follower（follower_id/follower_status）不同。
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
     * 提醒时间。
     * <p>
     * 接口可能返回时间戳或日期字符串（如 YYYY-MM-DD HH:mm:ss），统一建模为 String 以兼容两种格式。
     */
    @SerializedName("remind_time")
    private String remindTime;
  }
}
