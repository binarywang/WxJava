package com.github.binarywang.wxpay.v3.auth;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.charset.StandardCharsets;
import java.security.cert.X509Certificate;

import static org.testng.Assert.*;

/**
 * 测试公钥模式下 AutoUpdateCertificatesVerifier 的健壮性
 *
 * @author copilot
 */
public class AutoUpdateCertificatesVerifierPublicKeyModeTest {

  private String invalidMchId;
  private String invalidApiV3Key;
  private String invalidCertSerialNo;
  private String payBaseUrl;
  private WxPayCredentials credentials;

  @BeforeMethod
  public void setUp() {
    // 使用无效的配置，模拟证书下载失败的场景
    invalidMchId = "invalid_mch_id";
    invalidApiV3Key = "invalid_api_v3_key_must_be_32_b";
    invalidCertSerialNo = "invalid_serial_no";
    payBaseUrl = "https://api.mch.weixin.qq.com";

    credentials = new WxPayCredentials(
      invalidMchId,
      new PrivateKeySigner(invalidCertSerialNo, null)
    );
  }

  /**
   * 测试当证书下载失败时，构造函数不应该抛出异常
   * 这是为了支持公钥模式下的场景，在公钥模式下商户可能没有平台证书
   */
  @Test
  public void testConstructorShouldNotThrowExceptionWhenCertDownloadFails() {
    // 构造函数应该不抛出异常，即使证书下载失败
    AutoUpdateCertificatesVerifier verifier = new AutoUpdateCertificatesVerifier(
      credentials,
      invalidApiV3Key.getBytes(StandardCharsets.UTF_8),
      60,
      payBaseUrl,
      null
    );
    // 如果没有抛出异常，测试通过
    assertNotNull(verifier);
  }

  /**
   * 测试当没有有效证书时，verify 方法应该返回 false 而不是抛出异常
   */
  @Test
  public void testVerifyShouldReturnFalseWhenNoCertificateAvailable() {
    AutoUpdateCertificatesVerifier verifier = new AutoUpdateCertificatesVerifier(
      credentials,
      invalidApiV3Key.getBytes(StandardCharsets.UTF_8),
      60,
      payBaseUrl,
      null
    );

    // verify 方法应该返回 false，而不是抛出异常
    boolean result = verifier.verify("test_serial", "test_message".getBytes(), "test_signature");
    assertFalse(result, "当没有有效证书时，verify 应该返回 false");
  }

  /**
   * 测试当没有有效证书时，getValidCertificate 方法应该抛出有意义的异常
   */
  @Test(expectedExceptions = me.chanjar.weixin.common.error.WxRuntimeException.class,
    expectedExceptionsMessageRegExp = ".*No valid certificate available.*")
  public void testGetValidCertificateShouldThrowMeaningfulException() {
    AutoUpdateCertificatesVerifier verifier = new AutoUpdateCertificatesVerifier(
      credentials,
      invalidApiV3Key.getBytes(StandardCharsets.UTF_8),
      60,
      payBaseUrl,
      null
    );

    // 应该抛出有意义的异常
    X509Certificate certificate = verifier.getValidCertificate();
  }
}
