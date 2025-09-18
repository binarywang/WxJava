package me.chanjar.weixin.common.util.locks;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.testng.Assert.*;

@Slf4j
@Test(enabled = true)
public class RedisTemplateSimpleDistributedLockTest {

  private static final String KEY_PREFIX = "System:";
  RedisTemplateSimpleDistributedLock redisLock;

  StringRedisTemplate redisTemplate;

  AtomicInteger lockCurrentExecuteCounter;

  @BeforeTest
  public void init() {
    JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
    connectionFactory.setHostName("127.0.0.1");
    connectionFactory.setPort(6379);
    connectionFactory.afterPropertiesSet();
    StringRedisTemplate redisTemplate = new StringRedisTemplate(connectionFactory);
    // 自定义序列化器，为 key 自动加前缀
    redisTemplate.setKeySerializer(new StringRedisSerializer() {
      @NotNull
      @Override
      public byte[] serialize(String string) {
        if (string == null) {
          return super.serialize(null);
        }
        // 添加前缀
        return super.serialize(KEY_PREFIX + string);
      }

      @NotNull
      @Override
      public String deserialize(byte[] bytes) {
        String key = super.deserialize(bytes);
        if (key.startsWith(KEY_PREFIX)) {
          return key.substring(KEY_PREFIX.length());
        }
        return key;
      }
    });
    this.redisTemplate = redisTemplate;
    this.redisLock = new RedisTemplateSimpleDistributedLock(redisTemplate, 60000);
    this.lockCurrentExecuteCounter = new AtomicInteger(0);
  }

  @Test(description = "多线程测试锁排他性")
  public void testLockExclusive() throws InterruptedException {
    int threadSize = 100;
    final CountDownLatch startLatch = new CountDownLatch(threadSize);
    final CountDownLatch endLatch = new CountDownLatch(threadSize);

    for (int i = 0; i < threadSize; i++) {
      new Thread(() -> {
        try {
          startLatch.await();
        } catch (InterruptedException e) {
          log.error("unexpected exception", e);
        }

        redisLock.lock();
        assertEquals(lockCurrentExecuteCounter.incrementAndGet(), 1, "临界区同时只能有一个线程执行");
        lockCurrentExecuteCounter.decrementAndGet();
        redisLock.unlock();

        endLatch.countDown();
      }).start();
      startLatch.countDown();
    }
    endLatch.await();
  }

  @Test
  public void testTryLock() throws InterruptedException {
    assertTrue(redisLock.tryLock(3, TimeUnit.SECONDS), "第一次加锁应该成功");
    assertNotNull(redisLock.getLockSecretValue());
    String redisValue = this.redisTemplate.opsForValue().get(redisLock.getKey());
    assertEquals(redisValue, redisLock.getLockSecretValue());

    redisLock.unlock();
    assertNull(redisLock.getLockSecretValue());
    redisValue = this.redisTemplate.opsForValue().get(redisLock.getKey());
    assertNull(redisValue, "释放锁后key会被删除");

    redisLock.unlock();
  }


}

