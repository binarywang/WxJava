package com.github.binarywang.wxpay.v3;


import java.security.PrivateKey;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.github.binarywang.wxpay.v3.auth.PrivateKeySigner;
import com.github.binarywang.wxpay.v3.auth.WxPayCredentials;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.execchain.ClientExecChain;

public class WxPayV3HttpClientBuilder extends HttpClientBuilder {
  private Credentials credentials;
  private Validator validator;
  /**
   * 额外受信任的主机列表，用于代理转发场景：对这些主机的请求也会携带微信支付 Authorization 头
   */
  private final Set<String> trustedHosts = new HashSet<>();

  static final String OS = System.getProperty("os.name") + "/" + System.getProperty("os.version");
  static final String VERSION = System.getProperty("java.version");

  private WxPayV3HttpClientBuilder() {
    super();

    String userAgent = String.format(
        "WechatPay-Apache-HttpClient/%s (%s) Java/%s",
        getClass().getPackage().getImplementationVersion(),
      OS,
        VERSION == null ? "Unknown" : VERSION);
    setUserAgent(userAgent);
  }

  public static WxPayV3HttpClientBuilder create() {
    return new WxPayV3HttpClientBuilder();
  }

  public WxPayV3HttpClientBuilder withMerchant(String merchantId, String serialNo, PrivateKey privateKey) {
    this.credentials =
        new WxPayCredentials(merchantId, new PrivateKeySigner(serialNo, privateKey));
    return this;
  }

  public WxPayV3HttpClientBuilder withCredentials(Credentials credentials) {
    this.credentials = credentials;
    return this;
  }

  public WxPayV3HttpClientBuilder withValidator(Validator validator) {
    this.validator = validator;
    return this;
  }

  /**
   * 添加受信任的主机，对该主机的请求也会携带微信支付 Authorization 头.
   * 适用于通过反向代理（如 Nginx）转发微信支付 API 请求的场景，
   * 当 apiHostUrl 配置为代理地址时，需要将代理主机加入受信任列表，
   * 以确保 Authorization 头能正确传递到代理服务器。
   * 若传入值包含端口（如 "proxy.company.com:8080"），会自动提取主机名部分。
   *
   * @param host 受信任的主机（可含端口），例如 "proxy.company.com" 或 "proxy.company.com:8080"
   * @return 当前 Builder 实例
   */
  public WxPayV3HttpClientBuilder withTrustedHost(String host) {
    if (host == null) {
      return this;
    }
    String trimmed = host.trim();
    if (trimmed.isEmpty()) {
      return this;
    }
    // 若包含端口号（如 "host:8080"），只取主机名部分
    int colonIdx = trimmed.lastIndexOf(':');
    if (colonIdx > 0) {
      String portPart = trimmed.substring(colonIdx + 1);
      boolean isPort = !portPart.isEmpty() && portPart.chars().allMatch(Character::isDigit);
      if (isPort) {
        trimmed = trimmed.substring(0, colonIdx);
      }
    }
    if (!trimmed.isEmpty()) {
      this.trustedHosts.add(trimmed);
    }
    return this;
  }

  @Override
  public CloseableHttpClient build() {
    if (credentials == null) {
      throw new IllegalArgumentException("缺少身份认证信息");
    }
    if (validator == null) {
      throw new IllegalArgumentException("缺少签名验证信息");
    }

    return super.build();
  }

  @Override
  protected ClientExecChain decorateProtocolExec(final ClientExecChain requestExecutor) {
    return new SignatureExec(this.credentials, this.validator, requestExecutor,
      Collections.unmodifiableSet(new HashSet<>(this.trustedHosts)));
  }
}
