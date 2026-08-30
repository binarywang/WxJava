package com.binarywang.wxjava.store.enums;

/**
 * 微信小店 用户优惠券状态
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
public enum UserCouponStatus {
  /** 100 生效中 */
  VALID(100, "生效中"),
  /** 101 已过期 */
  EXPIRED(101, "已过期"),
  /** 102 已使用 */
  USED(102, "已使用"),

  ;

  private final int key;
  private final String val;

  UserCouponStatus(int key, String val) {
    this.key = key;
    this.val = val;
  }

  public int getKey() {
    return key;
  }

  public String getVal() {
    return val;
  }
}
