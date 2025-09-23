package me.chanjar.weixin.cp.bean.oa;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 企业微信企业所有打卡规则.
 *
 * @author Liuwm
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WxCpCropCheckinOption extends WxCpCheckinGroupBase implements Serializable {
  private static final long serialVersionUID = 1725954575430704232L;

  /**
   * 打卡人员信息
   */
  @SerializedName("range")
  private Range range;

  /**
   * 创建打卡规则时间，为unix时间戳
   */
  @SerializedName("create_time")
  private Long createTime;

  /**
   * 打卡人员白名单，即不需要打卡人员，需要有设置白名单才能查看
   */
  @SerializedName("white_users")
  private List<String> whiteUsers;

  /**
   * 打卡方式，0:手机；2:智慧考勤机；3:手机+智慧考勤机
   */
  @SerializedName("type")
  private Integer type;

  /**
   * 打卡方式，0:手机；2:智慧考勤机；3:手机+智慧考勤机
   */
  @SerializedName("reporterinfo")
  private ReporterInfo reporterInfo;

  /**
   * 加班信息，相关信息需要设置后才能显示
   */
  @SerializedName("ot_info")
  private OtInfo otInfo;

  /**
   * 加班信息V2，新版API返回的加班信息结构
   */
  @SerializedName("ot_info_v2")
  private OtInfoV2 otInfoV2;

  /**
   * 每月最多补卡次数，默认-1表示不限制
   */
  @SerializedName("allow_apply_bk_cnt")
  private Integer allowApplyBkCnt;

  /**
   * 范围外打卡处理方式，0-视为范围外异常，默认值；1-视为正常外勤；2:不允许范围外打卡
   */
  @SerializedName("option_out_range")
  private Integer optionOutRange;

  /**
   * 规则创建人userid
   */
  @SerializedName("create_userid")
  private String createUserid;

  /**
   * 人脸识别打卡开关，true为启用，false为不启用
   */
  @SerializedName("use_face_detect")
  private Boolean useFaceDetect;

  /**
   * 允许补卡时限，默认-1表示不限制。单位天
   */
  @SerializedName("allow_apply_bk_day_limit")
  private Integer allowApplyBkDayLimit;

  /**
   * 规则最近编辑人userid
   */
  @SerializedName("update_userid")
  private String updateUserid;


  /**
   * 自由签到，上班打卡后xx秒可打下班卡
   */
  @SerializedName("offwork_interval_time")
  private Integer offWorkIntervalTime;

  /**
   * The type Range.
   */
  @Data
  public static class Range implements Serializable {

    private static final long serialVersionUID = 8940086218556453088L;

    /**
     * 打卡人员中，单个打卡人员节点的userid
     */
    @SerializedName("party_id")
    private List<String> partyid;

    /**
     * 打卡人员中，部门节点的id
     */
    @SerializedName("userid")
    private List<String> userid;

    /**
     * 打卡人员中，标签节点的标签id
     */
    @SerializedName("tagid")
    private List<Integer> tagid;


  }

  /**
   * The type Reporter info.
   */
  @Data
  public static class ReporterInfo implements Serializable {
    private static final long serialVersionUID = 1132450350458936772L;
    /**
     * 汇报对象，每个汇报人用userid表示
     */
    @SerializedName("reporters")
    private List<Reporter> reporters;

    /**
     * 汇报对象更新时间
     */
    @SerializedName("updatetime")
    private long updateTime;
  }

  /**
   * The type Reporter.
   */
  @Data
  public static class Reporter implements Serializable {

    private static final long serialVersionUID = 4925417850482005397L;

    @SerializedName("userid")
    private String userid;
  }

  /**
   * The type Ot info.
   */
  @Data
  public static class OtInfo implements Serializable {

    private static final long serialVersionUID = 1610150484871066199L;

    /**
     * 加班类型
     * 0：以加班申请核算打卡记录（根据打卡记录和加班申请核算）,
     * 1：以打卡时间为准（根据打卡时间计算），
     * 2: 以加班申请审批为准（只根据加班申请计算）
     */
    @SerializedName("type")
    private Integer type;

    /**
     * 允许工作日加班，true为允许，false为不允许
     */
    @SerializedName("allow_ot_workingday")
    private Boolean allowOtWorkingDay;

    /**
     * 允许非工作日加班，true为允许，flase为不允许
     */
    @SerializedName("allow_ot_nonworkingday")
    private Boolean allowOtNonworkingDay;

    /**
     * 允许非工作日加班，true为允许，flase为不允许
     */
    @SerializedName("otcheckinfo")
    private OtCheckInfo otcheckinfo;

    /**
     * 更新时间
     */
    @SerializedName("uptime")
    private Long uptime;

    /**
     * 允许非工作日加班，true为允许，flase为不允许
     */
    @SerializedName("otapplyinfo")
    private OtApplyInfo otapplyinfo;
  }

  /**
   * The type Ot check info.
   */
  @Data
  public static class OtCheckInfo implements Serializable {

    private static final long serialVersionUID = -2363047492489556390L;

    /**
     * 允许工作日加班-加班开始时间：下班后xx秒开始计算加班，距离最晚下班时间的秒数，例如，1800（30分钟 乘以 60秒），默认值30分钟
     */
    @SerializedName("ot_workingday_time_start")
    private Integer otWorkingDayTimeStart;

    /**
     * 允许工作日加班-最短加班时长：不足xx秒视为未加班，单位秒，默认值30分钟
     */
    @SerializedName("ot_workingday_time_min")
    private Integer otWorkingDayTimeMin;

    /**
     * 允许工作日加班-最长加班时长：超过则视为加班xx秒，单位秒，默认值240分钟
     */
    @SerializedName("ot_workingday_time_max")
    private Integer otWorkingDayTimeMax;

    /**
     * 允许非工作日加班-最短加班时长：不足xx秒视为未加班，单位秒，默认值30分钟
     */
    @SerializedName("ot_nonworkingday_time_min")
    private Integer otNonworkingDayTimeMin;

    /**
     * 允许非工作日加班-最长加班时长：超过则视为加班xx秒 单位秒，默认值240分钟
     */
    @SerializedName("ot_nonworkingday_time_max")
    private Integer otNonworkingDayTimeMax;

    /**
     * 非工作日加班，跨天时间，距离当天00:00的秒数
     */
    @SerializedName("ot_nonworkingday_spanday_time")
    private Integer otNonworkingDaySpanDayTime;

    /**
     * 允许非工作日加班，true为允许，flase为不允许
     */
    @SerializedName("ot_workingday_restinfo")
    private OtWorkingDayRestInfo otWorkingdayRestinfo;

    /**
     * 允许非工作日加班，true为允许，flase为不允许
     */
    @SerializedName("ot_nonworkingday_restinfo")
    private OtNonworkingDayRestInfo otNonworkingdayRestinfo;
  }

  /**
   * The type Ot working day rest info.
   */
  @Data
  public static class OtWorkingDayRestInfo implements Serializable {

    private static final long serialVersionUID = -4011047369711928306L;

    /**
     * 工作日加班-休息扣除类型：0-不开启扣除；1-指定休息时间扣除；2-按加班时长扣除休息时间
     */
    @SerializedName("type")
    private Integer type;

    /**
     * 工作日加班-指定休息时间配置信息，当group.ot_info.otcheckinfo.ot_workingday_restinfo.type为1时有意义
     */
    @SerializedName("fix_time_rule")
    private FixTimeRule fixTimeRule;

    /**
     * 工作日加班-按加班时长扣除配置信息，当group.ot_info.otcheckinfo.ot_workingday_restinfo.type为2时有意义
     */
    @SerializedName("cal_ottime_rule")
    private CalOtTimeRule calOttimeRule;
  }

  /**
   * The type Fix time rule.
   */
  @Data
  public static class FixTimeRule implements Serializable {

    private static final long serialVersionUID = 5709478500196619664L;

    /**
     * 工作日加班-指定休息时间的开始时间， 距离当天00:00的秒数
     */
    @SerializedName("fix_time_begin_sec")
    private Integer fixTimeBeginSec;

    /**
     * 工作日加班-指定休息时间的结束时间， 距离当天00:00的秒数
     */
    @SerializedName("fix_time_end_sec")
    private Integer fixTimeEndSec;
  }

  /**
   * The type Cal ot time rule.
   */
  @Data
  public static class CalOtTimeRule implements Serializable {

    private static final long serialVersionUID = -2407839982631243413L;

    /**
     * 工作日加班-按加班时长扣除条件信息
     */
    @SerializedName("items")
    private List<Item> items;

  }

  /**
   * The type Item.
   */
  @Data
  public static class Item implements Serializable {

    private static final long serialVersionUID = 5235770378506228461L;

    /**
     * 加班满-时长（秒）
     */
    @SerializedName("ot_time")
    private Integer otTime;

    /**
     * 对应扣除-时长（秒）
     */
    @SerializedName("rest_time")
    private Integer restTime;
  }

  /**
   * The type Ot nonworking day rest info.
   */
  @Data
  public static class OtNonworkingDayRestInfo implements Serializable {

    private static final long serialVersionUID = 3773846077049838088L;

    /**
     * 非工作日加班-休息扣除类型：0-不开启扣除；1-指定休息时间扣除；2-按加班时长扣除休息时间
     */
    @SerializedName("type")
    private Integer type;

    /**
     * 非工作日加班-指定休息时间配置信息，当group.ot_info.otcheckinfo.ot_workingday_restinfo.type为1时有意义
     */
    @SerializedName("fix_time_rule")
    private FixTimeRule fixTimeRule;

    /**
     * 非工作日加班-按加班时长扣除配置信息，当group.ot_info.otcheckinfo.ot_workingday_restinfo.type为2时有意义
     */
    @SerializedName("cal_ottime_rule")
    private CalOtTimeRule calOttimeRule;
  }

  /**
   * The type Ot apply info.
   */
  @Data
  public static class OtApplyInfo implements Serializable {

    private static final long serialVersionUID = 961217471918884103L;

    /**
     * 允许工作日加班，true为允许，false为不允许
     */
    @SerializedName("allow_ot_workingday")
    private Boolean allowOtWorkingDay;

    /**
     * 允许非工作日加班，true为允许，flase为不允许
     */
    @SerializedName("allow_ot_nonworkingday")
    private Boolean allowOtNonworkingDay;

    /**
     * 更新时间
     */
    @SerializedName("uptime")
    private Long uptime;

    /**
     * 允许非工作日加班，true为允许，flase为不允许
     */
    @SerializedName("ot_workingday_restinfo")
    private OtWorkingDayRestInfo otWorkingdayRestinfo;

    /**
     * 允许非工作日加班，true为允许，flase为不允许
     */
    @SerializedName("ot_nonworkingday_restinfo")
    private OtNonworkingDayRestInfo otNonworkingdayRestinfo;

    /**
     * 非工作日加班，跨天时间，距离当天00:00的秒数
     */
    @SerializedName("ot_nonworkingday_spanday_time")
    private Integer otNonworkingDaySpanDayTime;

  }

  /**
   * 加班信息V2，新版API返回的加班信息结构
   */
  @Data
  public static class OtInfoV2 implements Serializable {

    private static final long serialVersionUID = 1610150484871066200L;

    /**
     * 工作日加班配置
     */
    @SerializedName("workdayconf")
    private WorkdayConf workdayConf;

    /**
     * 非工作日加班配置
     */
    @SerializedName("restdayconf")
    private RestdayConf restdayConf;

    /**
     * 节假日加班配置
     */
    @SerializedName("holidayconf")
    private HolidayConf holidayConf;

    /**
     * 工作日加班配置
     */
    @Data
    public static class WorkdayConf implements Serializable {
      private static final long serialVersionUID = 1610150484871066201L;

      /**
       * 是否允许工作日加班，true为允许，false为不允许
       */
      @SerializedName("allow_ot")
      private Boolean allowOt;

      /**
       * 加班类型
       * 0：以加班申请核算打卡记录（根据打卡记录和加班申请核算）,
       * 1：以打卡时间为准（根据打卡时间计算），
       * 2: 以加班申请审批为准（只根据加班申请计算）
       */
      @SerializedName("type")
      private Integer type;
    }

    /**
     * 非工作日加班配置
     */
    @Data
    public static class RestdayConf implements Serializable {
      private static final long serialVersionUID = 1610150484871066202L;

      /**
       * 是否允许非工作日加班，true为允许，false为不允许
       */
      @SerializedName("allow_ot")
      private Boolean allowOt;

      /**
       * 加班类型
       */
      @SerializedName("type")
      private Integer type;
    }

    /**
     * 节假日加班配置
     */
    @Data
    public static class HolidayConf implements Serializable {
      private static final long serialVersionUID = 1610150484871066203L;

      /**
       * 是否允许节假日加班，true为允许，false为不允许
       */
      @SerializedName("allow_ot")
      private Boolean allowOt;

      /**
       * 加班类型
       */
      @SerializedName("type")
      private Integer type;
    }
  }
}
