package com.github.binarywang.wxpay.v3.auth;

import org.apache.http.ProtocolVersion;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;
import org.testng.annotations.Test;

import java.io.IOException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * 测试 WxPayValidator 对应答时间戳的新鲜度校验（防重放）。
 *
 * @author sisyfy-Zhang
 */
public class WxPayValidatorTest {

  /**
   * 固定的"当前时间"（秒），便于构造超前/滞后的应答时间戳
   */
  private static final long NOW_SECONDS = 1_700_000_000L;

  private static final long FIVE_MINUTES = TimeUnit.MINUTES.toSeconds(5);
  private static final long SIX_MINUTES = TimeUnit.MINUTES.toSeconds(6);
  private static final long TEN_MINUTES = TimeUnit.MINUTES.toSeconds(10);

  /**
   * 最简 CloseableHttpResponse 实现，仅用于单元测试
   */
  private static class StubCloseableHttpResponse extends BasicHttpResponse implements CloseableHttpResponse {
    StubCloseableHttpResponse() {
      super(new BasicStatusLine(new ProtocolVersion("HTTP", 1, 1), 200, "OK"));
    }

    @Override
    public void close() {
    }
  }

  /**
   * 永远验签通过的 Verifier，用于把测试聚焦在时间戳校验上
   */
  private static final Verifier ALWAYS_VALID = new Verifier() {
    @Override
    public boolean verify(String serialNumber, byte[] message, String signature) {
      return true;
    }

    @Override
    public X509Certificate getValidCertificate() {
      return null;
    }
  };

  private static WxPayValidator validator(long toleranceSeconds) {
    return new WxPayValidator(ALWAYS_VALID, toleranceSeconds, () -> NOW_SECONDS);
  }

  private static CloseableHttpResponse jsonResponse(String timestamp) {
    StubCloseableHttpResponse response = new StubCloseableHttpResponse();
    response.setHeader("Content-Type", ContentType.APPLICATION_JSON.toString());
    if (timestamp != null) {
      response.setHeader("Wechatpay-Timestamp", timestamp);
    }
    response.setHeader("Wechatpay-Nonce", "nonce");
    response.setHeader("Wechatpay-Signature", "signature");
    response.setHeader("Wechatpay-Serial", "SERIAL");
    response.setEntity(new StringEntity("{\"code\":\"OK\"}", ContentType.APPLICATION_JSON));
    return response;
  }

  @Test
  public void testFreshTimestampPasses() throws IOException {
    assertTrue(validator(FIVE_MINUTES).validate(jsonResponse(String.valueOf(NOW_SECONDS))));
  }

  @Test
  public void testTimestampAtToleranceBoundaryPasses() throws IOException {
    assertTrue(validator(FIVE_MINUTES).validate(jsonResponse(String.valueOf(NOW_SECONDS - FIVE_MINUTES))));
    assertTrue(validator(FIVE_MINUTES).validate(jsonResponse(String.valueOf(NOW_SECONDS + FIVE_MINUTES))));
  }

  @Test
  public void testStaleTimestampIsRejected() throws IOException {
    assertFalse(validator(FIVE_MINUTES).validate(jsonResponse(String.valueOf(NOW_SECONDS - SIX_MINUTES))),
      "滞后超过容差的应答应被判定为验签失败");
  }

  @Test
  public void testFutureTimestampIsRejected() throws IOException {
    assertFalse(validator(FIVE_MINUTES).validate(jsonResponse(String.valueOf(NOW_SECONDS + SIX_MINUTES))),
      "超前超过容差的应答应被判定为验签失败");
  }

  @Test
  public void testNonNumericTimestampIsRejected() throws IOException {
    assertFalse(validator(FIVE_MINUTES).validate(jsonResponse("not-a-number")));
  }

  @Test
  public void testMissingTimestampHeaderIsRejected() throws IOException {
    assertFalse(validator(FIVE_MINUTES).validate(jsonResponse(null)));
  }

  @Test
  public void testCustomToleranceIsHonoured() throws IOException {
    assertTrue(validator(TEN_MINUTES).validate(jsonResponse(String.valueOf(NOW_SECONDS - SIX_MINUTES))),
      "容差放宽到 10 分钟后，滞后 6 分钟的应答应通过");
  }

  @Test
  public void testZeroToleranceDisablesTimestampCheck() throws IOException {
    assertTrue(validator(0).validate(jsonResponse(String.valueOf(NOW_SECONDS - TEN_MINUTES))),
      "容差为 0 表示关闭时间戳校验");
    assertTrue(validator(0).validate(jsonResponse("not-a-number")),
      "关闭校验后不再解析时间戳");
  }

  @Test
  public void testDefaultConstructorUsesFiveMinutes() throws IOException {
    long nowSeconds = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
    WxPayValidator validator = new WxPayValidator(ALWAYS_VALID);
    assertTrue(validator.validate(jsonResponse(String.valueOf(nowSeconds))));
    assertFalse(validator.validate(jsonResponse(String.valueOf(nowSeconds - SIX_MINUTES))));
  }

  @Test
  public void testNonJsonResponseSkipsValidation() throws IOException {
    StubCloseableHttpResponse response = new StubCloseableHttpResponse();
    response.setHeader("Content-Type", ContentType.APPLICATION_OCTET_STREAM.toString());
    // 非 JSON 应答（如下载账单）不做验签，这是既有行为，时间戳校验不应改变它
    assertTrue(validator(FIVE_MINUTES).validate(response));
  }
}
