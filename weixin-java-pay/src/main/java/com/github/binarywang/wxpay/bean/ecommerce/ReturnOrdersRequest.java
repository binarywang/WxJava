package com.github.binarywang.wxpay.bean.ecommerce;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.io.Serializable;

/**
 * 请求分账回退
 * * <pre>
 *  *   文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/profitsharing/chapter3_3.shtml
 *  * </pre>
 *
 * @author f00lish
 * created on  2020/09/14
 */
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReturnOrdersRequest implements Serializable {
  private static final long serialVersionUID = -3674823388136221959L;

  /**
   * <pre>
   * 字段名：二级商户号
   * 变量名：sub_mchid
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  分账出资的电商平台二级商户，填写微信支付分配的商户号。
   *  示例值：1900000109
   * </pre>
   */
  @SerializedName(value = "sub_mchid")
  private String subMchid;

  /**
   * <pre>
   * 字段名：微信分账单号
   * 变量名：order_id
   * 是否必填：与out_order_no二选一
   * 类型：string（64）
   * 描述：
   *  微信分账单号，微信系统返回的唯一标识。微信分账单号和商户分账单号二选一填写。
   *  示例值：3008450740201411110007820472
   * </pre>
   */
  @SerializedName(value = "order_id")
  private String orderId;

  /**
   * <pre>
   * 字段名：商户分账单号
   * 变量名：out_order_no
   * 是否必填：与order_id二选一
   * 类型：string（64）
   * 描述：
   *   商户系统内部的分账单号，在商户系统内部唯一（单次分账、多次分账、完结分账应使用不同的商户分账单号），同一分账单号多次请求等同一次。
   *  示例值：P20150806125346
   * </pre>
   */
  @SerializedName(value = "out_order_no")
  private String outOrderNo;

  /**
   * <pre>
   * 字段名：商户回退单号
   * 变量名：out_return_no
   * 是否必填：是
   * 类型：string（64）
   * 描述：
   *   此回退单号是商户在自己后台生成的一个新的回退单号，在商户后台唯一。
   *  示例值：P20150806125346
   * </pre>
   */
  @SerializedName(value = "out_return_no")
  private String outReturnNo;

  /**
   * <pre>
   * 字段名：回退商户号
   * 变量名：return_mchid
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  只能对原分账请求中成功分给商户接收方进行回退。
   *  示例值：86693852
   * </pre>
   */
  @SerializedName(value = "return_mchid")
  private String returnMchid;

  /**
   * <pre>
   * 字段名：回退金额
   * 变量名：amount
   * 是否必填：是
   * 类型：int
   * 描述：
   *  需要从分账接收方回退的金额，单位为分，只能为整数，不能超过原始分账单分出给该接收方的金额。
   *  示例值：10
   * </pre>
   */
  @SerializedName(value = "amount")
  private Integer amount;

  /**
   * <pre>
   * 字段名：回退描述
   * 变量名：description
   * 是否必填：是
   * 类型：string（80）
   * 描述：
   *  分账回退的原因描述
   *  示例值：分账回退
   * </pre>
   */
  @SerializedName(value = "description")
  private String description;

  /**
   * <pre>
   * 字段名：微信订单号
   * 变量名：transaction_id
   * 是否必填：否
   * 类型：string（32）
   * 描述：
   *  微信支付订单号，大于6个月的订单，必填
   *  示例值：4208450740201411110007820472
   * </pre>
   */
  @SerializedName(value = "transaction_id")
  private String transactionId;
}
