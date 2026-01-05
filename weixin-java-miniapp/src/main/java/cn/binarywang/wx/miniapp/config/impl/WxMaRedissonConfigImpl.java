package cn.binarywang.wx.miniapp.config.impl;

import lombok.NonNull;
import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.redis.RedissonWxRedisOps;
import me.chanjar.weixin.common.redis.WxRedisOps;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Strings;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * 基于Redisson的实现
 *
 * @author yuanqixun
 * created on  2020/5/3
 */
public class WxMaRedissonConfigImpl extends WxMaDefaultConfigImpl {

  protected static final String LOCK_KEY = "wechat_ma_lock:";
  protected static final String MA_ACCESS_TOKEN_KEY = "wechat_ma_access_token_key:";
  protected static final String MA_JSAPI_TICKET_KEY = "wechat_ma_jsapi_ticket_key:";
  protected static final String MA_CARD_API_TICKET_KEY = "wechat_ma_card_api_ticket_key:";

  /**
   * redis 存储的 key 的前缀，可为空
   */
  protected String keyPrefix;
  protected String accessTokenKey;
  protected String jsapiTicketKey;
  protected String cardApiTicketKey;
  protected String lockKey;

  private final WxRedisOps redisOps;

  public WxMaRedissonConfigImpl(@NonNull RedissonClient redissonClient, String keyPrefix) {
    this(new RedissonWxRedisOps(redissonClient), keyPrefix);
  }

  public WxMaRedissonConfigImpl(@NonNull RedissonClient redissonClient) {
    this(redissonClient, null);
  }

  private WxMaRedissonConfigImpl(@NonNull WxRedisOps redisOps, String keyPrefix) {
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
    jsapiTicketKey = prefix + MA_JSAPI_TICKET_KEY.concat(appid);
    cardApiTicketKey = prefix + MA_CARD_API_TICKET_KEY.concat(appid);
  }

  protected Lock getLockByKey(String key) {
    return redisOps.getLock(key);
  }

  @Override
  public Lock getAccessTokenLock() {
    return getLockByKey(this.lockKey.concat(":").concat("accessToken"));
  }

  @Override
  public Lock getCardApiTicketLock() {
    return getLockByKey(this.lockKey.concat(":").concat("cardApiTicket"));

  }

  @Override
  public Lock getJsapiTicketLock() {
    return getLockByKey(this.lockKey.concat(":").concat("jsapiTicket"));
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
    updateAccessToken(accessToken.getAccessToken(), accessToken.getExpiresIn());
  }

  @Override
  public void updateAccessToken(String accessToken, int expiresInSeconds) {
    redisOps.setValue(this.accessTokenKey, accessToken, expiresInSeconds, TimeUnit.SECONDS);
  }

  @Override
  public String getJsapiTicket() {
    return redisOps.getValue(this.jsapiTicketKey);
  }

  @Override
  public boolean isJsapiTicketExpired() {
    Long expire = redisOps.getExpire(this.jsapiTicketKey);
    return expire == null || expire < 2;
  }

  @Override
  public void expireJsapiTicket() {
    redisOps.expire(this.jsapiTicketKey, 0, TimeUnit.SECONDS);
  }

  @Override
  public void updateJsapiTicket(String jsapiTicket, int expiresInSeconds) {
    redisOps.setValue(this.jsapiTicketKey, jsapiTicket, expiresInSeconds, TimeUnit.SECONDS);

  }

  @Override
  public String getCardApiTicket() {
    return redisOps.getValue(cardApiTicketKey);
  }

  @Override
  public boolean isCardApiTicketExpired() {
    Long expire = redisOps.getExpire(this.cardApiTicketKey);
    return expire == null || expire < 2;
  }

  @Override
  public void expireCardApiTicket() {
    redisOps.expire(this.cardApiTicketKey, 0, TimeUnit.SECONDS);
  }

  @Override
  public void updateCardApiTicket(String cardApiTicket, int expiresInSeconds) {
    redisOps.setValue(this.cardApiTicketKey, cardApiTicket, expiresInSeconds, TimeUnit.SECONDS);
  }

  @Override
  public void expireAccessToken() {
    redisOps.expire(this.accessTokenKey, 0, TimeUnit.SECONDS);
  }

  @Override
  public long getExpiresTime() {
    return redisOps.getExpire(this.accessTokenKey);
  }
}
