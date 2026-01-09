package com.binarywang.solon.wxjava.qidian.properties;

import com.binarywang.solon.wxjava.qidian.enums.HttpClientType;
import com.binarywang.solon.wxjava.qidian.enums.StorageType;
import lombok.Data;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.annotation.Inject;

import java.io.Serializable;

import static com.binarywang.solon.wxjava.qidian.enums.StorageType.Memory;
import static com.binarywang.solon.wxjava.qidian.properties.WxQidianProperties.PREFIX;

/**
 * 企点接入相关配置属性.
 *
 * @author someone
 */
@Data
@Configuration
@Inject("${" + PREFIX + "}")
public class WxQidianProperties {
  public static final String PREFIX = "wx.qidian";

  /**
   * 设置腾讯企点的appid.
   */
  private String appId;

  /**
   * 设置腾讯企点的app secret.
   */
  private String secret;

  /**
   * 设置腾讯企点的token.
   */
  private String token;

  /**
   * 设置腾讯企点的EncodingAESKey.
   */
  private String aesKey;

  /**
   * 自定义host配置
   */
  private HostConfig hosts;

  /**
   * 存储策略
   */
  private ConfigStorage configStorage = new ConfigStorage();

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
    private RedisProperties redis = new RedisProperties();

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
