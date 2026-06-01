package me.chanjar.weixin.channel.bean.gift;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 赠品列表 / 在售商品转赠品 响应（二者返回结构相同）
 *
 * @author <a href="https://github.com/features/copilot">GitHub Copilot</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GiftProductListResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = -1L;

  /** 赠品ID列表 */
  @JsonProperty("product_ids")
  private List<String> productIds;

  /** 本次翻页的上下文，用于请求下一页 */
  @JsonProperty("next_key")
  private String nextKey;

  /** 赠品总数 */
  @JsonProperty("total_num")
  private Integer totalNum;
}
