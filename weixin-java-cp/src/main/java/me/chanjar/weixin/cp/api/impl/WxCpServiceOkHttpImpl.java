package me.chanjar.weixin.cp.api.impl;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.HttpClientType;
import me.chanjar.weixin.common.util.http.okhttp.DefaultOkHttpClientBuilder;
import me.chanjar.weixin.common.util.http.okhttp.OkHttpProxyInfo;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.locks.Lock;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.GET_TOKEN;

/**
 * The type Wx cp service ok http.
 *
 * @author someone
 */
@Slf4j
public class WxCpServiceOkHttpImpl extends BaseWxCpServiceImpl<OkHttpClient, OkHttpProxyInfo> {
  private OkHttpClient httpClient;
  private OkHttpProxyInfo httpProxy;

  @Override
  public OkHttpClient getRequestHttpClient() {
    return httpClient;
  }

  @Override
  public OkHttpProxyInfo getRequestHttpProxy() {
    return httpProxy;
  }

  @Override
  public HttpClientType getRequestType() {
    return HttpClientType.OK_HTTP;
  }

  @Override
  public String getAccessToken(boolean forceRefresh) throws WxErrorException {
    if (!this.configStorage.isAccessTokenExpired() && !forceRefresh) {
      return this.configStorage.getAccessToken();
    }

    synchronized (this.globalAccessTokenRefreshLock) {
      //得到httpClient
      OkHttpClient client = getRequestHttpClient();
      //请求的request
      Request request = new Request.Builder()
        .url(String.format(this.configStorage.getApiUrl(GET_TOKEN), this.configStorage.getCorpId(),
          this.configStorage.getCorpSecret()))
        .get()
        .build();
      String resultContent = null;
      try {
        Response response = client.newCall(request).execute();
        resultContent = response.body().string();
      } catch (IOException e) {
        log.error(e.getMessage(), e);
      }

      WxError error = WxError.fromJson(resultContent, WxType.CP);
      if (error.getErrorCode() != 0) {
        throw new WxErrorException(error);
      }
      WxAccessToken accessToken = WxAccessToken.fromJson(resultContent);
      this.configStorage.updateAccessToken(accessToken.getAccessToken(),
        accessToken.getExpiresIn());
    }
    return this.configStorage.getAccessToken();
  }

  @Override
  public String getMsgAuditAccessToken(boolean forceRefresh) throws WxErrorException {
    if (!this.configStorage.isMsgAuditAccessTokenExpired() && !forceRefresh) {
      return this.configStorage.getMsgAuditAccessToken();
    }

    Lock lock = this.configStorage.getMsgAuditAccessTokenLock();
    lock.lock();
    try {
      // 拿到锁之后，再次判断一下最新的token是否过期，避免重刷
      if (!this.configStorage.isMsgAuditAccessTokenExpired() && !forceRefresh) {
        return this.configStorage.getMsgAuditAccessToken();
      }
      // 使用会话存档secret获取access_token
      String msgAuditSecret = this.configStorage.getMsgAuditSecret();
      if (msgAuditSecret == null || msgAuditSecret.trim().isEmpty()) {
        throw new WxErrorException("会话存档secret未配置");
      }
      //得到httpClient
      OkHttpClient client = getRequestHttpClient();
      //请求的request
      Request request = new Request.Builder()
        .url(String.format(this.configStorage.getApiUrl(GET_TOKEN), this.configStorage.getCorpId(),
          msgAuditSecret))
        .get()
        .build();
      String resultContent = null;
      try (Response response = client.newCall(request).execute()) {
        resultContent = response.body().string();
      } catch (IOException e) {
        log.error(e.getMessage(), e);
      }

      WxError error = WxError.fromJson(resultContent, WxType.CP);
      if (error.getErrorCode() != 0) {
        throw new WxErrorException(error);
      }
      WxAccessToken accessToken = WxAccessToken.fromJson(resultContent);
      this.configStorage.updateMsgAuditAccessToken(accessToken.getAccessToken(),
        accessToken.getExpiresIn());
    } finally {
      lock.unlock();
    }
    return this.configStorage.getMsgAuditAccessToken();
  }

  @Override
  public void initHttp() {
    log.debug("WxCpServiceOkHttpImpl initHttp");
    //设置代理
    if (configStorage.getHttpProxyHost() != null && configStorage.getHttpProxyPort() > 0) {
      httpProxy = OkHttpProxyInfo.httpProxy(configStorage.getHttpProxyHost(),
        configStorage.getHttpProxyPort(),
        configStorage.getHttpProxyUsername(),
        configStorage.getHttpProxyPassword());
      OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
      clientBuilder.proxy(getRequestHttpProxy().getProxy());
      //设置授权
      clientBuilder.proxyAuthenticator(new Authenticator() {
        @Override
        public Request authenticate(Route route, Response response) throws IOException {
          String credential = Credentials.basic(httpProxy.getProxyUsername(), httpProxy.getProxyPassword());
          return response.request().newBuilder()
            .header("Proxy-Authorization", credential)
            .build();
        }
      });
      httpClient = clientBuilder.build();
    } else {
      httpClient = DefaultOkHttpClientBuilder.get().build();
    }
  }

  @Override
  public WxCpConfigStorage getWxCpConfigStorage() {
    return this.configStorage;
  }
}
