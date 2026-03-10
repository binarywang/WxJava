package com.github.binarywang.wxpay.bean.subscriptionbilling;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 立即扣费响应结果
 * <pre>
 *   文档地址：https://pay.weixin.qq.com/doc/v3/merchant/4012161105
 * </pre>
 *
 * @author Binary Wang
 */
@Data
@NoArgsConstructor
public class SubscriptionInstantBillingResult implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * <pre>
   * 字段名：微信支付订单号
   * 变量名：transaction_id
   * 是否必填：是
   * 类型：string(32)
   * 描述：
   *  微信支付系统生成的订单号
   *  示例值：1217752501201407033233368018
   * </pre>
   */
  @SerializedName("transaction_id")
  private String transactionId;

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
   * 字段名：交易状态
   * 变量名：trade_state
   * 是否必填：是
   * 类型：string(32)
   * 描述：
   *  交易状态
   *  SUCCESS：支付成功
   *  REFUND：转入退款
   *  NOTPAY：未支付
   *  CLOSED：已关闭
   *  REVOKED：已撤销(刷卡支付)
   *  USERPAYING：用户支付中
   *  PAYERROR：支付失败
   *  示例值：SUCCESS
   * </pre>
   */
  @SerializedName("trade_state")
  private String tradeState;

  /**
   * <pre>
   * 字段名：交易状态描述
   * 变量名：trade_state_desc
   * 是否必填：是
   * 类型：string(256)
   * 描述：
   *  交易状态描述
   *  示例值：支付成功
   * </pre>
   */
  @SerializedName("trade_state_desc")
  private String tradeStateDesc;

  /**
   * <pre>
   * 字段名：支付完成时间
   * 变量名：success_time
   * 是否必填：否
   * 类型：string(32)
   * 描述：
   *  支付完成时间，遵循rfc3339标准格式
   *  示例值：2018-06-08T10:34:56+08:00
   * </pre>
   */
  @SerializedName("success_time")
  private String successTime;

  /**
   * <pre>
   * 字段名：扣费金额
   * 变量名：amount
   * 是否必填：是
   * 类型：object
   * 描述：
   *  扣费金额信息
   * </pre>
   */
  @SerializedName("amount")
  private SubscriptionAmount amount;
}