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
public class WxMaXPayQueryRecoverBillResponse extends WxMaBaseResponse implements Serializable {
  private static final long serialVersionUID = 7495157056049312109L;

  @SerializedName("bill_list")
  private List<Bill> billList;
  @SerializedName("total_page")
  private Integer totalPage;

  @Data
  public static class Bill {
    @SerializedName("bill_id")
    private String billId;
    @SerializedName("recover_time")
    private Long recoverTime;
    @SerializedName("settle_begin")
    private Long settleBegin;
    @SerializedName("settle_end")
    private Long settleEnd;
    @SerializedName("fund_id")
    private String fundId;
    @SerializedName("recover_account_name")
    private String recoverAccountName;
    @SerializedName("recover_amount")
    private Integer recoverAmount;
    @SerializedName("refund_order_list")
    private List<String> refundOrderList;
  }

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }
}
