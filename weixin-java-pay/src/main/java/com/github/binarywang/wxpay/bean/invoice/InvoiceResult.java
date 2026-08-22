package com.github.binarywang.wxpay.bean.invoice;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 电子发票查询结果。
 *
 * @see <a href="https://pay.weixin.qq.com/doc/v3/partner/4015792567">查询电子发票</a>
 */
@Data
public class InvoiceResult implements Serializable {
  private static final long serialVersionUID = 1L;

  @SerializedName("total_count")
  private Integer totalCount;
  @SerializedName("fapiao_information")
  private List<InvoiceInformation> fapiaoInformation;

  @Data
  public static class InvoiceInformation implements Serializable {
    private static final long serialVersionUID = 1L;

    @SerializedName("fapiao_id")
    private String fapiaoId;
    private String status;
    @SerializedName("blue_fapiao")
    private Fapiao blueFapiao;
    @SerializedName("red_fapiao")
    private Fapiao redFapiao;
    @SerializedName("total_amount")
    private Integer totalAmount;
    @SerializedName("tax_amount")
    private Integer taxAmount;
    private Integer amount;
    @SerializedName("fapiao_error_code")
    private String fapiaoErrorCode;
    @SerializedName("fapiao_error_message")
    private String fapiaoErrorMessage;
  }

  @Data
  public static class Fapiao implements Serializable {
    private static final long serialVersionUID = 1L;

    @SerializedName("fapiao_code")
    private String fapiaoCode;
    @SerializedName("fapiao_number")
    private String fapiaoNumber;
    @SerializedName("check_code")
    private String checkCode;
    private String password;
    @SerializedName("fapiao_time")
    private String fapiaoTime;
  }
}
