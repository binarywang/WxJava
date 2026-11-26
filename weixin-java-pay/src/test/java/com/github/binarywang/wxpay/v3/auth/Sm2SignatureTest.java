package com.github.binarywang.wxpay.v3.auth;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.v3.WxPayV3HttpClientBuilder;
import com.github.binarywang.wxpay.v3.util.PemUtils;
import com.sun.net.httpserver.HttpServer;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;
import org.apache.http.util.EntityUtils;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.jcajce.spec.SM2ParameterSpec;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Provider;
import java.security.Signature;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.ECGenParameterSpec;
import java.time.Instant;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class Sm2SignatureTest {
  private static final String SIGNATURE_TYPE = "WECHATPAY2-SM2-WITH-SM3";
  private static final String RSA_SIGNATURE_TYPE = "WECHATPAY2-SHA256-RSA2048";
  private static final String RESPONSE_TIMESTAMP = "1700000000";
  private static final String RESPONSE_NONCE = "nonce";
  private static final String RESPONSE_BODY = "{\"code\":\"OK\"}";
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
  public void testSyntheticPublicKeyModeVerifiesSm2Response() throws Exception {
    KeyPair merchant = newSm2KeyPair();
    KeyPair platform = newSm2KeyPair();
    WxPayConfig config = new WxPayConfig();
    config.setMchId("test-merchant");
    config.setApiV3Key("0123456789abcdef0123456789abcdef");
    config.setCertSerialNo("01");
    config.setPrivateKeyString(pem("PRIVATE KEY", merchant.getPrivate().getEncoded()));
    config.setPublicKeyString(pem("PUBLIC KEY", platform.getPublic().getEncoded()));
    config.setPublicKeyId(PUBLIC_KEY_ID);
    config.setFullPublicKeyModel(true);

    try (CloseableHttpClient ignored = config.initApiV3HttpClient()) {
      WxPayValidator validator = new WxPayValidator(config.getVerifier(), 0);
      String signature = signResponse(platform, true);
      assertTrue(validator.validate(signedResponse(PUBLIC_KEY_ID, SIGNATURE_TYPE, signature, RESPONSE_BODY)));
      assertFalse(validator.validate(signedResponse(PUBLIC_KEY_ID, SIGNATURE_TYPE, signature, "{}")));
    }
  }

  @Test
  public void testHttpClientSignsAndVerifiesWithPreloadedSm2PlatformCertificate() throws Exception {
    KeyPair merchant = newSm2KeyPair();
    KeyPair platform = newSm2KeyPair();
    X500Name subject = new X500Name("CN=Offline SM2 platform certificate");
    Instant now = Instant.now();
    byte[] encoded = new JcaX509v3CertificateBuilder(subject, BigInteger.valueOf(42),
      Date.from(now.minusSeconds(3600)), Date.from(now.plusSeconds(3600)), subject, platform.getPublic())
      .build(new JcaContentSignerBuilder("SM3withSM2").setProvider(BOUNCY_CASTLE_PROVIDER).build(platform.getPrivate()))
      .getEncoded();
    X509Certificate certificate = (X509Certificate) CertificateFactory
      .getInstance("X.509", BOUNCY_CASTLE_PROVIDER).generateCertificate(inputStream(pem("CERTIFICATE", encoded)));
    certificate.checkValidity();
    String peerSignature = signResponse(platform, true);
    AtomicReference<String> authorization = new AtomicReference<>();
    HttpServer server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
    try {
      server.createContext("/v3/offline-fixture", exchange -> {
        authorization.set(exchange.getRequestHeaders().getFirst("Authorization"));
        exchange.getResponseHeaders().set("Content-Type", ContentType.APPLICATION_JSON.toString());
        exchange.getResponseHeaders().set("Wechatpay-Timestamp", RESPONSE_TIMESTAMP);
        exchange.getResponseHeaders().set("Wechatpay-Nonce", RESPONSE_NONCE);
        exchange.getResponseHeaders().set("Wechatpay-Signature", peerSignature);
        exchange.getResponseHeaders().set("Wechatpay-Signature-Type", SIGNATURE_TYPE);
        exchange.getResponseHeaders().set("Wechatpay-Serial", "2A");
        byte[] body = RESPONSE_BODY.getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(200, body.length);
        try (OutputStream out = exchange.getResponseBody()) {
          out.write(body);
        } finally {
          exchange.close();
        }
      });
      server.start();
      WxPayV3HttpClientBuilder builder = WxPayV3HttpClientBuilder.create()
        .withMerchant("test-merchant", "01", merchant.getPrivate())
        .withValidator(new WxPayValidator(new CertificatesVerifier(Collections.singletonList(certificate)),
          WxPayValidator.DEFAULT_TIMESTAMP_TOLERANCE_SECONDS, () -> Long.parseLong(RESPONSE_TIMESTAMP)))
        .withTrustedHost("127.0.0.1");
      builder.setDefaultRequestConfig(RequestConfig.custom().setConnectTimeout(3000).setSocketTimeout(3000).build());
      try (CloseableHttpClient client = builder.build();
           CloseableHttpResponse response = client.execute(
             new HttpGet("http://127.0.0.1:" + server.getAddress().getPort() + "/v3/offline-fixture"))) {
        assertEquals(response.getStatusLine().getStatusCode(), 200);
        assertEquals(EntityUtils.toString(response.getEntity()), RESPONSE_BODY);
      }
      assertTrue(authorization.get() != null && authorization.get().startsWith(SIGNATURE_TYPE + " "));
    } finally {
      server.stop(0);
    }
  }

  @Test
  public void testCertificateAndHybridVerifiersReceiveSm2ResponseType() throws Exception {
    KeyPair platform = newSm2KeyPair();
    AutoUpdateCertificatesVerifier wrapper = certificateWrapper(platform);
    PublicCertificateVerifier hybrid = new PublicCertificateVerifier(newSm2KeyPair().getPublic(), PUBLIC_KEY_ID);
    hybrid.setOtherVerifier(wrapper);
    String signature = signResponse(platform, true);
    // The separately generated hybrid public key cannot validate this certificate's signature.
    assertTrue(new WxPayValidator(wrapper, 0).validate(signedResponse("1", SIGNATURE_TYPE, signature, RESPONSE_BODY)));
    assertTrue(new WxPayValidator(hybrid, 0).validate(signedResponse("1", SIGNATURE_TYPE, signature, RESPONSE_BODY)));
  }

  @Test
  public void testCertificateVerifierPreservesLegacyRsaResponseType() throws Exception {
    KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
    generator.initialize(2048);
    KeyPair platform = generator.generateKeyPair();
    WxPayValidator validator = new WxPayValidator(certificateWrapper(platform), 0);
    String signature = signResponse(platform, false);

    assertTrue(validator.validate(signedResponse("1", null, signature, RESPONSE_BODY)));
    assertTrue(validator.validate(signedResponse("1", RSA_SIGNATURE_TYPE, signature, RESPONSE_BODY)));
  }

  @Test
  public void testLegacyVerifierCannotSilentlyIgnoreExplicitSm2Type() {
    Verifier legacy = new Verifier() {
      @Override
      public boolean verify(String serialNumber, byte[] message, String signature) {
        return true;
      }

      @Override
      public X509Certificate getValidCertificate() {
        return null;
      }
    };

    assertTrue(legacy.verify("1", new byte[0], "", null));
    assertTrue(legacy.verify("1", new byte[0], "", RSA_SIGNATURE_TYPE));
    assertFalse(legacy.verify("1", new byte[0], "", SIGNATURE_TYPE));
  }

  private AutoUpdateCertificatesVerifier certificateWrapper(KeyPair platform) throws Exception {
    WxPayCredentials credentials = new WxPayCredentials("test-merchant", new PrivateKeySigner("01", platform.getPrivate()));
    AutoUpdateCertificatesVerifier wrapper = new AutoUpdateCertificatesVerifier(
      credentials, new byte[32], "https://example.invalid") {
      @Override
      public void customHttpClientBuilder(WxPayV3HttpClientBuilder builder) {
        builder.addInterceptorFirst((HttpRequestInterceptor) (request, context) -> {
          throw new IOException("offline certificate fixture");
        });
      }
    };
    // Seed a cached certificate to isolate verification from the out-of-scope certificate download.
    Field verifier = AutoUpdateCertificatesVerifier.class.getDeclaredField("verifier");
    verifier.setAccessible(true);
    verifier.set(wrapper, new CertificatesVerifier(Collections.singletonList(
      new X509PublicCertificate(platform.getPublic(), "01"))));
    Field instant = AutoUpdateCertificatesVerifier.class.getDeclaredField("instant");
    instant.setAccessible(true);
    instant.set(wrapper, Instant.now());
    return wrapper;
  }

  private String signResponse(KeyPair keyPair, boolean sm2) throws Exception {
    // Build the peer's signature from the protocol rather than using the production signer.
    Signature signer = sm2
      ? Signature.getInstance("SM3withSM2", BOUNCY_CASTLE_PROVIDER)
      : Signature.getInstance("SHA256withRSA");
    if (sm2) {
      signer.setParameter(new SM2ParameterSpec("1234567812345678".getBytes(StandardCharsets.US_ASCII)));
    }
    signer.initSign(keyPair.getPrivate());
    signer.update((RESPONSE_TIMESTAMP + "\n" + RESPONSE_NONCE + "\n" + RESPONSE_BODY + "\n")
      .getBytes(StandardCharsets.UTF_8));
    return Base64.getEncoder().encodeToString(signer.sign());
  }

  private StubCloseableHttpResponse signedResponse(String serial, String type, String signature, String body) {
    StubCloseableHttpResponse response = new StubCloseableHttpResponse();
    response.setHeader("Content-Type", ContentType.APPLICATION_JSON.toString());
    response.setHeader("Wechatpay-Timestamp", RESPONSE_TIMESTAMP);
    response.setHeader("Wechatpay-Nonce", RESPONSE_NONCE);
    response.setHeader("Wechatpay-Signature", signature);
    response.setHeader("Wechatpay-Serial", serial);
    if (type != null) {
      response.setHeader("Wechatpay-Signature-Type", type);
    }
    response.setEntity(new StringEntity(body, ContentType.APPLICATION_JSON));
    return response;
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
