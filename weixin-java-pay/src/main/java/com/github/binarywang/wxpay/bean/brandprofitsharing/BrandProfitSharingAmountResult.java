package com.github.binarywang.wxpay.bean.brandprofitsharing;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/** 订单剩余待分账金额。 */
@Data
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class BrandProfitSharingAmountResult implements Serializable {
  private static final long serialVersionUID = 1L;

  /** 微信支付订单号。 */
  @SerializedName("transaction_id")
  private String transactionId;

  /** 剩余待分账金额，单位为分。 */
  @SerializedName("unsplit_amount")
  private Integer unsplitAmount;
}
