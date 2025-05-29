package me.chanjar.weixin.channel.bean.compass.finder;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

import java.util.List;

/**
 * 获取带货商品列表响应
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductListResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 7903039293558611066L;

  /**
   * 带货商品列表
   */
  @JsonProperty("product_list")
  private List<ProductInfo> productList;

}
