package com.binarywang.wxjava.store.bean.product.link;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * 商品二维码 结果
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductQrCodeResponse extends WxStoreBaseResponse {

  /** 商品二维码 */
  @JsonProperty("product_qrcode")
  private String productQrcode;
}
