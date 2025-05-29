package me.chanjar.weixin.cp.config.impl;


import lombok.Builder;
import lombok.NonNull;
import lombok.Setter;
import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.redis.WxRedisOps;
import me.chanjar.weixin.common.util.http.apache.ApacheHttpClientBuilder;
import me.chanjar.weixin.cp.bean.WxCpProviderToken;
import me.chanjar.weixin.cp.config.WxCpTpConfigStorage;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * 企业微信各种固定、授权配置的Redisson存储实现
 */
@Builder
public class WxCpTpRedissonConfigImpl implements WxCpTpConfigStorage, Serializable {
  private static final long serialVersionUID = -5385639031981770319L;

  /**
   * The constant LOCK_KEY.
   */
// lock key
  protected static final String LOCK_KEY = "wechat_tp_lock:";
  /**
   * The constant LOCKER_PROVIDER_ACCESS_TOKEN.
   */
  protected static final String LOCKER_PROVIDER_ACCESS_TOKEN = "providerAccessTokenLock";
  /**
   * The constant LOCKER_SUITE_ACCESS_TOKEN.
   */
  protected static final String LOCKER_SUITE_ACCESS_TOKEN = "suiteAccessTokenLock";
  /**
   * The constant LOCKER_ACCESS_TOKEN.
   */
  protected static final String LOCKER_ACCESS_TOKEN = "accessTokenLock";
  /**
   * The constant LOCKER_CORP_JSAPI_TICKET.
   */
  protected static final String LOCKER_CORP_JSAPI_TICKET = "corpJsapiTicketLock";
  /**
   * The constant LOCKER_SUITE_JSAPI_TICKET.
   */
  protected static final String LOCKER_SUITE_JSAPI_TICKET = "suiteJsapiTicketLock";
  @NonNull
  private final WxRedisOps wxRedisOps;
  private final String suiteAccessTokenKey = ":suiteAccessTokenKey:";
  private final String suiteTicketKey = ":suiteTicketKey:";
  private final String accessTokenKey = ":accessTokenKey:";
  private final String authCorpJsApiTicketKey = ":authCorpJsApiTicketKey:";
  private final String authSuiteJsApiTicketKey = ":authSuiteJsApiTicketKey:";
  private final String providerTokenKey = ":providerTokenKey:";
  /**
   * redis里面key的统一前缀
   */
  @Setter
  private String keyPrefix = "";
  private volatile String baseApiUrl;
  private volatile String httpProxyHost;
  private volatile int httpProxyPort;
  private volatile String httpProxyUsername;
  private volatile String httpProxyPassword;
  private volatile ApacheHttpClientBuilder apacheHttpClientBuilder;
  private volatile File tmpDirFile;
  /**
   * 第三方应用的其他配置，来自于企微配置
   */
  private volatile String suiteId;
  private volatile String suiteSecret;
  /**
   * 第三方应用的token，用来检查应用的签名
   */
  private volatile String token;
  /**
   * 第三方应用的EncodingAESKey，用来检查签名
   */
  private volatile String aesKey;
  /**
   * 企微服务商企业ID & 企业secret，来自于企微配置
   */
  private volatile String corpId;
  private volatile String corpSecret;
  /**
   * 服务商secret
   */
  private volatile String providerSecret;

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


  /**
   * 第三方应用的suite access token相关
   */
  @Override
  public String getSuiteAccessToken() {
    return wxRedisOps.getValue(keyWithPrefix(suiteAccessTokenKey));
  }

  @Override
  public WxAccessToken getSuiteAccessTokenEntity() {
    String suiteAccessToken = wxRedisOps.getValue(keyWithPrefix(suiteAccessTokenKey));
    Long expireIn = wxRedisOps.getExpire(keyWithPrefix(suiteAccessTokenKey));
    if (StringUtils.isBlank(suiteAccessToken) || expireIn == null || expireIn == 0 || expireIn == -2) {
      return new WxAccessToken();
    }

    WxAccessToken suiteAccessTokenEntity = new WxAccessToken();
    suiteAccessTokenEntity.setAccessToken(suiteAccessToken);
    suiteAccessTokenEntity.setExpiresIn(Math.max(Math.toIntExact(expireIn), 0));
    return suiteAccessTokenEntity;
  }

  @Override
  public boolean isSuiteAccessTokenExpired() {
    //remain time to live in seconds, or key not exist
    return wxRedisOps.getExpire(keyWithPrefix(suiteAccessTokenKey)) == 0L || wxRedisOps.getExpire(keyWithPrefix(suiteAccessTokenKey)) == -2;
  }

  @Override
  public void expireSuiteAccessToken() {
    wxRedisOps.expire(keyWithPrefix(suiteAccessTokenKey), 0, TimeUnit.SECONDS);
  }

  @Override
  public void updateSuiteAccessToken(WxAccessToken suiteAccessToken) {
    updateSuiteAccessToken(suiteAccessToken.getAccessToken(), suiteAccessToken.getExpiresIn());
  }

  @Override
  public void updateSuiteAccessToken(String suiteAccessToken, int expiresInSeconds) {
    wxRedisOps.setValue(keyWithPrefix(suiteAccessTokenKey), suiteAccessToken, expiresInSeconds, TimeUnit.SECONDS);
  }

  /**
   * 第三方应用的suite ticket相关
   */
  @Override
  public String getSuiteTicket() {
    return wxRedisOps.getValue(keyWithPrefix(suiteTicketKey));
  }

  @Override
  public boolean isSuiteTicketExpired() {
    //remain time to live in seconds, or key not exist
    return wxRedisOps.getExpire(keyWithPrefix(suiteTicketKey)) == 0L || wxRedisOps.getExpire(keyWithPrefix(suiteTicketKey)) == -2;
  }

  @Override
  public void expireSuiteTicket() {
    wxRedisOps.expire(keyWithPrefix(suiteTicketKey), 0, TimeUnit.SECONDS);
  }

  @Override
  public void updateSuiteTicket(String suiteTicket, int expiresInSeconds) {
    wxRedisOps.setValue(keyWithPrefix(suiteTicketKey), suiteTicket, expiresInSeconds, TimeUnit.SECONDS);
  }

  /**
   * 第三方应用的其他配置，来自于企微配置
   */
  @Override
  public String getSuiteId() {
    return suiteId;
  }

  @Override
  public String getSuiteSecret() {
    return suiteSecret;
  }

  // 第三方应用的token，用来检查应用的签名
  @Override
  public String getToken() {
    return token;
  }

  //第三方应用的EncodingAESKey，用来检查签名
  @Override
  public String getAesKey() {
    return aesKey;
  }


  /**
   * 企微服务商企业ID & 企业secret, 来自于企微配置
   */
  @Override
  public String getCorpId() {
    return corpId;
  }

  @Override
  public String getCorpSecret() {
    return corpSecret;
  }

  @Override
  public void setProviderSecret(String providerSecret) {
    this.providerSecret = providerSecret;
  }

  @Override
  public String getProviderSecret() {
    return providerSecret;
  }

  /**
   * 授权企业的access token相关
   */
  @Override
  public String getAccessToken(String authCorpId) {
    return wxRedisOps.getValue(keyWithPrefix(authCorpId) + accessTokenKey);
  }

  @Override
  public WxAccessToken getAccessTokenEntity(String authCorpId) {
    String accessToken = wxRedisOps.getValue(keyWithPrefix(authCorpId) + accessTokenKey);
    Long expire = wxRedisOps.getExpire(keyWithPrefix(authCorpId) + accessTokenKey);
    if (StringUtils.isBlank(accessToken) || expire == null || expire == 0 || expire == -2) {
      return new WxAccessToken();
    }

    WxAccessToken accessTokenEntity = new WxAccessToken();
    accessTokenEntity.setAccessToken(accessToken);
    accessTokenEntity.setExpiresIn((int) ((expire - System.currentTimeMillis()) / 1000 + 200));
    return accessTokenEntity;
  }

  @Override
  public boolean isAccessTokenExpired(String authCorpId) {
    //没有设置或者TTL为0，都是过期
    return wxRedisOps.getExpire(keyWithPrefix(authCorpId) + accessTokenKey) == 0L
      || wxRedisOps.getExpire(keyWithPrefix(authCorpId) + accessTokenKey) == -2;
  }

  @Override
  public void expireAccessToken(String authCorpId) {
    wxRedisOps.expire(keyWithPrefix(authCorpId) + accessTokenKey, 0, TimeUnit.SECONDS);
  }

  @Override
  public void updateAccessToken(String authCorpId, String accessToken, int expiredInSeconds) {
    wxRedisOps.setValue(keyWithPrefix(authCorpId) + accessTokenKey, accessToken, expiredInSeconds, TimeUnit.SECONDS);
  }


  /**
   * 授权企业的js api ticket相关
   */
  @Override
  public String getAuthCorpJsApiTicket(String authCorpId) {
    return wxRedisOps.getValue(keyWithPrefix(authCorpId) + authCorpJsApiTicketKey);
  }

  @Override
  public boolean isAuthCorpJsApiTicketExpired(String authCorpId) {
    //没有设置或TTL为0,都是过期
    return wxRedisOps.getExpire(keyWithPrefix(authCorpId) + authCorpJsApiTicketKey) == 0L
      || wxRedisOps.getExpire(keyWithPrefix(authCorpId) + authCorpJsApiTicketKey) == -2;
  }

  @Override
  public void expireAuthCorpJsApiTicket(String authCorpId) {
    wxRedisOps.expire(keyWithPrefix(authCorpId) + authCorpJsApiTicketKey, 0, TimeUnit.SECONDS);
  }

  @Override
  public void updateAuthCorpJsApiTicket(String authCorpId, String jsApiTicket, int expiredInSeconds) {
    wxRedisOps.setValue(keyWithPrefix(authCorpId) + authCorpJsApiTicketKey, jsApiTicket, expiredInSeconds,
      TimeUnit.SECONDS);
  }


  /**
   * 授权企业的第三方应用js api ticket相关
   */
  @Override
  public String getAuthSuiteJsApiTicket(String authCorpId) {
    return wxRedisOps.getValue(keyWithPrefix(authCorpId) + authSuiteJsApiTicketKey);
  }

  @Override
  public boolean isAuthSuiteJsApiTicketExpired(String authCorpId) {
    //没有设置或者TTL为0，都是过期
    return wxRedisOps.getExpire(keyWithPrefix(authCorpId) + authSuiteJsApiTicketKey) == 0L
      || wxRedisOps.getExpire(keyWithPrefix(authCorpId) + authSuiteJsApiTicketKey) == -2;
  }

  @Override
  public void expireAuthSuiteJsApiTicket(String authCorpId) {
    wxRedisOps.expire(keyWithPrefix(authCorpId) + authSuiteJsApiTicketKey, 0, TimeUnit.SECONDS);
  }

  @Override
  public void updateAuthSuiteJsApiTicket(String authCorpId, String jsApiTicket, int expiredInSeconds) {
    wxRedisOps.setValue(keyWithPrefix(authCorpId) + authSuiteJsApiTicketKey, jsApiTicket, expiredInSeconds,
      TimeUnit.SECONDS);
  }

  @Override
  public boolean isProviderTokenExpired() {
    //remain time to live in seconds, or key not exist
    return wxRedisOps.getExpire(providerKeyWithPrefix(providerTokenKey)) == 0L || wxRedisOps.getExpire(providerKeyWithPrefix(providerTokenKey)) == -2;
  }

  @Override
  public void updateProviderToken(String providerToken, int expiredInSeconds) {
    wxRedisOps.setValue(providerKeyWithPrefix(providerTokenKey), providerToken, expiredInSeconds, TimeUnit.SECONDS);
  }

  @Override
  public String getProviderToken() {
    return wxRedisOps.getValue(providerKeyWithPrefix(providerTokenKey));
  }

  @Override
  public WxCpProviderToken getProviderTokenEntity() {
    String providerToken = wxRedisOps.getValue(providerKeyWithPrefix(providerTokenKey));
    Long expire = wxRedisOps.getExpire(providerKeyWithPrefix(providerTokenKey));

    if (StringUtils.isBlank(providerToken) || expire == null || expire == 0 || expire == -2) {
      return new WxCpProviderToken();
    }

    WxCpProviderToken wxCpProviderToken = new WxCpProviderToken();
    wxCpProviderToken.setProviderAccessToken(providerToken);
    wxCpProviderToken.setExpiresIn(Math.max(Math.toIntExact(expire), 0));
    return wxCpProviderToken;
  }

  @Override
  public void expireProviderToken() {
    wxRedisOps.expire(providerKeyWithPrefix(providerTokenKey), 0, TimeUnit.SECONDS);
  }

  /**
   * 网络代理相关
   */
  @Override
  public String getHttpProxyHost() {
    return this.httpProxyHost;
  }

  @Override
  public int getHttpProxyPort() {
    return this.httpProxyPort;
  }

  @Override
  public String getHttpProxyUsername() {
    return this.httpProxyUsername;
  }

  @Override
  public String getHttpProxyPassword() {
    return this.httpProxyPassword;
  }

  @Override
  public File getTmpDirFile() {
    return tmpDirFile;
  }

  @Override
  public Lock getProviderAccessTokenLock() {
    return getProviderLockByKey(String.join(":", this.corpId, LOCKER_PROVIDER_ACCESS_TOKEN));
  }

  @Override
  public Lock getSuiteAccessTokenLock() {
    return getLockByKey(LOCKER_SUITE_ACCESS_TOKEN);
  }

  @Override
  public Lock getAccessTokenLock(String authCorpId) {
    return getLockByKey(String.join(":", authCorpId, LOCKER_ACCESS_TOKEN));
  }

  @Override
  public Lock getAuthCorpJsapiTicketLock(String authCorpId) {
    return getLockByKey(String.join(":", authCorpId, LOCKER_CORP_JSAPI_TICKET));
  }

  @Override
  public Lock getSuiteJsapiTicketLock(String authCorpId) {
    return getLockByKey(String.join(":", authCorpId, LOCKER_SUITE_JSAPI_TICKET));
  }

  private Lock getLockByKey(String key) {
    // 最终key的模式：(keyPrefix:)wechat_tp_lock:suiteId:(authCorpId):lockKey
    // 其中keyPrefix目前不支持外部配置，authCorpId只有涉及到corpAccessToken, suiteJsapiTicket, authCorpJsapiTicket时才会拼上
    return this.wxRedisOps.getLock(String.join(":", keyWithPrefix(LOCK_KEY + this.suiteId), key));
  }

  /**
   * 单独处理provider,且不应和suite 有关系
   */
  private Lock getProviderLockByKey(String key) {
    return this.wxRedisOps.getLock(String.join(":", providerKeyWithPrefix(LOCK_KEY), key));
  }

  @Override
  public ApacheHttpClientBuilder getApacheHttpClientBuilder() {
    return this.apacheHttpClientBuilder;
  }

  @Override
  public boolean autoRefreshToken() {
    return false;
  }

  @Override
  public String toString() {
    return WxCpGsonBuilder.create().toJson(this);
  }

  /**
   * 一个provider 会有多个suite,需要唯一标识作为前缀
   */
  private String keyWithPrefix(String key) {
    return keyPrefix + ":" + suiteId + ":" + key;
  }

  /**
   * provider 应该独享一个key,且不和任何suite关联
   * 一个provider  会有多个suite,不同的suite 都应该指向同一个provider 的数据
   */
  private String providerKeyWithPrefix(String key) {
    return keyPrefix + ":" + corpId + ":" + key;
  }
}
