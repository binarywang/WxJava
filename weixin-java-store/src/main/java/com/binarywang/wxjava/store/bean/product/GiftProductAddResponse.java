package com.binarywang.wxjava.store.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * 添加赠品响应
 *
 * @author GitHub Copilot
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GiftProductAddResponse extends WxStoreBaseResponse {

  private static final long serialVersionUID = -5971026809157610975L;

  /** 赠品商品ID */
  @JsonProperty("product_id")
  private String productId;
}
