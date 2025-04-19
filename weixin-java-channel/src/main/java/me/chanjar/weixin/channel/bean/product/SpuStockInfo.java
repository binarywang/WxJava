package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * SPU库存信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class SpuStockInfo implements Serializable {

  /** 商品ID */
  @JsonProperty("product_id")
  protected String productId;

  /** sku库存 */
  @JsonProperty("sku_stock")
  private List<SkuStockInfo> skuStock;
}
