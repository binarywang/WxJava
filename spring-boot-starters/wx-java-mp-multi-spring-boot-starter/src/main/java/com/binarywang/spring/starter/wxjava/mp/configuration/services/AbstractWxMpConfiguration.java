package com.binarywang.spring.starter.wxjava.mp.configuration.services;

import com.binarywang.spring.starter.wxjava.mp.properties.WxMpMultiProperties;
import com.binarywang.spring.starter.wxjava.mp.properties.WxMpSingleProperties;
import com.binarywang.spring.starter.wxjava.mp.service.WxMpMultiServices;
import com.binarywang.spring.starter.wxjava.mp.service.WxMpMultiServicesImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.*;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.config.WxMpHostConfig;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * WxMpConfigStorage 抽象配置类
 *
 * @author yl
 * created on 2024/1/23
 */
@RequiredArgsConstructor
@Slf4j
public abstract class AbstractWxMpConfiguration {

  protected WxMpMultiServices wxMpMultiServices(WxMpMultiProperties wxMpMultiProperties) {
    Map<String, WxMpSingleProperties> appsMap = wxMpMultiProperties.getApps();
    if (appsMap == null || appsMap.isEmpty()) {
      log.warn("微信公众号应用参数未配置，通过 WxMpMultiServices#getWxMpService(\"tenantId\")获取实例将返回空");
      return new WxMpMultiServicesImpl();
    }
    /**
     * 校验 appId 是否唯一，避免使用 redis 缓存 token、ticket 时错乱。
     *
     * 查看 {@link me.chanjar.weixin.mp.config.impl.WxMpRedisConfigImpl#setAppId(String)}
     */
    Collection<WxMpSingleProperties> apps = appsMap.values();
    if (apps.size() > 1) {
      // 校验 appId 是否唯一
      boolean multi = apps.stream()
        // 没有 appId，如果不判断是否为空，这里会报 NPE 异常
        .collect(Collectors.groupingBy(c -> c.getAppId() == null ? 0 : c.getAppId(), Collectors.counting()))
        .entrySet().stream().anyMatch(e -> e.getValue() > 1);
      if (multi) {
        throw new RuntimeException("请确保微信公众号配置 appId 的唯一性");
      }
    }
    WxMpMultiServicesImpl services = new WxMpMultiServicesImpl();

    Set<Map.Entry<String, WxMpSingleProperties>> entries = appsMap.entrySet();
    for (Map.Entry<String, WxMpSingleProperties> entry : entries) {
      String tenantId = entry.getKey();
      WxMpSingleProperties wxMpSingleProperties = entry.getValue();
      WxMpDefaultConfigImpl storage = this.wxMpConfigStorage(wxMpMultiProperties);
      this.configApp(storage, wxMpSingleProperties);
      this.configHttp(storage, wxMpMultiProperties.getConfigStorage());
      this.configHost(storage, wxMpMultiProperties.getHosts());
      WxMpService wxMpService = this.wxMpService(storage, wxMpMultiProperties);
      services.addWxMpService(tenantId, wxMpService);
    }
    return services;
  }

  /**
   * 配置 WxMpDefaultConfigImpl
   *
   * @param wxMpMultiProperties 参数
   * @return WxMpDefaultConfigImpl
   */
  protected abstract WxMpDefaultConfigImpl wxMpConfigStorage(WxMpMultiProperties wxMpMultiProperties);

  public WxMpService wxMpService(WxMpConfigStorage configStorage, WxMpMultiProperties wxMpMultiProperties) {
    WxMpMultiProperties.ConfigStorage storage = wxMpMultiProperties.getConfigStorage();
    WxMpMultiProperties.HttpClientType httpClientType = storage.getHttpClientType();
    WxMpService wxMpService;
    switch (httpClientType) {
      case OK_HTTP:
        wxMpService = new WxMpServiceOkHttpImpl();
        break;
      case JODD_HTTP:
        wxMpService = new WxMpServiceJoddHttpImpl();
        break;
      case HTTP_CLIENT:
        wxMpService = new WxMpServiceHttpClientImpl();
        break;
      case HTTP_COMPONENTS:
        wxMpService = new WxMpServiceHttpComponentsImpl();
        break;
      default:
        wxMpService = new WxMpServiceImpl();
        break;
    }

    wxMpService.setWxMpConfigStorage(configStorage);
    int maxRetryTimes = storage.getMaxRetryTimes();
    if (maxRetryTimes < 0) {
      maxRetryTimes = 0;
    }
    int retrySleepMillis = storage.getRetrySleepMillis();
    if (retrySleepMillis < 0) {
      retrySleepMillis = 1000;
    }
    wxMpService.setRetrySleepMillis(retrySleepMillis);
    wxMpService.setMaxRetryTimes(maxRetryTimes);
    return wxMpService;
  }

  private void configApp(WxMpDefaultConfigImpl config, WxMpSingleProperties corpProperties) {
    String appId = corpProperties.getAppId();
    String appSecret = corpProperties.getAppSecret();
    String token = corpProperties.getToken();
    String aesKey = corpProperties.getAesKey();
    boolean useStableAccessToken = corpProperties.isUseStableAccessToken();

    config.setAppId(appId);
    config.setSecret(appSecret);
    if (StringUtils.isNotBlank(token)) {
      config.setToken(token);
    }
    if (StringUtils.isNotBlank(aesKey)) {
      config.setAesKey(aesKey);
    }
    config.setUseStableAccessToken(useStableAccessToken);
  }

  private void configHttp(WxMpDefaultConfigImpl config, WxMpMultiProperties.ConfigStorage storage) {
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

  /**
   * wx host config
   */
  private void configHost(WxMpDefaultConfigImpl config, WxMpMultiProperties.HostConfig hostConfig) {
    if (hostConfig != null) {
      String apiHost = hostConfig.getApiHost();
      String mpHost = hostConfig.getMpHost();
      String openHost = hostConfig.getOpenHost();
      WxMpHostConfig wxMpHostConfig = new WxMpHostConfig();
      wxMpHostConfig.setApiHost(StringUtils.isNotBlank(apiHost) ? apiHost : null);
      wxMpHostConfig.setMpHost(StringUtils.isNotBlank(mpHost) ? mpHost : null);
      wxMpHostConfig.setOpenHost(StringUtils.isNotBlank(openHost) ? openHost : null);
      config.setHostConfig(wxMpHostConfig);
    }
  }
}
