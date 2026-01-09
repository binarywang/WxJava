package me.chanjar.weixin.cp.config;

import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.util.http.apache.ApacheHttpClientBuilder;

import java.util.concurrent.locks.Lock;

/**
 * 微信客户端（企业互联）配置
 *
 * @author libo
 */
public interface WxCpCorpGroupConfigStorage {
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
   * Update corp access token.
   *
   * @param corpId           企业ID
   * @param agentId          应用ID
   * @param corpAccessToken  the corp access token
   * @param expiresInSeconds the expires in seconds
   */
  void updateCorpAccessToken(String corpId, Integer agentId, String corpAccessToken, int expiresInSeconds);

  /**
   * 授权企业的access token相关
   *
   * @param corpId  企业ID
   * @param agentId 应用ID
   * @return the access token
   */
  String getCorpAccessToken(String corpId, Integer agentId);

  /**
   * Gets access token entity.
   *
   * @param corpId  企业ID
   * @param agentId 应用ID
   * @return the access token entity
   */
  WxAccessToken getCorpAccessTokenEntity(String corpId, Integer agentId);

  /**
   * Is access token expired boolean.
   *
   * @param corpId  企业ID
   * @param agentId 应用ID
   * @return the boolean
   */
  boolean isCorpAccessTokenExpired(String corpId, Integer agentId);

  /**
   * Expire access token.
   *
   * @param corpId  企业ID
   * @param agentId 应用ID
   */
  void expireCorpAccessToken(String corpId, Integer agentId);

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
   * Gets access token lock.
   *
   * @param corpId  企业ID
   * @param agentId 应用ID
   * @return the access token lock
   */
  Lock getCorpAccessTokenLock(String corpId, Integer agentId);

  void setCorpId(String corpId);

  void setAgentId(Integer agentId);

  /**
   * Gets corp id.
   *
   * @return the corp id
   */
  String getCorpId();

  /**
   * Gets agent id.
   *
   * @return the agent id
   */
  Integer getAgentId();
}
