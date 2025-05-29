package me.chanjar.weixin.channel.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 视频号助手 直播数据维度类型
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum DimensionType {

  /**
   * 一级渠道
   */
  PRIMARY_CHANNEL(1, "一级渠道"),
  /**
   * 年龄段
   */
  AGE(2, "年龄段"),
  /**
   * 性别
   */
  SEX(3, "性别"),
  /**
   * 关注关系
   */
  FOLLOW(5, "关注关系"),
  /**
   * 二级渠道
   */
  SECONDARY_CHANNEL(7, "二级渠道"),
  /**
   * 策略人群
   */
  CATE(9, "策略人群"),
  /**
   * 省级行政区
   */
  PROVINCE(10, "省级行政区"),
  /**
   * 地级行政区
   */
  CITY(11, "地级行政区"),
  /**
   * 消费者商品类目偏好
   */
  ECOM_USER_LEVEL(12, "消费者商品类目偏好"),
  /**
   * 客单价区间
   */
  GMV_PER_CNT(13, "客单价区间"),
//  /**
//   * 关注关系
//   */
//  FOLLOW(15, "关注关系"),
  /**
   * 流量类型（自然流量、直播加热、广告投流）
   */
  FLOW(16, "流量类型（自然流量、直播加热、广告投流）"),

  ;

  private final Integer key;
  private final String value;

  DimensionType(Integer key, String value) {
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
