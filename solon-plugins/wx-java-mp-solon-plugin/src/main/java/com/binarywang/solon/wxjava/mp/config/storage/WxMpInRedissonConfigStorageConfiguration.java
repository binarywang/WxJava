package com.binarywang.solon.wxjava.mp.config.storage;

import com.binarywang.solon.wxjava.mp.properties.RedisProperties;
import com.binarywang.solon.wxjava.mp.properties.WxMpProperties;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.config.impl.WxMpRedissonConfigImpl;
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
 * @author <a href="https://github.com/buaazyl">zhangyl</a>
 */
@Configuration
@Condition(
  onProperty = "${" + WxMpProperties.PREFIX + ".config-storage.type} = redisson",
  onClass = Redisson.class
)
@RequiredArgsConstructor
public class WxMpInRedissonConfigStorageConfiguration extends AbstractWxMpConfigStorageConfiguration {
  private final WxMpProperties properties;
  private final AppContext applicationContext;

  @Bean
  @Condition(onMissingBean = WxMpConfigStorage.class)
  public WxMpConfigStorage wxMaConfig() {
    WxMpRedissonConfigImpl config = getWxMpInRedissonConfigStorage();
    return this.config(config, properties);
  }

  private WxMpRedissonConfigImpl getWxMpInRedissonConfigStorage() {
    RedisProperties redisProperties = properties.getConfigStorage().getRedis();
    RedissonClient redissonClient;
    if (redisProperties != null && StringUtils.isNotEmpty(redisProperties.getHost())) {
      redissonClient = applicationContext.getBean("wxMpRedissonClient");
    } else {
      redissonClient = applicationContext.getBean(RedissonClient.class);
    }
    return new WxMpRedissonConfigImpl(redissonClient, properties.getConfigStorage().getKeyPrefix());
  }

  @Bean
  @Condition(onProperty = "${" + WxMpProperties.PREFIX + ".config-storage.redis.host}")
  public RedissonClient wxMpRedissonClient() {
    WxMpProperties.ConfigStorage storage = properties.getConfigStorage();
    RedisProperties redis = storage.getRedis();

    Config config = new Config();
    config.useSingleServer()
      .setAddress("redis://" + redis.getHost() + ":" + redis.getPort())
      .setDatabase(redis.getDatabase());
    if (StringUtils.isNotBlank(redis.getPassword())) {
      config.useSingleServer().setPassword(redis.getPassword());
    }
    config.setTransportMode(TransportMode.NIO);
    return Redisson.create(config);
  }
}
