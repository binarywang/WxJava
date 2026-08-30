package com.binarywang.spring.starter.wxjava.store.enums;

/**
 * storage类型
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
public enum StorageType {
  /**
   * 内存
   */
  Memory,
  /**
   * redis(JedisClient)
   */
  Jedis,
  /**
   * redis(Redisson)
   */
  Redisson,
  /**
   * redis(RedisTemplate)
   */
  RedisTemplate
}
