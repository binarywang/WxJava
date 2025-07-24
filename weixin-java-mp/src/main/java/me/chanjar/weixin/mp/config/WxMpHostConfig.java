package me.chanjar.weixin.mp.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 微信接口地址域名部分的自定义设置信息.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * created on  2019-06-09
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMpHostConfig implements Serializable {
  public static final String API_DEFAULT_HOST_URL = "https://api.weixin.qq.com";
  public static final String MP_DEFAULT_HOST_URL = "https://mp.weixin.qq.com";
  public static final String OPEN_DEFAULT_HOST_URL = "https://open.weixin.qq.com";
  private static final long serialVersionUID = 6998547464242356375L;


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

  /**
   * 是否使用HTTP协议而不是HTTPS，主要用于微信云托管等内网环境
   */
  private boolean useHttpOnly;

  public static String buildUrl(WxMpHostConfig hostConfig, String prefix, String path) {
    if (hostConfig == null) {
      return prefix + path;
    }

    String targetHost = null;
    if (hostConfig.getApiHost() != null && prefix.equals(API_DEFAULT_HOST_URL)) {
      targetHost = hostConfig.getApiHost();
    } else if (hostConfig.getMpHost() != null && prefix.equals(MP_DEFAULT_HOST_URL)) {
      targetHost = hostConfig.getMpHost();
    } else if (hostConfig.getOpenHost() != null && prefix.equals(OPEN_DEFAULT_HOST_URL)) {
      targetHost = hostConfig.getOpenHost();
    }

    if (targetHost != null) {
      return targetHost + path;
    }

    // 如果启用HTTP模式且没有自定义主机，则将默认的HTTPS替换为HTTP
    if (hostConfig.isUseHttpOnly()) {
      prefix = prefix.replace("https://", "http://");
    }

    return prefix + path;
  }
}
