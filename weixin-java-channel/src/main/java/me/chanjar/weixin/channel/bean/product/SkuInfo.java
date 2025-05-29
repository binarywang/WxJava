package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import me.chanjar.weixin.channel.bean.base.AttrInfo;

/**
 * SKU信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
public class SkuInfo implements Serializable {

  private static final long serialVersionUID = -8734396136299597845L;

  /** 商家自定义商品ID */
  @JsonProperty("out_product_id")
  private String outProductId;

  /** 商家自定义skuID */
  @JsonProperty("out_sku_id")
  private String outSkuId;

  /** sku小图 */
  @JsonProperty("thumb_img")
  private String thumbImg;

  /** 售卖价格，以分为单位，数字类型，最大不超过10000000（1000万元） */
  @JsonProperty("sale_price")
  private Integer salePrice;

  /** 市场价格，以分为单位，数字类型，最大不超过10000000（1000万元），且必须比sale_price大 */
  @JsonProperty("market_price")
  private Integer marketPrice;

  /** 库存，数字类型，最大不超过10000000（1000万） */
  @JsonProperty("stock_num")
  private Integer stockNum;

  /** 商品编码，字符类型，最长不超过20 */
  @JsonProperty("sku_code")
  private String skuCode;

  /** SKU属性 */
  @JsonProperty("sku_attrs")
  private List<AttrInfo> attrs;

  /** sku发货信息 */
  @JsonProperty("sku_deliver_info")
  private SkuDeliverInfo skuDeliverInfo;

  /** skuID */
  @JsonProperty("sku_id")
  private String skuId;

  public SkuInfo() {
  }

  public SkuInfo(String outProductId, String outSkuId) {
    this.outProductId = outProductId;
    this.outSkuId = outSkuId;
  }
}
