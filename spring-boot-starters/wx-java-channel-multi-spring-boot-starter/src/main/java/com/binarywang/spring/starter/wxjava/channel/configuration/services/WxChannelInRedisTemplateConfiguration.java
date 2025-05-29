package com.binarywang.spring.starter.wxjava.channel.configuration.services;

import com.binarywang.spring.starter.wxjava.channel.properties.WxChannelMultiProperties;
import com.binarywang.spring.starter.wxjava.channel.service.WxChannelMultiServices;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.channel.config.impl.WxChannelDefaultConfigImpl;
import me.chanjar.weixin.channel.config.impl.WxChannelRedisConfigImpl;
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
@ConditionalOnProperty(prefix = WxChannelMultiProperties.PREFIX + ".config-storage", name = "type", havingValue = "redis_template")
@RequiredArgsConstructor
public class WxChannelInRedisTemplateConfiguration extends AbstractWxChannelConfiguration {
  private final WxChannelMultiProperties wxChannelMultiProperties;
  private final ApplicationContext applicationContext;

  @Bean
  public WxChannelMultiServices wxChannelMultiServices() {
    return this.wxChannelMultiServices(wxChannelMultiProperties);
  }

  @Override
  protected WxChannelDefaultConfigImpl wxChannelConfigStorage(WxChannelMultiProperties wxChannelMultiProperties) {
    return this.configRedisTemplate(wxChannelMultiProperties);
  }

  private WxChannelDefaultConfigImpl configRedisTemplate(WxChannelMultiProperties wxChannelMultiProperties) {
    StringRedisTemplate redisTemplate = applicationContext.getBean(StringRedisTemplate.class);
    return new WxChannelRedisConfigImpl(new RedisTemplateWxRedisOps(redisTemplate), wxChannelMultiProperties.getConfigStorage().getKeyPrefix());
  }
}
