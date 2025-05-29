package me.chanjar.weixin.mp.bean.card.enums;

public enum CardSceneType {
  SCENE_NEAR_BY("附近"),
  SCENE_MENU("自定义菜单"),
  SCENE_QRCODE("二维码"),
  SCENE_ARTICLE("公众号文章"),
  SCENE_H5("H5"),
  SCENE_IVR("自动回复"),
  SCENE_CARD_CUSTOM_CELL("卡券自定义cell");

  private final String description;

  CardSceneType(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}
