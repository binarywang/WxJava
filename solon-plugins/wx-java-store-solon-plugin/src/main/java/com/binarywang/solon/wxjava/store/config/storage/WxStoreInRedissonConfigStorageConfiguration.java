package com.binarywang.solon.wxjava.store.config.storage;


import com.binarywang.solon.wxjava.store.properties.RedisProperties;
import com.binarywang.solon.wxjava.store.properties.WxStoreProperties;
import lombok.RequiredArgsConstructor;
import com.binarywang.wxjava.store.config.WxStoreConfig;
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
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Configuration
@Condition(
  onProperty = "${"+WxStoreProperties.PREFIX + ".configStorage.type} = redisson",
  onClass = Redisson.class
)
@RequiredArgsConstructor
public class WxStoreInRedissonConfigStorageConfiguration extends AbstractWxStoreConfigStorageConfiguration {
  private final WxStoreProperties properties;
  private final AppContext applicationContext;

  @Bean
  @Condition(onMissingBean=WxStoreConfig.class)
  public WxStoreConfig wxStoreConfig() {
    WxStoreRedissonConfigImpl config = getWxStoreRedissonConfig();
    return this.config(config, properties);
  }

  private WxStoreRedissonConfigImpl getWxStoreRedissonConfig() {
    RedisProperties redisProperties = properties.getConfigStorage().getRedis();
    RedissonClient redissonClient;
    if (redisProperties != null && StringUtils.isNotEmpty(redisProperties.getHost())) {
      redissonClient = getRedissonClient();
    } else {
      redissonClient = applicationContext.getBean(RedissonClient.class);
    }
    return new WxStoreRedissonConfigImpl(redissonClient, properties.getConfigStorage().getKeyPrefix());
  }

  private RedissonClient getRedissonClient() {
    WxStoreProperties.ConfigStorage storage = properties.getConfigStorage();
    RedisProperties redis = storage.getRedis();

    Config config = new Config();
    config.useSingleServer()
      .setAddress("redis://" + redis.getHost() + ":" + redis.getPort())
      .setDatabase(redis.getDatabase())
      .setPassword(redis.getPassword());
    config.setTransportMode(TransportMode.NIO);
    return Redisson.create(config);
  }
}
