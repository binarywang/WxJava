package com.github.binarywang.wxpay.bean.result;

import com.github.binarywang.wxpay.bean.result.enums.TradeTypeEnum;
import com.github.binarywang.wxpay.v3.util.SignUtils;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import me.chanjar.weixin.common.error.WxRuntimeException;

import java.io.Serializable;
import java.security.PrivateKey;

/**
 * <pre>
 * 在发起微信支付前，需要调用统一下单接口，获取"预支付交易会话标识"返回的结果
 * 参考文档：https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_1_1.shtml
 * https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_2_1.shtml
 * https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_3_1.shtml
 * https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_4_1.shtml
 * https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_5_1.shtml
 * </pre>
 *
 * @author thinsstar
 */
@Data
@NoArgsConstructor
public class WxPayUnifiedOrderV3Result implements Serializable {
  private static final long serialVersionUID = 1L;
  /**
   * <pre>
   * 字段名：预支付交易会话标识（APP支付、JSAPI支付 会返回）
   * 变量名：prepay_id
   * 是否必填：是
   * 类型：string[1,64]
   * 描述：
   *  预支付交易会话标识。用于后续接口调用中使用，该值有效期为2小时
   *  示例值：wx201410272009395522657a690389285100
   * </pre>
   */
  @SerializedName("prepay_id")
  private String prepayId;

  /**
   * <pre>
   * 字段名：支付跳转链接（H5支付 会返回）
   * 变量名：h5_url
   * 是否必填：是
   * 类型：string[1,512]
   * 描述：
   *  h5_url为拉起微信支付收银台的中间页面，可通过访问该url来拉起微信客户端，完成支付，h5_url的有效期为5分钟。
   *  示例值：https://wx.tenpay.com/cgi-bin/mmpayweb-bin/checkmweb?prepay_id=wx2016121516420242444321ca0631331346&package=1405458241
   * </pre>
   */
  @SerializedName("h5_url")
  private String h5Url;

  /**
   * <pre>
   * 字段名：二维码链接（NATIVE支付 会返回）
   * 变量名：h5_url
   * 是否必填：是
   * 类型：string[1,512]
   * 描述：
   *  此URL用于生成支付二维码，然后提供给用户扫码支付。
   *  注意：code_url并非固定值，使用时按照URL格式转成二维码即可。
   *  示例值：weixin://wxpay/bizpayurl/up?pr=NwY5Mz9&groupid=00
   * </pre>
   */
  @SerializedName("code_url")
  private String codeUrl;

  @Data
  @Accessors(chain = true)
  public static class JsapiResult implements Serializable {
    private static final long serialVersionUID = 4465376277943307271L;

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
    private static final long serialVersionUID = 5465773025172875110L;

    private String appid;
    private String partnerId;
    private String prepayId;
    private String packageValue;
    private String noncestr;
    private String timestamp;
    private String sign;

    private String getSignStr() {
      return String.format("%s\n%s\n%s\n%s\n", appid, timestamp, noncestr, prepayId);
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
      case H5:
        return (T) this.h5Url;
      case APP:
        AppResult appResult = new AppResult();
        appResult.setAppid(appId).setPrepayId(this.prepayId).setPartnerId(mchId)
          .setNoncestr(nonceStr).setTimestamp(timestamp)
          //暂填写固定值Sign=WXPay
          .setPackageValue("Sign=WXPay")
          .setSign(SignUtils.sign(appResult.getSignStr(), privateKey));
        return (T) appResult;
      case NATIVE:
        return (T) this.codeUrl;
      default:
        throw new WxRuntimeException("不支持的支付类型");
    }
  }
}
