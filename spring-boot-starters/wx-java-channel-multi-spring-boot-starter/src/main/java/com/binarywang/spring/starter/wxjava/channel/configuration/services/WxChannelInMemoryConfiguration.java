package com.binarywang.spring.starter.wxjava.channel.configuration.services;

import com.binarywang.spring.starter.wxjava.channel.properties.WxChannelMultiProperties;
import com.binarywang.spring.starter.wxjava.channel.service.WxChannelMultiServices;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.channel.config.impl.WxChannelDefaultConfigImpl;
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
@ConditionalOnProperty(prefix = WxChannelMultiProperties.PREFIX + ".config-storage", name = "type", havingValue = "memory", matchIfMissing = true)
@RequiredArgsConstructor
public class WxChannelInMemoryConfiguration extends AbstractWxChannelConfiguration {
  private final WxChannelMultiProperties wxChannelMultiProperties;

  @Bean
  public WxChannelMultiServices wxChannelMultiServices() {
    return this.wxChannelMultiServices(wxChannelMultiProperties);
  }

  @Override
  protected WxChannelDefaultConfigImpl wxChannelConfigStorage(WxChannelMultiProperties wxChannelMultiProperties) {
    return this.configInMemory();
  }

  private WxChannelDefaultConfigImpl configInMemory() {
    return new WxChannelDefaultConfigImpl();
  }
}
