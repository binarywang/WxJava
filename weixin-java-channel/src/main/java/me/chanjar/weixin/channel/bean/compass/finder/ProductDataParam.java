package me.chanjar.weixin.channel.bean.compass.finder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.compass.CompassFinderBaseParam;

/**
 * 获取带货商品数据请求参数
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDataParam extends CompassFinderBaseParam {

  private static final long serialVersionUID = - 5016298274452168329L;

  /**
   * 商品id
   */
  @JsonProperty("product_id")
  private String productId;

  public ProductDataParam(String ds, String productId) {
    super(ds);
    this.productId = productId;
  }

}
