package com.github.binarywang.wxpay.bean.marketing;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 营销补差付款请求对象
 * <pre>
 *   文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter9_2_16.shtml
 * </pre>
 *
 * @author yujam
 */
@Data
@NoArgsConstructor
public class BusiFavorSubsidyRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * <pre>* 字段名：商家券批次号
   * 变量名：stock_id
   * 是否必填：是
   * 类型：string[1, 20]
   * 描述：
   * body由微信支付生成，调用创建商家券API成功时返回的唯一批次ID 仅支持“满减券”和“折扣券”的批次，“换购券”批次不支持 示例值：128888000000001
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
   * body券的唯一标识。 在WECHATPAY_MODE的券Code模式下，商家券Code是由微信支付生成的唯一ID； 在MERCHANT_UPLOAD、MERCHANT_API的券Code模式下，商家券Code是由商户上传或指定，在批次下保证唯一； 示例值：ABCD12345678
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
   * body微信支付下单支付成功返回的订单号 示例值：4200000913202101152566792388
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
   * body营销补差扣款商户号 示例值：1900000001
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
   * body营销补差入账商户号 示例值：1900000002
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
   * body单位为分，单笔订单补差金额不得超过券的优惠金额，最高补差金额为5000元 > 券的优惠金额定义： 满减券：满减金额即为优惠金额 折扣券：优惠金额 = 微信支付订单金额 ÷ 折扣比例 × (1 - 折扣比例) 换购券：不支持 示例值：100
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
   * body付款备注描述，查询的时候原样带回 示例值：20210115DESCRIPTION
   * </pre>
   */
  @SerializedName(value = "description")
  private String description;

  /**
   * <pre>* 字段名：业务请求唯一单号
   * 变量名：out_subsidy_no
   * 是否必填：是
   * 类型：string[1, 128]
   * 描述：
   * body商户侧需保证唯一性。可包含英文字母，数字，｜，_，*，-等内容，不允许出现其他不合法符号 示例值：subsidy-abcd-12345678
   * </pre>
   */
  @SerializedName(value = "out_subsidy_no")
  private String outSubsidyNo;
}
