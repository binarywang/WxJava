package com.binarywang.wxjava.store.bean.compass.shop;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * 获取电商概览数据响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ShopOverallResponse extends WxStoreBaseResponse {

  private static final long serialVersionUID = 1632800741359642057L;

  /**
   * 电商概览数据
   */
  @JsonProperty("data")
  private ShopOverall data;

}
