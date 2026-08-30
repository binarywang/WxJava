package me.chanjar.weixin.channel.bean.product.stock;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 库存流水额外信息。
 */
@Data
@NoArgsConstructor
@Deprecated
public class StockFlowExtInfo implements Serializable {

  private static final long serialVersionUID = 1170328051641116647L;

  /** 归还的源库存子类型。 */
  @JsonProperty("unmove_from_stock_sub_type")
  private Integer unmoveFromStockSubType;

  /** 分配的目标库存子类型。 */
  @JsonProperty("move_to_stock_sub_type")
  private Integer moveToStockSubType;

  /** 操作来源。 */
  @JsonProperty("upload_source")
  private Integer uploadSource;

  /** 订单 ID。 */
  @JsonProperty("order_id")
  private String orderId;

  /** 区域仓库 ID。 */
  @JsonProperty("out_warehouse_id")
  private String outWarehouseId;

  /** 限时抢购任务 ID。 */
  @JsonProperty("limited_discount_id")
  private String limitedDiscountId;

  /** 达人的视频号 finder_id。 */
  @JsonProperty("finder_id")
  private String finderId;
}
