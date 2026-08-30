package com.binarywang.solon.wxjava.store.integration;

import com.binarywang.solon.wxjava.store.configuration.services.WxStoreInJedisConfiguration;
import com.binarywang.solon.wxjava.store.configuration.services.WxStoreInMemoryConfiguration;
import com.binarywang.solon.wxjava.store.configuration.services.WxStoreInRedissonConfiguration;
import com.binarywang.solon.wxjava.store.properties.WxStoreMultiProperties;
import org.noear.solon.core.AppContext;
import org.noear.solon.core.Plugin;

/**
 * 微信小店自动注册
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>  2024/9/13
 * @author noear 2024/10/9 created
 */
public class WxStoreMultiPluginImpl implements Plugin {
  @Override
  public void start(AppContext context) throws Throwable {
    context.beanMake(WxStoreMultiProperties.class);

    context.beanMake(WxStoreInJedisConfiguration.class);
    context.beanMake(WxStoreInMemoryConfiguration.class);
    context.beanMake(WxStoreInRedissonConfiguration.class);
  }
}
