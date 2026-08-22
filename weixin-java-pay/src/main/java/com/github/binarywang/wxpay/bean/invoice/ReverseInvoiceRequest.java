package com.github.binarywang.wxpay.bean.invoice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 冲红电子发票请求。
 *
 * @see <a href="https://pay.weixin.qq.com/doc/v3/partner/4015792575">冲红电子发票</a>
 */
@Data
public class ReverseInvoiceRequest implements Serializable {
  private static final long serialVersionUID = 1L;

  @SerializedName("sub_mchid")
  private String subMchid;
  @Expose(serialize = false)
  @SerializedName("fapiao_apply_id")
  private String fapiaoApplyId;
  @SerializedName("reverse_reason")
  private String reverseReason;
  @SerializedName("fapiao_information")
  private List<InvoiceInfo> fapiaoInformation;

  @Data
  public static class InvoiceInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @SerializedName("fapiao_id")
    private String fapiaoId;
    @SerializedName("fapiao_code")
    private String fapiaoCode;
    @SerializedName("fapiao_number")
    private String fapiaoNumber;
  }
}
