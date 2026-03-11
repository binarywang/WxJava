package com.binarywang.spring.starter.wxjava.cp.autoconfigure;

import com.binarywang.spring.starter.wxjava.cp.configuration.WxCpTpMultiServicesAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 企业微信自动注册
 *
 * @author yl
 * created on 2023/10/16
 */
@Configuration
@Import(WxCpTpMultiServicesAutoConfiguration.class)
public class WxCpTpMultiAutoConfiguration {
}
