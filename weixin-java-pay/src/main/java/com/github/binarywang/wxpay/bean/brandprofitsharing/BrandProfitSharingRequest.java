package com.github.binarywang.wxpay.bean.brandprofitsharing;

import com.github.binarywang.wxpay.v3.SpecEncrypt;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/** 请求连锁品牌分账，商户单号唯一，同一单号重复请求等同一次。 */
@Data
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class BrandProfitSharingRequest implements Serializable {
  private static final long serialVersionUID = 1L;

  /** 品牌主商户号。 */
  @SerializedName("brand_mchid")
  private String brandMchId;

  /** 订单收款商户号，可为品牌主或门店商户号。 */
  @SerializedName("sub_mchid")
  private String subMchId;

  /** 服务商应用 ID。 */
  @SerializedName("appid")
  private String appid;

  /** 品牌主应用 ID，PERSONAL_SUB_OPENID 时必填。 */
  @SerializedName("sub_appid")
  private String subAppid;

  /** 微信支付订单号。 */
  @SerializedName("transaction_id")
  private String transactionId;

  /** 商户分账单号。 */
  @SerializedName("out_order_no")
  private String outOrderNo;

  /** 分账接收方列表。 */
  @SpecEncrypt
  @SerializedName("receivers")
  private List<Receiver> receivers;

  /** 是否完结分账并解冻剩余资金。 */
  @SerializedName("finish")
  private Boolean finish;

  /** 分账接收方；姓名由 SDK 在发送时加密。 */
  @Data
  @Builder(builderMethodName = "newBuilder")
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Receiver implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 接收方类型：MERCHANT_ID、PERSONAL_OPENID 或 PERSONAL_SUB_OPENID。 */
    @SerializedName("type")
    private String type;

    /** 接收方账号。 */
    @SerializedName("account")
    private String account;

    /** 金额，单位为分。 */
    @SerializedName("amount")
    private Integer amount;

    /** 分账或回退原因描述。 */
    @SerializedName("description")
    private String description;

    /** 接收方名称。 */
    @SpecEncrypt
    @SerializedName("name")
    private String name;
  }
}
