package com.github.binarywang.wxpay.bean.mipay.enums;

import com.google.gson.annotations.SerializedName;

/**
 * 自费支付状态枚举
 * <p>
 * 描述医保自费混合支付中自费部分的支付状态
 *
 * @author xgl
 * @date 2025/12/20
 */
public enum SelfPayStatusEnum {
  /**
   * 未知的自费支付状态
   */
  @SerializedName("UNKNOWN_SELF_PAY_STATUS")
  UNKNOWN_SELF_PAY_STATUS,
  /**
   * 自费支付已创建
   */
  @SerializedName("SELF_PAY_CREATED")
  SELF_PAY_CREATED,
  /**
   * 自费支付成功
   */
  @SerializedName("SELF_PAY_SUCCESS")
  SELF_PAY_SUCCESS,
  /**
   * 自费支付已退款
   */
  @SerializedName("SELF_PAY_REFUND")
  SELF_PAY_REFUND,
  /**
   * 自费支付失败
   */
  @SerializedName("SELF_PAY_FAIL")
  SELF_PAY_FAIL,
  /**
   * 无需自费支付
   */
  @SerializedName("NO_SELF_PAY")
  NO_SELF_PAY
}
