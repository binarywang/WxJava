package com.github.binarywang.wxpay.bean.customs;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author xifenzhu
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class DeclarationRequest implements Serializable {

  private static final long serialVersionUID = -170115210896346836L;
  /**
   * <pre>
   * 字段名：机构APPID
   * 变量名：appid
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  微信分配的公众账号ID
   *  示例值：wxd678efh567hg6787
   * </pre>
   */
  @SerializedName(value = "appid")
  private String appid;

  /**
   * <pre>
   * 字段名：商户号
   * 变量名：mchid
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  微信支付分配的商户号
   *  示例值：1230000109
   * </pre>
   */
  @SerializedName(value = "mchid")
  private String mchid;

  /**
   * <pre>
   * 字段名：商户订单号
   * 变量名：out_trade_no
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-&#124;*@ ，且在同一个商户号下唯一
   *  示例值：20150806125346
   * </pre>
   */
  @SerializedName(value = "out_trade_no")
  private String outTradeNo;

  /**
   * <pre>
   * 字段名：微信支付返回的订单号
   * 变量名：transaction_id
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  微信分配的公众账号ID
   *  示例值：1000320306201511078440737890
   * </pre>
   */
  @SerializedName(value = "transaction_id")
  private String transactionId;

  /**
   * <pre>
   * 字段名：海关
   * 变量名：customs
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  海关代码, 枚举值参见参数规定-海关列表（https://pay.weixin.qq.com/wiki/doc/api/wxpay/ch/declarecustom_ch/chapter2_3.shtml#menu11）
   *  示例值：SHANGHAI_ZS
   * </pre>
   */
  @SerializedName(value = "customs")
  private String customs;

  /**
   * <pre>
   * 字段名：商户海关备案号
   * 变量名：merchant_customs_no
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  商户在海关登记的备案号
   *  示例值：123456
   * </pre>
   */
  @SerializedName(value = "merchant_customs_no")
  private String merchantCustomsNo;

  /**
   * <pre>
   * 字段名：关税
   * 变量名：duty
   * 是否必填：否
   * 类型：int
   * 描述：
   *  关税，以分为单位，非必填项，不会提交给海关
   *  示例值：888
   * </pre>
   */
  @SerializedName(value = "duty")
  private Integer duty;

  /**
   * <pre>
   * 字段名：商户子订单号
   * 变量名：sub_order_no
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  商户子订单号，如有拆单则必传
   *  注意：仅适用于机构模式
   *  示例值：20150806125346
   * </pre>
   */
  @SerializedName(value = "sub_order_no")
  private String subOrderNo;

  /**
   * <pre>
   * 字段名：货币类型
   * 变量名：fee_type
   * 是否必填：否
   * 类型：string（32）
   * 描述：
   *  微信支付订单支付时使用的币种，暂只支持人民币CNY，如有拆单则必传
   *  示例值：CNY
   * </pre>
   */
  @SerializedName(value = "fee_type")
  private String feeType;

  /**
   * <pre>
   * 字段名：子订单金额
   * 变量名：order_fee
   * 是否必填：否
   * 类型：int
   * 描述：
   *  子订单金额，以分为单位，不能超过原订单金额，order_fee=transport_fee+product_fee（应付金额=物流费+商品价格），如有拆单则必传
   *  示例值：888
   * </pre>
   */
  @SerializedName(value = "order_fee")
  private Integer orderFee;

  /**
   * <pre>
   * 字段名：物流费用
   * 变量名：transport_fee
   * 是否必填：否
   * 类型：int
   * 描述：
   *  物流费用，以分为单位，如有拆单则必传
   *  示例值：888
   * </pre>
   */
  @SerializedName(value = "transport_fee")
  private Integer transportFee;

  /**
   * <pre>
   * 字段名：商品费用
   * 变量名：product_fee
   * 是否必填：否
   * 类型：int
   * 描述：
   *  商品费用，以分为单位，如有拆单则必传
   *  示例值：888
   * </pre>
   */
  @SerializedName(value = "product_fee")
  private Integer productFee;
}
