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
   * 变量名：code_url
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
    /**
     * <pre>
     * 字段名：预支付交易会话标识
     * 变量名：prepay_id
     * 是否必填：否（用户可选存储）
     * 类型：string[1,64]
     * 描述：
     *  预支付交易会话标识。用于后续接口调用中使用，该值有效期为2小时
     *  此字段用于支持用户存储prepay_id，以便复用和重新生成支付签名
     *  示例值：wx201410272009395522657a690389285100
     * </pre>
     */
    private String prepayId;

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
    switch (tradeType) {
      case JSAPI:
        return (T) buildJsapiResult(this.prepayId, appId, privateKey);
      case H5:
        return (T) this.h5Url;
      case APP:
        return (T) buildAppResult(this.prepayId, appId, mchId, privateKey);
      case NATIVE:
        return (T) this.codeUrl;
      default:
        throw new WxRuntimeException("不支持的支付类型");
    }
  }

  /**
   * <pre>
   * 根据已有的prepay_id生成JSAPI支付所需的参数对象（解耦版本）
   * 应用场景：
   * 1. 用户已经通过createPartnerOrderV3或unifiedPartnerOrderV3获取了prepay_id
   * 2. 用户希望存储prepay_id用于后续复用
   * 3. 支付失败后，使用存储的prepay_id重新生成支付签名信息
   * 
   * 使用示例：
   * // 步骤1：创建订单并获取prepay_id
   * WxPayUnifiedOrderV3Result result = wxPayService.unifiedPartnerOrderV3(TradeTypeEnum.JSAPI, request);
   * String prepayId = result.getPrepayId();
   * // 存储prepayId到数据库...
   * 
   * // 步骤2：需要支付时，使用存储的prepay_id生成支付信息
   * WxPayUnifiedOrderV3Result.JsapiResult payInfo = WxPayUnifiedOrderV3Result.getJsapiPayInfo(
   *   prepayId, appId, wxPayService.getConfig().getPrivateKey()
   * );
   * </pre>
   *
   * @param prepayId   预支付交易会话标识
   * @param appId      应用ID
   * @param privateKey 商户私钥，用于签名
   * @return JSAPI支付所需的参数对象
   */
  public static JsapiResult getJsapiPayInfo(String prepayId, String appId, PrivateKey privateKey) {
    if (prepayId == null || appId == null || privateKey == null) {
      throw new IllegalArgumentException("prepayId, appId 和 privateKey 不能为空");
    }
    return buildJsapiResult(prepayId, appId, privateKey);
  }

  /**
   * <pre>
   * 根据已有的prepay_id生成APP支付所需的参数对象（解耦版本）
   * 应用场景：
   * 1. 用户已经通过createPartnerOrderV3或unifiedPartnerOrderV3获取了prepay_id
   * 2. 用户希望存储prepay_id用于后续复用
   * 3. 支付失败后，使用存储的prepay_id重新生成支付签名信息
   * 
   * 使用示例：
   * // 步骤1：创建订单并获取prepay_id
   * WxPayUnifiedOrderV3Result result = wxPayService.unifiedPartnerOrderV3(TradeTypeEnum.APP, request);
   * String prepayId = result.getPrepayId();
   * // 存储prepayId到数据库...
   * 
   * // 步骤2：需要支付时，使用存储的prepay_id生成支付信息
   * WxPayUnifiedOrderV3Result.AppResult payInfo = WxPayUnifiedOrderV3Result.getAppPayInfo(
   *   prepayId, appId, mchId, wxPayService.getConfig().getPrivateKey()
   * );
   * </pre>
   *
   * @param prepayId   预支付交易会话标识
   * @param appId      应用ID
   * @param mchId      商户号
   * @param privateKey 商户私钥，用于签名
   * @return APP支付所需的参数对象
   */
  public static AppResult getAppPayInfo(String prepayId, String appId, String mchId, PrivateKey privateKey) {
    if (prepayId == null || appId == null || mchId == null || privateKey == null) {
      throw new IllegalArgumentException("prepayId, appId, mchId 和 privateKey 不能为空");
    }
    return buildAppResult(prepayId, appId, mchId, privateKey);
  }

  /**
   * 构建JSAPI支付结果对象
   *
   * @param prepayId   预支付交易会话标识
   * @param appId      应用ID
   * @param privateKey 商户私钥，用于签名
   * @return JSAPI支付所需的参数对象
   */
  private static JsapiResult buildJsapiResult(String prepayId, String appId, PrivateKey privateKey) {
    String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
    String nonceStr = SignUtils.genRandomStr();
    JsapiResult jsapiResult = new JsapiResult();
    jsapiResult.setAppId(appId).setTimeStamp(timestamp)
      .setPackageValue("prepay_id=" + prepayId).setNonceStr(nonceStr)
      .setPrepayId(prepayId)
      //签名类型，默认为RSA，仅支持RSA。
      .setSignType("RSA").setPaySign(SignUtils.sign(jsapiResult.getSignStr(), privateKey));
    return jsapiResult;
  }

  /**
   * 构建APP支付结果对象
   *
   * @param prepayId   预支付交易会话标识
   * @param appId      应用ID
   * @param mchId      商户号
   * @param privateKey 商户私钥，用于签名
   * @return APP支付所需的参数对象
   */
  private static AppResult buildAppResult(String prepayId, String appId, String mchId, PrivateKey privateKey) {
    String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
    String nonceStr = SignUtils.genRandomStr();
    AppResult appResult = new AppResult();
    appResult.setAppid(appId).setPrepayId(prepayId).setPartnerId(mchId)
      .setNoncestr(nonceStr).setTimestamp(timestamp)
      //暂填写固定值Sign=WXPay
      .setPackageValue("Sign=WXPay")
      .setSign(SignUtils.sign(appResult.getSignStr(), privateKey));
    return appResult;
  }
}
