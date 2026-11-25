package com.github.binarywang.wxpay.v3.auth;

import com.github.binarywang.wxpay.v3.util.PemUtils;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Provider;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;
import java.util.Base64;
import java.util.Collections;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class Sm2SignatureTest {
  private static final String SIGNATURE_TYPE = "WECHATPAY2-SM2-WITH-SM3";
  private static final String PUBLIC_KEY_ID = "PUB_KEY_ID_01";
  private static final Provider BOUNCY_CASTLE_PROVIDER = new BouncyCastleProvider();

  private KeyPair newSm2KeyPair() throws Exception {
    KeyPairGenerator generator = KeyPairGenerator.getInstance("EC", BOUNCY_CASTLE_PROVIDER);
    generator.initialize(new ECGenParameterSpec("sm2p256v1"));
    return generator.generateKeyPair();
  }

  @Test
  public void testSm2SignerAndPublicKeyVerifier() throws Exception {
    KeyPair keyPair = newSm2KeyPair();
    byte[] message = "微信支付 V3 SM2 签名".getBytes(StandardCharsets.UTF_8);
    PrivateKeySigner signer = new PrivateKeySigner("serial", keyPair.getPrivate());

    Signer.SignatureResult result = signer.sign(message);
    PublicCertificateVerifier verifier = new PublicCertificateVerifier(keyPair.getPublic(), PUBLIC_KEY_ID);
    WxPayCredentials credentials = new WxPayCredentials("mch", signer);

    assertEquals(signer.getSchema(), SIGNATURE_TYPE);
    assertEquals(credentials.getSchema(), SIGNATURE_TYPE);
    assertTrue(verifier.verify(PUBLIC_KEY_ID, message, result.sign, SIGNATURE_TYPE));
  }

  @Test
  public void testCertificatesVerifierSupportsSm2() throws Exception {
    KeyPair keyPair = newSm2KeyPair();
    byte[] message = "certificate signature".getBytes(StandardCharsets.UTF_8);
    String signed = new PrivateKeySigner("serial", keyPair.getPrivate()).sign(message).sign;
    X509PublicCertificate certificate = new X509PublicCertificate(keyPair.getPublic(), "01");
    CertificatesVerifier verifier = new CertificatesVerifier(Collections.singletonList(certificate));

    assertTrue(verifier.verify(BigInteger.ONE.toString(16), message, signed, SIGNATURE_TYPE));
  }

  @Test
  public void testValidatorUsesResponseSignatureType() throws Exception {
    KeyPair keyPair = newSm2KeyPair();
    String timestamp = "1700000000";
    String nonce = "nonce";
    String body = "{\"code\":\"OK\"}";
    byte[] message = (timestamp + "\n" + nonce + "\n" + body + "\n").getBytes(StandardCharsets.UTF_8);
    String signed = new PrivateKeySigner("serial", keyPair.getPrivate()).sign(message).sign;

    StubCloseableHttpResponse response = new StubCloseableHttpResponse();
    response.setHeader("Content-Type", ContentType.APPLICATION_JSON.toString());
    response.setHeader("Wechatpay-Timestamp", timestamp);
    response.setHeader("Wechatpay-Nonce", nonce);
    response.setHeader("Wechatpay-Signature", signed);
    response.setHeader("Wechatpay-Serial", PUBLIC_KEY_ID);
    response.setHeader("Wechatpay-Signature-Type", SIGNATURE_TYPE);
    response.setEntity(new StringEntity(body, ContentType.APPLICATION_JSON));

    WxPayValidator validator = new WxPayValidator(
      new PublicCertificateVerifier(keyPair.getPublic(), PUBLIC_KEY_ID), 0);
    assertTrue(validator.validate(response));
  }

  @Test
  public void testValidatorRejectsUnknownSignatureType() throws Exception {
    StubCloseableHttpResponse response = new StubCloseableHttpResponse();
    response.setHeader("Content-Type", ContentType.APPLICATION_JSON.toString());
    response.setHeader("Wechatpay-Timestamp", "1700000000");
    response.setHeader("Wechatpay-Nonce", "nonce");
    response.setHeader("Wechatpay-Signature", "signature");
    response.setHeader("Wechatpay-Serial", PUBLIC_KEY_ID);
    response.setHeader("Wechatpay-Signature-Type", "UNKNOWN");
    response.setEntity(new StringEntity("{}", ContentType.APPLICATION_JSON));
    WxPayValidator validator = new WxPayValidator(new PublicCertificateVerifier(null, PUBLIC_KEY_ID), 0);

    assertFalse(validator.validate(response));
  }

  @Test
  public void testRsaSignerRemainsCompatible() throws Exception {
    KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
    generator.initialize(2048);
    KeyPair keyPair = generator.generateKeyPair();
    byte[] message = "RSA compatibility".getBytes(StandardCharsets.UTF_8);
    PrivateKeySigner signer = new PrivateKeySigner("serial", keyPair.getPrivate());
    String signed = signer.sign(message).sign;

    Signature verifier = Signature.getInstance("SHA256withRSA");
    verifier.initVerify(keyPair.getPublic());
    verifier.update(message);
    assertEquals(signer.getSchema(), "WECHATPAY2-SHA256-RSA2048");
    assertTrue(verifier.verify(Base64.getDecoder().decode(signed)));
  }

  @Test
  public void testPemUtilsLoadsSm2Keys() throws Exception {
    KeyPair keyPair = newSm2KeyPair();
    String privateKeyPem = pem("PRIVATE KEY", keyPair.getPrivate().getEncoded());
    String publicKeyPem = pem("PUBLIC KEY", keyPair.getPublic().getEncoded());

    assertEquals(PemUtils.loadPrivateKey(inputStream(privateKeyPem)).getAlgorithm(), "EC");
    assertEquals(PemUtils.loadPublicKey(inputStream(publicKeyPem)).getAlgorithm(), "EC");
  }

  private String pem(String type, byte[] encodedKey) {
    return "-----BEGIN " + type + "-----\n"
      + Base64.getMimeEncoder(64, new byte[] {'\n'}).encodeToString(encodedKey)
      + "\n-----END " + type + "-----";
  }

  private ByteArrayInputStream inputStream(String pem) {
    return new ByteArrayInputStream(pem.getBytes(StandardCharsets.UTF_8));
  }

  private static class StubCloseableHttpResponse extends BasicHttpResponse implements CloseableHttpResponse {
    StubCloseableHttpResponse() {
      super(new BasicStatusLine(new ProtocolVersion("HTTP", 1, 1), 200, "OK"));
    }

    @Override
    public void close() {
    }
  }
}
