package com.binarywang.solon.wxjava.channel.configuration.services;

import com.binarywang.solon.wxjava.channel.properties.WxChannelMultiProperties;
import com.binarywang.solon.wxjava.channel.properties.WxChannelMultiRedisProperties;
import com.binarywang.solon.wxjava.channel.service.WxChannelMultiServices;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.channel.config.impl.WxChannelDefaultConfigImpl;
import me.chanjar.weixin.channel.config.impl.WxChannelRedissonConfigImpl;
import org.apache.commons.lang3.StringUtils;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Condition;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.core.AppContext;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;

/**
 * 自动装配基于 redisson 策略配置
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a> 2024/9/13
 * @author noear
 */
@Configuration
@Condition(
  onProperty = "${"+WxChannelMultiProperties.PREFIX + ".configStorage.type} = redisson",
  onClass = Redisson.class
)
@RequiredArgsConstructor
public class WxChannelInRedissonConfiguration extends AbstractWxChannelConfiguration {
  private final WxChannelMultiProperties wxChannelMultiProperties;
  private final AppContext applicationContext;

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
