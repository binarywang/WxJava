package com.github.binarywang.wxpay.bean.marketing.busifavor;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 固定周期有效时间段
 * <pre>
 *   文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter9_2_1.shtml
 * </pre>
 *
 * @author yujam
 */
@Data
@NoArgsConstructor
public class AvailableWeek implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * <pre>
   * 字段名：可用星期数
   * 变量名：week_day
   * 是否必填：否
   * 类型：array[int]
   * 描述：
   *  0代表周日，1代表周一，以此类推
   *  当填写available_day_time时，week_day必填
   *  示例值：1, 2
   * </pre>
   */
  @SerializedName(value = "week_day")
  private Integer[] weekDay;


  /**
   * <pre>
   * 字段名：当天可用时间段
   * 变量名：available_day_time
   * 是否必填：否
   * 类型：array
   * 描述：
   *  可以填写多个时间段，最多不超过2个。
   * </pre>
   */
  @SerializedName(value = "available_day_time")
  private AvailableDayTimeItem[] availableDayTime;

  @Data
  @NoArgsConstructor
  public static class AvailableDayTimeItem implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * <pre>
     * 字段名：当天可用开始时间
     * 变量名：begin_time
     * 是否必填：否
     * 类型：int
     * 描述：
     *  当天可用开始时间，单位：秒，1代表当天0点0分1秒。
     *  示例值：3600
     * </pre>
     */
    @SerializedName(value = "begin_time")
    private Integer beginTime;

    /**
     * <pre>
     * 字段名：当天可用结束时间
     * 变量名：available_day_time
     * 是否必填：否
     * 类型：int
     * 描述：
     *  当天可用结束时间，单位：秒，86399代表当天23点59分59秒。
     *  示例值：86399
     * </pre>
     */
    @SerializedName(value = "end_time")
    private Integer endTime;
  }
}
