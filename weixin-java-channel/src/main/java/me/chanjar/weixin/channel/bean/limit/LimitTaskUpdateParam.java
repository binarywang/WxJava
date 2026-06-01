package me.chanjar.weixin.channel.bean.limit;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 更新限时抢购任务 请求参数
 *
 * @author <a href="https://github.com/features/copilot">GitHub Copilot</a>
 * @see <a href="https://developers.weixin.qq.com/doc/store/shop/API/channels-shop-product/limiteddiscounttask/api_updatelimiteddiscounttask.html">更新限时抢购任务</a>
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LimitTaskUpdateParam implements Serializable {

  private static final long serialVersionUID = -1L;

  /** 限时抢购任务ID */
  @JsonProperty("task_id")
  private String taskId;

  /** 当前活动状态（乐观锁校验）。0: 待开始；1: 进行中 */
  @JsonProperty("status")
  private Integer status;

  /** 限时抢购任务开始时间（秒级时间戳） */
  @JsonProperty("start_time")
  private Long startTime;

  /** 限时抢购任务结束时间（秒级时间戳） */
  @JsonProperty("end_time")
  private Long endTime;

  /** 活动名称（仅商家可见，最长50个字符） */
  @JsonProperty("title")
  private String title;

  /** SKU 抢购信息列表，修改 SKU 时必须传入 product_id */
  @JsonProperty("limited_discount_skus")
  private List<LimitSkuUpdate> skus;
}
