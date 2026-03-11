package com.github.binarywang.wxpay.bean.marketing.busifavor;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 券生效时间
 * <pre>
 *   文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter9_2_1.shtml
 * </pre>
 *
 * @author yujam
 */
@Data
@NoArgsConstructor
public class CouponAvailableTime implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * <pre>
   * 字段名：开始时间
   * 变量名：available_begin_time
   * 是否必填：是
   * 类型：string[1,32]
   * 描述：
   *  批次开始时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss表示时分秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35+08:00表示，北京时间2015年5月20日 13点29分35秒。
   *  注意：开始时间设置有效期最长为1年。
   *  示例值：2015-05-20T13:29:35+08:00
   * </pre>
   */
  @SerializedName(value = "available_begin_time")
  private String availableBeginTime;

  /**
   * <pre>
   * 字段名：结束时间
   * 变量名：available_end_time
   * 是否必填：是
   * 类型：string[1,32]
   * 描述：
   *  批次结束时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss表示时分秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35+08:00表示，北京时间2015年5月20日 13点29分35秒。
   *  注意：结束时间设置有效期最长为1年。
   *  示例值：2015-05-20T13:29:35+08:00
   * </pre>
   */
  @SerializedName(value = "available_end_time")
  private String availableEndTime;

  /**
   * <pre>
   * 字段名：生效后N天内有效
   * 变量名：available_day_after_receive
   * 是否必填：否
   * 类型：int
   * 描述：
   *  日期区间内，券生效后x天内有效。例如生效当天内有效填1，生效后2天内有效填2，以此类推……注意，用户在有效期开始前领取商家券，则从有效期第1天开始计算天数，用户在有效期内领取商家券，则从领取当天开始计算天数，无论用户何时领取商家券，商家券在活动有效期结束后均不可用。可配合wait_days_after_receive一同填写，也可单独填写。单独填写时，有效期内领券后立即生效，生效后x天内有效。
   *  示例值：3
   * </pre>
   */
  @SerializedName(value = "available_day_after_receive")
  private Integer availableDayAfterReceive;

  /**
   * <pre>
   * 字段名：固定周期有效时间段
   * 变量名：available_week
   * 是否必填：否
   * 类型：object
   * 描述：
   *  可以设置多个星期下的多个可用时间段，比如每周二10点到18点，用户自定义字段。
   * </pre>
   */
  @SerializedName(value = "available_week")
  private AvailableWeek availableWeek;

  /**
   * <pre>
   * 字段名：无规律的有效时间段
   * 变量名：irregulary_avaliable_time
   * 是否必填：否
   * 类型：array
   * 描述：
   *  无规律的有效时间，多个无规律时间段，用户自定义字段。
   * </pre>
   */
  @SerializedName(value = "irregulary_avaliable_time")
  private List<IrregularyAvaliableTime> irregularyAvaliableTime;

  /**
   * <pre>
   * 字段名：领取后N天开始生效
   * 变量名：wait_days_after_receive
   * 是否必填：否
   * 类型：int
   * 描述：
   *  日期区间内，用户领券后需等待x天开始生效。例如领券后当天开始生效则无需填写，领券后第2天开始生效填1，以此类推……用户在有效期开始前领取商家券，则从有效期第1天开始计算天数，用户在有效期内领取商家券，则从领取当天开始计算天数。无论用户何时领取商家券，商家券在活动有效期结束后均不可用。需配合available_day_after_receive一同填写，不可单独填写。
   *  示例值：7
   * </pre>
   */
  @SerializedName(value = "wait_days_after_receive")
  private Integer waitDaysAfterReceive;


}
