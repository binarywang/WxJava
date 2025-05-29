package me.chanjar.weixin.cp.config;

import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.util.http.apache.ApacheHttpClientBuilder;
import me.chanjar.weixin.cp.bean.WxCpProviderToken;

import java.io.File;
import java.util.concurrent.locks.Lock;

/**
 * 微信客户端（第三方应用）配置存储
 *
 * @author zhenjun cai
 */
public interface WxCpTpConfigStorage {

  /**
   * 设置企业微信服务器 baseUrl.
   * 默认值是 https://qyapi.weixin.qq.com , 如果使用默认值，则不需要调用 setBaseApiUrl
   *
   * @param baseUrl 企业微信服务器 Url
   */
  void setBaseApiUrl(String baseUrl);

  /**
   * 读取企业微信 API Url.
   * 支持私有化企业微信服务器.
   *
   * @param path the path
   * @return the api url
   */
  String getApiUrl(String path);

  /**
   * 第三方应用的suite access token相关
   *
   * @return the suite access token
   */
  String getSuiteAccessToken();

  /**
   * 获取suite_access_token和剩余过期时间
   *
   * @return suite access token and the remaining expiration time
   */
  WxAccessToken getSuiteAccessTokenEntity();

  /**
   * Is suite access token expired boolean.
   *
   * @return the boolean
   */
  boolean isSuiteAccessTokenExpired();

  /**
   * Expire suite access token.
   */
//强制将suite access token过期掉.
  void expireSuiteAccessToken();

  /**
   * Update suite access token.
   *
   * @param suiteAccessToken the suite access token
   */
  void updateSuiteAccessToken(WxAccessToken suiteAccessToken);

  /**
   * Update suite access token.
   *
   * @param suiteAccessToken the suite access token
   * @param expiresInSeconds the expires in seconds
   */
  void updateSuiteAccessToken(String suiteAccessToken, int expiresInSeconds);

  /**
   * 第三方应用的suite ticket相关
   *
   * @return the suite ticket
   */
  String getSuiteTicket();

  /**
   * Is suite ticket expired boolean.
   *
   * @return the boolean
   */
  boolean isSuiteTicketExpired();

  /**
   * Expire suite ticket.
   */
//强制将suite ticket过期掉.
  void expireSuiteTicket();

  /**
   * Update suite ticket.
   *
   * @param suiteTicket      the suite ticket
   * @param expiresInSeconds the expires in seconds
   */
//应该是线程安全的
  void updateSuiteTicket(String suiteTicket, int expiresInSeconds);

  /**
   * 第三方应用的其他配置，来自于企微配置
   *
   * @return the suite id
   */
  String getSuiteId();

  /**
   * Gets suite secret.
   *
   * @return the suite secret
   */
  String getSuiteSecret();

  /**
   * Gets token.
   *
   * @return the token
   */
// 第三方应用的token，用来检查应用的签名
  String getToken();

  /**
   * Gets aes key.
   *
   * @return the aes key
   */
//第三方应用的EncodingAESKey，用来检查签名
  String getAesKey();

  /**
   * 企微服务商企业ID & 企业secret
   *
   * @return the corp id
   */
  String getCorpId();

  /**
   * Gets corp secret.
   *
   * @return the corp secret
   */
  String getCorpSecret();

  /**
   * Sets provider secret.
   *
   * @param providerSecret the provider secret
   */
  void setProviderSecret(String providerSecret);

  /**
   * 服务商secret
   *
   * @return the provider secret
   */
  String getProviderSecret();

  /**
   * 授权企业的access token相关
   *
   * @param authCorpId the auth corp id
   * @return the access token
   */
  String getAccessToken(String authCorpId);

  /**
   * Gets access token entity.
   *
   * @param authCorpId the auth corp id
   * @return the access token entity
   */
  WxAccessToken getAccessTokenEntity(String authCorpId);

  /**
   * Is access token expired boolean.
   *
   * @param authCorpId the auth corp id
   * @return the boolean
   */
  boolean isAccessTokenExpired(String authCorpId);

  /**
   * Expire access token.
   *
   * @param authCorpId the auth corp id
   */
  void expireAccessToken(String authCorpId);

  /**
   * Update access token.
   *
   * @param authCorpId       the auth corp id
   * @param accessToken      the access token
   * @param expiredInSeconds the expired in seconds
   */
  void updateAccessToken(String authCorpId, String accessToken, int expiredInSeconds);

  /**
   * 授权企业的js api ticket相关
   *
   * @param authCorpId the auth corp id
   * @return the auth corp js api ticket
   */
  String getAuthCorpJsApiTicket(String authCorpId);

  /**
   * Is auth corp js api ticket expired boolean.
   *
   * @param authCorpId the auth corp id
   * @return the boolean
   */
  boolean isAuthCorpJsApiTicketExpired(String authCorpId);

  /**
   * Expire auth corp js api ticket.
   *
   * @param authCorpId the auth corp id
   */
  void expireAuthCorpJsApiTicket(String authCorpId);

  /**
   * Update auth corp js api ticket.
   *
   * @param authCorpId       the auth corp id
   * @param jsApiTicket      the js api ticket
   * @param expiredInSeconds the expired in seconds
   */
  void updateAuthCorpJsApiTicket(String authCorpId, String jsApiTicket, int expiredInSeconds);

  /**
   * 授权企业的第三方应用js api ticket相关
   *
   * @param authCorpId the auth corp id
   * @return the auth suite js api ticket
   */
  String getAuthSuiteJsApiTicket(String authCorpId);

  /**
   * Is auth suite js api ticket expired boolean.
   *
   * @param authCorpId the auth corp id
   * @return the boolean
   */
  boolean isAuthSuiteJsApiTicketExpired(String authCorpId);

  /**
   * Expire auth suite js api ticket.
   *
   * @param authCorpId the auth corp id
   */
  void expireAuthSuiteJsApiTicket(String authCorpId);

  /**
   * Update auth suite js api ticket.
   *
   * @param authCorpId       the auth corp id
   * @param jsApiTicket      the js api ticket
   * @param expiredInSeconds the expired in seconds
   */
  void updateAuthSuiteJsApiTicket(String authCorpId, String jsApiTicket, int expiredInSeconds);

  /**
   * Is provider token expired boolean.
   *
   * @return the boolean
   */
  boolean isProviderTokenExpired();

  /**
   * Update provider token.
   *
   * @param providerToken    the provider token
   * @param expiredInSeconds the expired in seconds
   */
  void updateProviderToken(String providerToken, int expiredInSeconds);

  /**
   * Gets provider token.
   *
   * @return the provider token
   */
  String getProviderToken();

  /**
   * Gets provider token entity.
   *
   * @return the provider token entity
   */
  WxCpProviderToken getProviderTokenEntity();

  /**
   * Expire provider token.
   */
// 强制过期
  void expireProviderToken();

  /**
   * 网络代理相关
   *
   * @return the http proxy host
   */
  String getHttpProxyHost();

  /**
   * Gets http proxy port.
   *
   * @return the http proxy port
   */
  int getHttpProxyPort();

  /**
   * Gets http proxy username.
   *
   * @return the http proxy username
   */
  String getHttpProxyUsername();

  /**
   * Gets http proxy password.
   *
   * @return the http proxy password
   */
  String getHttpProxyPassword();

  /**
   * Gets apache http client builder.
   *
   * @return the apache http client builder
   */
  ApacheHttpClientBuilder getApacheHttpClientBuilder();

  /**
   * Auto refresh token boolean.
   *
   * @return the boolean
   */
  boolean autoRefreshToken();

  /**
   * Gets tmp dir file.
   *
   * @return the tmp dir file
   */
// 毫无相关性的代码
  @Deprecated
  File getTmpDirFile();

  /**
   * Gets provider access token lock.
   *
   * @return the provider access token lock
   */
  Lock getProviderAccessTokenLock();

  /**
   * Gets suite access token lock.
   *
   * @return the suite access token lock
   */
  Lock getSuiteAccessTokenLock();

  /**
   * Gets access token lock.
   *
   * @param authCorpId the auth corp id
   * @return the access token lock
   */
  Lock getAccessTokenLock(String authCorpId);

  /**
   * Gets auth corp jsapi ticket lock.
   *
   * @param authCorpId the auth corp id
   * @return the auth corp jsapi ticket lock
   */
  Lock getAuthCorpJsapiTicketLock(String authCorpId);

  /**
   * Gets suite jsapi ticket lock.
   *
   * @param authCorpId the auth corp id
   * @return the suite jsapi ticket lock
   */
  Lock getSuiteJsapiTicketLock(String authCorpId);
}
