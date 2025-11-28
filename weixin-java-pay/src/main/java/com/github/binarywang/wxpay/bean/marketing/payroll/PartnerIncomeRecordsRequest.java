package com.github.binarywang.wxpay.bean.marketing.payroll;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <pre>
 * 特约商户银行来账查询
 * 文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3_partner/Offline/apis/chapter4_1_27.shtml
 *
 * 适用对象：服务商
 * 请求URL：https://api.mch.weixin.qq.com/v3/merchantfund/partner/income-records
 * 请求方式：GET
 * </pre>
 *
 * @author xiaoqiang
 * created on  2021/12/7
 */
@Data
@NoArgsConstructor
public class PartnerIncomeRecordsRequest implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * <pre>
   * 字段名：特约商户号
   * 变量名：sub_mchid
   * 是否必填：是
   * 类型：string[1, 32]
   * 描述：
   *  query需查询银行来账记录列表的特约商户的商户号，该商户号须为服务商的特约商户号。
   *  示例值：2480253391
   * </pre>
   */
  @SerializedName(value = "sub_mchid")
  private String subMchid;

  /**
   * <pre>
   * 字段名：账户类型
   * 变量名：account_type
   * 是否必填：是
   * 类型：string[1, 32]
   * 描述：
   * query需查询银行来账记录商户的账户类型。
   *     枚举值：
   *     BASIC：基本账户
   *     OPERATION：运营账户
   *     FEES：手续费账户
   * 示例值：BASIC
   * </pre>
   */
  @SerializedName(value = "account_type")
  private String accountType;
  /**
   * <pre>
   * 字段名：日期
   * 变量名：date
   * 是否必填：是
   * 类型：string[10, 10]
   * 描述：
   * query查询的日期，一次只能查询一天，最久可查询90天内的记录，格式为“YYYY-MM-DD”。
   * 示例值：2019-06-11
   * </pre>
   */
  @SerializedName(value = "date")
  private String date;
  /**
   * <pre>
   * 字段名：本次查询偏移量
   * 变量名：offset
   * 是否必填：否
   * 类型：int
   * 描述：
   * query非负整数，表示该次请求资源的起始位置，从0开始计数。调用方选填，默认为0。offset为20，limit为100时，查询第20-119条数据。
   * 示例值：0
   * </pre>
   */
  @SerializedName(value = "offset")
  private int offset;
  /**
   * <pre>
   * 字段名：本次请求最大查询条数
   * 变量名：limit
   * 是否必填：是
   * 类型：int
   * 描述：
   * query非0非负的整数，该次请求可返回的最大资源条数，最大支持100条。
   * 示例值：100
   * </pre>
   */
  @SerializedName(value = "limit")
  private int limit;
}
