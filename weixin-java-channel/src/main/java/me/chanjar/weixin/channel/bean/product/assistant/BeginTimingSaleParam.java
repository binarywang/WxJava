package me.chanjar.weixin.channel.bean.product.assistant;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品立即开售参数。
 */
@Data
@NoArgsConstructor
public class BeginTimingSaleParam implements Serializable {

  private static final long serialVersionUID = -1525220756273987016L;

  /** 商品 ID。 */
  @JsonProperty("product_id")
  private String productId;

  /** 定时开售任务 ID。 */
  @JsonProperty("task_id")
  private String taskId;
}
