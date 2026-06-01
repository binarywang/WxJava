package me.chanjar.weixin.channel.bean.gift;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 在售商品转赠品 请求参数
 *
 * @author <a href="https://github.com/features/copilot">GitHub Copilot</a>
 * @see <a href="https://developers.weixin.qq.com/doc/store/shop/API/channels-shop-product/gift/api_setproductasgift.html">在售商品转赠品</a>
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GiftSetParam implements Serializable {

  private static final long serialVersionUID = -1L;

  /** 原始商品ID */
  @JsonProperty("product_id")
  private String productId;

  /** sku 列表（目前仅支持单品商品） */
  @JsonProperty("skus")
  private List<GiftSetSkuParam> skus;
}
