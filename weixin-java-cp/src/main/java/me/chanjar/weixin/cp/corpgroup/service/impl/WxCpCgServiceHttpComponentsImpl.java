package me.chanjar.weixin.cp.corpgroup.service.impl;

import me.chanjar.weixin.common.util.http.HttpClientType;
import me.chanjar.weixin.common.util.http.hc.DefaultHttpComponentsClientBuilder;
import me.chanjar.weixin.common.util.http.hc.HttpComponentsClientBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.core5.http.HttpHost;

/**
 * @author altusea
 */
public class WxCpCgServiceHttpComponentsImpl extends BaseWxCpCgServiceImpl<CloseableHttpClient, HttpHost> {
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
    HttpComponentsClientBuilder apacheHttpClientBuilder = DefaultHttpComponentsClientBuilder.get();

    apacheHttpClientBuilder.httpProxyHost(this.configStorage.getHttpProxyHost())
      .httpProxyPort(this.configStorage.getHttpProxyPort())
      .httpProxyUsername(this.configStorage.getHttpProxyUsername())
      .httpProxyPassword(this.configStorage.getHttpProxyPassword().toCharArray());

    if (this.configStorage.getHttpProxyHost() != null && this.configStorage.getHttpProxyPort() > 0) {
      this.httpProxy = new HttpHost(this.configStorage.getHttpProxyHost(), this.configStorage.getHttpProxyPort());
    }

    this.httpClient = apacheHttpClientBuilder.build();
  }

}
