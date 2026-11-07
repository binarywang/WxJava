package com.binarywang.wxjava.store.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * 赠品详情响应
 *
 * @author GitHub Copilot
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GiftProductGetResponse extends WxStoreBaseResponse {

  private static final long serialVersionUID = 5331169221157446692L;

  /** 赠品线上数据 */
  @JsonProperty("product")
  private GiftProductInfo product;

  /** 赠品草稿数据 */
  @JsonProperty("edit_product")
  private GiftProductInfo editProduct;
}
