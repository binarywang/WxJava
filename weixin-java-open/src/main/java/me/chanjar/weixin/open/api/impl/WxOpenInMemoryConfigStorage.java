package me.chanjar.weixin.open.api.impl;

import cn.binarywang.wx.miniapp.config.WxMaConfig;
import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.enums.TicketType;
import me.chanjar.weixin.common.util.http.apache.ApacheHttpClientBuilder;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.config.WxMpHostConfig;
import me.chanjar.weixin.open.api.WxOpenConfigStorage;
import me.chanjar.weixin.open.bean.WxOpenAuthorizerAccessToken;
import me.chanjar.weixin.open.bean.WxOpenComponentAccessToken;
import me.chanjar.weixin.open.util.json.WxOpenGsonBuilder;

/**
 * 基于内存的微信配置provider，在实际生产环境中应该将这些配置持久化
 *
 * @author <a href="https://github.com/007gzs">007</a>
 */
@Data
public class WxOpenInMemoryConfigStorage implements WxOpenConfigStorage {
  private String componentAppId;
  private String componentAppSecret;
  private String componentToken;
  private String componentAesKey;
  private String componentVerifyTicket;
  private String componentAccessToken;
  private long componentExpiresTime;

  private String componentApiSignatureRsaPrivateKey;
  private String componentApiSignatureAesKey;
  private String componentApiSignatureRsaPrivateKeySn;
  private String componentApiSignatureAesKeySn;

  private String httpProxyHost;
  private int httpProxyPort;
  private String httpProxyUsername;
  private String httpProxyPassword;

  /**
   * http 请求重试间隔
   *
   * <pre>
   *   {@link me.chanjar.weixin.mp.api.impl.BaseWxMpServiceImpl#setRetrySleepMillis(int)}
   *   {@link cn.binarywang.wx.miniapp.api.impl.BaseWxMaServiceImpl#setRetrySleepMillis(int)}
   * </pre>
   */
  private int retrySleepMillis = 1000;

  /**
   * http 请求最大重试次数
   *
   * <pre>
   *   {@link me.chanjar.weixin.mp.api.impl.BaseWxMpServiceImpl#setMaxRetryTimes(int)}
   *   {@link cn.binarywang.wx.miniapp.api.impl.BaseWxMaServiceImpl#setMaxRetryTimes(int)}
   * </pre>
   */
  private int maxRetryTimes = 5;

  private ApacheHttpClientBuilder apacheHttpClientBuilder;

  private Map<String, Token> authorizerRefreshTokens = new ConcurrentHashMap<>();
  private Map<String, Token> authorizerAccessTokens = new ConcurrentHashMap<>();
  private Map<String, Token> jsapiTickets = new ConcurrentHashMap<>();
  private Map<String, Token> cardApiTickets = new ConcurrentHashMap<>();
  private Map<String, Lock> locks = new ConcurrentHashMap<>();

  @Override
  public boolean isComponentAccessTokenExpired() {
    return System.currentTimeMillis() > componentExpiresTime;
  }

  @Override
  public void expireComponentAccessToken() {
    this.componentExpiresTime = 0L;
  }

  @Override
  public void updateComponentAccessToken(WxOpenComponentAccessToken componentAccessToken) {
    updateComponentAccessToken(
        componentAccessToken.getComponentAccessToken(), componentAccessToken.getExpiresIn());
  }

  private Lock accessTokenLockInstance;

  @Override
  public Lock getComponentAccessTokenLock() {
    if (this.accessTokenLockInstance == null) {
      synchronized (this) {
        if (this.accessTokenLockInstance == null) {
          this.accessTokenLockInstance = getLockByKey("componentAccessTokenLock");
        }
      }
    }
    return this.accessTokenLockInstance;
  }

  @Override
  public Lock getLockByKey(String key) {
    Lock lock = locks.get(key);
    if (lock == null) {
      synchronized (this) {
        lock = locks.get(key);
        if (lock == null) {
          lock = new ReentrantLock();
          locks.put(key, lock);
        }
      }
    }
    return lock;
  }

  @Override
  public WxMpConfigStorage getWxMpConfigStorage(String appId) {
    return new WxOpenInnerConfigStorage(this, appId);
  }

  @Override
  public WxMaConfig getWxMaConfig(String appId) {
    return new WxOpenInnerConfigStorage(this, appId);
  }

  @Override
  public void updateComponentAccessToken(String componentAccessToken, int expiresInSeconds) {
    this.componentAccessToken = componentAccessToken;
    this.componentExpiresTime = System.currentTimeMillis() + (expiresInSeconds - 200) * 1000L;
  }

  @Override
  public void setWxOpenInfo(
      String componentAppId,
      String componentAppSecret,
      String componentToken,
      String componentAesKey) {
    setComponentAppId(componentAppId);
    setComponentAppSecret(componentAppSecret);
    setComponentToken(componentToken);
    setComponentAesKey(componentAesKey);
  }

  @Override
  public boolean autoRefreshToken() {
    return true;
  }

  private String getTokenString(Map<String, Token> map, String key) {
    Token token = map.get(key);
    if (token == null
        || (token.expiresTime != null && System.currentTimeMillis() > token.expiresTime)) {
      return null;
    }
    return token.token;
  }

  private void expireToken(Map<String, Token> map, String key) {
    Token token = map.get(key);
    if (token != null) {
      token.expiresTime = 0L;
    }
  }

  private void updateToken(
      Map<String, Token> map, String key, String tokenString, Integer expiresInSeconds) {
    Token token = map.get(key);
    if (token == null) {
      token = new Token();
      map.put(key, token);
    }
    token.token = tokenString;
    if (expiresInSeconds != null && expiresInSeconds != -1) {
      token.expiresTime = System.currentTimeMillis() + (expiresInSeconds - 200) * 1000L;
    }
  }

  @Override
  public String getAuthorizerRefreshToken(String appId) {
    return getTokenString(authorizerRefreshTokens, appId);
  }

  @Override
  public void setAuthorizerRefreshToken(String appId, String authorizerRefreshToken) {
    updateToken(authorizerRefreshTokens, appId, authorizerRefreshToken, null);
  }

  @Override
  public void updateAuthorizerRefreshToken(String appId, String authorizerRefreshToken) {
    this.setAuthorizerRefreshToken(appId, authorizerRefreshToken);
  }

  @Override
  public String getAuthorizerAccessToken(String appId) {
    return getTokenString(authorizerAccessTokens, appId);
  }

  @Override
  public boolean isAuthorizerAccessTokenExpired(String appId) {
    return getTokenString(authorizerAccessTokens, appId) == null;
  }

  @Override
  public void expireAuthorizerAccessToken(String appId) {
    expireToken(authorizerAccessTokens, appId);
  }

  @Override
  public void updateAuthorizerAccessToken(
      String appId, WxOpenAuthorizerAccessToken authorizerAccessToken) {
    updateAuthorizerAccessToken(
        appId,
        authorizerAccessToken.getAuthorizerAccessToken(),
        authorizerAccessToken.getExpiresIn());
  }

  @Override
  public void updateAuthorizerAccessToken(
      String appId, String authorizerAccessToken, int expiresInSeconds) {
    updateToken(authorizerAccessTokens, appId, authorizerAccessToken, expiresInSeconds);
  }

  @Override
  public String getJsapiTicket(String appId) {
    return getTokenString(jsapiTickets, appId);
  }

  @Override
  public boolean isJsapiTicketExpired(String appId) {
    return getTokenString(jsapiTickets, appId) == null;
  }

  @Override
  public void expireJsapiTicket(String appId) {
    expireToken(jsapiTickets, appId);
  }

  @Override
  public void updateJsapiTicket(String appId, String jsapiTicket, int expiresInSeconds) {
    updateToken(jsapiTickets, appId, jsapiTicket, expiresInSeconds);
  }

  @Override
  public String getCardApiTicket(String appId) {
    return getTokenString(cardApiTickets, appId);
  }

  @Override
  public boolean isCardApiTicketExpired(String appId) {
    return getTokenString(cardApiTickets, appId) == null;
  }

  @Override
  public void expireCardApiTicket(String appId) {
    expireToken(cardApiTickets, appId);
  }

  @Override
  public void updateCardApiTicket(String appId, String cardApiTicket, int expiresInSeconds) {
    updateToken(cardApiTickets, appId, cardApiTicket, expiresInSeconds);
  }

  @Data
  private static class Token {
    private String token;
    private Long expiresTime;
  }

  @Data
  private static class WxOpenInnerConfigStorage implements WxMpConfigStorage, WxMaConfig {
    private final WxOpenConfigStorage wxOpenConfigStorage;
    private final String appId;
    private WxMpHostConfig hostConfig;
    private String apiHostUrl;
    private String accessTokenUrl;

    /** 是否使用稳定版获取accessToken接口 */
    @Getter(value = AccessLevel.NONE)
    @Setter(value = AccessLevel.NONE)
    private boolean useStableAccessToken;

    /** 小程序原始ID */
    private volatile String originalId;

    /** 云环境ID */
    private volatile String cloudEnv;

    private final Lock accessTokenLock;
    private final Lock jsapiTicketLock;
    private final Lock cardApiTicketLock;

    private WxOpenInnerConfigStorage(WxOpenConfigStorage wxOpenConfigStorage, String appId) {
      this.wxOpenConfigStorage = wxOpenConfigStorage;
      this.appId = appId;
      this.accessTokenLock = wxOpenConfigStorage.getLockByKey(appId + ":accessTokenLock");
      this.jsapiTicketLock = wxOpenConfigStorage.getLockByKey(appId + ":jsapiTicketLock");
      this.cardApiTicketLock = wxOpenConfigStorage.getLockByKey(appId + ":cardApiTicketLock");
    }

    @Override
    public String getAccessToken() {
      return wxOpenConfigStorage.getAuthorizerAccessToken(appId);
    }

    @Override
    public boolean isStableAccessToken() {
      return this.useStableAccessToken;
    }

    @Override
    public void useStableAccessToken(boolean useStableAccessToken) {
      this.useStableAccessToken = useStableAccessToken;
    }

    @Override
    public Lock getAccessTokenLock() {
      return this.accessTokenLock;
    }

    @Override
    public boolean isAccessTokenExpired() {
      return wxOpenConfigStorage.isAuthorizerAccessTokenExpired(appId);
    }

    @Override
    public synchronized void updateAccessToken(WxAccessToken accessToken) {
      updateAccessToken(accessToken.getAccessToken(), accessToken.getExpiresIn());
    }

    @Override
    public synchronized void updateAccessToken(String accessToken, int expiresInSeconds) {
      wxOpenConfigStorage.updateAuthorizerAccessToken(appId, accessToken, expiresInSeconds);
    }

    @Override
    public String getTicket(TicketType type) {
      switch (type) {
        case JSAPI:
          {
            return wxOpenConfigStorage.getJsapiTicket(appId);
          }
        case WX_CARD:
          {
            return wxOpenConfigStorage.getCardApiTicket(appId);
          }
        default:
          {
            // do nothing
          }
      }
      return null;
    }

    @Override
    public Lock getTicketLock(TicketType type) {
      switch (type) {
        case JSAPI:
          {
            return this.jsapiTicketLock;
          }
        case WX_CARD:
          {
            return this.cardApiTicketLock;
          }
        default:
          {
            // do nothing
          }
      }
      return null;
    }

    @Override
    public boolean isTicketExpired(TicketType type) {
      switch (type) {
        case JSAPI:
          {
            return wxOpenConfigStorage.isJsapiTicketExpired(appId);
          }
        case WX_CARD:
          {
            return wxOpenConfigStorage.isCardApiTicketExpired(appId);
          }
        default:
          {
            // do nothing
          }
      }

      return false;
    }

    @Override
    public void expireTicket(TicketType type) {
      switch (type) {
        case JSAPI:
          {
            wxOpenConfigStorage.expireJsapiTicket(appId);
            break;
          }
        case WX_CARD:
          {
            wxOpenConfigStorage.expireCardApiTicket(appId);
            break;
          }
        default:
          {
            // do nothing
          }
      }
    }

    @Override
    public void updateTicket(TicketType type, String ticket, int expiresInSeconds) {
      switch (type) {
        case JSAPI:
          {
            wxOpenConfigStorage.updateJsapiTicket(appId, ticket, expiresInSeconds);
            break;
          }
        case WX_CARD:
          {
            wxOpenConfigStorage.updateCardApiTicket(appId, ticket, expiresInSeconds);
            break;
          }
        default:
          {
            // do nothing
          }
      }
    }

    @Override
    public String getAppid() {
      return this.appId;
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
    public void expireAccessToken() {
      wxOpenConfigStorage.expireAuthorizerAccessToken(appId);
    }

    @Override
    public String getJsapiTicket() {
      return wxOpenConfigStorage.getJsapiTicket(appId);
    }

    @Override
    public Lock getJsapiTicketLock() {
      return this.jsapiTicketLock;
    }

    @Override
    public boolean isJsapiTicketExpired() {
      return wxOpenConfigStorage.isJsapiTicketExpired(appId);
    }

    @Override
    public synchronized void updateJsapiTicket(String jsapiTicket, int expiresInSeconds) {
      wxOpenConfigStorage.updateJsapiTicket(appId, jsapiTicket, expiresInSeconds);
    }

    @Override
    public void expireJsapiTicket() {
      wxOpenConfigStorage.expireJsapiTicket(appId);
    }

    @Override
    public String getCardApiTicket() {
      return wxOpenConfigStorage.getCardApiTicket(appId);
    }

    @Override
    public Lock getCardApiTicketLock() {
      return this.cardApiTicketLock;
    }

    @Override
    public boolean isCardApiTicketExpired() {
      return wxOpenConfigStorage.isCardApiTicketExpired(appId);
    }

    @Override
    public synchronized void updateCardApiTicket(String cardApiTicket, int expiresInSeconds) {
      wxOpenConfigStorage.updateCardApiTicket(appId, cardApiTicket, expiresInSeconds);
    }

    @Override
    public void expireCardApiTicket() {
      wxOpenConfigStorage.expireCardApiTicket(appId);
    }

    @Override
    public String getAppId() {
      return this.appId;
    }

    @Override
    public String getSecret() {
      return null;
    }

    @Override
    public String getToken() {
      return wxOpenConfigStorage.getComponentToken();
    }

    @Override
    public String getTemplateId() {
      return null;
    }

    @Override
    public long getExpiresTime() {
      return 0;
    }

    @Override
    public String getAesKey() {
      return wxOpenConfigStorage.getComponentAesKey();
    }

    @Override
    public String getApiSignatureRsaPrivateKey() {
      return wxOpenConfigStorage.getComponentApiSignatureRsaPrivateKey();
    }

    @Override
    public String getApiSignatureAesKey() {
      return wxOpenConfigStorage.getComponentApiSignatureAesKey();
    }

    public String getApiSignatureRsaPrivateKeySn() {
      return wxOpenConfigStorage.getComponentApiSignatureRsaPrivateKeySn();
    }

    @Override
    public String getApiSignatureAesKeySn() {
      return wxOpenConfigStorage.getComponentApiSignatureAesKeySn();
    }

    @Override
    public String getWechatMpAppid() {
      return wxOpenConfigStorage.getComponentAppId();
    }

    @Override
    public String getMsgDataFormat() {
      return null;
    }

    @Deprecated
    @Override
    public String getOauth2redirectUri() {
      return null;
    }

    @Override
    public String getOauth2RedirectUrl() {
      return null;
    }

    @Override
    public String getQrConnectRedirectUrl() {
      return null;
    }

    @Override
    public String getHttpProxyHost() {
      return this.wxOpenConfigStorage.getHttpProxyHost();
    }

    @Override
    public int getHttpProxyPort() {
      return this.wxOpenConfigStorage.getHttpProxyPort();
    }

    @Override
    public String getHttpProxyUsername() {
      return this.wxOpenConfigStorage.getHttpProxyUsername();
    }

    @Override
    public String getHttpProxyPassword() {
      return this.wxOpenConfigStorage.getHttpProxyPassword();
    }

    @Override
    public int getRetrySleepMillis() {
      return this.wxOpenConfigStorage.getRetrySleepMillis();
    }

    @Override
    public int getMaxRetryTimes() {
      return this.wxOpenConfigStorage.getMaxRetryTimes();
    }

    @Override
    public String toString() {
      return WxOpenGsonBuilder.create().toJson(this);
    }

    @Override
    public File getTmpDirFile() {
      return null;
    }

    @Override
    public ApacheHttpClientBuilder getApacheHttpClientBuilder() {
      return wxOpenConfigStorage.getApacheHttpClientBuilder();
    }

    @Override
    public boolean autoRefreshToken() {
      return wxOpenConfigStorage.autoRefreshToken();
    }

    @Override
    public WxMpHostConfig getHostConfig() {
      return this.hostConfig;
    }

    @Override
    public void setHostConfig(WxMpHostConfig hostConfig) {
      this.hostConfig = hostConfig;
    }
  }
}
