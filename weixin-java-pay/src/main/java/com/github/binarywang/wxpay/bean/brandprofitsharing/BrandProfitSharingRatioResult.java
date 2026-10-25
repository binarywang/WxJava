package com.github.binarywang.wxpay.bean.brandprofitsharing;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/** 品牌最大分账比例。 */
@Data
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class BrandProfitSharingRatioResult implements Serializable {
  private static final long serialVersionUID = 1L;

  /** 品牌主商户号。 */
  @SerializedName("brand_mchid")
  private String brandMchId;

  /** 最大分账比例，单位为万分之一。 */
  @SerializedName("max_ratio")
  private Integer maxRatio;
}
