package com.github.binarywang.wxpay.bean.subscriptionbilling;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 查询扣费记录请求参数
 * <pre>
 *   文档地址：https://pay.weixin.qq.com/doc/v3/merchant/4012161105
 * </pre>
 *
 * @author Binary Wang
 */
@Data
@NoArgsConstructor
public class SubscriptionTransactionQueryRequest implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * <pre>
   * 字段名：用户标识
   * 变量名：openid
   * 是否必填：否
   * 类型：string(128)
   * 描述：
   *  用户在直连商户appid下的唯一标识
   *  示例值：oUpF8uMuAJO_M2pxb1Q9zNjWeS6o
   * </pre>
   */
  @SerializedName("openid")
  private String openid;

  /**
   * <pre>
   * 字段名：开始时间
   * 变量名：begin_time
   * 是否必填：否
   * 类型：string(32)
   * 描述：
   *  查询开始时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss+TIMEZONE
   *  示例值：2018-06-08T10:34:56+08:00
   * </pre>
   */
  @SerializedName("begin_time")
  private String beginTime;

  /**
   * <pre>
   * 字段名：结束时间
   * 变量名：end_time
   * 是否必填：否
   * 类型：string(32)
   * 描述：
   *  查询结束时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss+TIMEZONE
   *  示例值：2018-06-08T10:34:56+08:00
   * </pre>
   */
  @SerializedName("end_time")
  private String endTime;

  /**
   * <pre>
   * 字段名：分页大小
   * 变量名：limit
   * 是否必填：否
   * 类型：int
   * 描述：
   *  分页大小，不超过50
   *  示例值：20
   * </pre>
   */
  @SerializedName("limit")
  private Integer limit;

  /**
   * <pre>
   * 字段名：分页偏移量
   * 变量名：offset
   * 是否必填：否
   * 类型：int
   * 描述：
   *  分页偏移量
   *  示例值：0
   * </pre>
   */
  @SerializedName("offset")
  private Integer offset;
}