package me.chanjar.weixin.channel.bean.limit;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 限时抢购 SKU 信息（更新时使用，在 {@link LimitSku} 基础上增加 product_id）
 *
 * @author <a href="https://github.com/features/copilot">GitHub Copilot</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LimitSkuUpdate extends LimitSku {

  private static final long serialVersionUID = -1L;

  /** SKU 所属商品ID（修改SKU时必传） */
  @JsonProperty("product_id")
  private String productId;

  public LimitSkuUpdate(String productId, String skuId, Integer salePrice, Integer saleStock) {
    super(skuId, salePrice, saleStock);
    this.productId = productId;
  }
}
