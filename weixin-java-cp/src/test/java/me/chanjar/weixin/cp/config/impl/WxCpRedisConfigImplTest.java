package me.chanjar.weixin.cp.config.impl;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;
import redis.clients.jedis.JedisPool;

/**
 * WxCpRedisConfigImpl 测试类
 */
public class WxCpRedisConfigImplTest {

  /**
   * 测试 getWebhookKey 方法不会导致无限递归
   * 这个测试验证了 #issue 中提到的无限递归问题已被修复
   */
  @Test
  public void testGetWebhookKeyNoInfiniteRecursion() {
    // 使用 Mockito 创建 mock JedisPool，避免真实连接
    JedisPool jedisPool = Mockito.mock(JedisPool.class);

    WxCpRedisConfigImpl config = new WxCpRedisConfigImpl(jedisPool);

    // 测试1: webhookKey 为 null 时应该返回 null，而不是抛出 StackOverflowError
    String webhookKey = config.getWebhookKey();
    Assert.assertNull(webhookKey, "未设置 webhookKey 时应返回 null");

    // 测试2: 通过反射设置 webhookKey，然后验证能够正确获取
    // 注意：由于 WxCpRedisConfigImpl 没有提供 setWebhookKey 方法，
    // 我们通过反射来设置这个字段以测试 getter 的正确性
    try {
      java.lang.reflect.Field field = WxCpRedisConfigImpl.class.getDeclaredField("webhookKey");
      boolean originalAccessible = field.isAccessible();
      field.setAccessible(true);
      try {
        String testWebhookKey = "test-webhook-key-123";
        field.set(config, testWebhookKey);

        String retrievedKey = config.getWebhookKey();
        Assert.assertEquals(retrievedKey, testWebhookKey, "应该返回设置的 webhookKey 值");
      } finally {
        field.setAccessible(originalAccessible);
      }
    } catch (NoSuchFieldException | IllegalAccessException e) {
      Assert.fail("反射设置 webhookKey 失败: " + e.getMessage());
    }
  }
}
