package com.github.binarywang.wxpay.bean.ecommerce;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 二级商户查询提现状态
 * <pre>
 *   文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/fund/chapter3_3.shtml
 * </pre>
 *
 * @author f00lish
 * created on  2020/10/27
 */
@Data
@NoArgsConstructor
public class SubWithdrawStatusResult implements Serializable {

  private static final long serialVersionUID = 4692602703819018325L;
  /**
   * <pre>
   * 字段名：二级商户号
   * 变量名：sub_mchid
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  电商平台二级商户号，由微信支付生成并下发。
   * 示例值：1900000109
   * </pre>
   */
  @SerializedName(value = "sub_mchid")
  private String subMchid;

  /**
   * <pre>
   * 字段名：电商平台商户号
   * 变量名：sp_mchid
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  电商平台商户号
   * 示例值：1800000123
   * </pre>
   */
  @SerializedName(value = "sp_mchid")
  private String spMchid;


  /**
   * <pre>
   * 字段名：提现单状态
   * 变量名：status
   * 是否必填：是
   * 类型：string（16）
   * 描述：
   *  枚举值：
   *  CREATE_SUCCESS：受理成功
   *  SUCCESS：提现成功
   *  FAIL：提现失败
   *  REFUND：提现退票
   *  CLOSE：关单
   *  INIT：业务单已创建
   * 示例值：CREATE_SUCCESS
   * </pre>
   */
  @SerializedName(value = "status")
  private String status;


  /**
   * <pre>
   * 字段名：微信支付提现单号
   * 变量名：withdraw_id
   * 是否必填：是
   * 类型：string（128）
   * 描述：
   *  电商平台提交二级商户提现申请后，由微信支付返回的申请单号，作为查询申请状态的唯一标识。
   * 示例值： 12321937198237912739132791732912793127931279317929791239112123
   * </pre>
   */
  @SerializedName(value = "withdraw_id")
  private String withdrawId;

  /**
   * <pre>
   * 字段名：商户提现单号
   * 变量名：out_request_no
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  商户提现单号，由商户自定义生成。
   * 示例值： 20190611222222222200000000012122
   * </pre>
   */
  @SerializedName(value = "out_request_no")
  private String outRequestNo;

  /**
   * <pre>
   * 字段名：提现金额
   * 变量名：amount
   * 是否必填：是
   * 类型：int）
   * 描述：
   *  单位：分
   * 示例值：1
   * </pre>
   */
  @SerializedName(value = "amount")
  private Integer amount;


  /**
   * <pre>
   * 字段名：发起提现时间
   * 变量名：create_time
   * 是否必填：是
   * 类型：string（29）
   * 描述：
   *  遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss:sss+TIMEZONE，
   *  YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss:sss表示时分秒毫秒，
   *  TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。
   *  例如：2015-05-20T13:29:35+08:00表示，北京时间2015年5月20日13点29分35秒。
   * 示例值：2015-05-20T13:29:35.120+08:00
   * </pre>
   */
  @SerializedName(value = "create_time")
  private String createTime;


  /**
   * <pre>
   * 字段名：提现状态更新时间
   * 变量名：update_time
   * 是否必填：是
   * 类型：string（29）
   * 描述：
   *  遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss:sss+TIMEZONE，
   *  YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss:sss表示时分秒毫秒，
   *  TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。
   *  例如：2015-05-20T13:29:35+08:00表示，北京时间2015年5月20日13点29分35秒。
   * 示例值：2015-05-20T13:29:35.120+08:00
   * </pre>
   */
  @SerializedName(value = "update_time")
  private String updateTime;


  /**
   * <pre>
   * 字段名：失败原因
   * 变量名：reason
   * 是否必填：是
   * 类型：string（255）
   * 描述：
   *  仅在提现失败、退票、关单时有值
   * 示例值：卡号错误
   * </pre>
   */
  @SerializedName(value = "reason")
  private String reason;

  /**
   * <pre>
   * 字段名：提现备注
   * 变量名：remark
   * 是否必填：是
   * 类型：string（56）
   * 描述：
   *  商户对提现单的备注，若发起提现时未传入相应值或输入不合法，则该值为空
   * 示例值：交易提现
   * </pre>
   */
  @SerializedName(value = "remark")
  private String remark;

  /**
   * <pre>
   * 字段名：银行附言
   * 变量名：bank_memo
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  展示在收款银行系统中的附言，由数字、字母、汉字组成（能否成功展示依赖银行系统支持）。若发起提现时未传入相应值或输入不合法，则该值为空
   * 示例值：微信提现
   * </pre>
   */
  @SerializedName(value = "bank_memo")
  private String bankMemo;

}
