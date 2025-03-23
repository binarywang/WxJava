package me.chanjar.weixin.channel.bean.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 订单详细数据
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class OrderDetailInfo implements Serializable {

  private static final long serialVersionUID = 3916307299998005676L;
  /** 商品列表 */
  @JsonProperty("product_infos")
  private List<OrderProductInfo> productInfos;

  /** 支付信息 */
  @JsonProperty("pay_info")
  private OrderPayInfo payInfo;

  /** 价格信息 */
  @JsonProperty("price_info")
  private OrderPriceInfo priceInfo;

  /** 配送信息 */
  @JsonProperty("delivery_info")
  private OrderDeliveryInfo deliveryInfo;

  /** 优惠券信息 */
  @JsonProperty("coupon_info")
  private OrderCouponInfo couponInfo;

  /** 额外信息 */
  @JsonProperty("ext_info")
  private OrderExtInfo extInfo;

  /** 分佣信息 */
  @JsonProperty("commission_infos")
  private List<OrderCommissionInfo> commissionInfos;

  /** 分享信息 */
  @JsonProperty("sharer_info")
  private OrderSharerInfo sharerInfo;

  /** 结算信息 */
  @JsonProperty("settle_info")
  private OrderSettleInfo settleInfo;

  /** 分享员信息 */
  @JsonProperty("sku_sharer_infos")
  private List<OrderSkuShareInfo> skuSharerInfos;

  /** 授权账号信息 */
  @JsonProperty("agent_info")
  private OrderAgentInfo agentInfo;

  /** 订单来源信息 */
  @JsonProperty("source_info")
  private OrderSourceInfo sourceInfo;

}
