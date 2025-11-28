package com.github.binarywang.wxpay.bean.marketing;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 营销补差付款返回对象
 * <pre>
 *   文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter9_2_16.shtml
 * </pre>
 *
 * @author yujam
 */
@Data
@NoArgsConstructor
public class BusiFavorSubsidyResult implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * <pre>* 字段名：补差付款单号
   * 变量名：subsidy_receipt_id
   * 是否必填：是
   * 类型：string[28, 32]
   * 描述：
   * 补差付款唯一单号，由微信支付生成，仅在补差付款成功后有返回 示例值：1120200119165100000000000001
   * </pre>
   */
  @SerializedName(value = "subsidy_receipt_id")
  private String subsidyReceiptId;

  /**
   * <pre>* 字段名：商家券批次号
   * 变量名：stock_id
   * 是否必填：是
   * 类型：string[1, 20]
   * 描述：
   * 由微信支付生成，调用创建商家券API成功时返回的唯一批次ID 示例值：128888000000001
   * </pre>
   */
  @SerializedName(value = "stock_id")
  private String stockId;

  /**
   * <pre>* 字段名：商家券Code
   * 变量名：coupon_code
   * 是否必填：是
   * 类型：string[1, 128]
   * 描述：
   * 券的唯一标识 示例值：ABCD12345678
   * </pre>
   */
  @SerializedName(value = "coupon_code")
  private String couponCode;

  /**
   * <pre>* 字段名：微信支付订单号
   * 变量名：transaction_id
   * 是否必填：是
   * 类型：string[28, 32]
   * 描述：
   * 微信支付下单支付成功返回的订单号 示例值：4200000913202101152566792388
   * </pre>
   */
  @SerializedName(value = "transaction_id")
  private String transactionId;

  /**
   * <pre>* 字段名：营销补差扣款商户号
   * 变量名：payer_merchant
   * 是否必填：是
   * 类型：string[1, 32]
   * 描述：
   * 营销补差扣款商户号 示例值：1900000001
   * </pre>
   */
  @SerializedName(value = "payer_merchant")
  private String payerMerchant;

  /**
   * <pre>* 字段名：营销补差入账商户号
   * 变量名：payee_merchant
   * 是否必填：是
   * 类型：string[1, 32]
   * 描述：
   * 营销补差入账商户号 示例值：1900000002
   * </pre>
   */
  @SerializedName(value = "payee_merchant")
  private String payeeMerchant;

  /**
   * <pre>* 字段名：补差付款金额
   * 变量名：amount
   * 是否必填：是
   * 类型：int
   * 描述：
   * 单位为分，单笔订单补差金额不得超过券的优惠金额，最高补差金额为5000元 > 券的优惠金额定义： 满减券：满减金额即为优惠金额 折扣券：优惠金额 = 微信支付订单金额 ÷ 折扣比例 × (1 - 折扣比例) 换购券：不支持 示例值：100
   * </pre>
   */
  @SerializedName(value = "amount")
  private Integer amount;

  /**
   * <pre>* 字段名：补差付款描述
   * 变量名：description
   * 是否必填：是
   * 类型：string[1, 1024]
   * 描述：
   * 付款备注描述，查询的时候原样带回 示例值：20210115DESCRIPTION
   * </pre>
   */
  @SerializedName(value = "description")
  private String description;

  /**
   * <pre>* 字段名：补差付款单据状态
   * 变量名：status
   * 是否必填：是
   * 类型：string[1, 32]
   * 描述：
   * 补差付款单据状态 ACCEPTED：受理成功 SUCCESS：补差补款成功 FAIL：补差付款失败 RETURNING：补差回退中 PARTIAL_RETURN：补差部分回退 FULL_RETURN：补差全额回退 示例值：SUCCESS
   * </pre>
   */
  @SerializedName(value = "status")
  private String status;

  /**
   * <pre>* 字段名：补差付款失败原因
   * 变量名：fail_reason
   * 是否必填：否
   * 类型：string[1, 1024]
   * 描述：
   * 仅在补差付款失败时，返回告知对应失败的原因 INSUFFICIENT_BALANCE：扣款商户余额不足 NOT_INCOMESPLIT_ORDER：非分账订单 EXCEED_SUBSIDY_AMOUNT_QUOTA：超出订单补差总额限制 EXCEED_SUBSIDY_COUNT_QUOTA：超出订单补差总数限制 OTHER：其他原因 示例值：INSUFFICIENT_BALANCE
   * </pre>
   */
  @SerializedName(value = "fail_reason")
  private String failReason;

  /**
   * <pre>* 字段名：补差付款完成时间
   * 变量名：success_time
   * 是否必填：否
   * 类型：string[28, 32]
   * 描述：
   * 仅在补差付款成功时，返回完成时间。遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss表示时分秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35.+08:00表示，北京时间2015年5月20日 13点29分35秒。 示例值：2021-01-20T10:29:35+08:00
   * </pre>
   */
  @SerializedName(value = "success_time")
  private String successTime;

  /**
   * <pre>* 字段名：业务请求唯一单号
   * 变量名：out_subsidy_no
   * 是否必填：是
   * 类型：string[1, 128]
   * 描述：
   * 商户侧需保证唯一性。可包含英文字母，数字，｜，_，*，-等内容，不允许出现其他不合法符号 示例值：subsidy-abcd-12345678
   * </pre>
   */
  @SerializedName(value = "out_subsidy_no")
  private String outSubsidyNo;

  /**
   * <pre>* 字段名：补差付款发起时间
   * 变量名：create_time
   * 是否必填：否
   * 类型：string[28, 32]
   * 描述：
   * 补差付款单据创建时间。遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss表示时分秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35.+08:00表示，北京时间2015年5月20日 13点29分35秒。 示例值：2021-01-20T10:29:35+08:00
   * </pre>
   */
  @SerializedName(value = "create_time")
  private String createTime;
}
