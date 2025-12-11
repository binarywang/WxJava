package com.binarywang.spring.starter.wxjava.mp.config;

import com.binarywang.spring.starter.wxjava.mp.enums.HttpClientType;
import com.binarywang.spring.starter.wxjava.mp.properties.WxMpProperties;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceHttpClientImpl;
import me.chanjar.weixin.mp.api.impl.WxMpServiceHttpComponentsImpl;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.api.impl.WxMpServiceJoddHttpImpl;
import me.chanjar.weixin.mp.api.impl.WxMpServiceOkHttpImpl;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 微信公众号相关服务自动注册.
 *
 * @author someone
 */
@Configuration
public class WxMpServiceAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public WxMpService wxMpService(WxMpConfigStorage configStorage, WxMpProperties wxMpProperties) {
    HttpClientType httpClientType = wxMpProperties.getConfigStorage().getHttpClientType();
    WxMpService wxMpService;
    switch (httpClientType) {
      case OkHttp:
        wxMpService = newWxMpServiceOkHttpImpl();
        break;
      case JoddHttp:
        wxMpService = newWxMpServiceJoddHttpImpl();
        break;
      case HttpClient:
        wxMpService = newWxMpServiceHttpClientImpl();
        break;
      case HttpComponents:
        wxMpService = newWxMpServiceHttpComponentsImpl();
        break;
      default:
        wxMpService = newWxMpServiceImpl();
        break;
    }

    wxMpService.setWxMpConfigStorage(configStorage);
    return wxMpService;
  }

  private WxMpService newWxMpServiceImpl() {
    return new WxMpServiceImpl();
  }

  private WxMpService newWxMpServiceHttpClientImpl() {
    return new WxMpServiceHttpClientImpl();
  }

  private WxMpService newWxMpServiceOkHttpImpl() {
    return new WxMpServiceOkHttpImpl();
  }

  private WxMpService newWxMpServiceJoddHttpImpl() {
    return new WxMpServiceJoddHttpImpl();
  }

  private WxMpService newWxMpServiceHttpComponentsImpl() {
    return new WxMpServiceHttpComponentsImpl();
  }

}
