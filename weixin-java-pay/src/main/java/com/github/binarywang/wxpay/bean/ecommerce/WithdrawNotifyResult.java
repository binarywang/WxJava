package com.github.binarywang.wxpay.bean.ecommerce;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 提现状态变更通知结果
 * <pre>
 *   文档地址：https://pay.weixin.qq.com/doc/v3/partner/4013049135
 * </pre>
 *
 * @author copilot
 * created on  2024/12/24
 */
@Data
@NoArgsConstructor
public class WithdrawNotifyResult implements Serializable {

  private static final long serialVersionUID = -7451351849088368701L;

  /**
   * 源数据
   */
  private NotifyResponse rawData;

  /**
   * <pre>
   * 字段名：电商平台商户号
   * 变量名：sp_mchid
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  微信支付分配给电商平台的商户号
   * 示例值：1900000100
   * </pre>
   */
  @SerializedName(value = "sp_mchid")
  private String spMchid;

  /**
   * <pre>
   * 字段名：二级商户号
   * 变量名：sub_mchid
   * 是否必填：否
   * 类型：string（32）
   * 描述：
   *  微信支付分配给二级商户的商户号，仅二级商户提现时返回
   * 示例值：1900000109
   * </pre>
   */
  @SerializedName(value = "sub_mchid")
  private String subMchid;

  /**
   * <pre>
   * 字段名：商户提现单号
   * 变量名：out_request_no
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  商户提现单号，由商户自定义生成
   * 示例值：20190611222222222200000000012122
   * </pre>
   */
  @SerializedName(value = "out_request_no")
  private String outRequestNo;

  /**
   * <pre>
   * 字段名：微信支付提现单号
   * 变量名：withdraw_id
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  微信支付提现单号
   * 示例值：12321002198704230011101200
   * </pre>
   */
  @SerializedName(value = "withdraw_id")
  private String withdrawId;

  /**
   * <pre>
   * 字段名：提现状态
   * 变量名：status
   * 是否必填：是
   * 类型：string（16）
   * 描述：
   *  提现状态：
   *  CREATE_SUCCESS：受理成功
   *  SUCCESS：提现成功
   *  FAILED：提现失败
   *  REFUND：提现退票
   *  CLOSE：关单
   * 示例值：SUCCESS
   * </pre>
   */
  @SerializedName(value = "status")
  private String status;

  /**
   * <pre>
   * 字段名：提现金额
   * 变量名：amount
   * 是否必填：是
   * 类型：int64
   * 描述：
   *  提现金额，单位：分（人民币）
   * 示例值：100
   * </pre>
   */
  @SerializedName(value = "amount")
  private Integer amount;

  /**
   * <pre>
   * 字段名：提现发起时间
   * 变量名：create_time
   * 是否必填：是
   * 类型：string（29）
   * 描述：
   *  提现发起时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss:sss+TIMEZONE，
   *  YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss:sss表示时分秒毫秒，
   *  TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。
   *  例如：2015-05-20T13:29:35.120+08:00表示，北京时间2015年5月20日13点29分35秒
   * 示例值：2018-06-08T10:34:56+08:00
   * </pre>
   */
  @SerializedName(value = "create_time")
  private String createTime;

  /**
   * <pre>
   * 字段名：提现更新时间
   * 变量名：update_time
   * 是否必填：是
   * 类型：string（29）
   * 描述：
   *  提现更新时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss:sss+TIMEZONE，
   *  YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss:sss表示时分秒毫秒，
   *  TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。
   *  例如：2015-05-20T13:29:35.120+08:00表示，北京时间2015年5月20日13点29分35秒
   * 示例值：2018-06-08T10:34:56+08:00
   * </pre>
   */
  @SerializedName(value = "update_time")
  private String updateTime;

  /**
   * <pre>
   * 字段名：失败原因
   * 变量名：reason
   * 是否必填：否
   * 类型：string（256）
   * 描述：
   *  提现失败原因，仅在提现失败、退票时有值
   * 示例值：账户余额不足
   * </pre>
   */
  @SerializedName(value = "reason")
  private String reason;

  /**
   * <pre>
   * 字段名：备注
   * 变量名：remark
   * 是否必填：否
   * 类型：string（256）
   * 描述：
   *  商户对提现单的备注，若提现申请时未传递，则无此字段
   * 示例值：交易提现
   * </pre>
   */
  @SerializedName(value = "remark")
  private String remark;

  /**
   * <pre>
   * 字段名：银行附言
   * 变量名：bank_memo
   * 是否必填：否
   * 类型：string（256）
   * 描述：
   *  展示在收款银行系统中的附言，若提现申请时未传递，则无此字段
   * 示例值：微信支付提现
   * </pre>
   */
  @SerializedName(value = "bank_memo")
  private String bankMemo;

  /**
   * <pre>
   * 字段名：账户类型
   * 变量名：account_type
   * 是否必填：否
   * 类型：string（16）
   * 描述：
   *  提现账户类型，仅电商平台提现时返回：
   *  BASIC：基本账户
   *  OPERATION：运营账户
   *  FEES：手续费账户
   * 示例值：BASIC
   * </pre>
   */
  @SerializedName(value = "account_type")
  private String accountType;

  /**
   * <pre>
   * 字段名：提现失败解决方案
   * 变量名：solution
   * 是否必填：否
   * 类型：string（255）
   * 描述：
   *  仅在提现失败、退票、关单时有值
   * 示例值：请修改结算银行卡信息
   * </pre>
   */
  @SerializedName(value = "solution")
  private String solution;

  /**
   * <pre>
   * 字段名：出款户名
   * 变量名：account_name
   * 是否必填：否
   * 类型：string（256）
   * 描述：
   *  出款户名（加密）
   * 示例值：2mPt3pWzZ+O3dSGbGnCrR3bqMZ5pwfpQy1NNrA==
   * </pre>
   */
  @SerializedName(value = "account_name")
  private String accountName;

  /**
   * <pre>
   * 字段名：出款账号
   * 变量名：account_number
   * 是否必填：否
   * 类型：string（256）
   * 描述：
   *  出款账号（加密）
   * 示例值：2mPt3pWzZ+O3dSGbGnCrR3bqMZ5pwfpQy1NNrA==
   * </pre>
   */
  @SerializedName(value = "account_number")
  private String accountNumber;

  /**
   * <pre>
   * 字段名：出款银行全称（含支行）
   * 变量名：bank_name
   * 是否必填：否
   * 类型：string（256）
   * 描述：
   *  出款银行全称（含支行）（加密）
   * 示例值：2mPt3pWzZ+O3dSGbGnCrR3bqMZ5pwfpQy1NNrA==
   * </pre>
   */
  @SerializedName(value = "bank_name")
  private String bankName;
}