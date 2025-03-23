package me.chanjar.weixin.open.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.bean.WxMaAuditMediaUploadResult;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUploadAuthMaterialResult;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.executor.UploadAuthMaterialRequestExecutor;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.Getter;
import me.chanjar.weixin.common.bean.CommonUploadParam;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.open.api.*;
import me.chanjar.weixin.open.bean.ma.WxMaPrefetchDomain;
import me.chanjar.weixin.open.bean.ma.WxMaQrcodeParam;
import me.chanjar.weixin.open.bean.ma.WxMaScheme;
import me.chanjar.weixin.open.bean.message.WxOpenMaSubmitAuditMessage;
import me.chanjar.weixin.open.bean.message.WxOpenMaVerifyBetaWeappMessage;
import me.chanjar.weixin.open.bean.result.*;
import me.chanjar.weixin.open.executor.MaQrCodeRequestExecutor;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 *     增加开放平台代小程序管理服务能力
 *     说明：这里让这个服务公开便于调用者模拟本地测试服务
 * </pre>
 *
 * @author <a href="https://github.com/007gzs">007</a>
 * @author yqx
 * created on  2018-09-12
 */
public class WxOpenMaServiceImpl extends WxMaServiceImpl implements WxOpenMaService {
  private static final String ACTION = "action";
  private static final String ACTION_GET = "get";
  private final WxOpenComponentService wxOpenComponentService;
  private final WxMaConfig wxMaConfig;
  private final String appId;
  @Getter
  private final WxOpenMaBasicService basicService;
  @Getter
  private final WxOpenMaAuthService authService;
  @Getter
  private final WxOpenMaIcpService icpService;
  @Getter
  private final WxOpenMaPrivacyService privacyService;
  @Getter
  private final WxOpenMaShoppingOrdersService shoppingOrdersService;
  @Getter
  private final WxOpenMaEmbeddedService embeddedService;

  public WxOpenMaServiceImpl(WxOpenComponentService wxOpenComponentService, String appId, WxMaConfig wxMaConfig) {
    this.wxOpenComponentService = wxOpenComponentService;
    this.appId = appId;
    this.wxMaConfig = wxMaConfig;
    this.basicService = new WxOpenMaBasicServiceImpl(this);
    this.authService = new WxOpenMaAuthServiceImpl(this);
    this.icpService = new WxOpenMaIcpServiceImpl(this);
    this.privacyService = new WxOpenMaPrivacyServiceImpl(this);
    this.shoppingOrdersService = new WxOpenMaShoppingOrdersServiceImpl(this);
    this.embeddedService = new WxOpenMaEmbeddedServiceImpl(this);
    initHttp();
  }

  @Override
  public WxMaJscode2SessionResult jsCode2SessionInfo(String jsCode) throws WxErrorException {
    return wxOpenComponentService.miniappJscode2Session(appId, jsCode);
  }

  @Override
  public WxMaConfig getWxMaConfig() {
    return wxMaConfig;
  }

  @Override
  public String getAccessToken(boolean forceRefresh) throws WxErrorException {
    return wxOpenComponentService.getAuthorizerAccessToken(appId, forceRefresh);
  }

  @Override
  public WxOpenMaDomainResult getDomain() throws WxErrorException {
    return modifyDomain(ACTION_GET, null, null, null,
      null, null, null);
  }

  @Override
  public WxOpenMaDomainResult modifyDomain(String action, List<String> requestDomains, List<String> wsRequestDomains,
                                           List<String> uploadDomains, List<String> downloadDomains,
                                           List<String> udpDomains, List<String> tcpDomains) throws WxErrorException {
    JsonObject requestJson = new JsonObject();
    requestJson.addProperty(ACTION, action);
    if (!ACTION_GET.equals(action)) {
      requestJson.add("requestdomain", toJsonArray(requestDomains));
      requestJson.add("wsrequestdomain", toJsonArray(wsRequestDomains));
      requestJson.add("uploaddomain", toJsonArray(uploadDomains));
      requestJson.add("downloaddomain", toJsonArray(downloadDomains));
      requestJson.add("udpdomain", toJsonArray(udpDomains));
      requestJson.add("tcpdomain", toJsonArray(tcpDomains));
    }

    String response = post(API_MODIFY_DOMAIN, GSON.toJson(requestJson));
    return WxMaGsonBuilder.create().fromJson(response, WxOpenMaDomainResult.class);
  }

  @Override
  public String getWebViewDomain() throws WxErrorException {
    return setWebViewDomain(ACTION_GET, null);
  }

  @Override
  public WxOpenMaWebDomainResult getWebViewDomainInfo() throws WxErrorException {
    return setWebViewDomainInfo(ACTION_GET, null);
  }

  @Override
  public String setWebViewDomain(String action, List<String> domainList) throws WxErrorException {
    JsonObject requestJson = new JsonObject();
    requestJson.addProperty(ACTION, action);
    if (!ACTION_GET.equals(action)) {
      requestJson.add("webviewdomain", toJsonArray(domainList));
    }
    return post(API_SET_WEBVIEW_DOMAIN, GSON.toJson(requestJson));
  }


  @Override
  public WxOpenMaWebDomainResult setWebViewDomainInfo(String action, List<String> domainList) throws WxErrorException {
    String response = this.setWebViewDomain(action, domainList);
    return WxMaGsonBuilder.create().fromJson(response, WxOpenMaWebDomainResult.class);
  }

  @Override
  public WxOpenMaDomainConfirmFileResult getWebviewDomainConfirmFile() throws WxErrorException {
    String responseContent = post(API_GET_WEBVIEW_DOMAIN_CONFIRM_FILE, "{}");
    return WxOpenMaDomainConfirmFileResult.fromJson(responseContent);
  }

  @Override
  public String getAccountBasicInfo() throws WxErrorException {
    return get(API_GET_ACCOUNT_BASICINFO, "");
  }


  @Override
  public WxOpenMaBindTesterResult bindTester(String wechatId) throws WxErrorException {
    JsonObject paramJson = new JsonObject();
    paramJson.addProperty("wechatid", wechatId);
    String response = post(API_BIND_TESTER, GSON.toJson(paramJson));
    return WxMaGsonBuilder.create().fromJson(response, WxOpenMaBindTesterResult.class);
  }

  @Override
  public WxOpenResult unbindTester(String wechatId) throws WxErrorException {
    JsonObject paramJson = new JsonObject();
    paramJson.addProperty("wechatid", wechatId);
    String response = post(API_UNBIND_TESTER, GSON.toJson(paramJson));
    return WxMaGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  @Override
  public WxOpenResult unbindTesterByUserStr(String userStr) throws WxErrorException {
    JsonObject paramJson = new JsonObject();
    paramJson.addProperty("userstr", userStr);
    String response = post(API_UNBIND_TESTER, GSON.toJson(paramJson));
    return WxMaGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  @Override
  public WxOpenMaTesterListResult getTesterList() throws WxErrorException {
    JsonObject paramJson = new JsonObject();
    paramJson.addProperty(ACTION, "get_experiencer");
    String response = post(API_GET_TESTERLIST, GSON.toJson(paramJson));
    return WxMaGsonBuilder.create().fromJson(response, WxOpenMaTesterListResult.class);
  }

  @Override
  public WxOpenResult changeWxaSearchStatus(Integer status) throws WxErrorException {
    JsonObject paramJson = new JsonObject();
    paramJson.addProperty("status", status);
    String response = post(API_CHANGE_WXA_SEARCH_STATUS, GSON.toJson(paramJson));
    return WxMaGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  @Override
  public WxOpenMaSearchStatusResult getWxaSearchStatus() throws WxErrorException {
    String response = get(API_GET_WXA_SEARCH_STATUS, null);
    return WxMaGsonBuilder.create().fromJson(response, WxOpenMaSearchStatusResult.class);
  }

  @Override
  public WxOpenMaShowItemResult getShowWxaItem() throws WxErrorException {
    String response = get(API_GET_SHOW_WXA_ITEM, null);
    return WxMaGsonBuilder.create().fromJson(response, WxOpenMaShowItemResult.class);
  }

  @Override
  public WxOpenResult updateShowWxaItem(Integer flag, String mpAppId) throws WxErrorException {
    JsonObject paramJson = new JsonObject();
    paramJson.addProperty("wxa_subscribe_biz_flag", flag);
    paramJson.addProperty("appid", mpAppId);
    String response = post(API_UPDATE_SHOW_WXA_ITEM, GSON.toJson(paramJson));
    return WxMaGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  @Override
  public WxOpenResult codeCommit(Long templateId, String userVersion, String userDesc, Object extJsonObject) throws WxErrorException {
    JsonObject params = new JsonObject();
    params.addProperty("template_id", templateId);
    params.addProperty("user_version", userVersion);
    params.addProperty("user_desc", userDesc);
    //注意：ext_json必须是字符串类型
    params.addProperty("ext_json", GSON.toJson(extJsonObject));
    String response = post(API_CODE_COMMIT, GSON.toJson(params));
    return WxMaGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  @Override
  public File getTestQrcode(String pagePath, Map<String, String> params) throws WxErrorException {
    WxMaQrcodeParam qrcodeParam = WxMaQrcodeParam.create(pagePath);
    qrcodeParam.addPageParam(params);
    WxMaService wxMaService = this;
    return wxMaService.execute(MaQrCodeRequestExecutor.create(getRequestHttp()), API_TEST_QRCODE, qrcodeParam);
  }

  @Override
  public WxOpenResult verifyBetaWeapp(WxOpenMaVerifyBetaWeappMessage verifyBetaWeappMessage) throws WxErrorException {
    String response = post(API_VERIFY_BETA_WEAPP, GSON.toJson(verifyBetaWeappMessage));
    return WxMaGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  @Override
  public WxOpenMaCategoryListResult getCategoryList() throws WxErrorException {
    String response = get(API_GET_CATEGORY, null);
    return WxMaGsonBuilder.create().fromJson(response, WxOpenMaCategoryListResult.class);
  }

  @Override
  public WxOpenMaPageListResult getPageList() throws WxErrorException {
    String response = get(API_GET_PAGE, null);
    return WxMaGsonBuilder.create().fromJson(response, WxOpenMaPageListResult.class);
  }

  @Override
  public WxOpenMaSubmitAuditResult submitAudit(WxOpenMaSubmitAuditMessage submitAuditMessage) throws WxErrorException {
    String response = post(API_SUBMIT_AUDIT, GSON.toJson(submitAuditMessage));
    return WxMaGsonBuilder.create().fromJson(response, WxOpenMaSubmitAuditResult.class);
  }

  @Override
  public WxOpenMaQueryAuditResult getAuditStatus(Long auditId) throws WxErrorException {
    JsonObject params = new JsonObject();
    params.addProperty("auditid", auditId);
    String response = post(API_GET_AUDIT_STATUS, GSON.toJson(params));
    return WxMaGsonBuilder.create().fromJson(response, WxOpenMaQueryAuditResult.class);
  }

  @Override
  public WxOpenMaQueryAuditResult getLatestAuditStatus() throws WxErrorException {
    String response = get(API_GET_LATEST_AUDIT_STATUS, null);
    return WxMaGsonBuilder.create().fromJson(response, WxOpenMaQueryAuditResult.class);
  }

  @Override
  public WxOpenResult releaseAudited() throws WxErrorException {
    JsonObject params = new JsonObject();
    String response = post(API_RELEASE, GSON.toJson(params));
    return WxMaGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  @Override
  public WxOpenResult changeVisitStatus(String action) throws WxErrorException {
    JsonObject params = new JsonObject();
    params.addProperty(ACTION, action);
    String response = post(API_CHANGE_VISITSTATUS, GSON.toJson(params));
    return WxMaGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  @Override
  public WxOpenMaVisitStatusResult getVisitStatus() throws WxErrorException {
    String responseContent = post(API_GET_VISITSTATUS, "{}");
    return WxOpenMaVisitStatusResult.fromJson(responseContent);
  }

  @Override
  public WxOpenResult revertCodeRelease() throws WxErrorException {
    String response = get(API_REVERT_CODE_RELEASE, null);
    return WxMaGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  @Override
  public WxOpenMaHistoryVersionResult getHistoryVersion() throws WxErrorException {
    String response = get(API_REVERT_CODE_RELEASE, "action=get_history_version");
    return WxMaGsonBuilder.create().fromJson(response, WxOpenMaHistoryVersionResult.class);
  }

  @Override
  public WxOpenResult undoCodeAudit() throws WxErrorException {
    String response = get(API_UNDO_CODE_AUDIT, null);
    return WxMaGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  @Override
  public String getSupportVersion() throws WxErrorException {
    JsonObject params = new JsonObject();
    return post(API_GET_WEAPP_SUPPORT_VERSION, GSON.toJson(params));
  }

  @Override
  public WxOpenMaWeappSupportVersionResult getSupportVersionInfo() throws WxErrorException {
    String response = this.getSupportVersion();
    return WxMaGsonBuilder.create().fromJson(response, WxOpenMaWeappSupportVersionResult.class);
  }

  @Override
  public String setSupportVersion(String version) throws WxErrorException {
    JsonObject params = new JsonObject();
    params.addProperty("version", version);
    return post(API_SET_WEAPP_SUPPORT_VERSION, GSON.toJson(params));
  }

  @Override
  public WxOpenResult setSupportVersionInfo(String version) throws WxErrorException {
    String response = this.setSupportVersion(version);
    return WxMaGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  @Override
  public WxOpenResult grayRelease(Integer grayPercentage) throws WxErrorException {
    JsonObject params = new JsonObject();
    params.addProperty("gray_percentage", grayPercentage);
    String response = post(API_GRAY_RELEASE, GSON.toJson(params));
    return WxMaGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  @Override
  public WxOpenResult revertGrayRelease() throws WxErrorException {
    String response = get(API_REVERT_GRAY_RELEASE, null);
    return WxMaGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  @Override
  public WxOpenMaGrayReleasePlanResult getGrayReleasePlan() throws WxErrorException {
    String response = get(API_GET_GRAY_RELEASE_PLAN, null);
    return WxMaGsonBuilder.create().fromJson(response, WxOpenMaGrayReleasePlanResult.class);
  }

  @Override
  public WxOpenMaGetCodePrivacyInfoResult getCodePrivacyInfo() throws WxErrorException {
    String response = get(API_GET_CODE_PRIVACY_INFO, null);
    return WxMaGsonBuilder.create().fromJson(response, WxOpenMaGetCodePrivacyInfoResult.class);
  }

  @Override
  public WxOpenMaQueryQuotaResult queryQuota() throws WxErrorException {
    String response = get(API_QUERY_QUOTA, null);
    return WxMaGsonBuilder.create().fromJson(response, WxOpenMaQueryQuotaResult.class);
  }

  @Override
  public Boolean speedAudit(Long auditId) throws WxErrorException {
    JsonObject params = new JsonObject();
    params.addProperty("auditid", auditId);
    String response = post(API_SPEED_AUDIT, GSON.toJson(params));
    WxOpenResult result = WxMaGsonBuilder.create().fromJson(response, WxOpenResult.class);
    return result.isSuccess();
  }


  @Override
  public WxOpenResult addQrcodeJump(WxQrcodeJumpRule wxQrcodeJumpRule) throws WxErrorException {
    String response = post(API_QRCODE_JUMP_ADD, GSON.toJson(wxQrcodeJumpRule));
    return WxMaGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  @Override
  public WxGetQrcodeJumpResult getQrcodeJump() throws WxErrorException {
    String response = post(API_QRCODE_JUMP_GET, "{}");
    return WxMaGsonBuilder.create().fromJson(response, WxGetQrcodeJumpResult.class);
  }

  @Override
  public WxDownlooadQrcodeJumpResult downloadQrcodeJump() throws WxErrorException {
    String response = post(API_QRCODE_JUMP_DOWNLOAD, "{}");
    return WxMaGsonBuilder.create().fromJson(response, WxDownlooadQrcodeJumpResult.class);
  }

  @Override
  public WxOpenResult deleteQrcodeJump(String prefix) throws WxErrorException {
    JsonObject params = new JsonObject();
    params.addProperty("prefix", prefix);
    String response = post(API_QRCODE_JUMP_DELETE, GSON.toJson(params));
    return WxMaGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  @Override
  public WxOpenResult publishQrcodeJump(String prefix) throws WxErrorException {
    JsonObject params = new JsonObject();
    params.addProperty("prefix", prefix);
    String response = post(API_QRCODE_JUMP_PUBLISH, GSON.toJson(params));
    return WxMaGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  @Override
  public WxMaScheme generateMaScheme(String jumpWxaPath, String jumpWxaQuery, Boolean isExpire, Long expireTime) throws WxErrorException {
    JsonObject jumpWxa = null;
    if (jumpWxaPath != null && jumpWxaQuery != null) {
      jumpWxa = new JsonObject();
      jumpWxa.addProperty("path", jumpWxaPath);
      jumpWxa.addProperty("query", jumpWxaQuery);
    }

    JsonObject params = new JsonObject();
    if (jumpWxa != null) {
      params.add("jump_wxa", jumpWxa);
    }
    if (isExpire != null) {
      params.addProperty("is_expire", isExpire);
    }
    if (expireTime != null) {
      params.addProperty("expire_time", expireTime);
    }

    Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    String response = post(API_GENERATE_SCHEME, gson.toJson(params));

    return WxMaGsonBuilder.create().fromJson(response, WxMaScheme.class);
  }

  @Override
  public WxOpenResult registerShopComponent() throws WxErrorException {
    JsonObject params = new JsonObject();
    String response = post(API_REGISTER_SHOP_COMPONENT, GSON.toJson(params));
    return WxMaGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  @Override
  public WxMaAuditMediaUploadResult uploadMedia(File file) throws WxErrorException {
    CommonUploadParam param = CommonUploadParam.fromFile("media", file);
    String result = upload(API_AUDIT_UPLOAD_MEDIA, param);
    return WxMaAuditMediaUploadResult.fromJson(result);
  }

  private JsonArray toJsonArray(List<String> strList) {
    JsonArray jsonArray = new JsonArray();
    if (strList != null && !strList.isEmpty()) {
      for (String str : strList) {
        jsonArray.add(str);
      }
    }
    return jsonArray;
  }

  @Override
  public WxOpenVersioninfoResult getVersionInfo() throws WxErrorException {
    JsonObject params = new JsonObject();
    String response = post(API_GET_VERSION_INFO, GSON.toJson(params));
    return WxMaGsonBuilder.create().fromJson(response, WxOpenVersioninfoResult.class);
  }

  @Override
  public WxOpenResult setPrefetchDomain(WxMaPrefetchDomain domain) throws WxErrorException {
    String response = post(API_WX_SET_PREFETCH_DOMAIN, GSON.toJson(domain));
    return WxMaGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  @Override
  public WxOpenMaPrefetchDomainResult getPrefetchDomain() throws WxErrorException {
    String response = get(API_GET_PREFETCH_DOMAIN, null);
    return WxMaGsonBuilder.create().fromJson(response, WxOpenMaPrefetchDomainResult.class);
  }

  @Override
  public WxOpenMaApplyLiveInfoResult applyLiveInfo() throws WxErrorException {
    JsonObject params = new JsonObject();
    params.addProperty(ACTION, "apply");
    String response = post(API_WX_APPLY_LIVE_INFO, GSON.toJson(params));
    return WxMaGsonBuilder.create().fromJson(response, WxOpenMaApplyLiveInfoResult.class);
  }

  @Override
  public WxMaUploadAuthMaterialResult uploadAuthMaterial(File file) throws WxErrorException {
    return (WxMaUploadAuthMaterialResult) this.execute(UploadAuthMaterialRequestExecutor.create(getRequestHttp()), API_UPLOAD_AUTH_MATERIAL, file);
  }
}
