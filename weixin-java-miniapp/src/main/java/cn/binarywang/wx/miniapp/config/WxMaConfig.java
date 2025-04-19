package cn.binarywang.wx.miniapp.config;

import java.util.concurrent.locks.Lock;
import java.util.function.Consumer;
import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.bean.WxAccessTokenEntity;
import me.chanjar.weixin.common.util.http.apache.ApacheHttpClientBuilder;

/**
 * 小程序配置
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public interface WxMaConfig {

  default void setUpdateAccessTokenBefore(Consumer<WxAccessTokenEntity> updateAccessTokenBefore) {}

  /**
   * Gets access token.
   *
   * @return the access token
   */
  String getAccessToken();

  // region 稳定版access token
  boolean isStableAccessToken();

  void useStableAccessToken(boolean useStableAccessToken);

  // endregion

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

  /** 强制将access token过期掉 */
  void expireAccessToken();

  /**
   * 应该是线程安全的
   *
   * @param accessToken 要更新的WxAccessToken对象
   */
  default void updateAccessToken(WxAccessToken accessToken) {
    updateAccessToken(accessToken.getAccessToken(), accessToken.getExpiresIn());
  }

  /**
   * 应该是线程安全的
   *
   * @param accessToken 新的accessToken值
   * @param expiresInSeconds 过期时间，以秒为单位
   */
  void updateAccessToken(String accessToken, int expiresInSeconds);

  default void updateAccessTokenProcessor(String accessToken, int expiresInSeconds) {
    WxAccessTokenEntity wxAccessTokenEntity = new WxAccessTokenEntity();
    wxAccessTokenEntity.setAppid(getAppid());
    wxAccessTokenEntity.setAccessToken(accessToken);
    wxAccessTokenEntity.setExpiresIn(expiresInSeconds);
    updateAccessTokenBefore(wxAccessTokenEntity);
    updateAccessToken(accessToken, expiresInSeconds);
  }

  default void updateAccessTokenBefore(WxAccessTokenEntity wxAccessTokenEntity) {}

  /**
   * Gets jsapi ticket.
   *
   * @return the jsapi ticket
   */
  String getJsapiTicket();

  /**
   * Gets jsapi ticket lock.
   *
   * @return the jsapi ticket lock
   */
  Lock getJsapiTicketLock();

  /**
   * Is jsapi ticket expired boolean.
   *
   * @return the boolean
   */
  boolean isJsapiTicketExpired();

  /** 强制将jsapi ticket过期掉 */
  void expireJsapiTicket();

  /**
   * 应该是线程安全的
   *
   * @param jsapiTicket 新的jsapi ticket值
   * @param expiresInSeconds 过期时间，以秒为单位
   */
  void updateJsapiTicket(String jsapiTicket, int expiresInSeconds);

  /**
   * 卡券api_ticket.
   *
   * @return the card api ticket
   */
  String getCardApiTicket();

  /**
   * Gets card api ticket lock.
   *
   * @return the card api ticket lock
   */
  Lock getCardApiTicketLock();

  /**
   * Is card api ticket expired boolean.
   *
   * @return the boolean
   */
  boolean isCardApiTicketExpired();

  /** 强制将卡券api ticket过期掉. */
  void expireCardApiTicket();

  /**
   * 应该是线程安全的.
   *
   * @param apiTicket 新的卡券api ticket值
   * @param expiresInSeconds 过期时间，以秒为单位
   */
  void updateCardApiTicket(String apiTicket, int expiresInSeconds);

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
   * Gets original id.
   *
   * @return the original id
   */
  String getOriginalId();

  /**
   * Gets cloud env.
   *
   * @return the cloud env
   */
  String getCloudEnv();

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
   *
   * <pre>
   *   {@link cn.binarywang.wx.miniapp.api.impl.BaseWxMaServiceImpl#setRetrySleepMillis(int)}
   * </pre>
   */
  int getRetrySleepMillis();

  /**
   * http 请求最大重试次数
   *
   * <pre>
   *   {@link cn.binarywang.wx.miniapp.api.impl.BaseWxMaServiceImpl#setMaxRetryTimes(int)}
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

  /**
   * 服务端API签名用到的RSA私钥【pkcs8格式，会以 -----BEGIN PRIVATE KEY-----开头， 'BEGIN RSA PRIVATE
   * KEY'的是pkcs1格式，需要转换（可用openssl转换）。 设置参考：
   * https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/getting_started/api_signature.html
   *
   * @return rsa private key string
   */
  String getApiSignatureRsaPrivateKey();

  /**
   * 服务端API签名用到的AES密钥
   * https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/getting_started/api_signature.html
   *
   * @return aes key string
   */
  String getApiSignatureAesKey();

  /** 密钥对应的序号 */
  String getApiSignatureAesKeySn();

  /** 密钥对应的序号 */
  String getApiSignatureRsaPrivateKeySn();

  /** 密钥对应的小程序ID （普通小程序同 appId， 托管第三方平台的是 componentAppId） */
  String getWechatMpAppid();
}
