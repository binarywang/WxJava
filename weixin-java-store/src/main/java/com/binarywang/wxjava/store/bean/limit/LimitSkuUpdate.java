package com.binarywang.wxjava.store.bean.limit;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 限时抢购任务的 SKU 更新信息。
 */
@Data
@NoArgsConstructor
public class LimitSkuUpdate implements Serializable {

  private static final long serialVersionUID = 4209672674401016015L;

  /** SKU 所属商品 ID。 */
  @JsonProperty("product_id")
  private String productId;

  /** SKU ID。 */
  @JsonProperty("sku_id")
  private String skuId;

  /** SKU 抢购价格，单位为分。 */
  @JsonProperty("sale_price")
  private Integer salePrice;

  /** 参与抢购的商品库存。 */
  @JsonProperty("sale_stock")
  private Integer saleStock;
}
