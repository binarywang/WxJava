package me.chanjar.weixin.channel.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 视频号小店 优惠券 推广类型
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PromoteType {
  /** 1	小店内推广 */
  PROMOTE_TYPE_SHOP(1, "小店内推广"),
  /** 9	会员券 */
  MEMBER(9, "会员券"),
  /** 10	会员开卡礼券 */
  MEMBER_CARD(10, "会员开卡礼券"),

  ;

  private final int key;
  private final String val;

  PromoteType(int key, String val) {
    this.key = key;
    this.val = val;
  }

  public int getKey() {
    return key;
  }

  public String getVal() {
    return val;
  }
}
