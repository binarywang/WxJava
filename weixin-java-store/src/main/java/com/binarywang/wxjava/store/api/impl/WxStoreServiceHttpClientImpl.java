package com.binarywang.wxjava.store.api.impl;

import lombok.extern.slf4j.Slf4j;
import com.binarywang.wxjava.store.bean.token.StableTokenParam;
import com.binarywang.wxjava.store.config.WxStoreConfig;
import com.binarywang.wxjava.store.util.JsonUtils;
import me.chanjar.weixin.common.util.http.HttpClientType;
import me.chanjar.weixin.common.util.http.apache.ApacheBasicResponseHandler;
import me.chanjar.weixin.common.util.http.apache.ApacheHttpClientBuilder;
import me.chanjar.weixin.common.util.http.apache.DefaultApacheHttpClientBuilder;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;

import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.GET_ACCESS_TOKEN_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.GET_STABLE_ACCESS_TOKEN_URL;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Slf4j
public class WxStoreServiceHttpClientImpl extends BaseWxStoreServiceImpl<HttpClient, HttpHost> {

  private CloseableHttpClient httpClient;
  private HttpHost httpProxy;

  @Override
  public void initHttp() {
    WxStoreConfig config = this.getConfig();
    ApacheHttpClientBuilder apacheHttpClientBuilder = config.getApacheHttpClientBuilder();
    if (null == apacheHttpClientBuilder) {
      apacheHttpClientBuilder = DefaultApacheHttpClientBuilder.get();
    }

    apacheHttpClientBuilder.httpProxyHost(config.getHttpProxyHost())
      .httpProxyPort(config.getHttpProxyPort())
      .httpProxyUsername(config.getHttpProxyUsername())
      .httpProxyPassword(config.getHttpProxyPassword());

    if (config.getHttpProxyHost() != null && config.getHttpProxyPort() > 0) {
      this.httpProxy = new HttpHost(config.getHttpProxyHost(), config.getHttpProxyPort());
    }

    this.httpClient = apacheHttpClientBuilder.build();
  }

  @Override
  public CloseableHttpClient getRequestHttpClient() {
    return httpClient;
  }

  @Override
  public HttpHost getRequestHttpProxy() {
    return httpProxy;
  }

  @Override
  public HttpClientType getRequestType() {
    return HttpClientType.APACHE_HTTP;
  }

  @Override
  protected String doGetAccessTokenRequest() throws IOException {
    WxStoreConfig config = this.getConfig();
    String url = StringUtils.isNotEmpty(config.getAccessTokenUrl()) ? config.getAccessTokenUrl() :
      StringUtils.isNotEmpty(config.getApiHostUrl()) ?
        GET_ACCESS_TOKEN_URL.replace("https://api.weixin.qq.com", config.getApiHostUrl()) : GET_ACCESS_TOKEN_URL;

    url = String.format(url, config.getAppid(), config.getSecret());

    HttpGet httpGet = new HttpGet(url);
    if (this.getRequestHttpProxy() != null) {
      RequestConfig requestConfig = RequestConfig.custom().setProxy(this.getRequestHttpProxy()).build();
      httpGet.setConfig(requestConfig);
    }
    return getRequestHttpClient().execute(httpGet, ApacheBasicResponseHandler.INSTANCE);
  }

  /**
   * 获取稳定版接口调用凭据
   *
   * @param forceRefresh false 为普通模式， true为强制刷新模式
   * @return 返回json的字符串
   * @throws IOException the io exception
   */
  @Override
  protected String doGetStableAccessTokenRequest(boolean forceRefresh) throws IOException {
    WxStoreConfig config = this.getConfig();
    String url = StringUtils.isNotEmpty(config.getAccessTokenUrl()) ?
      config.getAccessTokenUrl() : StringUtils.isNotEmpty(config.getApiHostUrl()) ?
      GET_STABLE_ACCESS_TOKEN_URL.replace("https://api.weixin.qq.com", config.getApiHostUrl()) :
      GET_STABLE_ACCESS_TOKEN_URL;

    HttpPost httpPost = new HttpPost(url);
    if (this.getRequestHttpProxy() != null) {
      RequestConfig requestConfig = RequestConfig.custom().setProxy(this.getRequestHttpProxy()).build();
      httpPost.setConfig(requestConfig);
    }
    StableTokenParam requestParam = new StableTokenParam();
    requestParam.setAppId(config.getAppid());
    requestParam.setSecret(config.getSecret());
    requestParam.setGrantType("client_credential");
    requestParam.setForceRefresh(forceRefresh);
    String requestJson = JsonUtils.encode(requestParam);
    assert requestJson != null;

    httpPost.setEntity(new StringEntity(requestJson, ContentType.APPLICATION_JSON));
    return getRequestHttpClient().execute(httpPost, ApacheBasicResponseHandler.INSTANCE);
  }
}
