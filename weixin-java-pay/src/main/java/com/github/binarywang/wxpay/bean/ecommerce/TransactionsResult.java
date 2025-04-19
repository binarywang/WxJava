package com.github.binarywang.wxpay.bean.ecommerce;

import com.github.binarywang.wxpay.bean.ecommerce.enums.TradeTypeEnum;
import com.github.binarywang.wxpay.v3.util.SignUtils;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.security.PrivateKey;

/**
 * 合单支付 JSAPI支付结果响应
 */
@Data
@NoArgsConstructor
public class TransactionsResult implements Serializable {
  private static final long serialVersionUID = 1760592667519950149L;
  /**
   * <pre>
   * 字段名：预支付交易会话标识 （APP支付、JSAPI支付 会返回）
   * 变量名：prepay_id
   * 是否必填：是
   * 类型：string(64)
   * 描述：
   *  数字和字母。微信生成的预支付会话标识，用于后续接口调用使用。
   *  示例值：wx201410272009395522657a690389285100
   * </pre>
   */
  @SerializedName("prepay_id")
  private String prepayId;

  /**
   * <pre>
   * 字段名：支付跳转链接   （H5支付 会返回）
   * 变量名：h5_url
   * 是否必填：是
   * 类型：string(512)
   * 描述：
   *  支付跳转链接
   *  示例值：https://wx.tenpay.com/cgi-bin/mmpayweb-bin/checkmweb?prepay_id=wx2016121516420242444321ca0631331346&package=1405458241
   * </pre>
   */
  @SerializedName("h5_url")
  private String h5Url;

  /**
   * <pre>
   * 字段名：二维码链接  （NATIVE支付 会返回）
   * 变量名：h5_url
   * 是否必填：是
   * 类型：string(512)
   * 描述：
   *  二维码链接
   * 示例值：weixin://pay.weixin.qq.com/bizpayurl/up?pr=NwY5Mz9&groupid=00
   * </pre>
   */
  @SerializedName("code_url")
  private String codeUrl;

  @Data
  @Accessors(chain = true)
  public static class JsapiResult implements Serializable {
    private String appId;
    private String timeStamp;
    private String nonceStr;
    private String packageValue;
    private String signType;
    private String paySign;

    private String getSignStr() {
      return String.format("%s\n%s\n%s\n%s\n", appId, timeStamp, nonceStr, packageValue);
    }
  }

  @Data
  @Accessors(chain = true)
  public static class AppResult implements Serializable {
    private String appid;
    private String partnerid;
    private String prepayid;
    private String packageValue;
    private String noncestr;
    private String timestamp;
    private String sign;

    private String getSignStr() {
      return String.format("%s\n%s\n%s\n%s\n", appid, timestamp, noncestr, prepayid);
    }
  }

  public <T> T getPayInfo(TradeTypeEnum tradeType, String appId, String mchId, PrivateKey privateKey) {
    String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
    String nonceStr = SignUtils.genRandomStr();
    switch (tradeType) {
      case JSAPI:
        JsapiResult jsapiResult = new JsapiResult();
        jsapiResult.setAppId(appId).setTimeStamp(timestamp)
          .setPackageValue("prepay_id=" + this.prepayId).setNonceStr(nonceStr)
          //签名类型，默认为RSA，仅支持RSA。
          .setSignType("RSA").setPaySign(SignUtils.sign(jsapiResult.getSignStr(), privateKey));
        return (T) jsapiResult;
      case MWEB:
        return (T) this.h5Url;
      case APP:
        AppResult appResult = new AppResult();
        appResult.setAppid(appId).setPrepayid(this.prepayId).setPartnerid(mchId)
          .setNoncestr(nonceStr).setTimestamp(timestamp)
          //暂填写固定值Sign=WXPay
          .setPackageValue("Sign=WXPay").setSign(SignUtils.sign(appResult.getSignStr(), privateKey));
        return (T) appResult;
      case NATIVE:
        return (T) this.codeUrl;
    }
    return null;
  }
}
