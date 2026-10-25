package com.github.binarywang.wxpay.bean.brandstore;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/** 绑定品牌门店收款商户响应。 */
@Data
public class BrandStoreBindRecipientResult implements Serializable {
  private static final long serialVersionUID = 1L;

  /** 品牌ID。 */
  @SerializedName("brand_id")
  private String brandId;

  /** 门店ID。 */
  @SerializedName("store_id")
  private String storeId;

  /** 收款商户号。 */
  @SerializedName("mchid")
  private String mchid;

  /** 收款商户主体名称。 */
  @SerializedName("company_name")
  private String companyName;

  /** 绑定状态：CONFIRMED、ADMIN_REJECTED、CONFIRMING、TIMEOUT_REJECTED。 */
  @SerializedName("recipient_state")
  private String recipientState;
}
