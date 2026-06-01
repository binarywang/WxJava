package me.chanjar.weixin.channel.bean.gift;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.AttrInfo;
import me.chanjar.weixin.channel.bean.product.DescriptionInfo;
import me.chanjar.weixin.channel.bean.product.SpuCategory;

/**
 * 添加非卖赠品请求参数
 *
 * @author <a href="https://github.com/features/copilot">GitHub Copilot</a>
 * @see <a href="https://developers.weixin.qq.com/doc/store/shop/API/channels-shop-product/gift/api_addgiftproduct.html">添加非卖商品</a>
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GiftProductInfo implements Serializable {

  private static final long serialVersionUID = -1L;

  /** 外部平台自定义非卖商品ID，最多128字符，一旦添加成功后该字段无法修改 */
  @JsonProperty("out_product_id")
  private String outProductId;

  /** 标题，最多60字符 */
  @JsonProperty("title")
  private String title;

  /** 主图，最少3张，最多9张 */
  @JsonProperty("head_imgs")
  private List<String> headImgs;

  /** 赠品详情 */
  @JsonProperty("desc_info")
  private DescriptionInfo descInfo;

  /** 非卖商品类目，新类目树结构 */
  @JsonProperty("cats_v2")
  private List<SpuCategory> catsV2;

  /** 非卖商品参数 */
  @JsonProperty("attrs")
  private List<AttrInfo> attrs;

  /** 商家自定义的非卖商品编码 */
  @JsonProperty("spu_code")
  private String spuCode;

  /** 品牌id，无品牌为"2100000000" */
  @JsonProperty("brand_id")
  private String brandId;

  /** 仅支持单sku，长度固定为1 */
  @JsonProperty("skus")
  private List<GiftProductSkuInfo> skus;

  /** 添加完成后是否立即上架。1:是；0:否；默认0 */
  @JsonProperty("listing")
  private Integer listing;
}
