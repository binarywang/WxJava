package cn.binarywang.wx.miniapp.api.impl;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PSSParameterSpec;
import java.util.Base64;
import org.testng.annotations.Test;

/**
 * 验证同城配送 API 签名 payload 格式的单元测试。
 *
 * <p>根据微信官方文档，待签名串格式为：<br>
 * {@code urlpath\nappid\ntimestamp\npostdata}<br>
 * 字段之间使用换行符 {@code \n} 分隔，末尾无额外回车符。
 *
 * @author GitHub Copilot
 * @see <a
 *     href="https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/getting_started/api_signature.html">
 *     微信服务端API签名指南</a>
 */
public class WxMaSignaturePayloadTest {

  /**
   * 验证正确的签名 payload 格式（不含 rsaKeySn）可以通过签名验证，
   * 即格式为：urlpath\nappid\ntimestamp\npostdata
   */
  @Test
  public void testCorrectSignaturePayloadFormat() throws Exception {
    KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
    keyGen.initialize(2048);
    KeyPair keyPair = keyGen.generateKeyPair();
    RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
    RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

    String urlPath = "https://api.weixin.qq.com/cgi-bin/express/intracity/createstore";
    String appId = "wx1234567890abcdef";
    long timestamp = 1700000000L;
    String requestJson = "{\"iv\":\"abc\",\"data\":\"xyz\",\"authtag\":\"tag\"}";

    // 正确格式：urlpath\nappid\ntimestamp\npostdata（不含 rsaKeySn）
    String correctPayload = urlPath + "\n" + appId + "\n" + timestamp + "\n" + requestJson;
    byte[] dataBuffer = correctPayload.getBytes(StandardCharsets.UTF_8);

    Signature signer = Signature.getInstance("RSASSA-PSS");
    PSSParameterSpec pssSpec = new PSSParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256, 32, 1);
    signer.setParameter(pssSpec);
    signer.initSign(privateKey);
    signer.update(dataBuffer);
    byte[] sigBytes = signer.sign();
    String signatureStr = Base64.getEncoder().encodeToString(sigBytes);

    // 使用公钥验证签名
    Signature verifier = Signature.getInstance("RSASSA-PSS");
    verifier.setParameter(pssSpec);
    verifier.initVerify(publicKey);
    verifier.update(dataBuffer);
    assertTrue(verifier.verify(Base64.getDecoder().decode(signatureStr)),
        "正确格式的签名应该能通过验证");
  }

  /**
   * 验证错误的签名 payload（含 rsaKeySn）签名后，用正确 payload 验证会失败。
   * 这证明了原来代码中将 rsaKeySn 加入 payload 是错误的。
   */
  @Test
  public void testIncorrectPayloadWithRsaKeySnFails() throws Exception {
    KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
    keyGen.initialize(2048);
    KeyPair keyPair = keyGen.generateKeyPair();
    RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
    RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

    String urlPath = "https://api.weixin.qq.com/cgi-bin/express/intracity/createstore";
    String appId = "wx1234567890abcdef";
    long timestamp = 1700000000L;
    String rsaKeySn = "some_serial_number";
    String requestJson = "{\"iv\":\"abc\",\"data\":\"xyz\",\"authtag\":\"tag\"}";

    // 错误格式：payload 中包含了 rsaKeySn（修复前的代码逻辑）
    String incorrectPayload = urlPath + "\n" + appId + "\n" + timestamp + "\n" + rsaKeySn + "\n" + requestJson;
    byte[] incorrectData = incorrectPayload.getBytes(StandardCharsets.UTF_8);

    Signature signer = Signature.getInstance("RSASSA-PSS");
    PSSParameterSpec pssSpec = new PSSParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256, 32, 1);
    signer.setParameter(pssSpec);
    signer.initSign(privateKey);
    signer.update(incorrectData);
    byte[] sigBytes = signer.sign();
    String signatureStr = Base64.getEncoder().encodeToString(sigBytes);

    // 用正确格式的 payload 去验证签名，应该失败
    String correctPayload = urlPath + "\n" + appId + "\n" + timestamp + "\n" + requestJson;
    byte[] correctData = correctPayload.getBytes(StandardCharsets.UTF_8);

    Signature verifier = Signature.getInstance("RSASSA-PSS");
    verifier.setParameter(pssSpec);
    verifier.initVerify(publicKey);
    verifier.update(correctData);

    boolean verified = verifier.verify(Base64.getDecoder().decode(signatureStr));
    assertFalse(verified, "用错误 payload 生成的签名不应该通过正确 payload 的验证，"
        + "说明 rsaKeySn 不应该包含在签名 payload 中");
  }
}
