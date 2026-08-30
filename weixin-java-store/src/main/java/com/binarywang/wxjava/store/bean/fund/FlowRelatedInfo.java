package com.binarywang.wxjava.store.bean.fund;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 流水关联信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class FlowRelatedInfo implements Serializable {

  private static final long serialVersionUID = 3757839018198212504L;
  /** 关联类型, 1 订单， 2售后，3 提现，4 运费险 */
  @JsonProperty("related_type")
  private Integer relatedType;

  /** 关联订单号 */
  @JsonProperty("order_id")
  private String orderId;

  /** 关联售后单号 */
  @JsonProperty("aftersale_id")
  private String afterSaleId;

  /** 关联提现单号 */
  @JsonProperty("withdraw_id")
  private String withdrawId;

  /** 记账时间 */
  @JsonProperty("bookkeeping_time")
  private String bookkeepingTime;

  /** 关联运费险单号 */
  @JsonProperty("insurance_id")
  private String insuranceId;

  /** 关联支付单号 */
  @JsonProperty("transaction_id")
  private String transactionId;
}
