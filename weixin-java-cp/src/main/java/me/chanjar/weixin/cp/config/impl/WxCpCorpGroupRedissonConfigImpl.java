package me.chanjar.weixin.cp.config.impl;

import lombok.Builder;
import lombok.NonNull;
import lombok.Setter;
import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.redis.WxRedisOps;
import me.chanjar.weixin.common.util.http.apache.ApacheHttpClientBuilder;
import me.chanjar.weixin.cp.config.WxCpCorpGroupConfigStorage;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * 企业微信企业互联各种固定、授权配置的Redisson存储实现
 *
 * @author libo Email: 422423229@qq.com
 * @since 1/3/2023 10:48 AM
 */
@Builder
public class WxCpCorpGroupRedissonConfigImpl implements WxCpCorpGroupConfigStorage, Serializable {
  private static final long serialVersionUID = 1269450173683931930L;

  private final transient Map<String, Lock> corpAccessTokenLocker = new ConcurrentHashMap<>();

  /**
   * The constant LOCK_KEY.
   */
  protected static final String LOCK_KEY = "wechat_cg_lock:";
  /**
   * The constant LOCKER_CORP_ACCESS_TOKEN.
   */
  protected static final String LOCKER_CORP_ACCESS_TOKEN = "corpAccessTokenLock";
  /**
   * The constant CG_ACCESS_TOKEN_KEY.
   */
  protected static final String CG_ACCESS_TOKEN_KEY = "wechat_cg_access_token_key:";
  @NonNull
  private final WxRedisOps wxRedisOps;
  /**
   * redis里面key的统一前缀
   */
  @Setter
  private String keyPrefix = "";

  private volatile String httpProxyHost;
  private volatile int httpProxyPort;
  private volatile String httpProxyUsername;
  private volatile String httpProxyPassword;
  private volatile ApacheHttpClientBuilder apacheHttpClientBuilder;
  private volatile String baseApiUrl;
  /**
   * 微信企业号 corpId
   */
  private volatile String corpId;
  /**
   * 微信企业号应用 ID
   */
  private volatile Integer agentId;

  @Override
  public void setBaseApiUrl(String baseUrl) {
    this.baseApiUrl = baseUrl;
  }

  @Override
  public String getApiUrl(String path) {
    if (baseApiUrl == null) {
      baseApiUrl = "https://qyapi.weixin.qq.com";
    }
    return baseApiUrl + path;
  }

  @Override
  public String getCorpId() {
    return corpId;
  }

  @Override
  public void setCorpId(String corpId) {
    this.corpId = corpId;
  }

  @Override
  public Integer getAgentId() {
    return agentId;
  }

  @Override
  public void setAgentId(Integer agentId) {
    this.agentId = agentId;
  }

  @Override
  public void updateCorpAccessToken(String corpId, Integer agentId, String corpAccessToken, int expiresInSeconds) {
    wxRedisOps.setValue(generateAccessTokenKey(corpId, agentId), corpAccessToken, expiresInSeconds, TimeUnit.SECONDS);
  }

  @Override
  public String getCorpAccessToken(String corpId, Integer agentId) {
    return wxRedisOps.getValue(generateAccessTokenKey(corpId, agentId));
  }

  @Override
  public WxAccessToken getCorpAccessTokenEntity(String corpId, Integer agentId) {
    String key = generateAccessTokenKey(corpId, agentId);
    String accessToken = wxRedisOps.getValue(key);
    Long expire = wxRedisOps.getExpire(key);
    if (StringUtils.isBlank(accessToken) || expire == null || expire == 0 || expire == -2) {
      return new WxAccessToken();
    }
    WxAccessToken accessTokenEntity = new WxAccessToken();
    accessTokenEntity.setAccessToken(accessToken);
    accessTokenEntity.setExpiresIn(Math.max(Math.toIntExact(expire), 0));
    return accessTokenEntity;
  }

  @Override
  public boolean isCorpAccessTokenExpired(String corpId, Integer agentId) {
    String key = generateAccessTokenKey(corpId, agentId);
    return wxRedisOps.getExpire(key) == 0L || wxRedisOps.getExpire(key) == -2;
  }

  @Override
  public void expireCorpAccessToken(String corpId, Integer agentId) {
    wxRedisOps.expire(generateAccessTokenKey(corpId, agentId), 0, TimeUnit.SECONDS);
  }

  @Override
  public String getHttpProxyHost() {
    return this.httpProxyHost;
  }

  /**
   * Sets http proxy host.
   *
   * @param httpProxyHost the http proxy host
   */
  public void setHttpProxyHost(String httpProxyHost) {
    this.httpProxyHost = httpProxyHost;
  }

  @Override
  public int getHttpProxyPort() {
    return this.httpProxyPort;
  }

  /**
   * Sets http proxy port.
   *
   * @param httpProxyPort the http proxy port
   */
  public void setHttpProxyPort(int httpProxyPort) {
    this.httpProxyPort = httpProxyPort;
  }

  @Override
  public String getHttpProxyUsername() {
    return this.httpProxyUsername;
  }

  /**
   * Sets http proxy username.
   *
   * @param httpProxyUsername the http proxy username
   */
  public void setHttpProxyUsername(String httpProxyUsername) {
    this.httpProxyUsername = httpProxyUsername;
  }

  @Override
  public String getHttpProxyPassword() {
    return this.httpProxyPassword;
  }

  /**
   * Sets http proxy password.
   *
   * @param httpProxyPassword the http proxy password
   */
  public void setHttpProxyPassword(String httpProxyPassword) {
    this.httpProxyPassword = httpProxyPassword;
  }

  @Override
  public ApacheHttpClientBuilder getApacheHttpClientBuilder() {
    return this.apacheHttpClientBuilder;
  }

  /**
   * Sets apache http client builder.
   *
   * @param apacheHttpClientBuilder the apache http client builder
   */
  public void setApacheHttpClientBuilder(ApacheHttpClientBuilder apacheHttpClientBuilder) {
    this.apacheHttpClientBuilder = apacheHttpClientBuilder;
  }

  @Override
  public boolean autoRefreshToken() {
    return true;
  }

  @Override
  public Lock getCorpAccessTokenLock(String corpId, Integer agentId) {
    return this.getLockByKey(String.join(":", corpId, String.valueOf(agentId), LOCKER_CORP_ACCESS_TOKEN));
  }

  private String generateAccessTokenKey(String corpId, Integer agentId) {
    return String.join(":", keyPrefix, CG_ACCESS_TOKEN_KEY, corpId, String.valueOf(agentId));
  }

  private Lock getLockByKey(String key) {
    return this.wxRedisOps.getLock(String.join(":", keyPrefix, LOCK_KEY, key));
  }
}
