package com.github.binarywang.wxpay.bean.brandstore;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/** 品牌门店绑定收款商户。 */
@Data
public class BrandStoreRecipient implements Serializable {
  private static final long serialVersionUID = 1L;

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
