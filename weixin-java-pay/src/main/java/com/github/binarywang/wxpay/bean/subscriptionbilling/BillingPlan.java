package com.github.binarywang.wxpay.bean.subscriptionbilling;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 扣费计划信息
 * <pre>
 *   文档地址：https://pay.weixin.qq.com/doc/v3/merchant/4012161105
 * </pre>
 *
 * @author Binary Wang
 */
@Data
@NoArgsConstructor
public class BillingPlan implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * <pre>
   * 字段名：计划类型
   * 变量名：plan_type
   * 是否必填：是
   * 类型：string(32)
   * 描述：
   *  扣费计划类型
   *  MONTHLY：按月扣费
   *  WEEKLY：按周扣费
   *  DAILY：按日扣费
   *  YEARLY：按年扣费
   *  示例值：MONTHLY
   * </pre>
   */
  @SerializedName("plan_type")
  private String planType;

  /**
   * <pre>
   * 字段名：扣费周期
   * 变量名：period
   * 是否必填：是
   * 类型：int
   * 描述：
   *  扣费周期，配合plan_type使用
   *  例如：plan_type为MONTHLY，period为1，表示每1个月扣费一次
   *  示例值：1
   * </pre>
   */
  @SerializedName("period")
  private Integer period;

  /**
   * <pre>
   * 字段名：总扣费次数
   * 变量名：total_count
   * 是否必填：否
   * 类型：int
   * 描述：
   *  总扣费次数，不填表示无限次扣费
   *  示例值：12
   * </pre>
   */
  @SerializedName("total_count")
  private Integer totalCount;

  /**
   * <pre>
   * 字段名：已扣费次数
   * 变量名：executed_count
   * 是否必填：否
   * 类型：int
   * 描述：
   *  已扣费次数，查询时返回
   *  示例值：2
   * </pre>
   */
  @SerializedName("executed_count")
  private Integer executedCount;

  /**
   * <pre>
   * 字段名：计划开始时间
   * 变量名：start_time
   * 是否必填：否
   * 类型：string(32)
   * 描述：
   *  计划开始时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss+TIMEZONE
   *  示例值：2018-06-08T10:34:56+08:00
   * </pre>
   */
  @SerializedName("start_time")
  private String startTime;

  /**
   * <pre>
   * 字段名：计划结束时间
   * 变量名：end_time
   * 是否必填：否
   * 类型：string(32)
   * 描述：
   *  计划结束时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss+TIMEZONE
   *  示例值：2019-06-08T10:34:56+08:00
   * </pre>
   */
  @SerializedName("end_time")
  private String endTime;
}