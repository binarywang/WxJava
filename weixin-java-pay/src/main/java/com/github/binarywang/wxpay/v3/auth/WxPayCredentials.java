package com.github.binarywang.wxpay.v3.auth;


import com.github.binarywang.wxpay.v3.Credentials;
import com.github.binarywang.wxpay.v3.WechatPayUploadHttpPost;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.client.methods.HttpRequestWrapper;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

@Slf4j
public class WxPayCredentials implements Credentials {
  private static final String SYMBOLS =
      "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
  private static final SecureRandom RANDOM = new SecureRandom();
  protected String merchantId;
  protected Signer signer;
  /**
   * 签名前从 URI Path 中移除的前缀（用于带路径前缀的反向代理场景）
   * 例如配置为 "/api-weixin" 时，"/api-weixin/v3/pay/..." 将参与签名为 "/v3/pay/..."
   */
  protected String signUriStripPrefix;

  public WxPayCredentials(String merchantId, Signer signer) {
    this.merchantId = merchantId;
    this.signer = signer;
  }

  public WxPayCredentials(String merchantId, Signer signer, String signUriStripPrefix) {
    this.merchantId = merchantId;
    this.signer = signer;
    this.setSignUriStripPrefix(signUriStripPrefix);
  }

  public String getMerchantId() {
    return merchantId;
  }

  public void setSignUriStripPrefix(String signUriStripPrefix) {
    if (signUriStripPrefix == null || signUriStripPrefix.trim().isEmpty()) {
      this.signUriStripPrefix = null;
      return;
    }
    String normalized = signUriStripPrefix.trim();
    if (!normalized.startsWith("/")) {
      normalized = "/" + normalized;
    }
    if (normalized.length() > 1 && normalized.endsWith("/")) {
      normalized = normalized.substring(0, normalized.length() - 1);
    }
    this.signUriStripPrefix = normalized;
  }

  protected long generateTimestamp() {
    return System.currentTimeMillis() / 1000;
  }

  protected String generateNonceStr() {
    char[] nonceChars = new char[32];
    for (int index = 0; index < nonceChars.length; ++index) {
      nonceChars[index] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));
    }
    return new String(nonceChars);
  }

  @Override
  public final String getSchema() {
    return "WECHATPAY2-SHA256-RSA2048";
  }

  @Override
  public final String getToken(HttpRequestWrapper request) throws IOException {
    String nonceStr = generateNonceStr();
    long timestamp = generateTimestamp();

    String message = buildMessage(nonceStr, timestamp, request);
    log.debug("authorization message=[{}]", message);

    Signer.SignatureResult signature = signer.sign(message.getBytes(StandardCharsets.UTF_8));

    String token = "mchid=\"" + getMerchantId() + "\","
        + "nonce_str=\"" + nonceStr + "\","
        + "timestamp=\"" + timestamp + "\","
        + "serial_no=\"" + signature.certificateSerialNumber + "\","
        + "signature=\"" + signature.sign + "\"";
    log.debug("authorization token=[{}]", token);

    return token;
  }

  protected final String buildMessage(String nonce, long timestamp, HttpRequestWrapper request)
      throws IOException {
    URI uri = request.getURI();
    String canonicalUrl = stripPathPrefix(uri.getRawPath());
    if (uri.getQuery() != null) {
      canonicalUrl += "?" + uri.getRawQuery();
    }

    String body = "";
    // PATCH,POST,PUT
    if (request.getOriginal() instanceof WechatPayUploadHttpPost) {
      body = ((WechatPayUploadHttpPost) request.getOriginal()).getMeta();
    } else if (request instanceof HttpEntityEnclosingRequest) {
      body = EntityUtils.toString(((HttpEntityEnclosingRequest) request).getEntity());
    }

    return request.getRequestLine().getMethod() + "\n"
        + canonicalUrl + "\n"
        + timestamp + "\n"
        + nonce + "\n"
        + body + "\n";
  }

  private String stripPathPrefix(String rawPath) {
    if (rawPath == null || rawPath.isEmpty() || signUriStripPrefix == null) {
      return rawPath;
    }
    if (!rawPath.startsWith(signUriStripPrefix)) {
      return rawPath;
    }
    String stripped = rawPath.substring(signUriStripPrefix.length());
    if (stripped.isEmpty()) {
      return "/";
    }
    return stripped.startsWith("/") ? stripped : "/" + stripped;
  }

}
