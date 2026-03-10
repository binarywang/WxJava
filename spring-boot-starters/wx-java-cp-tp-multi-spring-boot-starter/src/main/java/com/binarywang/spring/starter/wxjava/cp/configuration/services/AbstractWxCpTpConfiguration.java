package com.binarywang.spring.starter.wxjava.cp.configuration.services;

import com.binarywang.spring.starter.wxjava.cp.properties.WxCpTpMultiProperties;
import com.binarywang.spring.starter.wxjava.cp.properties.WxCpTpSingleProperties;
import com.binarywang.spring.starter.wxjava.cp.service.WxCpTpMultiServices;
import com.binarywang.spring.starter.wxjava.cp.service.WxCpTpMultiServicesImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.cp.config.WxCpTpConfigStorage;
import me.chanjar.weixin.cp.config.impl.WxCpTpDefaultConfigImpl;
import me.chanjar.weixin.cp.tp.service.WxCpTpService;
import me.chanjar.weixin.cp.tp.service.impl.WxCpTpServiceApacheHttpClientImpl;
import me.chanjar.weixin.cp.tp.service.impl.WxCpTpServiceImpl;
import me.chanjar.weixin.cp.tp.service.impl.WxCpTpServiceJoddHttpImpl;
import me.chanjar.weixin.cp.tp.service.impl.WxCpTpServiceOkHttpImpl;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * WxCpConfigStorage 抽象配置类
 *
 * @author yl
 * created on 2023/10/16
 */
@RequiredArgsConstructor
@Slf4j
public abstract class AbstractWxCpTpConfiguration {

  /**
   *
   * @param wxCpTpMultiProperties 应用列表配置
   * @param services 用于支持，应用启动之后，可以调用这个接口添加新服务对象。主要是配置是从数据库中读取的
   * @return
   */
  public WxCpTpMultiServices wxCpMultiServices(WxCpTpMultiProperties wxCpTpMultiProperties,WxCpTpMultiServices services) {
    Map<String, WxCpTpSingleProperties> corps = wxCpTpMultiProperties.getCorps();
    if (corps == null || corps.isEmpty()) {
      log.warn("企业微信应用参数未配置，通过 WxCpMultiServices#getWxCpTpService(\"tenantId\")获取实例将返回空");
      return new WxCpTpMultiServicesImpl();
    }

    if (services == null) {
       services = new WxCpTpMultiServicesImpl();
    }

    Set<Map.Entry<String, WxCpTpSingleProperties>> entries = corps.entrySet();
    for (Map.Entry<String, WxCpTpSingleProperties> entry : entries) {
      String tenantId = entry.getKey();
      WxCpTpSingleProperties wxCpTpSingleProperties = entry.getValue();
      WxCpTpDefaultConfigImpl storage = this.wxCpTpConfigStorage(wxCpTpMultiProperties);
      this.configCorp(storage, wxCpTpSingleProperties);
      this.configHttp(storage, wxCpTpMultiProperties.getConfigStorage());
      WxCpTpService wxCpTpService = this.wxCpTpService(storage, wxCpTpMultiProperties.getConfigStorage());
      if (services.getWxCpTpService(tenantId) == null) {
        // 不存在的才会添加到服务列表中
        services.addWxCpTpService(tenantId, wxCpTpService);
      }
    }
    return services;
  }

  /**
   * 配置 WxCpDefaultConfigImpl
   *
   * @param wxCpTpMultiProperties 参数
   * @return WxCpDefaultConfigImpl
   */
  protected abstract WxCpTpDefaultConfigImpl wxCpTpConfigStorage(WxCpTpMultiProperties wxCpTpMultiProperties);

  private WxCpTpService wxCpTpService(WxCpTpConfigStorage wxCpTpConfigStorage, WxCpTpMultiProperties.ConfigStorage storage) {
    WxCpTpMultiProperties.HttpClientType httpClientType = storage.getHttpClientType();
    WxCpTpService cpTpService;
    switch (httpClientType) {
      case OK_HTTP:
        cpTpService = new WxCpTpServiceOkHttpImpl();
        break;
      case JODD_HTTP:
        cpTpService = new WxCpTpServiceJoddHttpImpl();
        break;
      case HTTP_CLIENT:
        cpTpService = new WxCpTpServiceApacheHttpClientImpl();
        break;
      default:
        cpTpService = new WxCpTpServiceImpl();
        break;
    }
    cpTpService.setWxCpTpConfigStorage(wxCpTpConfigStorage);
    int maxRetryTimes = storage.getMaxRetryTimes();
    if (maxRetryTimes < 0) {
      maxRetryTimes = 0;
    }
    int retrySleepMillis = storage.getRetrySleepMillis();
    if (retrySleepMillis < 0) {
      retrySleepMillis = 1000;
    }
    cpTpService.setRetrySleepMillis(retrySleepMillis);
    cpTpService.setMaxRetryTimes(maxRetryTimes);
    return cpTpService;
  }

  private void configCorp(WxCpTpDefaultConfigImpl config, WxCpTpSingleProperties wxCpTpSingleProperties) {
    String corpId = wxCpTpSingleProperties.getCorpId();
    String providerSecret = wxCpTpSingleProperties.getProviderSecret();
    String suiteId = wxCpTpSingleProperties.getSuiteId();
    String token = wxCpTpSingleProperties.getToken();
    String suiteSecret = wxCpTpSingleProperties.getSuiteSecret();
    // 企业微信，私钥，会话存档路径
    config.setCorpId(corpId);
    config.setProviderSecret(providerSecret);
    config.setEncodingAESKey(wxCpTpSingleProperties.getEncodingAESKey());
    config.setSuiteId(suiteId);
    config.setToken(token);
    config.setSuiteSecret(suiteSecret);
  }

  private void configHttp(WxCpTpDefaultConfigImpl config, WxCpTpMultiProperties.ConfigStorage storage) {
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
