package com.binarywang.spring.starter.wxjava.channel.properties;

import com.binarywang.spring.starter.wxjava.channel.enums.HttpClientType;
import com.binarywang.spring.starter.wxjava.channel.enums.StorageType;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * 属性配置类
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@ConfigurationProperties(prefix = WxChannelProperties.PREFIX)
public class WxChannelProperties {
  public static final String PREFIX = "wx.channel";

  /**
   * 设置视频号小店的appid
   */
  private String appid;

  /**
   * 设置视频号小店的Secret
   */
  private String secret;

  /**
   * 设置视频号小店消息服务器配置的token.
   */
  private String token;

  /**
   * 设置视频号小店消息服务器配置的EncodingAESKey
   */
  private String aesKey;

  /**
   * 消息格式，XML或者JSON
   */
  private String msgDataFormat = "JSON";

  /**
   * 是否使用稳定版 Access Token
   */
  private boolean useStableAccessToken = false;

  /**
   * 自定义API主机地址，用于替换默认的 https://api.weixin.qq.com
   * 例如：http://proxy.company.com:8080
   */
  private String apiHostUrl;

  /**
   * 自定义获取AccessToken地址，用于向自定义统一服务获取AccessToken
   * 例如：http://proxy.company.com:8080/oauth/token
   */
  private String accessTokenUrl;

  /**
   * 存储策略
   */
  private final ConfigStorage configStorage = new ConfigStorage();

  @Data
  public static class ConfigStorage {

    /**
     * 存储类型
     */
    private StorageType type = StorageType.Memory;

    /**
     * 指定key前缀
     */
    private String keyPrefix = "wh";

    /**
     * redis连接配置
     */
    @NestedConfigurationProperty
    private final RedisProperties redis = new RedisProperties();

    /**
     * http客户端类型
     */
    private HttpClientType httpClientType = HttpClientType.HttpComponents;

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
     * http 请求重试间隔
     * <pre>
     *   {@link me.chanjar.weixin.channel.api.BaseWxChannelService#setRetrySleepMillis(int)}
     * </pre>
     */
    private int retrySleepMillis = 1000;
    /**
     * http 请求最大重试次数
     * <pre>
     *   {@link me.chanjar.weixin.channel.api.BaseWxChannelService#setMaxRetryTimes(int)}
     * </pre>
     */
    private int maxRetryTimes = 5;
  }

}
