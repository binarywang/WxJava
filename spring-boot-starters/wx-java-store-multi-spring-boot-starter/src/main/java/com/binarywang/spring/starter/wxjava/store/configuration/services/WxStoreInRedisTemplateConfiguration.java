package com.binarywang.spring.starter.wxjava.store.configuration.services;

import com.binarywang.spring.starter.wxjava.store.properties.WxStoreMultiProperties;
import com.binarywang.spring.starter.wxjava.store.service.WxStoreMultiServices;
import lombok.RequiredArgsConstructor;
import com.binarywang.wxjava.store.config.impl.WxStoreDefaultConfigImpl;
import com.binarywang.wxjava.store.config.impl.WxStoreRedisConfigImpl;
import me.chanjar.weixin.common.redis.RedisTemplateWxRedisOps;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 自动装配基于 redisTemplate 策略配置
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 * @date 2024/9/13
 */
@Configuration
@ConditionalOnProperty(prefix = WxStoreMultiProperties.PREFIX + ".config-storage", name = "type", havingValue = "redis_template")
@RequiredArgsConstructor
public class WxStoreInRedisTemplateConfiguration extends AbstractWxStoreConfiguration {
  private final WxStoreMultiProperties wxStoreMultiProperties;
  private final ApplicationContext applicationContext;

  @Bean
  public WxStoreMultiServices wxStoreMultiServices() {
    return this.wxStoreMultiServices(wxStoreMultiProperties);
  }

  @Override
  protected WxStoreDefaultConfigImpl wxStoreConfigStorage(WxStoreMultiProperties wxStoreMultiProperties) {
    return this.configRedisTemplate(wxStoreMultiProperties);
  }

  private WxStoreDefaultConfigImpl configRedisTemplate(WxStoreMultiProperties wxStoreMultiProperties) {
    StringRedisTemplate redisTemplate = applicationContext.getBean(StringRedisTemplate.class);
    return new WxStoreRedisConfigImpl(new RedisTemplateWxRedisOps(redisTemplate), wxStoreMultiProperties.getConfigStorage().getKeyPrefix());
  }
}
