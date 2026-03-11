package me.chanjar.weixin.channel.bean.after;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 售后单列表 请求参数
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class AfterSaleListParam implements Serializable {

  private static final long serialVersionUID = -103549981452112069L;
  /** 订单创建启始时间 unix时间戳 */
  @JsonProperty("begin_create_time")
  private Long beginCreateTime;

  /** 订单创建结束时间，end_create_time减去begin_create_time不得大于24小时 unix时间戳 */
  @JsonProperty("end_create_time")
  private Long endCreateTime;

  /** 售后单更新起始时间 */
  @JsonProperty("begin_update_time")
  private Long beginUpdateTime;

  /** 售后单更新结束时间，end_update_time减去begin_update_time不得大于24小时 */
  @JsonProperty("end_update_time")
  private Long endUpdateTime;

  /** 翻页参数，从第二页开始传，来源于上一页的返回值 */
  @JsonProperty("next_key")
  private String nextKey;
}
