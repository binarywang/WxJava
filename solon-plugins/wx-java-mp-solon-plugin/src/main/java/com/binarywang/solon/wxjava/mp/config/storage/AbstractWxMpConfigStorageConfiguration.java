package com.binarywang.solon.wxjava.mp.config.storage;

import com.binarywang.solon.wxjava.mp.properties.WxMpProperties;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;

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
    return config;
  }
}
