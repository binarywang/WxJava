package com.binarywang.spring.starter.wxjava.store.config.storage;


import com.binarywang.spring.starter.wxjava.store.properties.WxStoreProperties;
import lombok.RequiredArgsConstructor;
import com.binarywang.wxjava.store.config.WxStoreConfig;
import com.binarywang.wxjava.store.config.impl.WxStoreDefaultConfigImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Configuration
@ConditionalOnProperty(prefix = WxStoreProperties.PREFIX + ".config-storage", name = "type",
  matchIfMissing = true, havingValue = "memory")
@RequiredArgsConstructor
public class WxStoreInMemoryConfigStorageConfiguration extends AbstractWxStoreConfigStorageConfiguration {
  private final WxStoreProperties properties;

  @Bean
  @ConditionalOnMissingBean(WxStoreConfig.class)
  public WxStoreConfig wxStoreConfig() {
    WxStoreDefaultConfigImpl config = new WxStoreDefaultConfigImpl();
    return this.config(config, properties);
  }
}
