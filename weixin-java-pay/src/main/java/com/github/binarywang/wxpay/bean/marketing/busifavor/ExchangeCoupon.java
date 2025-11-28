package com.github.binarywang.wxpay.bean.marketing.busifavor;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 换购券
 * <pre>
 *   文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter9_2_1.shtml
 * </pre>
 *
 * @author yujam
 */
@Data
@NoArgsConstructor
public class ExchangeCoupon implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * <pre>
   * 字段名：单品换购价
   * 变量名：exchange_price
   * 是否必填：是
   * 类型：int
   * 描述：
   *  单品换购价，单位：分。
   *  特殊规则：取值范围 1 ≤ value ≤ 10000000
   *  示例值：100
   * </pre>
   */
  @SerializedName(value = "exchange_price")
  private Integer exchangePrice;

  /**
   * <pre>
   * 字段名：消费门槛
   * 变量名：transaction_minimum
   * 是否必填：是
   * 类型：int
   * 描述：
   *  消费门槛，单位：分。
   *  特殊规则：取值范围 1 ≤ value ≤ 10000000
   *  示例值：100
   * </pre>
   */
  @SerializedName(value = "transaction_minimum")
  private Integer transactionMinimum;
}
