package com.github.binarywang.wxpay.bean.mipay.enums;

import com.google.gson.annotations.SerializedName;

/**
 * 混合支付状态枚举
 * <p>
 * 描述医保自费混合支付的整体状态
 *
 * @author xgl
 * @date 2025/12/20
 */
public enum MixPayStatusEnum {
  /**
   * 未知的混合支付状态
   */
  @SerializedName("UNKNOWN_MIX_PAY_STATUS")
  UNKNOWN_MIX_PAY_STATUS,
  /**
   * 混合支付已创建
   */
  @SerializedName("MIX_PAY_CREATED")
  MIX_PAY_CREATED,
  /**
   * 混合支付成功
   */
  @SerializedName("MIX_PAY_SUCCESS")
  MIX_PAY_SUCCESS,
  /**
   * 混合支付已退款
   */
  @SerializedName("MIX_PAY_REFUND")
  MIX_PAY_REFUND,
  /**
   * 混合支付失败
   */
  @SerializedName("MIX_PAY_FAIL")
  MIX_PAY_FAIL
}
