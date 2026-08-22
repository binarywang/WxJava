package me.chanjar.weixin.channel.bean.after;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
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

  @JsonProperty("guarantee_order_id_list")
  private List<String> guaranteeOrderIdList;

  @JsonProperty("order_id_list")
  private List<String> orderIdList;

  @JsonProperty("type")
  private Integer type;

  @JsonProperty("begin_time")
  private Long beginTime;

  @JsonProperty("end_time")
  private Long endTime;

  @JsonProperty("status_list")
  private String statusList;

  @JsonProperty("offset")
  private Integer offset;

  @JsonProperty("limit")
  private Integer limit;
}
