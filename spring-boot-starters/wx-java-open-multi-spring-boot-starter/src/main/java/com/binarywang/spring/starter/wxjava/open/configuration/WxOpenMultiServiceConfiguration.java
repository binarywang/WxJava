package com.binarywang.spring.starter.wxjava.open.configuration;

import com.binarywang.spring.starter.wxjava.open.configuration.services.WxOpenInJedisConfiguration;
import com.binarywang.spring.starter.wxjava.open.configuration.services.WxOpenInMemoryConfiguration;
import com.binarywang.spring.starter.wxjava.open.configuration.services.WxOpenInRedisTemplateConfiguration;
import com.binarywang.spring.starter.wxjava.open.configuration.services.WxOpenInRedissonConfiguration;
import com.binarywang.spring.starter.wxjava.open.properties.WxOpenMultiProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 微信开放平台相关服务自动注册
 *
 * @author Binary Wang
 */
@Configuration
@EnableConfigurationProperties(WxOpenMultiProperties.class)
@Import({
  WxOpenInJedisConfiguration.class,
  WxOpenInMemoryConfiguration.class,
  WxOpenInRedissonConfiguration.class,
  WxOpenInRedisTemplateConfiguration.class
})
public class WxOpenMultiServiceConfiguration {
}
