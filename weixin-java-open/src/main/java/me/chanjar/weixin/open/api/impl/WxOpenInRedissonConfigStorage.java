package me.chanjar.weixin.open.api.impl;

import lombok.NonNull;
import me.chanjar.weixin.common.redis.RedissonWxRedisOps;
import me.chanjar.weixin.common.redis.WxRedisOps;
import org.redisson.api.RedissonClient;

/**
 * @author yangyidian
 * created on  2020/01/06
 **/
public class WxOpenInRedissonConfigStorage extends AbstractWxOpenInRedisOpsConfigStorage {

  public WxOpenInRedissonConfigStorage(@NonNull RedissonClient redissonClient, String keyPrefix) {
    this(new RedissonWxRedisOps(redissonClient), keyPrefix);
  }

  public WxOpenInRedissonConfigStorage(@NonNull RedissonClient redissonClient) {
    this(redissonClient, null);
  }

  private WxOpenInRedissonConfigStorage(@NonNull WxRedisOps redisOps, String keyPrefix) {
    super(redisOps, keyPrefix);
  }
}
