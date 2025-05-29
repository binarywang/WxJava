package me.chanjar.weixin.open.api;

import cn.binarywang.wx.miniapp.config.WxMaConfig;
import java.util.concurrent.locks.Lock;
import me.chanjar.weixin.common.util.http.apache.ApacheHttpClientBuilder;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.open.bean.WxOpenAuthorizerAccessToken;
import me.chanjar.weixin.open.bean.WxOpenComponentAccessToken;

/**
 * The interface Wx open config storage.
 *
 * @author <a href="https://github.com/007gzs">007</a>
 */
public interface WxOpenConfigStorage {

  /**
   * Gets component app id.
   *
   * @return the component app id
   */
  String getComponentAppId();

  /**
   * Sets component app id.
   *
   * @param componentAppId the component app id
   */
  void setComponentAppId(String componentAppId);

  /**
   * Gets component app secret.
   *
   * @return the component app secret
   */
  String getComponentAppSecret();

  /**
   * Sets component app secret.
   *
   * @param componentAppSecret the component app secret
   */
  void setComponentAppSecret(String componentAppSecret);

  /**
   * Gets component token.
   *
   * @return the component token
   */
  String getComponentToken();

  /**
   * Sets component token.
   *
   * @param componentToken the component token
   */
  void setComponentToken(String componentToken);

  /**
   * Gets component aes key.
   *
   * @return the component aes key
   */
  String getComponentAesKey();

  /**
   * Sets component aes key.
   *
   * @param componentAesKey the component aes key
   */
  void setComponentAesKey(String componentAesKey);

  /**
   * Gets component verify ticket.
   *
   * @return the component verify ticket
   */
  String getComponentVerifyTicket();

  /**
   * Sets component verify ticket.
   *
   * @param componentVerifyTicket the component verify ticket
   */
  void setComponentVerifyTicket(String componentVerifyTicket);

  /**
   * Gets component access token.
   *
   * @return the component access token
   */
  String getComponentAccessToken();

  /**
   * Is component access token expired boolean.
   *
   * @return the boolean
   */
  boolean isComponentAccessTokenExpired();

  /** Expire component access token. */
  void expireComponentAccessToken();

  /**
   * Update component access token.
   *
   * @param componentAccessToken the component access token
   */
  void updateComponentAccessToken(WxOpenComponentAccessToken componentAccessToken);

  /**
   * Gets http proxy host.
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
   * http 请求重试间隔
   *
   * <pre>
   *   {@link me.chanjar.weixin.mp.api.impl.BaseWxMpServiceImpl#setRetrySleepMillis(int)}
   *   {@link cn.binarywang.wx.miniapp.api.impl.BaseWxMaServiceImpl#setRetrySleepMillis(int)}
   * </pre>
   */
  int getRetrySleepMillis();

  /**
   * http 请求最大重试次数
   *
   * <pre>
   *   {@link me.chanjar.weixin.mp.api.impl.BaseWxMpServiceImpl#setMaxRetryTimes(int)}
   *   {@link cn.binarywang.wx.miniapp.api.impl.BaseWxMaServiceImpl#setMaxRetryTimes(int)}
   * </pre>
   */
  int getMaxRetryTimes();

  /**
   * Gets apache http client builder.
   *
   * @return the apache http client builder
   */
  ApacheHttpClientBuilder getApacheHttpClientBuilder();

  /**
   * Gets wx mp config storage.
   *
   * @param appId the app id
   * @return the wx mp config storage
   */
  WxMpConfigStorage getWxMpConfigStorage(String appId);

  /**
   * Gets wx ma config.
   *
   * @param appId the app id
   * @return the wx ma config
   */
  WxMaConfig getWxMaConfig(String appId);

  /**
   * Gets component access token lock.
   *
   * @return the component access token lock
   */
  Lock getComponentAccessTokenLock();

  /**
   * Gets lock by key.
   *
   * @param key the key
   * @return the lock by key
   */
  Lock getLockByKey(String key);

  /**
   * 应该是线程安全的
   *
   * @param componentAccessToken 新的accessToken值
   * @param expiresInSeconds 过期时间，以秒为单位
   */
  void updateComponentAccessToken(String componentAccessToken, int expiresInSeconds);

  /**
   * 是否自动刷新token
   *
   * @return the boolean
   */
  boolean autoRefreshToken();

  /**
   * Gets authorizer refresh token.
   *
   * @param appId the app id
   * @return the authorizer refresh token
   */
  String getAuthorizerRefreshToken(String appId);

  /**
   * Sets authorizer refresh token.
   *
   * @param appId the app id
   * @param authorizerRefreshToken the authorizer refresh token
   */
  void setAuthorizerRefreshToken(String appId, String authorizerRefreshToken);

  /**
   * setAuthorizerRefreshToken(String appId, String authorizerRefreshToken) 方法重载方法
   *
   * @param appId the app id
   * @param authorizerRefreshToken the authorizer refresh token
   */
  void updateAuthorizerRefreshToken(String appId, String authorizerRefreshToken);

  /**
   * Gets authorizer access token.
   *
   * @param appId the app id
   * @return the authorizer access token
   */
  String getAuthorizerAccessToken(String appId);

  /**
   * Is authorizer access token expired boolean.
   *
   * @param appId the app id
   * @return the boolean
   */
  boolean isAuthorizerAccessTokenExpired(String appId);

  /**
   * 强制将access token过期掉
   *
   * @param appId the app id
   */
  void expireAuthorizerAccessToken(String appId);

  /**
   * 应该是线程安全的
   *
   * @param appId the app id
   * @param authorizerAccessToken 要更新的WxAccessToken对象
   */
  void updateAuthorizerAccessToken(String appId, WxOpenAuthorizerAccessToken authorizerAccessToken);

  /**
   * 应该是线程安全的
   *
   * @param appId the app id
   * @param authorizerAccessToken 新的accessToken值
   * @param expiresInSeconds 过期时间，以秒为单位
   */
  void updateAuthorizerAccessToken(
      String appId, String authorizerAccessToken, int expiresInSeconds);

  /**
   * Gets jsapi ticket.
   *
   * @param appId the app id
   * @return the jsapi ticket
   */
  String getJsapiTicket(String appId);

  /**
   * Is jsapi ticket expired boolean.
   *
   * @param appId the app id
   * @return the boolean
   */
  boolean isJsapiTicketExpired(String appId);

  /**
   * 强制将jsapi ticket过期掉
   *
   * @param appId the app id
   */
  void expireJsapiTicket(String appId);

  /**
   * 应该是线程安全的
   *
   * @param appId the app id
   * @param jsapiTicket 新的jsapi ticket值
   * @param expiresInSeconds 过期时间，以秒为单位
   */
  void updateJsapiTicket(String appId, String jsapiTicket, int expiresInSeconds);

  /**
   * Gets card api ticket.
   *
   * @param appId the app id
   * @return the card api ticket
   */
  String getCardApiTicket(String appId);

  /**
   * Is card api ticket expired boolean.
   *
   * @param appId the app id
   * @return the boolean
   */
  boolean isCardApiTicketExpired(String appId);

  /**
   * 强制将卡券api ticket过期掉
   *
   * @param appId the app id
   */
  void expireCardApiTicket(String appId);

  /**
   * 应该是线程安全的
   *
   * @param appId the app id
   * @param cardApiTicket 新的cardApi ticket值
   * @param expiresInSeconds 过期时间，以秒为单位
   */
  void updateCardApiTicket(String appId, String cardApiTicket, int expiresInSeconds);

  /**
   * 设置第三方平台基础信息
   *
   * @param componentAppId 第三方平台 appid
   * @param componentAppSecret 第三方平台 appsecret
   * @param componentToken 消息校验Token
   * @param componentAesKey 消息加解密Key
   */
  void setWxOpenInfo(
      String componentAppId,
      String componentAppSecret,
      String componentToken,
      String componentAesKey);

  /** 第三方平台设置API签名 RSA 私钥 */
  String getComponentApiSignatureRsaPrivateKey();

  void setComponentApiSignatureRsaPrivateKey(String apiSignatureRsaPrivateKey);

  /** 第三方平台设置API签名 AES KEY */
  String getComponentApiSignatureAesKey();

  void setComponentApiSignatureAesKey(String apiSignatureAesKey);

  /** 第三方平台设置API签名 RSA 私钥 序号 */
  String getComponentApiSignatureRsaPrivateKeySn();

  void setComponentApiSignatureRsaPrivateKeySn(String apiSignatureRsaPrivateKeySn);

  /** 第三方平台设置API签名 AES key 序号 */
  String getComponentApiSignatureAesKeySn();

  void setComponentApiSignatureAesKeySn(String apiSignatureAesKeySn);
}
