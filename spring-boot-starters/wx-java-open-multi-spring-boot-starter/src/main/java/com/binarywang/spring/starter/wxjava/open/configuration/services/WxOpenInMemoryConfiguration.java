package com.binarywang.spring.starter.wxjava.open.configuration.services;

import com.binarywang.spring.starter.wxjava.open.properties.WxOpenMultiProperties;
import com.binarywang.spring.starter.wxjava.open.service.WxOpenMultiServices;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.open.api.impl.WxOpenInMemoryConfigStorage;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动装配基于内存策略配置
 *
 * @author someone
 */
@Configuration
@ConditionalOnProperty(
  prefix = WxOpenMultiProperties.PREFIX + ".config-storage", name = "type", havingValue = "MEMORY", matchIfMissing = true
)
@RequiredArgsConstructor
public class WxOpenInMemoryConfiguration extends AbstractWxOpenConfiguration {
  private final WxOpenMultiProperties wxOpenMultiProperties;

  @Bean
  public WxOpenMultiServices wxOpenMultiServices() {
    return this.wxOpenMultiServices(wxOpenMultiProperties);
  }

  @Override
  protected WxOpenInMemoryConfigStorage wxOpenConfigStorage(WxOpenMultiProperties wxOpenMultiProperties) {
    return new WxOpenInMemoryConfigStorage();
  }
}
