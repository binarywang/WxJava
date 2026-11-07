package me.chanjar.weixin.channel.bean.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 订单列表 响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.order.OrderListResponse}。
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class OrderListResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = -6198624448684807852L;
  /** 订单id列表 */
  @JsonProperty("order_id_list")
  private List<String> ids;

  /** 分页参数，下一页请求回传 */
  @JsonProperty("next_key")
  private String nextKey;

  /** 是否还有下一页，true:有下一页；false:已经结束，没有下一页。 */
  @JsonProperty("has_more")
  private Boolean hasMore;
}
