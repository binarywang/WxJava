package com.github.binarywang.wxpay.v3.auth;

import com.github.binarywang.wxpay.config.WxPayHttpProxy;
import com.github.binarywang.wxpay.util.HttpProxyUtils;
import com.github.binarywang.wxpay.v3.Credentials;
import com.github.binarywang.wxpay.v3.WxPayV3HttpClientBuilder;
import com.github.binarywang.wxpay.v3.util.AesUtils;
import com.github.binarywang.wxpay.v3.util.PemUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxRuntimeException;
import me.chanjar.weixin.common.util.json.GsonParser;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 在原有CertificatesVerifier基础上，增加自动更新证书功能
 *
 * @author doger.wang
 * @author Long Yu
 */
@Slf4j
public class AutoUpdateCertificatesVerifier implements Verifier {
  /**
   * 证书下载地址
   */
  private static final String CERT_DOWNLOAD_PATH = "/v3/certificates";

  /**
   * 上次更新时间
   */
  private volatile Instant instant;

  /**
   * 证书更新间隔时间，单位为分钟
   */
  private final int minutesInterval;

  private CertificatesVerifier verifier;

  private final Credentials credentials;

  private final byte[] apiV3Key;

  private String payBaseUrl ;

  private final ReentrantLock lock = new ReentrantLock();

  /**
   * 微信支付代理对象
   */
  private WxPayHttpProxy wxPayHttpProxy;

  /**
   * 时间间隔枚举，支持一小时、六小时以及十二小时
   */
  @Getter
  @RequiredArgsConstructor
  public enum TimeInterval {
    /**
     * 一小时
     */
    OneHour(60),
    /**
     * 六小时
     */
    SixHours(60 * 6),
    /**
     * 十二小时
     */
    TwelveHours(60 * 12);

    private final int minutes;
  }

  public AutoUpdateCertificatesVerifier(Credentials credentials, byte[] apiV3Key, String payBaseUrl) {
    this(credentials, apiV3Key, TimeInterval.OneHour.getMinutes(), payBaseUrl);
  }

  public AutoUpdateCertificatesVerifier(Credentials credentials, byte[] apiV3Key, int minutesInterval, String payBaseUrl) {
    this(credentials, apiV3Key, minutesInterval, payBaseUrl, null);
  }

  public AutoUpdateCertificatesVerifier(Credentials credentials, byte[] apiV3Key, int minutesInterval, String payBaseUrl, WxPayHttpProxy wxPayHttpProxy) {
    this.credentials = credentials;
    this.apiV3Key = apiV3Key;
    this.minutesInterval = minutesInterval;
    this.payBaseUrl = payBaseUrl;
    this.wxPayHttpProxy = wxPayHttpProxy;
    //构造时更新证书
    try {
      autoUpdateCert();
      instant = Instant.now();
    } catch (IOException | GeneralSecurityException e) {
      throw new WxRuntimeException(e);
    }
  }

  @Override
  public boolean verify(String serialNumber, byte[] message, String signature) {
    checkAndAutoUpdateCert();
    return verifier.verify(serialNumber, message, signature);
  }

  /**
   * 检查证书是否在有效期内，如果不在有效期内则进行更新
   */
  private void checkAndAutoUpdateCert() {
    if (instant == null || instant.plus(minutesInterval, ChronoUnit.MINUTES).compareTo(Instant.now()) <= 0) {
      if (lock.tryLock()) {
        try {
          autoUpdateCert();
          //更新时间
          instant = Instant.now();
        } catch (GeneralSecurityException | IOException e) {
          log.warn("Auto update cert failed, exception = {}", e);
        } finally {
          lock.unlock();
        }
      }
    }
  }

  private void autoUpdateCert() throws IOException, GeneralSecurityException {
    WxPayV3HttpClientBuilder wxPayV3HttpClientBuilder = WxPayV3HttpClientBuilder.create()
      .withCredentials(credentials)
      .withValidator(verifier == null ? response -> true : new WxPayValidator(verifier));

    //调用自定义扩展设置设置HTTP PROXY对象
    HttpProxyUtils.initHttpProxy(wxPayV3HttpClientBuilder,this.wxPayHttpProxy);

    //增加自定义扩展点,子类可以设置其他构造参数
    this.customHttpClientBuilder(wxPayV3HttpClientBuilder);

    CloseableHttpClient httpClient = wxPayV3HttpClientBuilder.build();

    HttpGet httpGet = new HttpGet(this.payBaseUrl + CERT_DOWNLOAD_PATH);
    httpGet.addHeader("Accept", "application/json");

    CloseableHttpResponse response = httpClient.execute(httpGet);
    int statusCode = response.getStatusLine().getStatusCode();
    String body = EntityUtils.toString(response.getEntity());
    if (statusCode == HttpStatus.SC_OK) {
      List<X509Certificate> newCertList = deserializeToCerts(apiV3Key, body);
      if (newCertList.isEmpty()) {
        throw new WxRuntimeException("Cert list is empty");
      }
      this.verifier = new CertificatesVerifier(newCertList);
    } else {
      log.warn("Auto update cert failed, statusCode = {},body = {}", statusCode, body);
      throw new WxRuntimeException(this.getErrorMsg(body));
    }
  }


  /**
   * 子类可以自定义添加Http client builder的信息
   * @param builder httpclient构造器
   */
  public void customHttpClientBuilder(WxPayV3HttpClientBuilder builder) {
  }

  /**
   * 反序列化证书并解密
   */
  private List<X509Certificate> deserializeToCerts(byte[] apiV3Key, String body) throws GeneralSecurityException, IOException {
    AesUtils aesUtils = new AesUtils(apiV3Key);
    final JsonObject json = GsonParser.parse(body);
    final JsonArray dataNode = json.getAsJsonArray("data");
    if (dataNode == null) {
      return Collections.emptyList();
    }

    List<X509Certificate> newCertList = new ArrayList<>();
    for (int i = 0, count = dataNode.size(); i < count; i++) {
      final JsonObject encryptCertificateNode = ((JsonObject) dataNode.get(i)).getAsJsonObject("encrypt_certificate");
      //解密
      String cert = aesUtils.decryptToString(
        encryptCertificateNode.get("associated_data").toString().replaceAll("\"", "")
          .getBytes(StandardCharsets.UTF_8),
        encryptCertificateNode.get("nonce").toString().replaceAll("\"", "")
          .getBytes(StandardCharsets.UTF_8),
        encryptCertificateNode.get("ciphertext").toString().replaceAll("\"", ""));

      X509Certificate x509Cert = PemUtils
        .loadCertificate(new ByteArrayInputStream(cert.getBytes(StandardCharsets.UTF_8)));
      try {
        x509Cert.checkValidity();
      } catch (CertificateExpiredException | CertificateNotYetValidException e) {
        continue;
      }
      newCertList.add(x509Cert);
    }

    return newCertList;
  }

  @Override
  public X509Certificate getValidCertificate() {
    checkAndAutoUpdateCert();
    return verifier.getValidCertificate();
  }

  private String getErrorMsg(String body) {
    return Optional
      .ofNullable(GsonParser.parse(body).getAsJsonObject())
      .map(resp -> resp.get("message"))
      .map(JsonElement::getAsString)
      .orElse("update cert failed");
  }
}
