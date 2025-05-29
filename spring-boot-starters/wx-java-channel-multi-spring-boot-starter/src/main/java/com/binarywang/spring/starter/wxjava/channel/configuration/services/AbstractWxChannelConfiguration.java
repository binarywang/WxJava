package com.binarywang.spring.starter.wxjava.channel.configuration.services;

import com.binarywang.spring.starter.wxjava.channel.enums.HttpClientType;
import com.binarywang.spring.starter.wxjava.channel.properties.WxChannelMultiProperties;
import com.binarywang.spring.starter.wxjava.channel.properties.WxChannelSingleProperties;
import com.binarywang.spring.starter.wxjava.channel.service.WxChannelMultiServices;
import com.binarywang.spring.starter.wxjava.channel.service.WxChannelMultiServicesImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.channel.api.WxChannelService;
import me.chanjar.weixin.channel.api.impl.WxChannelServiceHttpClientImpl;
import me.chanjar.weixin.channel.api.impl.WxChannelServiceImpl;
import me.chanjar.weixin.channel.config.WxChannelConfig;
import me.chanjar.weixin.channel.config.impl.WxChannelDefaultConfigImpl;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * WxChannelConfigStorage 抽象配置类
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 * @date 2024/9/13
 */
@RequiredArgsConstructor
@Slf4j
public abstract class AbstractWxChannelConfiguration {
  protected WxChannelMultiServices wxChannelMultiServices(WxChannelMultiProperties wxChannelMultiProperties) {
    Map<String, WxChannelSingleProperties> appsMap = wxChannelMultiProperties.getApps();
    if (appsMap == null || appsMap.isEmpty()) {
      log.warn("微信视频号应用参数未配置，通过 WxChannelMultiServices#getWxChannelService(\"tenantId\")获取实例将返回空");
      return new WxChannelMultiServicesImpl();
    }
    /**
     * 校验 appId 是否唯一，避免使用 redis 缓存 token、ticket 时错乱。
     *
     * 查看 {@link me.chanjar.weixin.channel.config.impl.WxChannelRedisConfigImpl#setAppid(String)}
     */
    Collection<WxChannelSingleProperties> apps = appsMap.values();
    if (apps.size() > 1) {
      // 校验 appId 是否唯一
      boolean multi = apps.stream()
        // 没有 appId，如果不判断是否为空，这里会报 NPE 异常
        .collect(Collectors.groupingBy(c -> c.getAppId() == null ? 0 : c.getAppId(), Collectors.counting()))
        .entrySet().stream().anyMatch(e -> e.getValue() > 1);
      if (multi) {
        throw new RuntimeException("请确保微信视频号配置 appId 的唯一性");
      }
    }
    WxChannelMultiServicesImpl services = new WxChannelMultiServicesImpl();

    Set<Map.Entry<String, WxChannelSingleProperties>> entries = appsMap.entrySet();
    for (Map.Entry<String, WxChannelSingleProperties> entry : entries) {
      String tenantId = entry.getKey();
      WxChannelSingleProperties wxChannelSingleProperties = entry.getValue();
      WxChannelDefaultConfigImpl storage = this.wxChannelConfigStorage(wxChannelMultiProperties);
      this.configApp(storage, wxChannelSingleProperties);
      this.configHttp(storage, wxChannelMultiProperties.getConfigStorage());
      WxChannelService wxChannelService = this.wxChannelService(storage, wxChannelMultiProperties);
      services.addWxChannelService(tenantId, wxChannelService);
    }
    return services;
  }

  /**
   * 配置 WxChannelDefaultConfigImpl
   *
   * @param wxChannelMultiProperties 参数
   * @return WxChannelDefaultConfigImpl
   */
  protected abstract WxChannelDefaultConfigImpl wxChannelConfigStorage(WxChannelMultiProperties wxChannelMultiProperties);

  public WxChannelService wxChannelService(WxChannelConfig wxChannelConfig, WxChannelMultiProperties wxChannelMultiProperties) {
    WxChannelMultiProperties.ConfigStorage storage = wxChannelMultiProperties.getConfigStorage();
    HttpClientType httpClientType = storage.getHttpClientType();
    WxChannelService wxChannelService;
    switch (httpClientType) {
//      case OK_HTTP:
//        wxChannelService = new WxChannelServiceOkHttpImpl(false, false);
//        break;
      case HTTP_CLIENT:
        wxChannelService = new WxChannelServiceHttpClientImpl();
        break;
      default:
        wxChannelService = new WxChannelServiceImpl();
        break;
    }

    wxChannelService.setConfig(wxChannelConfig);
    int maxRetryTimes = storage.getMaxRetryTimes();
    if (maxRetryTimes < 0) {
      maxRetryTimes = 0;
    }
    int retrySleepMillis = storage.getRetrySleepMillis();
    if (retrySleepMillis < 0) {
      retrySleepMillis = 1000;
    }
    wxChannelService.setRetrySleepMillis(retrySleepMillis);
    wxChannelService.setMaxRetryTimes(maxRetryTimes);
    return wxChannelService;
  }

  private void configApp(WxChannelDefaultConfigImpl config, WxChannelSingleProperties wxChannelSingleProperties) {
    String appId = wxChannelSingleProperties.getAppId();
    String appSecret = wxChannelSingleProperties.getSecret();
    String token = wxChannelSingleProperties.getToken();
    String aesKey = wxChannelSingleProperties.getAesKey();
    boolean useStableAccessToken = wxChannelSingleProperties.isUseStableAccessToken();

    config.setAppid(appId);
    config.setSecret(appSecret);
    if (StringUtils.isNotBlank(token)) {
      config.setToken(token);
    }
    if (StringUtils.isNotBlank(aesKey)) {
      config.setAesKey(aesKey);
    }
    config.setStableAccessToken(useStableAccessToken);
  }

  private void configHttp(WxChannelDefaultConfigImpl config, WxChannelMultiProperties.ConfigStorage storage) {
    String httpProxyHost = storage.getHttpProxyHost();
    Integer httpProxyPort = storage.getHttpProxyPort();
    String httpProxyUsername = storage.getHttpProxyUsername();
    String httpProxyPassword = storage.getHttpProxyPassword();
    if (StringUtils.isNotBlank(httpProxyHost)) {
      config.setHttpProxyHost(httpProxyHost);
      if (httpProxyPort != null) {
        config.setHttpProxyPort(httpProxyPort);
      }
      if (StringUtils.isNotBlank(httpProxyUsername)) {
        config.setHttpProxyUsername(httpProxyUsername);
      }
      if (StringUtils.isNotBlank(httpProxyPassword)) {
        config.setHttpProxyPassword(httpProxyPassword);
      }
    }
  }
}
