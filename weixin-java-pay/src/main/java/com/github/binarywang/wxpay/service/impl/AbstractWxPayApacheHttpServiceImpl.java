package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.v3.WxPayV3DownloadHttpGet;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import me.chanjar.weixin.common.util.json.GsonParser;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Optional;

/**
 * 基于 apache httpclient 的微信支付请求实现的公共父类，
 * 收敛 v3 接口请求、请求构造、SSL 与代理配置等各实现类共用的逻辑。
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
abstract class AbstractWxPayApacheHttpServiceImpl extends BaseWxPayServiceImpl {

  protected static final String ACCEPT = "Accept";
  protected static final String CONTENT_TYPE = "Content-Type";
  protected static final String APPLICATION_JSON = "application/json";
  protected static final String WECHAT_PAY_SERIAL = "Wechatpay-Serial";

  /**
   * 使用实际实现类作为日志名称，保证各实现类的日志分类不变
   */
  protected final Logger log = LoggerFactory.getLogger(this.getClass());

  @Override
  public String postV3(String url, String requestStr) throws WxPayException {
    HttpPost httpPost = this.createHttpPost(url, requestStr);
    this.configureRequest(httpPost);
    return this.requestV3(url, requestStr, httpPost);
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

      if (HttpStatus.SC_OK == statusCode || HttpStatus.SC_NO_CONTENT == statusCode
        || (HttpStatus.SC_ACCEPTED == statusCode && url.endsWith("/codepay"))) {
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

      if (HttpStatus.SC_OK == statusCode || HttpStatus.SC_NO_CONTENT == statusCode || HttpStatus.SC_ACCEPTED == statusCode) {
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

  /**
   * 执行 v3 请求并按照状态码解析响应，失败时抛出带错误码的异常
   *
   * @param url             请求地址
   * @param requestStr      请求数据，仅用于日志
   * @param httpRequestBase 已构造好的请求
   * @return 响应内容，可能为 null
   * @throws WxPayException 请求失败或微信返回错误信息
   */
  protected String requestV3(String url, String requestStr, HttpRequestBase httpRequestBase) throws WxPayException {
    CloseableHttpClient httpClient = this.createApiV3HttpClient();
    try (CloseableHttpResponse response = httpClient.execute(httpRequestBase)) {
      //v3已经改为通过状态码判断200 204 成功
      int statusCode = response.getStatusLine().getStatusCode();
      //post方法有可能会没有返回值的情况
      String responseString = null;
      if (response.getEntity() != null) {
        responseString = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
      }

      if (HttpStatus.SC_OK == statusCode || HttpStatus.SC_NO_CONTENT == statusCode || HttpStatus.SC_ACCEPTED == statusCode) {
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

  /**
   * 为 v3 请求设置公共请求头和超时配置
   *
   * @param request 待配置的请求
   */
  protected void configureRequest(HttpRequestBase request) {
    String serialNumber = getWechatPaySerial(getConfig());
    String method = request.getMethod();
    request.addHeader(ACCEPT, APPLICATION_JSON);
    if (!method.equals("POST")) {
      request.addHeader(CONTENT_TYPE, APPLICATION_JSON);
    }
    request.addHeader(WECHAT_PAY_SERIAL, serialNumber);

    request.setConfig(this.createRequestConfig());
  }

  /**
   * 获取 v3 接口使用的 http 客户端，未初始化时先初始化
   *
   * @return v3 接口的 http 客户端
   * @throws WxPayException 初始化失败
   */
  protected CloseableHttpClient createApiV3HttpClient() throws WxPayException {
    CloseableHttpClient apiV3HttpClient = this.getConfig().getApiV3HttpClient();
    if (null == apiV3HttpClient) {
      return this.getConfig().initApiV3HttpClient();
    }
    return apiV3HttpClient;
  }

  /**
   * 构造带有代理、SSL 和自定义配置的 http 客户端构造器
   *
   * @param useKey 是否需要使用证书
   * @return http 客户端构造器
   * @throws WxPayException 初始化 SSL 上下文失败
   */
  protected HttpClientBuilder createHttpClientBuilder(boolean useKey) throws WxPayException {
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

  /**
   * 构造 json 请求体的 post 请求
   *
   * @param url        请求地址
   * @param requestStr 请求数据
   * @return post 请求
   */
  protected HttpPost createHttpPost(String url, String requestStr) {
    HttpPost httpPost = new HttpPost(url);
    httpPost.setEntity(createEntry(requestStr));
    httpPost.setConfig(this.createRequestConfig());
    return httpPost;
  }

  /**
   * 构造指定 mimeType 请求体的 post 请求
   *
   * @param url        请求地址
   * @param requestStr 请求数据
   * @param mimeType   请求体类型
   * @return post 请求
   * @throws WxPayException 构造失败
   */
  protected HttpPost createHttpPost(String url, String requestStr, String mimeType) throws WxPayException {
    HttpPost httpPost = new HttpPost(url);
    httpPost.setEntity(createEntry(requestStr, mimeType));
    httpPost.setConfig(this.createRequestConfig());
    return httpPost;
  }

  /**
   * 按照配置构造请求的超时设置
   *
   * @return 请求配置
   */
  protected RequestConfig createRequestConfig() {
    return RequestConfig.custom()
      .setConnectionRequestTimeout(this.getConfig().getHttpConnectionTimeout())
      .setConnectTimeout(this.getConfig().getHttpConnectionTimeout())
      .setSocketTimeout(this.getConfig().getHttpTimeout())
      .build();
  }

  /**
   * 记录请求和响应数据
   *
   * @param url         请求地址
   * @param requestStr  请求数据
   * @param responseStr 响应数据
   */
  protected void logRequestAndResponse(String url, String requestStr, String responseStr) {
    log.info("\n【请求地址】：{}\n【请求数据】：{}\n【响应数据】：{}", url, requestStr, responseStr);
  }

  /**
   * 记录请求异常信息
   *
   * @param url        请求地址
   * @param requestStr 请求数据
   * @param e          异常
   */
  protected void logError(String url, String requestStr, Exception e) {
    log.error("\n【请求地址】：{}\n【请求数据】：{}\n【异常信息】：{}", url, requestStr, e.getMessage());
  }

  protected static StringEntity createEntry(String requestStr) {
    return new StringEntity(requestStr, ContentType.create(APPLICATION_JSON, StandardCharsets.UTF_8));
  }

  protected static StringEntity createEntry(String requestStr, String mimeType) {
    return new StringEntity(requestStr, ContentType.create(mimeType, StandardCharsets.UTF_8));
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

    try {
      return wxPayConfig.getVerifier().getValidCertificate().getSerialNumber().toString(16).toUpperCase();
    } catch (Exception e) {
      log.warn("Failed to get certificate serial number: {}", e.getMessage());
      // 返回空字符串而不是抛出异常，让请求继续进行，由微信服务器判断是否需要Wechatpay-Serial
      return "";
    }
  }
}
