package me.chanjar.weixin.channel.bean.compass.finder;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 获取带货商品数据响应
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductDataResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 7264776818163943719L;

  /**
   * 带货商品数据
   */
  @JsonProperty("product_info")
  private ProductInfo productInfo;

}
