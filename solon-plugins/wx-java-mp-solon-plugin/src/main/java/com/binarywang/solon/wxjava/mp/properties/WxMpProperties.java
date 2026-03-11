package com.binarywang.solon.wxjava.mp.properties;

import com.binarywang.solon.wxjava.mp.enums.HttpClientType;
import com.binarywang.solon.wxjava.mp.enums.StorageType;
import lombok.Data;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.annotation.Inject;

import java.io.Serializable;

import static com.binarywang.solon.wxjava.mp.enums.StorageType.Memory;
import static com.binarywang.solon.wxjava.mp.properties.WxMpProperties.PREFIX;

/**
 * 微信接入相关配置属性.
 *
 * @author someone
 */
@Data
@Configuration
@Inject("${" + PREFIX + "}")
public class WxMpProperties {
  public static final String PREFIX = "wx.mp";

  /**
   * 设置微信公众号的appid.
   */
  private String appId;

  /**
   * 设置微信公众号的app secret.
   */
  private String secret;

  /**
   * 设置微信公众号的token.
   */
  private String token;

  /**
   * 设置微信公众号的EncodingAESKey.
   */
  private String aesKey;

  /**
   * 是否使用稳定版 Access Token
   */
  private boolean useStableAccessToken = false;

  /**
   * 自定义host配置
   */
  private HostConfig hosts;

  /**
   * 存储策略
   */
  private final ConfigStorage configStorage = new ConfigStorage();

  @Data
  public static class ConfigStorage implements Serializable {
    private static final long serialVersionUID = 4815731027000065434L;

    /**
     * 存储类型.
     */
    private StorageType type = Memory;

    /**
     * 指定key前缀.
     */
    private String keyPrefix = "wx";

    /**
     * redis连接配置.
     */
    private final RedisProperties redis = new RedisProperties();

    /**
     * http客户端类型.
     */
    private HttpClientType httpClientType = HttpClientType.HttpComponents;

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

  }

}
