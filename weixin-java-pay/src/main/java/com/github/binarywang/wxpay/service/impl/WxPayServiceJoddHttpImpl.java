package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.WxPayApiData;
import com.github.binarywang.wxpay.exception.WxPayException;
import jodd.http.HttpConnectionProvider;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.http.ProxyInfo;
import jodd.http.ProxyInfo.ProxyType;
import jodd.http.net.SSLSocketHttpConnectionProvider;
import jodd.http.net.SocketHttpConnectionProvider;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;

import javax.net.ssl.SSLContext;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * 微信支付请求实现类，jodd-http实现.
 * Created by Binary Wang on 2016/7/28.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Slf4j
public class WxPayServiceJoddHttpImpl extends BaseWxPayServiceImpl {
  @Override
  public byte[] postForBytes(String url, String requestStr, boolean useKey) throws WxPayException {
    try {
      HttpRequest request = this.buildHttpRequest(url, requestStr, useKey);
      byte[] responseBytes = request.send().bodyBytes();
      final String responseString = Base64.getEncoder().encodeToString(responseBytes);
      log.info("\n【请求地址】：{}\n【请求数据】：{}\n【响应数据(Base64编码后)】：{}", url, requestStr, responseString);
      if (this.getConfig().isIfSaveApiData()) {
        wxApiData.set(new WxPayApiData(url, requestStr, responseString, null));
      }
      return responseBytes;
    } catch (Exception e) {
      log.error("\n【请求地址】：{}\n【请求数据】：{}\n【异常信息】：{}", url, requestStr, e.getMessage());
      wxApiData.set(new WxPayApiData(url, requestStr, null, e.getMessage()));
      throw new WxPayException(e.getMessage(), e);
    }
  }

  @Override
  public String post(String url, String requestStr, boolean useKey) throws WxPayException {
    try {
      HttpRequest request = this.buildHttpRequest(url, requestStr, useKey);
      String responseString = this.getResponseString(request.send());

      log.info("\n【请求地址】：{}\n【请求数据】：{}\n【响应数据】：{}", url, requestStr, responseString);
      if (this.getConfig().isIfSaveApiData()) {
        wxApiData.set(new WxPayApiData(url, requestStr, responseString, null));
      }
      return responseString;
    } catch (Exception e) {
      log.error("\n【请求地址】：{}\n【请求数据】：{}\n【异常信息】：{}", url, requestStr, e.getMessage());
      wxApiData.set(new WxPayApiData(url, requestStr, null, e.getMessage()));
      throw new WxPayException(e.getMessage(), e);
    }
  }

  @Override
  public String post(String url, String requestStr, boolean useKey, String mimeType) throws WxPayException {
    try {
      HttpRequest request = this.buildHttpRequest(url, requestStr, useKey, mimeType);
      String responseString = this.getResponseString(request.send());

      log.info("\n【请求地址】：{}\n【请求数据】：{}\n【响应数据】：{}", url, requestStr, responseString);
      if (this.getConfig().isIfSaveApiData()) {
        wxApiData.set(new WxPayApiData(url, requestStr, responseString, null));
      }
      return responseString;
    } catch (Exception e) {
      log.error("\n【请求地址】：{}\n【请求数据】：{}\n【异常信息】：{}", url, requestStr, e.getMessage());
      wxApiData.set(new WxPayApiData(url, requestStr, null, e.getMessage()));
      throw new WxPayException(e.getMessage(), e);
    }
  }

  @Override
  public String postV3(String url, String requestStr) throws WxPayException {
    return null;
  }

  @Override
  public String patchV3(String url, String requestStr) throws WxPayException {
    return null;
  }

  @Override
  public String postV3WithWechatpaySerial(String url, String requestStr) throws WxPayException {
    return null;
  }

  @Override
  public String postV3(String url, HttpPost httpPost) throws WxPayException {
    return null;
  }

  @Override
  public String requestV3(String url, HttpRequestBase httpRequest) throws WxPayException {
    return null;
  }

  @Override
  public String getV3(String url) throws WxPayException {
    return null;
  }

  @Override
  public String getV3WithWechatPaySerial(String url) throws WxPayException {
    return null;
  }

  @Override
  public InputStream downloadV3(String url) throws WxPayException {
    return null;
  }

  @Override
  public String putV3(String url, String requestStr) throws WxPayException {
    return null;
  }

  @Override
  public String deleteV3(String url) throws WxPayException {
    return null;
  }

  private HttpRequest buildHttpRequest(String url, String requestStr, boolean useKey) throws WxPayException {
    HttpRequest request = HttpRequest
      .post(url)
      .timeout(this.getConfig().getHttpTimeout())
      .connectionTimeout(this.getConfig().getHttpConnectionTimeout())
      .bodyText(requestStr);

    if (useKey) {
      SSLContext sslContext = this.getConfig().getSslContext();
      if (null == sslContext) {
        sslContext = this.getConfig().initSSLContext();
      }
      final SSLSocketHttpConnectionProvider provider = new SSLSocketHttpConnectionProvider(sslContext);
      request.withConnectionProvider(provider);
    }

    if (StringUtils.isNotBlank(this.getConfig().getHttpProxyHost()) && this.getConfig().getHttpProxyPort() > 0) {
      if (StringUtils.isEmpty(this.getConfig().getHttpProxyUsername())) {
        this.getConfig().setHttpProxyUsername("whatever");
      }

      ProxyInfo httpProxy = new ProxyInfo(ProxyType.HTTP, this.getConfig().getHttpProxyHost(), this.getConfig().getHttpProxyPort(),
        this.getConfig().getHttpProxyUsername(), this.getConfig().getHttpProxyPassword());
      HttpConnectionProvider provider = request.connectionProvider();
      if (null == provider) {
        provider = new SocketHttpConnectionProvider();
      }
      provider.useProxy(httpProxy);
      request.withConnectionProvider(provider);
    }
    return request;
  }

  private HttpRequest buildHttpRequest(String url, String requestStr, boolean useKey, String mimeType) throws WxPayException {
    HttpRequest request = HttpRequest
      .post(url)
      .contentType(mimeType)
      .timeout(this.getConfig().getHttpTimeout())
      .connectionTimeout(this.getConfig().getHttpConnectionTimeout())
      .bodyText(requestStr);

    if (useKey) {
      SSLContext sslContext = this.getConfig().getSslContext();
      if (null == sslContext) {
        sslContext = this.getConfig().initSSLContext();
      }
      final SSLSocketHttpConnectionProvider provider = new SSLSocketHttpConnectionProvider(sslContext);
      request.withConnectionProvider(provider);
    }

    if (StringUtils.isNotBlank(this.getConfig().getHttpProxyHost()) && this.getConfig().getHttpProxyPort() > 0) {
      if (StringUtils.isEmpty(this.getConfig().getHttpProxyUsername())) {
        this.getConfig().setHttpProxyUsername("whatever");
      }

      ProxyInfo httpProxy = new ProxyInfo(ProxyType.HTTP, this.getConfig().getHttpProxyHost(), this.getConfig().getHttpProxyPort(),
        this.getConfig().getHttpProxyUsername(), this.getConfig().getHttpProxyPassword());
      HttpConnectionProvider provider = request.connectionProvider();
      if (null == provider) {
        provider = new SocketHttpConnectionProvider();
      }
      provider.useProxy(httpProxy);
      request.withConnectionProvider(provider);
    }
    return request;
  }

  private String getResponseString(HttpResponse response) throws WxPayException {
    try {
      log.debug("【微信服务器响应头信息】：\n{}", response.toString(false));
    } catch (NullPointerException e) {
      log.warn("HttpResponse.toString() 居然抛出空指针异常了", e);
    }

    String responseString = response.bodyText();

    if (StringUtils.isBlank(responseString)) {
      throw new WxPayException("响应信息为空");
    }

    if (StringUtils.isBlank(response.charset())) {
      responseString = new String(responseString.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
    }

    return responseString;
  }


}
