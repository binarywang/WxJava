package me.chanjar.weixin.cp.bean.oa;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 打卡规则基础信息
 *
 * @author zhongjun96
 * @since 2023/7/7
 */
@Data
public class WxCpCheckinGroupBase implements Serializable {

  private static final long serialVersionUID = -2763570465930237249L;


  /**
   * 打卡规则类型，1：固定时间上下班；2：按班次上下班；3：自由上下班
   */
  @SerializedName("grouptype")
  private Long groupType;

  /**
   * 打卡规则id
   */
  @SerializedName("groupid")
  private Long groupId;

  /**
   * 打卡规则名称
   */
  @SerializedName("groupname")
  private String groupName;

  /**
   * 打卡时间，当规则类型为排班时没有意义
   */
  @SerializedName("checkindate")
  private List<CheckinDate> checkinDate;

  /**
   * 特殊日期-必须打卡日期信息，timestamp表示具体时间
   */
  @SerializedName("spe_workdays")
  private List<SpeWorkday> speWorkdays;

  /**
   * 特殊日期-不用打卡日期信息， timestamp表示具体时间
   */
  @SerializedName("spe_offdays")
  private List<SpeOffDay> speOffDays;

  /**
   * 是否同步法定节假日，true为同步，false为不同步，当前排班不支持
   */
  @SerializedName("sync_holidays")
  private Boolean syncHolidays;

  /**
   * 是否打卡必须拍照，true为必须拍照，false为不必须拍照
   */
  @SerializedName("need_photo")
  private Boolean needPhoto;

  /**
   * 是否备注时允许上传本地图片，true为允许，false为不允许
   */
  @SerializedName("note_can_use_local_pic")
  private Boolean noteCanUseLocalPic;

  /**
   * 是否非工作日允许打卡,true为允许，false为不允许
   */
  @SerializedName("allow_checkin_offworkday")
  private Boolean allowCheckinOffWorkDay;

  /**
   * 是否允许提交补卡申请，true为允许，false为不允许
   */
  @SerializedName("allow_apply_offworkday")
  private Boolean allowApplyOffWorkDay;

  /**
   * 打卡地点-WiFi打卡信息
   */
  @SerializedName("wifimac_infos")
  private List<WifiMacInfo> wifiMacInfos;

  /**
   * 打卡地点-WiFi打卡信息
   */
  @SerializedName("loc_infos")
  private List<LocInfo> locInfos;


  /**
   * 排班信息，只有规则为按班次上下班打卡时才有该配置
   */
  @SerializedName("schedulelist")
  private List<Schedule> schedulelist;


  /**
   * The type Checkin date.
   */
  @Data
  public static class CheckinDate implements Serializable {
    private static final long serialVersionUID = -8560643656775167406L;
    /**
     * 工作日。若为固定时间上下班或自由上下班，则1到6分别表示星期一到星期六，0表示星期日
     */
    @SerializedName("workdays")
    private List<Integer> workdays;

    /**
     * 工作日上下班打卡时间信息
     */
    @SerializedName("checkintime")
    private List<CheckinTime> checkinTime;

    /**
     * 下班不需要打卡，true为下班不需要打卡，false为下班需要打卡
     */
    @SerializedName("noneed_offwork")
    private Boolean noneedOffwork;

    /**
     * 打卡时间限制（毫秒）
     */
    @SerializedName("limit_aheadtime")
    private Long limitAheadtime;

    /**
     * 弹性时间（毫秒）只有flex_on_duty_time，flex_off_duty_time不生效时（值为-1）才有意义
     */
    @SerializedName("flex_time")
    private Integer flexTime;

    /**
     * 允许迟到时间，单位ms
     */
    @SerializedName("flex_on_duty_time")
    private Integer flexOnDutyTime;

    /**
     * 允许早退时间，单位ms
     */
    @SerializedName("flex_off_duty_time")
    private Integer flexOffDutyTime;

    /**
     * 是否允许弹性时间
     */
    @SerializedName("allow_flex")
    private Boolean allowFlex;

    /**
     * 迟到规则
     */
    @SerializedName("late_rule")
    private LateRule lateRule;

    /**
     * 最早可打卡时间限制
     */
    @SerializedName("max_allow_arrive_early")
    private Integer maxAllowArriveEarly;

    /**
     * 最晚可打卡时间限制
     */
    @SerializedName("max_allow_arrive_late")
    private Integer maxAllowArriveLate;
  }

  /**
   * The type Checkin time.
   */
  @Data
  public static class CheckinTime implements Serializable {

    private static final long serialVersionUID = -5507709858609705279L;

    /**
     * 时段id，为班次中某一堆上下班时间组合的id
     */
    @SerializedName("time_id")
    private Integer timeId;

    /**
     * 上班时间，表示为距离当天0点的秒数。
     */
    @SerializedName("work_sec")
    private Integer workSec;

    /**
     * 下班时间，表示为距离当天0点的秒数。
     */
    @SerializedName("off_work_sec")
    private Integer offWorkSec;

    /**
     * 上班提醒时间，表示为距离当天0点的秒数。。
     */
    @SerializedName("remind_work_sec")
    private Integer remindWorkSec;

    /**
     * 下班提醒时间，表示为距离当天0点的秒数。
     */
    @SerializedName("remind_off_work_sec")
    private Integer remindOffWorkSec;

    /**
     * 休息开始时间，仅单时段支持，距离0点的秒
     */
    @SerializedName("rest_begin_time")
    private Integer restBeginTime;

    /**
     * 休息结束时间，仅单时段支持，距离0点的秒
     */
    @SerializedName("rest_end_time")
    private Integer restEndTime;

    /**
     * 是否允许休息
     */
    @SerializedName("allow_rest")
    private Boolean allowRest;

    /**
     * 最早可打卡时间，距离0点的秒数
     */
    @SerializedName("earliest_work_sec")
    private Integer earliestWorkSec;

    /**
     * 最晚可打卡时间，距离0点的秒数
     */
    @SerializedName("latest_work_sec")
    private Integer latestWorkSec;

    /**
     * 最早可下班打卡时间，距离0点的秒数
     */
    @SerializedName("earliest_off_work_sec")
    private Integer earliestOffWorkSec;

    /**
     * 最晚可下班打卡时间，距离0点的秒数
     */
    @SerializedName("latest_off_work_sec")
    private Integer latestOffWorkSec;

    /**
     * 上班无需打卡
     */
    @SerializedName("no_need_checkon")
    private Boolean noNeedCheckon;

    /**
     * 下班无需打卡
     */
    @SerializedName("no_need_checkoff")
    private Boolean noNeedCheckoff;
  }

  /**
   * The type Spe workday.
   */
  @Data
  public static class SpeWorkday implements Serializable {

    private static final long serialVersionUID = -4620710297258742666L;
    /**
     * 特殊日期-必须打卡日期时间戳
     */
    @SerializedName("timestamp")
    private Long timestamp;

    /**
     * 特殊日期备注
     */
    @SerializedName("notes")
    private String notes;

    /**
     * 特殊日期-必须打卡日期-上下班打卡时间
     */
    @SerializedName("checkintime")
    private List<CheckinTime> checkinTime;
  }

  /**
   * The type Spe off day.
   */
  @Data
  public static class SpeOffDay implements Serializable {
    private static final long serialVersionUID = 9214798931489490993L;
    /**
     * 特殊日期-不用打卡日期时间戳
     */
    @SerializedName("timestamp")
    private Long timestamp;

    /**
     * 特殊日期备注
     */
    @SerializedName("notes")
    private String notes;
  }

  /**
   * The type Wifi mac info.
   */
  @Data
  public static class WifiMacInfo implements Serializable {

    private static final long serialVersionUID = 6742659716677227089L;

    /**
     * WiFi打卡地点名称
     */
    @SerializedName("wifiname")
    private String wifiname;

    /**
     * WiFi打卡地点MAC地址/bssid
     */
    @SerializedName("wifimac")
    private String wifimac;
  }

  /**
   * The type Loc info.
   */
  @Data
  public static class LocInfo implements Serializable {

    private static final long serialVersionUID = -5591379191341944101L;
    /**
     * 位置打卡地点纬度，是实际纬度的1000000倍，与腾讯地图一致采用GCJ-02坐标系统标准
     */
    @SerializedName("lat")
    private Long lat;

    /**
     * 位置打卡地点经度，是实际经度的1000000倍，与腾讯地图一致采用GCJ-02坐标系统标准
     */
    @SerializedName("lng")
    private Long lng;

    /**
     * 位置打卡地点名称
     */
    @SerializedName("loc_title")
    private String locTitle;

    /**
     * 位置打卡地点详情
     */
    @SerializedName("loc_detail")
    private String locDetail;

    /**
     * 允许打卡范围（米）
     */
    @SerializedName("distance")
    private Integer distance;
  }


  /**
   * The type Schedule.
   */
  @Data
  public static class Schedule implements Serializable {

    private static final long serialVersionUID = -2461113644925307266L;

    /**
     * 班次id
     */
    @SerializedName("schedule_id")
    private Integer scheduleId;

    /**
     * 班次名称
     */
    @SerializedName("schedule_name")
    private String scheduleName;

    /**
     * 班次上下班时段信息
     */
    @SerializedName("time_section")
    private List<TimeSection> timeSection;

    /**
     * 允许提前打卡时间
     */
    @SerializedName("limit_aheadtime")
    private Long limitAheadTime;

    /**
     * 下班xx秒后不允许打下班卡
     */
    @SerializedName("limit_offtime")
    private Integer limitOffTime;

    /**
     * 下班不需要打卡
     */
    @SerializedName("noneed_offwork")
    private Boolean noNeedOffWork;

    /**
     * 是否允许弹性时间
     */
    @SerializedName("allow_flex")
    private Boolean allowFlex;

    /**
     * 允许迟到时间
     */
    @SerializedName("flex_on_duty_time")
    private Integer flexOnDutyTime;

    /**
     * 允许早退时间
     */
    @SerializedName("flex_off_duty_time")
    private Integer flexOffDutyTime;

    /**
     * 非工作日加班，跨天时间，距离当天00:00的秒数
     */
    @SerializedName("late_rule")
    private LateRule lateRule;

    /**
     * 最早可打卡时间限制
     */
    @SerializedName("max_allow_arrive_early")
    private Integer maxAllowArriveEarly;

    /**
     * 最晚可打卡时间限制，max_allow_arrive_early、max_allow_arrive_early与flex_on_duty_time、flex_off_duty_time互斥，当设置其中一组时，另一组数值置0
     */
    @SerializedName("max_allow_arrive_late")
    private Integer maxAllowArriveLate;

  }


  /**
   * The type Time section.
   */
  @Data
  public static class TimeSection implements Serializable {
    private static final long serialVersionUID = 7497252128339062724L;

    /**
     * 时段id，为班次中某一堆上下班时间组合的id
     */
    @SerializedName("time_id")
    private Integer timeId;

    /**
     * 上班时间，表示为距离当天0点的秒数。
     */
    @SerializedName("work_sec")
    private Integer workSec;

    /**
     * 下班时间，表示为距离当天0点的秒数。
     */
    @SerializedName("off_work_sec")
    private Integer offWorkSec;

    /**
     * 上班提醒时间，表示为距离当天0点的秒数。
     */
    @SerializedName("remind_work_sec")
    private Long remindWorkSec;

    /**
     * 下班提醒时间，表示为距离当天0点的秒数。
     */
    @SerializedName("remind_off_work_sec")
    private Integer remindOffWorkSec;

    /**
     * 休息开始时间，仅单时段支持，距离0点的秒
     */
    @SerializedName("rest_begin_time")
    private Integer restBeginTime;

    /**
     * 休息结束时间，仅单时段支持，距离0点的秒
     */
    @SerializedName("rest_end_time")
    private Integer restEndTime;

    /**
     * 是否允许休息
     */
    @SerializedName("allow_rest")
    private Boolean allowRest;
  }


  /**
   * The type Late rule.
   */
  @Data
  public static class LateRule implements Serializable {

    private static final long serialVersionUID = 5604969713950037053L;

    /**
     * 晚走的时间 距离最晚一个下班的时间单位：秒
     */
    @SerializedName("offwork_after_time")
    private Integer offWorkAfterTime;

    /**
     * 第二天第一个班次允许迟到的弹性时间单位：秒
     */
    @SerializedName("onwork_flex_time")
    private Integer onWorkFlexTime;

    /**
     * 是否允许超时下班（下班晚走次日晚到）允许时onwork_flex_time，offwork_after_time才有意义
     */
    @SerializedName("allow_offwork_after_time")
    private Boolean allowOffWorkAfterTime;

    /**
     * 迟到规则时间
     */
    @SerializedName("timerules")
    private List<TimeRule> timerules;
  }


  /**
   * The type Time rule.
   */
  @Data
  public static class TimeRule implements Serializable {

    private static final long serialVersionUID = 5680614050081598333L;

    /**
     * 晚走的时间 距离最晚一个下班的时间单位：秒
     */
    @SerializedName("offwork_after_time")
    private Integer offWorkAfterTime;

    /**
     * 第二天第一个班次允许迟到的弹性时间单位：秒
     */
    @SerializedName("onwork_flex_time")
    private Integer onWorkFlexTime;

  }
}
