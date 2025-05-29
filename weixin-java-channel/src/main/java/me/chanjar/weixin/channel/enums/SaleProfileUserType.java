package me.chanjar.weixin.channel.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 带货人群用户类型
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum SaleProfileUserType {

  /**
   * 商品曝光用户
   */
  PRODUCT_IMPRESSION_USER(1, "商品曝光用户"),
  /**
   * 商品点击用户
   */
  PRODUCT_CLICK_USER(2, "商品点击用户"),
  /**
   * 购买用户
   */
  PURCHASING_USER(3, "购买用户"),
  /**
   * 首购用户
   */
  FIRST_PURCHASE_USER(4, "首购用户"),
  /**
   * 复购用户
   */
  REPURCHASE_USER(5, "复购用户"),
  /**
   * 直播观看用户
   */
  LIVE_WATCHER_USER(6, "直播观看用户"),

  ;

  private final Integer key;
  private final String value;

  SaleProfileUserType(Integer key, String value) {
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
