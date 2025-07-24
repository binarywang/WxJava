package com.binarywang.solon.wxjava.miniapp.configuration.services;

import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import com.binarywang.solon.wxjava.miniapp.properties.WxMaMultiProperties;
import com.binarywang.solon.wxjava.miniapp.service.WxMaMultiServices;
import lombok.RequiredArgsConstructor;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Condition;
import org.noear.solon.annotation.Configuration;

/**
 * 自动装配基于内存策略配置
 *
 * @author monch
 * created on 2024/9/6
 */
@Configuration
@Condition(
  onProperty = "${"+WxMaMultiProperties.PREFIX + ".configStorage.type:memory} = memory"
)
@RequiredArgsConstructor
public class WxMaInMemoryConfiguration extends AbstractWxMaConfiguration {
  private final WxMaMultiProperties wxMaMultiProperties;

  @Bean
  public WxMaMultiServices wxMaMultiServices() {
    return this.wxMaMultiServices(wxMaMultiProperties);
  }

  @Override
  protected WxMaDefaultConfigImpl wxMaConfigStorage(WxMaMultiProperties wxMaMultiProperties) {
    return this.configInMemory();
  }

  private WxMaDefaultConfigImpl configInMemory() {
    return new WxMaDefaultConfigImpl();
    // return new WxMaDefaultConfigImpl();
  }
}
