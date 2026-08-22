package me.chanjar.weixin.channel.bean.limit;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 更新限时抢购任务请求参数。
 */
@Data
@NoArgsConstructor
public class LimitTaskUpdateParam implements Serializable {

  private static final long serialVersionUID = 7277247203887803045L;

  /** 限时抢购任务 ID。 */
  @JsonProperty("task_id")
  private String taskId;

  /** 当前活动状态：0 待开始，1 进行中。 */
  @JsonProperty("status")
  private Integer status;

  /** 活动开始时间，秒级时间戳。 */
  @JsonProperty("start_time")
  private Long startTime;

  /** 活动结束时间，秒级时间戳。 */
  @JsonProperty("end_time")
  private Long endTime;

  /** 活动名称。 */
  @JsonProperty("title")
  private String title;

  /** SKU 抢购信息列表。 */
  @JsonProperty("limited_discount_skus")
  private List<LimitSkuUpdate> skus;
}
