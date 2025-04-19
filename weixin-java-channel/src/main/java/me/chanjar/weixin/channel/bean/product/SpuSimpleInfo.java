package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class SpuSimpleInfo implements Serializable {

  private static final long serialVersionUID = 5583726432139404883L;

  /** 商品ID */
  @JsonProperty("product_id")
  protected String productId;

  /** 商家自定义商品ID */
  @JsonProperty("out_product_id")
  protected String outProductId;

  /** sku数组 */
  @JsonProperty("skus")
  protected List<SkuInfo> skus;
}
