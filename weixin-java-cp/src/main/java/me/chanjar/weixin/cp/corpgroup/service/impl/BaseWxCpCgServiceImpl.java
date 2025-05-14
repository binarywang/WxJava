package me.chanjar.weixin.cp.corpgroup.service.impl;

import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxCpErrorMsgEnum;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.error.WxRuntimeException;
import me.chanjar.weixin.common.util.DataUtils;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.SimpleGetRequestExecutor;
import me.chanjar.weixin.common.util.http.SimplePostRequestExecutor;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.corpgroup.WxCpCorpGroupCorpGetTokenReq;
import me.chanjar.weixin.cp.bean.corpgroup.WxCpMaTransferSession;
import me.chanjar.weixin.cp.config.WxCpCorpGroupConfigStorage;
import me.chanjar.weixin.cp.corpgroup.service.WxCpCgService;
import me.chanjar.weixin.cp.corpgroup.service.WxCpLinkedCorpService;

import java.io.IOException;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.CorpGroup.*;

/**
 * @author libo Email: 422423229@qq.com
 * @since  1/3/2023 5:45 PM
 */
@Slf4j
public abstract class BaseWxCpCgServiceImpl<H, P> implements WxCpCgService, RequestHttp<H, P> {

  WxCpService wxCpService;
  /**
   * The Config storage.
   */
  protected WxCpCorpGroupConfigStorage configStorage;

  private int retrySleepMillis = 1000;
  private int maxRetryTimes = 5;

  private final WxCpLinkedCorpService linkedCorpService = new WxCpLinkedCorpServiceImpl(this);

  @Override
  public void updateCorpAccessToken(String corpId, Integer agentId, String corpAccessToken, int expiresInSeconds) {

  }

  @Override
  public String getCorpAccessToken(String corpId, Integer agentId, Integer businessType) throws WxErrorException {
    return getCorpAccessToken(corpId, agentId, businessType, false);
  }

  @Override
  public String getCorpAccessToken(String corpId, Integer agentId, Integer businessType, boolean forceRefresh) throws WxErrorException {
    if (!this.configStorage.isCorpAccessTokenExpired(corpId, agentId) && !forceRefresh) {
      return this.configStorage.getCorpAccessToken(corpId, agentId);
    }
    synchronized (this) {
      JsonObject jsonObject = new JsonObject();
      jsonObject.addProperty("corpid", corpId);
      jsonObject.addProperty("agentid", agentId);
      jsonObject.addProperty("business_type", businessType);
      final String url = this.wxCpService.getWxCpConfigStorage().getApiUrl(CORP_GET_TOKEN);
      String responseContent = this.wxCpService.post(url, jsonObject);
      WxAccessToken corpToken = WxAccessToken.fromJson(responseContent);
      this.configStorage.updateCorpAccessToken(corpId, agentId, corpToken.getAccessToken(), corpToken.getExpiresIn());
    }
    return this.configStorage.getCorpAccessToken(corpId, agentId);
  }

  @Override
  public WxAccessToken getCorpAccessTokenEntity(String corpId, Integer agentId, Integer businessType) throws WxErrorException {
    return this.getCorpAccessTokenEntity(corpId, agentId, businessType, false);
  }


  @Override
  public WxAccessToken getCorpAccessTokenEntity(String corpId, Integer agentId, Integer businessType, boolean forceRefresh) throws WxErrorException {
    return this.configStorage.getCorpAccessTokenEntity(corpId, agentId);
  }

  @Override
  public boolean isCorpAccessTokenExpired(String corpId, Integer agentId) {
    return this.configStorage.isCorpAccessTokenExpired(corpId, agentId);
  }

  @Override
  public void expireCorpAccessToken(String corpId, Integer agentId) {
    this.configStorage.expireCorpAccessToken(corpId, agentId);
  }

  @Override
  public String get(String url, String queryParam, WxCpCorpGroupCorpGetTokenReq req) throws WxErrorException {
    return execute(SimpleGetRequestExecutor.create(this), url, queryParam, req);
  }

  @Override
  public String get(String url, String queryParam, boolean withoutCorpAccessToken, WxCpCorpGroupCorpGetTokenReq req) throws WxErrorException {
    return execute(SimpleGetRequestExecutor.create(this), url, queryParam, withoutCorpAccessToken, req);
  }

  @Override
  public String post(String url, String postData, WxCpCorpGroupCorpGetTokenReq req) throws WxErrorException {
    return execute(SimplePostRequestExecutor.create(this), url, postData, false, req);
  }

  /**
   * Post string.
   *
   * @param url                    the url
   * @param postData               the post data
   * @param withoutCorpAccessToken the without Corp access token
   * @return the string
   * @throws WxErrorException the wx error exception
   */
  public String post(String url, String postData, boolean withoutCorpAccessToken, WxCpCorpGroupCorpGetTokenReq req) throws WxErrorException {
    return execute(SimplePostRequestExecutor.create(this), url, postData, withoutCorpAccessToken, req);
  }

  /**
   * 向微信端发送请求，在这里执行的策略是当发生access_token过期时才去刷新，然后重新执行请求，而不是全局定时请求.
   */
  @Override
  public <T, E> T execute(RequestExecutor<T, E> executor, String uri, E data, WxCpCorpGroupCorpGetTokenReq req) throws WxErrorException {
    return execute(executor, uri, data, false, req);
  }

  /**
   * Execute t.
   *
   * @param <T>                    the type parameter
   * @param <E>                    the type parameter
   * @param executor               the executor
   * @param uri                    the uri
   * @param data                   the data
   * @param withoutCorpAccessToken the without Corp access token
   * @return the t
   * @throws WxErrorException the wx error exception
   */
  public <T, E> T execute(RequestExecutor<T, E> executor, String uri, E data, boolean withoutCorpAccessToken, WxCpCorpGroupCorpGetTokenReq req) throws WxErrorException {
    int retryTimes = 0;
    do {
      try {
        return this.executeInternal(executor, uri, data, withoutCorpAccessToken, req);
      } catch (WxErrorException e) {
        if (retryTimes + 1 > this.maxRetryTimes) {
          log.warn("重试达到最大次数【{}】", this.maxRetryTimes);
          //最后一次重试失败后，直接抛出异常，不再等待
          throw new WxRuntimeException("微信服务端异常，超出重试次数");
        }

        WxError error = e.getError();
        /*
         * -1 系统繁忙, 1000ms后重试
         */
        if (error.getErrorCode() == -1) {
          int sleepMillis = this.retrySleepMillis * (1 << retryTimes);
          try {
            log.debug("微信系统繁忙，{} ms 后重试(第{}次)", sleepMillis, retryTimes + 1);
            Thread.sleep(sleepMillis);
          } catch (InterruptedException e1) {
            Thread.currentThread().interrupt();
          }
        } else {
          throw e;
        }
      }
    } while (retryTimes++ < this.maxRetryTimes);

    log.warn("重试达到最大次数【{}】", this.maxRetryTimes);
    throw new WxRuntimeException("微信服务端异常，超出重试次数");
  }

  /**
   * Execute internal t.
   *
   * @param <T>      the type parameter
   * @param <E>      the type parameter
   * @param executor the executor
   * @param uri      the uri
   * @param data     the data
   * @return the t
   * @throws WxErrorException the wx error exception
   */
  protected <T, E> T executeInternal(RequestExecutor<T, E> executor, String uri, E data, WxCpCorpGroupCorpGetTokenReq req) throws WxErrorException {
    return executeInternal(executor, uri, data, false, req);
  }

  /**
   * Execute internal t.
   *
   * @param <T>                    the type parameter
   * @param <E>                    the type parameter
   * @param executor               the executor
   * @param uri                    the uri
   * @param data                   the data
   * @param withoutCorpAccessToken the without Corp access token
   * @return the t
   * @throws WxErrorException the wx error exception
   */
  protected <T, E> T executeInternal(RequestExecutor<T, E> executor, String uri, E data,
                                     boolean withoutCorpAccessToken, WxCpCorpGroupCorpGetTokenReq req) throws WxErrorException {
    E dataForLog = DataUtils.handleDataWithSecret(data);

    if (uri.contains("access_token=")) {
      throw new IllegalArgumentException("uri参数中不允许有access_token: " + uri);
    }
    String uriWithAccessToken;
    if (!withoutCorpAccessToken) {
      String corpAccessToken = getCorpAccessToken(req.getCorpId(), req.getAgentId(), req.getBusinessType());
      uriWithAccessToken = uri + (uri.contains("?") ? "&" : "?") + "access_token=" + corpAccessToken;
    } else {
      uriWithAccessToken = uri;
    }


    try {
      T result = executor.execute(uriWithAccessToken, data, WxType.CP);
      log.debug("\n【请求地址】: {}\n【请求参数】：{}\n【响应数据】：{}", uriWithAccessToken, dataForLog, result);
      return result;
    } catch (WxErrorException e) {
      WxError error = e.getError();
      /*
       * 发生以下情况时尝试刷新Corp_access_token
       * 42009 Corp_access_token已过期
       */
      if (error.getErrorCode() == WxCpErrorMsgEnum.CODE_42009.getCode()) {
        // 强制设置wxCpCorpGroupConfigStorage它的corp access token过期了，这样在下一次请求里就会刷新corp access token
        this.configStorage.expireCorpAccessToken(req.getCorpId(), req.getAgentId());
        if (this.getWxCpCorpGroupConfigStorage().autoRefreshToken()) {
          log.warn("即将重新获取新的access_token，错误代码：{}，错误信息：{}", error.getErrorCode(), error.getErrorMsg());
          return this.execute(executor, uri, data, req);
        }
      }

      if (error.getErrorCode() != 0) {
        log.error("\n【请求地址】: {}\n【请求参数】：{}\n【错误信息】：{}", uriWithAccessToken, dataForLog, error);
        throw new WxErrorException(error, e);
      }
      return null;
    } catch (IOException e) {
      log.error("\n【请求地址】: {}\n【请求参数】：{}\n【异常信息】：{}", uriWithAccessToken, dataForLog, e.getMessage());
      throw new WxRuntimeException(e);
    }
  }

  @Override
  public void setWxCpCorpGroupConfigStorage(WxCpCorpGroupConfigStorage wxCpCorpGroupConfigStorage) {
    this.configStorage = wxCpCorpGroupConfigStorage;
    this.initHttp();
  }

  @Override
  public WxCpCorpGroupConfigStorage getWxCpCorpGroupConfigStorage() {
    return configStorage;
  }

  @Override
  public void setRetrySleepMillis(int retrySleepMillis) {
    this.retrySleepMillis = retrySleepMillis;
  }


  @Override
  public void setMaxRetryTimes(int maxRetryTimes) {
    this.maxRetryTimes = maxRetryTimes;
  }

  @Override
  public RequestHttp<?, ?> getRequestHttp() {
    return this;
  }

  @Override
  public void setWxCpService(WxCpService wxCpService) {
    this.wxCpService = wxCpService;
  }

  @Override
  public WxCpLinkedCorpService getLinkedCorpService() {
    return linkedCorpService;
  }

  @Override
  public WxCpMaTransferSession getCorpTransferSession(String userId, String sessionKey, WxCpCorpGroupCorpGetTokenReq req) throws WxErrorException {
    final String url = this.wxCpService.getWxCpConfigStorage().getApiUrl(MA_TRANSFER_SESSION);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("userid", userId);
    jsonObject.addProperty("session_key", sessionKey);
    String result = this.post(url, jsonObject.toString(), req);
    return WxCpMaTransferSession.fromJson(result);
  }
}
