package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaStableAccessTokenRequest;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import me.chanjar.weixin.common.util.http.HttpClientType;
import me.chanjar.weixin.common.util.http.okhttp.DefaultOkHttpClientBuilder;
import me.chanjar.weixin.common.util.http.okhttp.OkHttpProxyInfo;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Objects;

/**
 * okhttp实现.
 */
public class WxMaServiceOkHttpImpl extends BaseWxMaServiceImpl<OkHttpClient, OkHttpProxyInfo> {

  private OkHttpClient httpClient;
  private OkHttpProxyInfo httpProxy;

  @Override
  public void initHttp() {
    WxMaConfig wxMpConfigStorage = this.getWxMaConfig();
    //设置代理
    if (wxMpConfigStorage.getHttpProxyHost() != null && wxMpConfigStorage.getHttpProxyPort() > 0) {
      httpProxy = OkHttpProxyInfo.httpProxy(wxMpConfigStorage.getHttpProxyHost(),
        wxMpConfigStorage.getHttpProxyPort(),
        wxMpConfigStorage.getHttpProxyUsername(),
        wxMpConfigStorage.getHttpProxyPassword());
      OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
      clientBuilder.proxy(getRequestHttpProxy().getProxy());
      //设置授权
      clientBuilder.authenticator(new Authenticator() {
        @Override
        public Request authenticate(Route route, Response response) throws IOException {
          String credential = Credentials.basic(httpProxy.getProxyUsername(), httpProxy.getProxyPassword());
          return response.request().newBuilder()
            .header("Authorization", credential)
            .build();
        }
      });
      httpClient = clientBuilder.build();
    } else {
      httpClient = DefaultOkHttpClientBuilder.get().build();
    }
  }

  @Override
  public OkHttpClient getRequestHttpClient() {
    return httpClient;
  }

  @Override
  public OkHttpProxyInfo getRequestHttpProxy() {
    return httpProxy;
  }

  @Override
  public HttpClientType getRequestType() {
    return HttpClientType.OK_HTTP;
  }

  @Override
  protected String doGetAccessTokenRequest() throws IOException {
    String url = StringUtils.isNotEmpty(this.getWxMaConfig().getAccessTokenUrl()) ?
      this.getWxMaConfig().getAccessTokenUrl() :
      WxMaService.GET_ACCESS_TOKEN_URL.replace(
        WxMaConfig.DEFAULT_API_HOST_URL, this.getWxMaConfig().getEffectiveApiHostUrl());

    url = String.format(url, this.getWxMaConfig().getAppid(), this.getWxMaConfig().getSecret());
    Request request = new Request.Builder().url(url).get().build();
    try (Response response = getRequestHttpClient().newCall(request).execute()) {
      return Objects.requireNonNull(response.body()).string();
    }
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
    RequestBody body = RequestBody.Companion.create(wxMaAccessTokenRequest.toJson(), MediaType.parse("application/json; charset=utf-8"));
    Request request = new Request.Builder().url(url).post(body).build();
    try (Response response = getRequestHttpClient().newCall(request).execute()) {
      return Objects.requireNonNull(response.body()).string();
    }
  }
}
