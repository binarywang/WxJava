package me.chanjar.weixin.channel.bean.order;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 结算信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.order.OrderSettleInfo}。
 */
@Data
@NoArgsConstructor
@Deprecated
public class OrderSettleInfo implements Serializable {

  private static final long serialVersionUID = 2140632631448343656L;
  /**
   * 预计技术服务费（单位为分）
   */
  @JsonProperty("predict_commission_fee")
  private Integer predictCommissionFee;

  /**
   * 实际技术服务费（单位为分）（未结算时本字段为空）
   */
  @JsonProperty("commission_fee")
  private Integer commissionFee;

  /**
   * 预计人气卡返佣金额，单位为分（未发起结算时本字段为空）
   */
  @JsonProperty("predict_wecoin_commission")
  private Integer predictWecoinCommission;

  /**
   * 实际人气卡返佣金额，单位为分（未结算时本字段为空）
   */
  @JsonProperty("wecoin_commission")
  private Integer wecoinCommission;

  /**
   * 商家结算时间
   */
  @JsonProperty("settle_time")
  private Long settleTime;
}
