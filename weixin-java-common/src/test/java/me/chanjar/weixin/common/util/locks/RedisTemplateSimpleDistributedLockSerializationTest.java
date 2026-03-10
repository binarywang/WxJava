package me.chanjar.weixin.common.util.locks;

import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * 测试 RedisTemplateSimpleDistributedLock 在自定义 Key 序列化时的兼容性
 * 
 * 这个测试验证修复后的实现确保 tryLock 和 unlock 使用一致的键序列化方式
 */
@Test(enabled = false) // 默认禁用，需要Redis实例才能运行
public class RedisTemplateSimpleDistributedLockSerializationTest {

  private RedisTemplateSimpleDistributedLock redisLock;
  private StringRedisTemplate redisTemplate;

  @BeforeTest
  public void init() {
    JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
    connectionFactory.setHostName("127.0.0.1");
    connectionFactory.setPort(6379);
    connectionFactory.afterPropertiesSet();
    
    // 创建一个带自定义键序列化的 StringRedisTemplate
    StringRedisTemplate redisTemplate = new StringRedisTemplate(connectionFactory);
    
    // 使用自定义键序列化器，模拟在键前面添加前缀的场景
    redisTemplate.setKeySerializer(new StringRedisSerializer() {
      @Override
      public byte[] serialize(String string) {
        if (string == null) return null;
        // 添加 "System:" 前缀，模拟用户自定义的键序列化
        return super.serialize("System:" + string);
      }
      
      @Override
      public String deserialize(byte[] bytes) {
        if (bytes == null) return null;
        String result = super.deserialize(bytes);
        // 移除前缀进行反序列化
        return result != null && result.startsWith("System:") ? result.substring(7) : result;
      }
    });
    
    this.redisTemplate = redisTemplate;
    this.redisLock = new RedisTemplateSimpleDistributedLock(redisTemplate, "test_lock_key", 60000);
  }

  @Test(description = "测试自定义键序列化器下的锁操作一致性")
  public void testLockConsistencyWithCustomKeySerializer() {
    // 1. 获取锁应该成功
    assertTrue(redisLock.tryLock(), "第一次获取锁应该成功");
    assertNotNull(redisLock.getLockSecretValue(), "锁值应该存在");
    
    // 2. 验证键已正确存储（通过 redisTemplate 直接查询）
    String actualValue = redisTemplate.opsForValue().get("test_lock_key");
    assertEquals(actualValue, redisLock.getLockSecretValue(), "通过 redisTemplate 查询的值应该与锁值相同");
    
    // 3. 再次尝试获取同一把锁应该成功（可重入）
    assertTrue(redisLock.tryLock(), "可重入锁应该再次获取成功");
    
    // 4. 释放锁应该成功
    redisLock.unlock();
    assertNull(redisLock.getLockSecretValue(), "释放锁后锁值应该为空");
    
    // 5. 验证键已被删除
    actualValue = redisTemplate.opsForValue().get("test_lock_key");
    assertNull(actualValue, "释放锁后 Redis 中的键应该被删除");
    
    // 6. 释放已释放的锁应该是安全的
    redisLock.unlock(); // 不应该抛出异常
  }
  
  @Test(description = "测试不同线程使用相同键的锁排他性")
  public void testLockExclusivityWithCustomKeySerializer() throws InterruptedException {
    // 第一个锁实例获取锁
    assertTrue(redisLock.tryLock(), "第一个锁实例应该成功获取锁");
    
    // 创建第二个锁实例使用相同的键
    RedisTemplateSimpleDistributedLock anotherLock = new RedisTemplateSimpleDistributedLock(
        redisTemplate, "test_lock_key", 60000);
    
    // 第二个锁实例不应该能获取锁
    assertFalse(anotherLock.tryLock(), "第二个锁实例不应该能获取已被占用的锁");
    
    // 释放第一个锁
    redisLock.unlock();
    
    // 现在第二个锁实例应该能获取锁
    assertTrue(anotherLock.tryLock(), "第一个锁释放后，第二个锁实例应该能获取锁");
    
    // 清理
    anotherLock.unlock();
  }
}