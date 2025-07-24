package me.chanjar.weixin.channel.bean.compass.shop;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 商品详细信息 响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ShopProductDataResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 6903392663954301579L;

  /** 商品详细信息 */
  @JsonProperty("product_info")
  private ShopProductInfo productInfo;


}
