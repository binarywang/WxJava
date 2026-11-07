package com.binarywang.solon.wxjava.store.config;


import com.binarywang.solon.wxjava.store.properties.WxStoreProperties;
import com.binarywang.solon.wxjava.store.enums.HttpClientType;
import lombok.AllArgsConstructor;
import com.binarywang.wxjava.store.api.WxStoreService;
import com.binarywang.wxjava.store.api.impl.WxStoreServiceHttpClientImpl;
import com.binarywang.wxjava.store.api.impl.WxStoreServiceHttpComponentsImpl;
import com.binarywang.wxjava.store.config.WxStoreConfig;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Condition;
import org.noear.solon.annotation.Configuration;

/**
 * 微信小程序平台相关服务自动注册
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Configuration
@AllArgsConstructor
public class WxStoreServiceAutoConfiguration {
  private final WxStoreProperties properties;

  /**
   * Store Service
   *
   * @return Store Service
   */
  @Bean
  @Condition(onMissingBean=WxStoreService.class, onBean = WxStoreConfig.class)
  public WxStoreService wxStoreService(WxStoreConfig wxStoreConfig) {
    HttpClientType httpClientType = properties.getConfigStorage().getHttpClientType();
    WxStoreService wxStoreService = httpClientType == HttpClientType.HttpClient
      ? new WxStoreServiceHttpClientImpl() : new WxStoreServiceHttpComponentsImpl();
    wxStoreService.setConfig(wxStoreConfig);
    return wxStoreService;
  }
}
