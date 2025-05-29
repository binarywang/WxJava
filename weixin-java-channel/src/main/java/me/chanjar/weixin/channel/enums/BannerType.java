package me.chanjar.weixin.channel.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 展示位类型
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum BannerType {

  /** 1	商品 */
  PRODUCT(1, "商品"),
  /** 3	视频号 */
  CHANNEL(3, "视频号"),
  /** 4	公众号 */
  MP(4, "公众号");

  ;

  private final int key;
  private final String value;

  BannerType(int key, String value) {
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
