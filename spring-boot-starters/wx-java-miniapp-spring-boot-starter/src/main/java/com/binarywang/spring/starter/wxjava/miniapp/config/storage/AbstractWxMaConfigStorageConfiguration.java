package com.binarywang.spring.starter.wxjava.miniapp.config.storage;

import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import com.binarywang.spring.starter.wxjava.miniapp.properties.WxMaProperties;
import me.chanjar.weixin.common.util.http.apache.ApacheHttpClientBuilder;
import me.chanjar.weixin.common.util.http.apache.DefaultApacheHttpClientBuilder;
import org.apache.commons.lang3.StringUtils;

/**
 * @author yl TaoYu
 */
public abstract class AbstractWxMaConfigStorageConfiguration {

  protected WxMaDefaultConfigImpl config(WxMaDefaultConfigImpl config, WxMaProperties properties) {
    WxMaProperties.ConfigStorage storage = properties.getConfigStorage();
    config.setAppid(StringUtils.trimToNull(properties.getAppid()));
    config.setSecret(StringUtils.trimToNull(properties.getSecret()));
    config.setToken(StringUtils.trimToNull(properties.getToken()));
    config.setAesKey(StringUtils.trimToNull(properties.getAesKey()));
    config.setMsgDataFormat(StringUtils.trimToNull(properties.getMsgDataFormat()));
    config.useStableAccessToken(properties.isUseStableAccessToken());

    WxMaProperties.ConfigStorage configStorageProperties = properties.getConfigStorage();
    config.setHttpProxyHost(configStorageProperties.getHttpProxyHost());
    config.setHttpProxyUsername(configStorageProperties.getHttpProxyUsername());
    config.setHttpProxyPassword(configStorageProperties.getHttpProxyPassword());
    if (configStorageProperties.getHttpProxyPort() != null) {
      config.setHttpProxyPort(configStorageProperties.getHttpProxyPort());
    }

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

    int maxRetryTimes = configStorageProperties.getMaxRetryTimes();
    if (configStorageProperties.getMaxRetryTimes() < 0) {
      maxRetryTimes = 0;
    }
    int retrySleepMillis = configStorageProperties.getRetrySleepMillis();
    if (retrySleepMillis < 0) {
      retrySleepMillis = 1000;
    }
    config.setRetrySleepMillis(retrySleepMillis);
    config.setMaxRetryTimes(maxRetryTimes);
    return config;
  }
}
