package com.binarywang.spring.starter.wxjava.cp.storage;

import com.binarywang.spring.starter.wxjava.cp.properties.WxCpProperties;
import me.chanjar.weixin.cp.config.impl.WxCpDefaultConfigImpl;
import org.apache.commons.lang3.StringUtils;

/**
 * WxCpConfigStorage 抽象配置类
 *
 * @author yl & Wang_Wong
 * created on  2021/12/6
 */
public abstract class AbstractWxCpConfigStorageConfiguration {

  protected WxCpDefaultConfigImpl config(WxCpDefaultConfigImpl config, WxCpProperties properties) {
    String corpId = properties.getCorpId();
    String corpSecret = properties.getCorpSecret();
    Integer agentId = properties.getAgentId();
    String token = properties.getToken();
    String aesKey = properties.getAesKey();
    // 企业微信，私钥，会话存档路径
    String msgAuditPriKey = properties.getMsgAuditPriKey();
    String msgAuditLibPath = properties.getMsgAuditLibPath();

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
    if (StringUtils.isNotBlank(properties.getBaseApiUrl())) {
      config.setBaseApiUrl(properties.getBaseApiUrl());
    }

    WxCpProperties.ConfigStorage storage = properties.getConfigStorage();
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
    return config;
  }

}
