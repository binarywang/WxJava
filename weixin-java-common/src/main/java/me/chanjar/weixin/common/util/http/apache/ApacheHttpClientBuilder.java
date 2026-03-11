package me.chanjar.weixin.common.util.http.apache;

import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;

/**
 * httpclient build interface.
 *
 * @author kakotor
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
   *
   * @param httpProxyHost 代理服务器地址
   * @return ApacheHttpClientBuilder
   */
  ApacheHttpClientBuilder httpProxyHost(String httpProxyHost);

  /**
   * 代理服务器端口.
   *
   * @param httpProxyPort 代理服务器端口
   * @return ApacheHttpClientBuilder
   */
  ApacheHttpClientBuilder httpProxyPort(int httpProxyPort);

  /**
   * 代理服务器用户名.
   *
   * @param httpProxyUsername 代理服务器用户名
   * @return ApacheHttpClientBuilder
   */
  ApacheHttpClientBuilder httpProxyUsername(String httpProxyUsername);

  /**
   * 代理服务器密码.
   *
   * @param httpProxyPassword 代理服务器密码
   * @return ApacheHttpClientBuilder
   */
  ApacheHttpClientBuilder httpProxyPassword(String httpProxyPassword);

  /**
   * 重试策略.
   *
   * @param httpRequestRetryHandler 重试处理器
   * @return ApacheHttpClientBuilder
   */
  ApacheHttpClientBuilder httpRequestRetryHandler(HttpRequestRetryHandler httpRequestRetryHandler);

  /**
   * 超时时间.
   *
   * @param keepAliveStrategy 保持连接策略
   * @return ApacheHttpClientBuilder
   */
  ApacheHttpClientBuilder keepAliveStrategy(ConnectionKeepAliveStrategy keepAliveStrategy);

  /**
   * ssl连接socket工厂.
   *
   * @param sslConnectionSocketFactory SSL连接Socket工厂
   * @return ApacheHttpClientBuilder
   */
  ApacheHttpClientBuilder sslConnectionSocketFactory(SSLConnectionSocketFactory sslConnectionSocketFactory);

  /**
   * 支持的TLS协议版本.
   * Supported TLS protocol versions.
   *
   * @param supportedProtocols 支持的协议版本数组
   * @return ApacheHttpClientBuilder
   */
  ApacheHttpClientBuilder supportedProtocols(String[] supportedProtocols);
}
