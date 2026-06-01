package me.chanjar.weixin.channel.bean.gift;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 赠品 SKU 详情（获取赠品时返回）
 *
 * @author <a href="https://github.com/features/copilot">GitHub Copilot</a>
 */
@Data
@NoArgsConstructor
public class GiftProductSku implements Serializable {

  private static final long serialVersionUID = -1L;

  /** sku ID */
  @JsonProperty("sku_id")
  private String skuId;

  /** 外部平台自定义 sku ID */
  @JsonProperty("out_sku_id")
  private String outSkuId;

  /** 售卖价格，以分为单位 */
  @JsonProperty("sale_price")
  private Integer salePrice;

  /** sku 库存 */
  @JsonProperty("stock_num")
  private Integer stockNum;

  /** 商家自定义 sku 编码 */
  @JsonProperty("sku_code")
  private String skuCode;

  /** sku 状态 */
  @JsonProperty("status")
  private Integer status;
}
