package com.binarywang.spring.starter.wxjava.store.config;

import com.binarywang.spring.starter.wxjava.store.config.storage.WxStoreInJedisConfigStorageConfiguration;
import com.binarywang.spring.starter.wxjava.store.config.storage.WxStoreInMemoryConfigStorageConfiguration;
import com.binarywang.spring.starter.wxjava.store.config.storage.WxStoreInRedisTemplateConfigStorageConfiguration;
import com.binarywang.spring.starter.wxjava.store.config.storage.WxStoreInRedissonConfigStorageConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 微信小程序存储策略自动配置
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Configuration
@Import({
  WxStoreInMemoryConfigStorageConfiguration.class,
  WxStoreInJedisConfigStorageConfiguration.class,
  WxStoreInRedisTemplateConfigStorageConfiguration.class,
  WxStoreInRedissonConfigStorageConfiguration.class
})
public class WxStoreStorageAutoConfiguration {
}
