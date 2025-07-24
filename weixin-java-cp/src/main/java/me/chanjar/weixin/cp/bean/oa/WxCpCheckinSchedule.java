package me.chanjar.weixin.cp.bean.oa;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


/**
 * 打卡人员排班信息
 */
@Data
public class WxCpCheckinSchedule implements Serializable {

  private static final long serialVersionUID = 5515056962298169806L;

  /**
   * userid 打卡人员userid
   */
  @SerializedName("userid")
  private String userid;

  /**
   * yearmonth 排班表月份，格式为年月，如202011
   */
  @SerializedName("yearmonth")
  private Integer yearmonth;

  /**
   * groupid 打卡规则id
   */
  @SerializedName("groupid")
  private Integer groupid;

  /**
   * groupname 打卡规则名
   */
  @SerializedName("groupname")
  private String groupName;

  /**
   * schedule 个人排班信息
   */
  @SerializedName("schedule")
  private UserSchedule schedule;

  /**
   * The type User schedule.
   */
  @Data
  public static class UserSchedule implements Serializable {
    private static final long serialVersionUID = 9138985222324576857L;
    /**
     * scheduleList 个人排班表信息
     */
    @SerializedName("scheduleList")
    private List<Schedule> scheduleList;

    /**
     * The type Schedule.
     */
    @Data
    public class Schedule implements Serializable {

      private static final long serialVersionUID = 8344153237512495728L;

      /**
       * day 排班日期，为表示当月第几天的数字
       */
      @SerializedName("day")
      private Integer day;

      /**
       * schedule_info 排班日期，为表示当月第几天的数字
       */
      @SerializedName("schedule_info")
      private ScheduleInfo scheduleInfo;

      /**
       * The type Schedule info.
       */
      @Data
      public class ScheduleInfo implements Serializable {
        private static final long serialVersionUID = 1317096341116256963L;
        /**
         * schedule_id 当日安排班次id，班次id也可在打卡规则中查询获得
         */
        @SerializedName("schedule_id")
        private Integer scheduleId;

        /**
         * schedule_name 排班日期，为表示当月第几天的数字
         */
        @SerializedName("schedule_name")
        private String scheduleName;

        /**
         * time_section 排班日期，为表示当月第几天的数字
         */
        @SerializedName("time_section")
        private List<TimeSection> timeSection;


        /**
         * The type Time section.
         */
        @Data
        public class TimeSection implements Serializable {
          private static final long serialVersionUID = -3447467962751285748L;
          /**
           * id 时段id，为班次中某一堆上下班时间组合的id
           */
          @SerializedName("id")
          private Integer id;

          /**
           * work_sec 上班时间。距当天00:00的秒数
           */
          @SerializedName("work_sec")
          private Integer workSec;

          /**
           * off_work_sec 下班时间。距当天00:00的秒数
           */
          @SerializedName("off_work_sec")
          private Integer offWorkSec;

          /**
           * remind_work_sec 上班提醒时间。距当天00:00的秒数
           */
          @SerializedName("remind_work_sec")
          private Integer remindWorkSec;

          /**
           * remind_off_work_sec 下班提醒时间。距当天00:00的秒数
           */
          @SerializedName("remind_off_work_sec")
          private Integer remindOffWorkSec;
        }
      }
    }


  }
}
