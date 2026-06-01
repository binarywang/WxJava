package me.chanjar.weixin.channel.bean.gift;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 赠品 SKU 信息（用于更新非卖赠品，在 {@link GiftProductSkuInfo} 基础上增加 sku_id 和 stock_diff）
 *
 * @author <a href="https://github.com/features/copilot">GitHub Copilot</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GiftProductUpdateSkuInfo extends GiftProductSkuInfo {

  private static final long serialVersionUID = -1L;

  /** 若填了已存在 sku_id 则进行更新，否则新增 */
  @JsonProperty("sku_id")
  private String skuId;

  /** 库存差值信息，更新时建议使用，避免高并发问题 */
  @JsonProperty("stock_diff")
  private GiftSkuStockDiff stockDiff;
}
