package me.chanjar.weixin.channel.bean.product.stock;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 库存流水信息。
 */
@Data
@NoArgsConstructor
public class StockFlowInfo implements Serializable {

  private static final long serialVersionUID = 4094168882102603379L;

  /** 操作数量。 */
  @JsonProperty("amount")
  private Integer amount;

  /** 操作前数量。 */
  @JsonProperty("beginning_amount")
  private Integer beginningAmount;

  /** 操作后数量。 */
  @JsonProperty("ending_amount")
  private Integer endingAmount;

  /** 库存子类型。 */
  @JsonProperty("stock_sub_type")
  private Integer stockSubType;

  /** 库存事件类型。 */
  @JsonProperty("op_type")
  private Integer opType;

  /** 流水发生时间，秒级时间戳。 */
  @JsonProperty("update_time")
  private Long updateTime;

  /** 额外信息。 */
  @JsonProperty("ext_info")
  private StockFlowExtInfo extInfo;
}
