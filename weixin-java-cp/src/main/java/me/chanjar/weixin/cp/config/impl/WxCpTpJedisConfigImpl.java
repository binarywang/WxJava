package me.chanjar.weixin.cp.config.impl;

import lombok.NonNull;
import me.chanjar.weixin.common.redis.JedisWxRedisOps;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.util.Pool;

/**
 * 基于 jedis 的实现
 *
 * @author yl
 * created on  2023/04/23
 */
public class WxCpTpJedisConfigImpl extends AbstractWxCpTpInRedisConfigImpl {
  private static final long serialVersionUID = -1869372247414407433L;

  public WxCpTpJedisConfigImpl(Pool<Jedis> jedisPool) {
    this(jedisPool, null);
  }

  public WxCpTpJedisConfigImpl(@NonNull Pool<Jedis> jedisPool, String keyPrefix) {
    super(new JedisWxRedisOps(jedisPool), keyPrefix);
  }
}
