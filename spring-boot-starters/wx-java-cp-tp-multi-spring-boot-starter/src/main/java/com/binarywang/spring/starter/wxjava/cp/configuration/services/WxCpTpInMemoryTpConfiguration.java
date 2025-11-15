package com.binarywang.spring.starter.wxjava.cp.configuration.services;

import com.binarywang.spring.starter.wxjava.cp.properties.WxCpTpMultiProperties;
import com.binarywang.spring.starter.wxjava.cp.service.WxCpTpMultiServices;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.cp.config.impl.WxCpDefaultConfigImpl;
import me.chanjar.weixin.cp.config.impl.WxCpTpDefaultConfigImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动装配基于内存策略配置
 *
 * @author yl
 * created on 2023/10/16
 */
@Configuration
@ConditionalOnProperty(
  prefix = WxCpTpMultiProperties.PREFIX + ".config-storage", name = "type", havingValue = "memory", matchIfMissing = true
)
@RequiredArgsConstructor
public class WxCpTpInMemoryTpConfiguration extends AbstractWxCpTpConfiguration {
  private final WxCpTpMultiProperties wxCpTpMultiProperties;

  @Bean
  public WxCpTpMultiServices wxCpMultiServices() {
    return this.wxCpMultiServices(wxCpTpMultiProperties,null);
  }

  @Override
  protected WxCpTpDefaultConfigImpl wxCpTpConfigStorage(WxCpTpMultiProperties wxCpTpMultiProperties) {
    return this.configInMemory();
  }

  private WxCpTpDefaultConfigImpl configInMemory() {
    return new WxCpTpDefaultConfigImpl();
  }
}
