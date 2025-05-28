package me.chanjar.weixin.common.util.http.hc5;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.hc.client5.http.ConnectionKeepAliveStrategy;
import org.apache.hc.client5.http.HttpRequestRetryStrategy;
import org.apache.hc.client5.http.auth.AuthScope;
import org.apache.hc.client5.http.auth.UsernamePasswordCredentials;
import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.auth.BasicCredentialsProvider;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.ssl.DefaultClientTlsStrategy;
import org.apache.hc.client5.http.ssl.NoopHostnameVerifier;
import org.apache.hc.client5.http.ssl.TrustAllStrategy;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.http.HttpRequestInterceptor;
import org.apache.hc.core5.http.HttpResponseInterceptor;
import org.apache.hc.core5.http.io.SocketConfig;
import org.apache.hc.core5.ssl.SSLContexts;

import javax.annotation.concurrent.NotThreadSafe;
import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * DefaultApacheHttpClientBuilder
 *
 * @author altusea
 */
@Slf4j
@Data
@NotThreadSafe
public class DefaultApacheHttpClientBuilder implements ApacheHttpClientBuilder {

  private final AtomicBoolean prepared = new AtomicBoolean(false);

  /**
   * 获取链接的超时时间设置
   * <p>
   * 设置为零时不超时,一直等待.
   * 设置为负数是使用系统默认设置(非3000ms的默认值,而是httpClient的默认设置).
   * </p>
   */
  private int connectionRequestTimeout = 3000;

  /**
   * 建立链接的超时时间,默认为5000ms.由于是在链接池获取链接,此设置应该并不起什么作用
   * <p>
   * 设置为零时不超时,一直等待.
   * 设置为负数是使用系统默认设置(非上述的5000ms的默认值,而是httpclient的默认设置).
   * </p>
   */
  private int connectionTimeout = 5000;
  /**
   * 默认NIO的socket超时设置,默认5000ms.
   */
  private int soTimeout = 5000;
  /**
   * 空闲链接的超时时间,默认60000ms.
   * <p>
   * 超时的链接将在下一次空闲链接检查是被销毁
   * </p>
   */
  private int idleConnTimeout = 60000;
  /**
   * 检查空间链接的间隔周期,默认60000ms.
   */
  private int checkWaitTime = 60000;
  /**
   * 每路的最大链接数,默认10
   */
  private int maxConnPerHost = 10;
  /**
   * 最大总连接数,默认50
   */
  private int maxTotalConn = 50;
  /**
   * 自定义httpclient的User Agent
   */
  private String userAgent;

  /**
   * 自定义请求拦截器
   */
  private List<HttpRequestInterceptor> requestInterceptors = new ArrayList<>();

  /**
   * 自定义响应拦截器
   */
  private List<HttpResponseInterceptor> responseInterceptors = new ArrayList<>();

  /**
   * 自定义重试策略
   */
  private HttpRequestRetryStrategy httpRequestRetryStrategy;

  /**
   * 自定义KeepAlive策略
   */
  private ConnectionKeepAliveStrategy connectionKeepAliveStrategy;

  private String httpProxyHost;
  private int httpProxyPort;
  private String httpProxyUsername;
  private char[] httpProxyPassword;
  /**
   * 持有client对象,仅初始化一次,避免多service实例的时候造成重复初始化的问题
   */
  private CloseableHttpClient closeableHttpClient;

  private DefaultApacheHttpClientBuilder() {
  }

  public static DefaultApacheHttpClientBuilder get() {
    return SingletonHolder.INSTANCE;
  }

  @Override
  public ApacheHttpClientBuilder httpProxyHost(String httpProxyHost) {
    this.httpProxyHost = httpProxyHost;
    return this;
  }

  @Override
  public ApacheHttpClientBuilder httpProxyPort(int httpProxyPort) {
    this.httpProxyPort = httpProxyPort;
    return this;
  }

  @Override
  public ApacheHttpClientBuilder httpProxyUsername(String httpProxyUsername) {
    this.httpProxyUsername = httpProxyUsername;
    return this;
  }

  @Override
  public ApacheHttpClientBuilder httpProxyPassword(char[] httpProxyPassword) {
    this.httpProxyPassword = httpProxyPassword;
    return this;
  }

  @Override
  public ApacheHttpClientBuilder httpRequestRetryStrategy(HttpRequestRetryStrategy httpRequestRetryStrategy) {
    this.httpRequestRetryStrategy = httpRequestRetryStrategy;
    return this;
  }

  @Override
  public ApacheHttpClientBuilder keepAliveStrategy(ConnectionKeepAliveStrategy keepAliveStrategy) {
    this.connectionKeepAliveStrategy = keepAliveStrategy;
    return this;
  }

  private synchronized void prepare() {
    if (prepared.get()) {
      return;
    }

    SSLContext sslcontext;
    try {
      sslcontext = SSLContexts.custom()
        .loadTrustMaterial(TrustAllStrategy.INSTANCE) // 忽略对服务器端证书的校验
        .build();
    } catch (NoSuchAlgorithmException | KeyManagementException | KeyStoreException e) {
      log.error("构建 SSLContext 时发生异常！", e);
      throw new RuntimeException(e);
    }

    PoolingHttpClientConnectionManager connManager = PoolingHttpClientConnectionManagerBuilder.create()
      .setTlsSocketStrategy(new DefaultClientTlsStrategy(sslcontext, NoopHostnameVerifier.INSTANCE))
      .setMaxConnTotal(this.maxTotalConn)
      .setMaxConnPerRoute(this.maxConnPerHost)
      .setDefaultSocketConfig(SocketConfig.custom()
        .setSoTimeout(this.soTimeout, TimeUnit.MILLISECONDS)
        .build())
      .setDefaultConnectionConfig(ConnectionConfig.custom()
        .setConnectTimeout(this.connectionTimeout, TimeUnit.MILLISECONDS)
        .build())
      .build();

    HttpClientBuilder httpClientBuilder = HttpClients.custom()
      .setConnectionManager(connManager)
      .setConnectionManagerShared(true)
      .setDefaultRequestConfig(RequestConfig.custom()
        .setConnectionRequestTimeout(this.connectionRequestTimeout, TimeUnit.MILLISECONDS)
        .build()
      );

    // 设置重试策略，没有则使用默认
    httpClientBuilder.setRetryStrategy(ObjectUtils.defaultIfNull(httpRequestRetryStrategy, NoopRetryStrategy.INSTANCE));

    // 设置KeepAliveStrategy，没有使用默认
    if (connectionKeepAliveStrategy != null) {
      httpClientBuilder.setKeepAliveStrategy(connectionKeepAliveStrategy);
    }

    if (StringUtils.isNotBlank(this.httpProxyHost) && StringUtils.isNotBlank(this.httpProxyUsername)) {
      // 使用代理服务器 需要用户认证的代理服务器
      BasicCredentialsProvider provider = new BasicCredentialsProvider();
      provider.setCredentials(new AuthScope(this.httpProxyHost, this.httpProxyPort),
        new UsernamePasswordCredentials(this.httpProxyUsername, this.httpProxyPassword));
      httpClientBuilder.setDefaultCredentialsProvider(provider);
      httpClientBuilder.setProxy(new HttpHost(this.httpProxyHost, this.httpProxyPort));
    }

    if (StringUtils.isNotBlank(this.userAgent)) {
      httpClientBuilder.setUserAgent(this.userAgent);
    }

    //添加自定义的请求拦截器
    requestInterceptors.forEach(httpClientBuilder::addRequestInterceptorFirst);

    //添加自定义的响应拦截器
    responseInterceptors.forEach(httpClientBuilder::addResponseInterceptorLast);

    this.closeableHttpClient = httpClientBuilder.build();
    prepared.set(true);
  }

  @Override
  public CloseableHttpClient build() {
    if (!prepared.get()) {
      prepare();
    }
    return this.closeableHttpClient;
  }

  /**
   * DefaultApacheHttpClientBuilder 改为单例模式,并持有唯一的CloseableHttpClient(仅首次调用创建)
   */
  private static class SingletonHolder {
    private static final DefaultApacheHttpClientBuilder INSTANCE = new DefaultApacheHttpClientBuilder();
  }
}
