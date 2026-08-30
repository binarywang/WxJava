package com.binarywang.spring.starter.wxjava.store.config;


import com.binarywang.spring.starter.wxjava.store.properties.WxStoreProperties;
import lombok.AllArgsConstructor;
import com.binarywang.wxjava.store.api.WxStoreService;
import com.binarywang.wxjava.store.api.impl.WxStoreServiceImpl;
import com.binarywang.wxjava.store.config.WxStoreConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
  @ConditionalOnMissingBean(WxStoreService.class)
  @ConditionalOnBean(WxStoreConfig.class)
  public WxStoreService wxStoreService(WxStoreConfig wxStoreConfig) {
    WxStoreService wxStoreService = new WxStoreServiceImpl();
    wxStoreService.setConfig(wxStoreConfig);
    return wxStoreService;
  }
}
