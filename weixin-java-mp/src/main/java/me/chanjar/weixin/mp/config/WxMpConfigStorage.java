package me.chanjar.weixin.mp.config;

import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.enums.TicketType;
import me.chanjar.weixin.common.util.http.apache.ApacheHttpClientBuilder;

import java.io.File;
import java.util.concurrent.locks.Lock;

/**
 * 微信客户端配置存储.
 *
 * @author chanjarster
 */
public interface WxMpConfigStorage {
  /**
   * Gets access token.
   *
   * @return the access token
   */
  String getAccessToken();

  /**
   * Is use stable access token api
   *
   * @return the boolean
   * @link https://developers.weixin.qq.com/doc/offiaccount/Basic_Information/getStableAccessToken.html
   */
  boolean isStableAccessToken();

  /**
   * Set use stable access token api
   *
   * @param useStableAccessToken true is use, false is not
   */
  void useStableAccessToken(boolean useStableAccessToken);

  /**
   * Gets access token lock.
   *
   * @return the access token lock
   */
  Lock getAccessTokenLock();

  /**
   * Is access token expired boolean.
   *
   * @return the boolean
   */
  boolean isAccessTokenExpired();

  /**
   * 强制将access token过期掉.
   */
  void expireAccessToken();

  /**
   * 应该是线程安全的.
   *
   * @param accessToken 要更新的WxAccessToken对象
   */
  void updateAccessToken(WxAccessToken accessToken);

  /**
   * 应该是线程安全的.
   *
   * @param accessToken      新的accessToken值
   * @param expiresInSeconds 过期时间，以秒为单位
   */
  void updateAccessToken(String accessToken, int expiresInSeconds);

  /**
   * Gets ticket.
   *
   * @param type the type
   * @return the ticket
   */
  String getTicket(TicketType type);

  /**
   * Gets ticket lock.
   *
   * @param type the type
   * @return the ticket lock
   */
  Lock getTicketLock(TicketType type);

  /**
   * Is ticket expired boolean.
   *
   * @param type the type
   * @return the boolean
   */
  boolean isTicketExpired(TicketType type);

  /**
   * 强制将ticket过期掉.
   *
   * @param type the type
   */
  void expireTicket(TicketType type);

  /**
   * 更新ticket.
   * 应该是线程安全的
   *
   * @param type             ticket类型
   * @param ticket           新的ticket值
   * @param expiresInSeconds 过期时间，以秒为单位
   */
  void updateTicket(TicketType type, String ticket, int expiresInSeconds);

  /**
   * Gets app id.
   *
   * @return the app id
   */
  String getAppId();

  /**
   * Gets secret.
   *
   * @return the secret
   */
  String getSecret();

  /**
   * Gets token.
   *
   * @return the token
   */
  String getToken();

  /**
   * Gets aes key.
   *
   * @return the aes key
   */
  String getAesKey();

  /**
   * Gets template id.
   *
   * @return the template id
   */
  String getTemplateId();

  /**
   * Gets expires time.
   *
   * @return the expires time
   */
  long getExpiresTime();

  /**
   * Gets oauth 2 redirect uri.
   *
   * @return the oauth 2 redirect uri
   * @deprecated This method is deprecated due to incorrect naming convention.
   * Use {@link #getOauth2RedirectUrl()} instead.
   */
  @Deprecated
  String getOauth2redirectUri();

  /**
   * Gets OAuth 2.0 redirect Url
   *
   * @return the OAuth 2.0 redirect Url
   * @author <a href="https://gitee.com/pengles">Peng Les</a>
   */
  String getOauth2RedirectUrl();

  /**
   * Gets QR connect redirect Url
   *
   * @return the QR connect redirect Url
   * @author <a href="https://gitee.com/pengles">Peng Les</a>
   */
  String getQrConnectRedirectUrl();

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
   * <pre>
   *   {@link me.chanjar.weixin.mp.api.impl.BaseWxMpServiceImpl#setRetrySleepMillis(int)}
   * </pre>
   */
  int getRetrySleepMillis();

  /**
   * http 请求最大重试次数
   * <pre>
   *   {@link me.chanjar.weixin.mp.api.impl.BaseWxMpServiceImpl#setMaxRetryTimes(int)}
   * </pre>
   */
  int getMaxRetryTimes();

  /**
   * Gets tmp dir file.
   *
   * @return the tmp dir file
   */
  File getTmpDirFile();

  /**
   * http client builder.
   *
   * @return ApacheHttpClientBuilder apache http client builder
   */
  ApacheHttpClientBuilder getApacheHttpClientBuilder();

  /**
   * 是否自动刷新token.
   *
   * @return the boolean
   */
  boolean autoRefreshToken();

  /**
   * 得到微信接口地址域名部分的自定义设置信息.
   *
   * @return the host config
   */
  WxMpHostConfig getHostConfig();

  /**
   * 设置微信接口地址域名部分的自定义设置信息.
   *
   * @param hostConfig host config
   */
  void setHostConfig(WxMpHostConfig hostConfig);
}
