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
public class WxMaXPayQueryTransferAccountResponse extends WxMaBaseResponse implements Serializable {
  private static final long serialVersionUID = 7495157056049312109L;

  @SerializedName("acct_list")
  private List<AcctList> acctList;

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }

  @Data
  public static class AcctList {
    @SerializedName("transfer_account_name")
    private String transferAccountName;
    @SerializedName("transfer_account_uid")
    private Long transferAccountUid;
    @SerializedName("transfer_account_agency_id")
    private Long transferAccountAgencyId;
    @SerializedName("transfer_account_agency_name")
    private String transferAccountAgencyName;
    @SerializedName("state")
    private Integer state;
    @SerializedName("bind_result")
    private Integer bindResult;
    @SerializedName("error_msg")
    private String errorMsg;
  }
}
