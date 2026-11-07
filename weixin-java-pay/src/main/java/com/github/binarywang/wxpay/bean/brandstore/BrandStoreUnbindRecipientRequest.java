package com.github.binarywang.wxpay.bean.brandstore;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/** 解绑品牌门店收款商户请求。 */
@Data
public class BrandStoreUnbindRecipientRequest implements Serializable {
  private static final long serialVersionUID = 1L;

  /** 必填，品牌ID。 */
  @SerializedName("brand_id")
  private String brandId;

  /** 必填，收款商户号。 */
  @SerializedName("mchid")
  private String mchid;
}
