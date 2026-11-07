package com.binarywang.solon.wxjava.store.integration;


import com.binarywang.solon.wxjava.store.config.WxStoreServiceAutoConfiguration;
import com.binarywang.solon.wxjava.store.config.storage.WxStoreInJedisConfigStorageConfiguration;
import com.binarywang.solon.wxjava.store.config.storage.WxStoreInMemoryConfigStorageConfiguration;
import com.binarywang.solon.wxjava.store.config.storage.WxStoreInRedissonConfigStorageConfiguration;
import com.binarywang.solon.wxjava.store.properties.WxStoreProperties;
import org.noear.solon.core.AppContext;
import org.noear.solon.core.Plugin;

/**
 * @author noear 2024/9/2 created
 */
public class WxStorePluginImpl implements Plugin {
  @Override
  public void start(AppContext context) throws Throwable {
    context.beanMake(WxStoreProperties.class);
    context.beanMake(WxStoreServiceAutoConfiguration.class);

    context.beanMake(WxStoreInMemoryConfigStorageConfiguration.class);
    context.beanMake(WxStoreInJedisConfigStorageConfiguration.class);
    context.beanMake(WxStoreInRedissonConfigStorageConfiguration.class);
  }
}
