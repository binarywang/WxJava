package com.binarywang.spring.starter.wxjava.mp.config.storage;

import com.binarywang.spring.starter.wxjava.mp.properties.WxMpProperties;
import me.chanjar.weixin.common.util.http.apache.ApacheHttpClientBuilder;
import me.chanjar.weixin.common.util.http.apache.DefaultApacheHttpClientBuilder;
import me.chanjar.weixin.mp.config.WxMpHostConfig;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.apache.commons.lang3.StringUtils;

/**
 * @author <a href="https://github.com/buaazyl">zhangyl</a>
 */
public abstract class AbstractWxMpConfigStorageConfiguration {

  protected WxMpDefaultConfigImpl config(WxMpDefaultConfigImpl config, WxMpProperties properties) {
    config.setAppId(properties.getAppId());
    config.setSecret(properties.getSecret());
    config.setToken(properties.getToken());
    config.setAesKey(properties.getAesKey());
    config.setUseStableAccessToken(properties.isUseStableAccessToken());

    WxMpProperties.ConfigStorage configStorageProperties = properties.getConfigStorage();
    config.setHttpProxyHost(configStorageProperties.getHttpProxyHost());
    config.setHttpProxyUsername(configStorageProperties.getHttpProxyUsername());
    config.setHttpProxyPassword(configStorageProperties.getHttpProxyPassword());
    if (configStorageProperties.getHttpProxyPort() != null) {
      config.setHttpProxyPort(configStorageProperties.getHttpProxyPort());
    }

    // 设置自定义的 HttpClient 超时配置
    ApacheHttpClientBuilder clientBuilder = config.getApacheHttpClientBuilder();
    if (clientBuilder == null) {
      clientBuilder = DefaultApacheHttpClientBuilder.get();
    }
    if (clientBuilder instanceof DefaultApacheHttpClientBuilder) {
      DefaultApacheHttpClientBuilder defaultBuilder = (DefaultApacheHttpClientBuilder) clientBuilder;
      defaultBuilder.setConnectionTimeout(configStorageProperties.getConnectionTimeout());
      defaultBuilder.setSoTimeout(configStorageProperties.getSoTimeout());
      defaultBuilder.setConnectionRequestTimeout(configStorageProperties.getConnectionRequestTimeout());
      config.setApacheHttpClientBuilder(defaultBuilder);
    }

    // wx host config
    if (null != properties.getHosts() && StringUtils.isNotEmpty(properties.getHosts().getApiHost())) {
      WxMpHostConfig hostConfig = new WxMpHostConfig();
      hostConfig.setApiHost(properties.getHosts().getApiHost());
      hostConfig.setOpenHost(properties.getHosts().getOpenHost());
      hostConfig.setMpHost(properties.getHosts().getMpHost());
      config.setHostConfig(hostConfig);
    }

    return config;
  }
}
