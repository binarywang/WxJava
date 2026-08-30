package com.binarywang.solon.wxjava.store.configuration.services;

import com.binarywang.solon.wxjava.store.properties.WxStoreMultiProperties;
import com.binarywang.solon.wxjava.store.properties.WxStoreMultiRedisProperties;
import com.binarywang.solon.wxjava.store.service.WxStoreMultiServices;
import lombok.RequiredArgsConstructor;
import com.binarywang.wxjava.store.config.impl.WxStoreDefaultConfigImpl;
import com.binarywang.wxjava.store.config.impl.WxStoreRedissonConfigImpl;
import org.apache.commons.lang3.StringUtils;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Condition;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.core.AppContext;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;

/**
 * 自动装配基于 redisson 策略配置
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a> 2024/9/13
 * @author noear
 */
@Configuration
@Condition(
  onProperty = "${"+WxStoreMultiProperties.PREFIX + ".configStorage.type} = redisson",
  onClass = Redisson.class
)
@RequiredArgsConstructor
public class WxStoreInRedissonConfiguration extends AbstractWxStoreConfiguration {
  private final WxStoreMultiProperties wxStoreMultiProperties;
  private final AppContext applicationContext;

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
