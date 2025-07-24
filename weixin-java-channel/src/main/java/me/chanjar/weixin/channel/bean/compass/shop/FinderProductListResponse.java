package me.chanjar.weixin.channel.bean.compass.shop;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 带货达人商品列表 响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FinderProductListResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 5883861777181983173L;

  /**
   * 带货达人商品列表
   */
  @JsonProperty("product_list")
  private List<FinderProductListItem> productList;

}
