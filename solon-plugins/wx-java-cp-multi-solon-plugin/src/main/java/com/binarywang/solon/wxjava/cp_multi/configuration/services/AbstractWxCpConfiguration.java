package com.binarywang.solon.wxjava.cp_multi.configuration.services;

import com.binarywang.solon.wxjava.cp_multi.properties.WxCpMultiProperties;
import com.binarywang.solon.wxjava.cp_multi.properties.WxCpSingleProperties;
import com.binarywang.solon.wxjava.cp_multi.service.WxCpMultiServices;
import com.binarywang.solon.wxjava.cp_multi.service.WxCpMultiServicesImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.api.impl.*;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import me.chanjar.weixin.cp.config.impl.WxCpDefaultConfigImpl;
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
public abstract class AbstractWxCpConfiguration {

  protected WxCpMultiServices wxCpMultiServices(WxCpMultiProperties wxCpMultiProperties) {
    Map<String, WxCpSingleProperties> corps = wxCpMultiProperties.getCorps();
    if (corps == null || corps.isEmpty()) {
      log.warn("企业微信应用参数未配置，通过 WxCpMultiServices#getWxCpService(\"tenantId\")获取实例将返回空");
      return new WxCpMultiServicesImpl();
    }
    /**
     * 校验同一个企业下，agentId 是否唯一，避免使用 redis 缓存 token、ticket 时错乱。
     *
     * 查看 {@link me.chanjar.weixin.cp.config.impl.AbstractWxCpInRedisConfigImpl#setAgentId(Integer)}
     */
    Collection<WxCpSingleProperties> corpList = corps.values();
    if (corpList.size() > 1) {
      // 先按 corpId 分组统计
      Map<String, List<WxCpSingleProperties>> corpsMap = corpList.stream()
        .collect(Collectors.groupingBy(WxCpSingleProperties::getCorpId));
      Set<Map.Entry<String, List<WxCpSingleProperties>>> entries = corpsMap.entrySet();
      for (Map.Entry<String, List<WxCpSingleProperties>> entry : entries) {
        String corpId = entry.getKey();
        // 校验每个企业下，agentId 是否唯一
        boolean multi = entry.getValue().stream()
          // 通讯录没有 agentId，如果不判断是否为空，这里会报 NPE 异常
          .collect(Collectors.groupingBy(c -> c.getAgentId() == null ? 0 : c.getAgentId(), Collectors.counting()))
          .entrySet().stream().anyMatch(e -> e.getValue() > 1);
        if (multi) {
          throw new RuntimeException("请确保企业微信配置唯一性[" + corpId + "]");
        }
      }
    }
    WxCpMultiServicesImpl services = new WxCpMultiServicesImpl();

    Set<Map.Entry<String, WxCpSingleProperties>> entries = corps.entrySet();
    for (Map.Entry<String, WxCpSingleProperties> entry : entries) {
      String tenantId = entry.getKey();
      WxCpSingleProperties wxCpSingleProperties = entry.getValue();
      WxCpDefaultConfigImpl storage = this.wxCpConfigStorage(wxCpMultiProperties);
      this.configCorp(storage, wxCpSingleProperties);
      this.configHttp(storage, wxCpMultiProperties.getConfigStorage());
      WxCpService wxCpService = this.wxCpService(storage, wxCpMultiProperties.getConfigStorage());
      services.addWxCpService(tenantId, wxCpService);
    }
    return services;
  }

  /**
   * 配置 WxCpDefaultConfigImpl
   *
   * @param wxCpMultiProperties 参数
   * @return WxCpDefaultConfigImpl
   */
  protected abstract WxCpDefaultConfigImpl wxCpConfigStorage(WxCpMultiProperties wxCpMultiProperties);

  private WxCpService wxCpService(WxCpConfigStorage wxCpConfigStorage, WxCpMultiProperties.ConfigStorage storage) {
    WxCpMultiProperties.HttpClientType httpClientType = storage.getHttpClientType();
    WxCpService wxCpService;
    switch (httpClientType) {
      case OK_HTTP:
        wxCpService = new WxCpServiceOkHttpImpl();
        break;
      case JODD_HTTP:
        wxCpService = new WxCpServiceJoddHttpImpl();
        break;
      case HTTP_CLIENT:
        wxCpService = new WxCpServiceApacheHttpClientImpl();
        break;
      case HTTP_COMPONENTS:
        wxCpService = new WxCpServiceHttpComponentsImpl();
        break;
      default:
        wxCpService = new WxCpServiceImpl();
        break;
    }
    wxCpService.setWxCpConfigStorage(wxCpConfigStorage);
    int maxRetryTimes = storage.getMaxRetryTimes();
    if (maxRetryTimes < 0) {
      maxRetryTimes = 0;
    }
    int retrySleepMillis = storage.getRetrySleepMillis();
    if (retrySleepMillis < 0) {
      retrySleepMillis = 1000;
    }
    wxCpService.setRetrySleepMillis(retrySleepMillis);
    wxCpService.setMaxRetryTimes(maxRetryTimes);
    return wxCpService;
  }

  private void configCorp(WxCpDefaultConfigImpl config, WxCpSingleProperties wxCpSingleProperties) {
    String corpId = wxCpSingleProperties.getCorpId();
    String corpSecret = wxCpSingleProperties.getCorpSecret();
    Integer agentId = wxCpSingleProperties.getAgentId();
    String token = wxCpSingleProperties.getToken();
    String aesKey = wxCpSingleProperties.getAesKey();
    // 企业微信，私钥，会话存档路径
    String msgAuditPriKey = wxCpSingleProperties.getMsgAuditPriKey();
    String msgAuditLibPath = wxCpSingleProperties.getMsgAuditLibPath();

    config.setCorpId(corpId);
    config.setCorpSecret(corpSecret);
    config.setAgentId(agentId);
    if (StringUtils.isNotBlank(token)) {
      config.setToken(token);
    }
    if (StringUtils.isNotBlank(aesKey)) {
      config.setAesKey(aesKey);
    }
    if (StringUtils.isNotBlank(msgAuditPriKey)) {
      config.setMsgAuditPriKey(msgAuditPriKey);
    }
    if (StringUtils.isNotBlank(msgAuditLibPath)) {
      config.setMsgAuditLibPath(msgAuditLibPath);
    }
  }

  private void configHttp(WxCpDefaultConfigImpl config, WxCpMultiProperties.ConfigStorage storage) {
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
