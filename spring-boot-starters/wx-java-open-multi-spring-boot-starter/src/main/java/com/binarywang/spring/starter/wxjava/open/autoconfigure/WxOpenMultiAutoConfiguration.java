package com.binarywang.spring.starter.wxjava.open.autoconfigure;

import com.binarywang.spring.starter.wxjava.open.configuration.WxOpenMultiServiceConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 微信开放平台多账号自动配置
 *
 * @author Binary Wang
 */
@Configuration
@Import(WxOpenMultiServiceConfiguration.class)
public class WxOpenMultiAutoConfiguration {
}
