package com.github.binarywang.wxpay.bean.brandstore;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/** 解绑品牌门店收款商户响应。 */
@Data
public class BrandStoreUnbindRecipientResult implements Serializable {
  private static final long serialVersionUID = 1L;

  /** 解绑失败原因；成功时可为空。 */
  @SerializedName("failed_reason")
  private String failedReason;
}
