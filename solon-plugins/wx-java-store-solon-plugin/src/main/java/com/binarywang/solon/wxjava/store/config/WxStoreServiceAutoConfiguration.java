package com.binarywang.solon.wxjava.store.config;


import com.binarywang.solon.wxjava.store.properties.WxStoreProperties;
import lombok.AllArgsConstructor;
import com.binarywang.wxjava.store.api.WxStoreService;
import com.binarywang.wxjava.store.api.impl.WxStoreServiceImpl;
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
    WxStoreService wxStoreService = new WxStoreServiceImpl();
    wxStoreService.setConfig(wxStoreConfig);
    return wxStoreService;
  }
}
