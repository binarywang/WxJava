package com.github.binarywang.wxpay.bean.mipay.enums;

import com.google.gson.annotations.SerializedName;

/**
 * 现金减少类型枚举
 * <p>
 * 描述医保自费混合支付中现金减少的类型
 *
 * @author xgl
 * @date 2025/12/20
 */
public enum CashReduceTypeEnum {
  /**
   * 默认减少类型
   */
  @SerializedName("DEFAULT_REDUCE_TYPE")
  DEFAULT_REDUCE_TYPE,
  /**
   * 医院减免
   */
  @SerializedName("HOSPITAL_REDUCE")
  HOSPITAL_REDUCE,
  /**
   * 药店折扣
   */
  @SerializedName("PHARMACY_DISCOUNT")
  PHARMACY_DISCOUNT,
  /**
   * 折扣优惠
   */
  @SerializedName("DISCOUNT")
  DISCOUNT,
  /**
   * 预付费抵扣
   */
  @SerializedName("PRE_PAYMENT")
  PRE_PAYMENT,
  /**
   * 押金扣除
   */
  @SerializedName("DEPOSIT_DEDUCTION")
  DEPOSIT_DEDUCTION
}
