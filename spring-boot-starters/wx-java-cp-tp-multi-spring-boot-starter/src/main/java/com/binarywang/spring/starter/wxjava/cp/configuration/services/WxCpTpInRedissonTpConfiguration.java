package com.binarywang.spring.starter.wxjava.cp.configuration.services;

import com.binarywang.spring.starter.wxjava.cp.properties.WxCpTpMultiProperties;
import com.binarywang.spring.starter.wxjava.cp.properties.WxCpTpMultiRedisProperties;
import com.binarywang.spring.starter.wxjava.cp.service.WxCpTpMultiServices;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.cp.config.impl.WxCpTpDefaultConfigImpl;
import me.chanjar.weixin.cp.config.impl.AbstractWxCpTpInRedisConfigImpl;
import me.chanjar.weixin.cp.config.impl.WxCpTpRedissonConfigImpl;
import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动装配基于 redisson 策略配置
 *
 * @author yl
 * created on 2023/10/16
 */
@Configuration
@ConditionalOnProperty(
  prefix = WxCpTpMultiProperties.PREFIX + ".config-storage", name = "type", havingValue = "redisson"
)
@RequiredArgsConstructor
public class WxCpTpInRedissonTpConfiguration extends AbstractWxCpTpConfiguration {
  private final WxCpTpMultiProperties wxCpTpMultiProperties;
  private final ApplicationContext applicationContext;

  @Bean
  public WxCpTpMultiServices wxCpMultiServices() {
    return this.wxCpMultiServices(wxCpTpMultiProperties,null);
  }

  @Override
  protected WxCpTpDefaultConfigImpl wxCpTpConfigStorage(WxCpTpMultiProperties wxCpTpMultiProperties) {
    return this.configRedisson(wxCpTpMultiProperties);
  }

  private WxCpTpDefaultConfigImpl configRedisson(WxCpTpMultiProperties wxCpTpMultiProperties) {
    WxCpTpMultiRedisProperties redisProperties = wxCpTpMultiProperties.getConfigStorage().getRedis();
    RedissonClient redissonClient;
    if (redisProperties != null && StringUtils.isNotEmpty(redisProperties.getHost())) {
      redissonClient = getRedissonClient(wxCpTpMultiProperties);
    } else {
      redissonClient = applicationContext.getBean(RedissonClient.class);
    }
    return new WxCpTpRedissonConfigImpl(redissonClient, wxCpTpMultiProperties.getConfigStorage().getKeyPrefix());
  }

  private RedissonClient getRedissonClient(WxCpTpMultiProperties wxCpTpMultiProperties) {
    WxCpTpMultiProperties.ConfigStorage storage = wxCpTpMultiProperties.getConfigStorage();
    WxCpTpMultiRedisProperties redis = storage.getRedis();

    Config config = new Config();
    config.useSingleServer()
      .setAddress("redis://" + redis.getHost() + ":" + redis.getPort())
      .setDatabase(redis.getDatabase())
      .setPassword(redis.getPassword());
    config.setTransportMode(TransportMode.NIO);
    return Redisson.create(config);
  }
}
