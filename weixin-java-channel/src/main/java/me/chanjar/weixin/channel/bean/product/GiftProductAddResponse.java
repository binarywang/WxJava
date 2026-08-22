package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 添加赠品响应
 *
 * @author GitHub Copilot
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GiftProductAddResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = -5971026809157610975L;

  /** 赠品商品ID */
  @JsonProperty("product_id")
  private String productId;
}
