package com.binarywang.spring.starter.wxjava.channel.configuration;

import com.binarywang.spring.starter.wxjava.channel.configuration.services.WxChannelInJedisConfiguration;
import com.binarywang.spring.starter.wxjava.channel.configuration.services.WxChannelInMemoryConfiguration;
import com.binarywang.spring.starter.wxjava.channel.configuration.services.WxChannelInRedisTemplateConfiguration;
import com.binarywang.spring.starter.wxjava.channel.configuration.services.WxChannelInRedissonConfiguration;
import com.binarywang.spring.starter.wxjava.channel.properties.WxChannelMultiProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 微信视频号相关服务自动注册
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 * @date 2024/9/13
 */
@Configuration
@EnableConfigurationProperties(WxChannelMultiProperties.class)
@Import({WxChannelInJedisConfiguration.class, WxChannelInMemoryConfiguration.class, WxChannelInRedissonConfiguration.class, WxChannelInRedisTemplateConfiguration.class})
public class WxChannelMultiServiceConfiguration {}
