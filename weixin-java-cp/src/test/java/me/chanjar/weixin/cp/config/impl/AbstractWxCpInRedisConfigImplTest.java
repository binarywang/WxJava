package me.chanjar.weixin.cp.config.impl;

import me.chanjar.weixin.common.redis.WxRedisOps;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试 AbstractWxCpInRedisConfigImpl 对 Redis 异常的容错处理
 *
 * @author GitHub Copilot
 */
public class AbstractWxCpInRedisConfigImplTest {

  private WxRedisOps mockRedisOps;
  private AbstractWxCpInRedisConfigImpl config;

  @BeforeMethod
  public void setUp() {
    mockRedisOps = Mockito.mock(WxRedisOps.class);
    Mockito.when(mockRedisOps.getLock(Mockito.anyString()))
      .thenReturn(new ReentrantLock());

    config = new AbstractWxCpInRedisConfigImpl(mockRedisOps, "test") {
      // 使用匿名类提供具体实现用于测试
    };
    config.setCorpId("testCorpId");
    config.setAgentId(1);
  }

  /**
   * 测试当 Redis getExpire 抛出异常时，isAccessTokenExpired() 应返回 true（视为已过期），且不影响线程中断标志
   */
  @Test
  public void testIsAccessTokenExpiredWhenRedisThrowsException() {
    Mockito.when(mockRedisOps.getExpire(Mockito.anyString()))
      .thenThrow(new RuntimeException("Redis command interrupted"));

    boolean expired = config.isAccessTokenExpired();

    Assert.assertTrue(expired, "Redis异常时应将token视为已过期");
    // 非中断相关异常不应影响线程中断标志
    Assert.assertFalse(Thread.currentThread().isInterrupted(), "非中断异常时线程中断标志不应被改变");
  }

  /**
   * 测试当线程中断状态已设置时，Redis 调用抛出中断相关异常，isAccessTokenExpired() 应处理并清除中断标志
   */
  @Test
  public void testIsAccessTokenExpiredClearsInterruptedFlag() {
    // 使用包含 InterruptedException cause 的异常，模拟 Lettuce 的 RedisCommandInterruptedException 行为
    Mockito.when(mockRedisOps.getExpire(Mockito.anyString()))
      .thenThrow(new RuntimeException("wrapped", new InterruptedException("command interrupted")));

    Thread.currentThread().interrupt();
    try {
      boolean expired = config.isAccessTokenExpired();

      Assert.assertTrue(expired, "Redis中断异常时应将token视为已过期");
      // 中断标志应该被清除，允许后续操作正常进行
      Assert.assertFalse(Thread.currentThread().isInterrupted(), "中断相关异常处理后线程中断标志应被清除");
    } finally {
      // 兜底清除当前线程的中断标志，避免影响后续测试用例
      Thread.interrupted();
    }
  }

  /**
   * 测试正常情况下 isAccessTokenExpired() 的行为
   */
  @Test
  public void testIsAccessTokenExpiredWhenTokenValid() {
    // 返回60秒后过期（未过期）
    Mockito.when(mockRedisOps.getExpire(Mockito.anyString())).thenReturn(60L);

    boolean expired = config.isAccessTokenExpired();

    Assert.assertFalse(expired, "token未过期时应返回false");
  }

  /**
   * 测试 isAccessTokenExpired() 当 expire 为 null 时视为已过期
   */
  @Test
  public void testIsAccessTokenExpiredWhenExpireIsNull() {
    Mockito.when(mockRedisOps.getExpire(Mockito.anyString())).thenReturn(null);

    boolean expired = config.isAccessTokenExpired();

    Assert.assertTrue(expired, "expire为null时应视为已过期");
  }

  /**
   * 测试当 Redis getExpire 抛出异常时，isJsapiTicketExpired() 应返回 true（视为已过期），且不影响线程中断标志
   */
  @Test
  public void testIsJsapiTicketExpiredWhenRedisThrowsException() {
    Mockito.when(mockRedisOps.getExpire(Mockito.anyString()))
      .thenThrow(new RuntimeException("Redis command interrupted"));

    boolean expired = config.isJsapiTicketExpired();

    Assert.assertTrue(expired, "Redis异常时应将jsapi_ticket视为已过期");
    Assert.assertFalse(Thread.currentThread().isInterrupted(), "非中断异常时线程中断标志不应被改变");
  }

  /**
   * 测试当 Redis getExpire 抛出异常时，isAgentJsapiTicketExpired() 应返回 true（视为已过期），且不影响线程中断标志
   */
  @Test
  public void testIsAgentJsapiTicketExpiredWhenRedisThrowsException() {
    Mockito.when(mockRedisOps.getExpire(Mockito.anyString()))
      .thenThrow(new RuntimeException("Redis command interrupted"));

    boolean expired = config.isAgentJsapiTicketExpired();

    Assert.assertTrue(expired, "Redis异常时应将agent_jsapi_ticket视为已过期");
    Assert.assertFalse(Thread.currentThread().isInterrupted(), "非中断异常时线程中断标志不应被改变");
  }

  /**
   * 测试当线程中断状态已设置时，Redis 调用抛出中断相关异常，isAgentJsapiTicketExpired() 应处理并清除中断标志
   */
  @Test
  public void testIsAgentJsapiTicketExpiredClearsInterruptedFlag() {
    Mockito.when(mockRedisOps.getExpire(Mockito.anyString()))
      .thenThrow(new RuntimeException("wrapped", new InterruptedException("command interrupted")));

    Thread.currentThread().interrupt();
    try {
      boolean expired = config.isAgentJsapiTicketExpired();

      Assert.assertTrue(expired, "Redis中断异常时应将agent_jsapi_ticket视为已过期");
      Assert.assertFalse(Thread.currentThread().isInterrupted(), "中断相关异常处理后线程中断标志应被清除");
    } finally {
      Thread.interrupted();
    }
  }

  /**
   * 测试提供自定义 Lock 实现时 getAccessTokenLock() 返回正确的锁
   */
  @Test
  public void testGetAccessTokenLockReturnsMockedLock() {
    Lock mockLock = Mockito.mock(Lock.class);
    Mockito.when(mockRedisOps.getLock(Mockito.anyString())).thenReturn(mockLock);

    Lock lock = config.getAccessTokenLock();

    Assert.assertNotNull(lock, "获取到的锁不应为null");
  }
}
