package me.chanjar.weixin.channel.bean.limit;

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
public class LimitSku implements Serializable {

  private static final long serialVersionUID = -1819737633227427482L;

  /** 参与抢购的商品 ID 下，不同规格(SKU)的商品信息 */
  @JsonProperty("sku_id")
  private String skuId;

  /** SKU的抢购价格，必须小于原价(原价为1分钱的商品无法创建抢购任务) */
  @JsonProperty("sale_price")
  private Integer salePrice;

  /** 参与抢购的商品库存，必须小于等于现有库存 */
  @JsonProperty("sale_stock")
  private Integer saleStock;
}
