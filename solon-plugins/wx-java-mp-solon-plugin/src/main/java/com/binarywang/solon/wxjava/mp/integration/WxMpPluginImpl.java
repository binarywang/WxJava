package com.binarywang.solon.wxjava.mp.integration;

import com.binarywang.solon.wxjava.mp.config.WxMpServiceAutoConfiguration;
import com.binarywang.solon.wxjava.mp.config.storage.WxMpInJedisConfigStorageConfiguration;
import com.binarywang.solon.wxjava.mp.config.storage.WxMpInMemoryConfigStorageConfiguration;
import com.binarywang.solon.wxjava.mp.config.storage.WxMpInRedissonConfigStorageConfiguration;
import com.binarywang.solon.wxjava.mp.properties.WxMpProperties;
import org.noear.solon.core.AppContext;
import org.noear.solon.core.Plugin;
import org.noear.solon.core.util.ClassUtil;

/**
 * @author noear 2024/9/2 created
 */
public class WxMpPluginImpl implements Plugin {
  @Override
  public void start(AppContext context) throws Throwable {
    context.beanMake(WxMpProperties.class);
    context.beanMake(WxMpServiceAutoConfiguration.class);

    context.beanMake(WxMpInMemoryConfigStorageConfiguration.class);
    if (ClassUtil.loadClass("redis.clients.jedis.Jedis") != null) {
      context.beanMake(WxMpInJedisConfigStorageConfiguration.class);
    }
    if (ClassUtil.loadClass("org.redisson.api.RedissonClient") != null) {
      context.beanMake(WxMpInRedissonConfigStorageConfiguration.class);
    }
  }
}
