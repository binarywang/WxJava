package me.chanjar.weixin.channel.bean.warehouse;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.warehouse.StockGetParam}。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Deprecated
public class StockGetParam implements Serializable {

  private static final long serialVersionUID = -4144913434092446664L;
  /** 商品ID */
  @JsonProperty("product_id")
  private String productId;

  /** skuID */
  @JsonProperty("sku_id")
  private String skuId;

  /** 外部仓库ID */
  @JsonProperty("out_warehouse_id")
  private String outWarehouseId;
}
