package com.binarywang.spring.starter.wxjava.store.configuration.services;

import com.binarywang.spring.starter.wxjava.store.properties.WxStoreMultiProperties;
import com.binarywang.spring.starter.wxjava.store.service.WxStoreMultiServices;
import lombok.RequiredArgsConstructor;
import com.binarywang.wxjava.store.config.impl.WxStoreDefaultConfigImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动装配基于内存策略配置
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 * @date 2024/9/13
 */
@Configuration
@ConditionalOnProperty(prefix = WxStoreMultiProperties.PREFIX + ".config-storage", name = "type", havingValue = "memory", matchIfMissing = true)
@RequiredArgsConstructor
public class WxStoreInMemoryConfiguration extends AbstractWxStoreConfiguration {
  private final WxStoreMultiProperties wxStoreMultiProperties;

  @Bean
  public WxStoreMultiServices wxStoreMultiServices() {
    return this.wxStoreMultiServices(wxStoreMultiProperties);
  }

  @Override
  protected WxStoreDefaultConfigImpl wxStoreConfigStorage(WxStoreMultiProperties wxStoreMultiProperties) {
    return this.configInMemory();
  }

  private WxStoreDefaultConfigImpl configInMemory() {
    return new WxStoreDefaultConfigImpl();
  }
}
