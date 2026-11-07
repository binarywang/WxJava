package com.github.binarywang.wxpay.bean.brandapplyment;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/** 提交或撤销品牌入驻申请应答。 */
@Data
public class BrandApplymentResult implements Serializable {
  private static final long serialVersionUID = 1L;
  /** 微信支付申请单号。 */
  @SerializedName("applyment_id")
  private String applymentId;

  /** 业务申请编号。 */
  @SerializedName("business_code")
  private String businessCode;
}
