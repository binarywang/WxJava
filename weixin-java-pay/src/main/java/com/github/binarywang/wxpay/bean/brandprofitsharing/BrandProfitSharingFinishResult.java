package com.github.binarywang.wxpay.bean.brandprofitsharing;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/** 完结分账受理结果，最终结果通过查询分账接口获取。 */
@Data
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class BrandProfitSharingFinishResult implements Serializable {
  private static final long serialVersionUID = 1L;

  /** 订单收款商户号，可为品牌主或门店商户号。 */
  @SerializedName("sub_mchid")
  private String subMchId;

  /** 微信支付订单号。 */
  @SerializedName("transaction_id")
  private String transactionId;

  /** 商户分账单号。 */
  @SerializedName("out_order_no")
  private String outOrderNo;

  /** 微信分账单号，与商户分账单号二选一。 */
  @SerializedName("order_id")
  private String orderId;
}
