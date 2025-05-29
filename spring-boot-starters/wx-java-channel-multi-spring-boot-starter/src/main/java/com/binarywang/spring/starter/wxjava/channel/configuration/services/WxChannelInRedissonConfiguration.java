package com.binarywang.spring.starter.wxjava.channel.configuration.services;

import com.binarywang.spring.starter.wxjava.channel.properties.WxChannelMultiProperties;
import com.binarywang.spring.starter.wxjava.channel.properties.WxChannelMultiRedisProperties;
import com.binarywang.spring.starter.wxjava.channel.service.WxChannelMultiServices;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.channel.config.impl.WxChannelDefaultConfigImpl;
import me.chanjar.weixin.channel.config.impl.WxChannelRedissonConfigImpl;
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
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 * @date 2024/9/13
 */
@Configuration
@ConditionalOnProperty(prefix = WxChannelMultiProperties.PREFIX + ".config-storage", name = "type", havingValue = "redisson")
@RequiredArgsConstructor
public class WxChannelInRedissonConfiguration extends AbstractWxChannelConfiguration {
  private final WxChannelMultiProperties wxChannelMultiProperties;
  private final ApplicationContext applicationContext;

  @Bean
  public WxChannelMultiServices wxChannelMultiServices() {
    return this.wxChannelMultiServices(wxChannelMultiProperties);
  }

  @Override
  protected WxChannelDefaultConfigImpl wxChannelConfigStorage(WxChannelMultiProperties wxChannelMultiProperties) {
    return this.configRedisson(wxChannelMultiProperties);
  }

  private WxChannelDefaultConfigImpl configRedisson(WxChannelMultiProperties wxChannelMultiProperties) {
    WxChannelMultiRedisProperties redisProperties = wxChannelMultiProperties.getConfigStorage().getRedis();
    RedissonClient redissonClient;
    if (redisProperties != null && StringUtils.isNotEmpty(redisProperties.getHost())) {
      redissonClient = getRedissonClient(wxChannelMultiProperties);
    } else {
      redissonClient = applicationContext.getBean(RedissonClient.class);
    }
    return new WxChannelRedissonConfigImpl(redissonClient, wxChannelMultiProperties.getConfigStorage().getKeyPrefix());
  }

  private RedissonClient getRedissonClient(WxChannelMultiProperties wxChannelMultiProperties) {
    WxChannelMultiProperties.ConfigStorage storage = wxChannelMultiProperties.getConfigStorage();
    WxChannelMultiRedisProperties redis = storage.getRedis();

    Config config = new Config();
    config.useSingleServer().setAddress("redis://" + redis.getHost() + ":" + redis.getPort()).setDatabase(redis.getDatabase()).setPassword(redis.getPassword());
    config.setTransportMode(TransportMode.NIO);
    return Redisson.create(config);
  }
}
