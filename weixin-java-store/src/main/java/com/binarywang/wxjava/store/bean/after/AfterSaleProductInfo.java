package com.binarywang.wxjava.store.bean.after;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 售后相关商品信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class AfterSaleProductInfo implements Serializable {

  private static final long serialVersionUID = 4205179093262757775L;
  /** 商品spu id */
  @JsonProperty("product_id")
  private String productId;

  /** 商品sku id */
  @JsonProperty("sku_id")
  private String skuId;

  /** 售后数量 */
  @JsonProperty("count")
  private Integer count;
}
