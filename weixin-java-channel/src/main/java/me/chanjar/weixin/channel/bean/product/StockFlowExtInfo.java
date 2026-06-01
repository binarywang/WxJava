package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 库存流水 额外信息
 *
 * @author <a href="https://github.com/features/copilot">GitHub Copilot</a>
 */
@Data
@NoArgsConstructor
public class StockFlowExtInfo implements Serializable {

  private static final long serialVersionUID = -1L;

  /** 归还的源库存子类型（仅对 op_type=7 生效） */
  @JsonProperty("unmove_from_stock_sub_type")
  private Integer unmoveFromStockSubType;

  /** 分配的目标库存子类型（仅对 op_type=6 生效） */
  @JsonProperty("move_to_stock_sub_type")
  private Integer moveToStockSubType;

  /**
   * 操作来源（仅对 op_type=1/2/3 生效）。
   * 2: API（开发者调用）；3: API（服务商代调用）；5: 手机端；6: web端
   */
  @JsonProperty("upload_source")
  private Integer uploadSource;

  /** 订单id（仅对 op_type=4/5 生效） */
  @JsonProperty("order_id")
  private Long orderId;

  /** 区域仓库id（仅对 stock_sub_type=3 生效） */
  @JsonProperty("out_warehouse_id")
  private String outWarehouseId;

  /** 限时抢购任务id（仅对 stock_sub_type=2 生效） */
  @JsonProperty("limited_discount_id")
  private Long limitedDiscountId;

  /** 达人的视频号finder_id（对 move_to_stock_sub_type=4 和 unmove_from_stock_sub_type=4 生效） */
  @JsonProperty("finder_id")
  private String finderId;
}
