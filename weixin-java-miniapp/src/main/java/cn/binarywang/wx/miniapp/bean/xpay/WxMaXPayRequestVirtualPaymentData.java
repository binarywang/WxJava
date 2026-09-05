package cn.binarywang.wx.miniapp.bean.xpay;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 调用 wx.requestVirtualPayment 所需的支付参数。
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaXPayRequestVirtualPaymentData implements Serializable {
  private static final long serialVersionUID = 7495157056049312108L;

  /**
   * 支付模式，个人主体虚拟支付固定为 short_series_goods。
   */
  @SerializedName("mode")
  private String mode;

  /**
   * 下单签名原文，需与计算 paySig 和 signature 时使用的字符串完全一致。
   */
  @SerializedName("signData")
  private String signData;

  /**
   * 使用 AppKey 对 requestVirtualPayment&signData 计算得到的签名。
   */
  @SerializedName("paySig")
  private String paySig;

  /**
   * 使用 session_key 对 signData 计算得到的签名。
   */
  @SerializedName("signature")
  private String signature;

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }
}
