package com.github.binarywang.wxpay.bean.brandprofitsharing;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/** 查询连锁品牌分账结果。 */
@Data
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class BrandProfitSharingQueryRequest implements Serializable {
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
}
