package com.binarywang.spring.starter.wxjava.open.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信开放平台多账号配置属性.
 *
 * @author Binary Wang
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(WxOpenMultiProperties.PREFIX)
public class WxOpenMultiProperties implements Serializable {
  private static final long serialVersionUID = -5358245184407791011L;
  public static final String PREFIX = "wx.open";

  private Map<String, WxOpenSingleProperties> apps = new HashMap<>();

  /**
   * 存储策略
   */
  private final ConfigStorage configStorage = new ConfigStorage();

  @Data
  @NoArgsConstructor
  public static class ConfigStorage implements Serializable {
    private static final long serialVersionUID = 4815731027000065434L;

    /**
     * 存储类型.
     */
    private StorageType type = StorageType.memory;

    /**
     * 指定key前缀.
     */
    private String keyPrefix = "wx:open:multi";

    /**
     * redis连接配置.
     */
    @NestedConfigurationProperty
    private final WxOpenMultiRedisProperties redis = new WxOpenMultiRedisProperties();

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
     *   {@link me.chanjar.weixin.mp.api.impl.BaseWxMpServiceImpl#setMaxRetryTimes(int)}
     *   {@link cn.binarywang.wx.miniapp.api.impl.BaseWxMaServiceImpl#setMaxRetryTimes(int)}
     * </pre>
     */
    private int maxRetryTimes = 5;

    /**
     * http 请求重试间隔
     * <pre>
     *   {@link me.chanjar.weixin.mp.api.impl.BaseWxMpServiceImpl#setRetrySleepMillis(int)}
     *   {@link cn.binarywang.wx.miniapp.api.impl.BaseWxMaServiceImpl#setRetrySleepMillis(int)}
     * </pre>
     */
    private int retrySleepMillis = 1000;

    /**
     * 连接超时时间，单位毫秒
     */
    private int connectionTimeout = 5000;

    /**
     * 读数据超时时间，即socketTimeout，单位毫秒
     */
    private int soTimeout = 5000;

    /**
     * 从连接池获取链接的超时时间，单位毫秒
     */
    private int connectionRequestTimeout = 5000;
  }

  public enum StorageType {
    /**
     * 内存
     */
    memory,
    /**
     * jedis
     */
    jedis,
    /**
     * redisson
     */
    redisson,
    /**
     * redisTemplate
     */
    redistemplate
  }

}
