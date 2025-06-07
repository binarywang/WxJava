package com.binarywang.solon.wxjava.cp_multi.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.annotation.Inject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 企业微信多企业接入相关配置属性
 *
 * @author yl
 * created on 2023/10/16
 */
@Data
@NoArgsConstructor
@Configuration
@Inject("${" + WxCpMultiProperties.PREFIX + "}")
public class WxCpMultiProperties implements Serializable {
  private static final long serialVersionUID = -1569510477055668503L;
  public static final String PREFIX = "wx.cp";

  private Map<String, WxCpSingleProperties> corps = new HashMap<>();

  /**
   * 配置存储策略，默认内存
   */
  private ConfigStorage configStorage = new ConfigStorage();

  @Data
  @NoArgsConstructor
  public static class ConfigStorage implements Serializable {
    private static final long serialVersionUID = 4815731027000065434L;
    /**
     * 存储类型
     */
    private StorageType type = StorageType.memory;

    /**
     * 指定key前缀
     */
    private String keyPrefix = "wx:cp";

    /**
     * redis连接配置
     */
    private WxCpMultiRedisProperties redis = new WxCpMultiRedisProperties();

    /**
     * http客户端类型.
     */
    private HttpClientType httpClientType = HttpClientType.HTTP_CLIENT;

    /**
     * http代理主机
     */
    private String httpProxyHost;

    /**
     * http代理端口
     */
    private Integer httpProxyPort;

    /**
     * http代理用户名
     */
    private String httpProxyUsername;

    /**
     * http代理密码
     */
    private String httpProxyPassword;

    /**
     * http 请求最大重试次数
     * <pre>
     *   {@link me.chanjar.weixin.cp.api.WxCpService#setMaxRetryTimes(int)}
     *   {@link me.chanjar.weixin.cp.api.impl.BaseWxCpServiceImpl#setMaxRetryTimes(int)}
     * </pre>
     */
    private int maxRetryTimes = 5;

    /**
     * http 请求重试间隔
     * <pre>
     *   {@link me.chanjar.weixin.cp.api.WxCpService#setRetrySleepMillis(int)}
     *   {@link me.chanjar.weixin.cp.api.impl.BaseWxCpServiceImpl#setRetrySleepMillis(int)}
     * </pre>
     */
    private int retrySleepMillis = 1000;
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
     * redistemplate
     */
    redistemplate
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
