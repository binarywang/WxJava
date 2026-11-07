package com.binarywang.wxjava.store.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 售后类型
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AfterSaleType {
  /** 1 仅退款 */
  REFUND_ONLY("REFUND", "仅退款"),
  /** 2 退货退款 */
  REFUND_GOODS("RETURN", "退货退款");

  private final String key;
  private final String value;

  AfterSaleType(String key, String value) {
    this.key = key;
    this.value = value;
  }

  public String getKey() {
    return key;
  }

  public String getValue() {
    return value;
  }
}
