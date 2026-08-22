package com.github.binarywang.wxpay.bean.invoice;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/** 服务商子商户电子发票能力状态。 */
@Data
public class SubMerchantInvoiceStatus implements Serializable {
  private static final long serialVersionUID = 1L;
  @SerializedName("sub_mchid")
  private String subMchid;
  @SerializedName("third_mode")
  private Mode thirdMode;
  @SerializedName("digital_tax_mode")
  private DigitalTaxMode digitalTaxMode;

  @Data
  public static class Mode implements Serializable {
    private static final long serialVersionUID = 1L;
    private String status;
  }

  @Data
  public static class DigitalTaxMode extends Mode {
    private static final long serialVersionUID = 1L;
    @SerializedName("billing_person_info")
    private List<BillingPerson> billingPersonInfo;
    @SerializedName("access_time")
    private String accessTime;
    @SerializedName("expired_time")
    private String expiredTime;
    @SerializedName("access_fail_reason")
    private String accessFailReason;
    @SerializedName("ability_info")
    private List<Ability> abilityInfo;
  }

  @Data
  public static class BillingPerson implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
  }

  @Data
  public static class Ability implements Serializable {
    private static final long serialVersionUID = 1L;
    private String type;
    private String status;
  }
}
