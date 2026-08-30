package com.binarywang.spring.starter.wxjava.store.autoconfigure;

import com.binarywang.spring.starter.wxjava.store.configuration.WxStoreMultiServiceConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 微信小店自动注册
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 * @date 2024/9/13
 */
@Configuration
@Import(WxStoreMultiServiceConfiguration.class)
public class WxStoreMultiAutoConfiguration {}
