package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Deprecated
public class SkuStockParam implements Serializable {

  private static final long serialVersionUID = -5542939078361208816L;

  /** 内部商品ID */
  @JsonProperty("product_id")
  protected String productId;

  /** 内部sku_id */
  @JsonProperty("sku_id")
  protected String skuId;

  /** 修改类型。1: 增加；2:减少；3:设置 */
  @JsonProperty("diff_type")
  protected Integer diffType;

  /** 增加、减少或者设置的库存值 */
  @JsonProperty("num")
  protected Integer num;
}
