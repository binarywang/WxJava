package me.chanjar.weixin.channel.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 分享场景
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ShareScene {
  /** 1 直播间 */
  LIVE_ROOM(1, "直播间"),
  /** 2 橱窗 */
  WINDOW(2, "橱窗"),
  /** 3 短视频 */
  SHORT_VIDEO(3, "短视频"),
  /** 4 视频号主页 */
  CHANNEL_HOME(4, "视频号主页"),
  /** 5 商品详情页 */
  PRODUCT_DETAIL(5, "商品详情页"),
  /** 6	带商品的公众号文章 */
  MP_ARTICLE(6, "带商品的公众号文章"),
  /** 7	商品链接 */
  PRODUCT_LINK(7, "商品链接"),
  /** 8	商品二维码 */
  PRODUCT_QR_CODE(8, "商品二维码"),
  /** 9	商品口令 */
  PRODUCT_TAG_LINK(9, "商品口令"),
  /** 12 视频号橱窗链接 */
  WINDOW_LINK(12, "视频号橱窗链接"),
  /** 13 视频号橱窗二维码 */
  WINDOW_QR_CODE(13, "视频号橱窗二维码"),
  ;


  private final Integer key;
  private final String value;

  ShareScene(Integer key, String value) {
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
