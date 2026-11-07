package me.chanjar.weixin.open.api.impl;

import org.springframework.data.redis.core.StringRedisTemplate;

import lombok.NonNull;
import me.chanjar.weixin.common.redis.RedisTemplateWxRedisOps;
import me.chanjar.weixin.common.redis.WxRedisOps;

/**
 * <pre>
 *     RedisTemplateConfigStorage
 * </pre>
 *
 * @author taneg
 * created on  2021/05/13 11:12:35
 */
public class WxOpenInRedisTemplateConfigStorage extends AbstractWxOpenInRedisOpsConfigStorage {

  public WxOpenInRedisTemplateConfigStorage(@NonNull StringRedisTemplate stringRedisTemplate, String keyPrefix) {
    this(new RedisTemplateWxRedisOps(stringRedisTemplate), keyPrefix);
  }

  public WxOpenInRedisTemplateConfigStorage(@NonNull WxRedisOps redisOps, String keyPrefix) {
    super(redisOps, keyPrefix);
  }
}
