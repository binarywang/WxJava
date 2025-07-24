package cn.binarywang.wx.miniapp.bean.xpay;

import cn.binarywang.wx.miniapp.bean.WxMaBaseResponse;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaXPayGetComplaintDetailResponse extends WxMaBaseResponse implements Serializable {
  private static final long serialVersionUID = 7495157056049312109L;


  @SerializedName("complaint")
  private Complaint complaint;

  @Data
  public static class Complaint {
    @SerializedName("complaint_id")
    private String complaintId;
    @SerializedName("complaint_time")
    private String complaintTime;
    @SerializedName("complaint_detail")
    private String complaintDetail;
    @SerializedName("complaint_state")
    private String complaintState;
    @SerializedName("payer_phone")
    private String payerPhone;
    @SerializedName("payer_openid")
    private String payerOpenid;
    @SerializedName("complaint_order_info")
    private List<ComplaintOrderInfo> complaintOrderInfo;
    @SerializedName("complaint_full_refunded")
    private Boolean complaintFullRefunded;
    @SerializedName("incoming_user_response")
    private Boolean incomingUserResponse;
    @SerializedName("user_complaint_times")
    private Integer userComplaintTimes;
    @SerializedName("complaint_media_list")
    private List<ComplaintMedia> complaintMediaList;
    @SerializedName("problem_description")
    private String problemDescription;
    @SerializedName("problem_type")
    private String problemType;
    @SerializedName("apply_refund_amount")
    private Integer applyRefundAmount;
    @SerializedName("user_tag_list")
    private List<String> userTagList;
    @SerializedName("service_order_info")
    private List<ServiceOrderInfo> serviceOrderInfo;


  }

  @Data
  public static class ComplaintOrderInfo {
    @SerializedName("transaction_id")
    private String transactionId;
    @SerializedName("out_trade_no")
    private String outTradeNo;
    @SerializedName("amount")
    private Integer amount;
    @SerializedName("wxa_out_trade_no")
    private String wxaOutTradeNo;
    @SerializedName("wx_order_id")
    private String wxOrderId;
  }

  @Data
  public static class ComplaintMedia {

    @SerializedName("media_type")
    private String mediaType;
    @SerializedName("media_url")
    private List<String> mediaUrl;
  }

  @Data
  public static class ServiceOrderInfo {
    @SerializedName("order_id")
    private String orderId;
    @SerializedName("out_order_no")
    private String outOrderNo;
    @SerializedName("state")
    private String state;
  }


  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }
}
