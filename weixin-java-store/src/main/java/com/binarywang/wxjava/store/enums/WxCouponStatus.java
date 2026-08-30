package com.binarywang.wxjava.store.enums;

/**
 * 微信小店 优惠券状态
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
public enum WxCouponStatus {
  /** 1 初始 */
  INIT(1, "初始"),
  /** 2 生效 */
  VALID(2, "生效"),
  /** 4 已作废 */
  INVALID(4, "已作废"),
  /** 5 删除 */
  DELETE(5, "删除"),

  ;

  private final int key;
  private final String val;

  WxCouponStatus(int key, String val) {
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
