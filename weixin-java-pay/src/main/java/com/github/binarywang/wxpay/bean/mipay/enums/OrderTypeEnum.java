package com.github.binarywang.wxpay.bean.mipay.enums;

import com.google.gson.annotations.SerializedName;

/**
 * 订单类型枚举
 * <p>
 * 描述医保自费混合支付的订单类型
 * 文档地址：https://pay.weixin.qq.com/doc/v3/partner/4012503131
 *
 * @author xgl
 * @date 2025/12/20
 */
public enum OrderTypeEnum {

  /**
   * 未知类型，会被拦截
   */
  @SerializedName("UNKNOWN_ORDER_TYPE")
  UNKNOWN_ORDER_TYPE,

  /**
   * 挂号支付
   */
  @SerializedName("REG_PAY")
  REG_PAY,

  /**
   * 诊间支付
   */
  @SerializedName("DIAG_PAY")
  DIAG_PAY,

  /**
   * 新冠检测费用（核酸）
   */
  @SerializedName("COVID_EXAM_PAY")
  COVID_EXAM_PAY,

  /**
   * 住院费支付
   */
  @SerializedName("IN_HOSP_PAY")
  IN_HOSP_PAY,

  /**
   * 药店支付
   */
  @SerializedName("PHARMACY_PAY")
  PHARMACY_PAY,

  /**
   * 保险费支付
   */
  @SerializedName("INSURANCE_PAY")
  INSURANCE_PAY,

  /**
   * 互联网医院挂号支付
   */
  @SerializedName("INT_REG_PAY")
  INT_REG_PAY,

  /**
   * 互联网医院复诊支付
   */
  @SerializedName("INT_RE_DIAG_PAY")
  INT_RE_DIAG_PAY,

  /**
   * 互联网医院处方支付
   */
  @SerializedName("INT_RX_PAY")
  INT_RX_PAY,

  /**
   * 新冠抗原检测
   */
  @SerializedName("COVID_ANTIGEN_PAY")
  COVID_ANTIGEN_PAY,

  /**
   * 药费支付
   */
  @SerializedName("MED_PAY")
  MED_PAY
}
