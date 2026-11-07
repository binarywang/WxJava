package com.binarywang.wxjava.store.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 微信小店 售后原因
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AfterSalesReason {
  /** 拍错/多拍 */
  INCORRECT_SELECTION("INCORRECT_SELECTION", "拍错/多拍"),
  /** 不想要了 */
  NO_LONGER_WANT("NO_LONGER_WANT", "不想要了"),
  /** 无快递信息 */
  NO_EXPRESS_INFO("NO_EXPRESS_INFO", "无快递信息"),
  /** 包裹为空 */
  EMPTY_PACKAGE("EMPTY_PACKAGE", "包裹为空"),
  /** 已拒签包裹 */
  REJECT_RECEIVE_PACKAGE("REJECT_RECEIVE_PACKAGE", "已拒签包裹"),
  /** 快递长时间未送达 */
  NOT_DELIVERED_TOO_LONG("NOT_DELIVERED_TOO_LONG", "快递长时间未送达了"),
  /** 与商品描述不符 */
  NOT_MATCH_PRODUCT_DESC("NOT_MATCH_PRODUCT_DESC", "与商品描述不符"),
  /** 质量问题 */
  QUALITY_ISSUE("QUALITY_ISSUE", "质量问题"),
  /** 卖家发错货 */
  SEND_WRONG_GOODS("SEND_WRONG_GOODS", "卖家发错货"),
  /** 三无产品 */
  THREE_NO_PRODUCT("THREE_NO_PRODUCT", "三无产品"),
  /** 假冒产品 */
  FAKE_PRODUCT("FAKE_PRODUCT", "假冒产品"),
  /** 其它 */
  OTHERS("OTHERS", "其它"),
  ;

  private final String key;
  private final String value;

  AfterSalesReason(String key, String value) {
    this.key = key;
    this.value = value;
  }

  public static AfterSalesReason getByKey(String key) {
    for (AfterSalesReason reason : AfterSalesReason.values()) {
      if (reason.getKey().equals(key)) {
        return reason;
      }
    }
    // 找不到就返回其他了
    return OTHERS;
  }

  public String getKey() {
    return key;
  }

  public String getValue() {
    return value;
  }
}
