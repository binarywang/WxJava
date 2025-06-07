package me.chanjar.weixin.qidian.api.impl;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.error.WxRuntimeException;
import me.chanjar.weixin.common.util.http.HttpClientType;
import me.chanjar.weixin.common.util.http.hc.BasicResponseHandler;
import me.chanjar.weixin.common.util.http.hc.DefaultHttpComponentsClientBuilder;
import me.chanjar.weixin.common.util.http.hc.HttpComponentsClientBuilder;
import me.chanjar.weixin.qidian.config.WxQidianConfigStorage;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.core5.http.HttpHost;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

import static me.chanjar.weixin.qidian.enums.WxQidianApiUrl.Other.GET_ACCESS_TOKEN_URL;

/**
 * apache http client5 方式实现.
 *
 * @author altusea
 */
public class WxQidianServiceHttpComponentsImpl extends BaseWxQidianServiceImpl<CloseableHttpClient, HttpHost> {
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
    WxQidianConfigStorage configStorage = this.getWxMpConfigStorage();
    HttpComponentsClientBuilder apacheHttpClientBuilder = DefaultHttpComponentsClientBuilder.get();

    apacheHttpClientBuilder.httpProxyHost(configStorage.getHttpProxyHost())
        .httpProxyPort(configStorage.getHttpProxyPort()).httpProxyUsername(configStorage.getHttpProxyUsername())
        .httpProxyPassword(configStorage.getHttpProxyPassword().toCharArray());

    if (configStorage.getHttpProxyHost() != null && configStorage.getHttpProxyPort() > 0) {
      this.httpProxy = new HttpHost(configStorage.getHttpProxyHost(), configStorage.getHttpProxyPort());
    }

    this.httpClient = apacheHttpClientBuilder.build();
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
      try {
        HttpGet httpGet = new HttpGet(url);
        if (this.getRequestHttpProxy() != null) {
          RequestConfig requestConfig = RequestConfig.custom().setProxy(this.getRequestHttpProxy()).build();
          httpGet.setConfig(requestConfig);
        }
        String responseContent = getRequestHttpClient().execute(httpGet, BasicResponseHandler.INSTANCE);
        return this.extractAccessToken(responseContent);
      } catch (IOException e) {
        throw new WxRuntimeException(e);
      }
    } catch (InterruptedException e) {
      throw new WxRuntimeException(e);
    } finally {
      if (locked) {
        lock.unlock();
      }
    }
  }

}
