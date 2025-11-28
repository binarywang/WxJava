package com.github.binarywang.wxpay.bean.ecommerce;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.io.Serializable;

/**
 * 完结分账 对象
 * <pre>
 *   文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/profitsharing/chapter3_5.shtml
 * </pre>
 *
 * @author f00lish
 * created on  2020/09/12
 */
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FinishOrderRequest implements Serializable {

  private static final long serialVersionUID = -8662837652326828377L;

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
   * 字段名：微信订单号
   * 变量名：transaction_id
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  微信支付订单号。
   *  示例值：4208450740201411110007820472
   * </pre>
   */
  @SerializedName(value = "transaction_id")
  private String transactionId;

  /**
   * <pre>
   * 字段名：商户分账单号
   * 变量名：out_order_no
   * 是否必填：是
   * 类型：string（64）
   * 描述：
   *  商户系统内部的分账单号，在商户系统内部唯一（单次分账、多次分账、完结分账应使用不同的商户分账单号），同一分账单号多次请求等同一次。
   *  示例值：P20150806125346
   * </pre>
   */
  @SerializedName(value = "out_order_no")
  private String outOrderNo;

  /**
   * <pre>
   * 字段名：分账描述
   * 变量名：description
   * 是否必填：是
   * 类型：string（80）
   * 描述：
   *  分账的原因描述，分账账单中需要体现。
   *  示例值：分给商户1900000109
   * </pre>
   */
  @SerializedName(value = "description")
  private String description;


}
