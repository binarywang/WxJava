package me.chanjar.weixin.channel.bean.compass.shop;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 店铺商品罗盘数据
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class ShopProductCompassData implements Serializable {

  private static final long serialVersionUID = 5387546181020447627L;

  /** 成交金额 */
  @JsonProperty("pay_gmv")
  private String payGmv;

  /**下单金额，单位分 */
  @JsonProperty("create_gmv")
  private String createGmv;

  /** 下单订单数 */
  @JsonProperty("create_cnt")
  private String createCnt;

  /** 下单人数 */
  @JsonProperty("create_uv")
  private String createUv;

  /** 下单件数 */
  @JsonProperty("create_product_cnt")
  private String createProductCnt;

  /** 成交订单数 */
  @JsonProperty("pay_cnt")
  private String payCnt;

  /** 成交人数 */
  @JsonProperty("pay_uv")
  private String payUv;

  /** 成交件数 */
  @JsonProperty("pay_product_cnt")
  private String payProductCnt;

  /** 成交金额（剔除退款） */
  @JsonProperty("pure_pay_gmv")
  private String purePayGmv;

  /** 成交客单价（剔除退款） */
  @JsonProperty("pay_gmv_per_uv")
  private String payGmvPerUv;

  /** 实际结算金额，单位分 */
  @JsonProperty("seller_actual_settle_amount")
  private String sellerActualSettleAmount;

  /** 实际服务费金额，单位分 */
  @JsonProperty("platform_actual_commission")
  private String platformActualCommission;

  /** 实际达人佣金支出，单位分 */
  @JsonProperty("finderuin_actual_commission")
  private String finderuinActualCommission;

  /** 实际团长佣金支出，单位分 */
  @JsonProperty("captain_actual_commission")
  private String captainActualCommission;

  /** 预估结算金额，单位分 */
  @JsonProperty("seller_predict_settle_amount")
  private String sellerPredictSettleAmount;

  /** 预估服务费金额，单位分 */
  @JsonProperty("platform_predict_commission")
  private String platformPredictCommission;

  /** 预估达人佣金支出，单位分 */
  @JsonProperty("finderuin_predict_commission")
  private String finderuinPredictCommission;

  /** 预估团长佣金支出，单位分 */
  @JsonProperty("captain_predict_commission")
  private String captainPredictCommission;

  /** 商品点击人数 */
  @JsonProperty("product_click_uv")
  private String productClickUv;

  /** 商品点击次数 */
  @JsonProperty("product_click_cnt")
  private String productClickCnt;

  /** 成交退款金额，单位分 */
  @JsonProperty("pay_refund_gmv")
  private String payRefundGmv;

  /** 成交退款人数，单位分 */
  @JsonProperty("pay_refund_uv")
  private String payRefundUv;

  /** 成交退款率 */
  @JsonProperty("pay_refund_ratio")
  private Double payRefundRatio;

  /** 发货后成交退款率 */
  @JsonProperty("pay_refund_after_send_ratio")
  private Double payRefundAfterSendRatio;

  /** 成交退款订单数 */
  @JsonProperty("pay_refund_cnt")
  private String payRefundCnt;

  /** 成交退款件数 */
  @JsonProperty("pay_refund_product_cnt")
  private String payRefundProductCnt;

  /** 发货前成交退款率 */
  @JsonProperty("pay_refund_before_send_ratio")
  private Double payRefundBeforeSendRatio;

  /** 退款金额，单位分 */
  @JsonProperty("refund_gmv")
  private String refundGmv;

  /** 退款件数 */
  @JsonProperty("refund_product_cnt")
  private String refundProductCnt;

  /** 退款订单数 */
  @JsonProperty("refund_cnt")
  private String refundCnt;

  /** 退款人数 */
  @JsonProperty("refund_uv")
  private String refundUv;

}
