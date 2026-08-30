package com.binarywang.wxjava.store.bean.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 订单修改信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class ChangeOrderInfo implements Serializable {

  private static final long serialVersionUID = 4932726847720452340L;

  /** 商品id */
  @JsonProperty("product_id")
  private String productId;

  /** 商品sku */
  @JsonProperty("sku_id")
  private String skuId;

  /** 订单中该商品修改后的总价，以分为单位 */
  @JsonProperty("change_price")
  private String changePrice;

}
