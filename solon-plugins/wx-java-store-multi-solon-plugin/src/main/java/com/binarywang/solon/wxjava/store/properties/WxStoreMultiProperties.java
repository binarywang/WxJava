package com.binarywang.solon.wxjava.store.properties;

import com.binarywang.solon.wxjava.store.enums.HttpClientType;
import com.binarywang.solon.wxjava.store.enums.StorageType;
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
@Inject("${" + WxStoreMultiProperties.PREFIX +"}")
public class WxStoreMultiProperties implements Serializable {
  private static final long serialVersionUID = - 8361973118805546037L;
  public static final String PREFIX = "wx.store";

  private Map<String, WxStoreSingleProperties> apps = new HashMap<>();

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
    private String keyPrefix = "wx:store:multi";

    /**
     * redis连接配置.
     */
    private final WxStoreMultiRedisProperties redis = new WxStoreMultiRedisProperties();

    /**
     * http客户端类型.
     */
    private HttpClientType httpClientType = HttpClientType.HTTP_COMPONENTS;

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
     * <p>{@link com.binarywang.wxjava.store.api.WxStoreService#setMaxRetryTimes(int)}</p>
     * <p>{@link com.binarywang.wxjava.store.api.impl.BaseWxStoreServiceImpl#setMaxRetryTimes(int)}</p>
     */
    private int maxRetryTimes = 5;

    /**
     * http 请求重试间隔
     *
     * <p>{@link com.binarywang.wxjava.store.api.WxStoreService#setRetrySleepMillis(int)}</p>
     * <p>{@link com.binarywang.wxjava.store.api.impl.BaseWxStoreServiceImpl#setRetrySleepMillis(int)}</p>
     */
    private int retrySleepMillis = 1000;
  }
}
