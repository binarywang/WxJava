package com.github.binarywang.wxpay.bean.brandprofitsharing;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/** 添加或删除分账接收方结果。 */
@Data
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class BrandProfitSharingReceiverResult implements Serializable {
  private static final long serialVersionUID = 1L;

  /** 品牌主商户号。 */
  @SerializedName("brand_mchid")
  private String brandMchId;

  /** 接收方类型：MERCHANT_ID、PERSONAL_OPENID 或 PERSONAL_SUB_OPENID。 */
  @SerializedName("type")
  private String type;

  /** 接收方账号。 */
  @SerializedName("account")
  private String account;
}
