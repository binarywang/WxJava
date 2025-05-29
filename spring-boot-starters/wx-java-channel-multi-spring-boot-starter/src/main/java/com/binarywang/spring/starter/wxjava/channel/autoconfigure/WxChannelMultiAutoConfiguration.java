package com.binarywang.spring.starter.wxjava.channel.autoconfigure;

import com.binarywang.spring.starter.wxjava.channel.configuration.WxChannelMultiServiceConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 微信视频号自动注册
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 * @date 2024/9/13
 */
@Configuration
@Import(WxChannelMultiServiceConfiguration.class)
public class WxChannelMultiAutoConfiguration {}
