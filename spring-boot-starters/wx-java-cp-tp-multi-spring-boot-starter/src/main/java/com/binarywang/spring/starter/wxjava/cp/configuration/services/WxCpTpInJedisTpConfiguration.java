package com.binarywang.spring.starter.wxjava.cp.configuration.services;

import com.binarywang.spring.starter.wxjava.cp.properties.WxCpTpMultiProperties;
import com.binarywang.spring.starter.wxjava.cp.properties.WxCpTpMultiRedisProperties;
import com.binarywang.spring.starter.wxjava.cp.service.WxCpTpMultiServices;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.cp.config.impl.WxCpDefaultConfigImpl;
import me.chanjar.weixin.cp.config.impl.WxCpJedisConfigImpl;
import me.chanjar.weixin.cp.config.impl.WxCpTpDefaultConfigImpl;
import me.chanjar.weixin.cp.config.impl.WxCpTpJedisConfigImpl;
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
 * @author yl
 * created on 2023/10/16
 */
@Configuration
@ConditionalOnProperty(
  prefix = WxCpTpMultiProperties.PREFIX + ".config-storage", name = "type", havingValue = "jedis"
)
@RequiredArgsConstructor
public class WxCpTpInJedisTpConfiguration extends AbstractWxCpTpConfiguration {
  private final WxCpTpMultiProperties wxCpTpMultiProperties;
  private final ApplicationContext applicationContext;

  @Bean
  public WxCpTpMultiServices wxCpMultiServices() {
    return this.wxCpMultiServices(wxCpTpMultiProperties,null);
  }

  @Override
  protected WxCpTpDefaultConfigImpl wxCpTpConfigStorage(WxCpTpMultiProperties wxCpTpMultiProperties) {
    return this.configRedis(wxCpTpMultiProperties);
  }

  private WxCpTpDefaultConfigImpl configRedis(WxCpTpMultiProperties wxCpTpMultiProperties) {
    WxCpTpMultiRedisProperties wxCpTpMultiRedisProperties = wxCpTpMultiProperties.getConfigStorage().getRedis();
    JedisPool jedisPool;
    if (wxCpTpMultiRedisProperties != null && StringUtils.isNotEmpty(wxCpTpMultiRedisProperties.getHost())) {
      jedisPool = getJedisPool(wxCpTpMultiProperties);
    } else {
      jedisPool = applicationContext.getBean(JedisPool.class);
    }
    return new WxCpTpJedisConfigImpl(jedisPool, wxCpTpMultiProperties.getConfigStorage().getKeyPrefix());
  }

  private JedisPool getJedisPool(WxCpTpMultiProperties wxCpTpMultiProperties) {
    WxCpTpMultiProperties.ConfigStorage storage = wxCpTpMultiProperties.getConfigStorage();
    WxCpTpMultiRedisProperties redis = storage.getRedis();

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
