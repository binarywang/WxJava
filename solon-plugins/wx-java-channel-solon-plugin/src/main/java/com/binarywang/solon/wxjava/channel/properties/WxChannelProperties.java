package com.binarywang.solon.wxjava.channel.properties;

import com.binarywang.solon.wxjava.channel.enums.HttpClientType;
import com.binarywang.solon.wxjava.channel.enums.StorageType;
import lombok.Data;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.annotation.Inject;

/**
 * 属性配置类
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@Configuration
@Inject("${" + WxChannelProperties.PREFIX +"}")
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
    private final RedisProperties redis = new RedisProperties();

    /**
     * http客户端类型
     */
    private HttpClientType httpClientType = HttpClientType.HttpClient;

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
