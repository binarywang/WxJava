package com.github.binarywang.wxpay.bean.ecommerce;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


/**
 * 垫付退款回补API结果
 * * <pre>
 *  *   文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3_partner/apis/chapter7_6_4.shtml
 *  * </pre>
 *
 * @author yantao
 * created on  2024/11/20
 */
@Data
@NoArgsConstructor
public class ReturnAdvanceResult implements Serializable {

  private static final long serialVersionUID = -186851559004865784L;

  /**
   * <pre>
   * 字段名：微信退款单号
   * 变量名：refund_id
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   * 微信支付退款单的主键，唯一定义此资源的标识。 必须是垫付退款的微信支付退款单
   * 示例值：50000000382019052709732678859
   * </pre>
   */
  @SerializedName(value = "refund_id")
  private String refundId;

  /**
   * <pre>
   * 字段名：微信回补单号
   * 变量名：advance_return_id
   * 是否必填：是
   * 类型：string（32）
   * 描述：微信支付生成的垫付回补操作单号
   * 示例值：1215562501201407033233368018
   * </pre>
   */
  @SerializedName(value = "advance_return_id")
  private String advanceReturnId	;

  /**
   * <pre>
   * 字段名：垫付回补金额
   * 变量名：return_amount
   * 是否必填：是
   * 类型：int
   * 描述：退款单对应的垫付退款的金额
   * 示例值：888
   * </pre>
   */
  @SerializedName(value = "return_amount")
  private Integer returnAmount	;

  /**
   * <pre>
   * 字段名：出款方商户号
   * 变量名：payer_mchid
   * 是否必填：是
   * 类型：string（32）
   * 描述：微信支付分配给出款方的商户号
   * 示例值：1900000109
   * </pre>
   */
  @SerializedName(value = "payer_mchid")
  private String  payerMchid ;

  /**
   * <pre>
   * 字段名：出款方账户
   * 变量名：payer_account
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   * 枚举值：
   * BASIC：基本账户
   * OPERATION：运营账户
   * 示例值：BASIC
   * </pre>
   */
  @SerializedName(value = "payer_account")
  private String payerAccount;

  /**
   * <pre>
   * 字段名：入账方商户号
   * 变量名：payee_mchid
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   * 微信支付分配给入账方的商户号
   * 示例值：1900000108
   * </pre>
   */
  @SerializedName(value = "payee_mchid")
  private String payeeMchid;

  /**
   * <pre>
   * 字段名：入账方账户
   * 变量名：payee_account
   * 是否必填：是
   * 类型：string（32）
   * 描述：枚举值：
   * BASIC：基本账户
   * OPERATION：运营账户
   * 示例值：BASIC
   * </pre>
   */
  @SerializedName(value = "payee_account")
  private String payeeAccount;

  /**
   * <pre>
   * 字段名：垫付回补结果
   * 变量名：result
   * 是否必填：是
   * 类型：string（32）
   * 描述：枚举值：
   * SUCCESS：回补成功
   * FAILED：回补失败，出款方账户余额不足时发生
   * PROCESSING：处理中
   * 示例值：SUCCESS
   * </pre>
   */
  @SerializedName(value = "result")
  private String result	;

  /**
   * <pre>
   * 字段名：垫付回补完成时间
   * 变量名：success_time
   * 是否必填：否
   * 类型：string（64）
   * 描述：垫付回补完成的时间，遵循rfc3339标准格式，
   * 格式为yyyy-MM-DDTHH:mm:ss+TIMEZONE，yyyy-MM-DD表示年月日，
   * T出现在字符串中，表示time元素的开头，HH:mm:ss表示时分秒，
   * TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。
   * 例如：2015-05-20T13:29:35+08:00表示北京时间2015年05月20日13点29分35秒。
   * 示例值：2018-06-08T10:34:56+08:00
   * </pre>
   */
  @SerializedName(value = "success_time")
  private String successTime;



}
