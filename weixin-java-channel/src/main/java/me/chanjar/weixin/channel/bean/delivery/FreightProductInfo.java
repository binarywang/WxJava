package me.chanjar.weixin.channel.bean.delivery;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 包裹中商品信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@Deprecated
public class FreightProductInfo implements Serializable {
  private static final long serialVersionUID = -3751269707150372172L;

  /**
   * 商品id
   */
  @JsonProperty("product_id")
  private String productId;

  /**
   * sku_id
   */
  @JsonProperty("sku_id")
  private String skuId;

  /**
   * 商品数量
   */
  @JsonProperty("product_cnt")
  private Integer productCnt;
}
