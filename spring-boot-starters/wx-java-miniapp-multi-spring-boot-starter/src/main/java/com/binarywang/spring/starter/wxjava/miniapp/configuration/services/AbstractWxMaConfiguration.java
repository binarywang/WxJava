package com.binarywang.spring.starter.wxjava.miniapp.configuration.services;

import com.binarywang.spring.starter.wxjava.miniapp.properties.WxMaMultiProperties;
import com.binarywang.spring.starter.wxjava.miniapp.properties.WxMaSingleProperties;
import com.binarywang.spring.starter.wxjava.miniapp.service.WxMaMultiServices;
import com.binarywang.spring.starter.wxjava.miniapp.service.WxMaMultiServicesImpl;
import com.binarywang.spring.starter.wxjava.miniapp.service.WxMaMultiServicesSharedImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceHttpClientImpl;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceJoddHttpImpl;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceOkHttpImpl;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * WxMaConfigStorage 抽象配置类
 *
 * @author monch
 * created on 2024/9/6
 */
@RequiredArgsConstructor
@Slf4j
public abstract class AbstractWxMaConfiguration {

  protected WxMaMultiServices wxMaMultiServices(WxMaMultiProperties wxMaMultiProperties) {
    Map<String, WxMaSingleProperties> appsMap = wxMaMultiProperties.getApps();
    if (appsMap == null || appsMap.isEmpty()) {
      log.warn("微信小程序应用参数未配置，通过 WxMaMultiServices#getWxMaService(\"tenantId\")获取实例将返回空");
      return new WxMaMultiServicesImpl();
    }
    
    /**
     * 校验 appId 是否唯一，避免使用 redis 缓存 token、ticket 时错乱。
     *
     * 查看 {@link cn.binarywang.wx.miniapp.config.impl.WxMaRedisConfigImpl#setAppId(String)}
     */
    Collection<WxMaSingleProperties> apps = appsMap.values();
    if (apps.size() > 1) {
      // 校验 appId 是否唯一
      boolean multi = apps.stream()
        // 没有 appId，如果不判断是否为空，这里会报 NPE 异常
        .collect(Collectors.groupingBy(c -> c.getAppId() == null ? 0 : c.getAppId(), Collectors.counting()))
        .entrySet().stream().anyMatch(e -> e.getValue() > 1);
      if (multi) {
        throw new RuntimeException("请确保微信小程序配置 appId 的唯一性");
      }
    }

    // 根据配置选择多租户模式
    WxMaMultiProperties.MultiTenantMode mode = wxMaMultiProperties.getConfigStorage().getMultiTenantMode();
    if (mode == WxMaMultiProperties.MultiTenantMode.SHARED) {
      return createSharedMultiServices(appsMap, wxMaMultiProperties);
    } else {
      return createIsolatedMultiServices(appsMap, wxMaMultiProperties);
    }
  }

  /**
   * 创建隔离模式的多租户服务（每个租户独立 WxMaService 实例）
   */
  private WxMaMultiServices createIsolatedMultiServices(
    Map<String, WxMaSingleProperties> appsMap,
    WxMaMultiProperties wxMaMultiProperties) {
    
    WxMaMultiServicesImpl services = new WxMaMultiServicesImpl();
    Set<Map.Entry<String, WxMaSingleProperties>> entries = appsMap.entrySet();
    
    for (Map.Entry<String, WxMaSingleProperties> entry : entries) {
      String tenantId = entry.getKey();
      WxMaSingleProperties wxMaSingleProperties = entry.getValue();
      WxMaDefaultConfigImpl storage = this.wxMaConfigStorage(wxMaMultiProperties);
      this.configApp(storage, wxMaSingleProperties);
      this.configHttp(storage, wxMaMultiProperties.getConfigStorage());
      WxMaService wxMaService = this.wxMaService(storage, wxMaMultiProperties);
      services.addWxMaService(tenantId, wxMaService);
    }
    
    log.info("微信小程序多租户服务初始化完成，使用隔离模式（ISOLATED），共配置 {} 个租户", appsMap.size());
    return services;
  }

  /**
   * 创建共享模式的多租户服务（单个 WxMaService 实例管理多个配置）
   */
  private WxMaMultiServices createSharedMultiServices(
    Map<String, WxMaSingleProperties> appsMap,
    WxMaMultiProperties wxMaMultiProperties) {
    
    // 创建共享的 WxMaService 实例
    WxMaMultiProperties.ConfigStorage storage = wxMaMultiProperties.getConfigStorage();
    WxMaService sharedService = createWxMaServiceByType(storage.getHttpClientType());
    configureWxMaService(sharedService, storage);
    
    // 准备所有租户的配置，使用 TreeMap 保证顺序一致性
    Map<String, WxMaConfig> configsMap = new HashMap<>();
    String defaultTenantId = new TreeMap<>(appsMap).firstKey();
    
    for (Map.Entry<String, WxMaSingleProperties> entry : appsMap.entrySet()) {
      String tenantId = entry.getKey();
      WxMaSingleProperties wxMaSingleProperties = entry.getValue();
      WxMaDefaultConfigImpl config = this.wxMaConfigStorage(wxMaMultiProperties);
      this.configApp(config, wxMaSingleProperties);
      this.configHttp(config, storage);
      configsMap.put(tenantId, config);
    }
    
    // 设置多配置到共享的 WxMaService
    sharedService.setMultiConfigs(configsMap, defaultTenantId);
    
    log.info("微信小程序多租户服务初始化完成，使用共享模式（SHARED），共配置 {} 个租户，共享一个 HTTP 客户端", appsMap.size());
    return new WxMaMultiServicesSharedImpl(sharedService);
  }

  /**
   * 根据类型创建 WxMaService 实例
   */
  private WxMaService createWxMaServiceByType(WxMaMultiProperties.HttpClientType httpClientType) {
    switch (httpClientType) {
      case OK_HTTP:
        return new WxMaServiceOkHttpImpl();
      case JODD_HTTP:
        return new WxMaServiceJoddHttpImpl();
      case HTTP_CLIENT:
        return new WxMaServiceHttpClientImpl();
      default:
        return new WxMaServiceImpl();
    }
  }

  /**
   * 配置 WxMaService 的通用参数
   */
  private void configureWxMaService(WxMaService wxMaService, WxMaMultiProperties.ConfigStorage storage) {
    int maxRetryTimes = storage.getMaxRetryTimes();
    if (maxRetryTimes < 0) {
      maxRetryTimes = 0;
    }
    int retrySleepMillis = storage.getRetrySleepMillis();
    if (retrySleepMillis < 0) {
      retrySleepMillis = 1000;
    }
    wxMaService.setRetrySleepMillis(retrySleepMillis);
    wxMaService.setMaxRetryTimes(maxRetryTimes);
  }

  /**
   * 配置 WxMaDefaultConfigImpl
   *
   * @param wxMaMultiProperties 参数
   * @return WxMaDefaultConfigImpl
   */
  protected abstract WxMaDefaultConfigImpl wxMaConfigStorage(WxMaMultiProperties wxMaMultiProperties);

  public WxMaService wxMaService(WxMaConfig wxMaConfig, WxMaMultiProperties wxMaMultiProperties) {
    WxMaMultiProperties.ConfigStorage storage = wxMaMultiProperties.getConfigStorage();
    WxMaService wxMaService = createWxMaServiceByType(storage.getHttpClientType());
    wxMaService.setWxMaConfig(wxMaConfig);
    configureWxMaService(wxMaService, storage);
    return wxMaService;
  }

  private void configApp(WxMaDefaultConfigImpl config, WxMaSingleProperties properties) {
    String appId = properties.getAppId();
    String appSecret = properties.getAppSecret();
    String token = properties.getToken();
    String aesKey = properties.getAesKey();
    boolean useStableAccessToken = properties.isUseStableAccessToken();

    config.setAppid(appId);
    config.setSecret(appSecret);
    if (StringUtils.isNotBlank(token)) {
      config.setToken(token);
    }
    if (StringUtils.isNotBlank(aesKey)) {
      config.setAesKey(aesKey);
    }
    config.setMsgDataFormat(properties.getMsgDataFormat());
    config.useStableAccessToken(useStableAccessToken);
    config.setApiHostUrl(StringUtils.trimToNull(properties.getApiHostUrl()));
    config.setAccessTokenUrl(StringUtils.trimToNull(properties.getAccessTokenUrl()));
  }

  private void configHttp(WxMaDefaultConfigImpl config, WxMaMultiProperties.ConfigStorage storage) {
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
