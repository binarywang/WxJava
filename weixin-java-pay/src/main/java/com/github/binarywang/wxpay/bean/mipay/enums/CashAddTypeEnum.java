package com.github.binarywang.wxpay.bean.mipay.enums;

import com.google.gson.annotations.SerializedName;

/**
 * 现金增加类型枚举
 * <p>
 * 描述医保自费混合支付中现金增加的类型
 *
 * @author xgl
 * @date 2025/12/20
 */
public enum CashAddTypeEnum {
  /**
   * 默认增加类型
   */
  @SerializedName("DEFAULT_ADD_TYPE")
  DEFAULT_ADD_TYPE,
  /**
   * 运费
   */
  @SerializedName("FREIGHT")
  FREIGHT,
  /**
   * 其他医疗费用
   */
  @SerializedName("OTHER_MEDICAL_EXPENSES")
  OTHER_MEDICAL_EXPENSES
}
