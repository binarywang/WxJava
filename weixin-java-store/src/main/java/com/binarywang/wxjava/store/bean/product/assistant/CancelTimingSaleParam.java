package com.binarywang.wxjava.store.bean.product.assistant;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 取消商品开售参数。
 */
@Data
@NoArgsConstructor
public class CancelTimingSaleParam implements Serializable {

  private static final long serialVersionUID = -3750831026611057323L;

  /** 商品 ID。 */
  @JsonProperty("product_id")
  private String productId;
}
