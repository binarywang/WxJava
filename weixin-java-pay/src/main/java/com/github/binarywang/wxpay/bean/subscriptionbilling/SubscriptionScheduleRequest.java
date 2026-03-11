package com.github.binarywang.wxpay.bean.subscriptionbilling;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 预约扣费请求参数
 * <pre>
 *   文档地址：https://pay.weixin.qq.com/doc/v3/merchant/4012161105
 * </pre>
 *
 * @author Binary Wang
 */
@Data
@NoArgsConstructor
public class SubscriptionScheduleRequest implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * <pre>
   * 字段名：商户订单号
   * 变量名：out_trade_no
   * 是否必填：是
   * 类型：string(32)
   * 描述：
   *  商户系统内部订单号，只能是数字、大小写字母_-*且在同一个商户号下唯一
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
   * 字段名：预约扣费时间
   * 变量名：schedule_time
   * 是否必填：是
   * 类型：string(32)
   * 描述：
   *  预约扣费的时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss+TIMEZONE
   *  示例值：2018-06-08T10:34:56+08:00
   * </pre>
   */
  @SerializedName("schedule_time")
  private String scheduleTime;

  /**
   * <pre>
   * 字段名：扣费计划
   * 变量名：billing_plan
   * 是否必填：否
   * 类型：object
   * 描述：
   *  扣费计划信息，用于连续包月等场景
   * </pre>
   */
  @SerializedName("billing_plan")
  private BillingPlan billingPlan;

  /**
   * <pre>
   * 字段名：通知地址
   * 变量名：notify_url
   * 是否必填：否
   * 类型：string(256)
   * 描述：
   *  异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数
   *  示例值：https://www.weixin.qq.com/wxpay/pay.php
   * </pre>
   */
  @SerializedName("notify_url")
  private String notifyUrl;

  /**
   * <pre>
   * 字段名：附加数据
   * 变量名：attach
   * 是否必填：否
   * 类型：string(128)
   * 描述：
   *  附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用
   *  示例值：自定义数据
   * </pre>
   */
  @SerializedName("attach")
  private String attach;
}