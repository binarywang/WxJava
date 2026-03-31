package com.binarywang.spring.starter.wxjava.miniapp.config;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceHttpClientImpl;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceHttpComponentsImpl;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceJoddHttpImpl;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceOkHttpImpl;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import com.binarywang.spring.starter.wxjava.miniapp.enums.HttpClientType;
import com.binarywang.spring.starter.wxjava.miniapp.properties.WxMaProperties;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 微信小程序平台相关服务自动注册.
 *
 * @author someone TaoYu
 */
@Configuration
@AllArgsConstructor
public class WxMaServiceAutoConfiguration {

  private final WxMaProperties wxMaProperties;

  /**
   * 小程序service.
   *
   * @return 小程序service
   */
  @Bean
  @ConditionalOnMissingBean(WxMaService.class)
  @ConditionalOnBean(WxMaConfig.class)
  public WxMaService wxMaService(WxMaConfig wxMaConfig) {
    HttpClientType httpClientType = wxMaProperties.getConfigStorage().getHttpClientType();
    WxMaService wxMaService;
    switch (httpClientType) {
      case OkHttp:
        wxMaService = new WxMaServiceOkHttpImpl();
        break;
      case JoddHttp:
        wxMaService = new WxMaServiceJoddHttpImpl();
        break;
      case HttpClient:
        wxMaService = new WxMaServiceHttpClientImpl();
        break;
      case HttpComponents:
        wxMaService = new WxMaServiceHttpComponentsImpl();
        break;
      default:
        wxMaService = new WxMaServiceImpl();
        break;
    }
    wxMaService.setWxMaConfig(wxMaConfig);
    return wxMaService;
  }
}
