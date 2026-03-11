package com.github.binarywang.wxpay.bean.subscriptionbilling;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 预约扣费金额信息
 * <pre>
 *   文档地址：https://pay.weixin.qq.com/doc/v3/merchant/4012161105
 * </pre>
 *
 * @author Binary Wang
 */
@Data
@NoArgsConstructor
public class SubscriptionAmount implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * <pre>
   * 字段名：总金额
   * 变量名：total
   * 是否必填：是
   * 类型：int
   * 描述：
   *  订单总金额，单位为分
   *  示例值：100
   * </pre>
   */
  @SerializedName("total")
  private Integer total;

  /**
   * <pre>
   * 字段名：货币类型
   * 变量名：currency
   * 是否必填：否
   * 类型：string(16)
   * 描述：
   *  CNY：人民币，境内商户号仅支持人民币
   *  示例值：CNY
   * </pre>
   */
  @SerializedName("currency")
  private String currency;
}