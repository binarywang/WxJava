package com.github.binarywang.wxpay.config;

import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.util.HttpProxyUtils;
import com.github.binarywang.wxpay.util.ResourcesUtils;
import com.github.binarywang.wxpay.v3.WxPayV3HttpClientBuilder;
import com.github.binarywang.wxpay.v3.auth.Verifier;
import com.github.binarywang.wxpay.v3.auth.WxPayValidator;
import com.github.binarywang.wxpay.v3.util.PemUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.SneakyThrows;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.SSLContext;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.Optional;

/**
 * 微信支付配置
 *
 * @author Binary Wang (<a href="https://github.com/binarywang">...</a>)
 */
@Data
@Slf4j
@ToString(exclude = "verifier")
@EqualsAndHashCode(exclude = "verifier")
public class WxPayConfig {
  private static final String DEFAULT_PAY_BASE_URL = "https://api.mch.weixin.qq.com";
  private static final String PROBLEM_MSG = "证书文件【%s】有问题，请核实！";
  private static final String NOT_FOUND_MSG = "证书文件【%s】不存在，请核实！";

  /**
   * 微信支付接口请求地址域名部分.
   */
  private String apiHostUrl = DEFAULT_PAY_BASE_URL;

  /**
   * http请求连接超时时间.
   */
  private int httpConnectionTimeout = 5000;

  /**
   * http请求数据读取等待时间.
   */
  private int httpTimeout = 10000;

  /**
   * 公众号appid.
   */
  private String appId;
  /**
   * 服务商模式下的子商户公众账号ID.
   */
  private String subAppId;
  /**
   * 商户号.
   */
  private String mchId;
  /**
   * 商户密钥.
   */
  private String mchKey;
  /**
   * 企业支付密钥.
   */
  private String entPayKey;
  /**
   * 服务商模式下的子商户号.
   */
  private String subMchId;
  /**
   * 微信支付异步回掉地址，通知url必须为直接可访问的url，不能携带参数.
   */
  private String notifyUrl;
  /**
   * 交易类型.
   * <pre>
   * JSAPI--公众号支付
   * NATIVE--原生扫码支付
   * APP--app支付
   * </pre>
   */
  private String tradeType;
  /**
   * 签名方式.
   * 有两种HMAC_SHA256 和MD5
   *
   * @see com.github.binarywang.wxpay.constant.WxPayConstants.SignType
   */
  private String signType;
  private SSLContext sslContext;
  /**
   * p12证书base64编码
   */
  private String keyString;
  /**
   * p12证书文件的绝对路径或者以classpath:开头的类路径.
   */
  private String keyPath;

  /**
   * apiclient_key.pem证书base64编码
   */
  private String privateKeyString;
  /**
   * apiclient_key.pem证书文件的绝对路径或者以classpath:开头的类路径.
   */
  private String privateKeyPath;

  /**
   * apiclient_cert.pem证书base64编码
   */
  private String privateCertString;
  /**
   * apiclient_cert.pem证书文件的绝对路径或者以classpath:开头的类路径.
   */
  private String privateCertPath;

  /**
   * apiclient_key.pem证书文件内容的字节数组.
   */
  private byte[] privateKeyContent;

  /**
   * apiclient_cert.pem证书文件内容的字节数组.
   */
  private byte[] privateCertContent;

  /**
   * 公钥ID
   */
  private String publicKeyId;

  /**
   * pub_key.pem证书base64编码
   */
  private String publicKeyString;

  /**
   * pub_key.pem证书文件的绝对路径或者以classpath:开头的类路径.
   */
  private String publicKeyPath;

  /**
   * pub_key.pem证书文件内容的字节数组.
   */
  private byte[] publicKeyContent;
  /**
   * apiV3 秘钥值.
   */
  private String apiV3Key;

  /**
   * apiV3 证书序列号值
   */
  private String certSerialNo;
  /**
   * 微信支付分serviceId
   */
  private String serviceId;

  /**
   * 微信支付分回调地址
   */
  private String payScoreNotifyUrl;


  /**
   * 微信支付分授权回调地址
   */
  private String payScorePermissionNotifyUrl;


  private CloseableHttpClient apiV3HttpClient;

  /**
   * 用于普通支付接口的可复用HttpClient，使用连接池
   */
  private CloseableHttpClient httpClient;

  /**
   * 用于需要SSL证书的支付接口的可复用HttpClient，使用连接池
   */
  private CloseableHttpClient sslHttpClient;

  /**
   * 支持扩展httpClientBuilder
   */
  private HttpClientBuilderCustomizer httpClientBuilderCustomizer;
  private HttpClientBuilderCustomizer apiV3HttpClientBuilderCustomizer;

  /**
   * HTTP连接池最大连接数，默认20
   */
  private int maxConnTotal = 20;

  /**
   * HTTP连接池每个路由的最大连接数，默认10
   */
  private int maxConnPerRoute = 10;
  /**
   * 私钥信息
   */
  private PrivateKey privateKey;

  /**
   * 证书自动更新时间差(分钟)，默认一分钟
   */
  private int certAutoUpdateTime = 60;

  /**
   * p12证书文件内容的字节数组.
   */
  private byte[] keyContent;
  /**
   * 微信支付是否使用仿真测试环境.
   * 默认不使用
   */
  private boolean useSandboxEnv = false;

  /**
   * 是否将接口请求日志信息保存到threadLocal中.
   * 默认不保存
   */
  private boolean ifSaveApiData = false;

  private String httpProxyHost;
  private Integer httpProxyPort;
  private String httpProxyUsername;
  private String httpProxyPassword;

  /**
   * v3接口下证书检验对象，通过改对象可以获取到X509Certificate，进一步对敏感信息加密
   * <a href="https://wechatpay-api.gitbook.io/wechatpay-api-v3/qian-ming-zhi-nan-1/min-gan-xin-xi-jia-mi">文档</a>
   */
  private Verifier verifier;

  /**
   * 是否将全部v3接口的请求都添加Wechatpay-Serial请求头，默认不添加
   */
  private boolean strictlyNeedWechatPaySerial = false;

  /**
   * 是否完全使用公钥模式(用以微信从平台证书到公钥的灰度切换)，默认不使用
   */
  private boolean fullPublicKeyModel = false;

  /**
   * 返回所设置的微信支付接口请求地址域名.
   *
   * @return 微信支付接口请求地址域名
   */
  public String getApiHostUrl() {
    if (StringUtils.isEmpty(this.apiHostUrl)) {
      return DEFAULT_PAY_BASE_URL;
    }

    return this.apiHostUrl;
  }

  @SneakyThrows
  public Verifier getVerifier() {
    if (verifier == null) {
      //当改对象为null时，初始化api v3的请求头
      initApiV3HttpClient();
    }
    return verifier;
  }

  /**
   * 初始化ssl.
   *
   * @return the ssl context
   * @throws WxPayException the wx pay exception
   */
  public SSLContext initSSLContext() throws WxPayException {
    if (StringUtils.isBlank(this.getMchId())) {
      throw new WxPayException("请确保商户号mchId已设置");
    }

    try (InputStream inputStream = this.loadConfigInputStream(this.keyString, this.getKeyPath(),
      this.keyContent, "p12证书")) {
      KeyStore keystore = KeyStore.getInstance("PKCS12");
      char[] partnerId2charArray = this.getMchId().toCharArray();
      keystore.load(inputStream, partnerId2charArray);
      this.sslContext = SSLContexts.custom().loadKeyMaterial(keystore, partnerId2charArray).build();
      return this.sslContext;
    } catch (Exception e) {
      throw new WxPayException("证书文件有问题，请核实！", e);
    }

  }

  /**
   * 初始化api v3请求头 自动签名验签
   * 方法参照 <a href="https://github.com/wechatpay-apiv3/wechatpay-apache-httpclient">微信支付官方api项目</a>
   *
   * @return org.apache.http.impl.client.CloseableHttpClient
   * @author doger.wang
   **/
  public CloseableHttpClient initApiV3HttpClient() throws WxPayException {
    if (StringUtils.isBlank(this.getApiV3Key())) {
      throw new WxPayException("请确保apiV3Key值已设置");
    }
    try {
      PrivateKey merchantPrivateKey = null;
      PublicKey publicKey = null;

      // 不使用完全公钥模式时，同时兼容平台证书和公钥
      X509Certificate certificate = null;
      // 尝试从p12证书中加载私钥和证书
      Object[] objects = this.p12ToPem();
      if (objects != null) {
        merchantPrivateKey = (PrivateKey) objects[0];
        certificate = (X509Certificate) objects[1];
        this.certSerialNo = certificate.getSerialNumber().toString(16).toUpperCase();
      }
      if (certificate == null && StringUtils.isBlank(this.getCertSerialNo()) && (StringUtils.isNotBlank(this.getPrivateCertPath()) || StringUtils.isNotBlank(this.getPrivateCertString())) || this.getPrivateCertContent() != null) {
        try (InputStream certInputStream = this.loadConfigInputStream(this.getPrivateCertString(), this.getPrivateCertPath(),
          this.privateCertContent, "privateCertPath")) {
          certificate = PemUtils.loadCertificate(certInputStream);
        }
        this.certSerialNo = certificate.getSerialNumber().toString(16).toUpperCase();
      }

      if (StringUtils.isNotBlank(this.getPublicKeyString()) || StringUtils.isNotBlank(this.getPublicKeyPath()) || this.publicKeyContent != null) {
        if (StringUtils.isBlank(this.getPublicKeyId())) {
          throw new WxPayException("请确保和publicKeyId配套使用");
        }
        try (InputStream pubInputStream =
               this.loadConfigInputStream(this.getPublicKeyString(), this.getPublicKeyPath(),
                 this.publicKeyContent, "publicKeyPath")) {
          publicKey = PemUtils.loadPublicKey(pubInputStream);
        }
      }

      // 加载api私钥
      if (merchantPrivateKey == null && (StringUtils.isNotBlank(this.getPrivateKeyPath()) || StringUtils.isNotBlank(this.getPrivateKeyString()) || null != this.privateKeyContent)) {
        try (InputStream keyInputStream = this.loadConfigInputStream(this.getPrivateKeyString(), this.getPrivateKeyPath(),
          this.privateKeyContent, "privateKeyPath")) {
          merchantPrivateKey = PemUtils.loadPrivateKey(keyInputStream);
        }
      }

      //构造Http Proxy正向代理
      WxPayHttpProxy wxPayHttpProxy = getWxPayHttpProxy();

      // 构造证书验签器
      Verifier certificatesVerifier;
      if (this.fullPublicKeyModel) {
        // 使用完全公钥模式时，只加载公钥相关配置，避免下载平台证书使灰度切换无法达到100%覆盖
        certificatesVerifier = VerifierBuilder.buildPublicCertVerifier(this.publicKeyId, publicKey);
      } else {
        certificatesVerifier = VerifierBuilder.build(
          this.getCertSerialNo(), this.getMchId(), this.getApiV3Key(), merchantPrivateKey, wxPayHttpProxy,
          this.getCertAutoUpdateTime(), this.getApiHostUrl(), this.getPublicKeyId(), publicKey);
      }

      WxPayV3HttpClientBuilder wxPayV3HttpClientBuilder = WxPayV3HttpClientBuilder.create()
        .withMerchant(mchId, certSerialNo, merchantPrivateKey)
        .withValidator(new WxPayValidator(certificatesVerifier));
      //初始化V3接口正向代理设置
      HttpProxyUtils.initHttpProxy(wxPayV3HttpClientBuilder, wxPayHttpProxy);

      // 提供自定义wxPayV3HttpClientBuilder的能力
      Optional.ofNullable(apiV3HttpClientBuilderCustomizer).ifPresent(e -> {
        e.customize(wxPayV3HttpClientBuilder);
      });
      CloseableHttpClient httpClient = wxPayV3HttpClientBuilder.build();

      this.apiV3HttpClient = httpClient;
      this.verifier = certificatesVerifier;
      this.privateKey = merchantPrivateKey;

      return httpClient;
    } catch (WxPayException e) {
      throw e;
    } catch (Exception e) {
      throw new WxPayException("v3请求构造异常！", e);
    }
  }

  /**
   * 初始化一个WxPayHttpProxy对象
   *
   * @return 返回封装的WxPayHttpProxy对象。如未指定代理主机和端口，则默认返回null
   */
  private WxPayHttpProxy getWxPayHttpProxy() {
    if (StringUtils.isNotBlank(this.getHttpProxyHost()) && this.getHttpProxyPort() > 0) {
      return new WxPayHttpProxy(getHttpProxyHost(), getHttpProxyPort(), getHttpProxyUsername(), getHttpProxyPassword());
    }
    return null;
  }

  /**
   * 从指定参数加载输入流
   *
   * @param configString  证书内容进行Base64加密后的字符串
   * @param configPath    证书路径
   * @param configContent 证书内容的字节数组
   * @param certName      证书的标识
   * @return 输入流
   * @throws WxPayException 异常
   */
  private InputStream loadConfigInputStream(String configString, String configPath, byte[] configContent,
                                            String certName) throws WxPayException {
    if (configContent != null) {
      return new ByteArrayInputStream(configContent);
    }

    if (StringUtils.isNotEmpty(configString)) {
      // 判断是否为PEM格式的字符串（包含-----BEGIN和-----END标记）
      if (configString.contains("-----BEGIN") && configString.contains("-----END")) {
        // PEM格式直接转为字节流，让PemUtils处理
        configContent = configString.getBytes(StandardCharsets.UTF_8);
      } else {
        // 纯Base64格式，需要先解码
        configContent = Base64.getDecoder().decode(configString);
      }
      return new ByteArrayInputStream(configContent);
    }

    if (StringUtils.isBlank(configPath)) {
      throw new WxPayException(String.format("请确保【%s】的文件地址【%s】存在", certName, configPath));
    }

    return this.loadConfigInputStream(configPath);
  }


  /**
   * 从配置路径 加载配置 信息（支持 classpath、本地路径、网络url）
   *
   * @param configPath 配置路径
   * @return .
   * @throws WxPayException .
   */
  private InputStream loadConfigInputStream(String configPath) throws WxPayException {
    String fileHasProblemMsg = String.format(PROBLEM_MSG, configPath);
    String fileNotFoundMsg = String.format(NOT_FOUND_MSG, configPath);

    final String prefix = "classpath:";
    InputStream inputStream;
    if (configPath.startsWith(prefix)) {
      String path = RegExUtils.removeFirst(configPath, prefix);
      if (!path.startsWith("/")) {
        path = "/" + path;
      }

      try {
        inputStream = ResourcesUtils.getResourceAsStream(path);
        if (inputStream == null) {
          throw new WxPayException(fileNotFoundMsg);
        }

        return inputStream;
      } catch (Exception e) {
        throw new WxPayException(fileNotFoundMsg, e);
      }
    }

    if (configPath.startsWith("http://") || configPath.startsWith("https://")) {
      try {
        inputStream = new URL(configPath).openStream();
        if (inputStream == null) {
          throw new WxPayException(fileNotFoundMsg);
        }
        return inputStream;
      } catch (IOException e) {
        throw new WxPayException(fileNotFoundMsg, e);
      }
    } else {
      try {
        File file = new File(configPath);
        if (!file.exists()) {
          throw new WxPayException(fileNotFoundMsg);
        }
        //使用Files.newInputStream打开公私钥文件，会存在无法释放句柄的问题
        //return Files.newInputStream(file.toPath());
        return new FileInputStream(file);
      } catch (IOException e) {
        throw new WxPayException(fileHasProblemMsg, e);
      }
    }
  }

  /**
   * 分解p12证书文件
   */
  private Object[] p12ToPem() {
    String key = getMchId();
    if (StringUtils.isBlank(key) ||
      (StringUtils.isBlank(this.getKeyPath()) && this.keyContent == null && StringUtils.isBlank(this.keyString))) {
      return null;
    }

    // 分解p12证书文件
    try (InputStream inputStream = this.loadConfigInputStream(this.keyString, this.getKeyPath(),
      this.keyContent, "p12证书")) {
      KeyStore keyStore = KeyStore.getInstance("PKCS12");
      keyStore.load(inputStream, key.toCharArray());

      String alias = keyStore.aliases().nextElement();
      PrivateKey privateKey = (PrivateKey) keyStore.getKey(alias, key.toCharArray());

      Certificate certificate = keyStore.getCertificate(alias);
      X509Certificate x509Certificate = (X509Certificate) certificate;
      return new Object[]{privateKey, x509Certificate};
    } catch (Exception e) {
      log.error("加载p12证书时发生异常", e);
    }

    return null;

  }

  /**
   * 初始化使用连接池的HttpClient
   *
   * @return CloseableHttpClient
   * @throws WxPayException 初始化异常
   */
  public CloseableHttpClient initHttpClient() throws WxPayException {
    if (this.httpClient != null) {
      return this.httpClient;
    }

    // 创建连接池管理器
    PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
    connectionManager.setMaxTotal(this.maxConnTotal);
    connectionManager.setDefaultMaxPerRoute(this.maxConnPerRoute);

    // 创建HttpClient构建器
    org.apache.http.impl.client.HttpClientBuilder httpClientBuilder = HttpClients.custom()
        .setConnectionManager(connectionManager);

    // 配置代理
    configureProxy(httpClientBuilder);

    // 提供自定义httpClientBuilder的能力
    Optional.ofNullable(httpClientBuilderCustomizer).ifPresent(e -> {
      e.customize(httpClientBuilder);
    });

    this.httpClient = httpClientBuilder.build();
    return this.httpClient;
  }

  /**
   * 初始化使用连接池且支持SSL的HttpClient
   *
   * @return CloseableHttpClient
   * @throws WxPayException 初始化异常
   */
  public CloseableHttpClient initSslHttpClient() throws WxPayException {
    if (this.sslHttpClient != null) {
      return this.sslHttpClient;
    }

    // 初始化SSL上下文
    SSLContext sslContext = this.getSslContext();
    if (null == sslContext) {
      sslContext = this.initSSLContext();
    }

    // 创建支持SSL的连接池管理器
    SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
      sslContext,
      new DefaultHostnameVerifier()
    );

    Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
      .<ConnectionSocketFactory>create()
      .register("https", sslsf)
      .register("http", PlainConnectionSocketFactory.getSocketFactory())
      .build();
    PoolingHttpClientConnectionManager connectionManager =
      new PoolingHttpClientConnectionManager(socketFactoryRegistry);

    // PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
    connectionManager.setMaxTotal(this.maxConnTotal);
    connectionManager.setDefaultMaxPerRoute(this.maxConnPerRoute);

    // 创建HttpClient构建器，配置SSL
    org.apache.http.impl.client.HttpClientBuilder httpClientBuilder = HttpClients.custom()
        .setConnectionManager(connectionManager)
        .setSSLSocketFactory(new SSLConnectionSocketFactory(sslContext, new DefaultHostnameVerifier()));

    // 配置代理
    configureProxy(httpClientBuilder);

    // 提供自定义httpClientBuilder的能力
    Optional.ofNullable(httpClientBuilderCustomizer).ifPresent(e -> {
      e.customize(httpClientBuilder);
    });

    this.sslHttpClient = httpClientBuilder.build();
    return this.sslHttpClient;
  }

  /**
   * 配置HTTP代理
   */
  private void configureProxy(org.apache.http.impl.client.HttpClientBuilder httpClientBuilder) {
    if (StringUtils.isNotBlank(this.getHttpProxyHost()) && this.getHttpProxyPort() > 0) {
      if (StringUtils.isEmpty(this.getHttpProxyUsername())) {
        this.setHttpProxyUsername("whatever");
      }

      // 使用代理服务器 需要用户认证的代理服务器
      CredentialsProvider provider = new BasicCredentialsProvider();
      provider.setCredentials(new AuthScope(this.getHttpProxyHost(), this.getHttpProxyPort()),
        new UsernamePasswordCredentials(this.getHttpProxyUsername(), this.getHttpProxyPassword()));
      httpClientBuilder.setDefaultCredentialsProvider(provider)
        .setProxy(new HttpHost(this.getHttpProxyHost(), this.getHttpProxyPort()));
    }
  }

  /**
   * 获取用于普通支付接口的HttpClient
   *
   * @return CloseableHttpClient
   */
  public CloseableHttpClient getHttpClient() {
    return httpClient;
  }

  /**
   * 获取用于SSL支付接口的HttpClient
   *
   * @return CloseableHttpClient
   */
  public CloseableHttpClient getSslHttpClient() {
    return sslHttpClient;
  }
}
