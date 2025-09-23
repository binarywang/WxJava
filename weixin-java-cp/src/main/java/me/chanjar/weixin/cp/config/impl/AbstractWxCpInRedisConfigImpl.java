package me.chanjar.weixin.cp.config.impl;

import lombok.NonNull;
import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.redis.WxRedisOps;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @author yl
 * created on  2023/04/23
 */
public abstract class AbstractWxCpInRedisConfigImpl extends WxCpDefaultConfigImpl {
  private static final long serialVersionUID = 7157341535439380615L;
  /**
   * The constant LOCK_KEY.
   */
  protected static final String LOCK_KEY = "wechat_cp_lock:";
  /**
   * The constant CP_ACCESS_TOKEN_KEY.
   */
  protected static final String CP_ACCESS_TOKEN_KEY = "wechat_cp_access_token_key:";
  /**
   * The constant CP_JSAPI_TICKET_KEY.
   */
  protected static final String CP_JSAPI_TICKET_KEY = "wechat_cp_jsapi_ticket_key:";
  /**
   * The constant CP_AGENT_JSAPI_TICKET_KEY.
   */
  protected static final String CP_AGENT_JSAPI_TICKET_KEY = "wechat_cp_agent_jsapi_ticket_key:";

  /**
   * redis 存储的 key 的前缀，可为空
   */
  protected String keyPrefix;
  /**
   * The Access token key.
   */
  protected String accessTokenKey;
  /**
   * The Jsapi ticket key.
   */
  protected String jsapiTicketKey;
  /**
   * The Agent jsapi ticket key.
   */
  protected String agentJsapiTicketKey;
  /**
   * The Lock key.
   */
  protected String lockKey;

  private final WxRedisOps redisOps;

  /**
   * Instantiates a new Wx cp redis config.
   *
   * @param redisOps  the redis ops
   * @param keyPrefix the key prefix
   */
  public AbstractWxCpInRedisConfigImpl(@NonNull WxRedisOps redisOps, String keyPrefix) {
    this.redisOps = redisOps;
    this.keyPrefix = keyPrefix;
  }

  /**
   * 设置企业微信自研应用ID（整数）,同时初始化相关的redis key，注意要先调用setCorpId，再调用setAgentId
   *
   * @param agentId 应用 agentId
   */
  @Override
  public void setAgentId(Integer agentId) {
    super.setAgentId(agentId);
    String ukey;
    if (agentId != null) {
      ukey = getCorpId().concat(":").concat(String.valueOf(agentId));
    } else {
      ukey = getCorpId();
    }
    String prefix = StringUtils.isBlank(keyPrefix) ? "" :
      (StringUtils.endsWith(keyPrefix, ":") ? keyPrefix : (keyPrefix + ":"));
    lockKey = prefix + LOCK_KEY.concat(ukey);
    accessTokenKey = prefix + CP_ACCESS_TOKEN_KEY.concat(ukey);
    jsapiTicketKey = prefix + CP_JSAPI_TICKET_KEY.concat(ukey);
    agentJsapiTicketKey = prefix + CP_AGENT_JSAPI_TICKET_KEY.concat(ukey);
  }

  /**
   * Gets lock by key.
   *
   * @param key the key
   * @return the lock by key
   */
  protected Lock getLockByKey(String key) {
    return redisOps.getLock(key);
  }

  @Override
  public Lock getAccessTokenLock() {
    return getLockByKey(this.lockKey.concat(":").concat("accessToken"));
  }

  @Override
  public Lock getAgentJsapiTicketLock() {
    return getLockByKey(this.lockKey.concat(":").concat("agentJsapiTicket"));

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
  public void expireAgentJsapiTicket() {
    redisOps.expire(this.agentJsapiTicketKey, 0, TimeUnit.SECONDS);
  }

  @Override
  public void updateAgentJsapiTicket(String agentJsapiTicket, int expiresInSeconds) {
    redisOps.setValue(this.agentJsapiTicketKey, agentJsapiTicket, expiresInSeconds, TimeUnit.SECONDS);
  }

  @Override
  public String getAgentJsapiTicket() {
    return redisOps.getValue(this.agentJsapiTicketKey);
  }

  @Override
  public boolean isAgentJsapiTicketExpired() {
    Long expire = redisOps.getExpire(this.agentJsapiTicketKey);
    return expire == null || expire < 2;
  }

  @Override
  public String toString() {
    return "AbstractWxCpInRedisConfigImpl{" +
      "corpId='" + getCorpId() + '\'' +
      ", agentId=" + getAgentId() +
      ", keyPrefix='" + keyPrefix + '\'' +
      ", accessTokenKey='" + accessTokenKey + '\'' +
      ", jsapiTicketKey='" + jsapiTicketKey + '\'' +
      ", agentJsapiTicketKey='" + agentJsapiTicketKey + '\'' +
      ", lockKey='" + lockKey + '\'' +
      '}';
  }
}
