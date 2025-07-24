package me.chanjar.weixin.cp.bean.oa.meeting;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.experimental.Accessors;
import me.chanjar.weixin.common.bean.ToJson;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 会议信息bean.
 *
 * @author <a href="https://github.com/wangmeng3486">wangmeng3486</a> created on  2023-02-02
 */
@Data
@Accessors(chain = true)
public class WxCpMeeting implements Serializable, ToJson {
  private static final long serialVersionUID = 957588409099876012L;

  /**
   * 会议id
   */
  @SerializedName("meetingid")
  private String meetingId;
  /**
   * 会议管理员userid
   */
  @SerializedName("admin_userid")
  private String adminUserId;
  /**
   * 会议的标题，最多支持40个字节或者20个utf8字符
   */
  @SerializedName("title")
  private String title;

  /**
   * 会议开始时间的unix时间戳。需大于当前时间
   */
  @SerializedName("meeting_start")
  private Long meetingStart;

  /**
   * 会议持续时间单位秒，最小300秒
   */
  @SerializedName("meeting_duration")
  private Integer meetingDuration;

  /**
   * 会议的描述，最多支持500个字节或者utf8字符
   */
  @SerializedName("description")
  private String description;

  /**
   * 会议地点,最多128个字符
   */
  @SerializedName("location")
  private String location;

  /**
   * 授权方安装的应用agentid。仅旧的第三方多应用套件需要填此参数
   */
  @SerializedName("agentid")
  private Integer agentId;

  /**
   * 发起人所在部门
   */
  @SerializedName("main_department")
  private Integer mainDepartment;

  /**
   * 会议的状态。
   * 1：待开始
   * 2：会议中
   * 3：已结束
   * 4：已取消
   * 5：已过期
   */
  @SerializedName("status")
  public Integer status;

  /**
   * 会议类型。
   * 0：一次性会议
   * 1：周期性会议
   * 2：微信专属会议
   * 3：Rooms 投屏会议
   * 5：个人会议号会议
   * 6：网络研讨会
   */
  @SerializedName("meeting_type")
  private Integer meetingType;


  /**
   * 参与会议的成员。会议人数上限，以指定的「管理员」可预约的人数上限来校验，普通企业与会人员最多100；
   * 付费企业根据企业选购的在线会议室容量。任何userid不合法或者不在应用可见范围，直接报错。
   */
  @SerializedName("attendees")
  private Attendees attendees;


  /**
   * 会议所属日历ID。该日历必须是access_token所对应应用所创建的日历。
   * 注意，若参与人在日历分享范围内，则插入到该日历（同时会插入会议参与人的默认日历），若不在分享范围内，否则仅插入到参与者默认日历；
   * 如果不填，那么插入到参与者的默认日历上。
   * 第三方应用必须指定cal_id
   * 不多于64字节
   */
  @SerializedName("cal_id")
  private String calId;
  /**
   * 会议配置
   */
  @SerializedName("settings")
  private Setting settings;

  /**
   * 重复会议相关配置
   */
  @SerializedName("reminders")
  private Reminder reminders;

  @SerializedName("meeting_code")
  private String meetingCode;

  @SerializedName("meeting_link")
  private String meetingLink;

  @Override
  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

  /**
   * The type Attendee.
   */
  @Data
  @Accessors(chain = true)
  public static class Attendees implements Serializable {
    private static final long serialVersionUID = 5419000348428480645L;
    /**
     * 会议参与者ID，
     * 不多于64字节
     */
    @SerializedName("userid")
    private List<String> userId;

    @SerializedName("member")
    private List<Member> member;
    @SerializedName("tmp_external_user")
    private List<TmpExternalUser> tmpExternalUser;

    /**
     * 企业内部成员
     */
    @Data
    @Accessors(chain = true)
    public static class Member implements Serializable {
      private static final long serialVersionUID = 1001531041411551854L;
      /**
       * 企业内部成员的userid
       */
      @SerializedName("userid")
      private String userid;

      /**
       * 与会状态。1为已参与，2为未参与
       */
      @SerializedName("status")
      private Integer status;
      /**
       * 参会人首次加入会议时间的unix时间戳
       */
      @SerializedName("first_join_time")
      private Long firstJoinTime;
      /**
       * 参会人最后一次离开会议时间的unix时间戳
       */
      @SerializedName("last_quit_time")
      private Long lastQuitTime;
      /**
       * 参会人累计参会时长，单位为秒
       */
      @SerializedName("cumulative_time")
      private Long cumulativeTime;

    }

    /**
     * 会中参会的外部联系人
     */
    @Data
    @Accessors(chain = true)
    public static class TmpExternalUser implements Serializable {
      private static final long serialVersionUID = -1411758297144496040L;
      /**
       * 会中入会的外部用户临时id。同一个用户在不同会议中返回的该id不一致。
       */
      @SerializedName("tmp_external_userid")
      private String tmpExternalUserid;
      /**
       * 参会人首次加入会议时间的unix时间戳
       */
      @SerializedName("first_join_time")
      private Long firstJoinTime;
      /**
       * 参会人最后一次离开会议时间的unix时间戳
       */
      @SerializedName("last_quit_time")
      private Long lastQuitTime;

      /**
       * 参会人入会次数
       */
      @SerializedName("total_join_count")
      private Integer totalJoinCount;

      /**
       * 参会人累计参会时长，单位为秒
       */
      @SerializedName("cumulative_time")
      private Integer cumulativeTime;
    }

  }


  /**
   * The type Reminder.
   */
  @Data
  @Accessors(chain = true)
  public static class Reminder implements Serializable {
    private static final long serialVersionUID = -4097232428444045131L;
    /**
     * 是否是周期性会议，1：周期性会议 0：非周期性会议。默认为0
     */
    @SerializedName("is_repeat")
    private Integer isRepeat;
    /**
     * 周期性会议重复类型，0.每天；1.每周；2.每月；7.每个工作日。默认为0。周期性会议该字段才生效
     */
    @SerializedName("repeat_type")
    private Integer repeatType;

    /**
     * 重复结束时刻。周期性会议该字段才生效。若会议结束时间超出最大结束时间或者未设置，则默认设置为最大结束时间。
     * 每天\每个工作日\每周 最多重复200次会议；
     * 每两周\每月最多重复50次会议
     */
    @SerializedName("repeat_until")
    private Long repeatUntil;

    /**
     * 重复间隔
     * 仅当指定为自定义重复时有效
     * 目前仅当repeat_type为2时，即周期为周时，支持设置该字段，且值不能大于2。
     */
    @SerializedName("repeat_interval")
    private Integer repeatInterval;

    /**
     * 指定会议开始前多久提醒用户，相对于meeting_start前的秒数，默认不提醒。
     * 目前仅支持0：会议开始时提醒；300:5分钟前提醒；900:15分钟前提醒；3600:一小时前提醒；86400一天前提醒。
     * 若指定了非支持的值，则表现为会议开始时提醒
     */
    @SerializedName("remind_before")
    private List<Integer> remindBefore;
  }


  /**
   * The Settings.
   */
  @Data
  @Accessors(chain = true)
  public static class Setting implements Serializable {
    private static final long serialVersionUID = 5030527150838243356L;

    /**
     * 入会密码，仅可输入4-6位纯数字
     */
    @SerializedName("password")
    private String password;
    /**
     * 是否开启等候室。true:开启等候室；false:不开启等候室；默认不开
     */
    @SerializedName("enable_waiting_room")
    private boolean enableWaitingRoom;
    /**
     * 是否允许成员在主持人进会前加入。true:允许；false:不允许。默认允许
     */
    @SerializedName("allow_enter_before_host")
    private boolean allowEnterBeforeHost;
    /**
     * 会议开始时来电提醒方式，1.不提醒 2.仅提醒主持人 3.提醒所有成员入 4.指定部分人响铃。默认仅提醒主持人
     */
    @SerializedName("remind_scope")
    private Integer remindScope;
    /**
     * 成员入会时静音；1:开启；0:关闭；2:超过6人后自动开启静音。默认超过6人自动开启静音
     */
    @SerializedName("enable_enter_mute")
    private Integer enableEnterMute;

    /**
     * true:所有成员可入会；false:仅企业内部成员可入会 。默认所有成员可入会
     */
    @SerializedName("allow_external_user")
    private boolean allowExternalUser;

    /**
     * 是否开启屏幕水印，true:开启；false:不开启。默认不开启
     */
    @SerializedName("enable_screen_watermark")
    private boolean enableScreenWatermark;

    /**
     * 会议主持人人列表，主持人员最多10个。若包含ceaater_userid,会自动过滤。任何userid不合法，直接报错。
     */
    @SerializedName("hosts")
    private Attendees hosts;

    /**
     * 指定响铃的用户列表。如果remid_scope为4，但是ring_users为空，则全部成员均不响铃。
     */
    @SerializedName("ring_users")
    private Attendees ringUsers;
  }
}
