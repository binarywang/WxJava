package me.chanjar.weixin.channel.config;

import java.util.concurrent.locks.Lock;
import me.chanjar.weixin.channel.api.BaseWxChannelService;
import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.util.http.apache.ApacheHttpClientBuilder;

/**
 * 视频号小店配置
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
public interface WxChannelConfig {

  /**
   * Gets access token.
   *
   * @return the access token
   */
  String getAccessToken();

  /**
   * Is use stable access token api
   *
   * @link <a href="https://developers.weixin.qq.com/doc/store/API/basics/getStableAccessToken.html">获取稳定版AccessToken</a>
   * @return the boolean
   */
  boolean isStableAccessToken();

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
   * 强制将access token过期掉
   */
  void expireAccessToken();

  /**
   * 应该是线程安全的
   *
   * @param accessToken 要更新的WxAccessToken对象
   */
  void updateAccessToken(WxAccessToken accessToken);

  /**
   * 应该是线程安全的
   *
   * @param accessToken      新的accessToken值
   * @param expiresInSeconds 过期时间，以秒为单位
   */
  void updateAccessToken(String accessToken, int expiresInSeconds);

  /**
   * Gets appid.
   *
   * @return the appid
   */
  String getAppid();

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
   * Gets msg data format.
   *
   * @return the msg data format
   */
  String getMsgDataFormat();

  /**
   * Gets expires time.
   *
   * @return the expires time
   */
  long getExpiresTime();

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
   *  {@link BaseWxChannelService#setRetrySleepMillis(int)(int)}
   * </pre>
   */
  int getRetrySleepMillis();

  /**
   * http 请求最大重试次数
   * <pre>
   *   {@link BaseWxChannelService#setMaxRetryTimes(int)}
   * </pre>
   */
  int getMaxRetryTimes();

  /**
   * http client builder
   *
   * @return ApacheHttpClientBuilder apache http client builder
   */
  ApacheHttpClientBuilder getApacheHttpClientBuilder();

  /**
   * 是否自动刷新token
   *
   * @return the boolean
   */
  boolean autoRefreshToken();

  /**
   * 设置自定义的apiHost地址
   * 具体取值，可以参考https://developers.weixin.qq.com/doc/offiaccount/Basic_Information/Interface_field_description.html
   *
   * @param apiHostUrl api域名地址
   */
  void setApiHostUrl(String apiHostUrl);

  /**
   * 获取自定义的apiHost地址，用于替换原请求中的https://api.weixin.qq.com
   * 具体取值，可以参考https://developers.weixin.qq.com/doc/offiaccount/Basic_Information/Interface_field_description.html
   *
   * @return 自定义的api域名地址
   */
  String getApiHostUrl();

  /**
   * 获取自定义的获取accessToken地址，用于向自定义统一服务获取accessToken
   *
   * @return 自定义的获取accessToken地址
   */
  String getAccessTokenUrl();

  /**
   * 设置自定义的获取accessToken地址 可用于设置获取accessToken的自定义服务
   *
   * @param accessTokenUrl 自定义的获取accessToken地址
   */
  void setAccessTokenUrl(String accessTokenUrl);
}
