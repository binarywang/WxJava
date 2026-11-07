package com.binarywang.wxjava.store.bean.shop;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * 店铺基本信息响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class ShopInfoResponse extends WxStoreBaseResponse {

  @JsonProperty("info")
  private ShopInfo info;
}
