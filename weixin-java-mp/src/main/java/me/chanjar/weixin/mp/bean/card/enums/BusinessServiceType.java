package me.chanjar.weixin.mp.bean.card.enums;

/**
 * 商户提供服务类型
 */
public enum BusinessServiceType {
  BIZ_SERVICE_DELIVER("外卖服务"),
  BIZ_SERVICE_FREE_PARK("停车位"),
  BIZ_SERVICE_WITH_PET("可带宠物"),
  BIZ_SERVICE_FREE_WIFI("可带宠物");

  private final String description;

  BusinessServiceType(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}
