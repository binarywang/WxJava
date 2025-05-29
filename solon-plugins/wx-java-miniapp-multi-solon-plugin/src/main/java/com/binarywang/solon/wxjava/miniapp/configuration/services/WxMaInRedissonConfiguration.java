package com.binarywang.solon.wxjava.miniapp.configuration.services;

import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import cn.binarywang.wx.miniapp.config.impl.WxMaRedissonConfigImpl;
import com.binarywang.solon.wxjava.miniapp.properties.WxMaMultiProperties;
import com.binarywang.solon.wxjava.miniapp.properties.WxMaMultiRedisProperties;
import com.binarywang.solon.wxjava.miniapp.service.WxMaMultiServices;
import lombok.RequiredArgsConstructor;
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
 * @author monch
 * created on 2024/9/6
 */
@Configuration
@Condition(
  onProperty = "${"+WxMaMultiProperties.PREFIX + ".configStorage.type} = redisson",
  onClass = Redisson.class
)
@RequiredArgsConstructor
public class WxMaInRedissonConfiguration extends AbstractWxMaConfiguration {
  private final WxMaMultiProperties wxMaMultiProperties;
  private final AppContext applicationContext;

  @Bean
  public WxMaMultiServices wxMaMultiServices() {
    return this.wxMaMultiServices(wxMaMultiProperties);
  }

  @Override
  protected WxMaDefaultConfigImpl wxMaConfigStorage(WxMaMultiProperties wxMaMultiProperties) {
    return this.configRedisson(wxMaMultiProperties);
  }

  private WxMaDefaultConfigImpl configRedisson(WxMaMultiProperties wxMaMultiProperties) {
    WxMaMultiRedisProperties redisProperties = wxMaMultiProperties.getConfigStorage().getRedis();
    RedissonClient redissonClient;
    if (redisProperties != null && StringUtils.isNotEmpty(redisProperties.getHost())) {
      redissonClient = getRedissonClient(wxMaMultiProperties);
    } else {
      redissonClient = applicationContext.getBean(RedissonClient.class);
    }
    return new WxMaRedissonConfigImpl(redissonClient, wxMaMultiProperties.getConfigStorage().getKeyPrefix());
  }

  private RedissonClient getRedissonClient(WxMaMultiProperties wxMaMultiProperties) {
    WxMaMultiProperties.ConfigStorage storage = wxMaMultiProperties.getConfigStorage();
    WxMaMultiRedisProperties redis = storage.getRedis();

    Config config = new Config();
    config.useSingleServer()
      .setAddress("redis://" + redis.getHost() + ":" + redis.getPort())
      .setDatabase(redis.getDatabase())
      .setPassword(redis.getPassword());
    config.setTransportMode(TransportMode.NIO);
    return Redisson.create(config);
  }
}
