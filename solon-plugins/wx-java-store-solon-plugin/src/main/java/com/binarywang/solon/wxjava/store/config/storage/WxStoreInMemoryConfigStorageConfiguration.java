package com.binarywang.solon.wxjava.store.config.storage;


import com.binarywang.solon.wxjava.store.properties.WxStoreProperties;
import lombok.RequiredArgsConstructor;
import com.binarywang.wxjava.store.config.WxStoreConfig;
import com.binarywang.wxjava.store.config.impl.WxStoreDefaultConfigImpl;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Condition;
import org.noear.solon.annotation.Configuration;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Configuration
@Condition(
  onProperty = "${"+WxStoreProperties.PREFIX + ".configStorage.type:memory} = memory"
)
@RequiredArgsConstructor
public class WxStoreInMemoryConfigStorageConfiguration extends AbstractWxStoreConfigStorageConfiguration {
  private final WxStoreProperties properties;

  @Bean
  @Condition(onMissingBean = WxStoreConfig.class)
  public WxStoreConfig wxStoreConfig() {
    WxStoreDefaultConfigImpl config = new WxStoreDefaultConfigImpl();
    return this.config(config, properties);
  }
}
