package me.chanjar.weixin.channel.bean.gift;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 赠品 SKU 信息（用于添加非卖赠品）
 *
 * @author <a href="https://github.com/features/copilot">GitHub Copilot</a>
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GiftProductSkuInfo implements Serializable {

  private static final long serialVersionUID = -1L;

  /** 外部平台自定义 sku_id，最多128字符，一旦添加成功后该字段无法修改 */
  @JsonProperty("out_sku_id")
  private String outSkuId;

  /** 售卖价格，以分为单位 */
  @JsonProperty("sale_price")
  private Integer salePrice;

  /** 创建非卖商品时初始化设置的库存 */
  @JsonProperty("stock_num")
  private Integer stockNum;

  /** 商家自定义 sku 编码，最多100字符 */
  @JsonProperty("sku_code")
  private String skuCode;
}
