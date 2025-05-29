package me.chanjar.weixin.mp.bean.card.enums;

/**
 * 会员卡富文本字段类型
 *
 * @author yuanqixun
 * created on  2018-08-30
 */
public enum CardRichFieldType {
  FORM_FIELD_RADIO("自定义单选"),
  FORM_FIELD_SELECT("自定义选择项"),
  FORM_FIELD_CHECK_BOX("自定义多选");

  private final String description;

  CardRichFieldType(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}
