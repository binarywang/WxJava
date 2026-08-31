package me.chanjar.weixin.channel.bean.complaint;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 纠纷单响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.complaint.ComplaintOrderResponse}。
 */
@Data
@NoArgsConstructor
@Deprecated
public class ComplaintOrderResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 1968530826349555367L;
  /** 售后单号 */
  @JsonProperty("after_sale_order_id")
  private String afterSaleOrderId;

  /** 订单号 */
  @JsonProperty("order_id")
  private String orderId;

  /** 纠纷历史 */
  @JsonProperty("history")
  private List<ComplaintHistory> history;

  /** 纠纷单状态, 见 {@link me.chanjar.weixin.channel.enums.ComplaintStatus} */
  @JsonProperty("status")
  private Integer status;

}
