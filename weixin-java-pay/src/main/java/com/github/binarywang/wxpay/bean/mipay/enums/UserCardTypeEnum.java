package com.github.binarywang.wxpay.bean.mipay.enums;

import com.google.gson.annotations.SerializedName;

/**
 * 用户证件类型枚举
 * <p>
 * 描述医保自费混合支付中用户的证件类型
 *
 * @author xgl
 * @date 2025/12/20
 */
public enum UserCardTypeEnum {
  /**
   * 未知的用户证件类型
   */
  @SerializedName("UNKNOWN_USER_CARD_TYPE")
  UNKNOWN_USER_CARD_TYPE,
  /**
   * 居民身份证
   */
  @SerializedName("ID_CARD")
  ID_CARD,
  /**
   * 户口本
   */
  @SerializedName("HOUSEHOLD_REGISTRATION")
  HOUSEHOLD_REGISTRATION,
  /**
   * 外国护照
   */
  @SerializedName("FOREIGNER_PASSPORT")
  FOREIGNER_PASSPORT,
  /**
   * 台湾居民来往大陆通行证
   */
  @SerializedName("MAINLAND_TRAVEL_PERMIT_FOR_TW")
  MAINLAND_TRAVEL_PERMIT_FOR_TW,
  /**
   * 澳门居民来往大陆通行证
   */
  @SerializedName("MAINLAND_TRAVEL_PERMIT_FOR_MO")
  MAINLAND_TRAVEL_PERMIT_FOR_MO,
  /**
   * 香港居民来往大陆通行证
   */
  @SerializedName("MAINLAND_TRAVEL_PERMIT_FOR_HK")
  MAINLAND_TRAVEL_PERMIT_FOR_HK,
  /**
   * 外国人永久居留身份证
   */
  @SerializedName("FOREIGN_PERMANENT_RESIDENT")
  FOREIGN_PERMANENT_RESIDENT
}
