package com.binarywang.solon.wxjava.store.configuration.services;

import com.binarywang.solon.wxjava.store.enums.HttpClientType;
import com.binarywang.solon.wxjava.store.properties.WxStoreMultiProperties;
import com.binarywang.solon.wxjava.store.properties.WxStoreSingleProperties;
import com.binarywang.solon.wxjava.store.service.WxStoreMultiServices;
import com.binarywang.solon.wxjava.store.service.WxStoreMultiServicesImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.binarywang.wxjava.store.api.WxStoreService;
import com.binarywang.wxjava.store.api.impl.WxStoreServiceHttpComponentsImpl;
import com.binarywang.wxjava.store.api.impl.WxStoreServiceHttpClientImpl;
import com.binarywang.wxjava.store.api.impl.WxStoreServiceImpl;
import com.binarywang.wxjava.store.config.WxStoreConfig;
import com.binarywang.wxjava.store.config.impl.WxStoreDefaultConfigImpl;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * WxStoreConfigStorage 抽象配置类
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a> 2024/9/13
 * @author noear
 */
@RequiredArgsConstructor
@Slf4j
public abstract class AbstractWxStoreConfiguration {
  protected WxStoreMultiServices wxStoreMultiServices(WxStoreMultiProperties wxStoreMultiProperties) {
    Map<String, WxStoreSingleProperties> appsMap = wxStoreMultiProperties.getApps();
    if (appsMap == null || appsMap.isEmpty()) {
      log.warn("微信小店应用参数未配置，通过 WxStoreMultiServices#getWxStoreService(\"tenantId\")获取实例将返回空");
      return new WxStoreMultiServicesImpl();
    }
    /**
     * 校验 appId 是否唯一，避免使用 redis 缓存 token、ticket 时错乱。
     *
     * 查看 {@link com.binarywang.wxjava.store.config.impl.WxStoreRedisConfigImpl#setAppid(String)}
     */
    Collection<WxStoreSingleProperties> apps = appsMap.values();
    if (apps.size() > 1) {
      // 校验 appId 是否唯一
      boolean multi = apps.stream()
        // 没有 appId，如果不判断是否为空，这里会报 NPE 异常
        .collect(Collectors.groupingBy(c -> c.getAppId() == null ? 0 : c.getAppId(), Collectors.counting()))
        .entrySet().stream().anyMatch(e -> e.getValue() > 1);
      if (multi) {
        throw new RuntimeException("请确保微信小店配置 appId 的唯一性");
      }
    }
    WxStoreMultiServicesImpl services = new WxStoreMultiServicesImpl();

    Set<Map.Entry<String, WxStoreSingleProperties>> entries = appsMap.entrySet();
    for (Map.Entry<String, WxStoreSingleProperties> entry : entries) {
      String tenantId = entry.getKey();
      WxStoreSingleProperties wxStoreSingleProperties = entry.getValue();
      WxStoreDefaultConfigImpl storage = this.wxStoreConfigStorage(wxStoreMultiProperties);
      this.configApp(storage, wxStoreSingleProperties);
      this.configHttp(storage, wxStoreMultiProperties.getConfigStorage());
      WxStoreService wxStoreService = this.wxStoreService(storage, wxStoreMultiProperties);
      services.addWxStoreService(tenantId, wxStoreService);
    }
    return services;
  }

  /**
   * 配置 WxStoreDefaultConfigImpl
   *
   * @param wxStoreMultiProperties 参数
   * @return WxStoreDefaultConfigImpl
   */
  protected abstract WxStoreDefaultConfigImpl wxStoreConfigStorage(WxStoreMultiProperties wxStoreMultiProperties);

  public WxStoreService wxStoreService(WxStoreConfig wxStoreConfig, WxStoreMultiProperties wxStoreMultiProperties) {
    WxStoreMultiProperties.ConfigStorage storage = wxStoreMultiProperties.getConfigStorage();
    HttpClientType httpClientType = storage.getHttpClientType();
    WxStoreService wxStoreService;
    switch (httpClientType) {
//      case OK_HTTP:
//        wxStoreService = new WxStoreServiceOkHttpImpl(false, false);
//        break;
      case HTTP_CLIENT:
        wxStoreService = new WxStoreServiceHttpClientImpl();
        break;
      case HTTP_COMPONENTS:
        wxStoreService = new WxStoreServiceHttpComponentsImpl();
        break;
      default:
        wxStoreService = new WxStoreServiceImpl();
        break;
    }

    wxStoreService.setConfig(wxStoreConfig);
    int maxRetryTimes = storage.getMaxRetryTimes();
    if (maxRetryTimes < 0) {
      maxRetryTimes = 0;
    }
    int retrySleepMillis = storage.getRetrySleepMillis();
    if (retrySleepMillis < 0) {
      retrySleepMillis = 1000;
    }
    wxStoreService.setRetrySleepMillis(retrySleepMillis);
    wxStoreService.setMaxRetryTimes(maxRetryTimes);
    return wxStoreService;
  }

  private void configApp(WxStoreDefaultConfigImpl config, WxStoreSingleProperties wxStoreSingleProperties) {
    String appId = wxStoreSingleProperties.getAppId();
    String appSecret = wxStoreSingleProperties.getSecret();
    String token = wxStoreSingleProperties.getToken();
    String aesKey = wxStoreSingleProperties.getAesKey();
    boolean useStableAccessToken = wxStoreSingleProperties.isUseStableAccessToken();

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

  private void configHttp(WxStoreDefaultConfigImpl config, WxStoreMultiProperties.ConfigStorage storage) {
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
