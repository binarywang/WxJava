package com.binarywang.solon.wxjava.store.configuration.services;

import com.binarywang.solon.wxjava.store.properties.WxStoreMultiProperties;
import com.binarywang.solon.wxjava.store.properties.WxStoreMultiRedisProperties;
import com.binarywang.solon.wxjava.store.service.WxStoreMultiServices;
import lombok.RequiredArgsConstructor;
import com.binarywang.wxjava.store.config.impl.WxStoreDefaultConfigImpl;
import com.binarywang.wxjava.store.config.impl.WxStoreRedisConfigImpl;
import me.chanjar.weixin.common.redis.JedisWxRedisOps;
import org.apache.commons.lang3.StringUtils;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Condition;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.core.AppContext;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 自动装配基于 jedis 策略配置
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a> 2024/9/13
 * @author noear
 */
@Configuration
@Condition(
  onProperty = "${"+WxStoreMultiProperties.PREFIX + ".configStorage.type} = jedis",
  onClass = JedisPool.class
)
@RequiredArgsConstructor
public class WxStoreInJedisConfiguration extends AbstractWxStoreConfiguration {
  private final WxStoreMultiProperties wxStoreMultiProperties;
  private final AppContext applicationContext;

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
