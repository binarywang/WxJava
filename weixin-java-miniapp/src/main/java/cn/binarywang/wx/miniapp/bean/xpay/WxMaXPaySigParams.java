package cn.binarywang.wx.miniapp.bean.xpay;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.common.util.SignUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaXPaySigParams implements Serializable {
  private static final long serialVersionUID = 7495157056049312108L;

  private String sessionKey;
  private String appKey;

  public String signUriWithBoth(String url, String postData) {
    final String sig = this.calcSig(postData);
    final String paySig = this.calcPaySig(url, postData);
    return String.format(url, paySig, sig);
  }

  public String signUriWithPay(String url, String postData) {
    final String paySig = this.calcPaySig(url, postData);
    return String.format(url, paySig);
  }

  public String signUriWithUser(String url, String postData) {
    final String sig = this.calcSig(postData);
    final String uri = String.format(url, sig);
    return uri;
  }

  protected String convUrlToSigUri(String url) {
    if (url == null) return "";

    String t = url.replace("https://api.weixin.qq.com", "");
    if (t.contains("?")) {
      t = t.substring(0, t.indexOf("?"));
    }
    return t;
  }

  public String calcPaySig(String url, String postBody) {
    String ak = StringUtils.trimToEmpty(this.appKey);
    final String sigUri = convUrlToSigUri(url);
    final String paySig = calcPaySignature(sigUri, postBody, ak);
    return paySig.toLowerCase();
  }

  public String calcSig(String postBody) {
    String sk = StringUtils.trimToEmpty(this.sessionKey);
    return calcSignature(postBody, sk).toLowerCase();
  }

  /**
   * 用户登录态signature签名算法
   *
   * @param postBody   - http POST的数据包体
   * @param sessionKey - 当前用户有效的session_key，参考auth.code2Session接口
   * @return 用户登录态签名signature
   */
  protected String calcSignature(String postBody, String sessionKey) {
    return SignUtils.createHmacSha256Sign(postBody, sessionKey);
  }

  /**
   * pay_sig签名算法
   *
   * @param uri      - 当前请求的API的uri部分，不带query_string 例如：/xpay/query_user_balance
   * @param postBody - http POST的数据包体
   * @param appKey   - 对应环境的AppKey
   * @return 支付请求签名pay_sig
   */
  protected String calcPaySignature(String uri, String postBody, String appKey) {
    String needSignData = uri + '&' + postBody;
    return SignUtils.createHmacSha256Sign(needSignData, appKey);
  }
}
