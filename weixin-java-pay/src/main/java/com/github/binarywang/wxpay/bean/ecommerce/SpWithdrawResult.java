package com.github.binarywang.wxpay.bean.ecommerce;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 电商平台提现 结果
 * <pre>
 *   文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/fund/chapter3_5.shtml
 * </pre>
 */
@Data
@NoArgsConstructor
public class SpWithdrawResult implements Serializable {

  /**
   * <pre>
   * 字段名：微信支付提现单号
   * 变量名：withdraw_id
   * 是否必填：否 （文档里面是【否】，理论上应该都有值）
   * 类型：string（128）
   * 描述：
   *  微信支付系统生成的提现单号。
   * 示例值：12321937198237912739132791732912793127931279317929791239112123
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
   *  必须是字母数字
   * 示例值： 20190611222222222200000000012122
   * </pre>
   */
  @SerializedName(value = "out_request_no")
  private String outRequestNo;
}
