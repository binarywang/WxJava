package me.chanjar.weixin.channel.config.impl;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import lombok.NonNull;
import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.redis.RedissonWxRedisOps;
import me.chanjar.weixin.common.redis.WxRedisOps;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Strings;
import org.redisson.api.RedissonClient;

/**
 * 基于Redisson的实现
 *
 * @author yuanqixun
 * created on  2020/5/3
 */
public class WxChannelRedissonConfigImpl extends WxChannelDefaultConfigImpl {

  protected static final String LOCK_KEY = "wx_channel_lock:";
  protected static final String MA_ACCESS_TOKEN_KEY = "wx_channel_access_token_key:";

  /**
   * redis 存储的 key 的前缀，可为空
   */
  protected String keyPrefix;
  protected String accessTokenKey;
  protected String lockKey;

  private final WxRedisOps redisOps;

  public WxChannelRedissonConfigImpl(@NonNull RedissonClient redissonClient, String keyPrefix) {
    this(new RedissonWxRedisOps(redissonClient), keyPrefix);
  }

  public WxChannelRedissonConfigImpl(@NonNull RedissonClient redissonClient) {
    this(redissonClient, null);
  }

  private WxChannelRedissonConfigImpl(@NonNull WxRedisOps redisOps, String keyPrefix) {
    this.redisOps = redisOps;
    this.keyPrefix = keyPrefix;
  }

  @Override
  public void setAppid(String appid) {
    super.setAppid(appid);
    String prefix = StringUtils.isBlank(keyPrefix) ? "" :
      (Strings.CS.endsWith(keyPrefix, ":") ? keyPrefix : (keyPrefix + ":"));
    lockKey = prefix + LOCK_KEY.concat(appid);
    accessTokenKey = prefix + MA_ACCESS_TOKEN_KEY.concat(appid);
  }

  protected Lock getLockByKey(String key) {
    return redisOps.getLock(key);
  }

  @Override
  public Lock getAccessTokenLock() {
    return getLockByKey(this.lockKey.concat(":").concat("accessToken"));
  }

  @Override
  public String getAccessToken() {
    return redisOps.getValue(this.accessTokenKey);
  }

  @Override
  public boolean isAccessTokenExpired() {
    Long expire = redisOps.getExpire(this.accessTokenKey);
    return expire == null || expire < 2;
  }

  @Override
  public void updateAccessToken(WxAccessToken accessToken) {
    redisOps.setValue(this.accessTokenKey, accessToken.getAccessToken(), accessToken.getExpiresIn(), TimeUnit.SECONDS);
  }

  @Override
  public void updateAccessToken(String accessToken, int expiresInSeconds) {
    redisOps.setValue(this.accessTokenKey, accessToken, expiresInSeconds, TimeUnit.SECONDS);
  }

  @Override
  public void expireAccessToken() {
    redisOps.expire(this.accessTokenKey, 0, TimeUnit.SECONDS);
  }

}
