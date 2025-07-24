package com.binarywang.solon.wxjava.miniapp.integration;

import com.binarywang.solon.wxjava.miniapp.configuration.services.WxMaInJedisConfiguration;
import com.binarywang.solon.wxjava.miniapp.configuration.services.WxMaInMemoryConfiguration;
import com.binarywang.solon.wxjava.miniapp.configuration.services.WxMaInRedissonConfiguration;
import com.binarywang.solon.wxjava.miniapp.properties.WxMaMultiProperties;
import org.noear.solon.core.AppContext;
import org.noear.solon.core.Plugin;

/**
 * @author noear 2024/10/9 created
 */
public class WxMiniappMultiPluginImpl implements Plugin {
  @Override
  public void start(AppContext context) throws Throwable {
    context.beanMake(WxMaMultiProperties.class);

    context.beanMake(WxMaInJedisConfiguration.class);
    context.beanMake(WxMaInMemoryConfiguration.class);
    context.beanMake(WxMaInRedissonConfiguration.class);
  }
}
