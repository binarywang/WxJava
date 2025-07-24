package me.chanjar.weixin.cp.bean.oa;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.experimental.Accessors;
import me.chanjar.weixin.common.bean.ToJson;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 日程信息bean.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a> created on  2020-12-25
 */
@Data
@Accessors(chain = true)
public class WxCpOaSchedule implements Serializable, ToJson {
  private static final long serialVersionUID = -6821274247372646346L;
  /**
   * 日程id
   */
  @SerializedName("schedule_id")
  private String scheduleId;
  /**
   * 日程编号，是一个自增数字
   */
  @SerializedName("sequence")
  private Integer sequence;
  /**
   * 组织者。不多于64字节
   */
  @SerializedName("organizer")
  private String organizer;
  /**
   * 管理员userid列表
   */
  @SerializedName("admins")
  private List<String> admins;
  /**
   * 日程参与者列表。最多支持2000人
   */
  @SerializedName("attendees")
  private List<Attendee> attendees;
  /**
   * 日程标题。0 ~ 128 字符。不填会默认显示为“新建事件”
   */
  @SerializedName("summary")
  private String summary;
  /**
   * 日程描述。不多于512个字符
   */
  @SerializedName("description")
  private String description;
  /**
   * 提醒相关信息
   */
  @SerializedName("reminders")
  private Reminder reminders;
  /**
   * 日程地址。
   * 不多于128个字符
   */
  @SerializedName("location")
  private String location;
  /**
   * 日程开始时间，Unix时间戳
   */
  @SerializedName("start_time")
  private Long startTime;
  /**
   * 日程结束时间，Unix时间戳
   */
  @SerializedName("end_time")
  private Long endTime;
  /**
   * 日程状态。0-正常；1-已取消
   */
  @SerializedName("status")
  private Integer status;
  /**
   * 日程所属日历ID。该日历必须是access_token所对应应用所创建的日历。
   * 注意，这个日历必须是属于组织者(organizer)的日历；
   * 如果不填，那么插入到组织者的默认日历上。
   * 第三方应用必须指定cal_id
   * 不多于64字节
   */
  @SerializedName("cal_id")
  private String calId;
  /**
   * 是否全天日程，0-否；1-是
   */
  @SerializedName("is_whole_day")
  private Integer isWholeDay;

  @Override
  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

  /**
   * The type Attendee.
   */
  @Data
  @Accessors(chain = true)
  public static class Attendee implements Serializable {
    private static final long serialVersionUID = 5419000348428480645L;
    /**
     * 日程参与者ID，
     * 不多于64字节
     */
    @SerializedName("userid")
    private String userid;
    /**
     * 日程参与者的接受状态。
     * 0 - 未处理
     * 1 - 待定
     * 2 - 全部接受
     * 3 - 仅接受一次
     * 4 - 拒绝
     */
    @SerializedName("response_status")
    private Integer responseStatus;
  }

  /**
   * The type Reminder.
   */
  @Data
  @Accessors(chain = true)
  public static class Reminder implements Serializable {
    private static final long serialVersionUID = 5030527150838243356L;

    /**
     * 是否需要提醒。0-否；1-是
     */
    @SerializedName("is_remind")
    private Integer isRemind;
    /**
     * 是否重复日程。0-否；1-是
     */
    @SerializedName("is_repeat")
    private Integer isRepeat;
    /**
     * 日程开始（start_time）前多少秒提醒，当is_remind为1时有效。
     * 例如： 300表示日程开始前5分钟提醒。目前仅支持以下数值：
     * 0 - 事件开始时
     * 300 - 事件开始前5分钟
     * 900 - 事件开始前15分钟
     * 3600 - 事件开始前1小时
     * 86400 - 事件开始前1天
     * 注意：建议使用 remind_time_diffs 字段，该字段后续将会废弃。
     */
    @SerializedName("remind_before_event_secs")
    private Integer remindBeforeEventSecs;
    /**
     * 提醒时间与日程开始时间（start_time）的差值，当is_remind为1时有效。例如：-300表示日程开始前5分钟提醒。
     * 特殊情况：企业微信终端设置的“全天”类型的日程，由于start_time是0点时间戳，提醒如果设置了当天9点，则会出现正数32400。
     * <br/>
     * 取值范围：-604800 ~ 86399
     */
    @SerializedName("remind_time_diffs")
    private List<Integer> remindTimeDiffs;
    /**
     * 重复类型，当is_repeat为1时有效。目前支持如下类型：
     * 0 - 每日
     * 1 - 每周
     * 2 - 每月
     * 5 - 每年
     * 7 - 工作日
     */
    @SerializedName("repeat_type")
    private Integer repeatType;
    /**
     * 重复结束时刻，Unix时间戳。不填或填0表示一直重复
     */
    @SerializedName("repeat_until")
    private Long repeatUntil;
    /**
     * 是否自定义重复。0-否；1-是
     */
    @SerializedName("is_custom_repeat")
    private Integer isCustomRepeat;
    /**
     * 重复间隔
     * 仅当指定为自定义重复时有效
     * 该字段随repeat_type不同而含义不同
     * 例如：
     * repeat_interval指定为3，repeat_type指定为每周重复，那么每3周重复一次；
     * repeat_interval指定为3，repeat_type指定为每月重复，那么每3个月重复一次
     */
    @SerializedName("repeat_interval")
    private Integer repeatInterval;
    /**
     * 每周周几重复
     * 仅当指定为自定义重复且重复类型为每周时有效
     * 取值范围：1 ~ 7，分别表示周一至周日
     */
    @SerializedName("repeat_day_of_week")
    private List<Integer> repeatDayOfWeek;
    /**
     * 每月哪几天重复
     * 仅当指定为自定义重复且重复类型为每月时有效
     * 取值范围：1 ~ 31，分别表示1~31号
     */
    @SerializedName("repeat_day_of_month")
    private List<Integer> repeatDayOfMonth;
    /**
     * 时区。UTC偏移量表示(即偏离零时区的小时数)，东区为正数，西区为负数。
     * 例如：+8 表示北京时间东八区
     * 默认为北京时间东八区
     * 取值范围：-12 ~ +12
     */
    @SerializedName("timezone")
    private Integer timezone;
    /**
     * 重复日程不包含的日期列表。对重复日程修改/删除特定一天或多天，则原来的日程将会排除对应的日期。
     */
    @SerializedName("exclude_time_list")
    private List<ExcludeTime> excludeTimeList;

    @Data
    @Accessors(chain = true)
    public static class ExcludeTime implements Serializable {
      private static final long serialVersionUID = 5030527150838243359L;
      /**
       * 不包含的日期时间戳。
       */
      @SerializedName("start_time")
      private Long startTime;
    }
  }
}
