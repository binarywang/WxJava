package me.chanjar.weixin.channel.bean.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 赠品对应的主品信息
 */
@Data
@NoArgsConstructor
public class MainProductInfo implements Serializable {

  private static final long serialVersionUID = 2024061212345678901L;

  /**
   * 赠品数量
   */
  @JsonProperty("gift_cnt")
  private Integer giftCnt;

  /**
   * 活动id
   */
  @JsonProperty("task_id")
  private Integer taskId;

  /**
   * 商品id
   */
  @JsonProperty("product_id")
  private Integer productId;

  /**
   * 主品sku_id
   */
  @JsonProperty("sku_id")
  private Integer skuId;

}
