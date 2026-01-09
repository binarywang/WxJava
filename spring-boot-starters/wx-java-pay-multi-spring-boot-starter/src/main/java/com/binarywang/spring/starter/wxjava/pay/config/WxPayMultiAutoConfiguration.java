package com.binarywang.spring.starter.wxjava.pay.config;

import com.binarywang.spring.starter.wxjava.pay.properties.WxPayMultiProperties;
import com.binarywang.spring.starter.wxjava.pay.service.WxPayMultiServices;
import com.binarywang.spring.starter.wxjava.pay.service.WxPayMultiServicesImpl;
import com.github.binarywang.wxpay.service.WxPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 微信支付多公众号关联自动配置.
 *
 * @author Binary Wang
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(WxPayMultiProperties.class)
@ConditionalOnClass(WxPayService.class)
@ConditionalOnProperty(prefix = WxPayMultiProperties.PREFIX, value = "enabled", matchIfMissing = true)
public class WxPayMultiAutoConfiguration {

  /**
   * 构造微信支付多服务管理对象.
   *
   * @param wxPayMultiProperties 多配置属性
   * @return 微信支付多服务管理对象
   */
  @Bean
  @ConditionalOnMissingBean(WxPayMultiServices.class)
  public WxPayMultiServices wxPayMultiServices(WxPayMultiProperties wxPayMultiProperties) {
    return new WxPayMultiServicesImpl(wxPayMultiProperties);
  }
}
