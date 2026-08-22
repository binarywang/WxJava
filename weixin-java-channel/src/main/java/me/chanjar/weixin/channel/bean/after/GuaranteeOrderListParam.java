package me.chanjar.weixin.channel.bean.after;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 保障单列表请求参数。
 */
@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class GuaranteeOrderListParam implements Serializable {

  private static final long serialVersionUID = 1622570776364341988L;

  /** 订单创建启始时间，Unix 时间戳（秒）。 */
  @JsonProperty("begin_create_time")
  private Long beginCreateTime;

  /** 订单创建结束时间，Unix 时间戳（秒）。 */
  @JsonProperty("end_create_time")
  private Long endCreateTime;

  /** 保障单更新启始时间，Unix 时间戳（秒）。 */
  @JsonProperty("begin_update_time")
  private Long beginUpdateTime;

  /** 保障单更新结束时间，Unix 时间戳（秒）。 */
  @JsonProperty("end_update_time")
  private Long endUpdateTime;

  /** 翻页参数。 */
  @JsonProperty("next_key")
  private String nextKey;
}
