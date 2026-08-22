package com.github.binarywang.wxpay.bean.invoice;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import java.io.Serializable;
/** 电子发票购买方抬头信息。敏感字段 phone、email 由调用方按支付文档加密。 */
@Data public class BuyerInformation implements Serializable {
  private static final long serialVersionUID = 1L;
  private String type;
  private String name;
  @SerializedName("taxpayer_id") private String taxpayerId;
  private String address;
  private String telephone;
  @SerializedName("bank_name") private String bankName;
  @SerializedName("bank_account") private String bankAccount;
  private String phone;
  private String email;
  private Integer amount;
  @SerializedName("out_trade_no") private String outTradeNo;
  @SerializedName("fapiao_bill_type") private String fapiaoBillType;
  @SerializedName("user_apply_message") private String userApplyMessage;
}
