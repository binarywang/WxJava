package com.binarywang.solon.wxjava.miniapp.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.annotation.Inject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author monch created on 2024/9/6
 * @author noear
 */
@Data
@NoArgsConstructor
@Configuration
@Inject("${" + WxMaMultiProperties.PREFIX + "}")
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
}
