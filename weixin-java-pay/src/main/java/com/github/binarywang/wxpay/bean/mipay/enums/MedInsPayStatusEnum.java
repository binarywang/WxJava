package com.github.binarywang.wxpay.bean.mipay.enums;

import com.google.gson.annotations.SerializedName;

/**
 * 医保支付状态枚举
 * <p>
 * 描述医保自费混合支付中医保部分的支付状态
 *
 * @author xgl
 * @date 2025/12/20
 */
public enum MedInsPayStatusEnum {
  /**
   * 未知的医保支付状态
   */
  @SerializedName("UNKNOWN_MED_INS_PAY_STATUS")
  UNKNOWN_MED_INS_PAY_STATUS,
  /**
   * 医保支付已创建
   */
  @SerializedName("MED_INS_PAY_CREATED")
  MED_INS_PAY_CREATED,
  /**
   * 医保支付成功
   */
  @SerializedName("MED_INS_PAY_SUCCESS")
  MED_INS_PAY_SUCCESS,
  /**
   * 医保支付已退款
   */
  @SerializedName("MED_INS_PAY_REFUND")
  MED_INS_PAY_REFUND,
  /**
   * 医保支付失败
   */
  @SerializedName("MED_INS_PAY_FAIL")
  MED_INS_PAY_FAIL,
  /**
   * 无需医保支付
   */
  @SerializedName("NO_MED_INS_PAY")
  NO_MED_INS_PAY
}
