package me.chanjar.weixin.mp.api.impl;

import me.chanjar.weixin.common.util.http.HttpClientType;
import me.chanjar.weixin.common.util.http.apache.ApacheBasicResponseHandler;
import me.chanjar.weixin.common.util.http.apache.ApacheHttpClientBuilder;
import me.chanjar.weixin.common.util.http.apache.DefaultApacheHttpClientBuilder;
import me.chanjar.weixin.mp.bean.WxMpStableAccessTokenRequest;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;

import static me.chanjar.weixin.mp.enums.WxMpApiUrl.Other.GET_ACCESS_TOKEN_URL;
import static me.chanjar.weixin.mp.enums.WxMpApiUrl.Other.GET_STABLE_ACCESS_TOKEN_URL;

/**
 * apache http client方式实现.
 *
 * @author someone
 */
public class WxMpServiceHttpClientImpl extends BaseWxMpServiceImpl<CloseableHttpClient, HttpHost> {
  private CloseableHttpClient httpClient;
  private HttpHost httpProxy;

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
  public void initHttp() {
    WxMpConfigStorage configStorage = this.getWxMpConfigStorage();
    ApacheHttpClientBuilder apacheHttpClientBuilder = configStorage.getApacheHttpClientBuilder();
    if (null == apacheHttpClientBuilder) {
      apacheHttpClientBuilder = DefaultApacheHttpClientBuilder.get();
    }

    apacheHttpClientBuilder.httpProxyHost(configStorage.getHttpProxyHost())
      .httpProxyPort(configStorage.getHttpProxyPort())
      .httpProxyUsername(configStorage.getHttpProxyUsername())
      .httpProxyPassword(configStorage.getHttpProxyPassword());

    if (configStorage.getHttpProxyHost() != null && configStorage.getHttpProxyPort() > 0) {
      this.httpProxy = new HttpHost(configStorage.getHttpProxyHost(), configStorage.getHttpProxyPort());
    }

    this.httpClient = apacheHttpClientBuilder.build();
  }

  @Override
  protected String doGetAccessTokenRequest() throws IOException {
    String url = String.format(GET_ACCESS_TOKEN_URL.getUrl(getWxMpConfigStorage()), getWxMpConfigStorage().getAppId(), getWxMpConfigStorage().getSecret());

    HttpGet httpGet = new HttpGet(url);
    if (this.getRequestHttpProxy() != null) {
      RequestConfig config = RequestConfig.custom().setProxy(this.getRequestHttpProxy()).build();
      httpGet.setConfig(config);
    }
    return getRequestHttpClient().execute(httpGet, ApacheBasicResponseHandler.INSTANCE);
  }

  @Override
  protected String doGetStableAccessTokenRequest(boolean forceRefresh) throws IOException {
    String url = GET_STABLE_ACCESS_TOKEN_URL.getUrl(getWxMpConfigStorage());

    HttpPost httpPost = new HttpPost(url);
    if (this.getRequestHttpProxy() != null) {
      RequestConfig config = RequestConfig.custom().setProxy(this.getRequestHttpProxy()).build();
      httpPost.setConfig(config);
    }
    WxMpStableAccessTokenRequest wxMaAccessTokenRequest = new WxMpStableAccessTokenRequest();
    wxMaAccessTokenRequest.setAppid(this.getWxMpConfigStorage().getAppId());
    wxMaAccessTokenRequest.setSecret(this.getWxMpConfigStorage().getSecret());
    wxMaAccessTokenRequest.setGrantType("client_credential");
    wxMaAccessTokenRequest.setForceRefresh(forceRefresh);

    httpPost.setEntity(new StringEntity(wxMaAccessTokenRequest.toJson(), ContentType.APPLICATION_JSON));
    return getRequestHttpClient().execute(httpPost, ApacheBasicResponseHandler.INSTANCE);
  }

}
