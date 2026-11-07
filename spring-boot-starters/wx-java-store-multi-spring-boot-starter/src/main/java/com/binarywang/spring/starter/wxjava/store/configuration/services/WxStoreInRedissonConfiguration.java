package com.binarywang.spring.starter.wxjava.store.configuration.services;

import com.binarywang.spring.starter.wxjava.store.properties.WxStoreMultiProperties;
import com.binarywang.spring.starter.wxjava.store.properties.WxStoreMultiRedisProperties;
import com.binarywang.spring.starter.wxjava.store.service.WxStoreMultiServices;
import lombok.RequiredArgsConstructor;
import com.binarywang.wxjava.store.config.impl.WxStoreDefaultConfigImpl;
import com.binarywang.wxjava.store.config.impl.WxStoreRedissonConfigImpl;
import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动装配基于 redisson 策略配置
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 * @date 2024/9/13
 */
@Configuration
@ConditionalOnProperty(prefix = WxStoreMultiProperties.PREFIX + ".config-storage", name = "type", havingValue = "redisson")
@RequiredArgsConstructor
public class WxStoreInRedissonConfiguration extends AbstractWxStoreConfiguration {
  private final WxStoreMultiProperties wxStoreMultiProperties;
  private final ApplicationContext applicationContext;

  @Bean
  public WxStoreMultiServices wxStoreMultiServices() {
    return this.wxStoreMultiServices(wxStoreMultiProperties);
  }

  @Override
  protected WxStoreDefaultConfigImpl wxStoreConfigStorage(WxStoreMultiProperties wxStoreMultiProperties) {
    return this.configRedisson(wxStoreMultiProperties);
  }

  private WxStoreDefaultConfigImpl configRedisson(WxStoreMultiProperties wxStoreMultiProperties) {
    WxStoreMultiRedisProperties redisProperties = wxStoreMultiProperties.getConfigStorage().getRedis();
    RedissonClient redissonClient;
    if (redisProperties != null && StringUtils.isNotEmpty(redisProperties.getHost())) {
      redissonClient = getRedissonClient(wxStoreMultiProperties);
    } else {
      redissonClient = applicationContext.getBean(RedissonClient.class);
    }
    return new WxStoreRedissonConfigImpl(redissonClient, wxStoreMultiProperties.getConfigStorage().getKeyPrefix());
  }

  private RedissonClient getRedissonClient(WxStoreMultiProperties wxStoreMultiProperties) {
    WxStoreMultiProperties.ConfigStorage storage = wxStoreMultiProperties.getConfigStorage();
    WxStoreMultiRedisProperties redis = storage.getRedis();

    Config config = new Config();
    config.useSingleServer().setAddress("redis://" + redis.getHost() + ":" + redis.getPort()).setDatabase(redis.getDatabase()).setPassword(redis.getPassword());
    config.setTransportMode(TransportMode.NIO);
    return Redisson.create(config);
  }
}
