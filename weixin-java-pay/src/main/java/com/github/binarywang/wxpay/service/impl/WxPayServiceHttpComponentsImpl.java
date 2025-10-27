package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.WxPayApiData;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.v3.WxPayV3DownloadHttpGet;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.util.http.apache.ByteArrayResponseHandler;
import me.chanjar.weixin.common.util.json.GsonParser;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.*;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;
import java.util.Optional;

/**
 * 微信支付请求实现类，apache httpconponents 实现.
 *
 * @author altusea
 */
@Slf4j
public class WxPayServiceHttpComponentsImpl extends BaseWxPayServiceImpl {

  private static final String ACCEPT = "Accept";
  private static final String CONTENT_TYPE = "Content-Type";
  private static final String APPLICATION_JSON = "application/json";
  private static final String WECHAT_PAY_SERIAL = "Wechatpay-Serial";

  @Override
  public byte[] postForBytes(String url, String requestStr, boolean useKey) throws WxPayException {
    try {
      HttpClientBuilder httpClientBuilder = createHttpClientBuilder(useKey);
      HttpPost httpPost = this.createHttpPost(url, requestStr);
      try (CloseableHttpClient httpClient = httpClientBuilder.build()) {
        final byte[] bytes = httpClient.execute(httpPost, ByteArrayResponseHandler.INSTANCE);
        final String responseData = Base64.getEncoder().encodeToString(bytes);
        this.logRequestAndResponse(url, requestStr, responseData);
        wxApiData.set(new WxPayApiData(url, requestStr, responseData, null));
        return bytes;
      }
    } catch (Exception e) {
      this.logError(url, requestStr, e);
      wxApiData.set(new WxPayApiData(url, requestStr, null, e.getMessage()));
      throw new WxPayException(e.getMessage(), e);
    }
  }

  @Override
  public String post(String url, String requestStr, boolean useKey) throws WxPayException {
    try {
      HttpClientBuilder httpClientBuilder = this.createHttpClientBuilder(useKey);
      HttpPost httpPost = this.createHttpPost(url, requestStr);
      try (CloseableHttpClient httpClient = httpClientBuilder.build()) {
        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
          String responseString = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
          this.logRequestAndResponse(url, requestStr, responseString);
          if (this.getConfig().isIfSaveApiData()) {
            wxApiData.set(new WxPayApiData(url, requestStr, responseString, null));
          }
          return responseString;
        }
      } finally {
        httpPost.releaseConnection();
      }
    } catch (Exception e) {
      this.logError(url, requestStr, e);
      if (this.getConfig().isIfSaveApiData()) {
        wxApiData.set(new WxPayApiData(url, requestStr, null, e.getMessage()));
      }
      throw new WxPayException(e.getMessage(), e);
    }
  }

  @Override
  public String post(String url, String requestStr, boolean useKey, String mimeType) throws WxPayException {
    try {
      HttpClientBuilder httpClientBuilder = this.createHttpClientBuilder(useKey);
      HttpPost httpPost = this.createHttpPost(url, requestStr, mimeType);
      try (CloseableHttpClient httpClient = httpClientBuilder.build()) {
        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
          String responseString = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
          this.logRequestAndResponse(url, requestStr, responseString);
          if (this.getConfig().isIfSaveApiData()) {
            wxApiData.set(new WxPayApiData(url, requestStr, responseString, null));
          }
          return responseString;
        }
      } finally {
        httpPost.releaseConnection();
      }
    } catch (Exception e) {
      this.logError(url, requestStr, e);
      if (this.getConfig().isIfSaveApiData()) {
        wxApiData.set(new WxPayApiData(url, requestStr, null, e.getMessage()));
      }
      throw new WxPayException(e.getMessage(), e);
    }
  }

  @Override
  public String postV3(String url, String requestStr) throws WxPayException {
    HttpPost httpPost = this.createHttpPost(url, requestStr);
    this.configureRequest(httpPost);
    return this.requestV3(url, requestStr, httpPost);
  }

  private String requestV3(String url, String requestStr, HttpRequestBase httpRequestBase) throws WxPayException {
    CloseableHttpClient httpClient = this.createApiV3HttpClient();
    try (CloseableHttpResponse response = httpClient.execute(httpRequestBase)) {
      //v3已经改为通过状态码判断200 204 成功
      int statusCode = response.getStatusLine().getStatusCode();
      //post方法有可能会没有返回值的情况
      String responseString = null;
      if (response.getEntity() != null) {
        responseString = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
      }

      if (HttpStatus.SC_OK == statusCode || HttpStatus.SC_NO_CONTENT == statusCode) {
        this.logRequestAndResponse(url, requestStr, responseString);
        return responseString;
      }

      //有错误提示信息返回
      JsonObject jsonObject = GsonParser.parse(responseString);
      throw convertException(jsonObject);
    } catch (Exception e) {
      this.logError(url, requestStr, e);
      throw (e instanceof WxPayException) ? (WxPayException) e : new WxPayException(e.getMessage(), e);
    } finally {
      httpRequestBase.releaseConnection();
    }
  }

  @Override
  public String patchV3(String url, String requestStr) throws WxPayException {
    HttpPatch httpPatch = new HttpPatch(url);
    httpPatch.setEntity(createEntry(requestStr));
    return this.requestV3(url, requestStr, httpPatch);
  }

  @Override
  public String postV3WithWechatpaySerial(String url, String requestStr) throws WxPayException {
    HttpPost httpPost = this.createHttpPost(url, requestStr);
    this.configureRequest(httpPost);
    CloseableHttpClient httpClient = this.createApiV3HttpClient();
    try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
      //v3已经改为通过状态码判断200 204 成功
      int statusCode = response.getStatusLine().getStatusCode();
      String responseString = "{}";
      HttpEntity entity = response.getEntity();
      if (entity != null) {
        responseString = EntityUtils.toString(entity, StandardCharsets.UTF_8);
      }

      if (HttpStatus.SC_OK == statusCode || HttpStatus.SC_NO_CONTENT == statusCode) {
        this.logRequestAndResponse(url, requestStr, responseString);
        return responseString;
      }

      //有错误提示信息返回
      JsonObject jsonObject = GsonParser.parse(responseString);
      throw convertException(jsonObject);
    } catch (Exception e) {
      this.logError(url, requestStr, e);
      throw (e instanceof WxPayException) ? (WxPayException) e : new WxPayException(e.getMessage(), e);
    } finally {
      httpPost.releaseConnection();
    }
  }

  @Override
  public String postV3(String url, HttpPost httpPost) throws WxPayException {
    return this.requestV3(url, httpPost);
  }

  @Override
  public String requestV3(String url, HttpRequestBase httpRequest) throws WxPayException {
    this.configureRequest(httpRequest);
    CloseableHttpClient httpClient = this.createApiV3HttpClient();
    try (CloseableHttpResponse response = httpClient.execute(httpRequest)) {
      //v3已经改为通过状态码判断200 204 成功
      int statusCode = response.getStatusLine().getStatusCode();
      //post方法有可能会没有返回值的情况
      String responseString = null;
      if (response.getEntity() != null) {
        responseString = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
      }

      if (HttpStatus.SC_OK == statusCode || HttpStatus.SC_NO_CONTENT == statusCode) {
        log.info("\n【请求地址】：{}\n【响应数据】：{}", url, responseString);
        return responseString;
      }

      //有错误提示信息返回
      JsonObject jsonObject = GsonParser.parse(responseString);
      throw convertException(jsonObject);
    } catch (Exception e) {
      log.error("\n【请求地址】：{}\n【异常信息】：{}", url, e.getMessage());
      throw (e instanceof WxPayException) ? (WxPayException) e : new WxPayException(e.getMessage(), e);
    } finally {
      httpRequest.releaseConnection();
    }
  }

  @Override
  public String getV3(String url) throws WxPayException {
    if (this.getConfig().isStrictlyNeedWechatPaySerial()) {
      return getV3WithWechatPaySerial(url);
    }
    HttpGet httpGet = new HttpGet(url);
    return this.requestV3(url, httpGet);
  }

  @Override
  public String getV3WithWechatPaySerial(String url) throws WxPayException {
    HttpGet httpGet = new HttpGet(url);
    return this.requestV3(url, httpGet);
  }

  @Override
  public InputStream downloadV3(String url) throws WxPayException {
    HttpGet httpGet = new WxPayV3DownloadHttpGet(url);
    this.configureRequest(httpGet);
    CloseableHttpClient httpClient = this.createApiV3HttpClient();
    try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
      //v3已经改为通过状态码判断200 204 成功
      int statusCode = response.getStatusLine().getStatusCode();
      Header contentType = response.getFirstHeader(HttpHeaders.CONTENT_TYPE);
      boolean isJsonContentType = Objects.nonNull(contentType) && ContentType.APPLICATION_JSON.getMimeType()
        .equals(ContentType.parse(String.valueOf(contentType.getValue())).getMimeType());
      if ((HttpStatus.SC_OK == statusCode || HttpStatus.SC_NO_CONTENT == statusCode) && !isJsonContentType) {
        log.info("\n【请求地址】：{}\n", url);
        return response.getEntity().getContent();
      }

      //response里的header有content-type=json说明返回了错误信息
      //有错误提示信息返回
      String responseString = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
      JsonObject jsonObject = GsonParser.parse(responseString);
      throw convertException(jsonObject);
    } catch (Exception e) {
      log.error("\n【请求地址】：{}\n【异常信息】：{}", url, e.getMessage());
      throw (e instanceof WxPayException) ? (WxPayException) e : new WxPayException(e.getMessage(), e);
    } finally {
      httpGet.releaseConnection();
    }
  }

  @Override
  public String putV3(String url, String requestStr) throws WxPayException {
    HttpPut httpPut = new HttpPut(url);
    StringEntity entity = createEntry(requestStr);
    httpPut.setEntity(entity);
    return requestV3(url, httpPut);
  }

  @Override
  public String deleteV3(String url) throws WxPayException {
    HttpDelete httpDelete = new HttpDelete(url);
    return requestV3(url, httpDelete);
  }

  private void configureRequest(HttpRequestBase request) {
    String serialNumber = getWechatPaySerial(getConfig());
    String method = request.getMethod();
    request.addHeader(ACCEPT, APPLICATION_JSON);
    if (!method.equals("POST")) {
      request.addHeader(CONTENT_TYPE, APPLICATION_JSON);
    }
    request.addHeader(WECHAT_PAY_SERIAL, serialNumber);

    request.setConfig(RequestConfig.custom()
      .setConnectionRequestTimeout(this.getConfig().getHttpConnectionTimeout())
      .setConnectTimeout(this.getConfig().getHttpConnectionTimeout())
      .setSocketTimeout(this.getConfig().getHttpTimeout())
      .build());
  }

  private CloseableHttpClient createApiV3HttpClient() throws WxPayException {
    CloseableHttpClient apiV3HttpClient = this.getConfig().getApiV3HttpClient();
    if (null == apiV3HttpClient) {
      return this.getConfig().initApiV3HttpClient();
    }
    return apiV3HttpClient;
  }

  private static StringEntity createEntry(String requestStr) {
    return new StringEntity(requestStr, ContentType.create(APPLICATION_JSON, StandardCharsets.UTF_8));
    //return new StringEntity(new String(requestStr.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
  }

  private static StringEntity createEntry(String requestStr, String mimeType) {
    return new StringEntity(requestStr, ContentType.create(mimeType, StandardCharsets.UTF_8));
    //return new StringEntity(new String(requestStr.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
  }

  private HttpClientBuilder createHttpClientBuilder(boolean useKey) throws WxPayException {
    HttpClientBuilder httpClientBuilder = HttpClients.custom();
    if (useKey) {
      this.initSSLContext(httpClientBuilder);
    }

    if (StringUtils.isNotBlank(this.getConfig().getHttpProxyHost()) && this.getConfig().getHttpProxyPort() > 0) {
      if (StringUtils.isEmpty(this.getConfig().getHttpProxyUsername())) {
        this.getConfig().setHttpProxyUsername("whatever");
      }

      // 使用代理服务器 需要用户认证的代理服务器
      CredentialsProvider provider = new BasicCredentialsProvider();
      provider.setCredentials(new AuthScope(this.getConfig().getHttpProxyHost(),
          this.getConfig().getHttpProxyPort()),
        new UsernamePasswordCredentials(this.getConfig().getHttpProxyUsername(),
          this.getConfig().getHttpProxyPassword()));
      httpClientBuilder.setDefaultCredentialsProvider(provider)
        .setProxy(new HttpHost(this.getConfig().getHttpProxyHost(), this.getConfig().getHttpProxyPort()));
    }

    // 提供自定义httpClientBuilder的能力
    Optional.ofNullable(getConfig().getHttpClientBuilderCustomizer()).ifPresent(e -> {
      e.customize(httpClientBuilder);
    });

    return httpClientBuilder;
  }

  private HttpPost createHttpPost(String url, String requestStr) {
    HttpPost httpPost = new HttpPost(url);
    httpPost.setEntity(createEntry(requestStr));

    httpPost.setConfig(RequestConfig.custom()
      .setConnectionRequestTimeout(this.getConfig().getHttpConnectionTimeout())
      .setConnectTimeout(this.getConfig().getHttpConnectionTimeout())
      .setSocketTimeout(this.getConfig().getHttpTimeout())
      .build());

    return httpPost;
  }

  private HttpPost createHttpPost(String url, String requestStr, String mimeType) throws WxPayException {
    HttpPost httpPost = new HttpPost(url);
    httpPost.setEntity(createEntry(requestStr, mimeType));

    httpPost.setConfig(RequestConfig.custom()
      .setConnectionRequestTimeout(this.getConfig().getHttpConnectionTimeout())
      .setConnectTimeout(this.getConfig().getHttpConnectionTimeout())
      .setSocketTimeout(this.getConfig().getHttpTimeout())
      .build());

    return httpPost;
  }

  private void initSSLContext(HttpClientBuilder httpClientBuilder) throws WxPayException {
    SSLContext sslContext = this.getConfig().getSslContext();
    if (null == sslContext) {
      sslContext = this.getConfig().initSSLContext();
    }

    httpClientBuilder.setSSLSocketFactory(new SSLConnectionSocketFactory(sslContext,
      new DefaultHostnameVerifier()));
  }

  private WxPayException convertException(JsonObject jsonObject) {
    //TODO 这里考虑使用新的适用于V3的异常
    JsonElement codeElement = jsonObject.get("code");
    String code = codeElement == null ? null : codeElement.getAsString();
    String message = jsonObject.get("message").getAsString();
    WxPayException wxPayException = new WxPayException(message);
    wxPayException.setErrCode(code);
    wxPayException.setErrCodeDes(message);
    return wxPayException;
  }

  /**
   * 兼容微信支付公钥模式
   */
  private String getWechatPaySerial(WxPayConfig wxPayConfig) {
    if (StringUtils.isNotBlank(wxPayConfig.getPublicKeyId())) {
      return wxPayConfig.getPublicKeyId();
    }

    return wxPayConfig.getVerifier().getValidCertificate().getSerialNumber().toString(16).toUpperCase();
  }

  private void logRequestAndResponse(String url, String requestStr, String responseStr) {
    log.info("\n【请求地址】：{}\n【请求数据】：{}\n【响应数据】：{}", url, requestStr, responseStr);
  }

  private void logError(String url, String requestStr, Exception e) {
    log.error("\n【请求地址】：{}\n【请求数据】：{}\n【异常信息】：{}", url, requestStr, e.getMessage());
  }
}
