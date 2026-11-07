package com.binarywang.wxjava.store.bean.shop;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * 店铺二维码 响应
 *
 * @author <a href="https://github.com/copilot">GitHub Copilot</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ShopQrCodeResponse extends WxStoreBaseResponse {

  /** 店铺二维码链接 */
  @JsonProperty("shop_qrcode")
  private String shopQrcode;
}
