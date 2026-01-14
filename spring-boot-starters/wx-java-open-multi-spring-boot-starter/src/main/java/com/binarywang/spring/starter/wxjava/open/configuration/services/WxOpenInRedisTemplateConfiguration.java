package com.binarywang.spring.starter.wxjava.open.configuration.services;

import com.binarywang.spring.starter.wxjava.open.properties.WxOpenMultiProperties;
import com.binarywang.spring.starter.wxjava.open.service.WxOpenMultiServices;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.open.api.impl.WxOpenInMemoryConfigStorage;
import me.chanjar.weixin.open.api.impl.WxOpenInRedisTemplateConfigStorage;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 自动装配基于 redis template 策略配置
 *
 * @author Binary Wang
 */
@Configuration
@ConditionalOnProperty(
  prefix = WxOpenMultiProperties.PREFIX + ".config-storage", name = "type", havingValue = "redistemplate"
)
@ConditionalOnClass(StringRedisTemplate.class)
@RequiredArgsConstructor
public class WxOpenInRedisTemplateConfiguration extends AbstractWxOpenConfiguration {
  private final WxOpenMultiProperties wxOpenMultiProperties;
  private final ApplicationContext applicationContext;

  @Bean
  public WxOpenMultiServices wxOpenMultiServices() {
    return this.wxOpenMultiServices(wxOpenMultiProperties);
  }

  @Override
  protected WxOpenInMemoryConfigStorage wxOpenConfigStorage(WxOpenMultiProperties wxOpenMultiProperties) {
    return this.configRedisTemplate();
  }

  private WxOpenInRedisTemplateConfigStorage configRedisTemplate() {
    StringRedisTemplate redisTemplate = applicationContext.getBean(StringRedisTemplate.class);
    return new WxOpenInRedisTemplateConfigStorage(redisTemplate, wxOpenMultiProperties.getConfigStorage().getKeyPrefix());
  }
}
