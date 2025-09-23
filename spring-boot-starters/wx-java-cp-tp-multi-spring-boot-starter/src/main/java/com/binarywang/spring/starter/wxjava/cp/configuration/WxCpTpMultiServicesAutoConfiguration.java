package com.binarywang.spring.starter.wxjava.cp.configuration;

import com.binarywang.spring.starter.wxjava.cp.configuration.services.WxCpTpInJedisTpConfiguration;
import com.binarywang.spring.starter.wxjava.cp.configuration.services.WxCpTpInMemoryTpConfiguration;
import com.binarywang.spring.starter.wxjava.cp.configuration.services.WxCpTpInRedisTemplateTpConfiguration;
import com.binarywang.spring.starter.wxjava.cp.configuration.services.WxCpTpInRedissonTpConfiguration;
import com.binarywang.spring.starter.wxjava.cp.properties.WxCpTpMultiProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 企业微信平台相关服务自动注册
 *
 * @author yl
 * created on 2023/10/16
 */
@Configuration
@EnableConfigurationProperties(WxCpTpMultiProperties.class)
@Import({
  WxCpTpInJedisTpConfiguration.class,
  WxCpTpInMemoryTpConfiguration.class,
  WxCpTpInRedissonTpConfiguration.class,
  WxCpTpInRedisTemplateTpConfiguration.class
})
public class WxCpTpMultiServicesAutoConfiguration {
}
