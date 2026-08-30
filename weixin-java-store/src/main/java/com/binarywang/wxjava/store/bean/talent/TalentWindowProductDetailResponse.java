package com.binarywang.wxjava.store.bean.talent;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * 带货助手-获取达人橱窗商品详情 响应
 *
 * @author <a href="https://github.com/features/copilot">GitHub Copilot</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TalentWindowProductDetailResponse extends WxStoreBaseResponse {

  private static final long serialVersionUID = 1634829710537264918L;

  /** 橱窗商品详情 */
  @JsonProperty("product")
  private ProductDetail product;

  /** 橱窗商品详情 */
  @Data
  @NoArgsConstructor
  public static class ProductDetail implements Serializable {

    private static final long serialVersionUID = 7283640192847516039L;

    /** 橱窗商品ID */
    @JsonProperty("product_id")
    private String productId;

    /** 商品来源店铺的appid */
    @JsonProperty("appid")
    private String appid;

    /** 商品在货源店铺的商品ID，对于带货商品会返回 */
    @JsonProperty("out_product_id")
    private String outProductId;

    /** 商品标题 */
    @JsonProperty("title")
    private String title;

    /** 商品头图url */
    @JsonProperty("img_url")
    private String imgUrl;

    /** 商品所属叶子类目（品类）ID */
    @JsonProperty("leaf_category_id")
    private Long leafCategoryId;

    /** 商品状态：1=生效中，2=被禁止售卖 */
    @JsonProperty("status")
    private Integer status;

    /** 价格区间最小值（单位分，销售价） */
    @JsonProperty("selling_price")
    private Long sellingPrice;

    /** 剩余库存 */
    @JsonProperty("stock")
    private Long stock;

    /** 销量 */
    @JsonProperty("sales")
    private Long sales;

    /** 是否在橱窗设置对外隐藏 */
    @JsonProperty("is_hide")
    private Boolean isHide;

    /** 用于在小程序跳转小店场景添加商品时传递跟佣信息 */
    @JsonProperty("product_promotion_link")
    private String productPromotionLink;
  }
}
