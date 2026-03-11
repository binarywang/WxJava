package com.binarywang.solon.wxjava.qidian.config;

import com.binarywang.solon.wxjava.qidian.enums.HttpClientType;
import com.binarywang.solon.wxjava.qidian.properties.WxQidianProperties;
import me.chanjar.weixin.qidian.api.WxQidianService;
import me.chanjar.weixin.qidian.api.impl.WxQidianServiceHttpClientImpl;
import me.chanjar.weixin.qidian.api.impl.WxQidianServiceHttpComponentsImpl;
import me.chanjar.weixin.qidian.api.impl.WxQidianServiceImpl;
import me.chanjar.weixin.qidian.api.impl.WxQidianServiceJoddHttpImpl;
import me.chanjar.weixin.qidian.api.impl.WxQidianServiceOkHttpImpl;
import me.chanjar.weixin.qidian.config.WxQidianConfigStorage;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Condition;
import org.noear.solon.annotation.Configuration;

/**
 * 腾讯企点相关服务自动注册.
 *
 * @author alegria
 */
@Configuration
public class WxQidianServiceAutoConfiguration {

  @Bean
  @Condition(onMissingBean = WxQidianService.class)
  public WxQidianService wxQidianService(WxQidianConfigStorage configStorage, WxQidianProperties wxQidianProperties) {
    HttpClientType httpClientType = wxQidianProperties.getConfigStorage().getHttpClientType();
    WxQidianService wxQidianService;
    switch (httpClientType) {
      case OkHttp:
        wxQidianService = newWxQidianServiceOkHttpImpl();
        break;
      case JoddHttp:
        wxQidianService = newWxQidianServiceJoddHttpImpl();
        break;
      case HttpClient:
        wxQidianService = newWxQidianServiceHttpClientImpl();
        break;
      case HttpComponents:
        wxQidianService = newWxQidianServiceHttpComponentsImpl();
        break;
      default:
        wxQidianService = newWxQidianServiceImpl();
        break;
    }

    wxQidianService.setWxMpConfigStorage(configStorage);
    return wxQidianService;
  }

  private WxQidianService newWxQidianServiceImpl() {
    return new WxQidianServiceImpl();
  }

  private WxQidianService newWxQidianServiceHttpClientImpl() {
    return new WxQidianServiceHttpClientImpl();
  }

  private WxQidianService newWxQidianServiceOkHttpImpl() {
    return new WxQidianServiceOkHttpImpl();
  }

  private WxQidianService newWxQidianServiceJoddHttpImpl() {
    return new WxQidianServiceJoddHttpImpl();
  }

  private WxQidianService newWxQidianServiceHttpComponentsImpl() {
    return new WxQidianServiceHttpComponentsImpl();
  }

}
