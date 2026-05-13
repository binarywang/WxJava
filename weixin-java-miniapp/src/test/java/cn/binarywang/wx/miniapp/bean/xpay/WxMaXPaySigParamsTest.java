package cn.binarywang.wx.miniapp.bean.xpay;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * 验证 {@link WxMaXPaySigParams} 签名方法的单元测试。
 *
 * <p>修复说明：{@link WxMaXPaySigParams#calcSig(String)} 方法计算用户态签名（HMAC-SHA256）后
 * 未转小写，导致微信小程序端返回 {@code SIGNATURE_INVALID -15005} 错误。
 * 修复方案与 {@link WxMaXPaySigParams#calcPaySig(String, String)} 保持一致，
 * 在返回前调用 {@code .toLowerCase()}。
 *
 * @author GitHub Copilot
 */
public class WxMaXPaySigParamsTest {

  private static final String SESSION_KEY = "your_session_key";
  private static final String APP_KEY = "your_app_key";
  private static final String POST_BODY = "{\"openid\":\"oHoSt5abc123\",\"env\":1}";
  private static final String URL = "https://api.weixin.qq.com/xpay/query_user_balance";

  /**
   * 验证 calcSig 返回值全部为小写十六进制字符，不包含大写字母。
   */
  @Test
  public void testCalcSigReturnsLowerCase() {
    WxMaXPaySigParams sigParams = WxMaXPaySigParams.builder()
      .sessionKey(SESSION_KEY)
      .appKey(APP_KEY)
      .build();

    String sig = sigParams.calcSig(POST_BODY);

    assertTrue(sig.equals(sig.toLowerCase()),
      "calcSig 返回值应为全小写，当前值: " + sig);
  }

  /**
   * 验证 calcPaySig 返回值全部为小写十六进制字符，不包含大写字母。
   */
  @Test
  public void testCalcPaySigReturnsLowerCase() {
    WxMaXPaySigParams sigParams = WxMaXPaySigParams.builder()
      .sessionKey(SESSION_KEY)
      .appKey(APP_KEY)
      .build();

    String paySig = sigParams.calcPaySig(URL, POST_BODY);

    assertTrue(paySig.equals(paySig.toLowerCase()),
      "calcPaySig 返回值应为全小写，当前值: " + paySig);
  }

  /**
   * 验证 calcSig 与 calcPaySig 的返回值均为有效的 HMAC-SHA256 十六进制字符串（64 个字符）。
   */
  @Test
  public void testCalcSigIsValidHexString() {
    WxMaXPaySigParams sigParams = WxMaXPaySigParams.builder()
      .sessionKey(SESSION_KEY)
      .appKey(APP_KEY)
      .build();

    String sig = sigParams.calcSig(POST_BODY);
    String paySig = sigParams.calcPaySig(URL, POST_BODY);

    assertEquals(sig.length(), 64, "HMAC-SHA256 签名应为 64 个十六进制字符");
    assertEquals(paySig.length(), 64, "HMAC-SHA256 pay_sig 应为 64 个十六进制字符");
    assertTrue(sig.matches("[0-9a-f]+"), "calcSig 返回值应只含小写十六进制字符");
    assertTrue(paySig.matches("[0-9a-f]+"), "calcPaySig 返回值应只含小写十六进制字符");
  }
}
