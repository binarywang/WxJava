package com.binarywang.spring.starter.wxjava.store.configuration.services;

import com.binarywang.spring.starter.wxjava.store.properties.WxStoreMultiProperties;
import com.binarywang.spring.starter.wxjava.store.properties.WxStoreMultiRedisProperties;
import com.binarywang.spring.starter.wxjava.store.service.WxStoreMultiServices;
import lombok.RequiredArgsConstructor;
import com.binarywang.wxjava.store.config.impl.WxStoreDefaultConfigImpl;
import com.binarywang.wxjava.store.config.impl.WxStoreRedisConfigImpl;
import me.chanjar.weixin.common.redis.JedisWxRedisOps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 自动装配基于 jedis 策略配置
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 * @date 2024/9/13
 */
@Configuration
@ConditionalOnProperty(prefix = WxStoreMultiProperties.PREFIX + ".config-storage", name = "type", havingValue = "jedis")
@RequiredArgsConstructor
public class WxStoreInJedisConfiguration extends AbstractWxStoreConfiguration {
  private final WxStoreMultiProperties wxStoreMultiProperties;
  private final ApplicationContext applicationContext;

  @Bean
  public WxStoreMultiServices wxStoreMultiServices() {
    return this.wxStoreMultiServices(wxStoreMultiProperties);
  }

  @Override
  protected WxStoreDefaultConfigImpl wxStoreConfigStorage(WxStoreMultiProperties wxStoreMultiProperties) {
    return this.configRedis(wxStoreMultiProperties);
  }

  private WxStoreDefaultConfigImpl configRedis(WxStoreMultiProperties wxStoreMultiProperties) {
    WxStoreMultiRedisProperties wxStoreMultiRedisProperties = wxStoreMultiProperties.getConfigStorage().getRedis();
    JedisPool jedisPool;
    if (wxStoreMultiRedisProperties != null && StringUtils.isNotEmpty(wxStoreMultiRedisProperties.getHost())) {
      jedisPool = getJedisPool(wxStoreMultiProperties);
    } else {
      jedisPool = applicationContext.getBean(JedisPool.class);
    }
    return new WxStoreRedisConfigImpl(new JedisWxRedisOps(jedisPool), wxStoreMultiProperties.getConfigStorage().getKeyPrefix());
  }

  private JedisPool getJedisPool(WxStoreMultiProperties wxStoreMultiProperties) {
    WxStoreMultiProperties.ConfigStorage storage = wxStoreMultiProperties.getConfigStorage();
    WxStoreMultiRedisProperties redis = storage.getRedis();

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

    return new JedisPool(config, redis.getHost(), redis.getPort(), redis.getTimeout(), redis.getPassword(), redis.getDatabase());
  }
}
