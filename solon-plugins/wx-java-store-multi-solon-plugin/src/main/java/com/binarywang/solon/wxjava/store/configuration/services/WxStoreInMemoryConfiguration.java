package com.binarywang.solon.wxjava.store.configuration.services;

import com.binarywang.solon.wxjava.store.properties.WxStoreMultiProperties;
import com.binarywang.solon.wxjava.store.service.WxStoreMultiServices;
import lombok.RequiredArgsConstructor;
import com.binarywang.wxjava.store.config.impl.WxStoreDefaultConfigImpl;
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
  onProperty = "${"+WxStoreMultiProperties.PREFIX + ".configStorage.type} = memory",
  onClass = JedisPool.class
)
@RequiredArgsConstructor
public class WxStoreInMemoryConfiguration extends AbstractWxStoreConfiguration {
  private final WxStoreMultiProperties wxStoreMultiProperties;

  @Bean
  public WxStoreMultiServices wxStoreMultiServices() {
    return this.wxStoreMultiServices(wxStoreMultiProperties);
  }

  @Override
  protected WxStoreDefaultConfigImpl wxStoreConfigStorage(WxStoreMultiProperties wxStoreMultiProperties) {
    return this.configInMemory();
  }

  private WxStoreDefaultConfigImpl configInMemory() {
    return new WxStoreDefaultConfigImpl();
  }
}
