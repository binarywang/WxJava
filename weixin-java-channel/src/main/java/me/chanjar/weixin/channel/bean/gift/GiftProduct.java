package me.chanjar.weixin.channel.bean.gift;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.AttrInfo;
import me.chanjar.weixin.channel.bean.product.DescriptionInfo;
import me.chanjar.weixin.channel.bean.product.SpuCategory;

/**
 * 赠品详情数据（线上数据或草稿数据）
 *
 * @author <a href="https://github.com/features/copilot">GitHub Copilot</a>
 */
@Data
@NoArgsConstructor
public class GiftProduct implements Serializable {

  private static final long serialVersionUID = -1L;

  /** 小店内部赠品ID */
  @JsonProperty("product_id")
  private String productId;

  /** 外部平台自定义赠品ID */
  @JsonProperty("out_product_id")
  private String outProductId;

  /** 标题 */
  @JsonProperty("title")
  private String title;

  /** 主图列表 */
  @JsonProperty("head_imgs")
  private List<String> headImgs;

  /** 赠品详情 */
  @JsonProperty("desc_info")
  private DescriptionInfo descInfo;

  /** 赠品线上状态 */
  @JsonProperty("status")
  private Integer status;

  /** 赠品草稿状态 */
  @JsonProperty("edit_status")
  private Integer editStatus;

  /** 新类目树结构 */
  @JsonProperty("cats_v2")
  private List<SpuCategory> catsV2;

  /** 商品参数 */
  @JsonProperty("attrs")
  private List<AttrInfo> attrs;

  /** 商家自定义赠品编码 */
  @JsonProperty("spu_code")
  private String spuCode;

  /** 品牌id */
  @JsonProperty("brand_id")
  private String brandId;

  /** sku 列表 */
  @JsonProperty("skus")
  private List<GiftProductSku> skus;

  /**
   * 赠品类型。4: 在售赠品；5: 非卖赠品
   */
  @JsonProperty("product_type")
  private Integer productType;

  /** 赠品草稿最近一次修改时间 */
  @JsonProperty("edit_time")
  private Long editTime;

  /** 在售赠品的来源商品id */
  @JsonProperty("src_product_id")
  private Long srcProductId;
}
