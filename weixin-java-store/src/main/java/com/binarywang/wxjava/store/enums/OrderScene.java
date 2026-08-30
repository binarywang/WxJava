package com.binarywang.wxjava.store.enums;

/**
 * 下单场景
 *
 * @author lizhengwu
 * @description
 */
public enum OrderScene {
  /**
   * 其他
   */
  OTHER(1, "其他"),
  /**
   * 直播间下单
   */
  LIVE(2, "直播间"),
  /**
   * 短视频
   */
  VIDEO(3, "短视频"),
  /**
   * 商品分享
   */
  SHARE(4, "商品分享"),
  /**
   * 商品橱窗主页
   */
  SHOW_CASE(5, "商品橱窗主页"),
  /**
   * 公众号文章商品卡片
   */
  ARTICLE_CARD(6, "公众号文章商品卡片"),
  ;

  private final int key;
  private final String value;

  OrderScene(int key, String value) {
    this.key = key;
    this.value = value;
  }

  public int getKey() {
    return key;
  }

  public String getValue() {
    return value;
  }

}
