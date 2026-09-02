package me.chanjar.weixin.open.api.impl;

import lombok.NonNull;
import me.chanjar.weixin.common.redis.JedisWxRedisOps;
import me.chanjar.weixin.common.redis.WxRedisOps;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.util.Pool;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 */
public class WxOpenInRedisConfigStorage extends AbstractWxOpenInRedisOpsConfigStorage {

  public WxOpenInRedisConfigStorage(Pool<Jedis> jedisPool) {
    this(jedisPool, null);
  }

  public WxOpenInRedisConfigStorage(@NonNull Pool<Jedis> jedisPool, String keyPrefix) {
    this(new JedisWxRedisOps(jedisPool), keyPrefix);
  }

  public WxOpenInRedisConfigStorage(@NonNull WxRedisOps redisOps, String keyPrefix) {
    super(redisOps, keyPrefix);
  }
}
