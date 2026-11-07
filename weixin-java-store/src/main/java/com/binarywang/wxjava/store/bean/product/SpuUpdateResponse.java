package com.binarywang.wxjava.store.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * 商品信息 响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SpuUpdateResponse extends WxStoreBaseResponse {

  private static final long serialVersionUID = -7072796795527767292L;

  /** 商品信息 */
  @JsonProperty("data")
  private SpuInfo data;

}
