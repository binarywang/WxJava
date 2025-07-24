package me.chanjar.weixin.mp.api.impl;

import jodd.http.HttpConnectionProvider;
import jodd.http.HttpRequest;
import jodd.http.ProxyInfo;
import jodd.http.net.SocketHttpConnectionProvider;
import jodd.net.MimeTypes;
import me.chanjar.weixin.common.util.http.HttpClientType;
import me.chanjar.weixin.mp.bean.WxMpStableAccessTokenRequest;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static me.chanjar.weixin.mp.enums.WxMpApiUrl.Other.GET_ACCESS_TOKEN_URL;
import static me.chanjar.weixin.mp.enums.WxMpApiUrl.Other.GET_STABLE_ACCESS_TOKEN_URL;

/**
 * jodd-http方式实现.
 *
 * @author someone
 */
public class WxMpServiceJoddHttpImpl extends BaseWxMpServiceImpl<HttpConnectionProvider, ProxyInfo> {
  private HttpConnectionProvider httpClient;
  private ProxyInfo httpProxy;

  @Override
  public HttpConnectionProvider getRequestHttpClient() {
    return httpClient;
  }

  @Override
  public ProxyInfo getRequestHttpProxy() {
    return httpProxy;
  }

  @Override
  public HttpClientType getRequestType() {
    return HttpClientType.JODD_HTTP;
  }

  @Override
  public void initHttp() {

    WxMpConfigStorage configStorage = this.getWxMpConfigStorage();

    if (configStorage.getHttpProxyHost() != null && configStorage.getHttpProxyPort() > 0) {
      httpProxy = new ProxyInfo(ProxyInfo.ProxyType.HTTP, configStorage.getHttpProxyHost(), configStorage.getHttpProxyPort(), configStorage.getHttpProxyUsername(), configStorage.getHttpProxyPassword());
    }

    httpClient = new SocketHttpConnectionProvider();
  }

  @Override
  protected String doGetAccessTokenRequest() throws IOException {
    String url = String.format(GET_ACCESS_TOKEN_URL.getUrl(getWxMpConfigStorage()), getWxMpConfigStorage().getAppId(), getWxMpConfigStorage().getSecret());

    HttpRequest request = HttpRequest.get(url);
    if (this.getRequestHttpProxy() != null) {
      SocketHttpConnectionProvider provider = new SocketHttpConnectionProvider();
      provider.useProxy(getRequestHttpProxy());

      request.withConnectionProvider(provider);
    }
    return request.send().bodyText();
  }

  @Override
  protected String doGetStableAccessTokenRequest(boolean forceRefresh) throws IOException {
    String url = GET_STABLE_ACCESS_TOKEN_URL.getUrl(getWxMpConfigStorage());

    WxMpStableAccessTokenRequest wxMaAccessTokenRequest = new WxMpStableAccessTokenRequest();
    wxMaAccessTokenRequest.setAppid(this.getWxMpConfigStorage().getAppId());
    wxMaAccessTokenRequest.setSecret(this.getWxMpConfigStorage().getSecret());
    wxMaAccessTokenRequest.setGrantType("client_credential");
    wxMaAccessTokenRequest.setForceRefresh(forceRefresh);

    HttpRequest request = HttpRequest.post(url)
      .contentType(MimeTypes.MIME_APPLICATION_JSON, StandardCharsets.UTF_8.name())
      .body(wxMaAccessTokenRequest.toJson());
    if (this.getRequestHttpProxy() != null) {
      SocketHttpConnectionProvider provider = new SocketHttpConnectionProvider();
      provider.useProxy(getRequestHttpProxy());

      request.withConnectionProvider(provider);
    }
    return request.send().bodyText();
  }

}
