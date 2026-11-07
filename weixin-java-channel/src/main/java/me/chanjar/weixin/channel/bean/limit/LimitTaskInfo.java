package me.chanjar.weixin.channel.bean.limit;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.limit.LimitTaskInfo}。
 */
@Data
@NoArgsConstructor
@Deprecated
public class LimitTaskInfo implements Serializable {

  private static final long serialVersionUID = 3032226931637189351L;

  /** 限时抢购任务ID */
  @JsonProperty("task_id")
  private String taskId;

  /** 抢购商品ID */
  @JsonProperty("product_id")
  private String productId;

  /** 限时抢购任务状态 */
  @JsonProperty("status")
  private Integer status;

  /** 限时抢购任务创建时间(秒级时间戳) */
  @JsonProperty("create_time")
  private Long createTime;

  /** 限时抢购任务开始时间(秒级时间戳) */
  @JsonProperty("start_time")
  private Long startTime;

  /** 限时抢购任务结束时间(秒级时间戳) */
  @JsonProperty("end_time")
  private Long endTime;

  /** sku列表 */
  @JsonProperty("limited_discount_skus")
  private List<LimitSku> skus;
}
