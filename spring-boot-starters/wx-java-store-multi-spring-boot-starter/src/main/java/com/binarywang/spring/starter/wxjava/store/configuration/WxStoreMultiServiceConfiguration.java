package com.binarywang.spring.starter.wxjava.store.configuration;

import com.binarywang.spring.starter.wxjava.store.configuration.services.WxStoreInJedisConfiguration;
import com.binarywang.spring.starter.wxjava.store.configuration.services.WxStoreInMemoryConfiguration;
import com.binarywang.spring.starter.wxjava.store.configuration.services.WxStoreInRedisTemplateConfiguration;
import com.binarywang.spring.starter.wxjava.store.configuration.services.WxStoreInRedissonConfiguration;
import com.binarywang.spring.starter.wxjava.store.properties.WxStoreMultiProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 微信小店相关服务自动注册
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 * @date 2024/9/13
 */
@Configuration
@EnableConfigurationProperties(WxStoreMultiProperties.class)
@Import({WxStoreInJedisConfiguration.class, WxStoreInMemoryConfiguration.class, WxStoreInRedissonConfiguration.class, WxStoreInRedisTemplateConfiguration.class})
public class WxStoreMultiServiceConfiguration {}
