package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 商品待开售信息
 *
 * @author <a href="https://gitee.com/cchengg">chu</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimingOnSaleInfo implements Serializable {

  /** 状态枚举 0-没有待开售；1-待开售 */
  @JsonProperty("status")
  private Integer status;

  /** 开售时间，秒级时间戳，0为未配置时间 */
  @JsonProperty("onsale_time")
  private Long onSaleTime;

  /** 是否隐藏价格 0-不隐藏；1-隐藏 */
  @JsonProperty("is_hide_price")
  private Integer isHidePrice;

  /** 待开售任务ID，可用于请求立即开售 */
  @JsonProperty("task_id")
  private Integer taskId;

}
