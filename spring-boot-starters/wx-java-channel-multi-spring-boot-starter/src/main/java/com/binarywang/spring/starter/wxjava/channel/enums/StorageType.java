package com.binarywang.spring.starter.wxjava.channel.enums;

/**
 * storage类型
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 * @date 2024/9/13
 */
public enum StorageType {
  /**
   * 内存
   */
  MEMORY,
  /**
   * redis(JedisClient)
   */
  JEDIS,
  /**
   * redis(Redisson)
   */
  REDISSON,
  /**
   * redis(RedisTemplate)
   */
  REDIS_TEMPLATE
}
