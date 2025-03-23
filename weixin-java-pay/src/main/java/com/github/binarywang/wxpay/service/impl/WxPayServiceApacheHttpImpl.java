package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.WxPayApiData;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.v3.WxPayV3DownloadHttpGet;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
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
 * <pre>
 * 微信支付请求实现类，apache httpclient实现.
 * Created by Binary Wang on 2016/7/28.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class WxPayServiceApacheHttpImpl extends BaseWxPayServiceImpl {

  private static final String ACCEPT = "Accept";
  private static final String CONTENT_TYPE = "Content-Type";
  private static final String APPLICATION_JSON = "application/json";

  @Override
  public byte[] postForBytes(String url, String requestStr, boolean useKey) throws WxPayException {
    try {
      HttpClientBuilder httpClientBuilder = createHttpClientBuilder(useKey);
      HttpPost httpPost = this.createHttpPost(url, requestStr);
      try (CloseableHttpClient httpClient = httpClientBuilder.build()) {
        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
          final byte[] bytes = EntityUtils.toByteArray(response.getEntity());
          final String responseData = Base64.getEncoder().encodeToString(bytes);
          this.log.info("\n【请求地址】：{}\n【请求数据】：{}\n【响应数据(Base64编码后)】：{}", url, requestStr, responseData);
          wxApiData.set(new WxPayApiData(url, requestStr, responseData, null));
          return bytes;
        }
      } finally {
        httpPost.releaseConnection();
      }
    } catch (Exception e) {
      this.log.error("\n【请求地址】：{}\n【请求数据】：{}\n【异常信息】：{}", url, requestStr, e.getMessage());
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
          this.log.info("\n【请求地址】：{}\n【请求数据】：{}\n【响应数据】：{}", url, requestStr, responseString);
          if (this.getConfig().isIfSaveApiData()) {
            wxApiData.set(new WxPayApiData(url, requestStr, responseString, null));
          }
          return responseString;
        }
      } finally {
        httpPost.releaseConnection();
      }
    } catch (Exception e) {
      this.log.error("\n【请求地址】：{}\n【请求数据】：{}\n【异常信息】：{}", url, requestStr, e.getMessage());
      if (this.getConfig().isIfSaveApiData()) {
        wxApiData.set(new WxPayApiData(url, requestStr, null, e.getMessage()));
      }
      throw new WxPayException(e.getMessage(), e);
    }
  }

  @Override
  public String postV3(String url, String requestStr) throws WxPayException {
    CloseableHttpClient httpClient = this.createApiV3HttpClient();
    HttpPost httpPost = this.createHttpPost(url, requestStr);
    httpPost.addHeader(ACCEPT, APPLICATION_JSON);
    httpPost.addHeader(CONTENT_TYPE, APPLICATION_JSON);
    String serialNumber = getWechatpaySerial(getConfig());
    httpPost.addHeader("Wechatpay-Serial", serialNumber);
    try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
      //v3已经改为通过状态码判断200 204 成功
      int statusCode = response.getStatusLine().getStatusCode();
      //post方法有可能会没有返回值的情况
      String responseString = null;
      if (response.getEntity() != null) {
        responseString = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
      }

      if (HttpStatus.SC_OK == statusCode || HttpStatus.SC_NO_CONTENT == statusCode) {
        this.log.info("\n【请求地址】：{}\n【请求数据】：{}\n【响应数据】：{}", url, requestStr, responseString);
        return responseString;
      }

      //有错误提示信息返回
      JsonObject jsonObject = GsonParser.parse(responseString);
      throw convertException(jsonObject);
    } catch (Exception e) {
      this.log.error("\n【请求地址】：{}\n【请求数据】：{}\n【异常信息】：{}", url, requestStr, e.getMessage());
      throw (e instanceof WxPayException) ? (WxPayException) e : new WxPayException(e.getMessage(), e);
    } finally {
      httpPost.releaseConnection();
    }


  }

  @Override
  public String patchV3(String url, String requestStr) throws WxPayException {
    CloseableHttpClient httpClient = this.createApiV3HttpClient();
    HttpPatch httpPatch = new HttpPatch(url);
    httpPatch.setEntity(this.createEntry(requestStr));

    httpPatch.setConfig(RequestConfig.custom()
      .setConnectionRequestTimeout(this.getConfig().getHttpConnectionTimeout())
      .setConnectTimeout(this.getConfig().getHttpConnectionTimeout())
      .setSocketTimeout(this.getConfig().getHttpTimeout())
      .build());

    httpPatch.addHeader(ACCEPT, APPLICATION_JSON);
    httpPatch.addHeader(CONTENT_TYPE, APPLICATION_JSON);
    try (CloseableHttpResponse response = httpClient.execute(httpPatch)) {
      //v3已经改为通过状态码判断200 204 成功
      int statusCode = response.getStatusLine().getStatusCode();
      //post方法有可能会没有返回值的情况
      String responseString = null;
      if (response.getEntity() != null) {
        responseString = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
      }

      if (HttpStatus.SC_OK == statusCode || HttpStatus.SC_NO_CONTENT == statusCode) {
        this.log.info("\n【请求地址】：{}\n【请求数据】：{}\n【响应数据】：{}", url, requestStr, responseString);
        return responseString;
      }

      //有错误提示信息返回
      JsonObject jsonObject = GsonParser.parse(responseString);
      throw convertException(jsonObject);
    } catch (Exception e) {
      this.log.error("\n【请求地址】：{}\n【请求数据】：{}\n【异常信息】：{}", url, requestStr, e.getMessage());
      throw (e instanceof WxPayException) ? (WxPayException) e : new WxPayException(e.getMessage(), e);
    } finally {
      httpPatch.releaseConnection();
    }
  }

  @Override
  public String postV3WithWechatpaySerial(String url, String requestStr) throws WxPayException {
    CloseableHttpClient httpClient = this.createApiV3HttpClient();
    HttpPost httpPost = this.createHttpPost(url, requestStr);
    httpPost.addHeader(ACCEPT, APPLICATION_JSON);
    httpPost.addHeader(CONTENT_TYPE, APPLICATION_JSON);
    String serialNumber = getWechatpaySerial(getConfig());
    httpPost.addHeader("Wechatpay-Serial", serialNumber);
    try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
      //v3已经改为通过状态码判断200 204 成功
      int statusCode = response.getStatusLine().getStatusCode();
      String responseString = "{}";
      HttpEntity entity = response.getEntity();
      if (entity != null) {
        responseString = EntityUtils.toString(entity, StandardCharsets.UTF_8);
      }

      if (HttpStatus.SC_OK == statusCode || HttpStatus.SC_NO_CONTENT == statusCode) {
        this.log.info("\n【请求地址】：{}\n【请求数据】：{}\n【响应数据】：{}", url, requestStr, responseString);
        return responseString;
      }

      //有错误提示信息返回
      JsonObject jsonObject = GsonParser.parse(responseString);
      throw convertException(jsonObject);
    } catch (Exception e) {
      this.log.error("\n【请求地址】：{}\n【请求数据】：{}\n【异常信息】：{}", url, requestStr, e.getMessage());
      e.printStackTrace();
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
    httpRequest.setConfig(RequestConfig.custom()
      .setConnectionRequestTimeout(this.getConfig().getHttpConnectionTimeout())
      .setConnectTimeout(this.getConfig().getHttpConnectionTimeout())
      .setSocketTimeout(this.getConfig().getHttpTimeout())
      .build());

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
        this.log.info("\n【请求地址】：{}\n【响应数据】：{}", url, responseString);
        return responseString;
      }

      //有错误提示信息返回
      JsonObject jsonObject = GsonParser.parse(responseString);
      throw convertException(jsonObject);
    } catch (Exception e) {
      this.log.error("\n【请求地址】：{}\n【异常信息】：{}", url, e.getMessage());
      throw (e instanceof WxPayException) ? (WxPayException) e : new WxPayException(e.getMessage(), e);
    } finally {
      httpRequest.releaseConnection();
    }
  }

  @Override
  public String getV3(String url) throws WxPayException {
    HttpGet httpGet = new HttpGet(url);
    httpGet.addHeader(ACCEPT, APPLICATION_JSON);
    httpGet.addHeader(CONTENT_TYPE, APPLICATION_JSON);
    return this.requestV3(url, httpGet);
  }

  @Override
  public String getV3WithWechatPaySerial(String url) throws WxPayException {
    HttpGet httpGet = new HttpGet(url);
    httpGet.addHeader(ACCEPT, APPLICATION_JSON);
    httpGet.addHeader(CONTENT_TYPE, APPLICATION_JSON);
    String serialNumber = getWechatpaySerial(getConfig());
    httpGet.addHeader("Wechatpay-Serial", serialNumber);
    return this.requestV3(url, httpGet);
  }

  @Override
  public InputStream downloadV3(String url) throws WxPayException {
    CloseableHttpClient httpClient = this.createApiV3HttpClient();
    HttpGet httpGet = new WxPayV3DownloadHttpGet(url);
    httpGet.addHeader(ACCEPT, ContentType.WILDCARD.getMimeType());
    try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
      //v3已经改为通过状态码判断200 204 成功
      int statusCode = response.getStatusLine().getStatusCode();
      Header contentType = response.getFirstHeader(HttpHeaders.CONTENT_TYPE);
      boolean isJsonContentType = Objects.nonNull(contentType) && ContentType.APPLICATION_JSON.getMimeType()
        .equals(ContentType.parse(String.valueOf(contentType.getValue())).getMimeType());
      if ((HttpStatus.SC_OK == statusCode || HttpStatus.SC_NO_CONTENT == statusCode) && !isJsonContentType) {
        this.log.info("\n【请求地址】：{}\n", url);
        return response.getEntity().getContent();
      }

      //response里的header有content-type=json说明返回了错误信息
      //有错误提示信息返回
      String responseString = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
      JsonObject jsonObject = GsonParser.parse(responseString);
      throw convertException(jsonObject);
    } catch (Exception e) {
      this.log.error("\n【请求地址】：{}\n【异常信息】：{}", url, e.getMessage());
      throw (e instanceof WxPayException) ? (WxPayException) e : new WxPayException(e.getMessage(), e);
    } finally {
      httpGet.releaseConnection();
    }
  }

  @Override
  public String putV3(String url, String requestStr) throws WxPayException {
    HttpPut httpPut = new HttpPut(url);
    StringEntity entity = this.createEntry(requestStr);
    httpPut.setEntity(entity);
    httpPut.addHeader(ACCEPT, APPLICATION_JSON);
    httpPut.addHeader(CONTENT_TYPE, APPLICATION_JSON);
    return requestV3(url, httpPut);
  }

  @Override
  public String deleteV3(String url) throws WxPayException {
    HttpDelete httpDelete = new HttpDelete(url);
    httpDelete.addHeader(ACCEPT, APPLICATION_JSON);
    httpDelete.addHeader(CONTENT_TYPE, APPLICATION_JSON);
    return requestV3(url, httpDelete);
  }

  private CloseableHttpClient createApiV3HttpClient() throws WxPayException {
    CloseableHttpClient apiV3HttpClient = this.getConfig().getApiV3HttpClient();
    if (null == apiV3HttpClient) {
      return this.getConfig().initApiV3HttpClient();
    }
    return apiV3HttpClient;
  }

  private StringEntity createEntry(String requestStr) {
    return new StringEntity(requestStr, ContentType.create(APPLICATION_JSON, "utf-8"));
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
    httpPost.setEntity(this.createEntry(requestStr));

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
    //todo 这里考虑使用新的适用于V3的异常
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
   * @param wxPayConfig
   * @return
   */
  private String getWechatpaySerial(WxPayConfig wxPayConfig) {
    if (StringUtils.isNotBlank(wxPayConfig.getPublicKeyId())) {
      return wxPayConfig.getPublicKeyId();
    }
    return wxPayConfig.getVerifier().getValidCertificate().getSerialNumber().toString(16).toUpperCase();
  }
}
