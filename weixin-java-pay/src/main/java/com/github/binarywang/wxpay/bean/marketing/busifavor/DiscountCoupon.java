package com.github.binarywang.wxpay.bean.marketing.busifavor;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 折扣券
 * <pre>
 *   文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter9_2_1.shtml
 * </pre>
 *
 * @author yujam
 */
@Data
@NoArgsConstructor
public  class DiscountCoupon implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * <pre>
   * 字段名：折扣比例
   * 变量名：discount_percent
   * 是否必填：是
   * 类型：int
   * 描述：
   *  折扣百分比，例如：88为八八折。
   *  示例值：88
   * </pre>
   */
  @SerializedName(value = "discount_percent")
  private Integer discountPercent;

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
