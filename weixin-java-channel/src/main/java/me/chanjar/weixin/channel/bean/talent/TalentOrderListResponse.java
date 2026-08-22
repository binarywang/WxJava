package me.chanjar.weixin.channel.bean.talent;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 带货助手-获取佣金单列表 响应
 *
 * @author <a href="https://github.com/features/copilot">GitHub Copilot</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TalentOrderListResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 3541802319654186172L;

  /** 佣金单信息列表 */
  @JsonProperty("order_list")
  private List<OrderInfo> orderList;

  /** 是否还有剩余订单 */
  @JsonProperty("has_more")
  private Boolean hasMore;

  /** 本次翻页的上下文，用于顺序翻页请求 */
  @JsonProperty("next_key")
  private String nextKey;

  /** 佣金单基础信息 */
  @Data
  @NoArgsConstructor
  public static class OrderInfo implements Serializable {

    private static final long serialVersionUID = 5261736494628827543L;

    /** 订单id */
    @JsonProperty("order_id")
    private String orderId;

    /** skuid */
    @JsonProperty("sku_id")
    private String skuId;

    /** 佣金单特殊标识【目前主要用于本地生活】 */
    @JsonProperty("special_id")
    private String specialId;
  }
}
