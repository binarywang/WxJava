package me.chanjar.weixin.channel.bean.talent;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 带货助手-获取佣金单详情 响应
 *
 * @author <a href="https://github.com/features/copilot">GitHub Copilot</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class TalentOrderDetailResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 2174806923145876312L;

  /** 订单基础信息 */
  @JsonProperty("base_info")
  private BaseInfo baseInfo;

  /** 订单佣金信息 */
  @JsonProperty("commission_info")
  private CommissionInfo commissionInfo;

  /** 下单通道信息 */
  @JsonProperty("channel_info")
  private ChannelInfo channelInfo;

  /** 内容推广推客机构信息 */
  @JsonProperty("promotion_head_supplier_info")
  private PromotionHeadSupplierInfo promotionHeadSupplierInfo;

  /** 商品信息 */
  @JsonProperty("product_info")
  private ProductInfo productInfo;

  /** 订单基础信息 */
  @Data
  @NoArgsConstructor
  public static class BaseInfo implements Serializable {

    private static final long serialVersionUID = 6382947162830498251L;

    /** 订单id */
    @JsonProperty("order_id")
    private String orderId;

    /** 商品spuid */
    @JsonProperty("spu_id")
    private String spuId;

    /** 商品skuid */
    @JsonProperty("sku_id")
    private String skuId;

    /** 特殊id【针对本地生活】 */
    @JsonProperty("special_id")
    private String specialId;

    /**
     * 订单状态：1=待支付, 2=待发货, 3=已发货, 4=已收货, 5=售后中, 6=已完成, 7=已取消, 8=已退款, 9=部分退款, 10=待使用
     */
    @JsonProperty("order_status")
    private Integer orderStatus;

    /** 实际支付金额【单位：分】 */
    @JsonProperty("actual_payment")
    private String actualPayment;

    /** 订单创建时间 */
    @JsonProperty("order_create_time")
    private Long orderCreateTime;

    /** 订单更新时间 */
    @JsonProperty("order_update_time")
    private Long orderUpdateTime;

    /** 下单用户 */
    @JsonProperty("buyer_info")
    private BuyerInfo buyerInfo;

    /** 订单支付时间 */
    @JsonProperty("order_pay_time")
    private Long orderPayTime;

    /** 订单的分佣基数【单位：分】 */
    @JsonProperty("settle_payment")
    private String settlePayment;
  }

  /** 下单用户信息 */
  @Data
  @NoArgsConstructor
  public static class BuyerInfo implements Serializable {

    private static final long serialVersionUID = 4729638451027364819L;

    /** 下单用户的openid */
    @JsonProperty("open_id")
    private String openId;

    /** 下单用户的unionid */
    @JsonProperty("union_id")
    private String unionId;
  }

  /** 订单佣金信息 */
  @Data
  @NoArgsConstructor
  public static class CommissionInfo implements Serializable {

    private static final long serialVersionUID = -3819264037182640581L;

    /** 佣金单状态：1=待结算, 2=已结算, 3=取消结算, 4=结算异常 */
    @JsonProperty("state")
    private Integer state;

    /** 佣金比例 */
    @JsonProperty("ratio")
    private String ratio;

    /** 预期结算时间 */
    @JsonProperty("expect_settle_time")
    private Long expectSettleTime;

    /** 预期结算金额 */
    @JsonProperty("expect_settlement")
    private String expectSettlement;

    /** 实际结算时间 */
    @JsonProperty("actual_settle_time")
    private Long actualSettleTime;

    /** 实际结算金额 */
    @JsonProperty("actual_settlement")
    private String actualSettlement;
  }

  /** 下单通道信息 */
  @Data
  @NoArgsConstructor
  public static class ChannelInfo implements Serializable {

    private static final long serialVersionUID = 7364918204736159023L;

    /** 渠道类型：1=视频号, 2=公众号 */
    @JsonProperty("channel_type")
    private Integer channelType;

    /** 渠道id */
    @JsonProperty("channel_id")
    private String channelId;

    /** 渠道名称 */
    @JsonProperty("channel_name")
    private String channelName;
  }

  /** 内容推广推客机构信息 */
  @Data
  @NoArgsConstructor
  public static class PromotionHeadSupplierInfo implements Serializable {

    private static final long serialVersionUID = 1826374950183647291L;

    /** 机构id */
    @JsonProperty("id")
    private String id;

    /** 机构名称 */
    @JsonProperty("name")
    private String name;

    /** 佣金单比例 */
    @JsonProperty("ratio")
    private String ratio;

    /** 佣金 */
    @JsonProperty("fee")
    private String fee;
  }

  /** 商品信息 */
  @Data
  @NoArgsConstructor
  public static class ProductInfo implements Serializable {

    private static final long serialVersionUID = -4920183645872916370L;

    /** 商品的标题 */
    @JsonProperty("title")
    private String title;

    /** 商品的头图 */
    @JsonProperty("thumb_img")
    private String thumbImg;
  }
}
