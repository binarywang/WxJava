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
public class WxMaXPayQueryAdverFundsResponse extends WxMaBaseResponse implements Serializable {
  private static final long serialVersionUID = 7495157056049312109L;

  @SerializedName("adver_funds_list")
  private List<AdverFunds> adverFundsList;
  @SerializedName("total_page")
  private Integer totalPage;


  @Data
  public static class AdverFunds {
    @SerializedName("settle_begin")
    private Long settleBegin;
    @SerializedName("settle_end")
    private Long settleEnd;
    @SerializedName("total_amount")
    private Integer totalAmount;
    @SerializedName("remain_amount")
    private Integer remainAmount;
    @SerializedName("expire_time")
    private Long expireTime;
    @SerializedName("fund_type")
    private Integer fundType;
    @SerializedName("fund_id")
    private String fundId;
  }

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }
}
