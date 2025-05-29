package com.binarywang.solon.wxjava.miniapp.configuration.services;

import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import cn.binarywang.wx.miniapp.config.impl.WxMaRedisConfigImpl;
import com.binarywang.solon.wxjava.miniapp.properties.WxMaMultiProperties;
import com.binarywang.solon.wxjava.miniapp.properties.WxMaMultiRedisProperties;
import com.binarywang.solon.wxjava.miniapp.service.WxMaMultiServices;
import lombok.RequiredArgsConstructor;
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
 * @author monch
 * created on 2024/9/6
 */
@Configuration
@Condition(
  onProperty = "${"+WxMaMultiProperties.PREFIX + ".configStorage.type} = jedis",
  onClass = JedisPool.class
)
@RequiredArgsConstructor
public class WxMaInJedisConfiguration extends AbstractWxMaConfiguration {
  private final WxMaMultiProperties wxMaMultiProperties;
  private final AppContext applicationContext;

  @Bean
  public WxMaMultiServices wxMaMultiServices() {
    return this.wxMaMultiServices(wxMaMultiProperties);
  }

  @Override
  protected WxMaDefaultConfigImpl wxMaConfigStorage(WxMaMultiProperties wxMaMultiProperties) {
    return this.configRedis(wxMaMultiProperties);
  }

  private WxMaDefaultConfigImpl configRedis(WxMaMultiProperties wxMaMultiProperties) {
    WxMaMultiRedisProperties wxMaMultiRedisProperties = wxMaMultiProperties.getConfigStorage().getRedis();
    JedisPool jedisPool;
    if (wxMaMultiRedisProperties != null && StringUtils.isNotEmpty(wxMaMultiRedisProperties.getHost())) {
      jedisPool = getJedisPool(wxMaMultiProperties);
    } else {
      jedisPool = applicationContext.getBean(JedisPool.class);
    }
    return new WxMaRedisConfigImpl(jedisPool);
  }

  private JedisPool getJedisPool(WxMaMultiProperties wxMaMultiProperties) {
    WxMaMultiProperties.ConfigStorage storage = wxMaMultiProperties.getConfigStorage();
    WxMaMultiRedisProperties redis = storage.getRedis();

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
