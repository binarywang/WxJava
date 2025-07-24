package cn.binarywang.wx.miniapp.bean.xpay;

import cn.binarywang.wx.miniapp.bean.WxMaBaseResponse;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaXPayQueryBizBalanceResponse extends WxMaBaseResponse implements Serializable {
  private static final long serialVersionUID = 7495157056049312108L;

  @SerializedName("balance_available")
  private BalanceAvailable balanceAvailable;

  @Data
  public static class BalanceAvailable {
    @SerializedName("amount")
    private String amount;
    @SerializedName("currency_code")
    private String currencyCode;

  }

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }

}
