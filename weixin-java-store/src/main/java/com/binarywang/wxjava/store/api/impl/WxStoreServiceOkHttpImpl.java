package com.binarywang.wxjava.store.api.impl;

import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.GET_ACCESS_TOKEN_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.GET_STABLE_ACCESS_TOKEN_URL;

import java.io.IOException;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import com.binarywang.wxjava.store.bean.token.StableTokenParam;
import com.binarywang.wxjava.store.config.WxStoreConfig;
import com.binarywang.wxjava.store.util.JsonUtils;
import me.chanjar.weixin.common.util.http.HttpClientType;
import me.chanjar.weixin.common.util.http.okhttp.DefaultOkHttpClientBuilder;
import me.chanjar.weixin.common.util.http.okhttp.OkHttpProxyInfo;
import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Route;
import org.apache.commons.lang3.StringUtils;

/**
 * @author : zhenyun.su
 * @since : 2024/2/27
 */
@Slf4j
public class WxStoreServiceOkHttpImpl extends BaseWxStoreServiceImpl<OkHttpClient, OkHttpProxyInfo> {
  private OkHttpClient httpClient;
  private OkHttpProxyInfo httpProxy;

  public WxStoreServiceOkHttpImpl() {
  }

  @Override
  public void initHttp() {
    log.debug("WxStoreServiceOkHttpImpl initHttp");
    if (this.config.getHttpProxyHost() != null && this.config.getHttpProxyPort() > 0) {
      this.httpProxy = OkHttpProxyInfo.httpProxy(this.config.getHttpProxyHost(), this.config.getHttpProxyPort(), this.config.getHttpProxyUsername(), this.config.getHttpProxyPassword());
      okhttp3.OkHttpClient.Builder clientBuilder = new okhttp3.OkHttpClient.Builder();
      clientBuilder.proxy(this.getRequestHttpProxy().getProxy());
      clientBuilder.proxyAuthenticator(new Authenticator() {
        @Override
        public Request authenticate(Route route, Response response) throws IOException {
          String credential = Credentials.basic(WxStoreServiceOkHttpImpl.this.httpProxy.getProxyUsername(), WxStoreServiceOkHttpImpl.this.httpProxy.getProxyPassword());
          return response.request().newBuilder().header("Proxy-Authorization", credential).build();
        }
      });
      this.httpClient = clientBuilder.build();
    } else {
      this.httpClient = DefaultOkHttpClientBuilder.get().build();
    }
  }

  @Override
  public OkHttpClient getRequestHttpClient() {
    return this.httpClient;
  }

  @Override
  public OkHttpProxyInfo getRequestHttpProxy() {
    return this.httpProxy;
  }

  @Override
  public HttpClientType getRequestType() {
    return HttpClientType.OK_HTTP;
  }

  @Override
  protected String doGetAccessTokenRequest() throws IOException {
    WxStoreConfig config = this.getConfig();
    String url = StringUtils.isNotEmpty(config.getAccessTokenUrl()) ? config.getAccessTokenUrl() :
      StringUtils.isNotEmpty(config.getApiHostUrl()) ?
        GET_ACCESS_TOKEN_URL.replace("https://api.weixin.qq.com", config.getApiHostUrl()) : GET_ACCESS_TOKEN_URL;

    url = String.format(url, config.getAppid(), config.getSecret());

    Request request = new Request.Builder().url(url).get().build();
    try (Response response = getRequestHttpClient().newCall(request).execute()) {
      return Objects.requireNonNull(response.body()).string();
    }
  }

  @Override
  protected String doGetStableAccessTokenRequest(boolean forceRefresh) throws IOException {
    WxStoreConfig config = this.getConfig();
    String url = StringUtils.isNotEmpty(config.getAccessTokenUrl()) ?
      config.getAccessTokenUrl() : StringUtils.isNotEmpty(config.getApiHostUrl()) ?
      GET_STABLE_ACCESS_TOKEN_URL.replace("https://api.weixin.qq.com", config.getApiHostUrl()) :
      GET_STABLE_ACCESS_TOKEN_URL;

    StableTokenParam requestParam = new StableTokenParam();
    requestParam.setAppId(config.getAppid());
    requestParam.setSecret(config.getSecret());
    requestParam.setGrantType("client_credential");
    requestParam.setForceRefresh(forceRefresh);
    String requestJson = JsonUtils.encode(requestParam);
    assert requestJson != null;

    RequestBody body = RequestBody.Companion.create(requestJson, MediaType.parse("application/json; charset=utf-8"));
    Request request = new Request.Builder().url(url).post(body).build();
    try (Response response = getRequestHttpClient().newCall(request).execute()) {
      return Objects.requireNonNull(response.body()).string();
    }
  }
}
