package cn.binarywang.wx.miniapp.bean.xpay;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @date 2025-07-23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaXPayCreateFundsBillRequest implements Serializable {
  private static final long serialVersionUID = 7495157056049312108L;
  @SerializedName("transfer_amount")
  private Integer transferAmount;
  @SerializedName("transfer_account_uid")
  private Long transferAccountUid;
  @SerializedName("transfer_account_name")
  private String transferAccountName;
  @SerializedName("transfer_account_agency_id")
  private Integer transferAccountAgencyId;
  @SerializedName("request_id")
  private String requestId;
  @SerializedName("settle_begin")
  private Long settleBegin;
  @SerializedName("settle_end")
  private Long settleEnd;
  @SerializedName("env")
  private Integer env;
  @SerializedName("authorize_advertise")
  private Integer authorizeAdvertise;
  @SerializedName("fund_type")
  private Integer fundType;


  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }
}


