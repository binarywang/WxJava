package com.binarywang.spring.starter.wxjava.mp.config.storage;

import com.binarywang.spring.starter.wxjava.mp.properties.WxMpProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.redis.RedisTemplateWxRedisOps;
import me.chanjar.weixin.common.redis.WxRedisOps;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.config.impl.WxMpRedisConfigImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author <a href="https://github.com/buaazyl">zhangyl</a>
 */
@Slf4j
@Configuration
@ConditionalOnProperty(
  prefix = WxMpProperties.PREFIX + ".config-storage",
  name = "type",
  havingValue = "redistemplate"
)
@ConditionalOnClass(StringRedisTemplate.class)
@RequiredArgsConstructor
public class WxMpInRedisTemplateConfigStorageConfiguration extends AbstractWxMpConfigStorageConfiguration {
  private final WxMpProperties properties;
  private final ApplicationContext applicationContext;

  @Bean
  @ConditionalOnMissingBean(WxMpConfigStorage.class)
  public WxMpConfigStorage wxMpConfigStorage() {
    WxMpRedisConfigImpl config = getWxMpInRedisTemplateConfigStorage();
    return this.config(config, properties);
  }

  private WxMpRedisConfigImpl getWxMpInRedisTemplateConfigStorage() {
    StringRedisTemplate redisTemplate = applicationContext.getBean(StringRedisTemplate.class);
    WxRedisOps redisOps = new RedisTemplateWxRedisOps(redisTemplate);
    return new WxMpRedisConfigImpl(redisOps, properties.getConfigStorage().getKeyPrefix());
  }
}
