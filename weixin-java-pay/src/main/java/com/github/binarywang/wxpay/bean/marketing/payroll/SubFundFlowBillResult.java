package com.github.binarywang.wxpay.bean.marketing.payroll;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <pre>
 * 申请单个子商户资金账单
 * 文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3_partner/Offline/apis/chapter4_1_25.shtml
 *
 * 适用对象：服务商
 * 请求URL：https://api.mch.weixin.qq.com/v3/bill/sub-merchant-fundflowbill
 * 请求方式：GET
 * </pre>
 *
 * @author xiaoqiang
 * created on  2021/12/7
 */
@Data
@NoArgsConstructor
public class SubFundFlowBillResult implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * <pre>
   * 字段名：子商户号
   * 变量名：sub_mchid
   * 是否必填：是
   * 类型：string[1, 32]
   * 描述：
   *  query下载指定子商户的账单。
   *  示例值：19000000001
   * </pre>
   */
  @SerializedName(value = "sub_mchid")
  private String subMchid;

  /**
   * <pre>
   * 字段名：账单日期
   * 变量名：bill_date
   * 是否必填：是
   * 类型：string[10, 10]
   * 描述：
   *  query格式YYYY-MM-DD
   *  示例值：2019-06-11
   * </pre>
   */
  @SerializedName(value = "bill_date")
  private String billDate;

  /**
   * <pre>
   * 字段名：资金账户类型
   * 变量名：account_type
   * 是否必填：是
   * 类型：string[1, 16]
   * 描述：
   * query枚举值：
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
   * 字段名：加密算法
   * 变量名：algorithm
   * 是否必填：是
   * 类型：string[1, 31]
   * 描述：
   * query枚举值：
   *     AEAD_AES_256_GCM：AEAD_AES_256_GCM加密算法
   * 示例值：AEAD_AES_256_GCM
   * </pre>
   */
  @SerializedName(value = "algorithm")
  private String algorithm;
  /**
   * <pre>
   * 字段名：压缩格式
   * 变量名：tar_type
   * 是否必填：否
   * 类型：string[1, 8]
   * 描述：
   * query不填则以不压缩的方式返回数据流
   *     枚举值：
   *     GZIP：返回格式为.gzip的压缩包账单
   * 示例值：GZIP
   * </pre>
   */
  @SerializedName(value = "tar_type")
  private String tarType;
}
