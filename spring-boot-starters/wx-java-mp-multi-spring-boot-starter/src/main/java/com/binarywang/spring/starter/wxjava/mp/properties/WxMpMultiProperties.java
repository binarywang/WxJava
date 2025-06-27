package com.binarywang.spring.starter.wxjava.mp.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yl
 * created on 2024/1/23
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(WxMpMultiProperties.PREFIX)
public class WxMpMultiProperties implements Serializable {
  private static final long serialVersionUID = -5358245184407791011L;
  public static final String PREFIX = "wx.mp";

  private Map<String, WxMpSingleProperties> apps = new HashMap<>();

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
    private String keyPrefix = "wx:mp:multi";

    /**
     * redis连接配置.
     */
    @NestedConfigurationProperty
    private final WxMpMultiRedisProperties redis = new WxMpMultiRedisProperties();

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
     *   {@link me.chanjar.weixin.mp.api.WxMpService#setMaxRetryTimes(int)}
     *   {@link me.chanjar.weixin.mp.api.impl.BaseWxMpServiceImpl#setMaxRetryTimes(int)}
     * </pre>
     */
    private int maxRetryTimes = 5;

    /**
     * http 请求重试间隔
     * <pre>
     *   {@link me.chanjar.weixin.mp.api.WxMpService#setRetrySleepMillis(int)}
     *   {@link me.chanjar.weixin.mp.api.impl.BaseWxMpServiceImpl#setRetrySleepMillis(int)}
     * </pre>
     */
    private int retrySleepMillis = 1000;
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
     * HttpComponents
     */
    HTTP_COMPONENTS,
    /**
     * OkHttp
     */
    OK_HTTP,
    /**
     * JoddHttp
     */
    JODD_HTTP
  }
}
