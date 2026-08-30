package com.binarywang.wxjava.store.bean.limit;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LimitTaskParam implements Serializable {

  private static final long serialVersionUID = 3885409806249022528L;

  /** 抢购商品ID */
  @JsonProperty("product_id")
  private String productId;

  /** 限时抢购任务开始时间(秒级时间戳) */
  @JsonProperty("start_time")
  private Date startTime;

  /** 限时抢购任务结束时间(秒级时间戳) */
  @JsonProperty("end_time")
  private Date endTime;

  /** sku列表 */
  @JsonProperty("limited_discount_skus")
  private List<LimitSku> skus;
}
