package com.binarywang.spring.starter.wxjava.open.configuration.services;

import com.binarywang.spring.starter.wxjava.open.properties.WxOpenMultiProperties;
import com.binarywang.spring.starter.wxjava.open.properties.WxOpenSingleProperties;
import com.binarywang.spring.starter.wxjava.open.service.WxOpenMultiServices;
import com.binarywang.spring.starter.wxjava.open.service.WxOpenMultiServicesImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.util.http.apache.ApacheHttpClientBuilder;
import me.chanjar.weixin.common.util.http.apache.DefaultApacheHttpClientBuilder;
import me.chanjar.weixin.open.api.WxOpenConfigStorage;
import me.chanjar.weixin.open.api.WxOpenService;
import me.chanjar.weixin.open.api.impl.WxOpenInMemoryConfigStorage;
import me.chanjar.weixin.open.api.impl.WxOpenServiceImpl;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * WxOpenConfigStorage 抽象配置类
 *
 * @author Binary Wang
 */
@RequiredArgsConstructor
@Slf4j
public abstract class AbstractWxOpenConfiguration {

  protected WxOpenMultiServices wxOpenMultiServices(WxOpenMultiProperties wxOpenMultiProperties) {
    Map<String, WxOpenSingleProperties> appsMap = wxOpenMultiProperties.getApps();
    if (appsMap == null || appsMap.isEmpty()) {
      log.warn("微信开放平台应用参数未配置，通过 WxOpenMultiServices#getWxOpenService(\"tenantId\")获取实例将返回空");
      return new WxOpenMultiServicesImpl();
    }
    /**
     * 校验 appId 是否唯一，避免使用 redis 缓存 token、ticket 时错乱。
     */
    Collection<WxOpenSingleProperties> apps = appsMap.values();
    if (apps.size() > 1) {
      // 校验 appId 是否唯一
      String nullAppIdPlaceholder = "__NULL_APP_ID__";
      boolean multi = apps.stream()
        // 没有 appId，如果不判断是否为空，这里会报 NPE 异常
        .collect(Collectors.groupingBy(c -> c.getAppId() == null ? nullAppIdPlaceholder : c.getAppId(), Collectors.counting()))
        .entrySet().stream().anyMatch(e -> e.getValue() > 1);
      if (multi) {
        throw new RuntimeException("请确保微信开放平台配置 appId 的唯一性");
      }
    }
    WxOpenMultiServicesImpl services = new WxOpenMultiServicesImpl();

    Set<Map.Entry<String, WxOpenSingleProperties>> entries = appsMap.entrySet();
    for (Map.Entry<String, WxOpenSingleProperties> entry : entries) {
      String tenantId = entry.getKey();
      WxOpenSingleProperties wxOpenSingleProperties = entry.getValue();
      WxOpenInMemoryConfigStorage storage = this.wxOpenConfigStorage(wxOpenMultiProperties);
      this.configApp(storage, wxOpenSingleProperties);
      this.configHttp(storage, wxOpenMultiProperties.getConfigStorage());
      WxOpenService wxOpenService = this.wxOpenService(storage, wxOpenMultiProperties);
      services.addWxOpenService(tenantId, wxOpenService);
    }
    return services;
  }

  /**
   * 配置 WxOpenInMemoryConfigStorage
   *
   * @param wxOpenMultiProperties 参数
   * @return WxOpenInMemoryConfigStorage
   */
  protected abstract WxOpenInMemoryConfigStorage wxOpenConfigStorage(WxOpenMultiProperties wxOpenMultiProperties);

  public WxOpenService wxOpenService(WxOpenConfigStorage configStorage, WxOpenMultiProperties wxOpenMultiProperties) {
    WxOpenService wxOpenService = new WxOpenServiceImpl();
    wxOpenService.setWxOpenConfigStorage(configStorage);
    return wxOpenService;
  }

  private void configApp(WxOpenInMemoryConfigStorage config, WxOpenSingleProperties appProperties) {
    String appId = appProperties.getAppId();
    String secret = appProperties.getSecret();
    String token = appProperties.getToken();
    String aesKey = appProperties.getAesKey();
    String apiHostUrl = appProperties.getApiHostUrl();
    String accessTokenUrl = appProperties.getAccessTokenUrl();

    // appId 和 secret 是必需的
    if (StringUtils.isBlank(appId)) {
      throw new IllegalArgumentException("微信开放平台 appId 不能为空");
    }
    if (StringUtils.isBlank(secret)) {
      throw new IllegalArgumentException("微信开放平台 secret 不能为空");
    }

    config.setComponentAppId(appId);
    config.setComponentAppSecret(secret);
    if (StringUtils.isNotBlank(token)) {
      config.setComponentToken(token);
    }
    if (StringUtils.isNotBlank(aesKey)) {
      config.setComponentAesKey(aesKey);
    }
    // 设置URL配置
    config.setApiHostUrl(StringUtils.trimToNull(apiHostUrl));
    config.setAccessTokenUrl(StringUtils.trimToNull(accessTokenUrl));
  }

  private void configHttp(WxOpenInMemoryConfigStorage config, WxOpenMultiProperties.ConfigStorage storage) {
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

    // 设置重试配置
    int maxRetryTimes = storage.getMaxRetryTimes();
    if (maxRetryTimes < 0) {
      maxRetryTimes = 0;
    }
    int retrySleepMillis = storage.getRetrySleepMillis();
    if (retrySleepMillis < 0) {
      retrySleepMillis = 1000;
    }
    config.setRetrySleepMillis(retrySleepMillis);
    config.setMaxRetryTimes(maxRetryTimes);

    // 设置自定义的HttpClient超时配置
    ApacheHttpClientBuilder clientBuilder = config.getApacheHttpClientBuilder();
    if (clientBuilder == null) {
      clientBuilder = DefaultApacheHttpClientBuilder.get();
    }
    if (clientBuilder instanceof DefaultApacheHttpClientBuilder) {
      DefaultApacheHttpClientBuilder defaultBuilder = (DefaultApacheHttpClientBuilder) clientBuilder;
      defaultBuilder.setConnectionTimeout(storage.getConnectionTimeout());
      defaultBuilder.setSoTimeout(storage.getSoTimeout());
      defaultBuilder.setConnectionRequestTimeout(storage.getConnectionRequestTimeout());
      config.setApacheHttpClientBuilder(defaultBuilder);
    }
  }
}
