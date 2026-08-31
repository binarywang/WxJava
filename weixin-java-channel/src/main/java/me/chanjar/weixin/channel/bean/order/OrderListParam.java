package me.chanjar.weixin.channel.bean.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.StreamPageParam;
import me.chanjar.weixin.channel.bean.base.TimeRange;

/**
 * 获取订单列表参数
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.order.OrderListParam}。
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(Include.NON_NULL)
@Deprecated
public class OrderListParam extends StreamPageParam {

  private static final long serialVersionUID = 3780097459964746890L;
  /** 订单创建时间范围 */
  @JsonProperty("create_time_range")
  private TimeRange createTimeRange;

  /** 订单更新时间范围 */
  @JsonProperty("update_time_range")
  private TimeRange updateTimeRange;

  /** 订单状态，枚举值见 {@link me.chanjar.weixin.channel.enums.WxOrderStatus} */
  @JsonProperty("status")
  private Integer status;

  /** 买家身份标识 */
  @JsonProperty("openid")
  private Integer openid;
}
