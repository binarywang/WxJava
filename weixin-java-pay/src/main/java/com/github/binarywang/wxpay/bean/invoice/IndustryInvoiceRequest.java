package com.github.binarywang.wxpay.bean.invoice;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import java.io.Serializable;
/** 行业电子发票请求；fapiaoInformation 对应不动产租赁或成品油官方请求对象。 */
@Data public class IndustryInvoiceRequest implements Serializable {
  private static final long serialVersionUID = 1L;
  @SerializedName("sub_mchid") private String subMchid;
  @SerializedName("fapiao_apply_id") private String fapiaoApplyId;
  @SerializedName("buyer_information") private BuyerInformation buyerInformation;
  @SerializedName("fapiao_information") private Object fapiaoInformation;
}
