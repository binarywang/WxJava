package me.chanjar.weixin.mp.config.impl;

import lombok.Data;
import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.enums.TicketType;
import me.chanjar.weixin.common.util.http.apache.ApacheHttpClientBuilder;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.config.WxMpHostConfig;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.File;
import java.io.Serializable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 基于内存的微信配置provider，在实际生产环境中应该将这些配置持久化.
 *
 * @author chanjarster
 */
@Data
public class WxMpDefaultConfigImpl implements WxMpConfigStorage, Serializable {
  private static final long serialVersionUID = -6646519023303395185L;

  protected volatile String appId;
  protected volatile String secret;
  /**
   * 是否使用稳定版 Access Token
   */
  private boolean useStableAccessToken;
  protected volatile String token;
  protected volatile String templateId;
  protected volatile String accessToken;
  protected volatile String aesKey;
  protected volatile long expiresTime;

  @Deprecated
  protected volatile String oauth2redirectUri;
  protected volatile String oauth2RedirectUrl;
  protected volatile String qrConnectRedirectUrl;

  protected volatile String httpProxyHost;
  protected volatile int httpProxyPort;
  protected volatile String httpProxyUsername;
  protected volatile String httpProxyPassword;

  protected volatile int retrySleepMillis = 1000;
  protected volatile int maxRetryTimes = 5;

  protected volatile String jsapiTicket;
  protected volatile long jsapiTicketExpiresTime;

  protected volatile String sdkTicket;
  protected volatile long sdkTicketExpiresTime;

  protected volatile String cardApiTicket;
  protected volatile long cardApiTicketExpiresTime;

  protected volatile Lock accessTokenLock = new ReentrantLock();
  protected volatile Lock jsapiTicketLock = new ReentrantLock();
  protected volatile Lock sdkTicketLock = new ReentrantLock();
  protected volatile Lock cardApiTicketLock = new ReentrantLock();

  protected volatile File tmpDirFile;

  protected volatile ApacheHttpClientBuilder apacheHttpClientBuilder;

  private WxMpHostConfig hostConfig = null;

  @Override
  public boolean isStableAccessToken() {
    return this.useStableAccessToken;
  }

  @Override
  public void useStableAccessToken(boolean useStableAccessToken) {
    this.useStableAccessToken = useStableAccessToken;
  }

  @Override
  public boolean isAccessTokenExpired() {
    return System.currentTimeMillis() > this.expiresTime;
  }

  @Override
  public synchronized void updateAccessToken(WxAccessToken accessToken) {
    updateAccessToken(accessToken.getAccessToken(), accessToken.getExpiresIn());
  }

  @Override
  public synchronized void updateAccessToken(String accessToken, int expiresInSeconds) {
    this.accessToken = accessToken;
    this.expiresTime = System.currentTimeMillis() + (expiresInSeconds - 200) * 1000L;
  }

  @Override
  public void expireAccessToken() {
    this.expiresTime = 0;
  }

  @Override
  public String getTicket(TicketType type) {
    switch (type) {
      case SDK:
        return this.sdkTicket;
      case JSAPI:
        return this.jsapiTicket;
      case WX_CARD:
        return this.cardApiTicket;
      default:
        return null;
    }
  }

  public void setTicket(TicketType type, String ticket) {
    switch (type) {
      case JSAPI:
        this.jsapiTicket = ticket;
        break;
      case WX_CARD:
        this.cardApiTicket = ticket;
        break;
      case SDK:
        this.sdkTicket = ticket;
        break;
      default:
    }
  }

  @Override
  public Lock getTicketLock(TicketType type) {
    switch (type) {
      case SDK:
        return this.sdkTicketLock;
      case JSAPI:
        return this.jsapiTicketLock;
      case WX_CARD:
        return this.cardApiTicketLock;
      default:
        return null;
    }
  }

  @Override
  public boolean isTicketExpired(TicketType type) {
    switch (type) {
      case SDK:
        return System.currentTimeMillis() > this.sdkTicketExpiresTime;
      case JSAPI:
        return System.currentTimeMillis() > this.jsapiTicketExpiresTime;
      case WX_CARD:
        return System.currentTimeMillis() > this.cardApiTicketExpiresTime;
      default:
        return false;
    }
  }

  @Override
  public synchronized void updateTicket(TicketType type, String ticket, int expiresInSeconds) {
    switch (type) {
      case JSAPI:
        this.jsapiTicket = ticket;
        // 预留200秒的时间
        this.jsapiTicketExpiresTime = System.currentTimeMillis() + (expiresInSeconds - 200) * 1000L;
        break;
      case WX_CARD:
        this.cardApiTicket = ticket;
        // 预留200秒的时间
        this.cardApiTicketExpiresTime = System.currentTimeMillis() + (expiresInSeconds - 200) * 1000L;
        break;
      case SDK:
        this.sdkTicket = ticket;
        // 预留200秒的时间
        this.sdkTicketExpiresTime = System.currentTimeMillis() + (expiresInSeconds - 200) * 1000L;
        break;
      default:
    }
  }

  @Override
  public void expireTicket(TicketType type) {
    switch (type) {
      case JSAPI:
        this.jsapiTicketExpiresTime = 0;
        break;
      case WX_CARD:
        this.cardApiTicketExpiresTime = 0;
        break;
      case SDK:
        this.sdkTicketExpiresTime = 0;
        break;
      default:
    }
  }

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }

  @Override
  public boolean autoRefreshToken() {
    return true;
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
