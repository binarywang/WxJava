package me.chanjar.weixin.channel.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 视频号助手 直播分布场景类型
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum LiveDistributionSceneType {

  /**
   * 商品曝光
   */
  PRODUCT_IMPRESSION(6, "商品曝光"),
  /**
   * 直播间曝光次数
   */
  LIVE_ROOM_IMPRESSION_PV(7, "直播间曝光次数"),
  /**
   * 商品点击次数
   */
  PRODUCT_CLICK_PV(8, "商品点击次数"),
  /**
   * 创建订单数按渠道统计
   */
  CHANNEL_TOTAL_CREATE_PV(9, "创建订单数按渠道统计"),
  /**
   * 成交订单数按渠道统计
   */
  CHANNEL_TOTAL_PAY_PV(10, "成交订单数按渠道统计"),

  ;

  private final Integer key;
  private final String value;

  LiveDistributionSceneType(Integer key, String value) {
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
