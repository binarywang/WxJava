package com.github.binarywang.wxpay.bean.request;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <pre>
 * 境外微信支付统一下单请求参数对象.
 * 参考文档：https://pay.weixin.qq.com/doc/global/v3/zh/4013014223
 * </pre>
 *
 * @author Binary Wang
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class WxPayUnifiedOrderV3GlobalRequest extends WxPayUnifiedOrderV3Request implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * <pre>
   * 字段名：交易类型
   * 变量名：trade_type
   * 是否必填：是
   * 类型：string[1,16]
   * 描述：
   *  交易类型，取值如下：
   *  JSAPI--JSAPI支付
   *  NATIVE--Native支付
   *  APP--APP支付
   *  H5--H5支付
   *  示例值：JSAPI
   * </pre>
   */
  @SerializedName(value = "trade_type")
  private String tradeType;

  /**
   * <pre>
   * 字段名：商户类目
   * 变量名：merchant_category_code
   * 是否必填：是
   * 类型：string[1,32]
   * 描述：
   *  商户类目，境外商户必填
   *  示例值：5812
   * </pre>
   */
  @SerializedName(value = "merchant_category_code")
  private String merchantCategoryCode;
}