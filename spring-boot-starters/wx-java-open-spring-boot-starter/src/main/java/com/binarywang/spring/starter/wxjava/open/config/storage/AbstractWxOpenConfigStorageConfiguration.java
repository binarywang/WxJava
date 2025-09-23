package com.binarywang.spring.starter.wxjava.open.config.storage;

import com.binarywang.spring.starter.wxjava.open.properties.WxOpenProperties;
import me.chanjar.weixin.common.util.http.apache.ApacheHttpClientBuilder;
import me.chanjar.weixin.common.util.http.apache.DefaultApacheHttpClientBuilder;
import me.chanjar.weixin.open.api.impl.WxOpenInMemoryConfigStorage;
import org.apache.commons.lang3.StringUtils;

/**
 * @author yl
 */
public abstract class AbstractWxOpenConfigStorageConfiguration {

  protected WxOpenInMemoryConfigStorage config(WxOpenInMemoryConfigStorage config, WxOpenProperties properties) {
    WxOpenProperties.ConfigStorage storage = properties.getConfigStorage();
    config.setWxOpenInfo(properties.getAppId(), properties.getSecret(), properties.getToken(), properties.getAesKey());
    config.setHttpProxyHost(storage.getHttpProxyHost());
    config.setHttpProxyUsername(storage.getHttpProxyUsername());
    config.setHttpProxyPassword(storage.getHttpProxyPassword());
    Integer httpProxyPort = storage.getHttpProxyPort();
    if (httpProxyPort != null) {
      config.setHttpProxyPort(httpProxyPort);
    }
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
    
    // 设置URL配置
    config.setApiHostUrl(StringUtils.trimToNull(properties.getApiHostUrl()));
    config.setAccessTokenUrl(StringUtils.trimToNull(properties.getAccessTokenUrl()));
    
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
    
    return config;
  }
}
