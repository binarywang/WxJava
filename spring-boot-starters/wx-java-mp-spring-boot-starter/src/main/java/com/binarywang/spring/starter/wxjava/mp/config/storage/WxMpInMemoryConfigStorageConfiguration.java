package com.binarywang.spring.starter.wxjava.mp.config.storage;

import com.binarywang.spring.starter.wxjava.mp.properties.WxMpProperties;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="https://github.com/buaazyl">zhangyl</a>
 */
@Configuration
@ConditionalOnProperty(
  prefix = WxMpProperties.PREFIX + ".config-storage",
  name = "type",
  havingValue = "memory",
  matchIfMissing = true
)
@RequiredArgsConstructor
public class WxMpInMemoryConfigStorageConfiguration extends AbstractWxMpConfigStorageConfiguration {
  private final WxMpProperties properties;

  @Bean
  @ConditionalOnMissingBean(WxMpConfigStorage.class)
  public WxMpConfigStorage wxMpConfigStorage() {
    WxMpDefaultConfigImpl config = new WxMpDefaultConfigImpl();
    config(config, properties);
    return config;
  }
}
