package me.chanjar.weixin.channel.bean.after;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 换货商品信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class AfterSaleExchangeProductInfo implements Serializable {

  private static final long serialVersionUID = -1341436607011117854L;

  /** 商品spuid */
  @JsonProperty("product_id")
  private String productId;

  /** 旧商品skuid */
  @JsonProperty("old_sku_id")
  private String oldSkuId;

  /** 新商品skuid */
  @JsonProperty("new_sku_id")
  private String newSkuId;

  /** 数量 */
  @JsonProperty("product_cnt")
  private String productCnt;

  /** 旧商品价格 */
  @JsonProperty("old_sku_price")
  private Integer oldSkuPrice;

  /** 新商品价格 */
  @JsonProperty("new_sku_price")
  private Integer newSkuPrice;
}
