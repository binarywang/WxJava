package me.chanjar.weixin.channel.bean.gift;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 在售商品转赠品时，sku 划拨参数
 *
 * @author <a href="https://github.com/features/copilot">GitHub Copilot</a>
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GiftSetSkuParam implements Serializable {

  private static final long serialVersionUID = -1L;

  /** 原始商品 sku ID（目前仅支持单品商品） */
  @JsonProperty("sku_id")
  private String skuId;

  /** 划拨给赠品的库存数量，将直接从原始商品库存中扣除 */
  @JsonProperty("stock_num")
  private Integer stockNum;
}
