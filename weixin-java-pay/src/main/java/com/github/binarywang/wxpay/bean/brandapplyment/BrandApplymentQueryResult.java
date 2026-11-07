package com.github.binarywang.wxpay.bean.brandapplyment;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/** 品牌入驻申请状态应答。 */
@Data
public class BrandApplymentQueryResult implements Serializable {
  private static final long serialVersionUID = 1L;
  /** 微信支付申请单号。 */
  @SerializedName("applyment_id")
  private String applymentId;

  /** 业务申请编号。 */
  @SerializedName("business_code")
  private String businessCode;

  /** 申请状态，使用微信支付 APPLYMENT_STATE_* 值。 */
  @SerializedName("applyment_state")
  private String applymentState;

  /** 申请状态说明，包含并行环节状态。 */
  @SerializedName("applyment_state_desc")
  private String applymentStateDesc;

  /** 授权确认二维码链接，管理员使用微信扫码。 */
  @SerializedName("authorization_confirmation_qr_code")
  private String authorizationConfirmationQrCode;

  /** 审核驳回原因。 */
  @SerializedName("reject_reason")
  private String rejectReason;

  /** 品牌 ID，入驻完成时返回。 */
  @SerializedName("brand_id")
  private String brandId;
}
