package com.binarywang.wxjava.store.bean.compass.shop;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * 店铺人群数据 响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ShopSaleProfileDataResponse extends WxStoreBaseResponse {

  private static final long serialVersionUID = 8520148855114842741L;

  /** 店铺人群数据 */
  @JsonProperty("data")
  private ShopSaleProfileData data;

}
