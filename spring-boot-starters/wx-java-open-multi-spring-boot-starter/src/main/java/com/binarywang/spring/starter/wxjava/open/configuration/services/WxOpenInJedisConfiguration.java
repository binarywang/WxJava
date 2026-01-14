package com.binarywang.spring.starter.wxjava.open.configuration.services;

import com.binarywang.spring.starter.wxjava.open.properties.WxOpenMultiProperties;
import com.binarywang.spring.starter.wxjava.open.properties.WxOpenMultiRedisProperties;
import com.binarywang.spring.starter.wxjava.open.service.WxOpenMultiServices;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.open.api.impl.WxOpenInMemoryConfigStorage;
import me.chanjar.weixin.open.api.impl.WxOpenInRedisConfigStorage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


/**
 * 自动装配基于 jedis 策略配置
 *
 * @author Binary Wang
 */
@Configuration
@ConditionalOnProperty(
  prefix = WxOpenMultiProperties.PREFIX + ".config-storage", name = "type", havingValue = "JEDIS"
)
@ConditionalOnClass({JedisPool.class, JedisPoolConfig.class})
@RequiredArgsConstructor
public class WxOpenInJedisConfiguration extends AbstractWxOpenConfiguration {
  private final WxOpenMultiProperties wxOpenMultiProperties;
  private final ApplicationContext applicationContext;

  @Bean
  public WxOpenMultiServices wxOpenMultiServices() {
    return this.wxOpenMultiServices(wxOpenMultiProperties);
  }

  @Override
  protected WxOpenInMemoryConfigStorage wxOpenConfigStorage(WxOpenMultiProperties wxOpenMultiProperties) {
    return this.configJedis(wxOpenMultiProperties);
  }

  private WxOpenInRedisConfigStorage configJedis(WxOpenMultiProperties wxOpenMultiProperties) {
    WxOpenMultiRedisProperties redisProperties = wxOpenMultiProperties.getConfigStorage().getRedis();
    JedisPool jedisPool;
    if (redisProperties != null && StringUtils.isNotEmpty(redisProperties.getHost())) {
      jedisPool = getJedisPool(wxOpenMultiProperties);
    } else {
      jedisPool = applicationContext.getBean(JedisPool.class);
    }
    return new WxOpenInRedisConfigStorage(jedisPool, wxOpenMultiProperties.getConfigStorage().getKeyPrefix());
  }

  private JedisPool getJedisPool(WxOpenMultiProperties wxOpenMultiProperties) {
    WxOpenMultiProperties.ConfigStorage storage = wxOpenMultiProperties.getConfigStorage();
    WxOpenMultiRedisProperties redis = storage.getRedis();

    JedisPoolConfig config = new JedisPoolConfig();
    if (redis.getMaxActive() != null) {
      config.setMaxTotal(redis.getMaxActive());
    }
    if (redis.getMaxIdle() != null) {
      config.setMaxIdle(redis.getMaxIdle());
    }
    if (redis.getMaxWaitMillis() != null) {
      config.setMaxWaitMillis(redis.getMaxWaitMillis());
    }
    if (redis.getMinIdle() != null) {
      config.setMinIdle(redis.getMinIdle());
    }
    config.setTestOnBorrow(true);
    config.setTestWhileIdle(true);

    return new JedisPool(config, redis.getHost(), redis.getPort(),
      redis.getTimeout(), redis.getPassword(), redis.getDatabase());
  }
}
