package com.binarywang.solon.wxjava.channel.properties;

import com.binarywang.solon.wxjava.channel.enums.HttpClientType;
import com.binarywang.solon.wxjava.channel.enums.StorageType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.annotation.Inject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信多视频号接入相关配置属性
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 * @date 2024/9/13
 */
@Data
@NoArgsConstructor
@Configuration
@Inject("${" + WxChannelMultiProperties.PREFIX +"}")
public class WxChannelMultiProperties implements Serializable {
  private static final long serialVersionUID = - 8361973118805546037L;
  public static final String PREFIX = "wx.channel";

  private Map<String, WxChannelSingleProperties> apps = new HashMap<>();

  /**
   * 存储策略
   */
  private final ConfigStorage configStorage = new ConfigStorage();

  @Data
  @NoArgsConstructor
  public static class ConfigStorage implements Serializable {
    private static final long serialVersionUID = - 5152619132544179942L;

    /**
     * 存储类型.
     */
    private StorageType type = StorageType.MEMORY;

    /**
     * 指定key前缀.
     */
    private String keyPrefix = "wx:channel:multi";

    /**
     * redis连接配置.
     */
    private final WxChannelMultiRedisProperties redis = new WxChannelMultiRedisProperties();

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
     *
     * <p>{@link me.chanjar.weixin.channel.api.WxChannelService#setMaxRetryTimes(int)}</p>
     * <p>{@link me.chanjar.weixin.channel.api.impl.BaseWxChannelServiceImpl#setMaxRetryTimes(int)}</p>
     */
    private int maxRetryTimes = 5;

    /**
     * http 请求重试间隔
     *
     * <p>{@link me.chanjar.weixin.channel.api.WxChannelService#setRetrySleepMillis(int)}</p>
     * <p>{@link me.chanjar.weixin.channel.api.impl.BaseWxChannelServiceImpl#setRetrySleepMillis(int)}</p>
     */
    private int retrySleepMillis = 1000;
  }
}
