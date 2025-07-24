package me.chanjar.weixin.channel.bean.order;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.AttrInfo;

/**
 * 订单商品信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class OrderProductInfo implements Serializable {

  private static final long serialVersionUID = -2193536732955185928L;
  /**
   * 商品spu id
   */
  @JsonProperty("product_id")
  private String productId;

  /**
   * sku_id
   */
  @JsonProperty("sku_id")
  private String skuId;

  /**
   * sku小图
   */
  @JsonProperty("thumb_img")
  private String thumbImg;

  /**
   * sku数量
   */
  @JsonProperty("sku_cnt")
  private Integer skuCnt;

  /**
   * 售卖价格（单位：分）
   */
  @JsonProperty("sale_price")
  private Integer salePrice;

  /**
   * 商品标题
   */
  @JsonProperty("title")
  private String title;

  /**
   * 正在售后/退款流程中的 sku 数量
   */
  @JsonProperty("on_aftersale_sku_cnt")
  private Integer onAfterSaleSkuCnt;

  /**
   * 完成售后/退款的 sku 数量
   */
  @JsonProperty("finish_aftersale_sku_cnt")
  private Integer finishAfterSaleSkuCnt;

  /**
   * 商品编码
   */
  @JsonProperty("sku_code")
  private String skuCode;

  /**
   * 市场价格（单位：分）
   */
  @JsonProperty("market_price")
  private Integer marketPrice;

  /**
   * sku属性
   */
  @JsonProperty("sku_attrs")
  private List<AttrInfo> skuAttrs;

  /**
   * sku实付价格
   */
  @JsonProperty("real_price")
  private Integer realPrice;

  /**
   * 商品外部spu id
   */
  @JsonProperty("out_product_id")
  private String outProductId;

  /**
   * 商品外部sku id
   */
  @JsonProperty("out_sku_id")
  private String outSkuId;

  /**
   * 是否有优惠金额，非必填，默认为false
   */
  @JsonProperty("is_discounted")
  private Boolean isDiscounted;

  /**
   * 优惠后 sku 价格，非必填，is_discounted为 true 时有值
   */
  @JsonProperty("estimate_price")
  private Integer estimatePrice;

  /**
   * 是否修改过价格，非必填，默认为false
   */
  @JsonProperty("is_change_price")
  private Boolean changePriced;

  /**
   * 改价后 sku 价格，非必填，is_change_price为 true 时有值
   */
  @JsonProperty("change_price")
  private Integer changePrice;

  /**
   * 区域库存id
   */
  @JsonProperty("out_warehouse_id")
  private String outWarehouseId;

  /**
   * 商品发货信息
   */
  @JsonProperty("sku_deliver_info")
  private OrderSkuDeliverInfo skuDeliverInfo;

  /**
   * 商品额外服务信息
   */
  @JsonProperty("extra_service")
  private OrderProductExtraService extraService;

  /**
   * 是否使用了会员积分抵扣
   */
  @JsonProperty("use_deduction")
  private Boolean useDeduction;

  /**
   * 会员积分抵扣金额，单位为分
   */
  @JsonProperty("deduction_price")
  private Integer deductionPrice;

  /**
   * 商品优惠券信息，具体结构请参考OrderProductCouponInfo结构体，逐步替换 order.order_detail.coupon_info
   */
  @JsonProperty("order_product_coupon_info_list")
  private List<OrderCouponInfo> orderProductCouponInfoList;

  /**
   * 商品发货时效，超时此时间未发货即为发货超时
   */
  @JsonProperty("delivery_deadline")
  private Long deliveryDeadline;

  /**
   * 商家优惠金额，单位为分
   */
  @JsonProperty("merchant_discounted_price")
  private Integer merchantDiscountedPrice;

  /**
   * 商家优惠金额，单位为分
   */
  @JsonProperty("finder_discounted_price")
  private Integer finderDiscountedPrice;

  /**
   * 是否赠品，非必填，赠品商品返回，1:是赠品
   */
  @JsonProperty("is_free_gift")
  private Boolean freeGift;
}
