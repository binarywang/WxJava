package cn.binarywang.wx.miniapp.bean.xpay;

import cn.binarywang.wx.miniapp.constant.WxMaConstants;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 个人主体虚拟支付调起 wx.requestVirtualPayment 的签名原文参数。
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaXPayRequestVirtualPaymentRequest implements Serializable {
  private static final long serialVersionUID = 7495157056049312108L;

  /**
   * 支付应用 ID。
   */
  @SerializedName("offerId")
  private String offerId;

  /**
   * 购买数量。
   */
  @SerializedName("buyQuantity")
  private Integer buyQuantity;

  /**
   * 环境，个人主体虚拟支付固定为 0。
   */
  @SerializedName("env")
  private Integer env;

  /**
   * 币种，个人主体虚拟支付固定为 CNY。
   */
  @SerializedName("currencyType")
  private String currencyType;

  /**
   * 道具 ID。
   */
  @SerializedName("productId")
  private String productId;

  /**
   * 道具单价，单位为分。
   */
  @SerializedName("goodsPrice")
  private Integer goodsPrice;

  /**
   * 商户订单号，8 到 32 位且不能以下划线开头。
   */
  @SerializedName("outTradeNo")
  private String outTradeNo;

  /**
   * 商户透传数据。
   */
  @SerializedName("attach")
  private String attach;

  public WxMaXPayRequestVirtualPaymentData createPayData(WxMaXPaySigParams sigParams) {
    final String signData = this.toJson();
    return WxMaXPayRequestVirtualPaymentData.builder()
      .mode(WxMaConstants.XPayPaymentMode.GOODS)
      .signData(signData)
      .paySig(sigParams.calcPaySig(WxMaConstants.XPayWxApiSigUri.WXAPI, signData))
      .signature(sigParams.calcSig(signData))
      .build();
  }

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }
}
