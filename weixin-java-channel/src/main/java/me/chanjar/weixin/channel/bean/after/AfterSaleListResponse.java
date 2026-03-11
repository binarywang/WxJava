package me.chanjar.weixin.channel.bean.after;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 售后单列表 响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AfterSaleListResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 5033313416948732123L;
  /** 售后单号列表 */
  @JsonProperty("after_sale_order_id_list")
  private List<String> ids;

  /** 翻页参数 */
  @JsonProperty("next_key")
  private String nextKey;

  /** 是否还有数据 */
  @JsonProperty("has_more")
  private Boolean hasMore;
}
