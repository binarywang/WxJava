package com.binarywang.spring.starter.wxjava.mp.config;

import com.binarywang.spring.starter.wxjava.mp.config.storage.WxMpInJedisConfigStorageConfiguration;
import com.binarywang.spring.starter.wxjava.mp.config.storage.WxMpInMemoryConfigStorageConfiguration;
import com.binarywang.spring.starter.wxjava.mp.config.storage.WxMpInRedisTemplateConfigStorageConfiguration;
import com.binarywang.spring.starter.wxjava.mp.config.storage.WxMpInRedissonConfigStorageConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 微信公众号存储策略自动配置.
 *
 * @author Luo
 */
@Configuration
@Import({
  WxMpInMemoryConfigStorageConfiguration.class,
  WxMpInJedisConfigStorageConfiguration.class,
  WxMpInRedisTemplateConfigStorageConfiguration.class,
  WxMpInRedissonConfigStorageConfiguration.class
})
@RequiredArgsConstructor
public class WxMpStorageAutoConfiguration {

}
