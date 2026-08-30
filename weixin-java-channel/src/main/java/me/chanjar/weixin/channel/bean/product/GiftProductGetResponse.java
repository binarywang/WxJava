package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 赠品详情响应
 *
 * @author GitHub Copilot
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class GiftProductGetResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 5331169221157446692L;

  /** 赠品线上数据 */
  @JsonProperty("product")
  private GiftProductInfo product;

  /** 赠品草稿数据 */
  @JsonProperty("edit_product")
  private GiftProductInfo editProduct;
}
