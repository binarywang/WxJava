package com.binarywang.solon.wxjava.mp.config.storage;

import com.binarywang.solon.wxjava.mp.properties.RedisProperties;
import com.binarywang.solon.wxjava.mp.properties.WxMpProperties;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.redis.JedisWxRedisOps;
import me.chanjar.weixin.common.redis.WxRedisOps;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.config.impl.WxMpRedisConfigImpl;
import org.apache.commons.lang3.StringUtils;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Condition;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.core.AppContext;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author <a href="https://github.com/buaazyl">zhangyl</a>
 */
@Configuration
@Condition(
  onProperty = "${" + WxMpProperties.PREFIX + ".config-storage.type} = jedis",
  onClass = Jedis.class
)
@RequiredArgsConstructor
public class WxMpInJedisConfigStorageConfiguration extends AbstractWxMpConfigStorageConfiguration {
  private final WxMpProperties properties;
  private final AppContext applicationContext;

  @Bean
  @Condition(onMissingBean = WxMpConfigStorage.class)
  public WxMpConfigStorage wxMpConfigStorage() {
    WxMpRedisConfigImpl config = getWxMpRedisConfigImpl();
    return this.config(config, properties);
  }

  private WxMpRedisConfigImpl getWxMpRedisConfigImpl() {
    RedisProperties redisProperties = properties.getConfigStorage().getRedis();
    JedisPool jedisPool;
    if (redisProperties != null && StringUtils.isNotEmpty(redisProperties.getHost())) {
      jedisPool = applicationContext.getBean("wxMpJedisPool");
    } else {
      jedisPool = applicationContext.getBean(JedisPool.class);
    }
    WxRedisOps redisOps = new JedisWxRedisOps(jedisPool);
    return new WxMpRedisConfigImpl(redisOps, properties.getConfigStorage().getKeyPrefix());
  }

  @Bean
  @Condition(onProperty = "${" + WxMpProperties.PREFIX + ".config-storage.redis.host}")
  public JedisPool wxMpJedisPool() {
    WxMpProperties.ConfigStorage storage = properties.getConfigStorage();
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

    return new JedisPool(config, redis.getHost(), redis.getPort(), redis.getTimeout(), redis.getPassword(),
      redis.getDatabase());
  }
}
