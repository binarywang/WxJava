package me.chanjar.weixin.common.util.http.apache;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;

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
 * httpclient 连接管理器
 *
 * @author kakotor
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
  private HttpRequestRetryHandler httpRequestRetryHandler;

  /**
   * 自定义KeepAlive策略
   */
  private ConnectionKeepAliveStrategy connectionKeepAliveStrategy;

  private final HttpRequestRetryHandler defaultHttpRequestRetryHandler = (exception, executionCount, context) -> false;

  private SSLConnectionSocketFactory sslConnectionSocketFactory = SSLConnectionSocketFactory.getSocketFactory();
  private final PlainConnectionSocketFactory plainConnectionSocketFactory = PlainConnectionSocketFactory.getSocketFactory();
  private String httpProxyHost;
  private int httpProxyPort;
  private String httpProxyUsername;
  private String httpProxyPassword;
  /**
   * 闲置连接监控线程
   */
  private IdleConnectionMonitorThread idleConnectionMonitorThread;
  /**
   * 持有client对象,仅初始化一次,避免多service实例的时候造成重复初始化的问题
   */
  private CloseableHttpClient closeableHttpClient;

  private DefaultApacheHttpClientBuilder() {
  }

  public static DefaultApacheHttpClientBuilder get() {
    return DefaultApacheHttpClientBuilder.SingletonHolder.INSTANCE;
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
  public ApacheHttpClientBuilder httpProxyPassword(String httpProxyPassword) {
    this.httpProxyPassword = httpProxyPassword;
    return this;
  }

  @Override
  public ApacheHttpClientBuilder httpRequestRetryHandler(HttpRequestRetryHandler httpRequestRetryHandler) {
    this.httpRequestRetryHandler = httpRequestRetryHandler;
    return this;
  }

  @Override
  public ApacheHttpClientBuilder keepAliveStrategy(ConnectionKeepAliveStrategy keepAliveStrategy) {
    this.connectionKeepAliveStrategy = keepAliveStrategy;
    return this;
  }

  @Override
  public ApacheHttpClientBuilder sslConnectionSocketFactory(SSLConnectionSocketFactory sslConnectionSocketFactory) {
    this.sslConnectionSocketFactory = sslConnectionSocketFactory;
    return this;
  }

  public IdleConnectionMonitorThread getIdleConnectionMonitorThread() {
    return this.idleConnectionMonitorThread;
  }

  private synchronized void prepare() {
    if (prepared.get()) {
      return;
    }
    Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
      .register("http", this.plainConnectionSocketFactory)
      .register("https", this.sslConnectionSocketFactory)
      .build();

    PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);
    connectionManager.setMaxTotal(this.maxTotalConn);
    connectionManager.setDefaultMaxPerRoute(this.maxConnPerHost);
    connectionManager.setDefaultSocketConfig(
      SocketConfig.copy(SocketConfig.DEFAULT)
        .setSoTimeout(this.soTimeout)
        .build()
    );

    this.idleConnectionMonitorThread = new IdleConnectionMonitorThread(
      connectionManager, this.idleConnTimeout, this.checkWaitTime);
    this.idleConnectionMonitorThread.setDaemon(true);
    this.idleConnectionMonitorThread.start();

    HttpClientBuilder httpClientBuilder = HttpClients.custom()
      .setConnectionManager(connectionManager)
      .setConnectionManagerShared(true)
      .setSSLSocketFactory(this.buildSSLConnectionSocketFactory())
      .setDefaultRequestConfig(RequestConfig.custom()
        .setSocketTimeout(this.soTimeout)
        .setConnectTimeout(this.connectionTimeout)
        .setConnectionRequestTimeout(this.connectionRequestTimeout)
        .build()
      );

    // 设置重试策略，没有则使用默认
    httpRequestRetryHandler = httpRequestRetryHandler == null ? defaultHttpRequestRetryHandler : httpRequestRetryHandler;
    httpClientBuilder.setRetryHandler(httpRequestRetryHandler);

    // 设置KeepAliveStrategy，没有使用默认
    if (connectionKeepAliveStrategy != null) {
      httpClientBuilder.setKeepAliveStrategy(connectionKeepAliveStrategy);
    }

    if (StringUtils.isNotBlank(this.httpProxyHost) && StringUtils.isNotBlank(this.httpProxyUsername)) {
      // 使用代理服务器 需要用户认证的代理服务器
      CredentialsProvider provider = new BasicCredentialsProvider();
      provider.setCredentials(new AuthScope(this.httpProxyHost, this.httpProxyPort),
        new UsernamePasswordCredentials(this.httpProxyUsername, this.httpProxyPassword));
      httpClientBuilder.setDefaultCredentialsProvider(provider);
      httpClientBuilder.setProxy(new HttpHost(this.httpProxyHost, this.httpProxyPort));
    }

    if (StringUtils.isNotBlank(this.userAgent)) {
      httpClientBuilder.setUserAgent(this.userAgent);
    }

    //添加自定义的请求拦截器
    requestInterceptors.forEach(httpClientBuilder::addInterceptorFirst);

    //添加自定义的响应拦截器
    responseInterceptors.forEach(httpClientBuilder::addInterceptorLast);

    this.closeableHttpClient = httpClientBuilder.build();
    prepared.set(true);
  }

  private SSLConnectionSocketFactory buildSSLConnectionSocketFactory() {
    try {
      SSLContext sslcontext = SSLContexts.custom()
        //忽略掉对服务器端证书的校验
        .loadTrustMaterial((TrustStrategy) (chain, authType) -> true).build();

      return new SSLConnectionSocketFactory(
        sslcontext,
        new String[]{"TLSv1"},
        null,
        SSLConnectionSocketFactory.getDefaultHostnameVerifier());
    } catch (NoSuchAlgorithmException | KeyManagementException | KeyStoreException e) {
      log.error("构建SSL连接工厂时发生异常！", e);
    }

    return null;
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

  public static class IdleConnectionMonitorThread extends Thread {
    private final HttpClientConnectionManager connMgr;
    private final int idleConnTimeout;
    private final int checkWaitTime;
    private volatile boolean shutdown;

    public IdleConnectionMonitorThread(HttpClientConnectionManager connMgr, int idleConnTimeout, int checkWaitTime) {
      super("IdleConnectionMonitorThread");
      this.connMgr = connMgr;
      this.idleConnTimeout = idleConnTimeout;
      this.checkWaitTime = checkWaitTime;
    }

    @Override
    public void run() {
      try {
        while (!this.shutdown) {
          synchronized (this) {
            wait(this.checkWaitTime);
            this.connMgr.closeExpiredConnections();
            this.connMgr.closeIdleConnections(this.idleConnTimeout,
              TimeUnit.MILLISECONDS);
          }
        }
      } catch (InterruptedException ignore) {
      }
    }

    public void trigger() {
      synchronized (this) {
        notifyAll();
      }
    }

    public void shutdown() {
      this.shutdown = true;
      synchronized (this) {
        notifyAll();
      }
    }
  }
}
