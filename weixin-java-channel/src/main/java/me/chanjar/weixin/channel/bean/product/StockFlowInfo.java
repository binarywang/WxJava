package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 单条库存流水信息
 *
 * @author <a href="https://github.com/features/copilot">GitHub Copilot</a>
 */
@Data
@NoArgsConstructor
public class StockFlowInfo implements Serializable {

  private static final long serialVersionUID = -1L;

  /** 操作数量 */
  @JsonProperty("amount")
  private Integer amount;

  /** 开始数量 */
  @JsonProperty("beginning_amount")
  private Integer beginningAmount;

  /** 结束数量 */
  @JsonProperty("ending_amount")
  private Integer endingAmount;

  /**
   * 本次开始结束数量的库存子类型。
   * 1: 普通/通用库存；2: 限时抢购库存；3: 自营区域库存；4: 达人专属计划营销库存；
   * 5: 直播预热专属库存；6: 赠品库存；7: B2C活动库存；8: 活动库存；
   * 9: 限时抢购2.0库存；10: 不可售库存
   */
  @JsonProperty("stock_sub_type")
  private Integer stockSubType;

  /**
   * 库存事件类型。
   * 0: 设置库存；2: 增加库存；3: 减少库存；4: 下单扣除库存；5: 取消订单释放库存；
   * 6: 分配库存；7: 归还库存；8: 售后换货扣除库存；9: 售后换货取消释放库存；10: 系统转移
   */
  @JsonProperty("op_type")
  private Integer opType;

  /** 流水发生时间（秒级时间戳） */
  @JsonProperty("update_time")
  private Long updateTime;

  /** 额外信息 */
  @JsonProperty("ext_info")
  private StockFlowExtInfo extInfo;
}
