package me.chanjar.weixin.mp.bean.card.enums;

/**
 * 会员信息类目半自定义名称，当开发者变更这类类目信息的value值时 可以选择触发系统模板消息通知用户。
 */
public enum CustomFieldNameType {

  FIELD_NAME_TYPE_LEVEL("等级"),
  FIELD_NAME_TYPE_COUPON("优惠券"),
  FIELD_NAME_TYPE_STAMP("印花"),
  FIELD_NAME_TYPE_DISCOUNT("折扣"),
  FIELD_NAME_TYPE_ACHIEVEMEN("成就"),
  FIELD_NAME_TYPE_MILEAGE("里程"),
  FIELD_NAME_TYPE_SET_POINTS("集点"),
  FIELD_NAME_TYPE_TIMS("次数");

  private final String description;

  CustomFieldNameType(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}
