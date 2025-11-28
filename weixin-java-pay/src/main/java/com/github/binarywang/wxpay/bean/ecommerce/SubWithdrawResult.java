package com.github.binarywang.wxpay.bean.ecommerce;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 二级商户账户余额提现 结果
 * <pre>
 *   文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/fund/chapter3_2.shtml
 * </pre>
 */
@Data
@NoArgsConstructor
public class SubWithdrawResult implements Serializable {

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
   * 是否必填：否
   * 类型：string（32）
   * 描述：
   *  必须是字母数字
   * 示例值： 20190611222222222200000000012122
   * </pre>
   */
  @SerializedName(value = "out_request_no")
  private String outRequestNo;
}
