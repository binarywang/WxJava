package me.chanjar.weixin.mp.api.impl;

import me.chanjar.weixin.common.util.http.HttpType;
import me.chanjar.weixin.common.util.http.okhttp.DefaultOkHttpClientBuilder;
import me.chanjar.weixin.common.util.http.okhttp.OkHttpProxyInfo;
import me.chanjar.weixin.mp.bean.WxMpStableAccessTokenRequest;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import okhttp3.*;

import java.io.IOException;
import java.util.Objects;

import static me.chanjar.weixin.mp.enums.WxMpApiUrl.Other.GET_ACCESS_TOKEN_URL;
import static me.chanjar.weixin.mp.enums.WxMpApiUrl.Other.GET_STABLE_ACCESS_TOKEN_URL;

/**
 * okhttp实现.
 *
 * @author someone
 */
public class WxMpServiceOkHttpImpl extends BaseWxMpServiceImpl<OkHttpClient, OkHttpProxyInfo> {
  private OkHttpClient httpClient;
  private OkHttpProxyInfo httpProxy;

  @Override
  public OkHttpClient getRequestHttpClient() {
    return httpClient;
  }

  @Override
  public OkHttpProxyInfo getRequestHttpProxy() {
    return httpProxy;
  }

  @Override
  public HttpType getRequestType() {
    return HttpType.OK_HTTP;
  }

  @Override
  public void initHttp() {
    WxMpConfigStorage wxMpConfigStorage = getWxMpConfigStorage();
    //设置代理
    if (wxMpConfigStorage.getHttpProxyHost() != null && wxMpConfigStorage.getHttpProxyPort() > 0) {
      httpProxy = OkHttpProxyInfo.httpProxy(wxMpConfigStorage.getHttpProxyHost(),
        wxMpConfigStorage.getHttpProxyPort(),
        wxMpConfigStorage.getHttpProxyUsername(),
        wxMpConfigStorage.getHttpProxyPassword());
      OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
      clientBuilder.proxy(getRequestHttpProxy().getProxy());

      //设置授权
      clientBuilder.proxyAuthenticator(new Authenticator() {
        @Override
        public Request authenticate(Route route, Response response) throws IOException {
          String credential = Credentials.basic(httpProxy.getProxyUsername(), httpProxy.getProxyPassword());
          return response.request().newBuilder()
            .header("Proxy-Authorization", credential)
            .build();
        }
      });
      httpClient = clientBuilder.build();
    } else {
      httpClient = DefaultOkHttpClientBuilder.get().build();
    }
  }

  @Override
  protected String doGetAccessTokenRequest() throws IOException {
    String url = String.format(GET_ACCESS_TOKEN_URL.getUrl(getWxMpConfigStorage()), getWxMpConfigStorage().getAppId(), getWxMpConfigStorage().getSecret());

    Request request = new Request.Builder().url(url).get().build();
    try (Response response = getRequestHttpClient().newCall(request).execute()) {
      return Objects.requireNonNull(response.body()).string();
    }
  }

  @Override
  protected String doGetStableAccessTokenRequest(boolean forceRefresh) throws IOException {
    String url = GET_STABLE_ACCESS_TOKEN_URL.getUrl(getWxMpConfigStorage());

    WxMpStableAccessTokenRequest wxMaAccessTokenRequest = new WxMpStableAccessTokenRequest();
    wxMaAccessTokenRequest.setAppid(this.getWxMpConfigStorage().getAppId());
    wxMaAccessTokenRequest.setSecret(this.getWxMpConfigStorage().getSecret());
    wxMaAccessTokenRequest.setGrantType("client_credential");
    wxMaAccessTokenRequest.setForceRefresh(forceRefresh);

    RequestBody body = RequestBody.Companion.create(wxMaAccessTokenRequest.toJson(), MediaType.parse("application/json; charset=utf-8"));
    Request request = new Request.Builder().url(url).post(body).build();
    try (Response response = getRequestHttpClient().newCall(request).execute()) {
      return Objects.requireNonNull(response.body()).string();
    }
  }
}
