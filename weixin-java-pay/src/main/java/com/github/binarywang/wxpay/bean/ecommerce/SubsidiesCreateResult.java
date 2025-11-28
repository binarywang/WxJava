package com.github.binarywang.wxpay.bean.ecommerce;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.io.Serializable;

/**
 * add by 306932545@qq.com
 * 请求补差返回对象
 * <pre>
 *   https://pay.weixin.qq.com/wiki/doc/apiv3_partner/apis/chapter7_5_1.shtml
 * </pre>
 */
@Data
@Builder
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SubsidiesCreateResult implements Serializable {
  private static final long serialVersionUID =  5008480977464421822L;

  /**
   * <pre>
   * 字段名：二级商户号
   * 变量名：sub_mchid
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   * 补差的电商平台二级商户，填写微信支付分配的商户号。
   * 示例值：1900000109
   * </pre>
   */
  @SerializedName(value = "sub_mchid")
  private String subMchid;


  /**
   * <pre>
   * 字段名：微信订单号
   * 变量名：transaction_id
   * 是否必填：是
   * 类型：string（64）
   * 描述：
   *  微信支付订单号。
   * 示例值： 4208450740201411110007820472
   * </pre>
   */
  @SerializedName(value = "transaction_id")
  private String transactionId;

  /**
   * <pre>
   * 字段名：微信补差单号
   * 变量名：subsidy_id
   * 是否必填：是
   * 类型：string（64）
   * 描述：
   *  微信补差单号，微信支付系统返回的唯一标识。
   * 示例值： 3008450740201411110007820472
   * </pre>
   */
  @SerializedName(value = "subsidy_id")
  private String subsidyId;

  /**
   * <pre>
   * 字段名：补差描述
   * 变量名：description
   * 是否必填：是
   * 类型：string（80）
   * 描述：
   * 补差备注描述，查询的时候原样带回。
   * 示例值：测试备注
   * </pre>
   */
  @SerializedName(value = "description")
  private String description;

  /**
   * <pre>
   * 字段名：补差金额
   * 变量名：amount
   * 是否必填：是
   * 类型：int64
   * 描述：
   * 补差金额，单位为分，只能为整数，不能超过下单时候的最大补差金额。
   * 注意：单笔订单最高补差金额为10000元
   * 示例值：10
   * </pre>
   */
  @SerializedName(value = "amount")
  private Integer amount;

  /**
   * <pre>
   * 字段名：补差单结果
   * 变量名：result
   * 是否必填：是
   * 类型：string（16）
   * 描述：
   * 补差单状态，枚举值：
   * SUCCESS：补差成功
   * FAIL：补差失败
   * REFUND：已全额回退
   * 示例值：SUCCESS
   * </pre>
   */
  @SerializedName(value = "result")
  private String result;

  /**
   * <pre>
   * 字段名：补差完成时间
   * 变量名：success_time
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  补贴完成时间，遵循rfc3339标准格式，格式为yyyy-MM-DDTHH:mm:ss:sss+TIMEZONE，yyyy-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss:sss表示时分秒毫秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35+08:00表示，北京时间2015年5月20日13点29分35秒。
   *  示例值： 2015-05-20T13:29:35.120+08:00
   * </pre>
   */
  @SerializedName(value = "success_time")
  private String successTime;

}
