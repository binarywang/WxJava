package me.chanjar.weixin.channel.bean.product.link;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 商品口令 结果
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.product.link.ProductTagLinkResponse}。
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class ProductTagLinkResponse extends WxChannelBaseResponse {

  /** 商品口令 */
  @JsonProperty("product_taglink")
  private String productTaglink;
}
