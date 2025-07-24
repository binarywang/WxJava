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
public class WxMaXPayGetNegotiationHistoryResponse extends WxMaBaseResponse implements Serializable {
  private static final long serialVersionUID = 7495157056049312109L;


  @SerializedName("total")
  private Integer total;
  @SerializedName("history")
  private List<History> history;

  @Data
  public static class History {
    @SerializedName("log_id")
    private String logId;
    @SerializedName("operator")
    private String operator;
    @SerializedName("operate_time")
    private String operateTime;
    @SerializedName("operate_type")
    private String operateType;
    @SerializedName("operate_details")
    private String operateDetails;
    @SerializedName("complaint_media_list")
    private List<ComplaintMedia> complaintMediaList;

    @Data
    public static class ComplaintMedia {

      @SerializedName("media_type")
      private String mediaType;
      @SerializedName("media_url")
      private List<String> mediaUrl;
    }
  }

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }
}
