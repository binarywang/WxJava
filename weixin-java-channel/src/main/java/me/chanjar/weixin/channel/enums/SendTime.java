package me.chanjar.weixin.channel.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 视频号小店 发货时间
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum SendTime {
//    /** 4小时内发货 */
//    FOUR_HOUR("SendTime_FOUR_HOUR", "4小时内发货"),
//    /** 8小时内发货 */
//    EIGHT_HOUR("SendTime_EIGHT_HOUR", "8小时内发货"),
//    /** 12小时内发货 */
//    TWELVE_HOUR("SendTime_TWELVE_HOUR", "12小时内发货"),
//    /** 16小时内发货 */
//    SIXTEEN_HOUR("SendTime_SIXTEEN_HOUR", "16小时内发货"),
//    /** 20小时内发货 */
//    TWENTY_HOUR("SendTime_TWENTY_HOUR", "20小时内发货"),
  /** 24小时内发货 */
  TWENTYFOUR_HOUR("SendTime_TWENTYFOUR_HOUR", "24小时内发货"),
  /** 48小时内发货 */
  FOUTYEIGHT_HOUR("SendTime_FOUTYEIGHT_HOUR", "48小时内发货"),
  /**
   * 3天内发货
   * @deprecated 已不支持，微信小店发货管理规则调整
   */
  @Deprecated
  THREE_DAY("SendTime_THREE_DAY", "3天内发货"),
//    /** 5天内发货 */
//    FIVE_DAY("SendTime_FIVE_DAY", "5天内发货"),
//    /** 7天内发货 */
//    SEVEN_DAY("SendTime_SEVEN_DAY", "7天内发货"),
//    /** 10天内发货 */
//    TEN_DAY("SendTime_TEN_DAY", "10天内发货"),
//    /** 12天内发货 */
//    TWELVE_DAY("SendTime_TWELVE_DAY", "12天内发货"),
//    /** 14天内发货 */
//    FOUTEEN_DAY("SendTime_FOUTEEN_DAY", "14天内发货"),
//    /** 16天内发货 */
//    SIXTEEN_DAY("SendTime_SIXTEEN_DAY", "16天内发货"),
//    /** 20天内发货 */
//    TWENTY_DAY("SendTime_TWENTY_DAY", "20天内发货"),
//    /** 25天内发货 */
//    TWENTYFIVE_DAY("SendTime_TWENTYFIVE_DAY", "25天内发货"),
//    /** 30天内发货 */
//    THIRY_DAY("SendTime_THIRY_DAY", "30天内发货"),
//    /** 35天内发货 */
//    THIRYFIVE_DAY("SendTime_THIRYFIVE_DAY", "35天内发货"),
//    /** 45天内发货 */
//    FOURTYFIVE_DAY("SendTime_FOURTYFIVE_DAY", "45天内发货"),
  ;

  private final String key;
  private final String value;

  SendTime(String key, String value) {
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
