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

  /** 保障单号。 */
  @JsonProperty("guarantee_order_id")
  private String guaranteeOrderId;

  /** 订单号。 */
  @JsonProperty("order_id")
  private String orderId;

  /** 申请开始时间，Unix 时间戳（秒）。 */
  @JsonProperty("begin_apply_time")
  private Long beginApplyTime;

  /** 申请结束时间，Unix 时间戳（秒）。 */
  @JsonProperty("end_apply_time")
  private Long endApplyTime;

  /** 保障单状态。 */
  @JsonProperty("status")
  private Integer status;

  /** 分页偏移量。 */
  @JsonProperty("offset")
  private Integer offset;

  /** 返回条数。 */
  @JsonProperty("limit")
  private Integer limit;
}
