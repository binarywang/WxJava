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
public class WxMaXPayDownloadAdverfundsOrderResponse extends WxMaBaseResponse implements Serializable {
  private static final long serialVersionUID = 7495157056049312109L;

  @SerializedName("url")
  private String url;

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }
}
