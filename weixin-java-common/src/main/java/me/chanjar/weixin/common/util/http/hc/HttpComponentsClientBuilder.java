package me.chanjar.weixin.common.util.http.hc;

import me.chanjar.weixin.common.util.http.apache.ApacheHttpClientBuilder;
import org.apache.hc.client5.http.ConnectionKeepAliveStrategy;
import org.apache.hc.client5.http.HttpRequestRetryStrategy;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;

/**
 * httpclient build interface.
 *
 * @author altusea
 */
public interface HttpComponentsClientBuilder {

  /**
   * 构建httpclient实例.
   *
   * @return new instance of CloseableHttpClient
   */
  CloseableHttpClient build();

  /**
   * 代理服务器地址.
   */
  HttpComponentsClientBuilder httpProxyHost(String httpProxyHost);

  /**
   * 代理服务器端口.
   */
  HttpComponentsClientBuilder httpProxyPort(int httpProxyPort);

  /**
   * 代理服务器用户名.
   */
  HttpComponentsClientBuilder httpProxyUsername(String httpProxyUsername);

  /**
   * 代理服务器密码.
   */
  HttpComponentsClientBuilder httpProxyPassword(char[] httpProxyPassword);

  /**
   * 重试策略.
   */
  HttpComponentsClientBuilder httpRequestRetryStrategy(HttpRequestRetryStrategy httpRequestRetryStrategy);

  /**
   * 超时时间.
   */
  HttpComponentsClientBuilder keepAliveStrategy(ConnectionKeepAliveStrategy keepAliveStrategy);
}
