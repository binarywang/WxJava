package me.chanjar.weixin.channel.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 视频号助手 直播分布流量类型
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum LiveDistributionFlowType {

  /**
   * 无效值
   */
  INVALID(0, "无效值"),
  /**
   * 自然流量
   */
  NATURAL(1, "自然流量"),
  /**
   * 加热流量
   */
  PROMOTE(2, "加热流量"),
  /**
   * 广告流量
   */
  ADS(3, "广告流量"),
  /**
   * 公域流量
   */
  COMMON_DOMAIN(4, "公域流量"),
  /**
   * 私域流量
   */
  PRIVATE_DOMAIN(5, "私域流量"),

  ;

  private final Integer key;
  private final String value;

  LiveDistributionFlowType(Integer key, String value) {
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
