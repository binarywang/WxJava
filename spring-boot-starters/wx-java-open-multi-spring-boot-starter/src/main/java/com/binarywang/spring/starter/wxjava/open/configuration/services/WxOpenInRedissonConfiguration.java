package com.binarywang.spring.starter.wxjava.open.configuration.services;

import com.binarywang.spring.starter.wxjava.open.properties.WxOpenMultiProperties;
import com.binarywang.spring.starter.wxjava.open.properties.WxOpenMultiRedisProperties;
import com.binarywang.spring.starter.wxjava.open.service.WxOpenMultiServices;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.open.api.impl.WxOpenInMemoryConfigStorage;
import me.chanjar.weixin.open.api.impl.WxOpenInRedissonConfigStorage;
import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动装配基于 redisson 策略配置
 *
 * @author Binary Wang
 */
@Configuration
@ConditionalOnProperty(
  prefix = WxOpenMultiProperties.PREFIX + ".config-storage", name = "type", havingValue = "redisson"
)
@ConditionalOnClass({Redisson.class, RedissonClient.class})
@RequiredArgsConstructor
public class WxOpenInRedissonConfiguration extends AbstractWxOpenConfiguration {
  private final WxOpenMultiProperties wxOpenMultiProperties;
  private final ApplicationContext applicationContext;

  @Bean
  public WxOpenMultiServices wxOpenMultiServices() {
    return this.wxOpenMultiServices(wxOpenMultiProperties);
  }

  @Override
  protected WxOpenInMemoryConfigStorage wxOpenConfigStorage(WxOpenMultiProperties wxOpenMultiProperties) {
    return this.configRedisson(wxOpenMultiProperties);
  }

  private WxOpenInRedissonConfigStorage configRedisson(WxOpenMultiProperties wxOpenMultiProperties) {
    WxOpenMultiRedisProperties redisProperties = wxOpenMultiProperties.getConfigStorage().getRedis();
    RedissonClient redissonClient;
    if (redisProperties != null && StringUtils.isNotEmpty(redisProperties.getHost())) {
      redissonClient = getRedissonClient(wxOpenMultiProperties);
    } else {
      redissonClient = applicationContext.getBean(RedissonClient.class);
    }
    return new WxOpenInRedissonConfigStorage(redissonClient, wxOpenMultiProperties.getConfigStorage().getKeyPrefix());
  }

  private RedissonClient getRedissonClient(WxOpenMultiProperties wxOpenMultiProperties) {
    WxOpenMultiProperties.ConfigStorage storage = wxOpenMultiProperties.getConfigStorage();
    WxOpenMultiRedisProperties redis = storage.getRedis();

    Config config = new Config();
    config.useSingleServer()
      .setAddress("redis://" + redis.getHost() + ":" + redis.getPort())
      .setDatabase(redis.getDatabase())
      .setPassword(redis.getPassword());
    config.setTransportMode(TransportMode.NIO);
    return Redisson.create(config);
  }
}
