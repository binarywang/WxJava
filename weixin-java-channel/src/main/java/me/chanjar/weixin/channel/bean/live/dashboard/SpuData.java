package me.chanjar.weixin.channel.bean.live.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 商品明细数据
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
@Data
@NoArgsConstructor
public class SpuData implements Serializable {

  private static final long serialVersionUID = 7409791549917863816L;

  /**
   * 商品基础数据
   */
  @JsonProperty("base_data")
  private SpuBaseData baseData;

  /**
   * 商品曝光人数
   */
  @JsonProperty("exp_uv")
  private Long expUv;

  /**
   * 商品曝光次数
   */
  @JsonProperty("exp_pv")
  private Long expPv;

  /**
   * 商品粉丝曝光人数
   */
  @JsonProperty("fans_exp_uv")
  private Long fansExpUv;

  /**
   * 商品粉丝曝光次数
   */
  @JsonProperty("fans_exp_pv")
  private Long fansExpPv;

  /**
   * 商品非粉丝曝光人数
   */
  @JsonProperty("non_fans_exp_uv")
  private Long nonFansExpUv;

  /**
   * 商品非粉丝曝光次数
   */
  @JsonProperty("non_fans_exp_pv")
  private Long nonFansExpPv;

  /**
   * 商品新客户曝光人数
   */
  @JsonProperty("new_customer_exp_uv")
  private Long newCustomerExpUv;

  /**
   * 商品新客户曝光次数
   */
  @JsonProperty("new_customer_exp_pv")
  private Long newCustomerExpPv;

  /**
   * 商品老客户曝光人数
   */
  @JsonProperty("repeated_customer_exp_uv")
  private Long repeatedCustomerExpUv;

  /**
   * 商品老客户曝光次数
   */
  @JsonProperty("repeated_customer_exp_pv")
  private Long repeatedCustomerExpPv;

  /**
   * 商品点击人数
   */
  @JsonProperty("clk_uv")
  private Long clkUv;

  /**
   * 商品点击次数
   */
  @JsonProperty("clk_pv")
  private Long clkPv;

  /**
   * 商品新客户点击人数
   */
  @JsonProperty("new_customer_clk_uv")
  private Long newCustomerClkUv;

  /**
   * 商品新客户点击次数
   */
  @JsonProperty("new_customer_clk_pv")
  private Long newCustomerClkPv;

  /**
   * 商品老客户点击人数
   */
  @JsonProperty("repeated_customer_clk_uv")
  private Long repeatedCustomerClkUv;

  /**
   * 商品老客户点击次数
   */
  @JsonProperty("repeated_customer_clk_pv")
  private Long repeatedCustomerClkPv;

  /**
   * 商品粉丝点击人数
   */
  @JsonProperty("fans_clk_uv")
  private Long fansClkUv;

  /**
   * 商品粉丝点击次数
   */
  @JsonProperty("fans_clk_pv")
  private Long fansClkPv;

  /**
   * 商品非粉丝点击人数
   */
  @JsonProperty("non_fans_clk_uv")
  private Long nonFansClkUv;

  /**
   * 商品非粉丝点击次数
   */
  @JsonProperty("non_fans_clk_pv")
  private Long nonFansClkPv;

  /**
   * 商品分享人数
   */
  @JsonProperty("share_uv")
  private Long shareUv;

  /**
   * 商品分享次数
   */
  @JsonProperty("share_pv")
  private Long sharePv;

  /**
   * 商品曝光点击率
   */
  @JsonProperty("exp_clk_ratio")
  private Double expClkRatio;

  /**
   * 商品点击成交率
   */
  @JsonProperty("clk_pay_ratio")
  private Double clkPayRatio;

  /**
   * 商品成交金额（单位：分）
   */
  @JsonProperty("gmv")
  private Long gmv;

  /**
   * 商品成交订单数
   */
  @JsonProperty("pay_pv")
  private Long payPv;

  /**
   * 商品成交人数
   */
  @JsonProperty("pay_uv")
  private Long payUv;

  /**
   * 商品粉丝成交订单数
   */
  @JsonProperty("fans_pay_pv")
  private Long fansPayPv;

  /**
   * 商品粉丝成交人数
   */
  @JsonProperty("fans_pay_uv")
  private Long fansPayUv;

  /**
   * 商品非粉丝成交订单数
   */
  @JsonProperty("non_fans_pay_pv")
  private Long nonFansPayPv;

  /**
   * 商品非粉丝成交人数
   */
  @JsonProperty("non_fans_pay_uv")
  private Long nonFansPayUv;

  /**
   * 商品新客户成交次数
   */
  @JsonProperty("new_customer_pay_pv")
  private Long newCustomerPayPv;

  /**
   * 商品新客户成交人数
   */
  @JsonProperty("new_customer_pay_uv")
  private Long newCustomerPayUv;

  /**
   * 商品老客户成交次数
   */
  @JsonProperty("repeated_customer_pay_pv")
  private Long repeatedCustomerPayPv;

  /**
   * 商品老客户成交人数
   */
  @JsonProperty("repeated_customer_pay_uv")
  private Long repeatedCustomerPayUv;

  /**
   * 商品退款人数
   */
  @JsonProperty("refund_uv")
  private Long refundUv;

  /**
   * 商品退款订单数
   */
  @JsonProperty("refund_pv")
  private Long refundPv;

  /**
   * 商品退款金额（单位：分）
   */
  @JsonProperty("refund_amount")
  private Long refundAmount;

  /**
   * 商品订单创建人数
   */
  @JsonProperty("create_uv")
  private Long createUv;

  /**
   * 商品订单创建次数
   */
  @JsonProperty("create_pv")
  private Long createPv;

  /**
   * 商品粉丝订单创建人数
   */
  @JsonProperty("fans_create_uv")
  private Long fansCreateUv;

  /**
   * 商品粉丝订单创建次数
   */
  @JsonProperty("fans_create_pv")
  private Long fansCreatePv;

  /**
   * 商品非粉丝订单创建人数
   */
  @JsonProperty("non_fans_create_uv")
  private Long nonFansCreateUv;

  /**
   * 商品非粉丝订单创建次数
   */
  @JsonProperty("non_fans_create_pv")
  private Long nonFansCreatePv;

  /**
   * 商品新客户订单创建人数
   */
  @JsonProperty("new_customer_create_uv")
  private Long newCustomerCreateUv;

  /**
   * 商品新客户订单创建次数
   */
  @JsonProperty("new_customer_create_pv")
  private Long newCustomerCreatePv;

  /**
   * 商品老客户订单创建人数
   */
  @JsonProperty("repeated_customer_create_uv")
  private Long repeatedCustomerCreateUv;

  /**
   * 商品老客户订单创建次数
   */
  @JsonProperty("repeated_customer_create_pv")
  private Long repeatedCustomerCreatePv;

  /**
   * 商品库存
   */
  @JsonProperty("stock")
  private Long stock;

  /**
   * 商品退费率
   */
  @JsonProperty("refund_rate")
  private Double refundRate;

  /**
   * 商品完成订单数
   */
  @JsonProperty("finish_pv")
  private Long finishPv;

  /**
   * 商品未完成订单数
   */
  @JsonProperty("no_finish_pv")
  private Long noFinishPv;

  /**
   * 商品新客户转换率
   */
  @JsonProperty("new_customer_conversion_rate")
  private Double newCustomerConversionRate;

  /**
   * 商品讲解数
   */
  @JsonProperty("explanation_count")
  private Long explanationCount;

}
