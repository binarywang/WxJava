package com.binarywang.spring.starter.wxjava.miniapp.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author monch
 * created on 2024/9/6
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(WxMaMultiProperties.PREFIX)
public class WxMaMultiProperties implements Serializable {
  private static final long serialVersionUID = -5358245184407791011L;
  public static final String PREFIX = "wx.ma";

  private Map<String, WxMaSingleProperties> apps = new HashMap<>();

  /**
   * 自定义host配置
   */
  private HostConfig hosts;

  /**
   * 存储策略
   */
  private final ConfigStorage configStorage = new ConfigStorage();

  @Data
  @NoArgsConstructor
  public static class HostConfig implements Serializable {
    private static final long serialVersionUID = -4172767630740346001L;

    /**
     * 对应于：https://api.weixin.qq.com
     */
    private String apiHost;

    /**
     * 对应于：https://open.weixin.qq.com
     */
    private String openHost;

    /**
     * 对应于：https://mp.weixin.qq.com
     */
    private String mpHost;
  }

  @Data
  @NoArgsConstructor
  public static class ConfigStorage implements Serializable {
    private static final long serialVersionUID = 4815731027000065434L;

    /**
     * 存储类型.
     */
    private StorageType type = StorageType.MEMORY;

    /**
     * 指定key前缀.
     */
    private String keyPrefix = "wx:ma:multi";

    /**
     * redis连接配置.
     */
    @NestedConfigurationProperty
    private final WxMaMultiRedisProperties redis = new WxMaMultiRedisProperties();

    /**
     * http客户端类型.
     */
    private HttpClientType httpClientType = HttpClientType.HTTP_CLIENT;

    /**
     * http代理主机.
     */
    private String httpProxyHost;

    /**
     * http代理端口.
     */
    private Integer httpProxyPort;

    /**
     * http代理用户名.
     */
    private String httpProxyUsername;

    /**
     * http代理密码.
     */
    private String httpProxyPassword;

    /**
     * http 请求最大重试次数
     * <pre>
     *   {@link cn.binarywang.wx.miniapp.api.WxMaService#setMaxRetryTimes(int)}
     *   {@link cn.binarywang.wx.miniapp.api.impl.BaseWxMaServiceImpl#setMaxRetryTimes(int)}
     * </pre>
     */
    private int maxRetryTimes = 5;

    /**
     * http 请求重试间隔
     * <pre>
     *   {@link cn.binarywang.wx.miniapp.api.WxMaService#setRetrySleepMillis(int)}
     *   {@link cn.binarywang.wx.miniapp.api.impl.BaseWxMaServiceImpl#setRetrySleepMillis(int)}
     * </pre>
     */
    private int retrySleepMillis = 1000;

    /**
     * 多租户实现模式.
     * <ul>
     * <li>ISOLATED: 为每个租户创建独立的 WxMaService 实例（默认）</li>
     * <li>SHARED: 使用单个 WxMaService 实例管理所有租户配置，共享 HTTP 客户端</li>
     * </ul>
     */
    private MultiTenantMode multiTenantMode = MultiTenantMode.ISOLATED;
  }

  public enum StorageType {
    /**
     * 内存
     */
    MEMORY,
    /**
     * jedis
     */
    JEDIS,
    /**
     * redisson
     */
    REDISSON,
    /**
     * redisTemplate
     */
    REDIS_TEMPLATE
  }

  public enum HttpClientType {
    /**
     * HttpClient
     */
    HTTP_CLIENT,
    /**
     * OkHttp
     */
    OK_HTTP,
    /**
     * JoddHttp
     */
    JODD_HTTP
  }

  public enum MultiTenantMode {
    /**
     * 隔离模式：为每个租户创建独立的 WxMaService 实例.
     * 优点：线程安全，不依赖 ThreadLocal
     * 缺点：每个租户创建独立的 HTTP 客户端，资源占用较多
     */
    ISOLATED,
    /**
     * 共享模式：使用单个 WxMaService 实例管理所有租户配置.
     * 优点：共享 HTTP 客户端，节省资源
     * 缺点：依赖 ThreadLocal 切换配置，异步场景需注意
     */
    SHARED
  }
}
