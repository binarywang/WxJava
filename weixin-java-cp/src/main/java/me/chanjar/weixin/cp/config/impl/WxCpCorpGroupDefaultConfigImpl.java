package me.chanjar.weixin.cp.config.impl;

import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.util.http.apache.ApacheHttpClientBuilder;
import me.chanjar.weixin.cp.config.WxCpCorpGroupConfigStorage;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 基于内存的微信配置provider，在实际生产环境中应该将这些配置持久化.
 *
 * @author libo
 */
public class WxCpCorpGroupDefaultConfigImpl implements WxCpCorpGroupConfigStorage, Serializable {
  private static final long serialVersionUID = -8392908346536154435L;

  private final transient Map<String, Lock> corpAccessTokenLocker = new ConcurrentHashMap<>();

  private final Map<String, String> corpAccessTokenMap = new HashMap<>();
  private final Map<String, Long> corpAccessTokenExpireTimeMap = new HashMap<>();

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
    String key = generateAccessTokenKey(corpId, agentId);
    corpAccessTokenMap.put(key, corpAccessToken);
    //预留200秒的时间
    corpAccessTokenExpireTimeMap.put(key, System.currentTimeMillis() + (expiresInSeconds - 200) * 1000L);
  }

  @Override
  public String getCorpAccessToken(String corpId, Integer agentId) {
    return this.corpAccessTokenMap.get(generateAccessTokenKey(corpId, agentId));
  }

  @Override
  public WxAccessToken getCorpAccessTokenEntity(String corpId, Integer agentId) {
    String key = generateAccessTokenKey(corpId, agentId);
    String accessToken = corpAccessTokenMap.getOrDefault(key, StringUtils.EMPTY);
    Long expire = corpAccessTokenExpireTimeMap.getOrDefault(key, 0L);
    WxAccessToken accessTokenEntity = new WxAccessToken();
    accessTokenEntity.setAccessToken(accessToken);
    accessTokenEntity.setExpiresIn((int) ((expire - System.currentTimeMillis()) / 1000 + 200));
    return accessTokenEntity;
  }

  @Override
  public boolean isCorpAccessTokenExpired(String corpId, Integer agentId) {
    //不存在或者过期
    String key = generateAccessTokenKey(corpId, agentId);
    return corpAccessTokenExpireTimeMap.get(key) == null
      || System.currentTimeMillis() > corpAccessTokenExpireTimeMap.get(key);
  }

  @Override
  public void expireCorpAccessToken(String corpId, Integer agentId) {
    String key = generateAccessTokenKey(corpId, agentId);
    corpAccessTokenMap.remove(key);
    corpAccessTokenExpireTimeMap.remove(key);
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
    return this.corpAccessTokenLocker
      .computeIfAbsent(generateAccessTokenKey(corpId, agentId), key -> new ReentrantLock());
  }

  private String generateAccessTokenKey(String corpId, Integer agentId) {
    return String.join(":", this.corpId, String.valueOf(this.agentId), corpId, String.valueOf(agentId));
  }
}
