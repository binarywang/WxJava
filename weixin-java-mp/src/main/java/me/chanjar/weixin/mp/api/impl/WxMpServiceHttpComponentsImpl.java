package me.chanjar.weixin.mp.api.impl;

import me.chanjar.weixin.common.util.http.HttpClientType;
import me.chanjar.weixin.common.util.http.hc.BasicResponseHandler;
import me.chanjar.weixin.common.util.http.hc.DefaultHttpComponentsClientBuilder;
import me.chanjar.weixin.common.util.http.hc.HttpComponentsClientBuilder;
import me.chanjar.weixin.mp.bean.WxMpStableAccessTokenRequest;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;

import static me.chanjar.weixin.mp.enums.WxMpApiUrl.Other.GET_ACCESS_TOKEN_URL;
import static me.chanjar.weixin.mp.enums.WxMpApiUrl.Other.GET_STABLE_ACCESS_TOKEN_URL;

/**
 * apache http client方式实现.
 *
 * @author altusea
 */
public class WxMpServiceHttpComponentsImpl extends BaseWxMpServiceImpl<CloseableHttpClient, HttpHost> {
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
    return HttpClientType.HTTP_COMPONENTS;
  }

  @Override
  public void initHttp() {
    WxMpConfigStorage configStorage = this.getWxMpConfigStorage();
    HttpComponentsClientBuilder apacheHttpClientBuilder = DefaultHttpComponentsClientBuilder.get();

    apacheHttpClientBuilder.httpProxyHost(configStorage.getHttpProxyHost())
      .httpProxyPort(configStorage.getHttpProxyPort())
      .httpProxyUsername(configStorage.getHttpProxyUsername())
      .httpProxyPassword(configStorage.getHttpProxyPassword().toCharArray());

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
    return getRequestHttpClient().execute(httpGet, BasicResponseHandler.INSTANCE);
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
    return getRequestHttpClient().execute(httpPost, BasicResponseHandler.INSTANCE);
  }

}
