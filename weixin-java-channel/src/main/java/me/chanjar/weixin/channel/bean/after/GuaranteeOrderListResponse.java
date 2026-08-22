package me.chanjar.weixin.channel.bean.after;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 保障单列表响应。
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GuaranteeOrderListResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 9105476087203713187L;

  /** 保障单总数。 */
  @JsonProperty("total_num")
  private Integer totalNum;

  /** 保障单列表。 */
  @JsonProperty("guarantee_order_list")
  private List<GuaranteeOrderInfo> guaranteeOrderList;
}
