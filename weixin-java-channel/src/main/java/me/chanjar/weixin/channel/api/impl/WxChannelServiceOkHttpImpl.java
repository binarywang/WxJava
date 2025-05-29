package me.chanjar.weixin.channel.api.impl;

import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.GET_ACCESS_TOKEN_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.GET_STABLE_ACCESS_TOKEN_URL;

import java.io.IOException;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.channel.bean.token.StableTokenParam;
import me.chanjar.weixin.channel.config.WxChannelConfig;
import me.chanjar.weixin.channel.util.JsonUtils;
import me.chanjar.weixin.common.util.http.HttpType;
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
public class WxChannelServiceOkHttpImpl extends BaseWxChannelServiceImpl<OkHttpClient, OkHttpProxyInfo> {
  private OkHttpClient httpClient;
  private OkHttpProxyInfo httpProxy;

  public WxChannelServiceOkHttpImpl() {
  }

  @Override
  public void initHttp() {
    log.debug("WxChannelServiceOkHttpImpl initHttp");
    if (this.config.getHttpProxyHost() != null && this.config.getHttpProxyPort() > 0) {
      this.httpProxy = OkHttpProxyInfo.httpProxy(this.config.getHttpProxyHost(), this.config.getHttpProxyPort(), this.config.getHttpProxyUsername(), this.config.getHttpProxyPassword());
      okhttp3.OkHttpClient.Builder clientBuilder = new okhttp3.OkHttpClient.Builder();
      clientBuilder.proxy(this.getRequestHttpProxy().getProxy());
      clientBuilder.authenticator(new Authenticator() {
        @Override
        public Request authenticate(Route route, Response response) throws IOException {
          String credential = Credentials.basic(WxChannelServiceOkHttpImpl.this.httpProxy.getProxyUsername(), WxChannelServiceOkHttpImpl.this.httpProxy.getProxyPassword());
          return response.request().newBuilder().header("Authorization", credential).build();
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
  public HttpType getRequestType() {
    return HttpType.OK_HTTP;
  }

  @Override
  protected String doGetAccessTokenRequest() throws IOException {
    WxChannelConfig config = this.getConfig();
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
    WxChannelConfig config = this.getConfig();
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
