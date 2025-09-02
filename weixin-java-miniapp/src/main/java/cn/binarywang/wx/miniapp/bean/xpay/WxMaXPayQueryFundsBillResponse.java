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
public class WxMaXPayQueryFundsBillResponse extends WxMaBaseResponse implements Serializable {
  private static final long serialVersionUID = 7495157056049312109L;

  @SerializedName("bill_list")
  private List<Bill> billList;
  @SerializedName("total_page")
  private Integer totalPage;


  @Data
  public static class Bill {
    @SerializedName("bill_id")
    private String billId;
    @SerializedName("oper_time")
    private Long operTime;
    @SerializedName("settle_begin")
    private Long settleBegin;
    @SerializedName("settle_end")
    private Long settleEnd;
    @SerializedName("fund_id")
    private String fundId;
    @SerializedName("transfer_account_name")
    private String transferAccountName;
    @SerializedName("transfer_account_uid")
    private Integer transferAccountUid;
    @SerializedName("transfer_amount")
    private Integer transferAmount;
    @SerializedName("status")
    private Integer status;
    @SerializedName("request_id")
    private String requestId;
  }

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }
}
