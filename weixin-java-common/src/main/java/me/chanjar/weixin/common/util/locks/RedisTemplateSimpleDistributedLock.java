package me.chanjar.weixin.common.util.locks;

import lombok.Getter;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.core.types.Expiration;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 实现简单的redis分布式锁, 支持重入, 不是红锁
 *
 * @see <a href="https://redis.io/topics/distlock">reids distlock</a>
 */
public class RedisTemplateSimpleDistributedLock implements Lock {

  @Getter
  private final StringRedisTemplate redisTemplate;
  @Getter
  private final String key;
  @Getter
  private final int leaseMilliseconds;

  private final ThreadLocal<String> valueThreadLocal = new ThreadLocal<>();

  public RedisTemplateSimpleDistributedLock( StringRedisTemplate redisTemplate, int leaseMilliseconds) {
    this(redisTemplate, "lock:" + UUID.randomUUID().toString(), leaseMilliseconds);
  }

  public RedisTemplateSimpleDistributedLock( StringRedisTemplate redisTemplate,  String key, int leaseMilliseconds) {
    if (leaseMilliseconds <= 0) {
      throw new IllegalArgumentException("Parameter 'leaseMilliseconds' must grate then 0: " + leaseMilliseconds);
    }
    this.redisTemplate = redisTemplate;
    this.key = key;
    this.leaseMilliseconds = leaseMilliseconds;
  }

  @Override
  public void lock() {
    while (!tryLock()) {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        // Ignore
      }
    }
  }

  @Override
  public void lockInterruptibly() throws InterruptedException {
    while (!tryLock()) {
      Thread.sleep(1000);
    }
  }

  @Override
  public boolean tryLock() {
    String value = valueThreadLocal.get();
    if (value == null || value.isEmpty()) {
      value = UUID.randomUUID().toString();
      valueThreadLocal.set(value);
    }
    final byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
    final byte[] valueBytes = value.getBytes(StandardCharsets.UTF_8);
    List<Object> redisResults = redisTemplate.executePipelined((RedisCallback<String>) connection -> {
      connection.set(keyBytes, valueBytes, Expiration.milliseconds(leaseMilliseconds), RedisStringCommands.SetOption.SET_IF_ABSENT);
      connection.get(keyBytes);
      return null;
    });
    Object currentLockSecret = redisResults.size() > 1 ? redisResults.get(1) : redisResults.get(0);
    return currentLockSecret != null && currentLockSecret.toString().equals(value);
  }

  @Override
  public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
    long waitMs = unit.toMillis(time);
    boolean locked = tryLock();
    while (!locked && waitMs > 0) {
      long sleep = waitMs < 1000 ? waitMs : 1000;
      Thread.sleep(sleep);
      waitMs -= sleep;
      locked = tryLock();
    }
    return locked;
  }

  @Override
  public void unlock() {
    if (valueThreadLocal.get() != null) {
      // 提示: 必须指定returnType, 类型: 此处必须为Long, 不能是Integer
      RedisScript<Long> script = new DefaultRedisScript<>("if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end", Long.class);
      redisTemplate.execute(script, Collections.singletonList(key), valueThreadLocal.get());
      valueThreadLocal.remove();
    }
  }

  @Override
  public Condition newCondition() {
    throw new UnsupportedOperationException();
  }

  /**
   * 获取当前锁的值
   * return 返回null意味着没有加锁, 但是返回非null值并不以为着当前加锁成功(redis中key可能自动过期)
   */
  public String getLockSecretValue() {
    return valueThreadLocal.get();
  }
}
