package com.binarywang.spring.starter.wxjava.store.config.storage;

import com.binarywang.spring.starter.wxjava.store.properties.WxStoreProperties;
import lombok.RequiredArgsConstructor;
import com.binarywang.wxjava.store.config.WxStoreConfig;
import com.binarywang.wxjava.store.config.impl.WxStoreRedisConfigImpl;
import me.chanjar.weixin.common.redis.RedisTemplateWxRedisOps;
import me.chanjar.weixin.common.redis.WxRedisOps;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Configuration
@ConditionalOnProperty(prefix = WxStoreProperties.PREFIX + ".config-storage", name = "type", havingValue = "redis_template")
@ConditionalOnClass(StringRedisTemplate.class)
@RequiredArgsConstructor
public class WxStoreInRedisTemplateConfigStorageConfiguration extends AbstractWxStoreConfigStorageConfiguration {
  private final WxStoreProperties properties;
  private final ApplicationContext applicationContext;

  @Bean
  @ConditionalOnMissingBean(WxStoreConfig.class)
  public WxStoreConfig wxStoreConfig() {
    WxStoreRedisConfigImpl config = getWxStoreInRedisTemplateConfig();
    return this.config(config, properties);
  }

  private WxStoreRedisConfigImpl getWxStoreInRedisTemplateConfig() {
    StringRedisTemplate redisTemplate = applicationContext.getBean(StringRedisTemplate.class);
    WxRedisOps redisOps = new RedisTemplateWxRedisOps(redisTemplate);
    return new WxStoreRedisConfigImpl(redisOps, properties.getConfigStorage().getKeyPrefix());
  }
}
