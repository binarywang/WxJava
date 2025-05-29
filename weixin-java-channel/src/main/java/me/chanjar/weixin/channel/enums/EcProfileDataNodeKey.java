package me.chanjar.weixin.channel.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 视频号助手 用户群体数据节点键
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum EcProfileDataNodeKey {

  /**
   * 性别分布
   */
  SEX("sex_distribution", "性别分布"),
  /**
   * 年龄分布
   */
  AGE("age_distribution", "年龄分布"),
  /**
   * 省份分布
   */
  PROVINCE("province_distribution", "省份分布"),
  /**
   * 城市分布
   */
  CITY("city_distribution", "城市分布"),
  /**
   * 关注关系分布
   */
  FOLLOW("follow_distribution", "关注关系分布"),
  /**
   * 策略人群分布
   */
  CATE("cate_distribution", "策略人群分布"),
  /**
   * 商品类目偏好分布
   */
  ECOM_USER_LEVEL("ecom_user_level_distribution", "商品类目偏好分布"),
  /**
   * 平均客单价分布
   */
  GMV_PER_CNT("gmv_per_cnt_distribution", "平均客单价分布"),

  ;

  private final String key;
  private final String value;

  EcProfileDataNodeKey(String key, String value) {
    this.key = key;
    this.value = value;
  }

  public String getKey() {
    return key;
  }

  public String getValue() {
    return value;
  }

}
