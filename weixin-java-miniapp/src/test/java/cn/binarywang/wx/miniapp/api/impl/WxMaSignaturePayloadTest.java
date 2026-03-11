package cn.binarywang.wx.miniapp.api.impl;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

import org.testng.annotations.Test;

/**
 * 验证同城配送 API 签名 payload 格式的单元测试。
 *
 * <p>直接测试 {@link BaseWxMaServiceImpl#buildSignaturePayload} 生产方法，
 * 确保待签名串格式符合微信官方规范：<br>
 * {@code urlpath\nappid\ntimestamp\npostdata}<br>
 * 共 4 个字段，字段间以换行符 {@code \n} 分隔，末尾无额外回车符。
 *
 * @author GitHub Copilot
 * @see <a
 *     href="https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/getting_started/api_signature.html">
 *     微信服务端API签名指南</a>
 */
public class WxMaSignaturePayloadTest {

  private static final String URL_PATH =
      "https://api.weixin.qq.com/cgi-bin/express/intracity/createstore";
  private static final String APP_ID = "wx1234567890abcdef";
  private static final long TIMESTAMP = 1700000000L;
  private static final String POST_DATA = "{\"iv\":\"abc\",\"data\":\"xyz\",\"authtag\":\"tag\"}";
  private static final String RSA_KEY_SN = "some_serial_number";

  /**
   * 验证 buildSignaturePayload 返回的待签名串恰好包含 4 个字段，
   * 格式为：urlpath\nappid\ntimestamp\npostdata
   */
  @Test
  public void testPayloadHasExactlyFourFields() {
    String payload =
        BaseWxMaServiceImpl.buildSignaturePayload(URL_PATH, APP_ID, TIMESTAMP, POST_DATA);

    String[] parts = payload.split("\n", -1);
    assertEquals(parts.length, 4, "待签名串应恰好包含 4 个字段（urlpath、appid、timestamp、postdata）");
    assertEquals(parts[0], URL_PATH, "第 1 段应为 urlpath");
    assertEquals(parts[1], APP_ID, "第 2 段应为 appid");
    assertEquals(parts[2], String.valueOf(TIMESTAMP), "第 3 段应为 timestamp");
    assertEquals(parts[3], POST_DATA, "第 4 段应为 postdata");
  }

  /**
   * 验证 buildSignaturePayload 返回的待签名串不包含 rsaKeySn。
   * rsaKeySn 应通过请求头 Wechatmp-Serial 传递，而不应出现在签名 payload 中。
   */
  @Test
  public void testPayloadDoesNotContainRsaKeySn() {
    String payload =
        BaseWxMaServiceImpl.buildSignaturePayload(URL_PATH, APP_ID, TIMESTAMP, POST_DATA);

    assertFalse(payload.contains(RSA_KEY_SN),
        "待签名串不应包含 rsaKeySn，rsaKeySn 应通过请求头 Wechatmp-Serial 传递");
  }

  /**
   * 验证 buildSignaturePayload 返回的待签名串与预期格式完全一致。
   */
  @Test
  public void testPayloadMatchesExpectedFormat() {
    String expected = URL_PATH + "\n" + APP_ID + "\n" + TIMESTAMP + "\n" + POST_DATA;
    String actual =
        BaseWxMaServiceImpl.buildSignaturePayload(URL_PATH, APP_ID, TIMESTAMP, POST_DATA);

    assertEquals(actual, expected, "待签名串格式应为：urlpath\\nappid\\ntimestamp\\npostdata");
  }
}
