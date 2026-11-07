package com.binarywang.wxjava.store.bean.product.link;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * 商品H5短链 结果
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductH5UrlResponse extends WxStoreBaseResponse {

  /** 商品H5短链 */
  @JsonProperty("product_h5url")
  private String productH5url;
}
