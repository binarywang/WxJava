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
   * 获取当前的 access_token
   *
   * @return 当前的 access_token 字符串
   */
  String getAccessToken();

  // region 稳定版access token
  boolean isStableAccessToken();

  void useStableAccessToken(boolean useStableAccessToken);

  // endregion

  /**
   * 获取用于保护 access_token 更新的锁（线程安全用）
   *
   * @return access_token 的锁对象
   */
  Lock getAccessTokenLock();

  /**
   * 判断 access_token 是否已过期
   *
   * @return 如果已过期则返回 true，否则返回 false
   */
  boolean isAccessTokenExpired();

  /**
   * 强制将 access_token 标记为已过期
   */
  void expireAccessToken();

  /**
   * 应该是线程安全的
   *
   * @param accessToken 要更新的 WxAccessToken 对象
   */
  default void updateAccessToken(WxAccessToken accessToken) {
    updateAccessToken(accessToken.getAccessToken(), accessToken.getExpiresIn());
  }

  /**
   * 应该是线程安全的
   *
   * @param accessToken 新的 access_token 值
   * @param expiresInSeconds 过期时间，单位：秒
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
   * 获取当前的 JSAPI ticket
   *
   * @return 当前的 jsapi_ticket 字符串
   */
  String getJsapiTicket();

  /**
   * 获取用于保护 jsapi_ticket 更新的锁（线程安全用）
   *
   * @return jsapi_ticket 的锁对象
   */
  Lock getJsapiTicketLock();

  /**
   * 判断 jsapi_ticket 是否已过期
   *
   * @return 如果已过期则返回 true，否则返回 false
   */
  boolean isJsapiTicketExpired();

  /**
   * 强制将 jsapi_ticket 标记为已过期
   */
  void expireJsapiTicket();

  /**
   * 应该是线程安全的
   *
   * @param jsapiTicket 新的 jsapi_ticket 值
   * @param expiresInSeconds 过期时间，单位：秒
   */
  void updateJsapiTicket(String jsapiTicket, int expiresInSeconds);

  /**
   * 获取卡券相关的 api_ticket
   *
   * @return 卡券 api_ticket 字符串
   */
  String getCardApiTicket();

  /**
   * 获取用于保护卡券 api_ticket 更新的锁（线程安全用）
   *
   * @return 卡券 api_ticket 的锁对象
   */
  Lock getCardApiTicketLock();

  /**
   * 判断卡券 api_ticket 是否已过期
   *
   * @return 如果已过期则返回 true，否则返回 false
   */
  boolean isCardApiTicketExpired();

  /**
   * 强制将卡券 api_ticket 标记为已过期
   */
  void expireCardApiTicket();

  /**
   * 应该是线程安全的
   *
   * @param apiTicket 新的卡券 api_ticket 值
   * @param expiresInSeconds 过期时间，单位：秒
   */
  void updateCardApiTicket(String apiTicket, int expiresInSeconds);

  /**
   * 获取小程序的 appId
   *
   * @return 小程序的 appId
   */
  String getAppid();

  /**
   * 获取小程序的 secret
   *
   * @return 小程序的 secret
   */
  String getSecret();

  /**
   * 获取消息校验用的 token
   *
   * @return token 字符串
   */
  String getToken();

  /**
   * 获取消息加解密使用的 AES 密钥（用于消息加密/解密）
   *
   * @return AES 密钥字符串
   */
  String getAesKey();

  /**
   * 获取原始 ID（原始公众号/小程序 ID）
   *
   * @return 原始 ID 字符串
   */
  String getOriginalId();

  /**
   * 获取云开发（Cloud）环境标识
   *
   * @return 云环境 ID
   */
  String getCloudEnv();

  /**
   * 获取消息数据的格式（例如 json）
   *
   * @return 消息数据格式字符串
   */
  String getMsgDataFormat();

  /**
   * 获取 access_token 或 ticket 的过期时间（时间戳）
   *
   * @return 过期时间的毫秒时间戳
   */
  long getExpiresTime();

  /**
   * 获取 HTTP 代理主机
   *
   * @return 代理主机名或 IP
   */
  String getHttpProxyHost();

  /**
   * 获取 HTTP 代理端口
   *
   * @return 代理端口号
   */
  int getHttpProxyPort();

  /**
   * 获取 HTTP 代理用户名
   *
   * @return 代理用户名
   */
  String getHttpProxyUsername();

  /**
   * 获取 HTTP 代理密码
   *
   * @return 代理密码
   */
  String getHttpProxyPassword();

  /**
   * HTTP 请求重试间隔（毫秒）
   *
   * <pre>
   *   {@link cn.binarywang.wx.miniapp.api.impl.BaseWxMaServiceImpl#setRetrySleepMillis(int)}
   * </pre>
   *
   * @return 重试间隔，单位：毫秒
   */
  int getRetrySleepMillis();

  /**
   * HTTP 请求最大重试次数
   *
   * <pre>
   *   {@link cn.binarywang.wx.miniapp.api.impl.BaseWxMaServiceImpl#setMaxRetryTimes(int)}
   * </pre>
   *
   * @return 最大重试次数
   */
  int getMaxRetryTimes();

  /**
   * 获取用于创建 HTTP 客户端的 ApacheHttpClientBuilder
   *
   * @return ApacheHttpClientBuilder 实例
   */
  ApacheHttpClientBuilder getApacheHttpClientBuilder();

  /**
   * 是否在 token 失效时自动刷新
   *
   * @return 如果自动刷新则返回 true，否则返回 false
   */
  boolean autoRefreshToken();

  /**
   * 设置自定义的 apiHost 地址
   * 具体取值，可以参考 <a href="https://developers.weixin.qq.com/doc/offiaccount/Basic_Information/Interface_field_description.html">API 域名文档</a>
   *
   * @param apiHostUrl api 域名地址
   */
  void setApiHostUrl(String apiHostUrl);

  /**
   * 获取自定义的 apiHost 地址，用于替换原请求中的 <a href="https://api.weixin.qq.com">https://api.weixin.qq.com</a>
   * 具体取值，可以参考 <a href="https://developers.weixin.qq.com/doc/offiaccount/Basic_Information/Interface_field_description.html">API 域名文档</a>
   *
   * @return 自定义的 api 域名地址
   */
  String getApiHostUrl();

  /**
   * 获取自定义的获取 accessToken 地址，用于向自定义统一服务获取 accessToken
   *
   * @return 自定义的获取 accessToken 地址
   */
  String getAccessTokenUrl();

  /**
   * 设置自定义的获取 accessToken 地址，可用于设置获取 accessToken 的自定义服务
   *
   * @param accessTokenUrl 自定义的获取 accessToken 地址
   */
  void setAccessTokenUrl(String accessTokenUrl);

  /**
   * 服务端 API 签名用到的 RSA 私钥（pkcs8 格式，会以 -----BEGIN PRIVATE KEY----- 开头，
   * 'BEGIN RSA PRIVATE KEY' 的是 pkcs1 格式，需要转换（可用 openssl 转换）。设置参考：
   * <a href="https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/getting_started/api_signature.html">API 签名文档</a>
   *
   * @return RSA 私钥字符串（pkcs8 格式）
   */
  String getApiSignatureRsaPrivateKey();

  /**
   * 服务端 API 签名用到的 AES 密钥
   * <a href="https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/getting_started/api_signature.html">API 签名文档</a>
   *
   * @return AES 密钥字符串
   */
  String getApiSignatureAesKey();

  /** 密钥对应的序号 */
  String getApiSignatureAesKeySn();

  /** 密钥对应的序号 */
  String getApiSignatureRsaPrivateKeySn();

  /** 密钥对应的小程序 ID（普通小程序为 appId，托管第三方平台为 componentAppId） */
  String getWechatMpAppid();
}
