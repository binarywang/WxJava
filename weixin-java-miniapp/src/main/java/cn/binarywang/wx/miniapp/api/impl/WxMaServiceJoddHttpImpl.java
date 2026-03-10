package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaStableAccessTokenRequest;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import jodd.http.HttpConnectionProvider;
import jodd.http.HttpRequest;
import jodd.http.ProxyInfo;
import jodd.http.net.SocketHttpConnectionProvider;
import jodd.net.MimeTypes;
import me.chanjar.weixin.common.util.http.HttpClientType;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * jodd-http方式实现.
 *
 * @author someone
 */
public class WxMaServiceJoddHttpImpl extends BaseWxMaServiceImpl<HttpConnectionProvider, ProxyInfo> {
  private HttpConnectionProvider httpClient;
  private ProxyInfo httpProxy;

  @Override
  public void initHttp() {
    WxMaConfig configStorage = this.getWxMaConfig();
    if (configStorage.getHttpProxyHost() != null && configStorage.getHttpProxyPort() > 0) {
      this.httpProxy = new ProxyInfo(ProxyInfo.ProxyType.HTTP, configStorage.getHttpProxyHost(), configStorage.getHttpProxyPort(), configStorage.getHttpProxyUsername(), configStorage.getHttpProxyPassword());
    }
    this.httpClient = new SocketHttpConnectionProvider();
  }

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
  protected String doGetAccessTokenRequest() throws IOException {
    String url = StringUtils.isNotEmpty(this.getWxMaConfig().getAccessTokenUrl()) ?
      this.getWxMaConfig().getAccessTokenUrl() :
      WxMaService.GET_ACCESS_TOKEN_URL.replace(
        WxMaConfig.DEFAULT_API_HOST_URL, this.getWxMaConfig().getEffectiveApiHostUrl());

    url = String.format(url, this.getWxMaConfig().getAppid(), this.getWxMaConfig().getSecret());
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
    String url = StringUtils.isNotEmpty(this.getWxMaConfig().getAccessTokenUrl()) ?
      this.getWxMaConfig().getAccessTokenUrl() :
      GET_STABLE_ACCESS_TOKEN.replace(
        WxMaConfig.DEFAULT_API_HOST_URL, this.getWxMaConfig().getEffectiveApiHostUrl());

    WxMaStableAccessTokenRequest wxMaAccessTokenRequest = new WxMaStableAccessTokenRequest();
    wxMaAccessTokenRequest.setAppid(this.getWxMaConfig().getAppid());
    wxMaAccessTokenRequest.setSecret(this.getWxMaConfig().getSecret());
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
