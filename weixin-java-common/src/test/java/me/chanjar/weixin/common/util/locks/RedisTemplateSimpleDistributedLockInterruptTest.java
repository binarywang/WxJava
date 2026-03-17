package me.chanjar.weixin.common.util.locks;

import org.mockito.Mockito;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 测试 RedisTemplateSimpleDistributedLock 的线程中断处理行为
 *
 * @author GitHub Copilot
 */
public class RedisTemplateSimpleDistributedLockInterruptTest {

  private StringRedisTemplate mockRedisTemplate;
  private ValueOperations<String, String> mockValueOps;
  private RedisTemplateSimpleDistributedLock lock;

  @BeforeMethod
  @SuppressWarnings("unchecked")
  public void setUp() {
    mockRedisTemplate = Mockito.mock(StringRedisTemplate.class);
    mockValueOps = Mockito.mock(ValueOperations.class);
    Mockito.when(mockRedisTemplate.opsForValue()).thenReturn(mockValueOps);
    lock = new RedisTemplateSimpleDistributedLock(mockRedisTemplate, "test_interrupt_lock", 60000);
  }

  /**
   * 测试 lock() 在 Thread.sleep 被中断时应恢复线程中断标志
   * <p>
   * 修复前：InterruptedException 被忽略（// Ignore），线程中断标志丢失
   * 修复后：调用 Thread.currentThread().interrupt() 恢复中断标志
   * </p>
   */
  @Test(description = "lock() 方法在中断时应恢复线程中断标志")
  public void testLockRestoresInterruptedFlagAfterSleepInterruption() throws InterruptedException {
    AtomicBoolean interruptedFlagAfterLock = new AtomicBoolean(false);

    // 第一次 setIfAbsent 返回 false（模拟锁被占用），第二次返回 true（模拟锁释放）
    Mockito.when(mockValueOps.setIfAbsent(Mockito.anyString(), Mockito.anyString(),
        Mockito.anyLong(), Mockito.any(TimeUnit.class)))
      .thenReturn(false)
      .thenReturn(true);
    // get() 返回不同的值，确保不走可重入路径
    Mockito.when(mockValueOps.get(Mockito.anyString())).thenReturn("other-value");

    Thread testThread = new Thread(() -> {
      // 设置中断标志
      Thread.currentThread().interrupt();
      // 调用 lock()，第一次 tryLock 失败，sleep 会因中断标志立即抛出 InterruptedException
      lock.lock();
      interruptedFlagAfterLock.set(Thread.currentThread().isInterrupted());
    });

    testThread.start();
    testThread.join(5000);

    // 线程应该已经完成（不会永远阻塞）
    Assert.assertFalse(testThread.isAlive(), "线程应该已完成");
    // 关键验证：中断标志应被恢复（而非被忽略丢失）
    Assert.assertTrue(interruptedFlagAfterLock.get(), "lock()执行后线程中断标志应被恢复");
  }

  /**
   * 测试 tryLock() 在 Redis 正常响应时的基本行为
   */
  @Test(description = "tryLock() 成功获取锁时应返回 true")
  public void testTryLockSuccessfully() {
    Mockito.when(mockValueOps.setIfAbsent(Mockito.anyString(), Mockito.anyString(),
        Mockito.anyLong(), Mockito.any(TimeUnit.class)))
      .thenReturn(true);

    boolean result = lock.tryLock();

    Assert.assertTrue(result, "应成功获取锁");
    Assert.assertNotNull(lock.getLockSecretValue(), "锁值不应为null");
  }

  /**
   * 测试 tryLock() 在锁已被其他线程持有时应返回 false
   */
  @Test(description = "锁被占用时 tryLock() 应返回 false")
  public void testTryLockWhenLockHeld() {
    Mockito.when(mockValueOps.setIfAbsent(Mockito.anyString(), Mockito.anyString(),
        Mockito.anyLong(), Mockito.any(TimeUnit.class)))
      .thenReturn(false);
    Mockito.when(mockValueOps.get(Mockito.anyString())).thenReturn("other-lock-value");

    boolean result = lock.tryLock();

    Assert.assertFalse(result, "锁被占用时应返回false");
  }
}
