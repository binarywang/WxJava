package me.chanjar.weixin.common.util.http.hc5;

import org.apache.hc.client5.http.ConnectionKeepAliveStrategy;
import org.apache.hc.client5.http.HttpRequestRetryStrategy;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;

/**
 * httpclient build interface.
 *
 * @author altusea
 */
public interface ApacheHttpClientBuilder {

  /**
   * 构建httpclient实例.
   *
   * @return new instance of CloseableHttpClient
   */
  CloseableHttpClient build();

  /**
   * 代理服务器地址.
   */
  ApacheHttpClientBuilder httpProxyHost(String httpProxyHost);

  /**
   * 代理服务器端口.
   */
  ApacheHttpClientBuilder httpProxyPort(int httpProxyPort);

  /**
   * 代理服务器用户名.
   */
  ApacheHttpClientBuilder httpProxyUsername(String httpProxyUsername);

  /**
   * 代理服务器密码.
   */
  ApacheHttpClientBuilder httpProxyPassword(char[] httpProxyPassword);

  /**
   * 重试策略.
   */
  ApacheHttpClientBuilder httpRequestRetryStrategy(HttpRequestRetryStrategy httpRequestRetryStrategy);

  /**
   * 超时时间.
   */
  ApacheHttpClientBuilder keepAliveStrategy(ConnectionKeepAliveStrategy keepAliveStrategy);
}
