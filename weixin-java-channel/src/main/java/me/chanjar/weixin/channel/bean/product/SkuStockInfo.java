package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品库存
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class SkuStockInfo implements Serializable {

  private static final long serialVersionUID = 4719729125885685958L;

  /** 通用库存数量 */
  @JsonProperty("normal_stock_num")
  private Integer normalStockNum;

  /** 限时抢购库存数量 */
  @JsonProperty("limited_discount_stock_num")
  private Integer limitedDiscountStockNum;

  /** 区域库存 */
  @JsonProperty("warehouse_stocks")
  private List<WarehouseStockInfo> warehouseStocks;

  /**
   * 普通查询：库存总量：通用库存数量 + 限时抢购库存数量 + 区域库存总量
   * 批量查询：库存总量：通用库存数量 + 限时抢购库存数量 + 区域库存数量 + 达人专属计划营销库存数量
   */
  @JsonProperty("total_stock_num")
  private Integer totalStockNum;

  /** 达人专属计划营销库存数量 */
  @JsonProperty("finder_stock_num")
  private Integer finderTotalNum;
}
