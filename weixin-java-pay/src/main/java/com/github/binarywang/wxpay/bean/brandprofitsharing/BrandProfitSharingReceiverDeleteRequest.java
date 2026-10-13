package com.github.binarywang.wxpay.bean.brandprofitsharing;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/** 删除品牌分账接收方。 */
@Data
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class BrandProfitSharingReceiverDeleteRequest implements Serializable {
  private static final long serialVersionUID = 1L;

  /** 品牌主商户号。 */
  @SerializedName("brand_mchid")
  private String brandMchId;

  /** 服务商应用 ID。 */
  @SerializedName("appid")
  private String appid;

  /** 品牌主应用 ID，PERSONAL_SUB_OPENID 时必填。 */
  @SerializedName("sub_appid")
  private String subAppid;

  /** 接收方类型：MERCHANT_ID、PERSONAL_OPENID 或 PERSONAL_SUB_OPENID。 */
  @SerializedName("type")
  private String type;

  /** 接收方账号。 */
  @SerializedName("account")
  private String account;
}
