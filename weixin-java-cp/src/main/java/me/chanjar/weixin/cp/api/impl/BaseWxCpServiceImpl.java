package me.chanjar.weixin.cp.api.impl;

import com.google.common.base.Joiner;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.CommonUploadParam;
import me.chanjar.weixin.common.bean.ToJson;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.error.WxRuntimeException;
import me.chanjar.weixin.common.executor.CommonUploadRequestExecutor;
import me.chanjar.weixin.common.session.StandardSessionManager;
import me.chanjar.weixin.common.session.WxSession;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.common.util.DataUtils;
import me.chanjar.weixin.common.util.RandomUtils;
import me.chanjar.weixin.common.util.crypto.SHA1;
import me.chanjar.weixin.common.util.http.*;
import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.cp.api.*;
import me.chanjar.weixin.cp.bean.WxCpAgentJsapiSignature;
import me.chanjar.weixin.cp.bean.WxCpMaJsCode2SessionResult;
import me.chanjar.weixin.cp.bean.WxCpProviderToken;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.*;

/**
 * .
 *
 * @param <H> the type parameter
 * @param <P> the type parameter
 * @author chanjarster
 */
@Slf4j
public abstract class BaseWxCpServiceImpl<H, P> implements WxCpService, RequestHttp<H, P> {
  private WxCpUserService userService = new WxCpUserServiceImpl(this);
  private final WxCpChatService chatService = new WxCpChatServiceImpl(this);
  private WxCpDepartmentService departmentService = new WxCpDepartmentServiceImpl(this);
  private WxCpMediaService mediaService = new WxCpMediaServiceImpl(this);
  private WxCpMenuService menuService = new WxCpMenuServiceImpl(this);
  private WxCpOAuth2Service oauth2Service = new WxCpOAuth2ServiceImpl(this);
  private WxCpTagService tagService = new WxCpTagServiceImpl(this);
  private WxCpAgentService agentService = new WxCpAgentServiceImpl(this);
  private final WxCpOaService oaService = new WxCpOaServiceImpl(this);
  private final WxCpSchoolService schoolService = new WxCpSchoolServiceImpl(this);
  private final WxCpSchoolUserService schoolUserService = new WxCpSchoolUserServiceImpl(this);
  private final WxCpSchoolHealthService schoolHealthService = new WxCpSchoolHealthServiceImpl(this);
  private final WxCpLivingService livingService = new WxCpLivingServiceImpl(this);
  private final WxCpOaAgentService oaAgentService = new WxCpOaAgentServiceImpl(this);
  private final WxCpOaWeDriveService oaWeDriveService = new WxCpOaWeDriveServiceImpl(this);
  private final WxCpMsgAuditService msgAuditService = new WxCpMsgAuditServiceImpl(this);
  private final WxCpTaskCardService taskCardService = new WxCpTaskCardServiceImpl(this);
  private final WxCpExternalContactService externalContactService = new WxCpExternalContactServiceImpl(this);
  private final WxCpGroupRobotService groupRobotService = new WxCpGroupRobotServiceImpl(this);
  private final WxCpMessageService messageService = new WxCpMessageServiceImpl(this);
  private final WxCpOaCalendarService oaCalendarService = new WxCpOaCalendarServiceImpl(this);
  private final WxCpOaMeetingRoomService oaMeetingRoomService = new WxCpOaMeetingRoomServiceImpl(this);
  private final WxCpOaScheduleService oaScheduleService = new WxCpOaOaScheduleServiceImpl(this);
  private final WxCpAgentWorkBenchService workBenchService = new WxCpAgentWorkBenchServiceImpl(this);
  private WxCpKfService kfService = new WxCpKfServiceImpl(this);

  private WxCpExportService exportService = new WxCpExportServiceImpl(this);

  private final WxCpMeetingService meetingService = new WxCpMeetingServiceImpl(this);
  private final WxCpCorpGroupService corpGroupService = new WxCpCorpGroupServiceImpl(this);
  private final WxCpIntelligentRobotService intelligentRobotService = new WxCpIntelligentRobotServiceImpl(this);

  /**
   * 全局的是否正在刷新access token的锁.
   */
  protected final Object globalAccessTokenRefreshLock = new Object();

  /**
   * 全局的是否正在刷新jsapi_ticket的锁.
   */
  protected final Object globalJsapiTicketRefreshLock = new Object();

  /**
   * 全局的是否正在刷新agent的jsapi_ticket的锁.
   */
  protected final Object globalAgentJsapiTicketRefreshLock = new Object();

  /**
   * The Config storage.
   */
  protected WxCpConfigStorage configStorage;

  private WxSessionManager sessionManager = new StandardSessionManager();

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
      log.error("Checking signature failed, and the reason is :{}", e.getMessage());
      return false;
    }
  }

  @Override
  public String getAccessToken() throws WxErrorException {
    return getAccessToken(false);
  }

  @Override
  public String getAgentJsapiTicket() throws WxErrorException {
    return this.getAgentJsapiTicket(false);
  }

  @Override
  public String getAgentJsapiTicket(boolean forceRefresh) throws WxErrorException {
    if (forceRefresh) {
      this.configStorage.expireAgentJsapiTicket();
    }

    if (this.configStorage.isAgentJsapiTicketExpired()) {
      synchronized (this.globalAgentJsapiTicketRefreshLock) {
        if (this.configStorage.isAgentJsapiTicketExpired()) {
          String responseContent = this.get(this.configStorage.getApiUrl(GET_AGENT_CONFIG_TICKET), null);
          JsonObject jsonObject = GsonParser.parse(responseContent);
          this.configStorage.updateAgentJsapiTicket(jsonObject.get("ticket").getAsString(),
            jsonObject.get("expires_in").getAsInt());
        }
      }
    }

    return this.configStorage.getAgentJsapiTicket();
  }

  @Override
  public String getJsapiTicket() throws WxErrorException {
    return getJsapiTicket(false);
  }

  @Override
  public String getJsapiTicket(boolean forceRefresh) throws WxErrorException {
    if (forceRefresh) {
      this.configStorage.expireJsapiTicket();
    }

    if (this.configStorage.isJsapiTicketExpired()) {
      synchronized (this.globalJsapiTicketRefreshLock) {
        if (this.configStorage.isJsapiTicketExpired()) {
          String responseContent = this.get(this.configStorage.getApiUrl(GET_JSAPI_TICKET), null);
          JsonObject tmpJsonObject = GsonParser.parse(responseContent);
          this.configStorage.updateJsapiTicket(tmpJsonObject.get("ticket").getAsString(),
            tmpJsonObject.get("expires_in").getAsInt());
        }
      }
    }

    return this.configStorage.getJsapiTicket();
  }

  @Override
  public WxJsapiSignature createJsapiSignature(String url) throws WxErrorException {
    long timestamp = System.currentTimeMillis() / 1000;
    String noncestr = RandomUtils.getRandomStr();
    String jsapiTicket = getJsapiTicket(false);
    String signature = SHA1.genWithAmple(
      "jsapi_ticket=" + jsapiTicket,
      "noncestr=" + noncestr,
      "timestamp=" + timestamp,
      "url=" + url
    );
    WxJsapiSignature jsapiSignature = new WxJsapiSignature();
    jsapiSignature.setTimestamp(timestamp);
    jsapiSignature.setNonceStr(noncestr);
    jsapiSignature.setUrl(url);
    jsapiSignature.setSignature(signature);

    // Fixed bug
    jsapiSignature.setAppId(this.configStorage.getCorpId());

    return jsapiSignature;
  }

  @Override
  public WxCpAgentJsapiSignature createAgentJsapiSignature(String url) throws WxErrorException {
    long timestamp = System.currentTimeMillis() / 1000;
    String noncestr = RandomUtils.getRandomStr();
    String jsapiTicket = getAgentJsapiTicket(false);
    String signature = SHA1.genWithAmple(
      "jsapi_ticket=" + jsapiTicket,
      "noncestr=" + noncestr,
      "timestamp=" + timestamp,
      "url=" + url
    );

    WxCpAgentJsapiSignature jsapiSignature = new WxCpAgentJsapiSignature();
    jsapiSignature.setTimestamp(timestamp);
    jsapiSignature.setNonceStr(noncestr);
    jsapiSignature.setUrl(url);
    jsapiSignature.setSignature(signature);

    jsapiSignature.setCorpid(this.configStorage.getCorpId());
    jsapiSignature.setAgentid(this.configStorage.getAgentId());

    return jsapiSignature;
  }

  @Override
  public WxCpMaJsCode2SessionResult jsCode2Session(String jsCode) throws WxErrorException {
    Map<String, String> params = new HashMap<>(2);
    params.put("js_code", jsCode);
    params.put("grant_type", "authorization_code");

    final String url = this.configStorage.getApiUrl(JSCODE_TO_SESSION);
    return WxCpMaJsCode2SessionResult.fromJson(this.get(url, Joiner.on("&").withKeyValueSeparator("=").join(params)));
  }

  @Override
  public String[] getCallbackIp() throws WxErrorException {
    return getIp(GET_CALLBACK_IP);
  }

  @Override
  public String[] getApiDomainIp() throws WxErrorException {
    return getIp(GET_API_DOMAIN_IP);
  }

  /**
   * 获取 IP
   *
   * @param suffixUrl 接口URL 后缀
   * @return 返回结果
   * @throws WxErrorException 异常信息
   */
  private String[] getIp(String suffixUrl) throws WxErrorException {
    String responseContent = get(this.configStorage.getApiUrl(suffixUrl), null);
    JsonObject tmpJsonObject = GsonParser.parse(responseContent);
    JsonArray jsonArray = tmpJsonObject.get("ip_list").getAsJsonArray();
    String[] ips = new String[jsonArray.size()];
    for (int i = 0; i < jsonArray.size(); i++) {
      ips[i] = jsonArray.get(i).getAsString();
    }
    return ips;
  }

  @Override
  public WxCpProviderToken getProviderToken(String corpId, String providerSecret) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("corpid", corpId);
    jsonObject.addProperty("provider_secret", providerSecret);
    return WxCpProviderToken.fromJson(this.post(this.configStorage.getApiUrl(Tp.GET_PROVIDER_TOKEN),
      jsonObject.toString()));
  }

  @Override
  public String get(String url, String queryParam) throws WxErrorException {
    return execute(SimpleGetRequestExecutor.create(this), url, queryParam);
  }

  @Override
  public String post(String url, String postData) throws WxErrorException {
    return execute(SimplePostRequestExecutor.create(this), url, postData);
  }

  @Override
  public String post(String url, JsonObject jsonObject) throws WxErrorException {
    return this.post(url, jsonObject.toString());
  }

  @Override
  public String post(String url, ToJson obj) throws WxErrorException {
    return this.post(url, obj.toJson());
  }

  @Override
  public String upload(String url, CommonUploadParam param) throws WxErrorException {
    RequestExecutor<String, CommonUploadParam> executor = CommonUploadRequestExecutor.create(getRequestHttp());
    return this.execute(executor, url, param);
  }

  @Override
  public String post(String url, Object obj) throws WxErrorException {
    return this.post(url, obj.toString());
  }

  @Override
  public String postWithoutToken(String url, String postData) throws WxErrorException {
    return this.executeNormal(SimplePostRequestExecutor.create(this), url, postData);
  }

  /**
   * 向微信端发送请求，在这里执行的策略是当发生access_token过期时才去刷新，然后重新执行请求，而不是全局定时请求.
   */
  @Override
  public <T, E> T execute(RequestExecutor<T, E> executor, String uri, E data) throws WxErrorException {
    int retryTimes = 0;
    do {
      try {
        return this.executeInternal(executor, uri, data, false);
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
   * @param <T>              the type parameter
   * @param <E>              the type parameter
   * @param executor         the executor
   * @param uri              the uri
   * @param data             the data
   * @param doNotAutoRefresh the do not auto refresh
   * @return the t
   * @throws WxErrorException the wx error exception
   */
  protected <T, E> T executeInternal(RequestExecutor<T, E> executor, String uri, E data, boolean doNotAutoRefresh) throws WxErrorException {
    E dataForLog = DataUtils.handleDataWithSecret(data);

    if (uri.contains("access_token=")) {
      throw new IllegalArgumentException("uri参数中不允许有access_token: " + uri);
    }
    String accessToken = getAccessToken(false);

    String uriWithAccessToken = uri + (uri.contains("?") ? "&" : "?") + "access_token=" + accessToken;

    try {
      T result = executor.execute(uriWithAccessToken, data, WxType.CP);
      log.debug("\n【请求地址】: {}\n【请求参数】：{}\n【响应数据】：{}", uriWithAccessToken, dataForLog, result);
      return result;
    } catch (WxErrorException e) {
      WxError error = e.getError();

      if (WxConsts.ACCESS_TOKEN_ERROR_CODES.contains(error.getErrorCode())) {
        // 强制设置wxCpConfigStorage它的access token过期了，这样在下一次请求里就会刷新access token
        this.configStorage.expireAccessToken();
        if (this.getWxCpConfigStorage().autoRefreshToken() && !doNotAutoRefresh) {
          log.warn("即将重新获取新的access_token，错误代码：{}，错误信息：{}", error.getErrorCode(), error.getErrorMsg());
          //下一次不再自动重试
          //当小程序误调用第三方平台专属接口时,第三方无法使用小程序的access token,如果可以继续自动获取token会导致无限循环重试,直到栈溢出
          return this.executeInternal(executor, uri, data, true);
        }
      }

      if (error.getErrorCode() != 0) {
        log.warn("\n【请求地址】: {}\n【请求参数】：{}\n【错误信息】：{}", uriWithAccessToken, dataForLog, error);
        throw new WxErrorException(error, e);
      }
      return null;
    } catch (IOException e) {
      log.warn("\n【请求地址】: {}\n【请求参数】：{}\n【异常信息】：{}", uriWithAccessToken, dataForLog, e.getMessage());
      throw new WxRuntimeException(e);
    }
  }

  /**
   * 普通请求，不自动带accessToken
   */
  private <T, E> T executeNormal(RequestExecutor<T, E> executor, String uri, E data) throws WxErrorException {
    try {
      T result = executor.execute(uri, data, WxType.CP);
      log.debug("\n【请求地址】: {}\n【请求参数】：{}\n【响应数据】：{}", uri, data, result);
      return result;
    } catch (WxErrorException e) {
      WxError error = e.getError();
      if (error.getErrorCode() != 0) {
        log.error("\n【请求地址】: {}\n【请求参数】：{}\n【错误信息】：{}", uri, data, error);
        throw new WxErrorException(error, e);
      }
      return null;
    } catch (IOException e) {
      log.error("\n【请求地址】: {}\n【请求参数】：{}\n【异常信息】：{}", uri, data, e.getMessage());
      throw new WxErrorException(e);
    }
  }

  @Override
  public void setWxCpConfigStorage(WxCpConfigStorage wxConfigProvider) {
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

  @Override
  public WxSession getSession(String id) {
    if (this.sessionManager == null) {
      return null;
    }
    return this.sessionManager.getSession(id);
  }

  @Override
  public WxSession getSession(String id, boolean create) {
    if (this.sessionManager == null) {
      return null;
    }
    return this.sessionManager.getSession(id, create);
  }

  @Override
  public void setSessionManager(WxSessionManager sessionManager) {
    this.sessionManager = sessionManager;
  }

  @Override
  public WxSessionManager getSessionManager() {
    return this.sessionManager;
  }

  @Override
  public String replaceParty(String mediaId) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("media_id", mediaId);
    return post(this.configStorage.getApiUrl(BATCH_REPLACE_PARTY), jsonObject.toString());
  }

  @Override
  public String syncUser(String mediaId) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("media_id", mediaId);
    String responseContent = post(this.configStorage.getApiUrl(BATCH_SYNC_USER), jsonObject.toString());
    JsonObject tmpJson = GsonParser.parse(responseContent);
    return tmpJson.get("jobid").getAsString();
  }

  @Override
  public String replaceUser(String mediaId) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("media_id", mediaId);
    return post(this.configStorage.getApiUrl(BATCH_REPLACE_USER), jsonObject.toString());
  }

  @Override
  public String getTaskResult(String jobId) throws WxErrorException {
    String url = this.configStorage.getApiUrl(BATCH_GET_RESULT + jobId);
    return get(url, null);
  }

  @Override
  public String buildQrConnectUrl(String redirectUri, String state) {
    return String.format("https://open.work.weixin.qq.com/wwopen/sso/qrConnect?appid=%s&agentid=%s&redirect_uri=%s" +
        "&state=%s",
      this.configStorage.getCorpId(), this.configStorage.getAgentId(),
      URIUtil.encodeURIComponent(redirectUri), StringUtils.trimToEmpty(state));
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
  public WxCpDepartmentService getDepartmentService() {
    return departmentService;
  }

  @Override
  public WxCpMediaService getMediaService() {
    return mediaService;
  }

  @Override
  public WxCpMenuService getMenuService() {
    return menuService;
  }

  @Override
  public WxCpOAuth2Service getOauth2Service() {
    return oauth2Service;
  }

  @Override
  public WxCpTagService getTagService() {
    return tagService;
  }

  @Override
  public WxCpUserService getUserService() {
    return userService;
  }

  @Override
  public WxCpExternalContactService getExternalContactService() {
    return externalContactService;
  }

  @Override
  public WxCpChatService getChatService() {
    return chatService;
  }

  @Override
  public WxCpOaService getOaService() {
    return oaService;
  }

  @Override
  public WxCpSchoolService getSchoolService() {
    return schoolService;
  }

  @Override
  public WxCpSchoolUserService getSchoolUserService() {
    return schoolUserService;
  }

  @Override
  public WxCpSchoolHealthService getSchoolHealthService() {
    return schoolHealthService;
  }

  @Override
  public WxCpLivingService getLivingService() {
    return livingService;
  }

  @Override
  public WxCpOaAgentService getOaAgentService() {
    return oaAgentService;
  }

  @Override
  public WxCpOaWeDriveService getOaWeDriveService() {
    return oaWeDriveService;
  }

  @Override
  public WxCpMsgAuditService getMsgAuditService() {
    return msgAuditService;
  }

  @Override
  public WxCpOaCalendarService getOaCalendarService() {
    return this.oaCalendarService;
  }

  @Override
  public WxCpOaMeetingRoomService getOaMeetingRoomService() {
    return this.oaMeetingRoomService;
  }

  @Override
  public WxCpGroupRobotService getGroupRobotService() {
    return groupRobotService;
  }

  @Override
  public WxCpAgentWorkBenchService getWorkBenchService() {
    return workBenchService;
  }

  @Override
  public WxCpTaskCardService getTaskCardService() {
    return taskCardService;
  }

  @Override
  public RequestHttp<?, ?> getRequestHttp() {
    return this;
  }

  @Override
  public void setUserService(WxCpUserService userService) {
    this.userService = userService;
  }

  @Override
  public void setDepartmentService(WxCpDepartmentService departmentService) {
    this.departmentService = departmentService;
  }

  @Override
  public void setMediaService(WxCpMediaService mediaService) {
    this.mediaService = mediaService;
  }

  @Override
  public void setMenuService(WxCpMenuService menuService) {
    this.menuService = menuService;
  }

  @Override
  public void setOauth2Service(WxCpOAuth2Service oauth2Service) {
    this.oauth2Service = oauth2Service;
  }

  @Override
  public void setTagService(WxCpTagService tagService) {
    this.tagService = tagService;
  }

  @Override
  public WxCpAgentService getAgentService() {
    return agentService;
  }

  @Override
  public WxCpMessageService getMessageService() {
    return this.messageService;
  }

  /**
   * Sets agent service.
   *
   * @param agentService the agent service
   */
  public void setAgentService(WxCpAgentService agentService) {
    this.agentService = agentService;
  }

  @Override
  public WxCpOaScheduleService getOaScheduleService() {
    return this.oaScheduleService;
  }

  @Override
  public WxCpKfService getKfService() {
    return kfService;
  }

  @Override
  public void setKfService(WxCpKfService kfService) {
    this.kfService = kfService;
  }


  @Override
  public WxCpExportService getExportService() {
    return exportService;
  }

  @Override
  public void setExportService(WxCpExportService exportService) {
    this.exportService = exportService;
  }

  @Override
  public WxCpMeetingService getMeetingService() {
    return meetingService;
  }

  @Override
  public WxCpCorpGroupService getCorpGroupService() {
    return corpGroupService;
  }

  @Override
  public WxCpIntelligentRobotService getIntelligentRobotService() {
    return this.intelligentRobotService;
  }
}
