package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 赠品列表响应
 *
 * @author GitHub Copilot
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class GiftProductListResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = -2734111694780970778L;

  /** 总数 */
  @JsonProperty("total_num")
  private Integer totalNum;

  /** 本次翻页的上下文，用于请求下一页 */
  @JsonProperty("next_key")
  private String nextKey;

  /** 赠品商品 id 列表 */
  @JsonProperty("product_ids")
  private List<String> ids;
}
