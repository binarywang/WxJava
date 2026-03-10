package com.binarywang.spring.starter.wxjava.miniapp.configuration;

import com.binarywang.spring.starter.wxjava.miniapp.configuration.services.WxMaInJedisConfiguration;
import com.binarywang.spring.starter.wxjava.miniapp.configuration.services.WxMaInMemoryConfiguration;
import com.binarywang.spring.starter.wxjava.miniapp.configuration.services.WxMaInRedisTemplateConfiguration;
import com.binarywang.spring.starter.wxjava.miniapp.configuration.services.WxMaInRedissonConfiguration;
import com.binarywang.spring.starter.wxjava.miniapp.properties.WxMaMultiProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 微信小程序相关服务自动注册
 *
 * @author monch
 * created on 2024/9/6
 */
@Configuration
@EnableConfigurationProperties(WxMaMultiProperties.class)
@Import({
        WxMaInJedisConfiguration.class,
        WxMaInMemoryConfiguration.class,
        WxMaInRedissonConfiguration.class,
        WxMaInRedisTemplateConfiguration.class
})
public class WxMaMultiServiceConfiguration {
}
