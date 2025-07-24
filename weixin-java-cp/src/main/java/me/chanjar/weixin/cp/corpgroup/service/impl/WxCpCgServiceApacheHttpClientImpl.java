package me.chanjar.weixin.cp.corpgroup.service.impl;

import me.chanjar.weixin.common.util.http.HttpClientType;
import me.chanjar.weixin.common.util.http.apache.ApacheHttpClientBuilder;
import me.chanjar.weixin.common.util.http.apache.DefaultApacheHttpClientBuilder;
import org.apache.http.HttpHost;
import org.apache.http.impl.client.CloseableHttpClient;

/**
 * @author libo Email: 422423229@qq.com
 * @since 1/3/2023 6:16 PM
 */
public class WxCpCgServiceApacheHttpClientImpl extends BaseWxCpCgServiceImpl<CloseableHttpClient, HttpHost> {
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
    return HttpClientType.APACHE_HTTP;
  }

  @Override
  public void initHttp() {
    ApacheHttpClientBuilder apacheHttpClientBuilder = this.configStorage.getApacheHttpClientBuilder();
    if (null == apacheHttpClientBuilder) {
      apacheHttpClientBuilder = DefaultApacheHttpClientBuilder.get();
    }

    apacheHttpClientBuilder.httpProxyHost(this.configStorage.getHttpProxyHost())
      .httpProxyPort(this.configStorage.getHttpProxyPort())
      .httpProxyUsername(this.configStorage.getHttpProxyUsername())
      .httpProxyPassword(this.configStorage.getHttpProxyPassword());

    if (this.configStorage.getHttpProxyHost() != null && this.configStorage.getHttpProxyPort() > 0) {
      this.httpProxy = new HttpHost(this.configStorage.getHttpProxyHost(), this.configStorage.getHttpProxyPort());
    }

    this.httpClient = apacheHttpClientBuilder.build();
  }

}
