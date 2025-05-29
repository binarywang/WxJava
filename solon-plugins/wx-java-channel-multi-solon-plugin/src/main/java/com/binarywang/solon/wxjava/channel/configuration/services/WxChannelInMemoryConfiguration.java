package com.binarywang.solon.wxjava.channel.configuration.services;

import com.binarywang.solon.wxjava.channel.properties.WxChannelMultiProperties;
import com.binarywang.solon.wxjava.channel.service.WxChannelMultiServices;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.channel.config.impl.WxChannelDefaultConfigImpl;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Condition;
import org.noear.solon.annotation.Configuration;
import redis.clients.jedis.JedisPool;

/**
 * 自动装配基于内存策略配置
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a> 2024/9/13
 * @author noear
 */
@Configuration
@Condition(
  onProperty = "${"+WxChannelMultiProperties.PREFIX + ".configStorage.type} = memory",
  onClass = JedisPool.class
)
@RequiredArgsConstructor
public class WxChannelInMemoryConfiguration extends AbstractWxChannelConfiguration {
  private final WxChannelMultiProperties wxChannelMultiProperties;

  @Bean
  public WxChannelMultiServices wxChannelMultiServices() {
    return this.wxChannelMultiServices(wxChannelMultiProperties);
  }

  @Override
  protected WxChannelDefaultConfigImpl wxChannelConfigStorage(WxChannelMultiProperties wxChannelMultiProperties) {
    return this.configInMemory();
  }

  private WxChannelDefaultConfigImpl configInMemory() {
    return new WxChannelDefaultConfigImpl();
  }
}
