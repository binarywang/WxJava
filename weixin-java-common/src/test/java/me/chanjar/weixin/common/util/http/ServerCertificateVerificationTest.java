package me.chanjar.weixin.common.util.http;

import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsServer;
import me.chanjar.weixin.common.util.http.apache.DefaultApacheHttpClientBuilder;
import me.chanjar.weixin.common.util.http.hc.DefaultHttpComponentsClientBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.math.BigInteger;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Date;

/**
 * 验证默认情况下会校验服务器端证书（避免中间人攻击），且显式跳过校验的开关确实生效.
 */
public class ServerCertificateVerificationTest {

  private static final char[] KEY_STORE_PASSWORD = "wxjava".toCharArray();

  private HttpsServer httpsServer;
  private String selfSignedUrl;

  @BeforeClass
  public void startSelfSignedHttpsServer() throws Exception {
    KeyStore keyStore = createSelfSignedKeyStore();
    KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
    keyManagerFactory.init(keyStore, KEY_STORE_PASSWORD);

    SSLContext sslContext = SSLContext.getInstance("TLS");
    sslContext.init(keyManagerFactory.getKeyManagers(), null, null);

    this.httpsServer = HttpsServer.create(new InetSocketAddress(0), 0);
    this.httpsServer.setHttpsConfigurator(new HttpsConfigurator(sslContext));
    this.httpsServer.createContext("/", exchange -> {
      byte[] body = "ok".getBytes(StandardCharsets.UTF_8);
      exchange.sendResponseHeaders(200, body.length);
      try (OutputStream out = exchange.getResponseBody()) {
        out.write(body);
      }
    });
    this.httpsServer.start();
    this.selfSignedUrl = "https://localhost:" + this.httpsServer.getAddress().getPort() + "/";
  }

  @AfterClass(alwaysRun = true)
  public void stopSelfSignedHttpsServer() {
    if (this.httpsServer != null) {
      this.httpsServer.stop(0);
    }
  }

  @Test
  public void testApacheBuilderRejectsUntrustedCertificateByDefault() throws Exception {
    DefaultApacheHttpClientBuilder builder = newApacheBuilder();
    Assert.assertFalse(builder.isSkipServerCertificateVerification(), "默认应校验服务器端证书");

    try (CloseableHttpClient client = builder.build()) {
      client.execute(new HttpGet(this.selfSignedUrl));
      Assert.fail("默认配置下应拒绝自签名证书");
    } catch (SSLException e) {
      // 期望的结果：证书校验失败
    }
  }

  @Test
  public void testApacheBuilderAcceptsUntrustedCertificateWhenSkipEnabled() throws Exception {
    DefaultApacheHttpClientBuilder builder = newApacheBuilder();
    builder.setSkipServerCertificateVerification(true);

    try (CloseableHttpClient client = builder.build();
         CloseableHttpResponse response = client.execute(new HttpGet(this.selfSignedUrl))) {
      Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
    }
  }

  @Test
  public void testHttpComponentsBuilderRejectsUntrustedCertificateByDefault() throws Exception {
    DefaultHttpComponentsClientBuilder builder = newHttpComponentsBuilder();
    Assert.assertFalse(builder.isSkipServerCertificateVerification(), "默认应校验服务器端证书");

    try (org.apache.hc.client5.http.impl.classic.CloseableHttpClient client = builder.build()) {
      client.execute(new org.apache.hc.client5.http.classic.methods.HttpGet(this.selfSignedUrl));
      Assert.fail("默认配置下应拒绝自签名证书");
    } catch (SSLException e) {
      // 期望的结果：证书校验失败
    }
  }

  @Test
  public void testHttpComponentsBuilderAcceptsUntrustedCertificateWhenSkipEnabled() throws Exception {
    DefaultHttpComponentsClientBuilder builder = newHttpComponentsBuilder();
    builder.setSkipServerCertificateVerification(true);

    try (org.apache.hc.client5.http.impl.classic.CloseableHttpClient client = builder.build();
         org.apache.hc.client5.http.impl.classic.CloseableHttpResponse response =
           client.execute(new org.apache.hc.client5.http.classic.methods.HttpGet(this.selfSignedUrl))) {
      Assert.assertEquals(response.getCode(), 200);
    }
  }

  private DefaultApacheHttpClientBuilder newApacheBuilder() throws Exception {
    Constructor<DefaultApacheHttpClientBuilder> constructor =
      DefaultApacheHttpClientBuilder.class.getDeclaredConstructor();
    constructor.setAccessible(true);
    return constructor.newInstance();
  }

  private DefaultHttpComponentsClientBuilder newHttpComponentsBuilder() throws Exception {
    Constructor<DefaultHttpComponentsClientBuilder> constructor =
      DefaultHttpComponentsClientBuilder.class.getDeclaredConstructor();
    constructor.setAccessible(true);
    return constructor.newInstance();
  }

  private KeyStore createSelfSignedKeyStore() throws Exception {
    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
    keyPairGenerator.initialize(2048);
    KeyPair keyPair = keyPairGenerator.generateKeyPair();

    long now = System.currentTimeMillis();
    X500Name subject = new X500Name("CN=localhost");
    X509Certificate certificate = new JcaX509CertificateConverter().getCertificate(
      new JcaX509v3CertificateBuilder(subject, BigInteger.valueOf(now),
        new Date(now - 86400_000L), new Date(now + 86400_000L), subject, keyPair.getPublic())
        .build(new JcaContentSignerBuilder("SHA256withRSA").build(keyPair.getPrivate())));

    KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
    keyStore.load(null, null);
    keyStore.setKeyEntry("wxjava-test", keyPair.getPrivate(), KEY_STORE_PASSWORD,
      new Certificate[]{certificate});
    return keyStore;
  }
}
