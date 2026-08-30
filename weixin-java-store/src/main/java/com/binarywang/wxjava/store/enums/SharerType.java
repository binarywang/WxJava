package com.binarywang.wxjava.store.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 分享员类型
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum SharerType {
  /** 0 普通分享员 */
  NORMAL(0, "普通分享员"),
  /** 1 企业分享员 */
  ENTERPRISE(1, "企业分享员"),

  ;


  private final Integer key;
  private final String value;

  SharerType(Integer key, String value) {
    this.key = key;
    this.value = value;
  }

  public Integer getKey() {
    return key;
  }

  public String getValue() {
    return value;
  }
}
