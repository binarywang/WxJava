package me.chanjar.weixin.open.api.impl;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

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
public class WxOpenInRedisTemplateConfigStorage extends AbstractWxOpenInRedisConfigStorage {

  private final WxRedisOps redisOps;

  public WxOpenInRedisTemplateConfigStorage(@NonNull StringRedisTemplate stringRedisTemplate, String keyPrefix) {
    this(new RedisTemplateWxRedisOps(stringRedisTemplate), keyPrefix);
  }

  public WxOpenInRedisTemplateConfigStorage(@NonNull WxRedisOps redisOps, String keyPrefix) {
    this.redisOps = redisOps;
    this.keyPrefix = keyPrefix;
  }

  @Override
  public String getComponentVerifyTicket() {
    return redisOps.getValue(this.componentVerifyTicketKey);
  }

  @Override
  public void setComponentVerifyTicket(String componentVerifyTicket) {
    redisOps.setValue(this.componentVerifyTicketKey, componentVerifyTicket, 43200, TimeUnit.SECONDS);
  }

  @Override
  public String getComponentAccessToken() {
    return redisOps.getValue(this.componentAccessTokenKey);
  }

  @Override
  public boolean isComponentAccessTokenExpired() {
    Long expire = redisOps.getExpire(this.componentAccessTokenKey);
    return expire == null || expire < 2;
  }

  @Override
  public void expireComponentAccessToken() {
    redisOps.expire(this.componentAccessTokenKey, 0, TimeUnit.SECONDS);
  }

  @Override
  public void updateComponentAccessToken(String componentAccessToken, int expiresInSeconds) {
    redisOps.setValue(this.componentAccessTokenKey, componentAccessToken, expiresInSeconds - 200, TimeUnit.SECONDS);
  }

  @Override
  public String getAuthorizerRefreshToken(String appId) {
    return redisOps.getValue(this.getKey(this.authorizerRefreshTokenKey, appId));
  }

  @Override
  public void setAuthorizerRefreshToken(String appId, String authorizerRefreshToken) {
    redisOps.setValue(this.getKey(this.authorizerRefreshTokenKey, appId), authorizerRefreshToken, 0, TimeUnit.SECONDS);
  }

  @Override
  public String getAuthorizerAccessToken(String appId) {
    return redisOps.getValue(this.getKey(this.authorizerAccessTokenKey, appId));
  }

  @Override
  public boolean isAuthorizerAccessTokenExpired(String appId) {
    Long expire = redisOps.getExpire(this.getKey(this.authorizerAccessTokenKey, appId));
    return expire == null || expire < 2;
  }

  @Override
  public void expireAuthorizerAccessToken(String appId) {
    redisOps.expire(this.getKey(this.authorizerAccessTokenKey, appId), 0, TimeUnit.SECONDS);
  }

  @Override
  public void updateAuthorizerAccessToken(String appId, String authorizerAccessToken, int expiresInSeconds) {
    redisOps.setValue(this.getKey(this.authorizerAccessTokenKey, appId), authorizerAccessToken, expiresInSeconds - 200, TimeUnit.SECONDS);
  }

  @Override
  public String getJsapiTicket(String appId) {
    return redisOps.getValue(this.getKey(this.jsapiTicketKey, appId));
  }

  @Override
  public boolean isJsapiTicketExpired(String appId) {
    Long expire = redisOps.getExpire(this.getKey(this.jsapiTicketKey, appId));
    return expire == null || expire < 2;
  }

  @Override
  public void expireJsapiTicket(String appId) {
    redisOps.expire(this.getKey(this.jsapiTicketKey, appId), 0, TimeUnit.SECONDS);
  }

  @Override
  public void updateJsapiTicket(String appId, String jsapiTicket, int expiresInSeconds) {
    redisOps.setValue(this.getKey(this.jsapiTicketKey, appId), jsapiTicket, expiresInSeconds - 200, TimeUnit.SECONDS);
  }

  @Override
  public String getCardApiTicket(String appId) {
    return redisOps.getValue(this.getKey(this.cardApiTicket, appId));
  }

  @Override
  public boolean isCardApiTicketExpired(String appId) {
    Long expire = redisOps.getExpire(this.getKey(this.cardApiTicket, appId));
    return expire == null || expire < 2;
  }

  @Override
  public void expireCardApiTicket(String appId) {
    redisOps.expire(this.getKey(this.cardApiTicket, appId), 0, TimeUnit.SECONDS);
  }

  @Override
  public void updateCardApiTicket(String appId, String cardApiTicket, int expiresInSeconds) {
    redisOps.setValue(this.getKey(this.cardApiTicket, appId), cardApiTicket, expiresInSeconds - 200, TimeUnit.SECONDS);
  }

  @Override
  public Lock getLockByKey(String key) {
    return redisOps.getLock(key);
  }
}
