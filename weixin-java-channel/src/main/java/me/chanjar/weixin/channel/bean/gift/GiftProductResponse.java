package me.chanjar.weixin.channel.bean.gift;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 添加/更新非卖赠品 响应
 *
 * @author <a href="https://github.com/features/copilot">GitHub Copilot</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GiftProductResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = -1L;

  /** 非卖商品ID */
  @JsonProperty("product_id")
  private String productId;

  /** 创建时间 */
  @JsonProperty("create_time")
  private String createTime;

  /** 更新时间 */
  @JsonProperty("update_time")
  private String updateTime;
}
