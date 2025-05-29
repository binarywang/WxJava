package com.binarywang.spring.starter.wxjava.channel.configuration.services;

import com.binarywang.spring.starter.wxjava.channel.properties.WxChannelMultiProperties;
import com.binarywang.spring.starter.wxjava.channel.properties.WxChannelMultiRedisProperties;
import com.binarywang.spring.starter.wxjava.channel.service.WxChannelMultiServices;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.channel.config.impl.WxChannelDefaultConfigImpl;
import me.chanjar.weixin.channel.config.impl.WxChannelRedisConfigImpl;
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
@ConditionalOnProperty(prefix = WxChannelMultiProperties.PREFIX + ".config-storage", name = "type", havingValue = "jedis")
@RequiredArgsConstructor
public class WxChannelInJedisConfiguration extends AbstractWxChannelConfiguration {
  private final WxChannelMultiProperties wxChannelMultiProperties;
  private final ApplicationContext applicationContext;

  @Bean
  public WxChannelMultiServices wxChannelMultiServices() {
    return this.wxChannelMultiServices(wxChannelMultiProperties);
  }

  @Override
  protected WxChannelDefaultConfigImpl wxChannelConfigStorage(WxChannelMultiProperties wxChannelMultiProperties) {
    return this.configRedis(wxChannelMultiProperties);
  }

  private WxChannelDefaultConfigImpl configRedis(WxChannelMultiProperties wxChannelMultiProperties) {
    WxChannelMultiRedisProperties wxChannelMultiRedisProperties = wxChannelMultiProperties.getConfigStorage().getRedis();
    JedisPool jedisPool;
    if (wxChannelMultiRedisProperties != null && StringUtils.isNotEmpty(wxChannelMultiRedisProperties.getHost())) {
      jedisPool = getJedisPool(wxChannelMultiProperties);
    } else {
      jedisPool = applicationContext.getBean(JedisPool.class);
    }
    return new WxChannelRedisConfigImpl(new JedisWxRedisOps(jedisPool), wxChannelMultiProperties.getConfigStorage().getKeyPrefix());
  }

  private JedisPool getJedisPool(WxChannelMultiProperties wxChannelMultiProperties) {
    WxChannelMultiProperties.ConfigStorage storage = wxChannelMultiProperties.getConfigStorage();
    WxChannelMultiRedisProperties redis = storage.getRedis();

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
