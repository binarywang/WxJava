package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 区域库存
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class WarehouseStockInfo implements Serializable {

  private static final long serialVersionUID = 3184902895765107425L;

  /** 区域库存外部id */
  @JsonProperty("out_warehouse_id")
  private String outWarehouseId;

  /** 区域库存数量 */
  @JsonProperty("num")
  private Integer num;

  /** 区域库存的锁定库存（已下单未支付的库存）数量 */
  @JsonProperty("lock_stock")
  private Integer lockStock;
}
