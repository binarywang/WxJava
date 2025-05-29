package me.chanjar.weixin.channel.bean.compass.shop;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.compass.CompassFinderBaseParam;

/**
 * 商品数据 请求参数
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShopProductDataParam extends CompassFinderBaseParam {

  private static final long serialVersionUID = - 5016298274452168329L;

  /** 商品id */
  @JsonProperty("product_id")
  private String productId;

  public ShopProductDataParam(String ds, String productId) {
    super(ds);
    this.productId = productId;
  }

}
