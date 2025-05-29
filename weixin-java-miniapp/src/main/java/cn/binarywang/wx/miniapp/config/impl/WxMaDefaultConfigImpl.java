package cn.binarywang.wx.miniapp.config.impl;

import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import java.io.File;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.common.bean.WxAccessTokenEntity;
import me.chanjar.weixin.common.util.http.apache.ApacheHttpClientBuilder;

/**
 * 基于内存的微信配置provider，在实际生产环境中应该将这些配置持久化
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Getter
public class WxMaDefaultConfigImpl implements WxMaConfig {
  protected volatile String appid;
  protected volatile String token;

  /** 是否使用稳定版获取accessToken接口 */
  @Getter(value = AccessLevel.NONE)
  private boolean useStableAccessToken;

  /** 小程序原始ID */
  protected volatile String originalId;

  protected Lock accessTokenLock = new ReentrantLock();

  /** 临时文件目录. */
  protected volatile File tmpDirFile;

  private volatile String msgDataFormat;
  private volatile String secret;
  private volatile String accessToken;
  private volatile String aesKey;
  private volatile long expiresTime;
  private volatile String apiSignatureRsaPrivateKey;
  private volatile String apiSignatureAesKey;
  private volatile String apiSignatureRsaPrivateKeySn;
  private volatile String apiSignatureAesKeySn;
  private volatile String wechatMpAppid;

  /** 云环境ID */
  private volatile String cloudEnv;

  private volatile String httpProxyHost;
  private volatile int httpProxyPort;
  private volatile String httpProxyUsername;
  private volatile String httpProxyPassword;

  private volatile int retrySleepMillis = 1000;
  private volatile int maxRetryTimes = 5;

  private volatile String jsapiTicket;
  private volatile long jsapiTicketExpiresTime;

  /** 微信卡券的ticket单独缓存. */
  private volatile String cardApiTicket;

  private volatile long cardApiTicketExpiresTime;
  protected volatile Lock jsapiTicketLock = new ReentrantLock();
  protected volatile Lock cardApiTicketLock = new ReentrantLock();
  private volatile ApacheHttpClientBuilder apacheHttpClientBuilder;
  private String apiHostUrl;
  private String accessTokenUrl;

  /** 自定义配置token的消费者 */
  @Setter private Consumer<WxAccessTokenEntity> updateAccessTokenBefore;

  /** 开启回调 */
  @Getter(AccessLevel.NONE)
  private boolean enableUpdateAccessTokenBefore = true;

  /** 可临时关闭更新token回调，主要用于其他介质初始化数据时，可不进行回调 */
  public void enableUpdateAccessTokenBefore(boolean enableUpdateAccessTokenBefore) {
    this.enableUpdateAccessTokenBefore = enableUpdateAccessTokenBefore;
  }

  /** 会过期的数据提前过期时间，默认预留200秒的时间 */
  protected long expiresAheadInMillis(int expiresInSeconds) {
    return System.currentTimeMillis() + (expiresInSeconds - 200) * 1000L;
  }

  /** 判断 expiresTime 是否已经过期 */
  protected boolean isExpired(long expiresTime) {
    return System.currentTimeMillis() > expiresTime;
  }

  @Override
  public String getAccessToken() {
    return this.accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  // region 使用稳定版接口获取accessToken
  @Override
  public boolean isStableAccessToken() {
    return this.useStableAccessToken;
  }

  @Override
  public void useStableAccessToken(boolean useStableAccessToken) {
    this.useStableAccessToken = useStableAccessToken;
  }

  // endregion

  @Override
  public Lock getAccessTokenLock() {
    return this.accessTokenLock;
  }

  public void setAccessTokenLock(Lock accessTokenLock) {
    this.accessTokenLock = accessTokenLock;
  }

  @Override
  public boolean isAccessTokenExpired() {
    return isExpired(this.expiresTime);
  }

  //  @Override
  //  public synchronized void updateAccessToken(WxAccessToken accessToken) {
  //    updateAccessToken(accessToken.getAccessToken(), accessToken.getExpiresIn());
  //  }

  @Override
  public synchronized void updateAccessToken(String accessToken, int expiresInSeconds) {
    setAccessToken(accessToken);
    setExpiresTime(expiresAheadInMillis(expiresInSeconds));
  }

  @Override
  public void updateAccessTokenBefore(WxAccessTokenEntity wxAccessTokenEntity) {
    if (updateAccessTokenBefore != null && enableUpdateAccessTokenBefore) {
      updateAccessTokenBefore.accept(wxAccessTokenEntity);
    }
  }

  @Override
  public String getJsapiTicket() {
    return this.jsapiTicket;
  }

  @Override
  public Lock getJsapiTicketLock() {
    return this.jsapiTicketLock;
  }

  @Override
  public boolean isJsapiTicketExpired() {
    return isExpired(this.jsapiTicketExpiresTime);
  }

  @Override
  public void expireJsapiTicket() {
    this.jsapiTicketExpiresTime = 0;
  }

  @Override
  public void updateJsapiTicket(String jsapiTicket, int expiresInSeconds) {
    this.jsapiTicket = jsapiTicket;
    this.jsapiTicketExpiresTime = expiresAheadInMillis(expiresInSeconds);
  }

  @Override
  public String getCardApiTicket() {
    return this.cardApiTicket;
  }

  @Override
  public Lock getCardApiTicketLock() {
    return this.cardApiTicketLock;
  }

  @Override
  public boolean isCardApiTicketExpired() {
    return isExpired(this.cardApiTicketExpiresTime);
  }

  @Override
  public void expireCardApiTicket() {
    this.cardApiTicketExpiresTime = 0;
  }

  @Override
  public void updateCardApiTicket(String cardApiTicket, int expiresInSeconds) {
    this.cardApiTicket = cardApiTicket;
    this.cardApiTicketExpiresTime = expiresAheadInMillis(expiresInSeconds);
  }

  @Override
  public void expireAccessToken() {
    this.expiresTime = 0;
  }

  @Override
  public String getSecret() {
    return this.secret;
  }

  public void setSecret(String secret) {
    this.secret = secret;
  }

  @Override
  public String getToken() {
    return this.token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  @Override
  public long getExpiresTime() {
    return this.expiresTime;
  }

  public void setExpiresTime(long expiresTime) {
    this.expiresTime = expiresTime;
  }

  @Override
  public String getAesKey() {
    return this.aesKey;
  }

  public void setAesKey(String aesKey) {
    this.aesKey = aesKey;
  }

  public String getApiSignatureRsaPrivateKey() {
    return apiSignatureRsaPrivateKey;
  }

  public void setApiSignatureRsaPrivateKey(String apiSignatureRsaPrivateKey) {
    this.apiSignatureRsaPrivateKey = apiSignatureRsaPrivateKey;
  }

  public String getApiSignatureAesKey() {
    return apiSignatureAesKey;
  }

  public void setApiSignatureAesKey(String apiSignatureAesKey) {
    this.apiSignatureAesKey = apiSignatureAesKey;
  }

  public String getApiSignatureRsaPrivateKeySn() {
    return apiSignatureRsaPrivateKeySn;
  }

  public void setApiSignatureRsaPrivateKeySn(String apiSignatureRsaPrivateKeySn) {
    this.apiSignatureRsaPrivateKeySn = apiSignatureRsaPrivateKeySn;
  }

  public String getApiSignatureAesKeySn() {
    return apiSignatureAesKeySn;
  }

  public void setApiSignatureAesKeySn(String apiSignatureAesKeySn) {
    this.apiSignatureAesKeySn = apiSignatureAesKeySn;
  }

  public String getWechatMpAppid() {
    return wechatMpAppid == null ? appid : wechatMpAppid;
  }

  public void setWechatMpAppid(String wechatMpAppid) {
    this.wechatMpAppid = wechatMpAppid;
  }

  @Override
  public String getOriginalId() {
    return originalId;
  }

  public void setOriginalId(String originalId) {
    this.originalId = originalId;
  }

  @Override
  public String getCloudEnv() {
    return this.cloudEnv;
  }

  public void setCloudEnv(String cloudEnv) {
    this.cloudEnv = cloudEnv;
  }

  @Override
  public String getMsgDataFormat() {
    return this.msgDataFormat;
  }

  public void setMsgDataFormat(String msgDataFormat) {
    this.msgDataFormat = msgDataFormat;
  }

  @Override
  public String getHttpProxyHost() {
    return this.httpProxyHost;
  }

  public void setHttpProxyHost(String httpProxyHost) {
    this.httpProxyHost = httpProxyHost;
  }

  @Override
  public int getHttpProxyPort() {
    return this.httpProxyPort;
  }

  public void setHttpProxyPort(int httpProxyPort) {
    this.httpProxyPort = httpProxyPort;
  }

  @Override
  public String getHttpProxyUsername() {
    return this.httpProxyUsername;
  }

  public void setHttpProxyUsername(String httpProxyUsername) {
    this.httpProxyUsername = httpProxyUsername;
  }

  @Override
  public String getHttpProxyPassword() {
    return this.httpProxyPassword;
  }

  public void setHttpProxyPassword(String httpProxyPassword) {
    this.httpProxyPassword = httpProxyPassword;
  }

  @Override
  public int getRetrySleepMillis() {
    return this.retrySleepMillis;
  }

  public void setRetrySleepMillis(int retrySleepMillis) {
    this.retrySleepMillis = retrySleepMillis;
  }

  @Override
  public int getMaxRetryTimes() {
    return this.maxRetryTimes;
  }

  public void setMaxRetryTimes(int maxRetryTimes) {
    this.maxRetryTimes = maxRetryTimes;
  }

  @Override
  public String toString() {
    return WxMaGsonBuilder.create().toJson(this);
  }

  @Override
  public ApacheHttpClientBuilder getApacheHttpClientBuilder() {
    return this.apacheHttpClientBuilder;
  }

  public void setApacheHttpClientBuilder(ApacheHttpClientBuilder apacheHttpClientBuilder) {
    this.apacheHttpClientBuilder = apacheHttpClientBuilder;
  }

  @Override
  public boolean autoRefreshToken() {
    return true;
  }

  @Override
  public void setApiHostUrl(String apiHostUrl) {
    this.apiHostUrl = apiHostUrl;
  }

  @Override
  public void setAccessTokenUrl(String accessTokenUrl) {
    this.accessTokenUrl = accessTokenUrl;
  }

  @Override
  public String getAppid() {
    return appid;
  }

  public void setAppid(String appid) {
    this.appid = appid;
  }
}
