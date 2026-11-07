package com.github.binarywang.wxpay.bean.brandapplyment;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/** 撤销品牌入驻申请，业务申请编号与微信支付申请单号至少填写一个。 */
@Data
public class BrandApplymentCancelRequest implements Serializable {
  private static final long serialVersionUID = 1L;
  /** 业务申请编号。 */
  @SerializedName("business_code")
  private String businessCode;

  /** 微信支付申请单号。 */
  @SerializedName("applyment_id")
  private String applymentId;
}
