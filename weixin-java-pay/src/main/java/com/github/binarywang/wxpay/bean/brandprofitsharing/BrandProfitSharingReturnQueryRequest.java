package com.github.binarywang.wxpay.bean.brandprofitsharing;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/** 查询回退，orderId 与 outOrderNo 二选一。 */
@Data
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class BrandProfitSharingReturnQueryRequest implements Serializable {
  private static final long serialVersionUID = 1L;

  /** 订单收款商户号，可为品牌主或门店商户号。 */
  @SerializedName("sub_mchid")
  private String subMchId;

  /** 商户回退单号。 */
  @SerializedName("out_return_no")
  private String outReturnNo;

  /** 微信分账单号，与商户分账单号二选一。 */
  @SerializedName("order_id")
  private String orderId;

  /** 商户分账单号。 */
  @SerializedName("out_order_no")
  private String outOrderNo;
}
