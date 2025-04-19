package me.chanjar.weixin.cp.tp.service.impl;

import com.google.common.base.Joiner;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxCpErrorMsgEnum;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.error.WxRuntimeException;
import me.chanjar.weixin.common.session.StandardSessionManager;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.common.util.DataUtils;
import me.chanjar.weixin.common.util.RandomUtils;
import me.chanjar.weixin.common.util.crypto.SHA1;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.SimpleGetRequestExecutor;
import me.chanjar.weixin.common.util.http.SimplePostRequestExecutor;
import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import me.chanjar.weixin.cp.bean.*;
import me.chanjar.weixin.cp.config.WxCpTpConfigStorage;
import me.chanjar.weixin.cp.tp.service.*;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.Tp.*;

/**
 * .
 *
 * @param <H> the type parameter
 * @param <P> the type parameter
 * @author zhenjun cai
 */
@Slf4j
public abstract class BaseWxCpTpServiceImpl<H, P> implements WxCpTpService, RequestHttp<H, P> {

  private WxCpTpContactService wxCpTpContactService = new WxCpTpContactServiceImpl(this);
  private WxCpTpDepartmentService wxCpTpDepartmentService = new WxCpTpDepartmentServiceImpl(this);
  private WxCpTpMediaService wxCpTpMediaService = new WxCpTpMediaServiceImpl(this);
  private WxCpTpOAService wxCpTpOAService = new WxCpTpOAServiceImpl(this);
  private WxCpTpUserService wxCpTpUserService = new WxCpTpUserServiceImpl(this);
  private WxCpTpOrderService wxCpTpOrderService = new WxCpTpOrderServiceImpl(this);
  private WxCpTpEditionService wxCpTpEditionService = new WxCpTpEditionServiceImpl(this);
  private WxCpTpLicenseService wxCpTpLicenseService = new WxCpTpLicenseServiceImpl(this);
  private WxCpTpIdConvertService wxCpTpIdConvertService = new WxCpTpIdConvertServiceImpl(this);
  private WxCpTpOAuth2Service wxCpTpOAuth2Service = new WxCpTpOAuth2ServiceImpl(this);
  /**
   * 全局的是否正在刷新access token的锁.
   */
  protected final Object globalSuiteAccessTokenRefreshLock = new Object();


  /**
   * 全局刷新suite ticket的锁
   */
  protected final Object globalSuiteTicketRefreshLock = new Object();

  /**
   * 全局的是否正在刷新jsapi_ticket的锁.
   */
  protected final Object globalJsApiTicketRefreshLock = new Object();

  /**
   * 全局的是否正在刷新auth_corp_jsapi_ticket的锁.
   */
  protected final Object globalAuthCorpJsApiTicketRefreshLock = new Object();

  /**
   * The Global provider token refresh lock.
   */
  protected final Object globalProviderTokenRefreshLock = new Object();

  /**
   * The Config storage.
   */
  protected WxCpTpConfigStorage configStorage;

  private final WxSessionManager sessionManager = new StandardSessionManager();

  /**
   * 临时文件目录.
   */
  private File tmpDirFile;
  private int retrySleepMillis = 1000;
  private int maxRetryTimes = 5;

  @Override
  public boolean checkSignature(String msgSignature, String timestamp, String nonce, String data) {
    try {
      return SHA1.gen(this.configStorage.getToken(), timestamp, nonce, data)
        .equals(msgSignature);
    } catch (Exception e) {
      log.error("Checking signature failed, and the reason is :" + e.getMessage());
      return false;
    }
  }

  @Override
  public String getSuiteAccessToken() throws WxErrorException {
    return getSuiteAccessToken(false);
  }

  @Override
  public WxAccessToken getSuiteAccessTokenEntity() throws WxErrorException {
    return this.getSuiteAccessTokenEntity(false);
  }

  @Override
  public WxAccessToken getSuiteAccessTokenEntity(boolean forceRefresh) throws WxErrorException {
    getSuiteAccessToken(forceRefresh);
    return this.configStorage.getSuiteAccessTokenEntity();
  }

  @Override
  public String getSuiteTicket() throws WxErrorException {
    if (this.configStorage.isSuiteTicketExpired()) {
      // 本地suite ticket 不存在或者过期
      WxError wxError = WxError.fromJson("{\"errcode\":40085, \"errmsg\":\"invalid suite ticket\"}", WxType.CP);
      throw new WxErrorException(wxError);
    }
    return this.configStorage.getSuiteTicket();
  }

  @Override
  public String getSuiteTicket(boolean forceRefresh) throws WxErrorException {
//     suite ticket由微信服务器推送，不能强制刷新
//    if (forceRefresh) {
//      this.configStorage.expireSuiteTicket();
//    }
    return getSuiteTicket();
  }

  @Override
  public void setSuiteTicket(String suiteTicket) {
    setSuiteTicket(suiteTicket, 28 * 60);
  }

  @Override
  public void setSuiteTicket(String suiteTicket, int expiresInSeconds) {
    synchronized (globalSuiteTicketRefreshLock) {
      this.configStorage.updateSuiteTicket(suiteTicket, expiresInSeconds);
    }
  }

  @Override
  public String getSuiteJsApiTicket(String authCorpId) throws WxErrorException {
    if (this.configStorage.isAuthSuiteJsApiTicketExpired(authCorpId)) {

      String resp = get(configStorage.getApiUrl(GET_SUITE_JSAPI_TICKET),
        "type=agent_config&access_token=" + this.configStorage.getAccessToken(authCorpId), true);

      JsonObject jsonObject = GsonParser.parse(resp);
      if (jsonObject.get(WxConsts.ERR_CODE).getAsInt() == 0) {
        String jsApiTicket = jsonObject.get("ticket").getAsString();
        int expiredInSeconds = jsonObject.get("expires_in").getAsInt();
        synchronized (globalJsApiTicketRefreshLock) {
          configStorage.updateAuthSuiteJsApiTicket(authCorpId, jsApiTicket, expiredInSeconds);
        }
      } else {
        throw new WxErrorException(WxError.fromJson(resp));
      }
    }

    return configStorage.getAuthSuiteJsApiTicket(authCorpId);
  }

  @Override
  public String getSuiteJsApiTicket(String authCorpId, boolean forceRefresh) throws WxErrorException {
    if (forceRefresh) {
      this.configStorage.expireAuthSuiteJsApiTicket(authCorpId);
    }
    return this.getSuiteJsApiTicket(authCorpId);
  }

  @Override
  public String getAuthCorpJsApiTicket(String authCorpId) throws WxErrorException {
    if (this.configStorage.isAuthCorpJsApiTicketExpired(authCorpId)) {

      String resp = get(configStorage.getApiUrl(GET_AUTH_CORP_JSAPI_TICKET),
        "access_token=" + this.configStorage.getAccessToken(authCorpId), true);

      JsonObject jsonObject = GsonParser.parse(resp);
      if (jsonObject.get(WxConsts.ERR_CODE).getAsInt() == 0) {
        String jsApiTicket = jsonObject.get("ticket").getAsString();
        int expiredInSeconds = jsonObject.get("expires_in").getAsInt();

        synchronized (globalAuthCorpJsApiTicketRefreshLock) {
          configStorage.updateAuthCorpJsApiTicket(authCorpId, jsApiTicket, expiredInSeconds);
        }
      } else {
        throw new WxErrorException(WxError.fromJson(resp));
      }
    }
    return configStorage.getAuthCorpJsApiTicket(authCorpId);
  }

  @Override
  public String getAuthCorpJsApiTicket(String authCorpId, boolean forceRefresh) throws WxErrorException {
    if (forceRefresh) {
      this.configStorage.expireAuthCorpJsApiTicket(authCorpId);
    }
    return this.getAuthCorpJsApiTicket(authCorpId);
  }

  @Override
  public WxCpMaJsCode2SessionResult jsCode2Session(String jsCode) throws WxErrorException {
    Map<String, String> params = new HashMap<>(2);
    params.put("js_code", jsCode);
    params.put("grant_type", "authorization_code");

    final String url = configStorage.getApiUrl(JSCODE_TO_SESSION);
    return WxCpMaJsCode2SessionResult.fromJson(this.get(url, Joiner.on("&").withKeyValueSeparator("=").join(params)));
  }


  @Override
  public WxAccessToken getCorpToken(String authCorpId, String permanentCode) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("auth_corpid", authCorpId);
    jsonObject.addProperty("permanent_code", permanentCode);
    String result = post(configStorage.getApiUrl(GET_CORP_TOKEN), jsonObject.toString());

    return WxAccessToken.fromJson(result);
  }

  @Override
  public WxAccessToken getCorpToken(String authCorpId, String permanentCode, boolean forceRefresh)
    throws WxErrorException {
    if (this.configStorage.isAccessTokenExpired(authCorpId) || forceRefresh) {
      WxAccessToken corpToken = this.getCorpToken(authCorpId, permanentCode);
      this.configStorage.updateAccessToken(authCorpId, corpToken.getAccessToken(), corpToken.getExpiresIn());
    }
    return this.configStorage.getAccessTokenEntity(authCorpId);
  }

  @Override
  public WxCpTpCorp getPermanentCode(String authCode) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("auth_code", authCode);

    String result = post(configStorage.getApiUrl(GET_PERMANENT_CODE), jsonObject.toString());
    jsonObject = GsonParser.parse(result);
    WxCpTpCorp wxCpTpCorp = WxCpTpCorp.fromJson(jsonObject.get("auth_corp_info").getAsJsonObject().toString());
    wxCpTpCorp.setPermanentCode(jsonObject.get("permanent_code").getAsString());
    return wxCpTpCorp;
  }

  @Override
  public WxCpTpPermanentCodeInfo getPermanentCodeInfo(String authCode) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("auth_code", authCode);
    String result = post(configStorage.getApiUrl(GET_PERMANENT_CODE), jsonObject.toString());
    return WxCpTpPermanentCodeInfo.fromJson(result);
  }

  @Override
  @SneakyThrows
  public String getPreAuthUrl(String redirectUri, String state) throws WxErrorException {
    String result = get(configStorage.getApiUrl(GET_PREAUTH_CODE), null);
    WxCpTpPreauthCode preAuthCode = WxCpTpPreauthCode.fromJson(result);
    String preAuthUrl = "https://open.work.weixin.qq.com/3rdapp/install?suite_id=" + configStorage.getSuiteId() +
      "&pre_auth_code=" + preAuthCode.getPreAuthCode() + "&redirect_uri=" + URLEncoder.encode(redirectUri, "utf-8");
    if (StringUtils.isNotBlank(state)) {
      preAuthUrl += "&state=" + state;
    }
    return preAuthUrl;
  }

  @Override
  @SneakyThrows
  public String getPreAuthUrl(String redirectUri, String state, int authType) throws WxErrorException {
    String result = get(configStorage.getApiUrl(GET_PREAUTH_CODE), null);
    WxCpTpPreauthCode preAuthCode = WxCpTpPreauthCode.fromJson(result);
    String setSessionUrl = "https://qyapi.weixin.qq.com/cgi-bin/service/set_session_info";

    Map<String, Object> sessionInfo = new HashMap<>(1);
    sessionInfo.put("auth_type", authType);
    Map<String, Object> param = new HashMap<>(2);
    param.put("pre_auth_code", preAuthCode.getPreAuthCode());
    param.put("session_info", sessionInfo);
    String postData = new Gson().toJson(param);

    post(setSessionUrl, postData);

    String preAuthUrl = "https://open.work.weixin.qq.com/3rdapp/install?suite_id=" + configStorage.getSuiteId() +
      "&pre_auth_code=" + preAuthCode.getPreAuthCode() + "&redirect_uri=" + URLEncoder.encode(redirectUri, "utf-8");
    if (StringUtils.isNotBlank(state)) {
      preAuthUrl += "&state=" + state;
    }
    return preAuthUrl;
  }

  @Override
  public WxCpTpAuthInfo getAuthInfo(String authCorpId, String permanentCode) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("auth_corpid", authCorpId);
    jsonObject.addProperty("permanent_code", permanentCode);
    String result = post(configStorage.getApiUrl(GET_AUTH_INFO), jsonObject.toString());
    return WxCpTpAuthInfo.fromJson(result);
  }

  @Override
  public String get(String url, String queryParam) throws WxErrorException {
    return execute(SimpleGetRequestExecutor.create(this), url, queryParam);
  }

  @Override
  public String get(String url, String queryParam, boolean withoutSuiteAccessToken) throws WxErrorException {
    return execute(SimpleGetRequestExecutor.create(this), url, queryParam, withoutSuiteAccessToken);
  }

  @Override
  public String post(String url, String postData) throws WxErrorException {
    return execute(SimplePostRequestExecutor.create(this), url, postData, false);
  }

  /**
   * Post string.
   *
   * @param url                     the url
   * @param postData                the post data
   * @param withoutSuiteAccessToken the without suite access token
   * @return the string
   * @throws WxErrorException the wx error exception
   */
  @Override
  public String post(String url, String postData, boolean withoutSuiteAccessToken) throws WxErrorException {
    return execute(SimplePostRequestExecutor.create(this), url, postData, withoutSuiteAccessToken);
  }

  /**
   * 向微信端发送请求，在这里执行的策略是当发生access_token过期时才去刷新，然后重新执行请求，而不是全局定时请求.
   */
  @Override
  public <T, E> T execute(RequestExecutor<T, E> executor, String uri, E data) throws WxErrorException {
    return execute(executor, uri, data, false);
  }

  /**
   * Execute t.
   *
   * @param <T>                     the type parameter
   * @param <E>                     the type parameter
   * @param executor                the executor
   * @param uri                     the uri
   * @param data                    the data
   * @param withoutSuiteAccessToken the without suite access token
   * @return the t
   * @throws WxErrorException the wx error exception
   */
  public <T, E> T execute(RequestExecutor<T, E> executor, String uri, E data, boolean withoutSuiteAccessToken) throws WxErrorException {
    int retryTimes = 0;
    do {
      try {
        return this.executeInternal(executor, uri, data, withoutSuiteAccessToken);
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
  protected <T, E> T executeInternal(RequestExecutor<T, E> executor, String uri, E data) throws WxErrorException {
    return executeInternal(executor, uri, data, false);
  }

  /**
   * Execute internal t.
   *
   * @param <T>                     the type parameter
   * @param <E>                     the type parameter
   * @param executor                the executor
   * @param uri                     the uri
   * @param data                    the data
   * @param withoutSuiteAccessToken the without suite access token
   * @return the t
   * @throws WxErrorException the wx error exception
   */
  protected <T, E> T executeInternal(RequestExecutor<T, E> executor, String uri, E data,
                                     boolean withoutSuiteAccessToken) throws WxErrorException {
    E dataForLog = DataUtils.handleDataWithSecret(data);

    if (uri.contains("suite_access_token=")) {
      throw new IllegalArgumentException("uri参数中不允许有suite_access_token: " + uri);
    }
    String uriWithAccessToken;
    if (!withoutSuiteAccessToken) {
      String suiteAccessToken = getSuiteAccessToken(false);
      uriWithAccessToken = uri + (uri.contains("?") ? "&" : "?") + "suite_access_token=" + suiteAccessToken;
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
       * 发生以下情况时尝试刷新suite_access_token
       * 42009 suite_access_token已过期
       */
      if (error.getErrorCode() == WxCpErrorMsgEnum.CODE_42009.getCode()) {
        // 强制设置wxCpTpConfigStorage它的suite access token过期了，这样在下一次请求里就会刷新suite access token
        this.configStorage.expireSuiteAccessToken();
        if (this.getWxCpTpConfigStorage().autoRefreshToken()) {
          log.warn("即将重新获取新的access_token，错误代码：{}，错误信息：{}", error.getErrorCode(), error.getErrorMsg());
          return this.execute(executor, uri, data);
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
  public void setWxCpTpConfigStorage(WxCpTpConfigStorage wxConfigProvider) {
    this.configStorage = wxConfigProvider;
    this.initHttp();
  }

  @Override
  public void setRetrySleepMillis(int retrySleepMillis) {
    this.retrySleepMillis = retrySleepMillis;
  }


  @Override
  public void setMaxRetryTimes(int maxRetryTimes) {
    this.maxRetryTimes = maxRetryTimes;
  }

  /**
   * Gets tmp dir file.
   *
   * @return the tmp dir file
   */
  public File getTmpDirFile() {
    return this.tmpDirFile;
  }

  /**
   * Sets tmp dir file.
   *
   * @param tmpDirFile the tmp dir file
   */
  public void setTmpDirFile(File tmpDirFile) {
    this.tmpDirFile = tmpDirFile;
  }

  @Override
  public RequestHttp<?, ?> getRequestHttp() {
    return this;
  }

  @Override
  public WxSessionManager getSessionManager() {
    return this.sessionManager;
  }

  @Override
  public WxCpTpUserInfo getUserInfo3rd(String code) throws WxErrorException {
    String url = configStorage.getApiUrl(GET_USERINFO3RD);
    String result = get(url + "?code=" + code, null);
    return WxCpTpUserInfo.fromJson(result);
  }

  @Override
  public WxCpTpUserDetail getUserDetail3rd(String userTicket) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("user_ticket", userTicket);
    String result = post(configStorage.getApiUrl(GET_USERDETAIL3RD), jsonObject.toString());
    return WxCpTpUserDetail.fromJson(result);
  }

  @Override
  public WxTpLoginInfo getLoginInfo(String authCode) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("auth_code", authCode);
    String access_token = getWxCpProviderToken();
    String responseText = post(configStorage.getApiUrl(GET_LOGIN_INFO) + "?access_token=" + access_token,
      jsonObject.toString(), true);
    return WxTpLoginInfo.fromJson(responseText);
  }

  @Override
  public WxTpCustomizedAuthUrl getCustomizedAuthUrl(String state, List<String> templateIdList) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("state", state);
    jsonObject.add("templateid_list", WxGsonBuilder.create().toJsonTree(templateIdList).getAsJsonArray());

    String responseText = post(configStorage.getApiUrl(GET_CUSTOMIZED_AUTH_URL) + "?provider_access_token=" + getWxCpProviderToken(), jsonObject.toString(), true);
    return WxTpCustomizedAuthUrl.fromJson(responseText);
  }

  @Override
  public String getWxCpProviderToken() throws WxErrorException {
    if (this.configStorage.isProviderTokenExpired()) {

      JsonObject jsonObject = new JsonObject();
      jsonObject.addProperty("corpid", configStorage.getCorpId());
      jsonObject.addProperty("provider_secret", configStorage.getProviderSecret());
      //providerAccessToken 的获取不需要suiteAccessToken ,一不必要，二可以提高效率
      WxCpProviderToken wxCpProviderToken =
        WxCpProviderToken.fromJson(this.post(this.configStorage.getApiUrl(GET_PROVIDER_TOKEN)
          , jsonObject.toString(), true));
      String providerAccessToken = wxCpProviderToken.getProviderAccessToken();
      Integer expiresIn = wxCpProviderToken.getExpiresIn();

      synchronized (globalProviderTokenRefreshLock) {
        configStorage.updateProviderToken(providerAccessToken, expiresIn - 200);
      }
    }
    return configStorage.getProviderToken();
  }

  @Override
  public WxCpProviderToken getWxCpProviderTokenEntity() throws WxErrorException {
    return this.getWxCpProviderTokenEntity(false);
  }

  @Override
  public WxCpProviderToken getWxCpProviderTokenEntity(boolean forceRefresh) throws WxErrorException {
    if (forceRefresh) {
      this.configStorage.expireProviderToken();
    }
    this.getWxCpProviderToken();
    return this.configStorage.getProviderTokenEntity();
  }

  @Override
  public WxCpTpContactService getWxCpTpContactService() {
    return wxCpTpContactService;
  }

  @Override
  public WxCpTpDepartmentService getWxCpTpDepartmentService() {
    return wxCpTpDepartmentService;
  }

  @Override
  public WxCpTpMediaService getWxCpTpMediaService() {
    return wxCpTpMediaService;
  }

  @Override
  public WxCpTpOAService getWxCpTpOAService() {
    return wxCpTpOAService;
  }

  @Override
  public WxCpTpUserService getWxCpTpUserService() {
    return wxCpTpUserService;
  }

  @Override
  public void setWxCpTpContactService(WxCpTpContactService wxCpTpContactService) {
    this.wxCpTpContactService = wxCpTpContactService;
  }

  @Override
  public void setWxCpTpDepartmentService(WxCpTpDepartmentService wxCpTpDepartmentService) {
    this.wxCpTpDepartmentService = wxCpTpDepartmentService;
  }

  @Override
  public void setWxCpTpMediaService(WxCpTpMediaService wxCpTpMediaService) {
    this.wxCpTpMediaService = wxCpTpMediaService;
  }

  @Override
  public void setWxCpTpOAService(WxCpTpOAService wxCpTpOAService) {
    this.wxCpTpOAService = wxCpTpOAService;
  }


  @Override
  public WxCpTpLicenseService getWxCpTpLicenseService() {
    return wxCpTpLicenseService;
  }


  @Override
  public void setWxCpTpLicenseService(WxCpTpLicenseService wxCpTpLicenseService) {
    this.wxCpTpLicenseService = wxCpTpLicenseService;
  }

  @Override
  public void setWxCpTpUserService(WxCpTpUserService wxCpTpUserService) {
    this.wxCpTpUserService = wxCpTpUserService;
  }

  @Override
  public WxCpTpAdmin getAdminList(String authCorpId, Integer agentId) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("auth_corpid", authCorpId);
    jsonObject.addProperty("agentid", agentId);
    String result = post(configStorage.getApiUrl(GET_ADMIN_LIST), jsonObject.toString());
    return WxCpTpAdmin.fromJson(result);
  }

  public WxCpTpAppQrcode getAppQrcode(String suiteId, String appId, String state, Integer style, Integer resultType) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("suite_id", suiteId);
    jsonObject.addProperty("appid", appId);
    jsonObject.addProperty("state", state);
    jsonObject.addProperty("style", style);
    jsonObject.addProperty("result_type", resultType);
    String result = post(configStorage.getApiUrl(GET_APP_QRCODE), jsonObject.toString());
    return WxCpTpAppQrcode.fromJson(result);
  }

  public WxCpTpCorpId2OpenCorpId corpId2OpenCorpId(String corpId) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("corpid", corpId);
    String result = post(configStorage.getApiUrl(CORPID_TO_OPENCORPID) +"?provider_access_token=" + getWxCpProviderToken(), jsonObject.toString());
    return WxCpTpCorpId2OpenCorpId.fromJson(result);
  }

  @Override
  public WxJsapiSignature createAuthCorpJsApiTicketSignature(String url, String authCorpId) throws WxErrorException {
    return doCreateWxJsapiSignature(url, authCorpId, this.getAuthCorpJsApiTicket(authCorpId));
  }

  @Override
  public WxJsapiSignature createSuiteJsApiTicketSignature(String url, String authCorpId) throws WxErrorException {
    return doCreateWxJsapiSignature(url, authCorpId, this.getSuiteJsApiTicket(authCorpId));
  }

  @Override
  public void expireSuiteAccessToken() {
    this.configStorage.expireSuiteAccessToken();
  }

  @Override
  public void expireAccessToken(String authCorpId) {
    this.configStorage.expireAccessToken(authCorpId);
  }

  @Override
  public void expireAuthCorpJsApiTicket(String authCorpId) {
    this.configStorage.expireAuthCorpJsApiTicket(authCorpId);
  }

  @Override
  public void expireAuthSuiteJsApiTicket(String authCorpId) {
    this.configStorage.expireAuthSuiteJsApiTicket(authCorpId);
  }

  @Override
  public void expireProviderToken() {
    this.configStorage.expireProviderToken();
  }

  @Override
  public WxCpTpOrderService getWxCpTpOrderService() {
    return wxCpTpOrderService;
  }

  @Override
  public void setWxCpTpOrderService(WxCpTpOrderService wxCpTpOrderService) {
    this.wxCpTpOrderService = wxCpTpOrderService;
  }

  @Override
  public WxCpTpEditionService getWxCpTpEditionService() {
    return wxCpTpEditionService;
  }

  @Override
  public void setWxCpTpOrderService(WxCpTpEditionService wxCpTpEditionService) {
    this.wxCpTpEditionService = wxCpTpEditionService;
  }

  private WxJsapiSignature doCreateWxJsapiSignature(String url, String authCorpId, String jsapiTicket) {
    long timestamp = System.currentTimeMillis() / 1000;
    String noncestr = RandomUtils.getRandomStr();
    String signature = SHA1
      .genWithAmple("jsapi_ticket=" + jsapiTicket, "noncestr=" + noncestr, "timestamp=" + timestamp,
        "url=" + url);
    WxJsapiSignature jsapiSignature = new WxJsapiSignature();
    jsapiSignature.setTimestamp(timestamp);
    jsapiSignature.setNonceStr(noncestr);
    jsapiSignature.setUrl(url);
    jsapiSignature.setSignature(signature);
    jsapiSignature.setAppId(authCorpId);

    return jsapiSignature;
  }

  @Override
  public WxCpTpIdConvertService getWxCpTpIdConverService() {
    return wxCpTpIdConvertService;
  }

  @Override
  public void setWxCpTpIdConverService(WxCpTpIdConvertService wxCpTpIdConvertService) {
    this.wxCpTpIdConvertService = wxCpTpIdConvertService;
  }


  @Override
  public WxCpTpOAuth2Service getWxCpTpOAuth2Service() {
    return wxCpTpOAuth2Service;
  }

  @Override
  public void setWxCpTpOAuth2Service(WxCpTpOAuth2Service wxCpTpOAuth2Service) {
    this.wxCpTpOAuth2Service = wxCpTpOAuth2Service;
  }
}
