package me.chanjar.weixin.mp.bean.card.enums;

public enum CardStatusType {
  CARD_STATUS_NOT_VERIFY("待审核"),
  CARD_STATUS_VERIFY_FAIL("审核失败"),
  CARD_STATUS_VERIFY_OK("通过审核"),
  CARD_STATUS_DELETE("卡券被商户删除"),
  CARD_STATUS_DISPATCH("在公众平台投放过的卡券");

  private final String description;

  CardStatusType(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}
