package com.binarywang.solon.wxjava.miniapp.config.storage;

import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import com.binarywang.solon.wxjava.miniapp.properties.WxMaProperties;
import org.apache.commons.lang3.StringUtils;

/**
 * @author yl TaoYu
 */
public abstract class AbstractWxMaConfigStorageConfiguration {

  protected WxMaDefaultConfigImpl config(WxMaDefaultConfigImpl config, WxMaProperties properties) {
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
