package com.github.binarywang.wxpay.bean.invoice;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 服务商开具通用行业电子发票请求。
 *
 * @see <a href="https://pay.weixin.qq.com/doc/v3/partner/4015792574">开具通用行业电子发票</a>
 */
@Data
public class GeneralInvoiceRequest implements Serializable {
  private static final long serialVersionUID = 1L;

  @SerializedName("sub_mchid")
  private String subMchid;
  @SerializedName("fapiao_apply_id")
  private String fapiaoApplyId;
  @SerializedName("buyer_information")
  private BuyerInformation buyerInformation;
  @SerializedName("fapiao_information")
  private FapiaoInformation fapiaoInformation;

  @Data
  public static class FapiaoInformation implements Serializable {
    private static final long serialVersionUID = 1L;
    @SerializedName("fapiao_id")
    private String fapiaoId;
    @SerializedName("total_amount")
    private Integer totalAmount;
    private List<InvoiceItem> items;
    @SerializedName("export_business_policy_code")
    private Integer exportBusinessPolicyCode;
    @SerializedName("vat_refund_levy_code")
    private Integer vatRefundLevyCode;
    @SerializedName("billing_person_id")
    private String billingPersonId;
    @SerializedName("billing_person")
    private String billingPerson;
    @SerializedName("fapiao_bill_type")
    private String fapiaoBillType;
    @SerializedName("transaction_information")
    private List<TransactionInformation> transactionInformation;
    private String remark;
  }

  @Data
  public static class InvoiceItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @SerializedName("tax_code")
    private String taxCode;
    @SerializedName("goods_name")
    private String goodsName;
    private String specification;
    private String unit;
    private Integer quantity;
    @SerializedName("total_amount")
    private Integer totalAmount;
    @SerializedName("tax_rate")
    private Integer taxRate;
    private Boolean discount;
    @SerializedName("preferential_policy_code")
    private Integer preferentialPolicyCode;
  }

  @Data
  public static class TransactionInformation implements Serializable {
    private static final long serialVersionUID = 1L;
    @SerializedName("pay_channel")
    private String payChannel;
    @SerializedName("transaction_id")
    private String transactionId;
    @SerializedName("out_trade_no")
    private String outTradeNo;
    private Integer amount;
  }
}
