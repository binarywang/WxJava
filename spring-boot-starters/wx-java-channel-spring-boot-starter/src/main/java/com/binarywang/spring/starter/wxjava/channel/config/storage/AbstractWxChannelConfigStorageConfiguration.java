package com.binarywang.spring.starter.wxjava.channel.config.storage;

import com.binarywang.spring.starter.wxjava.channel.properties.WxChannelProperties;
import me.chanjar.weixin.channel.config.impl.WxChannelDefaultConfigImpl;
import org.apache.commons.lang3.StringUtils;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
public abstract class AbstractWxChannelConfigStorageConfiguration {

  protected WxChannelDefaultConfigImpl config(WxChannelDefaultConfigImpl config, WxChannelProperties properties) {
    config.setAppid(StringUtils.trimToNull(properties.getAppid()));
    config.setSecret(StringUtils.trimToNull(properties.getSecret()));
    config.setToken(StringUtils.trimToNull(properties.getToken()));
    config.setAesKey(StringUtils.trimToNull(properties.getAesKey()));
    config.setMsgDataFormat(StringUtils.trimToNull(properties.getMsgDataFormat()));
    config.setStableAccessToken(properties.isUseStableAccessToken());

    WxChannelProperties.ConfigStorage configStorageProperties = properties.getConfigStorage();
    config.setHttpProxyHost(configStorageProperties.getHttpProxyHost());
    config.setHttpProxyUsername(configStorageProperties.getHttpProxyUsername());
    config.setHttpProxyPassword(configStorageProperties.getHttpProxyPassword());
    if (configStorageProperties.getHttpProxyPort() != null) {
      config.setHttpProxyPort(configStorageProperties.getHttpProxyPort());
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
