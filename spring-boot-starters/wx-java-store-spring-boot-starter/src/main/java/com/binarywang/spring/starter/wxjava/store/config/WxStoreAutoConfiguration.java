package com.binarywang.spring.starter.wxjava.store.config;

import com.binarywang.spring.starter.wxjava.store.properties.WxStoreProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 自动配置
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Configuration
@EnableConfigurationProperties(WxStoreProperties.class)
@Import({
  WxStoreStorageAutoConfiguration.class,
  WxStoreServiceAutoConfiguration.class
})
public class WxStoreAutoConfiguration {
}
