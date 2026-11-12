package com.binarywang.spring.starter.wxjava.mp.config.storage;

import com.binarywang.spring.starter.wxjava.mp.properties.RedisProperties;
import com.binarywang.spring.starter.wxjava.mp.properties.WxMpProperties;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.config.impl.WxMpRedissonConfigImpl;
import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author <a href="https://github.com/buaazyl">zhangyl</a>
 */
@Configuration
@ConditionalOnProperty(
  prefix = WxMpProperties.PREFIX + ".config-storage",
  name = "type",
  havingValue = "redisson"
)
@ConditionalOnClass({Redisson.class, RedissonClient.class})
@RequiredArgsConstructor
public class WxMpInRedissonConfigStorageConfiguration extends AbstractWxMpConfigStorageConfiguration {
  private static final String REDISSON_CLIENT_BEAN = "wxMpRedissonClient";

  private final WxMpProperties properties;
  private final ApplicationContext applicationContext;

  @Bean
  @ConditionalOnMissingBean(WxMpConfigStorage.class)
  public WxMpConfigStorage wxMpConfigStorage() {
    WxMpRedissonConfigImpl config = getWxMpInRedissonConfigStorage();
    return this.config(config, properties);
  }

  private WxMpRedissonConfigImpl getWxMpInRedissonConfigStorage() {
    RedissonClient redissonClient;
    if (applicationContext.containsBean(REDISSON_CLIENT_BEAN)) {
      redissonClient = applicationContext.getBean(REDISSON_CLIENT_BEAN, RedissonClient.class);
    } else {
      redissonClient = applicationContext.getBean(RedissonClient.class);
    }
    return new WxMpRedissonConfigImpl(redissonClient, properties.getConfigStorage().getKeyPrefix());
  }

  @Bean(REDISSON_CLIENT_BEAN)
  @Conditional(RedisHostConfiguredCondition.class)
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

  static class RedisHostConfiguredCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
      String host = context.getEnvironment().getProperty(WxMpProperties.PREFIX + ".config-storage.redis.host");
      return StringUtils.isNotBlank(host) && !"false".equalsIgnoreCase(host);
    }
  }
}
