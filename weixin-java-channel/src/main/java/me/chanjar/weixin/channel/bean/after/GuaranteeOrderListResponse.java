package me.chanjar.weixin.channel.bean.after;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 保障单列表响应.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GuaranteeOrderListResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = -6136894046806166855L;

  /** 保障单号列表. */
  @JsonProperty("guarantee_order_id_list")
  private List<String> guaranteeOrderIdList;

  /** 翻页参数. */
  @JsonProperty("next_key")
  private String nextKey;

  /** 是否还有数据. */
  @JsonProperty("has_more")
  private Boolean hasMore;
}
