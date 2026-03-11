package me.chanjar.weixin.qidian.api.impl;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.error.WxRuntimeException;
import me.chanjar.weixin.common.util.http.HttpClientType;
import me.chanjar.weixin.common.util.http.okhttp.DefaultOkHttpClientBuilder;
import me.chanjar.weixin.common.util.http.okhttp.OkHttpProxyInfo;
import me.chanjar.weixin.qidian.config.WxQidianConfigStorage;
import okhttp3.*;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

import static me.chanjar.weixin.qidian.enums.WxQidianApiUrl.Other.GET_ACCESS_TOKEN_URL;

/**
 * okhttp实现.
 *
 * @author someone
 */
public class WxQidianServiceOkHttpImpl extends BaseWxQidianServiceImpl<OkHttpClient, OkHttpProxyInfo> {
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
  public HttpClientType getRequestType() {
    return HttpClientType.OK_HTTP;
  }

  @Override
  public String getAccessToken(boolean forceRefresh) throws WxErrorException {
    final WxQidianConfigStorage config = this.getWxMpConfigStorage();
    if (!config.isAccessTokenExpired() && !forceRefresh) {
      return config.getAccessToken();
    }

    Lock lock = config.getAccessTokenLock();
    boolean locked = false;
    try {
      do {
        locked = lock.tryLock(100, TimeUnit.MILLISECONDS);
        if (!forceRefresh && !config.isAccessTokenExpired()) {
          return config.getAccessToken();
        }
      } while (!locked);
      String url = String.format(GET_ACCESS_TOKEN_URL.getUrl(config), config.getAppId(), config.getSecret());

      Request request = new Request.Builder().url(url).get().build();
      Response response = getRequestHttpClient().newCall(request).execute();
      return this.extractAccessToken(Objects.requireNonNull(response.body()).string());
    } catch (IOException | InterruptedException e) {
      throw new WxRuntimeException(e);
    } finally {
      if (locked) {
        lock.unlock();
      }
    }
  }

  @Override
  public void initHttp() {
    WxQidianConfigStorage wxMpConfigStorage = getWxMpConfigStorage();
    // 设置代理
    if (wxMpConfigStorage.getHttpProxyHost() != null && wxMpConfigStorage.getHttpProxyPort() > 0) {
      httpProxy = OkHttpProxyInfo.httpProxy(wxMpConfigStorage.getHttpProxyHost(), wxMpConfigStorage.getHttpProxyPort(),
        wxMpConfigStorage.getHttpProxyUsername(), wxMpConfigStorage.getHttpProxyPassword());
      OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
      clientBuilder.proxy(getRequestHttpProxy().getProxy());

      // 设置授权
      clientBuilder.proxyAuthenticator(new Authenticator() {
        @Override
        public Request authenticate(Route route, Response response) throws IOException {
          String credential = Credentials.basic(httpProxy.getProxyUsername(), httpProxy.getProxyPassword());
          return response.request().newBuilder().header("Proxy-Authorization", credential).build();
        }
      });
      httpClient = clientBuilder.build();
    } else {
      httpClient = DefaultOkHttpClientBuilder.get().build();
    }
  }

}
