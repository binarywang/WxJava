package com.binarywang.solon.wxjava.channel.integration;

import com.binarywang.solon.wxjava.channel.configuration.services.WxChannelInJedisConfiguration;
import com.binarywang.solon.wxjava.channel.configuration.services.WxChannelInMemoryConfiguration;
import com.binarywang.solon.wxjava.channel.configuration.services.WxChannelInRedissonConfiguration;
import com.binarywang.solon.wxjava.channel.properties.WxChannelMultiProperties;
import org.noear.solon.core.AppContext;
import org.noear.solon.core.Plugin;

/**
 * 微信视频号自动注册
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>  2024/9/13
 * @author noear 2024/10/9 created
 */
public class WxChannelMultiPluginImpl implements Plugin {
  @Override
  public void start(AppContext context) throws Throwable {
    context.beanMake(WxChannelMultiProperties.class);

    context.beanMake(WxChannelInJedisConfiguration.class);
    context.beanMake(WxChannelInMemoryConfiguration.class);
    context.beanMake(WxChannelInRedissonConfiguration.class);
  }
}
