package com.binarywang.solon.wxjava.mp.config.storage;

import com.binarywang.solon.wxjava.mp.properties.WxMpProperties;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Condition;
import org.noear.solon.annotation.Configuration;

/**
 * @author <a href="https://github.com/buaazyl">zhangyl</a>
 */
@Configuration
@Condition(
  onProperty = "${" + WxMpProperties.PREFIX + ".config-storage.type:memory} = memory"
)
@RequiredArgsConstructor
public class WxMpInMemoryConfigStorageConfiguration extends AbstractWxMpConfigStorageConfiguration {
  private final WxMpProperties properties;

  @Bean
  @Condition(onMissingBean = WxMpConfigStorage.class)
  public WxMpConfigStorage wxMpConfigStorage() {
    WxMpDefaultConfigImpl config = new WxMpDefaultConfigImpl();
    config(config, properties);
    return config;
  }
}
