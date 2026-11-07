package com.binarywang.solon.wxjava.store.config.storage;


import com.binarywang.solon.wxjava.store.properties.RedisProperties;
import com.binarywang.solon.wxjava.store.properties.WxStoreProperties;
import lombok.RequiredArgsConstructor;
import com.binarywang.wxjava.store.config.WxStoreConfig;
import com.binarywang.wxjava.store.config.impl.WxStoreRedisConfigImpl;
import me.chanjar.weixin.common.redis.JedisWxRedisOps;
import me.chanjar.weixin.common.redis.WxRedisOps;
import org.apache.commons.lang3.StringUtils;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Condition;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.core.AppContext;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @author noear
 */
@Configuration
@Condition(
  onProperty = "${"+WxStoreProperties.PREFIX + ".configStorage.type} = jedis",
  onClass = JedisPool.class
)
@RequiredArgsConstructor
public class WxStoreInJedisConfigStorageConfiguration extends AbstractWxStoreConfigStorageConfiguration {
  private final WxStoreProperties properties;
  private final AppContext applicationContext;

  @Bean
  @Condition(onMissingBean=WxStoreConfig.class)
  public WxStoreConfig wxStoreConfig() {
    WxStoreRedisConfigImpl config = getWxStoreRedisConfig();
    return this.config(config, properties);
  }

  private WxStoreRedisConfigImpl getWxStoreRedisConfig() {
    RedisProperties redisProperties = properties.getConfigStorage().getRedis();
    JedisPool jedisPool;
    if (redisProperties != null && StringUtils.isNotEmpty(redisProperties.getHost())) {
      jedisPool = getJedisPool();
    } else {
      jedisPool = applicationContext.getBean(JedisPool.class);
    }
    WxRedisOps redisOps = new JedisWxRedisOps(jedisPool);
    return new WxStoreRedisConfigImpl(redisOps, properties.getConfigStorage().getKeyPrefix());
  }

  private JedisPool getJedisPool() {
    WxStoreProperties.ConfigStorage storage = properties.getConfigStorage();
    RedisProperties redis = storage.getRedis();

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
