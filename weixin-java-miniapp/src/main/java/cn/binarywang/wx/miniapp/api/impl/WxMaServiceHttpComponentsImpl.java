package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.bean.WxMaStableAccessTokenRequest;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.util.http.HttpClientType;
import me.chanjar.weixin.common.util.http.hc.BasicResponseHandler;
import me.chanjar.weixin.common.util.http.hc.DefaultHttpComponentsClientBuilder;
import me.chanjar.weixin.common.util.http.hc.HttpComponentsClientBuilder;
import org.apache.commons.lang3.StringUtils;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;

/**
 * Apache Http client5 方式实现
 *
 * @author <a href="https://github.com/buaazyl">zhangyl</a>
 */
@Slf4j
public class WxMaServiceHttpComponentsImpl extends BaseWxMaServiceImpl<CloseableHttpClient, HttpHost> {
  private CloseableHttpClient httpClient;
  private HttpHost httpProxy;

  @Override
  public void initHttp() {
    WxMaConfig configStorage = this.getWxMaConfig();
    HttpComponentsClientBuilder apacheHttpClientBuilder = DefaultHttpComponentsClientBuilder.get();

    apacheHttpClientBuilder.httpProxyHost(configStorage.getHttpProxyHost())
      .httpProxyPort(configStorage.getHttpProxyPort())
      .httpProxyUsername(configStorage.getHttpProxyUsername())
      .httpProxyPassword(configStorage.getHttpProxyPassword() == null ? null :
        configStorage.getHttpProxyPassword().toCharArray());

    if (configStorage.getHttpProxyHost() != null && configStorage.getHttpProxyPort() > 0) {
      this.httpProxy = new HttpHost(configStorage.getHttpProxyHost(), configStorage.getHttpProxyPort());
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
    return HttpClientType.HTTP_COMPONENTS;
  }

  @Override
  protected String doGetAccessTokenRequest() throws IOException {
    String url = StringUtils.isNotEmpty(this.getWxMaConfig().getAccessTokenUrl()) ?
      this.getWxMaConfig().getAccessTokenUrl() :
      GET_ACCESS_TOKEN_URL.replace(WxMaConfig.DEFAULT_API_HOST_URL, this.getWxMaConfig().getEffectiveApiHostUrl());

    url = String.format(url, this.getWxMaConfig().getAppid(), this.getWxMaConfig().getSecret());

    HttpGet httpGet = new HttpGet(url);
    if (this.getRequestHttpProxy() != null) {
      RequestConfig config = RequestConfig.custom().setProxy(this.getRequestHttpProxy()).build();
      httpGet.setConfig(config);
    }
    return getRequestHttpClient().execute(httpGet, BasicResponseHandler.INSTANCE);
  }

  @Override
  protected String doGetStableAccessTokenRequest(boolean forceRefresh) throws IOException {
    String url = StringUtils.isNotEmpty(this.getWxMaConfig().getAccessTokenUrl()) ?
      this.getWxMaConfig().getAccessTokenUrl() :
      GET_STABLE_ACCESS_TOKEN.replace(
        WxMaConfig.DEFAULT_API_HOST_URL, this.getWxMaConfig().getEffectiveApiHostUrl());

    HttpPost httpPost = new HttpPost(url);
    if (this.getRequestHttpProxy() != null) {
      RequestConfig config = RequestConfig.custom().setProxy(this.getRequestHttpProxy()).build();
      httpPost.setConfig(config);
    }
    WxMaStableAccessTokenRequest wxMaAccessTokenRequest = new WxMaStableAccessTokenRequest();
    wxMaAccessTokenRequest.setAppid(this.getWxMaConfig().getAppid());
    wxMaAccessTokenRequest.setSecret(this.getWxMaConfig().getSecret());
    wxMaAccessTokenRequest.setGrantType("client_credential");
    wxMaAccessTokenRequest.setForceRefresh(forceRefresh);

    httpPost.setEntity(new StringEntity(wxMaAccessTokenRequest.toJson(), ContentType.APPLICATION_JSON));
    return getRequestHttpClient().execute(httpPost, BasicResponseHandler.INSTANCE);
  }
}
