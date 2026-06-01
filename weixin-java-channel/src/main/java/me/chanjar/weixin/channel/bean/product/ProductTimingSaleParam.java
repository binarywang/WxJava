package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品立即开售请求参数.
 *
 * @author GitHub Copilot
 */
@Data
@NoArgsConstructor
public class ProductTimingSaleParam implements Serializable {

  private static final long serialVersionUID = -7185451543781817487L;

  /** 商品ID. */
  @JsonProperty("product_id")
  private String productId;

  /** 定时开售任务ID. */
  @JsonProperty("task_id")
  private Long taskId;
}
