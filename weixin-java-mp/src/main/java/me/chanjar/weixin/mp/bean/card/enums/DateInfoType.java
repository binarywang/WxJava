package me.chanjar.weixin.mp.bean.card.enums;

public enum DateInfoType {
  DATE_TYPE_PERMANENT("永久有效类型"),
  DATE_TYPE_FIX_TIME_RANGE("固定日期"),
  DATE_TYPE_FIX_TERM("固定时长");

  private final String description;

  DateInfoType(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}
