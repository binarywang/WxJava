package com.binarywang.wxjava.store.enums;

/**
 * 微信小店 订单状态
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
public enum WxOrderStatus {
  /** 10 待付款 */
  UNPAID(10, "待付款"),
  /** 20 待发货（已付款/用户已付尾款） */
  PAID(20, "待发货"),
  /** 21 部分发货 */
  PART_DELIVERY(21, "部分发货"),
  /** 30 待收货 */
  DELIVERY(30, "待收货"),
  /** 100 完成 */
  COMPLETED(100, "已完成"),
  /** 190	商品超卖商家取消订单 */
  UNPAID_CANCEL(190, "已取消"),
  /** 200 全部商品售后之后，订单取消 */
  ALL_AFTER_SALE(200, "已取消"),
  /** 250 用户主动取消/待付款超时取消/商家取消 */
  CANCEL(250, "已取消");

  private final int key;

  private final String val;

  WxOrderStatus(int key, String val) {
    this.key = key;
    this.val = val;
  }

  public int getKey() {
    return key;
  }

  public String getVal() {
    return val;
  }

  /**
   * 获取状态中文
   *
   * @param key 状态码
   * @return 状态
   */
  public static String getStatusStr(Integer key) {
    if (key == null) {
      return "未知";
    }
    for (WxOrderStatus status : WxOrderStatus.values()) {
      if (key.equals(status.getKey())) {
        return status.getVal();
      }
    }
    return String.valueOf(key);
  }

  /**
   * 判断是否在取消状态
   *
   * @param key key
   * @return boolean
   */
  public static boolean isCancel(Integer key) {
    if (key == null) {
      return false;
    }
    return key.equals(UNPAID_CANCEL.key) || key.equals(ALL_AFTER_SALE.key) || key.equals(CANCEL.key);
  }
}
