package com.binarywang.solon.wxjava.miniapp.configuration.services;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceHttpClientImpl;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceJoddHttpImpl;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceOkHttpImpl;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import com.binarywang.solon.wxjava.miniapp.properties.WxMaMultiProperties;
import com.binarywang.solon.wxjava.miniapp.properties.WxMaSingleProperties;
import com.binarywang.solon.wxjava.miniapp.service.WxMaMultiServices;
import com.binarywang.solon.wxjava.miniapp.service.WxMaMultiServicesImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
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
      log.warn("微信公众号应用参数未配置，通过 WxMaMultiServices#getWxMaService(\"tenantId\")获取实例将返回空");
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
        throw new RuntimeException("请确保微信公众号配置 appId 的唯一性");
      }
    }
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
    return services;
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
    WxMaMultiProperties.HttpClientType httpClientType = storage.getHttpClientType();
    WxMaService wxMaService;
    switch (httpClientType) {
      case OK_HTTP:
        wxMaService = new WxMaServiceOkHttpImpl();
        break;
      case JODD_HTTP:
        wxMaService = new WxMaServiceJoddHttpImpl();
        break;
      case HTTP_CLIENT:
        wxMaService = new WxMaServiceHttpClientImpl();
        break;
      default:
        wxMaService = new WxMaServiceImpl();
        break;
    }

    wxMaService.setWxMaConfig(wxMaConfig);
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
    return wxMaService;
  }

  private void configApp(WxMaDefaultConfigImpl config, WxMaSingleProperties corpProperties) {
    String appId = corpProperties.getAppId();
    String appSecret = corpProperties.getAppSecret();
    String token = corpProperties.getToken();
    String aesKey = corpProperties.getAesKey();
    boolean useStableAccessToken = corpProperties.isUseStableAccessToken();

    config.setAppid(appId);
    config.setSecret(appSecret);
    if (StringUtils.isNotBlank(token)) {
      config.setToken(token);
    }
    if (StringUtils.isNotBlank(aesKey)) {
      config.setAesKey(aesKey);
    }
    config.useStableAccessToken(useStableAccessToken);
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
