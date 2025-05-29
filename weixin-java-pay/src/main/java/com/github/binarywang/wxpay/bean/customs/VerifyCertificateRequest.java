package com.github.binarywang.wxpay.bean.customs;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xifengzhu
 */
@Data
public class VerifyCertificateRequest implements Serializable {
  private static final long serialVersionUID = 721089103541592315L;
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
   * 字段名：证件类型
   * 变量名：certificate_type
   * 是否必填：是
   * 类型：string（16）
   * 描述：
   *  请传固定值IDCARD，暂只支持大陆身份证
   *  示例值：IDCARD
   * </pre>
   */
  @SerializedName(value = "certificate_type")
  private String certificateType;

  /**
   * <pre>
   * 字段名：证件号
   * 变量名：certificate_id
   * 是否必填：是
   * 类型：string
   * 描述：
   *  用户大陆身份证号，尾号为字母X的身份证号，请大写字母X。该字段需要进行加密
   *  示例值：330821198809085211
   * </pre>
   */
  @SerializedName(value = "certificate_id")
  private String certificateId;

  /**
   * <pre>
   * 字段名：证件姓名
   * 变量名：certificate_name
   * 是否必填：是
   * 类型：string
   * 描述：
   *  证件姓名，字段值需要进行加密
   *  示例值：330821198809085211
   * </pre>
   */
  @SerializedName(value = "certificate_name")
  private String certificateName;

}
