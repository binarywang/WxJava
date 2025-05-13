package me.chanjar.weixin.cp.bean.oa;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 企业微信打卡日报数据
 */
@Data
public class WxCpCheckinDayData implements Serializable {

  private static final long serialVersionUID = 5950483201268226746L;

  /**
   * base_info 基础信息
   */
  @SerializedName("base_info")
  private BaseInfo baseInfo;

  /**
   * The type Base info.
   */
  @Data
  public static class BaseInfo implements Serializable {


    private static final long serialVersionUID = 3679745559788648438L;
    /**
     * date 日报日期 时间戳
     */
    @SerializedName("date")
    private Integer date;

    /**
     * record_type 记录类型：1-固定上下班；2-外出（此报表中不会出现外出打卡数据）；3-按班次上下班；4-自由签到；5-加班；7-无规则
     */
    @SerializedName("record_type")
    private Integer recordType;

    /**
     * name 打卡人员姓名
     */
    @SerializedName("name")
    private String name;

    /**
     * name_ex 打卡人员姓名
     */
    @SerializedName("name_ex")
    private String nameEx;

    /**
     * departs_name 打卡人员所在部门，会显示所有所在部门
     */
    @SerializedName("departs_name")
    private String departsName;

    /**
     * acctid 打卡人员帐号，即userid
     */
    @SerializedName("acctid")
    private String acctId;

    /**
     * rule_info 打卡人员所属规则信息
     */
    @SerializedName("rule_info")
    private RuleInfo ruleInfo;

    /**
     * The type Rule info.
     */
    @Data
    public class RuleInfo implements Serializable {
      private static final long serialVersionUID = -5512479811648914189L;
      /**
       * groupid 所属规则的id
       */
      @SerializedName("groupid")
      private Integer groupId;

      /**
       * groupname 打卡规则名
       */
      @SerializedName("groupname")
      private String groupName;

      /**
       * scheduleid 当日所属班次id，仅按班次上下班才有值，显示在打卡日报-班次列
       */
      @SerializedName("scheduleid")
      private Integer scheduleId;

      /**
       * schedulename 当日所属班次名称，仅按班次上下班才有值，显示在打卡日报-班次列
       */
      @SerializedName("schedulename")
      private String scheduleName;

      /**
       * checkintime 当日打卡时间，仅固定上下班规则有值，显示在打卡日报-班次列
       */
      @SerializedName("checkintime")
      private List<CheckinTime> checkinTime;

      /**
       * The type Checkin time.
       */
      @Data
      public class CheckinTime implements Serializable {
        private static final long serialVersionUID = 1582835435812966332L;
        /**
         * work_sec 上班时间，为距离0点的时间差
         */
        @SerializedName("work_sec")
        private Integer workSec;

        /**
         * off_work_sec 下班时间，为距离0点的时间差
         */
        @SerializedName("off_work_sec")
        private Integer offWorkSec;
      }
    }

    /**
     * day_type 日报类型：0-工作日日报；1-休息日日报
     */
    @SerializedName("day_type")
    private Integer dayType;
  }

  /**
   * summary_info 汇总信息
   */
  @SerializedName("summary_info")
  private SummaryInfo summaryInfo;

  /**
   * The type Summary info.
   */
  @Data
  public static class SummaryInfo implements Serializable {
    private static final long serialVersionUID = 3428576099259666595L;
    /**
     * checkin_count 当日打卡次数
     */
    @SerializedName("checkin_count")
    private Integer checkinCount;

    /**
     * regular_work_sec 当日实际工作时长，单位：秒
     */
    @SerializedName("regular_work_sec")
    private Integer regularWorkSec;

    /**
     * standard_work_sec 当日标准工作时长，单位：秒
     */
    @SerializedName("standard_work_sec")
    private Integer standardWorkSec;

    /**
     * earliest_time 当日最早打卡时间
     */
    @SerializedName("earliest_time")
    private Integer earliestTime;

    /**
     * lastest_time 当日最晚打卡时间
     */
    @SerializedName("lastest_time")
    private Integer lastestTime;
  }

  /**
   * holiday_infos 假勤相关信息
   */
  @SerializedName("holiday_infos")
  private List<HolidayInfos> holidayInfos;

  /**
   * The type Holiday infos.
   */
  @Data
  public static class HolidayInfos implements Serializable {
    private static final long serialVersionUID = -6671577072585561527L;
    /**
     * sp_number 假勤相关信息
     */
    @SerializedName("sp_number")
    private String spNumber;

    /**
     * sp_title 假勤信息摘要-标题信息
     */
    @SerializedName("sp_title")
    private SpTitle spTitle;

    /**
     * The type Sp title.
     */
    @Data
    public class SpTitle implements Serializable {
      private static final long serialVersionUID = 2148815417115384998L;
      /**
       * data 多种语言描述，目前只有中文一种
       */
      @SerializedName("data")
      private List<Data> data;

      /**
       * The type Data.
       */
      @lombok.Data
      public class Data implements Serializable {
        private static final long serialVersionUID = -1672692024530543180L;
        /**
         * text 假勤信息摘要-标题文本
         */
        @SerializedName("text")
        private String text;

        /**
         * lang 语言类型：”zh_CN”
         */
        @SerializedName("lang")
        private String lang;
      }
    }

    /**
     * sp_description 假勤信息摘要-描述信息
     */
    @SerializedName("sp_description")
    private SpDescription spDescription;

    /**
     * The type Sp description.
     */
    @Data
    public class SpDescription implements Serializable {

      private static final long serialVersionUID = 77680581771933449L;
      /**
       * data 多种语言描述，目前只有中文一种
       */
      @SerializedName("data")
      private List<Data> data;

      /**
       * The type Data.
       */
      @lombok.Data
      public class Data implements Serializable {
        private static final long serialVersionUID = 3555479101375365805L;
        /**
         * text 假勤信息摘要-标题文本
         */
        @SerializedName("text")
        private String text;

        /**
         * lang 语言类型：”zh_CN”
         */
        @SerializedName("lang")
        private String lang;
      }
    }
  }

  /**
   * exception_infos 校准状态信息
   */
  @SerializedName("exception_infos")
  private List<ExceptionInfos> exceptionInfos;

  /**
   * The type Exception infos.
   */
  @Data
  public static class ExceptionInfos implements Serializable {
    private static final long serialVersionUID = -5987438373762518299L;
    /**
     * exception 校准状态类型：1-迟到；2-早退；3-缺卡；4-旷工；5-地点异常；6-设备异常
     */
    @SerializedName("exception")
    private Integer exception;

    /**
     * count 当日此异常的次数
     */
    @SerializedName("count")
    private Integer count;

    /**
     * duration 当日此异常的时长（迟到/早退/旷工才有值）
     */
    @SerializedName("duration")
    private Integer duration;
  }

  /**
   * ot_info 加班信息
   */
  @SerializedName("ot_info")
  private OtInfo otInfo;

  /**
   * The type Ot info.
   */
  @Data
  public static class OtInfo implements Serializable {
    private static final long serialVersionUID = -6557759801572150175L;
    /**
     * ot_status 状态：0-无加班；1-正常；2-缺时长
     */
    @SerializedName("ot_status")
    private Integer otStatus;

    /**
     * ot_duration 加班时长
     */
    @SerializedName("ot_duration")
    private Integer otDuration;

    /**
     * exception_duration ot_status为2下，加班不足的时长
     */
    @SerializedName("exception_duration")
    private List<Integer> exceptionDuration;
  }

  /**
   * sp_items 假勤统计信息
   */
  @SerializedName("sp_items")
  private List<SpItem> spItems;

  /**
   * The type Sp item.
   */
  @Data
  public static class SpItem implements Serializable {
    private static final long serialVersionUID = 2423158264958352024L;
    /**
     * type 类型：1-请假；2-补卡；3-出差；4-外出；100-外勤
     */
    @SerializedName("type")
    private Integer type;

    /**
     * vacation_id 具体请假类型，当type为1请假时，具体的请假类型id，可通过审批相关接口获取假期详情
     */
    @SerializedName("vacation_id")
    private Integer vacationId;

    /**
     * count 当日假勤次数
     */
    @SerializedName("count")
    private Integer count;

    /**
     * duration 当日假勤时长秒数，时长单位为天直接除以86400即为天数，单位为小时直接除以3600即为小时数
     */
    @SerializedName("duration")
    private Integer duration;

    /**
     * time_type 时长单位：0-按天 1-按小时
     */
    @SerializedName("time_type")
    private Integer timeType;

    /**
     * name 统计项名称
     */
    @SerializedName("name")
    private String name;


  }

}
