package me.chanjar.weixin.open.api.impl;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.oauth2.WxOAuth2AccessToken;
import me.chanjar.weixin.common.bean.result.WxMinishopImageUploadResult;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.error.WxRuntimeException;
import me.chanjar.weixin.common.util.crypto.SHA1;
import me.chanjar.weixin.common.util.http.URIUtil;
import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.open.api.*;
import me.chanjar.weixin.open.bean.*;
import me.chanjar.weixin.open.bean.auth.WxOpenAuthorizationInfo;
import me.chanjar.weixin.open.bean.ma.WxOpenMaApplyOrderPathInfo;
import me.chanjar.weixin.open.bean.message.WxOpenXmlMessage;
import me.chanjar.weixin.open.bean.minishop.*;
import me.chanjar.weixin.open.bean.minishop.coupon.WxMinishopCoupon;
import me.chanjar.weixin.open.bean.minishop.coupon.WxMinishopCouponStock;
import me.chanjar.weixin.open.bean.minishop.goods.*;
import me.chanjar.weixin.open.bean.minishop.limitdiscount.LimitDiscountGoods;
import me.chanjar.weixin.open.bean.minishop.limitdiscount.LimitDiscountSku;
import me.chanjar.weixin.open.bean.result.*;
import me.chanjar.weixin.open.bean.tcb.ShareCloudBaseEnvRequest;
import me.chanjar.weixin.open.bean.tcb.ShareCloudBaseEnvResponse;
import me.chanjar.weixin.open.bean.tcbComponent.GetShareCloudBaseEnvResponse;
import me.chanjar.weixin.open.bean.tcbComponent.GetTcbEnvListResponse;
import me.chanjar.weixin.open.util.json.WxOpenGsonBuilder;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 */
@Slf4j
@AllArgsConstructor
public class WxOpenComponentServiceImpl implements WxOpenComponentService {

  private static final Map<String, WxOpenMaService> WX_OPEN_MA_SERVICE_MAP = new ConcurrentHashMap<>();
  private static final Map<String, WxOpenMpService> WX_OPEN_MP_SERVICE_MAP = new ConcurrentHashMap<>();
  private static final Map<String, WxOpenFastMaService> WX_OPEN_FAST_MA_SERVICE_MAP = new ConcurrentHashMap<>();

  private static final Map<String, WxOpenMinishopService> WX_OPEN_MINISHOP_SERVICE_MAP = new ConcurrentHashMap<>();

  private final WxOpenService wxOpenService;

  @Override
  public WxOpenMpService getWxMpServiceByAppid(String appId) {
    WxOpenMpService wxMpService = WX_OPEN_MP_SERVICE_MAP.get(appId);
    if (wxMpService == null) {
      synchronized (WX_OPEN_MP_SERVICE_MAP) {
        wxMpService = WX_OPEN_MP_SERVICE_MAP.get(appId);
        if (wxMpService == null) {
          WxOpenConfigStorage storage = this.getWxOpenConfigStorage();
          wxMpService = new WxOpenMpServiceImpl(this, appId, storage.getWxMpConfigStorage(appId));
          // 配置重试次数和重试间隔
          wxMpService.setMaxRetryTimes(storage.getMaxRetryTimes());
          wxMpService.setRetrySleepMillis(storage.getRetrySleepMillis());
          WX_OPEN_MP_SERVICE_MAP.put(appId, wxMpService);
        }
      }
    }
    return wxMpService;
  }

  @Override
  public WxOpenMaService getWxMaServiceByAppid(String appId) {
    WxOpenMaService wxOpenMaService = WX_OPEN_MA_SERVICE_MAP.get(appId);
    if (wxOpenMaService == null) {
      synchronized (WX_OPEN_MA_SERVICE_MAP) {
        wxOpenMaService = WX_OPEN_MA_SERVICE_MAP.get(appId);
        if (wxOpenMaService == null) {
          WxOpenConfigStorage storage = this.getWxOpenConfigStorage();
          wxOpenMaService = new WxOpenMaServiceImpl(this, appId, storage.getWxMaConfig(appId));
          // 配置重试次数和重试间隔
          wxOpenMaService.setMaxRetryTimes(storage.getMaxRetryTimes());
          wxOpenMaService.setRetrySleepMillis(storage.getRetrySleepMillis());
          WX_OPEN_MA_SERVICE_MAP.put(appId, wxOpenMaService);
        }
      }
    }
    return wxOpenMaService;
  }

  @Override
  public WxOpenFastMaService getWxFastMaServiceByAppid(String appId) {
    WxOpenFastMaService fastMaService = WX_OPEN_FAST_MA_SERVICE_MAP.get(appId);
    if (fastMaService == null) {
      synchronized (WX_OPEN_FAST_MA_SERVICE_MAP) {
        fastMaService = WX_OPEN_FAST_MA_SERVICE_MAP.get(appId);
        if (fastMaService == null) {
          WxOpenConfigStorage storage = this.getWxOpenConfigStorage();
          fastMaService = new WxOpenFastMaServiceImpl(this, appId, storage.getWxMaConfig(appId));
          // 配置重试次数和重试间隔
          fastMaService.setMaxRetryTimes(storage.getMaxRetryTimes());
          fastMaService.setRetrySleepMillis(storage.getRetrySleepMillis());
          WX_OPEN_FAST_MA_SERVICE_MAP.put(appId, fastMaService);
        }
      }
    }
    return fastMaService;
  }

  @Override
  public WxOpenMinishopService getWxMinishopServiceByAppid(String appId) {
    WxOpenMinishopService minishopService = WX_OPEN_MINISHOP_SERVICE_MAP.get(appId);
    if (minishopService == null) {
      synchronized (WX_OPEN_MINISHOP_SERVICE_MAP) {
        minishopService = WX_OPEN_MINISHOP_SERVICE_MAP.get(appId);
        if (minishopService == null) {
          minishopService = new WxOpenMinishopServiceImpl(this, appId, getWxOpenConfigStorage().getWxMaConfig(appId));
          WX_OPEN_MINISHOP_SERVICE_MAP.put(appId, minishopService);
        }
      }
    }

    return minishopService;
  }

  public WxOpenService getWxOpenService() {
    return wxOpenService;
  }

  @Override
  public WxOpenConfigStorage getWxOpenConfigStorage() {
    return wxOpenService.getWxOpenConfigStorage();
  }

  @Override
  public boolean checkSignature(String timestamp, String nonce, String signature) {
    try {
      return SHA1.gen(getWxOpenConfigStorage().getComponentToken(), timestamp, nonce)
        .equals(signature);
    } catch (Exception e) {
      log.error("Checking signature failed, and the reason is :{}", e.getMessage());
      return false;
    }
  }

  @Override
  public void startPushTicket() throws WxErrorException {
    WxOpenConfigStorage config = getWxOpenConfigStorage();

    JsonObject json = new JsonObject();
    json.addProperty("component_appid", config.getComponentAppId());
    json.addProperty("component_secret", config.getComponentAppSecret());

    getWxOpenService().post(API_START_PUSH_TICKET, json.toString());
  }

  @Override
  public String getComponentAccessToken(boolean forceRefresh) throws WxErrorException {
    final WxOpenConfigStorage config = this.getWxOpenConfigStorage();
    if (!config.isComponentAccessTokenExpired() && !forceRefresh) {
      return config.getComponentAccessToken();
    }
    Lock lock = config.getComponentAccessTokenLock();
    lock.lock();
    try {
      if (!config.isComponentAccessTokenExpired() && !forceRefresh) {
        return config.getComponentAccessToken();
      }
      JsonObject jsonObject = new JsonObject();
      jsonObject.addProperty("component_appid", getWxOpenConfigStorage().getComponentAppId());
      jsonObject.addProperty("component_appsecret", getWxOpenConfigStorage().getComponentAppSecret());
      jsonObject.addProperty("component_verify_ticket", getWxOpenConfigStorage().getComponentVerifyTicket());

      String responseContent = this.getWxOpenService().post(API_COMPONENT_TOKEN_URL, jsonObject.toString());
      WxOpenComponentAccessToken componentAccessToken = WxOpenComponentAccessToken.fromJson(responseContent);
      config.updateComponentAccessToken(componentAccessToken);
      return config.getComponentAccessToken();
    } finally {
      lock.unlock();
    }
  }

  @Override
  public String post(String uri, String postData) throws WxErrorException {
    return post(uri, postData, "component_access_token");
  }

  @Override
  public String post(String uri, String postData, String accessTokenKey) throws WxErrorException {
    String componentAccessToken = getComponentAccessToken(false);
    String uriWithComponentAccessToken = uri + (uri.contains("?") ? "&" : "?") + accessTokenKey + "=" + componentAccessToken;
    try {
      return getWxOpenService().post(uriWithComponentAccessToken, postData);
    } catch (WxErrorException e) {
      WxError error = e.getError();
      if (WxConsts.ACCESS_TOKEN_ERROR_CODES.contains(error.getErrorCode())) {
        // 强制设置access token过期，这样在下一次请求里就会刷新access token
        Lock lock = this.getWxOpenConfigStorage().getComponentAccessTokenLock();
        lock.lock();
        try {
          if (StringUtils.equals(componentAccessToken, this.getWxOpenConfigStorage().getComponentAccessToken())) {
            this.getWxOpenConfigStorage().expireComponentAccessToken();
          }
        } catch (Exception ex) {
          this.getWxOpenConfigStorage().expireComponentAccessToken();
        } finally {
          lock.unlock();
        }

        if (this.getWxOpenConfigStorage().autoRefreshToken()) {
          log.warn("即将重新获取新的access_token，错误代码：{}，错误信息：{}", error.getErrorCode(), error.getErrorMsg());
          return this.post(uri, postData, accessTokenKey);
        }
      }
      if (error.getErrorCode() != 0) {
        throw new WxErrorException(error, e);
      }
      return null;
    }
  }

  @Override
  public String post(String uri, String postData, String accessTokenKey, String accessToken) throws WxErrorException {
    String uriWithComponentAccessToken = uri + (uri.contains("?") ? "&" : "?") + accessTokenKey + "=" + accessToken;
    try {
      return getWxOpenService().post(uriWithComponentAccessToken, postData);
    } catch (WxErrorException e) {
      WxError error = e.getError();
      if (error.getErrorCode() != 0) {
        throw new WxErrorException(error, e);
      }
      return error.getErrorMsg();
    }
  }

  @Override
  public String get(String uri) throws WxErrorException {
    return get(uri, "component_access_token");
  }

  @Override
  public String get(String uri, String accessTokenKey) throws WxErrorException {
    String componentAccessToken = getComponentAccessToken(false);
    String uriWithComponentAccessToken = uri + (uri.contains("?") ? "&" : "?") + accessTokenKey + "=" + componentAccessToken;
    try {
      return getWxOpenService().get(uriWithComponentAccessToken, null);
    } catch (WxErrorException e) {
      WxError error = e.getError();
      if (WxConsts.ACCESS_TOKEN_ERROR_CODES.contains(error.getErrorCode())) {
        // 强制设置wxMpConfigStorage它的access token过期了，这样在下一次请求里就会刷新access token
        Lock lock = this.getWxOpenConfigStorage().getComponentAccessTokenLock();
        lock.lock();
        try {
          if (StringUtils.equals(componentAccessToken, this.getWxOpenConfigStorage().getComponentAccessToken())) {
            this.getWxOpenConfigStorage().expireComponentAccessToken();
          }
        } catch (Exception ex) {
          this.getWxOpenConfigStorage().expireComponentAccessToken();
        } finally {
          lock.unlock();
        }
        if (this.getWxOpenConfigStorage().autoRefreshToken()) {
          log.warn("即将重新获取新的access_token，错误代码：{}，错误信息：{}", error.getErrorCode(), error.getErrorMsg());
          return this.get(uri, accessTokenKey);
        }
      }
      if (error.getErrorCode() != 0) {
        throw new WxErrorException(error, e);
      }
      return null;
    }
  }

  @Override
  public String getPreAuthUrl(String redirectUri) throws WxErrorException {
    return getPreAuthUrl(redirectUri, null, null);
  }

  @Override
  public String getPreAuthUrl(String redirectUri, String authType, String bizAppid) throws WxErrorException {
    return createPreAuthUrl(redirectUri, authType, bizAppid, false);
  }

  @Override
  public String getMobilePreAuthUrl(String redirectUri) throws WxErrorException {
    return getMobilePreAuthUrl(redirectUri, null, null);
  }

  @Override
  public String getMobilePreAuthUrl(String redirectUri, String authType, String bizAppid) throws WxErrorException {
    return createPreAuthUrl(redirectUri, authType, bizAppid, true);
  }

  /**
   * 创建预授权链接
   */
  private String createPreAuthUrl(String redirectUri, String authType, String bizAppid, boolean isMobile) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("component_appid", getWxOpenConfigStorage().getComponentAppId());
    String responseContent = post(API_CREATE_PREAUTHCODE_URL, jsonObject.toString());
    jsonObject = WxGsonBuilder.create().fromJson(responseContent, JsonObject.class);

    String preAuthUrlStr = String.format((isMobile ? COMPONENT_MOBILE_LOGIN_PAGE_URL : COMPONENT_LOGIN_PAGE_URL),
      getWxOpenConfigStorage().getComponentAppId(),
      jsonObject.get("pre_auth_code").getAsString(),
      URIUtil.encodeURIComponent(redirectUri));
    if (StringUtils.isNotEmpty(authType)) {
      preAuthUrlStr = preAuthUrlStr.replace("&auth_type=xxx", "&auth_type=" + authType);
    } else {
      preAuthUrlStr = preAuthUrlStr.replace("&auth_type=xxx", "");
    }
    if (StringUtils.isNotEmpty(bizAppid)) {
      preAuthUrlStr = preAuthUrlStr.replace("&biz_appid=xxx", "&biz_appid=" + bizAppid);
    } else {
      preAuthUrlStr = preAuthUrlStr.replace("&biz_appid=xxx", "");
    }
    return preAuthUrlStr;
  }


  @Override
  public String route(final WxOpenXmlMessage wxMessage) throws WxErrorException {
    if (wxMessage == null) {
      throw new NullPointerException("message is empty");
    }
    if (StringUtils.equalsIgnoreCase(wxMessage.getInfoType(), "component_verify_ticket")) {
      getWxOpenConfigStorage().setComponentVerifyTicket(wxMessage.getComponentVerifyTicket());
      return "success";
    }
    //新增、更新授权
    if (StringUtils.equalsAnyIgnoreCase(wxMessage.getInfoType(), "authorized", "updateauthorized")) {
      WxOpenQueryAuthResult queryAuth = wxOpenService.getWxOpenComponentService().getQueryAuth(wxMessage.getAuthorizationCode());
      if (queryAuth == null || queryAuth.getAuthorizationInfo() == null || queryAuth.getAuthorizationInfo().getAuthorizerAppid() == null) {
        throw new NullPointerException("getQueryAuth");
      }
      return "success";
    }
    //快速创建小程序
    if (StringUtils.equalsIgnoreCase(wxMessage.getInfoType(), "notify_third_fasteregister") && wxMessage.getStatus() == 0) {
      WxOpenQueryAuthResult queryAuth = wxOpenService.getWxOpenComponentService().getQueryAuth(wxMessage.getAuthCode());
      if (queryAuth == null || queryAuth.getAuthorizationInfo() == null || queryAuth.getAuthorizationInfo().getAuthorizerAppid() == null) {
        throw new NullPointerException("getQueryAuth");
      }
      return "success";
    }
    return "";
  }

  @Override
  public WxOpenQueryAuthResult getQueryAuth(String authorizationCode) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("component_appid", getWxOpenConfigStorage().getComponentAppId());
    jsonObject.addProperty("authorization_code", authorizationCode);
    String responseContent = post(API_QUERY_AUTH_URL, jsonObject.toString());
    WxOpenQueryAuthResult queryAuth = WxOpenGsonBuilder.create().fromJson(responseContent, WxOpenQueryAuthResult.class);
    if (queryAuth == null || queryAuth.getAuthorizationInfo() == null) {
      return queryAuth;
    }
    WxOpenAuthorizationInfo authorizationInfo = queryAuth.getAuthorizationInfo();
    if (authorizationInfo.getAuthorizerAccessToken() != null) {
      getWxOpenConfigStorage().updateAuthorizerAccessToken(authorizationInfo.getAuthorizerAppid(),
        authorizationInfo.getAuthorizerAccessToken(), authorizationInfo.getExpiresIn());
    }
    if (authorizationInfo.getAuthorizerRefreshToken() != null) {
      getWxOpenConfigStorage().updateAuthorizerRefreshToken(authorizationInfo.getAuthorizerAppid(), authorizationInfo.getAuthorizerRefreshToken());
    }
    return queryAuth;
  }

  @Override
  public WxOpenAuthorizerInfoResult getAuthorizerInfo(String authorizerAppid) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("component_appid", getWxOpenConfigStorage().getComponentAppId());
    jsonObject.addProperty("authorizer_appid", authorizerAppid);
    String responseContent = post(API_GET_AUTHORIZER_INFO_URL, jsonObject.toString());
    return WxOpenGsonBuilder.create().fromJson(responseContent, WxOpenAuthorizerInfoResult.class);
  }

  @Override
  public WxOpenAuthorizerListResult getAuthorizerList(int begin, int len) throws WxErrorException {
    begin = Math.max(begin, 0);
    len = len == 0 ? 10 : len;
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("component_appid", getWxOpenConfigStorage().getComponentAppId());
    jsonObject.addProperty("offset", begin);
    jsonObject.addProperty("count", len);
    String responseContent = post(API_GET_AUTHORIZER_LIST, jsonObject.toString());
    WxOpenAuthorizerListResult ret = WxOpenGsonBuilder.create().fromJson(responseContent, WxOpenAuthorizerListResult.class);
    if (ret != null && ret.getList() != null) {
      for (Map<String, String> data : ret.getList()) {
        String authorizerAppid = data.get("authorizer_appid");
        String refreshToken = data.get("refresh_token");
        if (authorizerAppid != null && refreshToken != null) {
          this.getWxOpenConfigStorage().updateAuthorizerRefreshToken(authorizerAppid, refreshToken);
        }
      }
    }
    return ret;
  }

  @Override
  public WxOpenAuthorizerOptionResult getAuthorizerOption(String authorizerAppid, String optionName) throws WxErrorException {
    String authorizerAccessToken = this.getAuthorizerAccessToken(authorizerAppid, false);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("component_appid", getWxOpenConfigStorage().getComponentAppId());
    jsonObject.addProperty("authorizer_appid", authorizerAppid);
    jsonObject.addProperty("option_name", optionName);
    String responseContent = post(GET_AUTHORIZER_OPTION_URL, jsonObject.toString(), "access_token", authorizerAccessToken);
    return WxOpenGsonBuilder.create().fromJson(responseContent, WxOpenAuthorizerOptionResult.class);
  }

  @Override
  public void setAuthorizerOption(String authorizerAppid, String optionName, String optionValue) throws WxErrorException {
    String authorizerAccessToken = this.getAuthorizerAccessToken(authorizerAppid, false);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("component_appid", getWxOpenConfigStorage().getComponentAppId());
    jsonObject.addProperty("authorizer_appid", authorizerAppid);
    jsonObject.addProperty("option_name", optionName);
    jsonObject.addProperty("option_value", optionValue);
    post(SET_AUTHORIZER_OPTION_URL, jsonObject.toString(), "access_token", authorizerAccessToken);
  }

  @Override
  public String getAuthorizerAccessToken(String appId, boolean forceRefresh) throws WxErrorException {
    WxOpenConfigStorage config = getWxOpenConfigStorage();
    if (!config.isAuthorizerAccessTokenExpired(appId) && !forceRefresh) {
      return config.getAuthorizerAccessToken(appId);
    }
    Lock lock = config.getWxMpConfigStorage(appId).getAccessTokenLock();
    boolean locked = false;
    try {
      do {
        locked = lock.tryLock(100, TimeUnit.MILLISECONDS);
        if (!forceRefresh && !config.isAuthorizerAccessTokenExpired(appId)) {
          return config.getAuthorizerAccessToken(appId);
        }
      } while (!locked);

      JsonObject jsonObject = new JsonObject();
      jsonObject.addProperty("component_appid", getWxOpenConfigStorage().getComponentAppId());
      jsonObject.addProperty("authorizer_appid", appId);
      jsonObject.addProperty("authorizer_refresh_token", getWxOpenConfigStorage().getAuthorizerRefreshToken(appId));
      String responseContent = post(API_AUTHORIZER_TOKEN_URL, jsonObject.toString());

      WxOpenAuthorizerAccessToken wxOpenAuthorizerAccessToken = WxOpenAuthorizerAccessToken.fromJson(responseContent);
      config.updateAuthorizerAccessToken(appId, wxOpenAuthorizerAccessToken);
      config.updateAuthorizerRefreshToken(appId, wxOpenAuthorizerAccessToken.getAuthorizerRefreshToken());
      return config.getAuthorizerAccessToken(appId);
    } catch (InterruptedException e) {
      throw new WxRuntimeException(e);
    } finally {
      if (locked) {
        lock.unlock();
      }
    }
  }

  @Override
  public WxOAuth2AccessToken oauth2getAccessToken(String appId, String code) throws WxErrorException {
    String url = String.format(OAUTH2_ACCESS_TOKEN_URL, appId, code, getWxOpenConfigStorage().getComponentAppId());
    String responseContent = get(url);
    return WxOAuth2AccessToken.fromJson(responseContent);
  }

  @Override
  public boolean checkSignature(String appid, String timestamp, String nonce, String signature) {
    return false;
  }

  @Override
  public WxOAuth2AccessToken oauth2refreshAccessToken(String appId, String refreshToken) throws WxErrorException {
    String url = String.format(OAUTH2_REFRESH_TOKEN_URL, appId, refreshToken, getWxOpenConfigStorage().getComponentAppId());
    String responseContent = get(url);
    return WxOAuth2AccessToken.fromJson(responseContent);
  }

  @Override
  public String oauth2buildAuthorizationUrl(String appId, String redirectURI, String scope, String state) {
    return String.format(CONNECT_OAUTH2_AUTHORIZE_URL, appId, URIUtil.encodeURIComponent(redirectURI), scope,
      StringUtils.trimToEmpty(state), getWxOpenConfigStorage().getComponentAppId());
  }

  @Override
  public WxMaJscode2SessionResult miniappJscode2Session(String appId, String jsCode) throws WxErrorException {
    String url = String.format(MINIAPP_JSCODE_2_SESSION, appId, jsCode, getWxOpenConfigStorage().getComponentAppId());
    String responseContent = get(url);
    return WxMaJscode2SessionResult.fromJson(responseContent);
  }

  @Override
  public List<WxOpenMaCodeTemplate> getTemplateDraftList() throws WxErrorException {
    String responseContent = get(GET_TEMPLATE_DRAFT_LIST_URL, "access_token");
    JsonObject response = GsonParser.parse(StringUtils.defaultString(responseContent, "{}"));
    boolean hasDraftList = response.has("draft_list");
    if (hasDraftList) {
      return WxOpenGsonBuilder.create().fromJson(response.getAsJsonArray("draft_list"),
        new TypeToken<List<WxOpenMaCodeTemplate>>() {
        }.getType());
    } else {
      return null;
    }
  }

  @Override
  public List<WxOpenMaCodeTemplate> getTemplateList() throws WxErrorException {
    return getTemplateList(null);
  }

  @Override
  public List<WxOpenMaCodeTemplate> getTemplateList(Integer templateType) throws WxErrorException {
    String url = GET_TEMPLATE_LIST_URL + (templateType == null ? "" : "?template_type=" + templateType);
    String responseContent = get(url, "access_token");
    JsonObject response = GsonParser.parse(StringUtils.defaultString(responseContent, "{}"));
    boolean hasTemplateList = response.has("template_list");
    if (hasTemplateList) {
      return WxOpenGsonBuilder.create().fromJson(response.getAsJsonArray("template_list"),
        new TypeToken<List<WxOpenMaCodeTemplate>>() {
        }.getType());
    } else {
      return null;
    }
  }

  @Override
  public void addToTemplate(long draftId) throws WxErrorException {
    JsonObject param = new JsonObject();
    param.addProperty("draft_id", draftId);
    post(ADD_TO_TEMPLATE_URL, param.toString(), "access_token");
  }

  @Override
  public void addToTemplate(long draftId, int templateType) throws WxErrorException {
    JsonObject param = new JsonObject();
    param.addProperty("draft_id", draftId);
    param.addProperty("template_type", templateType);
    post(ADD_TO_TEMPLATE_URL, param.toString(), "access_token");
  }

  @Override
  public void deleteTemplate(long templateId) throws WxErrorException {
    JsonObject param = new JsonObject();
    param.addProperty("template_id", templateId);
    post(DELETE_TEMPLATE_URL, param.toString(), "access_token");
  }

  /**
   * 微信开放平台帐号管理统一请求入口
   *
   * @param appId      操作appId 小程序/公众号
   * @param appIdType  操作类型   小程序/公众号
   * @param requestUrl 请求地址
   * @param param      请求参数
   * @return 请求结果
   * @throws WxErrorException
   */
  private String openAccountServicePost(String appId, String appIdType, String requestUrl, JsonObject param) throws WxErrorException {
    String result = "";
    switch (appIdType) {
      case WxConsts.AppIdType.MP_TYPE:
        WxMpService wxMpService = this.getWxMpServiceByAppid(appId);
        result = wxMpService.post(requestUrl, param.toString());
        return result;
      case WxConsts.AppIdType.MINI_TYPE:
        WxOpenMaService maService = this.getWxMaServiceByAppid(appId);
        result = maService.post(requestUrl, param.toString());
        return result;
      default:
        throw new WxErrorException("appIdType类型异常");
    }
  }

  @Override
  public WxOpenCreateResult createOpenAccount(String appId, String appIdType) throws WxErrorException {
    JsonObject param = new JsonObject();
    param.addProperty("appid", appId);

    String json = openAccountServicePost(appId, appIdType, CREATE_OPEN_URL, param);

    return WxOpenCreateResult.fromJson(json);
  }


  @Override
  public Boolean bindOpenAccount(String appId, String appIdType, String openAppid) throws WxErrorException {
    JsonObject param = new JsonObject();
    param.addProperty("appid", appId);
    param.addProperty("open_appid", openAppid);

    String json = openAccountServicePost(appId, appIdType, BIND_OPEN_URL, param);
    return WxOpenResult.fromJson(json).isSuccess();
  }


  @Override
  public Boolean unbindOpenAccount(String appId, String appIdType, String openAppid) throws WxErrorException {
    JsonObject param = new JsonObject();
    param.addProperty("appid", appId);
    param.addProperty("open_appid", openAppid);

    String json = openAccountServicePost(appId, appIdType, UNBIND_OPEN_URL, param);
    return WxOpenResult.fromJson(json).isSuccess();
  }


  @Override
  public WxOpenGetResult getOpenAccount(String appId, String appIdType) throws WxErrorException {
    JsonObject param = new JsonObject();
    param.addProperty("appid", appId);

    String json = openAccountServicePost(appId, appIdType, GET_OPEN_URL, param);
    return WxOpenGetResult.fromJson(json);
  }

  @Override
  public WxOpenHaveResult haveOpen() throws WxErrorException {
    String json = get(HAVE_OPEN_URL, "access_token");
    return WxOpenHaveResult.fromJson(json);
  }


  @Override
  public WxOpenResult fastRegisterWeapp(String name, String code, String codeType, String legalPersonaWechat, String legalPersonaName, String componentPhone) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("name", name);
    jsonObject.addProperty("code", code);
    jsonObject.addProperty("code_type", codeType);
    jsonObject.addProperty("legal_persona_wechat", legalPersonaWechat);
    jsonObject.addProperty("legal_persona_name", legalPersonaName);
    jsonObject.addProperty("component_phone", componentPhone);
    String response = post(FAST_REGISTER_WEAPP_URL, jsonObject.toString(), "component_access_token");
    return WxOpenGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  @Override
  public WxOpenResult fastRegisterWeappSearch(String name, String legalPersonaWechat, String legalPersonaName) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("name", name);
    jsonObject.addProperty("legal_persona_wechat", legalPersonaWechat);
    jsonObject.addProperty("legal_persona_name", legalPersonaName);
    String response = post(FAST_REGISTER_WEAPP_SEARCH_URL, jsonObject.toString(), "component_access_token");
    return WxOpenGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  @Override
  public WxOpenRegisterPersonalWeappResult fastRegisterPersonalWeapp(String idname, String wxuser, String componentPhone) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("idname", idname);
    jsonObject.addProperty("wxuser", wxuser);
    jsonObject.addProperty("component_phone", componentPhone);
    String response = post(FAST_REGISTER_PERSONAL_WEAPP_URL, jsonObject.toString(), "component_access_token");
    return WxOpenGsonBuilder.create().fromJson(response, WxOpenRegisterPersonalWeappResult.class);
  }

  @Override
  public WxOpenRegisterPersonalWeappResult fastRegisterPersonalWeappSearch(String taskid) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("taskid", taskid);
    String response = post(FAST_REGISTER_PERSONAL_WEAPP_SEARCH_URL, jsonObject.toString(), "component_access_token");
    return WxOpenGsonBuilder.create().fromJson(response, WxOpenRegisterPersonalWeappResult.class);
  }

  @Override
  public WxOpenRegisterBetaWeappResult fastRegisterBetaWeapp(String name, String openid) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("name", name);
    jsonObject.addProperty("openid", openid);
    String response = wxOpenService.getWxOpenComponentService()
      .post(FAST_REGISTER_BETA_WEAPP_URL, jsonObject.toString(), "access_token");
    return WxOpenGsonBuilder.create().fromJson(response, WxOpenRegisterBetaWeappResult.class);
  }

  @Override
  public WxOpenResult registerShop(String wxName, String idCardName, String idCardNumber, String channelId, Integer apiOpenstoreType, String authPageUrl) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("wx_name", wxName);
    jsonObject.addProperty("id_card_name", idCardName);
    jsonObject.addProperty("id_card_number", idCardNumber);
    if (channelId != null && !channelId.isEmpty()) {
      jsonObject.addProperty("channel_id", channelId);
    }
    jsonObject.addProperty("api_openstore_type", apiOpenstoreType);
    if (authPageUrl != null && !authPageUrl.isEmpty()) {
      jsonObject.addProperty("auth_page_url", authPageUrl);
    }

    String response = post(REGISTER_SHOP_URL, jsonObject.toString(), "component_access_token");
    return WxOpenGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  @Override
  public String checkAuditStatus(String wxName) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("wx_name", wxName);
    String url = CHECK_SHOP_AUDITSTATUS_URL + "?access_token=" + getComponentAccessToken(false);
    String response = post(url, jsonObject.toString());
    log.info("CHECK_SHOP_AUDITSTATUS_URL: {}", response);
    return response;
  }

  @Override
  public String checkAuditStatus(String appId, String wxName) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("wx_name", wxName);
    String url = CHECK_SHOP_AUDITSTATUS_URL + "?access_token=" + getAuthorizerAccessToken(appId, false);
    String response = post(url, jsonObject.toString());
    log.info("CHECK_SHOP_AUDITSTATUS_URL: {}", response);
    return response;
  }

  @Override
  public WxOpenResult submitMerchantInfo(String appId, String subjectType, MinishopBusiLicense busiLicense, MinishopOrganizationCodeInfo organizationCodeInfo, MinishopIdcardInfo idcardInfo, MinishopSuperAdministratorInfo superAdministratorInfo, String merchantShoprtName) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("app_id", appId);
    jsonObject.addProperty("subject_type", subjectType);
    jsonObject.add("busi_license", busiLicense.toJsonObject());
    if (organizationCodeInfo != null) {
      jsonObject.add("organization_code_info", organizationCodeInfo.toJsonObject());
    }
    if (idcardInfo != null) {
      jsonObject.add("id_card_info", idcardInfo.toJsonObject());
    }
    if (superAdministratorInfo != null) {
      jsonObject.add("super_administrator_info", superAdministratorInfo.toJsonObject());
    }

    if (merchantShoprtName != null) {
      jsonObject.addProperty("merchant_shortname", merchantShoprtName);
    }
    String url = SUBMIT_MERCHANTINFO_URL + "?access_token=" + getAuthorizerAccessToken(appId, false);
    String response = getWxOpenService().post(url, jsonObject.toString());
    return WxOpenGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  @Override
  public WxOpenResult submitBasicInfo(String appId, MinishopNameInfo nameInfo, MinishopReturnInfo returnInfo) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("appid", appId);
    jsonObject.add("name_info", nameInfo.toJsonObject());
    jsonObject.add("return_info", returnInfo.toJsonObject());
    String url = SUBMIT_BASICINFO_URL + "?access_token=" + getAuthorizerAccessToken(appId, false);
    String response = getWxOpenService().post(url, jsonObject.toString());
    return WxOpenGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  @Override
  public WxMinishopImageUploadResult uploadMinishopImagePicFile(String appId, Integer height, Integer width, File file) throws WxErrorException {
    String url = WxOpenMinishopService.UPLOAD_IMG_MINISHOP_FILE_URL + "?access_token=" + getAuthorizerAccessToken(appId, false) + "&height=" + height + "&width=" + width;
    log.info("upload url: {}", url);
//    String response = (url, file);
    WxMinishopImageUploadResult result = getWxOpenService().uploadMinishopMediaFile(url, file);

    return result;
  }

  @Override
  public MinishopCategories getMinishopCategories(String appId, Integer fCatId) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("f_cat_id", fCatId);
    String url = MINISHOP_CATEGORY_GET_URL + "?access_token=" + getAuthorizerAccessToken(appId, false);
    String response = getWxOpenService().post(url, jsonObject.toString());
    log.info("response: {}", response);
    JsonObject respJson = GsonParser.parse(response);
    MinishopCategories categories = new MinishopCategories();
    categories.setErrcode(respJson.get(WxConsts.ERR_CODE).getAsInt());
    if (categories.getErrcode() == 0) {
      JsonArray catListJson = respJson.getAsJsonArray("cat_list");
      if (catListJson != null || !catListJson.isEmpty()) {
        List<MinishopCategory> categoryList = new ArrayList<>();
        for (int i = 0; i < catListJson.size(); i++) {
          JsonObject catJson = catListJson.get(i).getAsJsonObject();
          MinishopCategory cate = new MinishopCategory();
          cate.setCatId(catJson.get("cat_id").getAsInt());
          cate.setFCatId(catJson.get("f_cat_id").getAsInt());
          cate.setName(catJson.get("name").getAsString());
          categoryList.add(cate);
        }

        categories.setCatList(categoryList);
      }
    } else {
      categories.setErrmsg(respJson.get("errmsg").getAsString());
    }
    return categories;
  }

  @Override
  public MinishopBrandList getMinishopBrands(String appId) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    String url = MINISHOP_BRAND_GET_URL + "?access_token=" + getAuthorizerAccessToken(appId, false);

    String response = getWxOpenService().post(url, jsonObject.toString());
    JsonObject respJson = GsonParser.parse(response);
    MinishopBrandList brandList = new MinishopBrandList();
    brandList.setErrcode(respJson.get(WxConsts.ERR_CODE).getAsInt());
    if (brandList.getErrcode() == 0) {
      JsonArray brandArrayJson = respJson.get("brands").getAsJsonArray();
      if (!brandArrayJson.isEmpty()) {
        List<MinishopBrand> brands = new ArrayList<>();
        for (int i = 0; i < brandArrayJson.size(); i++) {
          JsonObject brandJson = brandArrayJson.get(i).getAsJsonObject();
          MinishopBrand brand = new MinishopBrand();
          brand.setFirstCatId(brandJson.get("first_cat_id").getAsInt());
          brand.setSecondCatId(brandJson.get("second_cat_id").getAsInt());
          brand.setThirdCatId(brandJson.get("third_cat_id").getAsInt());
          MinishopBrand.MinishopBrandInfo brandInfo = new MinishopBrand.MinishopBrandInfo();
          JsonObject brandInfoJson = brandJson.get("brand_info").getAsJsonObject();
          brandInfo.setBrandId(brandInfoJson.get("brand_id").getAsInt());
          brandInfo.setBrandName(brandInfoJson.get("brand_name").getAsString());
          brand.setBrandInfo(brandInfo);

          brands.add(brand);

        }

        brandList.setBrands(brands);
      }
    } else {
      brandList.setErrmsg(respJson.get("errmsg").getAsString());
    }
    return brandList;
  }

  @Override
  public MinishopDeliveryTemplateResult getMinishopDeliveryTemplate(String appId) throws WxErrorException {
    String url = MINISHOP_DELIVERY_TEMPLATE_GET_URL + "?access_token=" + getAuthorizerAccessToken(appId, false);
    JsonObject jsonObject = new JsonObject();

    String response = getWxOpenService().post(url, jsonObject.toString());
    JsonObject respJson = GsonParser.parse(response);
    MinishopDeliveryTemplateResult templateResult = new MinishopDeliveryTemplateResult();
    templateResult.setErrCode(respJson.get(WxConsts.ERR_CODE).getAsInt());
    if (templateResult.getErrCode() == 0) {
      JsonArray templateArrayJson = respJson.get("template_list").getAsJsonArray();
      if (!templateArrayJson.isEmpty()) {
        List<MinishopDeliveryTemplate> templateList = new ArrayList<>();
        for (int i = 0; i < templateArrayJson.size(); i++) {
          JsonObject templateJson = templateArrayJson.get(i).getAsJsonObject();
          MinishopDeliveryTemplate template = new MinishopDeliveryTemplate();
          template.setTemplateId(templateJson.get("template_id").getAsInt());
          template.setName(templateJson.get("name").getAsString());
          template.setValuationType(templateJson.get("valuation_type").getAsInt() == 1 ? MinishopDeliveryTemplate.ValuationType.WEIGHT : MinishopDeliveryTemplate.ValuationType.PACKAGE);


          templateList.add(template);

        }

        templateResult.setTemplateList(templateList);
      }
    } else {
      templateResult.setErrMsg(respJson.get("errmsg").getAsString());
    }
    return templateResult;
  }

  @Override
  public MinishopShopCatList getMinishopCatList(String appId) throws WxErrorException {
    String url = MINISHOP_SHOPCATEGORY_GET_URL + "?access_token=" + getAuthorizerAccessToken(appId, false);
    JsonObject jsonObject = new JsonObject();

    String response = getWxOpenService().post(url, jsonObject.toString());
    JsonObject respJson = GsonParser.parse(response);
    MinishopShopCatList shopCatList = new MinishopShopCatList();
    shopCatList.setErrcode(respJson.get(WxConsts.ERR_CODE).getAsInt());
    if (shopCatList.getErrcode() == 0) {
      JsonArray shopcatArrayJson = respJson.get("shopcat_list").getAsJsonArray();
      if (!shopcatArrayJson.isEmpty()) {
        List<MinishopShopCat> shopCats = new ArrayList<>();
        for (int i = 0; i < shopcatArrayJson.size(); i++) {
          JsonObject shopCatJson = shopcatArrayJson.get(i).getAsJsonObject();
          MinishopShopCat shopCat = new MinishopShopCat();
          shopCat.setShopCatId(shopCatJson.get("shopcat_id").getAsInt());
          shopCat.setShopCatName(shopCatJson.get("shopcat_name").getAsString());
          shopCat.setFShopCatId(shopCatJson.get("f_shopcat_id").getAsInt());
          shopCat.setCatLevel(shopCatJson.get("cat_level").getAsInt());

          shopCats.add(shopCat);

        }

        shopCatList.setShopCatList(shopCats);
      }
    } else {
      shopCatList.setErrmsg(respJson.get("errmsg").getAsString());
    }
    return shopCatList;
  }

  @Override
  public WxMinishopAddGoodsSpuResult<List<WxMinishopDeliveryCompany>> getMinishopDeliveryCompany(String appId) throws WxErrorException {
    String url = MINISHOP_GET_DELIVERY_COMPANY_URL + "?access_token=" + getAuthorizerAccessToken(appId, false);
    JsonObject jsonObject = new JsonObject();

    String response = getWxOpenService().post(url, jsonObject.toString());

    JsonObject respObj = GsonParser.parse(response);
    WxMinishopAddGoodsSpuResult<List<WxMinishopDeliveryCompany>> result = new WxMinishopAddGoodsSpuResult<>();
    result.setErrcode(respObj.get(WxConsts.ERR_CODE).getAsInt());
    if (result.getErrcode() == 0) {
      JsonArray companyArray = respObj.get("company_list").getAsJsonArray();
      List<WxMinishopDeliveryCompany> companies = new ArrayList<>();
      for (int i = 0; i < companyArray.size(); i++) {
        JsonObject company = companyArray.get(i).getAsJsonObject();
        WxMinishopDeliveryCompany resultData = new WxMinishopDeliveryCompany();
        resultData.setDeliveryId(company.get("delivery_id").getAsString());
        resultData.setDeliveryName(company.get("delivery_name").getAsString());
        companies.add(resultData);
      }
      result.setData(companies);
    } else {
      result.setErrmsg(respObj.get("errmsg").getAsString());
    }
    return result;
  }

  @Override
  public Integer minishopCreateCoupon(String appId, WxMinishopCoupon couponInfo) throws WxErrorException {
    String url = MINISHOP_CREATE_COUPON_URL + "?access_token=" + getAuthorizerAccessToken(appId, true);
    JsonObject jsonObject = couponInfo.toJsonObject();
    String response = getWxOpenService().post(url, jsonObject.toString());
    JsonObject respJson = GsonParser.parse(response);
    int couponId = -1;
    if (respJson.get(WxConsts.ERR_CODE).getAsInt() == 0) {
      JsonObject dataJson = respJson.get("data").getAsJsonObject();
      couponId = dataJson.get("coupon_id").getAsInt();
    }
    return couponId;
  }

  @Override
  public WxMinishopCouponStock minishopGetCouponList(String appId, String startCreateTime, String endCreateTime, Integer status, Integer page, Integer pageSize) throws WxErrorException {
    String url = MINISHOP_GET_COUPON_LIST + "?access_token=" + getAuthorizerAccessToken(appId, true);
    JsonObject jsonObject = new JsonObject();
    return null;
  }

  @Override
  public WxOpenResult minishopPushCouponToUser(String appId, String openId, Integer couponId) throws WxErrorException {
    String url = MINISHOP_PUSH_COUPON + "?access_token=" + getAuthorizerAccessToken(appId, true);
    JsonObject jsonObject = new JsonObject();

    jsonObject.addProperty("openid", openId);
    jsonObject.addProperty("coupon_id", couponId);

    String response = getWxOpenService().post(url, jsonObject.toString());

    return WxOpenGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  @Override
  public Integer minishopUpdateCoupon(String appId, WxMinishopCoupon couponInfo) throws WxErrorException {
    String url = MINISHOP_UPDATE_COUPON_URL + "?access_token=" + getAuthorizerAccessToken(appId, true);
    JsonObject jsonObject = couponInfo.toJsonObject();
    String response = getWxOpenService().post(url, jsonObject.toString());
    JsonObject respJson = GsonParser.parse(response);
    int couponId = -1;
    if (respJson.get(WxConsts.ERR_CODE).getAsInt() == 0) {
      JsonObject dataJson = respJson.get("data").getAsJsonObject();
      couponId = dataJson.get("coupon_id").getAsInt();
    }
    return couponId;
  }

  @Override
  public WxOpenResult minishopUpdateCouponStatus(String appId, Integer couponId, Integer status) throws WxErrorException {
    String url = MINISHOP_UPDATE_COUPON_STATUS_URL + "?access_token=" + getAuthorizerAccessToken(appId, true);
    JsonObject jsonObject = new JsonObject();

    jsonObject.addProperty("coupon_id", couponId);
    jsonObject.addProperty("status", status);

    String response = getWxOpenService().post(url, jsonObject.toString());

    return WxOpenGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  @Override
  public WxMinishopAddGoodsSpuResult<WxMinishopAddGoodsSpuData> minishopGoodsAddSpu(String appId, WxMinishopSpu spu) throws WxErrorException {
    String url = MINISHOP_ADD_SPU_URL + "?access_token=" + getAuthorizerAccessToken(appId, true);
    JsonObject jsonObject = spu.toJsonObject();

    String response = getWxOpenService().post(url, jsonObject.toString());

    JsonObject respObj = GsonParser.parse(response);
    WxMinishopAddGoodsSpuResult<WxMinishopAddGoodsSpuData> result = new WxMinishopAddGoodsSpuResult<>();
    result.setErrcode(respObj.get(WxConsts.ERR_CODE).getAsInt());

    if (result.getErrcode() == 0) {
      JsonObject dataObj = respObj.get("data").getAsJsonObject();
      WxMinishopAddGoodsSpuData resultData = new WxMinishopAddGoodsSpuData();
      resultData.setProductId(dataObj.get("product_id").getAsLong());
      resultData.setOutProductId(dataObj.get("out_product_id").getAsString());
      resultData.setCreateTime(dataObj.get("create_time").getAsString());
      result.setData(resultData);
    } else {
      result.setErrmsg(respObj.get("errmsg").getAsString());

    }
    return result;
  }

  @Override
  public WxOpenResult minishopGoodsDelSpu(String appId, Long productId, Long outProductId) throws WxErrorException {
    String url = MINISHOP_DEL_SPU_URL + "?access_token=" + getAuthorizerAccessToken(appId, true);
    JsonObject jsonObject = new JsonObject();

    jsonObject.addProperty("product_id", productId);
    jsonObject.addProperty("out_product_id", outProductId.toString());

    String response = getWxOpenService().post(url, jsonObject.toString());

    return WxOpenGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  @Override
  public WxMinishopAddGoodsSpuResult<WxMinishopAddGoodsSpuData> minishopGoodsUpdateSpu(String appId, WxMinishopSpu spu) throws WxErrorException {
    String url = MINISHOP_UPDATE_SPU_URL + "?access_token=" + getAuthorizerAccessToken(appId, true);
    JsonObject jsonObject = spu.toJsonObject();

    String response = getWxOpenService().post(url, jsonObject.toString());

    JsonObject respObj = GsonParser.parse(response);
    WxMinishopAddGoodsSpuResult<WxMinishopAddGoodsSpuData> result = new WxMinishopAddGoodsSpuResult<>();
    result.setErrcode(respObj.get(WxConsts.ERR_CODE).getAsInt());
    if (result.getErrcode() == 0) {
      JsonObject dataObj = respObj.get("data").getAsJsonObject();
      WxMinishopAddGoodsSpuData resultData = new WxMinishopAddGoodsSpuData();
      resultData.setProductId(dataObj.get("product_id").getAsLong());
      resultData.setOutProductId(dataObj.get("out_product_id").getAsString());
      resultData.setCreateTime(dataObj.get("update_time").getAsString());
      result.setData(resultData);
    } else {
      result.setErrmsg(respObj.get("errmsg").getAsString());
    }

    return result;
  }

  @Override
  public WxOpenResult minishopGoodsListingSpu(String appId, Long productId, Long outProductId) throws WxErrorException {
    String url = MINISHOP_LISTING_SPU_URL + "?access_token=" + getAuthorizerAccessToken(appId, true);
    JsonObject jsonObject = new JsonObject();

    jsonObject.addProperty("product_id", productId);
    jsonObject.addProperty("out_product_id", outProductId.toString());

    String response = getWxOpenService().post(url, jsonObject.toString());

    return WxOpenGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  @Override
  public WxOpenResult minishopGoodsDelistingSpu(String appId, Long productId, Long outProductId) throws WxErrorException {
    String url = MINISHOP_DELISTING_SPU_URL + "?access_token=" + getAuthorizerAccessToken(appId, true);
    JsonObject jsonObject = new JsonObject();

    jsonObject.addProperty("product_id", productId);
    jsonObject.addProperty("out_product_id", outProductId.toString());

    String response = getWxOpenService().post(url, jsonObject.toString());

    return WxOpenGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  @Override
  public WxMinishopAddGoodsSpuResult<WxMinishopAddGoodsSkuData> minishiopGoodsAddSku(String appId, WxMinishopSku sku) throws WxErrorException {
    String url = MINISHOP_ADD_SKU_URL + "?access_token=" + getAuthorizerAccessToken(appId, true);
    JsonObject jsonObject = sku.toJsonObject();

    String response = getWxOpenService().post(url, jsonObject.toString());

    JsonObject respObj = GsonParser.parse(response);
    WxMinishopAddGoodsSpuResult<WxMinishopAddGoodsSkuData> result = new WxMinishopAddGoodsSpuResult<>();
    result.setErrcode(respObj.get(WxConsts.ERR_CODE).getAsInt());
    if (result.getErrcode() == 0) {
      JsonObject dataObj = respObj.get("data").getAsJsonObject();
      WxMinishopAddGoodsSkuData resultData = new WxMinishopAddGoodsSkuData();
      resultData.setSkuId(dataObj.get("sku_id").getAsLong());
      resultData.setCreateTime(dataObj.get("create_time").getAsString());
      result.setData(resultData);
    } else {
      result.setErrmsg(respObj.get("errmsg").getAsString());
    }

    return result;
  }

  @Override
  public WxOpenResult minishopGoodsBatchAddSku(String appId, List<WxMinishopSku> skuList) throws WxErrorException {
    String url = MINISHOP_BATCH_ADD_SKU_URL + "?access_token=" + getAuthorizerAccessToken(appId, true);
    JsonObject jsonObject = new JsonObject();
    JsonArray jsonArray = new JsonArray();

    for (WxMinishopSku sku : skuList) {
      jsonArray.add(sku.toJsonObject());
    }

    jsonObject.add("skus", jsonArray);

    String response = getWxOpenService().post(url, jsonObject.toString());

    return WxOpenGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  @Override
  public WxOpenResult minishopGoodsDelSku(String appId, Long productId, Long outProductId, String outSkuId, Long skuId) throws WxErrorException {
    String url = MINISHOP_DEL_SKU_URL + "?access_token=" + getAuthorizerAccessToken(appId, true);
    JsonObject jsonObject = new JsonObject();

    jsonObject.addProperty("product_id", productId);
    jsonObject.addProperty("out_product_id", outProductId);
    jsonObject.addProperty("sku_id", skuId);
    jsonObject.addProperty("out_sku_id", outSkuId);

    String response = getWxOpenService().post(url, jsonObject.toString());

    return WxOpenGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  @Override
  public WxOpenResult minishopGoodsUpdateSku(String appId, WxMinishopSku sku) throws WxErrorException {
    String url = MINISHOP_UPDATE_SKU_URL + "?access_token=" + getAuthorizerAccessToken(appId, true);
    JsonObject jsonObject = sku.toJsonObject();

    String response = getWxOpenService().post(url, jsonObject.toString());

    return WxOpenGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  @Override
  public WxOpenResult minishopGoodsUpdateSkuPrice(String appId, Long productId, Long outProductId, String outSkuId, Long skuId, Long salePrice, Long marketPrice) throws WxErrorException {
    String url = MINISHOP_UPDATE_SKU_PRICE_URL + "?access_token=" + getAuthorizerAccessToken(appId, true);
    JsonObject jsonObject = new JsonObject();

    jsonObject.addProperty("product_id", productId);
    jsonObject.addProperty("out_product_id", outProductId);
    jsonObject.addProperty("sku_id", skuId);
    jsonObject.addProperty("out_sku_id", outSkuId);
    jsonObject.addProperty("sale_price", outSkuId);
    jsonObject.addProperty("market_price", outSkuId);


    String response = getWxOpenService().post(url, jsonObject.toString());

    return WxOpenGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  @Override
  public WxOpenResult minishopGoodsUpdateSkuStock(String appId, Long productId, Long outProductId, String outSkuId, Long skuId, Integer type, Integer stockNum) throws WxErrorException {
    String url = MINISHOP_UPDATE_SKU_STOCK_URL + "?access_token=" + getAuthorizerAccessToken(appId, true);
    JsonObject jsonObject = new JsonObject();

    jsonObject.addProperty("product_id", productId);
    jsonObject.addProperty("out_product_id", outProductId);
    jsonObject.addProperty("sku_id", skuId);
    jsonObject.addProperty("out_sku_id", outSkuId);
    jsonObject.addProperty("type", type);
    jsonObject.addProperty("stock_num", stockNum);


    String response = getWxOpenService().post(url, jsonObject.toString());

    return WxOpenGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  @Override
  public String minishopCommonPost(String appId, String url, String requestParam) throws WxErrorException {

    return null;
  }

  @Override
  public Integer addLimitDiscountGoods(String appId, LimitDiscountGoods limitDiscountGoods) throws WxErrorException {
    String url = API_MINISHOP_ADD_LIMIT_DISCOUNT_URL + "access_token=" + getAuthorizerAccessToken(appId, false);
    JsonObject jsonObject = limitDiscountGoods.toJsonObject();
    String response = getWxOpenService().post(url, jsonObject.toString());
    JsonObject respObj = GsonParser.parse(response);
    int taskId = 0;
    if (respObj.get(WxConsts.ERR_CODE).getAsInt() == 0) {
      taskId = respObj.get("task_id").getAsInt();
    }
    return taskId;
  }

  @Override
  public List<LimitDiscountGoods> getLimitDiscountList(String appId, Integer status) throws WxErrorException {
    String url = API_MINISHOP_GET_LIMIT_DISCOUNT_URL + "access_token=" + getAuthorizerAccessToken(appId, false);
    JsonObject jsonObject = new JsonObject();
    if (status != null) {
      jsonObject.addProperty("status", status);
    }
    String response = getWxOpenService().post(url, jsonObject.toString());
    JsonObject respObj = GsonParser.parse(response);
    List<LimitDiscountGoods> limitDiscountGoodsList = new ArrayList<>();
    if (respObj.get(WxConsts.ERR_CODE).getAsInt() == 0) {
      //成功获取到秒杀活动列表

      JsonArray jsonArray = respObj.get("limited_discount_list").getAsJsonArray();
      if (jsonArray != null && !jsonArray.isEmpty()) {
        for (int i = 0; i < jsonArray.size(); i++) {
          JsonObject goodsObj = jsonArray.get(i).getAsJsonObject();
          LimitDiscountGoods discountGoods = new LimitDiscountGoods();
          discountGoods.setTaskId(goodsObj.get("task_id").getAsLong());
          discountGoods.setStatus(goodsObj.get("status").getAsInt());
          discountGoods.setStartTime(new Date(goodsObj.get("start_time").getAsLong() * 1000));
          discountGoods.setEndTime(new Date(goodsObj.get("end_time").getAsLong() * 1000));

          List<LimitDiscountSku> skuList = new ArrayList<>();
          JsonArray skuArray = goodsObj.get("limited_discount_sku_list").getAsJsonArray();
          if (skuArray != null && !skuArray.isEmpty()) {
            for (int j = 0; j < skuArray.size(); j++) {
              JsonObject skuObj = skuArray.get(i).getAsJsonObject();
              LimitDiscountSku sku = new LimitDiscountSku();
              sku.setSkuId(skuObj.get("sku_id").getAsLong());
              sku.setSalePrice(BigDecimal.valueOf(skuObj.get("sale_price").getAsDouble() / 100));
              sku.setSaleStock(skuObj.get("sale_stock").getAsInt());
              skuList.add(sku);
            }

            discountGoods.setLimitDiscountSkuList(skuList);
          }

          limitDiscountGoodsList.add(discountGoods);
        }
      }
    }
    return limitDiscountGoodsList;
  }

  @Override
  public WxOpenResult updateLimitDiscountStatus(String appId, Long taskId, Integer status) throws WxErrorException {
    String url = API_MINISHOP_UPDATE_LIMIT_DICOUNT_STATUS_URL + "access_token=" + getAuthorizerAccessToken(appId, false);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("task_id", taskId);
    jsonObject.addProperty("status", status);
    String response = getWxOpenService().post(url, jsonObject.toString());

    return WxOpenGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  @Override
  public GetShareCloudBaseEnvResponse getShareCloudBaseEnv(List<String> appids) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    JsonArray jsonArray = new JsonArray();
    for (String appId : appids) {
      jsonArray.add(appId);
    }
    jsonObject.add("appids", jsonArray);
    String response = post(BATCH_GET_ENVID_URL, jsonObject.toString());
    return WxOpenGsonBuilder.create().fromJson(response, GetShareCloudBaseEnvResponse.class);
  }

  @Override
  public GetTcbEnvListResponse getTcbEnvList() throws WxErrorException {
    String response = post(DESCRIBE_ENVS_URL, new JsonObject().toString());
    return WxOpenGsonBuilder.create().fromJson(response, GetTcbEnvListResponse.class);
  }

  @Override
  public WxOpenResult changeTcbEnv(String env) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("env", env);
    String response = post(MODIFY_ENV_URL, jsonObject.toString());
    return WxOpenGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  @Override
  public ShareCloudBaseEnvResponse shareCloudBaseEnv(ShareCloudBaseEnvRequest request) throws WxErrorException {
    Gson gson = new Gson();
    String response = post(BATCH_SHARE_ENV, gson.toJson(request));
    return WxOpenGsonBuilder.create().fromJson(response, ShareCloudBaseEnvResponse.class);
  }

  @Override
  public WxOpenResult clearQuotaV2(String appid) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("appid", appid);
    jsonObject.addProperty("component_appid", getWxOpenConfigStorage().getComponentAppId());
    jsonObject.addProperty("appsecret", getWxOpenConfigStorage().getComponentAppSecret());
    String response = getWxOpenService().post(COMPONENT_CLEAR_QUOTA_URL, jsonObject.toString());
    return WxOpenResult.fromJson(response);
  }

  /**
   * 申请设置订单页path信息
   * 注意：一次提交不超过100个appid
   *
   * @param info 订单页path信息
   * @return .
   * @throws WxErrorException .
   */
  @Override
  public WxOpenResult applySetOrderPathInfo(WxOpenMaApplyOrderPathInfo info) throws WxErrorException {
    Gson gson = new Gson();
    String response = post(OPEN_APPLY_SET_ORDER_PATH_INFO, gson.toJson(info));
    return WxOpenGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  @Override
  public WxOpenMaDomainResult modifyWxaServerDomain(String action, List<String> requestDomains, List<String> wsRequestDomains,
                                                    List<String> uploadDomains, List<String> downloadDomains,
                                                    List<String> udpDomains, List<String> tcpDomains) throws WxErrorException {
    JsonObject requestJson = new JsonObject();
    requestJson.addProperty("action", action);
    if (!"get".equals(action)) {
      requestJson.add("requestdomain", toJsonArray(requestDomains));
      requestJson.add("wsrequestdomain", toJsonArray(wsRequestDomains));
      requestJson.add("uploaddomain", toJsonArray(uploadDomains));
      requestJson.add("downloaddomain", toJsonArray(downloadDomains));
      requestJson.add("udpdomain", toJsonArray(udpDomains));
      requestJson.add("tcpdomain", toJsonArray(tcpDomains));
    }

    String response = post(API_MODIFY_WXA_SERVER_DOMAIN, requestJson.toString());
    return WxOpenGsonBuilder.create().fromJson(response, WxOpenMaDomainResult.class);
  }

  @Override
  public WxOpenMaDomainConfirmFileResult getDomainConfirmFile() throws WxErrorException {
    String responseContent = post(API_GET_DOMAIN_CONFIRM_FILE, "{}");
    return WxOpenMaDomainConfirmFileResult.fromJson(responseContent);
  }

  @Override
  public String modifyWxaJumpDomain(String action, List<String> domainList) throws WxErrorException {
    JsonObject requestJson = new JsonObject();
    requestJson.addProperty("action", action);
    if (!"get".equals(action)) {
      requestJson.add("webviewdomain", toJsonArray(domainList));
    }
    return post(API_MODIFY_WXA_JUMP_DOMAIN, requestJson.toString());
  }

  @Override
  public WxOpenMaWebDomainResult modifyWxaJumpDomainInfo(String action, List<String> domainList) throws WxErrorException {
    String response = this.modifyWxaJumpDomain(action, domainList);
    return WxOpenGsonBuilder.create().fromJson(response, WxOpenMaWebDomainResult.class);
  }

  private JsonArray toJsonArray(List<String> list) {
    JsonArray jsonArray = new JsonArray();
    if (list != null) {
      for (String item : list) {
        jsonArray.add(item);
      }
    }
    return jsonArray;
  }
}
