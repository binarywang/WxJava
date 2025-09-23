package me.chanjar.weixin.cp.config.impl;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import me.chanjar.weixin.common.redis.RedissonWxRedisOps;
import org.redisson.api.RedissonClient;

/**
 * 基于Redisson的实现
 *
 * @author yuanqixun  created on  2020 /5/13
 * @author yl
 */
public class WxCpTpRedissonConfigImpl extends AbstractWxCpTpInRedisConfigImpl {
  private static final long serialVersionUID = -5674792341070783967L;

  public WxCpTpRedissonConfigImpl(@NonNull RedissonClient redissonClient) {
    this(redissonClient, null);
  }

  public WxCpTpRedissonConfigImpl(@NonNull RedissonClient redissonClient, String keyPrefix) {
    super(new RedissonWxRedisOps(redissonClient), keyPrefix);
  }
}
