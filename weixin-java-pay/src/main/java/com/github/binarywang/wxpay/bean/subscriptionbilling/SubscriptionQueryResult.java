package com.github.binarywang.wxpay.bean.subscriptionbilling;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 预约扣费查询结果
 * <pre>
 *   文档地址：https://pay.weixin.qq.com/doc/v3/merchant/4012161105
 * </pre>
 *
 * @author Binary Wang
 */
@Data
@NoArgsConstructor
public class SubscriptionQueryResult implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * <pre>
   * 字段名：预约扣费ID
   * 变量名：subscription_id
   * 是否必填：是
   * 类型：string(64)
   * 描述：
   *  微信支付预约扣费ID
   *  示例值：1217752501201407033233368018
   * </pre>
   */
  @SerializedName("subscription_id")
  private String subscriptionId;

  /**
   * <pre>
   * 字段名：商户订单号
   * 变量名：out_trade_no
   * 是否必填：是
   * 类型：string(32)
   * 描述：
   *  商户系统内部订单号
   *  示例值：1217752501201407033233368018
   * </pre>
   */
  @SerializedName("out_trade_no")
  private String outTradeNo;

  /**
   * <pre>
   * 字段名：用户标识
   * 变量名：openid
   * 是否必填：是
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
   * 字段名：订单描述
   * 变量名：description
   * 是否必填：是
   * 类型：string(127)
   * 描述：
   *  订单描述
   *  示例值：腾讯充值中心-QQ会员充值
   * </pre>
   */
  @SerializedName("description")
  private String description;

  /**
   * <pre>
   * 字段名：预约状态
   * 变量名：status
   * 是否必填：是
   * 类型：string(32)
   * 描述：
   *  预约状态
   *  SCHEDULED：已预约
   *  CANCELLED：已取消
   *  EXECUTED：已执行
   *  FAILED：执行失败
   *  示例值：SCHEDULED
   * </pre>
   */
  @SerializedName("status")
  private String status;

  /**
   * <pre>
   * 字段名：预约扣费时间
   * 变量名：schedule_time
   * 是否必填：是
   * 类型：string(32)
   * 描述：
   *  预约扣费的时间，遵循rfc3339标准格式
   *  示例值：2018-06-08T10:34:56+08:00
   * </pre>
   */
  @SerializedName("schedule_time")
  private String scheduleTime;

  /**
   * <pre>
   * 字段名：创建时间
   * 变量名：create_time
   * 是否必填：是
   * 类型：string(32)
   * 描述：
   *  预约创建时间，遵循rfc3339标准格式
   *  示例值：2018-06-08T10:34:56+08:00
   * </pre>
   */
  @SerializedName("create_time")
  private String createTime;

  /**
   * <pre>
   * 字段名：更新时间
   * 变量名：update_time
   * 是否必填：否
   * 类型：string(32)
   * 描述：
   *  预约更新时间，遵循rfc3339标准格式
   *  示例值：2018-06-08T10:34:56+08:00
   * </pre>
   */
  @SerializedName("update_time")
  private String updateTime;

  /**
   * <pre>
   * 字段名：预约扣费金额
   * 变量名：amount
   * 是否必填：是
   * 类型：object
   * 描述：
   *  预约扣费金额信息
   * </pre>
   */
  @SerializedName("amount")
  private SubscriptionAmount amount;

  /**
   * <pre>
   * 字段名：扣费计划
   * 变量名：billing_plan
   * 是否必填：否
   * 类型：object
   * 描述：
   *  扣费计划信息
   * </pre>
   */
  @SerializedName("billing_plan")
  private BillingPlan billingPlan;

  /**
   * <pre>
   * 字段名：附加数据
   * 变量名：attach
   * 是否必填：否
   * 类型：string(128)
   * 描述：
   *  附加数据
   *  示例值：自定义数据
   * </pre>
   */
  @SerializedName("attach")
  private String attach;
}