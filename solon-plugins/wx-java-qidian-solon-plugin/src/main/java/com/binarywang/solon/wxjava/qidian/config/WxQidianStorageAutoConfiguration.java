package com.binarywang.solon.wxjava.qidian.config;

import com.binarywang.solon.wxjava.qidian.enums.StorageType;
import com.binarywang.solon.wxjava.qidian.properties.RedisProperties;
import com.binarywang.solon.wxjava.qidian.properties.WxQidianProperties;
import com.google.common.collect.Sets;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.redis.JedisWxRedisOps;
import me.chanjar.weixin.common.redis.WxRedisOps;
import me.chanjar.weixin.qidian.bean.WxQidianHostConfig;
import me.chanjar.weixin.qidian.config.WxQidianConfigStorage;
import me.chanjar.weixin.qidian.config.impl.WxQidianDefaultConfigImpl;
import me.chanjar.weixin.qidian.config.impl.WxQidianRedisConfigImpl;
import org.apache.commons.lang3.StringUtils;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Condition;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.annotation.Inject;
import org.noear.solon.core.AppContext;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.util.Pool;

import java.time.Duration;
import java.util.Set;

/**
 * 腾讯企点存储策略自动配置.
 *
 * @author alegria
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class WxQidianStorageAutoConfiguration {
  private final AppContext applicationContext;

  private final WxQidianProperties wxQidianProperties;

  @Inject("${wx.mp.config-storage.redis.host:")
  private String redisHost;

  @Inject("${wx.mp.configStorage.redis.host:")
  private String redisHost2;

  @Bean
  @Condition(onMissingBean=WxQidianConfigStorage.class)
  public WxQidianConfigStorage wxQidianConfigStorage() {
    StorageType type = wxQidianProperties.getConfigStorage().getType();
    WxQidianConfigStorage config;
    switch (type) {
      case Jedis:
        config = jedisConfigStorage();
        break;
      default:
        config = defaultConfigStorage();
        break;
    }
    // wx host config
    if (null != wxQidianProperties.getHosts() && StringUtils.isNotEmpty(wxQidianProperties.getHosts().getApiHost())) {
      WxQidianHostConfig hostConfig = new WxQidianHostConfig();
      hostConfig.setApiHost(wxQidianProperties.getHosts().getApiHost());
      hostConfig.setQidianHost(wxQidianProperties.getHosts().getQidianHost());
      hostConfig.setOpenHost(wxQidianProperties.getHosts().getOpenHost());
      config.setHostConfig(hostConfig);
    }
    return config;
  }

  private WxQidianConfigStorage defaultConfigStorage() {
    WxQidianDefaultConfigImpl config = new WxQidianDefaultConfigImpl();
    setWxMpInfo(config);
    return config;
  }

  private WxQidianConfigStorage jedisConfigStorage() {
    Pool<Jedis> jedisPool;
    if (StringUtils.isNotEmpty(redisHost) || StringUtils.isNotEmpty(redisHost2)) {
      jedisPool = getJedisPool();
    } else {
      jedisPool = applicationContext.getBean(JedisPool.class);
    }
    WxRedisOps redisOps = new JedisWxRedisOps(jedisPool);
    WxQidianRedisConfigImpl wxQidianRedisConfig = new WxQidianRedisConfigImpl(redisOps,
        wxQidianProperties.getConfigStorage().getKeyPrefix());
    setWxMpInfo(wxQidianRedisConfig);
    return wxQidianRedisConfig;
  }

  private void setWxMpInfo(WxQidianDefaultConfigImpl config) {
    WxQidianProperties properties = wxQidianProperties;
    WxQidianProperties.ConfigStorage configStorageProperties = properties.getConfigStorage();
    config.setAppId(properties.getAppId());
    config.setSecret(properties.getSecret());
    config.setToken(properties.getToken());
    config.setAesKey(properties.getAesKey());

    config.setHttpProxyHost(configStorageProperties.getHttpProxyHost());
    config.setHttpProxyUsername(configStorageProperties.getHttpProxyUsername());
    config.setHttpProxyPassword(configStorageProperties.getHttpProxyPassword());
    if (configStorageProperties.getHttpProxyPort() != null) {
      config.setHttpProxyPort(configStorageProperties.getHttpProxyPort());
    }
  }

  private Pool<Jedis> getJedisPool() {
    WxQidianProperties.ConfigStorage storage = wxQidianProperties.getConfigStorage();
    RedisProperties redis = storage.getRedis();

    JedisPoolConfig config = new JedisPoolConfig();
    if (redis.getMaxActive() != null) {
      config.setMaxTotal(redis.getMaxActive());
    }
    if (redis.getMaxIdle() != null) {
      config.setMaxIdle(redis.getMaxIdle());
    }
    if (redis.getMaxWaitMillis() != null) {
      config.setMaxWait(Duration.ofMillis(redis.getMaxWaitMillis()));
    }
    if (redis.getMinIdle() != null) {
      config.setMinIdle(redis.getMinIdle());
    }
    config.setTestOnBorrow(true);
    config.setTestWhileIdle(true);
    if (StringUtils.isNotEmpty(redis.getSentinelIps())) {

      Set<String> sentinels = Sets.newHashSet(redis.getSentinelIps().split(","));
      return new JedisSentinelPool(redis.getSentinelName(), sentinels,config);
    }

    return new JedisPool(config, redis.getHost(), redis.getPort(), redis.getTimeout(), redis.getPassword(),
        redis.getDatabase());
  }
}
