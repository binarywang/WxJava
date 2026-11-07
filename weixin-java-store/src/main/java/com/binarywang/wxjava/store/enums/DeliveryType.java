package com.binarywang.wxjava.store.enums;

/**
 * 快递类型
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
public enum DeliveryType {
  /** 1 自寄快递 */
  SELF_DELIVERY(1, "自寄快递"),
  /** 2 在线签约快递单 */
  ONLINE_DELIVERY(2, "在线签约快递单"),
  /** 3 虚拟商品无需物流发货 */
  VIRTUAL_DELIVERY(3, "虚拟商品无需物流发货"),
  /** 4 在线快递散单 */
  ONLINE_DELIVERY_SCATTER(4, "在线快递散单");

  private final Integer key;
  private final String value;

  DeliveryType(Integer key, String value) {
    this.key = key;
    this.value = value;
  }

  public static DeliveryType getDeliveryType(Integer key) {
    for (DeliveryType deliveryType : DeliveryType.values()) {
      if (deliveryType.getKey().equals(key)) {
        return deliveryType;
      }
    }
    return null;
  }

  public Integer getKey() {
    return key;
  }

  public String getValue() {
    return value;
  }
}
