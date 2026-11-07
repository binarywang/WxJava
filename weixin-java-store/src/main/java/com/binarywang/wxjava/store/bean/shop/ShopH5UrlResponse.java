package com.binarywang.wxjava.store.bean.shop;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * 店铺H5链接 响应
 *
 * @author <a href="https://github.com/copilot">GitHub Copilot</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ShopH5UrlResponse extends WxStoreBaseResponse {

  /** 店铺H5链接 */
  @JsonProperty("shop_h5url")
  private String shopH5url;
}
