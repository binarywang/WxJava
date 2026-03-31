package cn.binarywang.wx.miniapp.api.impl;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * 验证小程序 API 签名 payload 格式的单元测试。
 *
 * <p>直接测试 {@link BaseWxMaServiceImpl#buildSignaturePayload} 生产方法，
 * 确保待签名串格式符合微信官方规范：<br>
 * {@code urlpath\nappid\ntimestamp\npostdata}<br>
 * 共 4 个字段，字段间以换行符 {@code \n} 分隔，末尾无额外回车符。
 *
 * <p>修复说明：4.8.0 版本在计算签名时错误地将 rsaKeySn 添加到签名串（5 个字段），
 * 导致微信服务端返回 40234（invalid signature）。此测试验证修复后签名串格式正确（4 个字段，
 * 不含 rsaKeySn），适用于所有走加密访问路径的接口（包括 getPhoneNumber、同城配送等）。
 *
 * @author GitHub Copilot
 * @see <a
 *     href="https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/getting_started/api_signature.html">
 *     微信服务端API签名指南</a>
 */
public class WxMaSignaturePayloadTest {

  private static final String URL_PATH =
      "https://api.weixin.qq.com/cgi-bin/express/intracity/createstore";
  private static final String GET_PHONE_NUMBER_URL_PATH =
      "https://api.weixin.qq.com/wxa/business/getuserphonenumber";
  private static final String APP_ID = "wx1234567890abcdef";
  private static final long TIMESTAMP = 1700000000L;
  private static final String POST_DATA = "{\"iv\":\"abc\",\"data\":\"xyz\",\"authtag\":\"tag\"}";
  private static final String RSA_KEY_SN = "some_serial_number";

  /**
   * 提供不同 API 接口 URL 作为测试数据，用于验证签名串格式在各接口中均符合规范。
   */
  @DataProvider(name = "apiUrlPaths")
  public Object[][] apiUrlPaths() {
    return new Object[][] {
      {URL_PATH, "同城配送 createstore"},
      {GET_PHONE_NUMBER_URL_PATH, "getPhoneNumber"}
    };
  }

  /**
   * 验证 buildSignaturePayload 返回的待签名串恰好包含 4 个字段，
   * 格式为：urlpath\nappid\ntimestamp\npostdata。
   * 使用多个 URL 验证，覆盖同城配送及 getPhoneNumber 等接口（issue 4.8.0 升级后 40234 错误）。
   */
  @Test(dataProvider = "apiUrlPaths")
  public void testPayloadHasExactlyFourFields(String urlPath, String description) {
    String payload =
        BaseWxMaServiceImpl.buildSignaturePayload(urlPath, APP_ID, TIMESTAMP, POST_DATA);

    String[] parts = payload.split("\n", -1);
    assertEquals(parts.length, 4,
        description + " 签名串应恰好包含 4 个字段（urlpath、appid、timestamp、postdata）");
    assertEquals(parts[0], urlPath, description + " 第 1 段应为 urlpath");
    assertEquals(parts[1], APP_ID, description + " 第 2 段应为 appid");
    assertEquals(parts[2], String.valueOf(TIMESTAMP), description + " 第 3 段应为 timestamp");
    assertEquals(parts[3], POST_DATA, description + " 第 4 段应为 postdata");
    assertFalse(payload.contains(RSA_KEY_SN),
        description + " 签名串不应包含 rsaKeySn，rsaKeySn 应通过请求头 Wechatmp-Serial 传递");
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
