package com.binarywang.wxjava.store.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 微信小店 优惠券 推广类型
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum CouponType {
  /** 1 商品条件折券 */
  C_1(1, "商品条件折券"),
  /** 2 商品满减券 */
  C_2(2, "商品满减券"),
  /** 3 商品统一折扣券 */
  C_3(3, "商品统一折扣券"),
  /** 4 商品直减券 */
  C_4(4, "商品直减券"),
  /** 101 店铺条件折扣券 */
  C_101(101, "店铺条件折扣券"),
  /** 102 店铺满减券 */
  C_102(102, "店铺满减券"),
  /** 103 店铺统一折扣券 */
  C_103(103, "店铺统一折扣券"),
  /** 104 店铺直减券 */
  C_104(104, "店铺直减券"),
  ;

  private final int key;
  private final String val;

  CouponType(int key, String val) {
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
