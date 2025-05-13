package me.chanjar.weixin.mp.bean.card.enums;

public enum CardCodeType {

  CODE_TYPE_TEXT("文本"),
  CODE_TYPE_NONE("不显示任何码型"),
  CODE_TYPE_ONLY_BARCODE("仅显示一维码"),
  CODE_TYPE_ONLY_QRCODE("仅显示二维码"),
  CODE_TYPE_BARCODE("一维码"),
  CODE_TYPE_QRCODE("二维码");

  private final String description;

  CardCodeType(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}
