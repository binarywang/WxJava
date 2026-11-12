package com.binarywang.spring.starter.wxjava.mp.config.storage;

import com.binarywang.spring.starter.wxjava.mp.config.WxMpAutoConfiguration;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.MapPropertySource;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertSame;

public class WxMpInRedissonConfigStorageConfigurationTest {
  private static final String CLIENT_BEAN_NAME = "wxMpRedissonClient";

  @Test
  public void reusesExistingClientWithoutDedicatedHost() {
    RedissonClient sharedClient = mock(RedissonClient.class);
    try (AnnotationConfigApplicationContext context = context(null, sharedClient)) {
      context.refresh();

      assertFalse(context.containsBean(CLIENT_BEAN_NAME));
      verify(sharedClient, atLeastOnce()).getLock(anyString());
    }
  }

  @Test
  public void usesDedicatedClientWithExplicitHost() {
    RedissonClient sharedClient = mock(RedissonClient.class);
    RedissonClient dedicatedClient = mock(RedissonClient.class);
    try (AnnotationConfigApplicationContext context = context("redis.example.test", sharedClient)) {
      // Verify that Spring registers the dedicated bean without opening a Redis connection.
      context.addBeanFactoryPostProcessor(beanFactory -> {
        AbstractBeanDefinition definition = (AbstractBeanDefinition) beanFactory.getBeanDefinition(CLIENT_BEAN_NAME);
        definition.setInstanceSupplier(() -> dedicatedClient);
      });
      context.refresh();

      assertSame(context.getBean(CLIENT_BEAN_NAME), dedicatedClient);
      verify(dedicatedClient, atLeastOnce()).getLock(anyString());
    }
  }

  private AnnotationConfigApplicationContext context(String host, RedissonClient sharedClient) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    Map<String, Object> properties = new HashMap<>();
    properties.put("wx.mp.app-id", "test-app");
    properties.put("wx.mp.config-storage.type", "redisson");
    if (host != null) {
      properties.put("wx.mp.config-storage.redis.host", host);
    }
    context.getEnvironment().getPropertySources().addFirst(new MapPropertySource("test", properties));
    context.registerBean("sharedRedissonClient", RedissonClient.class, () -> sharedClient);
    context.register(WxMpAutoConfiguration.class);
    return context;
  }
}
