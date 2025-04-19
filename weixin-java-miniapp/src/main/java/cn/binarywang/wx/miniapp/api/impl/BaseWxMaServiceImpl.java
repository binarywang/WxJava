package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.*;
import cn.binarywang.wx.miniapp.bean.WxMaApiResponse;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.executor.ApiSignaturePostRequestExecutor;
import cn.binarywang.wx.miniapp.util.WxMaConfigHolder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.PSSParameterSpec;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.function.Function;
import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.CommonUploadParam;
import me.chanjar.weixin.common.bean.ToJson;
import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.error.WxRuntimeException;
import me.chanjar.weixin.common.executor.CommonUploadRequestExecutor;
import me.chanjar.weixin.common.service.WxImgProcService;
import me.chanjar.weixin.common.service.WxOcrService;
import me.chanjar.weixin.common.util.DataUtils;
import me.chanjar.weixin.common.util.crypto.SHA1;
import me.chanjar.weixin.common.util.http.*;
import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import org.apache.commons.lang3.StringUtils;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @see #doGetAccessTokenRequest
 */
@Slf4j
public abstract class BaseWxMaServiceImpl<H, P> implements WxMaService, RequestHttp<H, P> {
  /**
   * 开启API签名验证后需要API签名的接口，根据 https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/
   * 整理，uri包含下这些字符串且配置了api signature aes ras key 自动用签名接口
   */
  protected static final String[] urlPathSupportApiSignature =
      new String[] {
        "cgi-bin/clear_quota",
        "cgi-bin/openapi/quota/get",
        "cgi-bin/openapi/rid/get",
        "wxa/getpluginopenpid",
        "wxa/business/checkencryptedmsg",
        "wxa/business/getuserencryptkey",
        "wxa/business/getuserphonenumber",
        "wxa/getwxacode",
        "wxa/getwxacodeunlimit",
        "cgi-bin/wxaapp/createwxaqrcode",
        "cgi-bin/message/custom/send",
        "cgi-bin/message/wxopen/updatablemsg/send",
        "wxaapi/newtmpl/deltemplate",
        "cgi-bin/message/subscribe/send",
        "wxaapi/newtmpl/addtemplate",
        "wxa/msg_sec_check",
        "wxa/media_check_async",
        "wxa/getuserriskrank",
        "datacube/getweanalysisappidweeklyretaininfo",
        "datacube/getweanalysisappidmonthlyretaininfo",
        "datacube/getweanalysisappiddailyretaininfo",
        "datacube/getweanalysisappidmonthlyvisittrend",
        "datacube/getweanalysisappiddailyvisittrend",
        "datacube/getweanalysisappidweeklyvisittrend",
        "datacube/getweanalysisappiddailysummarytrend",
        "datacube/getweanalysisappidvisitpage",
        "datacube/getweanalysisappiduserportrait",
        "wxa/business/performance/boot",
        "datacube/getweanalysisappidvisitdistribution",
        "wxa/getwxadevinfo",
        "wxaapi/log/get_performance",
        "wxaapi/log/jserr_detail",
        "wxaapi/log/jserr_list",
        "wxa/devplugin",
        "wxa/plugin",
        "cgi-bin/express/business/account/getall",
        "cgi-bin/express/business/delivery/getall",
        "cgi-bin/express/business/printer/getall",
        "wxa/servicemarket",
        "cgi-bin/soter/verify_signature"
      };

  protected static final Gson GSON = new Gson();
  private final WxMaMsgService kefuService = new WxMaMsgServiceImpl(this);
  private final WxMaMediaService materialService = new WxMaMediaServiceImpl(this);
  private final WxMaUserService userService = new WxMaUserServiceImpl(this);
  private final WxMaQrcodeService qrCodeService = new WxMaQrcodeServiceImpl(this);
  private final WxMaSchemeService schemeService = new WxMaSchemeServiceImpl(this);
  private final WxMaAnalysisService analysisService = new WxMaAnalysisServiceImpl(this);
  private final WxMaCodeService codeService = new WxMaCodeServiceImpl(this);
  private final WxMaInternetService internetService = new WxMaInternetServiceImpl(this);
  private final WxMaSettingService settingService = new WxMaSettingServiceImpl(this);
  private final WxMaJsapiService jsapiService = new WxMaJsapiServiceImpl(this);
  private final WxMaShareService shareService = new WxMaShareServiceImpl(this);
  private final WxMaRunService runService = new WxMaRunServiceImpl(this);
  private final WxMaSecurityService securityService = new WxMaSecurityServiceImpl(this);
  private final WxMaPluginService pluginService = new WxMaPluginServiceImpl(this);
  private final WxMaExpressService expressService = new WxMaExpressServiceImpl(this);
  private final WxMaSubscribeService subscribeService = new WxMaSubscribeServiceImpl(this);
  private final WxMaCloudService cloudService = new WxMaCloudServiceImpl(this);
  private final WxMaLiveService liveService = new WxMaLiveServiceImpl(this);
  private final WxMaLiveGoodsService liveGoodsService = new WxMaLiveGoodsServiceImpl(this);
  private final WxMaLiveMemberService liveMemberService = new WxMaLiveMemberServiceImpl(this);
  private final WxOcrService ocrService = new WxMaOcrServiceImpl(this);
  private final WxImgProcService imgProcService = new WxMaImgProcServiceImpl(this);
  private final WxMaShopSpuService shopSpuService = new WxMaShopSpuServiceImpl(this);
  private final WxMaShopOrderService shopOrderService = new WxMaShopOrderServiceImpl(this);
  private final WxMaShopRegisterService shopRegisterService = new WxMaShopRegisterServiceImpl(this);
  private final WxMaShopAccountService shopAccountService = new WxMaShopAccountServiceImpl(this);
  private final WxMaShopCatService shopCatService = new WxMaShopCatServiceImpl(this);
  private final WxMaShopImgService shopImgService = new WxMaShopImgServiceImpl(this);
  private final WxMaShopAuditService shopAuditService = new WxMaShopAuditServiceImpl(this);
  private final WxMaShopAfterSaleService shopAfterSaleService =
      new WxMaShopAfterSaleServiceImpl(this);
  private final WxMaShopDeliveryService shopDeliveryService = new WxMaShopDeliveryServiceImpl(this);
  private final WxMaLinkService linkService = new WxMaLinkServiceImpl(this);
  private final WxMaReimburseInvoiceService reimburseInvoiceService =
      new WxMaReimburseInvoiceServiceImpl(this);
  private final WxMaDeviceSubscribeService deviceSubscribeService =
      new WxMaDeviceSubscribeServiceImpl(this);
  private final WxMaMarketingService marketingService = new WxMaMarketingServiceImpl(this);
  private final WxMaImmediateDeliveryService immediateDeliveryService =
      new WxMaImmediateDeliveryServiceImpl(this);
  private final WxMaShopSharerService shopSharerService = new WxMaShopSharerServiceImpl(this);
  private final WxMaProductService productService = new WxMaProductServiceImpl(this);
  private final WxMaProductOrderService productOrderService = new WxMaProductOrderServiceImpl(this);
  private final WxMaShopCouponService wxMaShopCouponService = new WxMaShopCouponServiceImpl(this);
  private final WxMaShopPayService wxMaShopPayService = new WxMaShopPayServiceImpl(this);

  private final WxMaOrderShippingService wxMaOrderShippingService =
      new WxMaOrderShippingServiceImpl(this);

  private final WxMaOrderManagementService wxMaOrderManagementService =
      new WxMaOrderManagementServiceImpl(this);

  private final WxMaOpenApiService wxMaOpenApiService = new WxMaOpenApiServiceImpl(this);
  private final WxMaVodService wxMaVodService = new WxMaVodServiceImpl(this);
  private final WxMaXPayService wxMaXPayService = new WxMaXPayServiceImpl(this);
  private final WxMaExpressDeliveryReturnService wxMaExpressDeliveryReturnService =
      new WxMaExpressDeliveryReturnServiceImpl(this);
  private final WxMaPromotionService wxMaPromotionService = new WxMaPromotionServiceImpl(this);
  private final WxMaIntracityService intracityService = new WxMaIntracityServiceImpl(this);

  private Map<String, WxMaConfig> configMap = new HashMap<>();
  private int retrySleepMillis = 1000;
  private int maxRetryTimes = 5;

  @Override
  public RequestHttp getRequestHttp() {
    return this;
  }

  @Override
  public String getPaidUnionId(String openid, String transactionId, String mchId, String outTradeNo)
      throws WxErrorException {
    Map<String, String> params = new HashMap<>(8);
    params.put("openid", openid);

    if (StringUtils.isNotEmpty(transactionId)) {
      params.put("transaction_id", transactionId);
    }

    if (StringUtils.isNotEmpty(mchId)) {
      params.put("mch_id", mchId);
    }

    if (StringUtils.isNotEmpty(outTradeNo)) {
      params.put("out_trade_no", outTradeNo);
    }

    String responseContent =
        this.get(GET_PAID_UNION_ID_URL, Joiner.on("&").withKeyValueSeparator("=").join(params));
    WxError error = WxError.fromJson(responseContent, WxType.MiniApp);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    }

    return GsonParser.parse(responseContent).get("unionid").getAsString();
  }

  @Override
  public WxMaJscode2SessionResult jsCode2SessionInfo(String jsCode) throws WxErrorException {
    final WxMaConfig config = getWxMaConfig();
    Map<String, String> params = new HashMap<>(8);
    params.put("appid", config.getAppid());
    params.put("secret", config.getSecret());
    params.put("js_code", jsCode);
    params.put("grant_type", "authorization_code");

    String result =
        get(JSCODE_TO_SESSION_URL, Joiner.on("&").withKeyValueSeparator("=").join(params));
    return WxMaJscode2SessionResult.fromJson(result);
  }

  @Override
  public void setDynamicData(int lifespan, String type, int scene, String data)
      throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("lifespan", lifespan);
    jsonObject.addProperty("query", WxGsonBuilder.create().toJson(ImmutableMap.of("type", type)));
    jsonObject.addProperty("data", data);
    jsonObject.addProperty("scene", scene);

    this.post(SET_DYNAMIC_DATA_URL, jsonObject.toString());
  }

  @Override
  public boolean checkSignature(String timestamp, String nonce, String signature) {
    try {
      return SHA1.gen(this.getWxMaConfig().getToken(), timestamp, nonce).equals(signature);
    } catch (Exception e) {
      log.error("Checking signature failed, and the reason is :" + e.getMessage());
      return false;
    }
  }

  @Override
  public String getAccessToken() throws WxErrorException {
    return getAccessToken(false);
  }

  @Override
  public String getAccessToken(boolean forceRefresh) throws WxErrorException {
    if (!forceRefresh && !this.getWxMaConfig().isAccessTokenExpired()) {
      return this.getWxMaConfig().getAccessToken();
    }

    Lock lock = this.getWxMaConfig().getAccessTokenLock();
    boolean locked = false;
    try {
      do {
        locked = lock.tryLock(100, TimeUnit.MILLISECONDS);
        if (!forceRefresh && !this.getWxMaConfig().isAccessTokenExpired()) {
          return this.getWxMaConfig().getAccessToken();
        }
      } while (!locked);

      String response;
      if (getWxMaConfig().isStableAccessToken()) {
        response = doGetStableAccessTokenRequest(forceRefresh);
      } else {
        response = doGetAccessTokenRequest();
      }
      return extractAccessToken(response);
    } catch (IOException | InterruptedException e) {
      throw new WxRuntimeException(e);
    } finally {
      if (locked) {
        lock.unlock();
      }
    }
  }

  /**
   * 通过网络请求获取AccessToken
   *
   * @return .
   * @throws IOException .
   */
  protected abstract String doGetAccessTokenRequest() throws IOException;

  /**
   * 通过网络请求获取稳定版接口调用凭据
   *
   * @return .
   * @throws IOException .
   */
  protected abstract String doGetStableAccessTokenRequest(boolean forceRefresh) throws IOException;

  @Override
  public String get(String url, String queryParam) throws WxErrorException {
    return execute(SimpleGetRequestExecutor.create(this), url, queryParam);
  }

  private boolean isApiSignatureRequired(String url) {
    return this.getWxMaConfig().getApiSignatureAesKey() != null
        && Arrays.stream(urlPathSupportApiSignature).anyMatch(part -> url.contains(part));
  }

  @Override
  public String post(String url, String postData) throws WxErrorException {
    if (isApiSignatureRequired(url)) {
      // 接口需要签名
      log.debug("已经配置接口需要签名，接口{}将走加密访问路径", url);
      JsonObject jsonObject = GSON.fromJson(postData == null ? "{}" : postData, JsonObject.class);
      return postWithSignature(url, jsonObject);
    } else {
      return execute(SimplePostRequestExecutor.create(this), url, postData);
    }
  }

  @Override
  public String post(String url, Object obj) throws WxErrorException {
    if (isApiSignatureRequired(url)) {
      // 接口需要签名
      log.debug("已经配置接口需要签名，接口{}将走加密访问路径", url);
      return postWithSignature(url, obj);
    } else {
      return this.execute(
          SimplePostRequestExecutor.create(this), url, WxGsonBuilder.create().toJson(obj));
    }
  }

  @Override
  public String post(String url, ToJson obj) throws WxErrorException {
    return this.post(url, obj == null ? "{}" : obj.toJson());
  }

  @Override
  public String post(String url, JsonObject jsonObject) throws WxErrorException {
    return this.post(url, jsonObject == null ? "{}" : jsonObject.toString());
  }

  @Override
  public String upload(String url, CommonUploadParam param) throws WxErrorException {
    RequestExecutor<String, CommonUploadParam> executor =
        CommonUploadRequestExecutor.create(getRequestHttp());
    return this.execute(executor, url, param);
  }

  /** 向微信端发送请求，在这里执行的策略是当发生access_token过期时才去刷新，然后重新执行请求，而不是全局定时请求 */
  @Override
  public <R, T> R execute(RequestExecutor<R, T> executor, String uri, T data)
      throws WxErrorException {
    String dataForLog;
    if (data == null) {
      dataForLog = null;
    } else if (data instanceof String) {
      dataForLog = DataUtils.handleDataWithSecret((String) data);
    } else {
      dataForLog = data.toString();
    }
    return executeWithRetry(
        (uriWithAccessToken) -> executor.execute(uriWithAccessToken, data, WxType.MiniApp),
        uri,
        dataForLog);
  }

  @Override
  public WxMaApiResponse execute(
      ApiSignaturePostRequestExecutor executor,
      String uri,
      Map<String, String> headers,
      String data)
      throws WxErrorException {
    String dataForLog = "Headers: " + headers.toString() + " Body: " + data;
    return executeWithRetry(
        (uriWithAccessToken) -> executor.execute(uriWithAccessToken, headers, data, WxType.MiniApp),
        uri,
        dataForLog);
  }

  private static interface ExecutorAction<R> {
    R execute(String urlWithAccessToken) throws IOException, WxErrorException;
  }

  private <R, T> R executeWithRetry(ExecutorAction<R> executor, String uri, String dataForLog)
      throws WxErrorException {
    int retryTimes = 0;
    do {
      try {
        return this.executeInternal(executor, uri, dataForLog, false);
      } catch (WxErrorException e) {
        if (retryTimes + 1 > this.maxRetryTimes) {
          log.warn("重试达到最大次数【{}】", maxRetryTimes);
          // 最后一次重试失败后，直接抛出异常，不再等待
          throw new WxErrorException(
              WxError.builder()
                  .errorCode(e.getError().getErrorCode())
                  .errorMsg("微信服务端异常，超出重试次数！")
                  .build());
        }

        WxError error = e.getError();
        // -1 系统繁忙, 1000ms后重试
        if (error.getErrorCode() == -1) {
          int sleepMillis = this.retrySleepMillis * (1 << retryTimes);
          try {
            log.warn("微信系统繁忙，{} ms 后重试(第{}次)", sleepMillis, retryTimes + 1);
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

  private <R, T> R executeInternal(
      ExecutorAction<R> executor, String uri, String dataForLog, boolean doNotAutoRefreshToken)
      throws WxErrorException {

    if (uri.contains("access_token=")) {
      throw new IllegalArgumentException("uri参数中不允许有access_token: " + uri);
    }
    String accessToken = getAccessToken(false);

    if (StringUtils.isNotEmpty(this.getWxMaConfig().getApiHostUrl())) {
      uri = uri.replace("https://api.weixin.qq.com", this.getWxMaConfig().getApiHostUrl());
    }

    String uriWithAccessToken =
        uri + (uri.contains("?") ? "&" : "?") + "access_token=" + accessToken;
    try {
      R result = executor.execute(uriWithAccessToken);
      log.debug("\n【请求地址】: {}\n【请求参数】：{}\n【响应数据】：{}", uriWithAccessToken, dataForLog, result);
      return result;
    } catch (WxErrorException e) {
      WxError error = e.getError();
      if (WxConsts.ACCESS_TOKEN_ERROR_CODES.contains(error.getErrorCode())) {
        // 强制设置WxMaConfig的access token过期了，这样在下一次请求里就会刷新access token
        Lock lock = this.getWxMaConfig().getAccessTokenLock();
        lock.lock();
        try {
          if (StringUtils.equals(this.getWxMaConfig().getAccessToken(), accessToken)) {
            this.getWxMaConfig().expireAccessToken();
          }
        } catch (Exception ex) {
          this.getWxMaConfig().expireAccessToken();
        } finally {
          lock.unlock();
        }
        if (this.getWxMaConfig().autoRefreshToken() && !doNotAutoRefreshToken) {
          log.warn(
              "即将重新获取新的access_token，错误代码：{}，错误信息：{}", error.getErrorCode(), error.getErrorMsg());
          // 下一次不再自动重试
          // 当小程序误调用第三方平台专属接口时,第三方无法使用小程序的access token,如果可以继续自动获取token会导致无限循环重试,直到栈溢出
          return this.executeInternal(executor, uri, dataForLog, true);
        }
      }

      if (error.getErrorCode() != 0) {
        log.warn("\n【请求地址】: {}\n【请求参数】：{}\n【错误信息】：{}", uriWithAccessToken, dataForLog, error);
        throw new WxErrorException(error, e);
      }
      return null;
    } catch (IOException e) {
      log.warn(
          "\n【请求地址】: {}\n【请求参数】：{}\n【异常信息】：{}", uriWithAccessToken, dataForLog, e.getMessage());
      throw new WxRuntimeException(e);
    }
  }

  /**
   * 设置当前的AccessToken
   *
   * @param resultContent 响应内容
   * @return access token
   * @throws WxErrorException 异常
   */
  protected String extractAccessToken(String resultContent) throws WxErrorException {
    log.debug("access-token response: {}", resultContent);
    WxMaConfig config = this.getWxMaConfig();
    WxError error = WxError.fromJson(resultContent, WxType.MiniApp);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    }

    WxAccessToken accessToken = WxAccessToken.fromJson(resultContent);
    config.updateAccessTokenProcessor(accessToken.getAccessToken(), accessToken.getExpiresIn());
    return accessToken.getAccessToken();
  }

  @Override
  public WxMaConfig getWxMaConfig() {
    if (this.configMap.size() == 1) {
      // 只有一个小程序，直接返回其配置即可
      return this.configMap.values().iterator().next();
    }

    return this.configMap.get(WxMaConfigHolder.get());
  }

  @Override
  public void setWxMaConfig(WxMaConfig maConfig) {
    final String appid = maConfig.getAppid();
    Map<String, WxMaConfig> map = new HashMap<>();
    map.put(appid, maConfig);
    Map<String, WxMaConfig> configMap = Collections.unmodifiableMap(map);
    this.setMultiConfigs(configMap, appid);
  }

  @Override
  public void setMultiConfigs(Map<String, WxMaConfig> configs) {
    this.setMultiConfigs(configs, configs.keySet().iterator().next());
  }

  @Override
  @JsonDeserialize
  public void setMultiConfigs(Map<String, WxMaConfig> configs, String defaultMiniappId) {
    // 防止覆盖配置
    if (this.configMap != null) {
      this.configMap.putAll(configs);
    } else {
      this.configMap = Maps.newHashMap(configs);
    }
    WxMaConfigHolder.set(defaultMiniappId);
    this.initHttp();
  }

  @Override
  public void addConfig(String miniappId, WxMaConfig configStorages) {
    synchronized (this) {
      /*
       * 因为commit f74b00cf 默认初始化了configMap，导致使用此方法无法进入if从而触发initHttp()，
       * 就会出现HttpClient报NullPointException
       */
      if (this.configMap == null || this.configMap.isEmpty()) {
        this.setWxMaConfig(configStorages);
      } else {
        WxMaConfigHolder.set(miniappId);
        this.configMap.put(miniappId, configStorages);
      }
    }
  }

  @Override
  public void removeConfig(String miniappId) {
    synchronized (this) {
      if (this.configMap.size() == 1) {
        this.configMap.remove(miniappId);
        log.warn("已删除最后一个小程序配置：{}，须立即使用setWxMaConfig或setMultiConfigs添加配置", miniappId);
        return;
      }
      if (WxMaConfigHolder.get().equals(miniappId)) {
        this.configMap.remove(miniappId);
        final String defaultMpId = this.configMap.keySet().iterator().next();
        WxMaConfigHolder.set(defaultMpId);
        log.warn("已删除默认小程序配置，小程序【{}】被设为默认配置", defaultMpId);
        return;
      }
      this.configMap.remove(miniappId);
    }
  }

  @Override
  public WxMaService switchoverTo(String miniAppId) {
    return switchoverTo(miniAppId, null);
  }

  @Override
  public WxMaService switchoverTo(String miniAppId, Function<String, WxMaConfig> func) {
    if (this.configMap.containsKey(miniAppId)) {
      WxMaConfigHolder.set(miniAppId);
      return this;
    }

    if (func != null) {
      WxMaConfig config = func.apply(miniAppId);
      if (config != null) {
        this.addConfig(miniAppId, config);
        return this;
      }
    }

    throw new WxRuntimeException(String.format("无法找到对应【%s】的小程序配置信息，请核实！", miniAppId));
  }

  @Override
  public boolean switchover(String mpId) {
    if (this.configMap.containsKey(mpId)) {
      WxMaConfigHolder.set(mpId);
      return true;
    }

    log.error("无法找到对应【{}】的小程序配置信息，请核实！", mpId);
    return false;
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
  public WxMaMsgService getMsgService() {
    return this.kefuService;
  }

  @Override
  public WxMaMediaService getMediaService() {
    return this.materialService;
  }

  @Override
  public WxMaUserService getUserService() {
    return this.userService;
  }

  @Override
  public WxMaQrcodeService getQrcodeService() {
    return this.qrCodeService;
  }

  @Override
  public WxMaSchemeService getWxMaSchemeService() {
    return schemeService;
  }

  @Override
  public WxMaSubscribeService getSubscribeService() {
    return this.subscribeService;
  }

  @Override
  public WxMaAnalysisService getAnalysisService() {
    return this.analysisService;
  }

  @Override
  public WxMaCodeService getCodeService() {
    return this.codeService;
  }

  @Override
  public WxMaJsapiService getJsapiService() {
    return this.jsapiService;
  }

  @Override
  public WxMaSettingService getSettingService() {
    return this.settingService;
  }

  @Override
  public WxMaShareService getShareService() {
    return this.shareService;
  }

  @Override
  public WxMaRunService getRunService() {
    return this.runService;
  }

  @Override
  public WxMaSecurityService getSecurityService() {
    return this.securityService;
  }

  @Override
  public WxMaPluginService getPluginService() {
    return this.pluginService;
  }

  @Override
  public WxMaExpressService getExpressService() {
    return this.expressService;
  }

  @Override
  public WxMaCloudService getCloudService() {
    return this.cloudService;
  }

  @Override
  public WxMaInternetService getInternetService() {
    return this.internetService;
  }

  @Override
  public WxMaLiveService getLiveService() {
    return this.liveService;
  }

  @Override
  public WxMaLiveGoodsService getLiveGoodsService() {
    return this.liveGoodsService;
  }

  @Override
  public WxMaLiveMemberService getLiveMemberService() {
    return this.liveMemberService;
  }

  @Override
  public WxOcrService getOcrService() {
    return this.ocrService;
  }

  @Override
  public WxImgProcService getImgProcService() {
    return this.imgProcService;
  }

  @Override
  public WxMaShopSpuService getShopSpuService() {
    return this.shopSpuService;
  }

  @Override
  public WxMaShopOrderService getShopOrderService() {
    return this.shopOrderService;
  }

  @Override
  public WxMaShopRegisterService getShopRegisterService() {
    return this.shopRegisterService;
  }

  @Override
  public WxMaShopAccountService getShopAccountService() {
    return this.shopAccountService;
  }

  @Override
  public WxMaShopCatService getShopCatService() {
    return this.shopCatService;
  }

  @Override
  public WxMaShopImgService getShopImgService() {
    return this.shopImgService;
  }

  @Override
  public WxMaShopAuditService getShopAuditService() {
    return this.shopAuditService;
  }

  @Override
  public WxMaShopAfterSaleService getShopAfterSaleService() {
    return this.shopAfterSaleService;
  }

  @Override
  public WxMaShopDeliveryService getShopDeliveryService() {
    return this.shopDeliveryService;
  }

  @Override
  public WxMaLinkService getLinkService() {
    return this.linkService;
  }

  @Override
  public WxMaReimburseInvoiceService getReimburseInvoiceService() {
    return this.reimburseInvoiceService;
  }

  @Override
  public WxMaDeviceSubscribeService getDeviceSubscribeService() {
    return this.deviceSubscribeService;
  }

  @Override
  public WxMaMarketingService getMarketingService() {
    return this.marketingService;
  }

  @Override
  public WxMaImmediateDeliveryService getWxMaImmediateDeliveryService() {
    return this.immediateDeliveryService;
  }

  @Override
  public WxMaShopSharerService getShopSharerService() {
    return this.shopSharerService;
  }

  @Override
  public WxMaProductService getProductService() {
    return this.productService;
  }

  @Override
  public WxMaProductOrderService getProductOrderService() {
    return this.productOrderService;
  }

  @Override
  public WxMaShopCouponService getWxMaShopCouponService() {
    return this.wxMaShopCouponService;
  }

  @Override
  public WxMaShopPayService getWxMaShopPayService() {
    return this.wxMaShopPayService;
  }

  /**
   * 小程序发货信息管理服务
   *
   * @return getWxMaOrderShippingService
   */
  @Override
  public WxMaOrderShippingService getWxMaOrderShippingService() {
    return this.wxMaOrderShippingService;
  }

  /**
   * 小程序订单管理服务
   *
   * @return WxMaOrderManagementService
   */
  @Override
  public WxMaOrderManagementService getWxMaOrderManagementService() {
    return this.wxMaOrderManagementService;
  }

  @Override
  public WxMaOpenApiService getWxMaOpenApiService() {
    return this.wxMaOpenApiService;
  }

  @Override
  public WxMaVodService getWxMaVodService() {
    return this.wxMaVodService;
  }

  @Override
  public WxMaXPayService getWxMaXPayService() {
    return this.wxMaXPayService;
  }

  @Override
  public WxMaExpressDeliveryReturnService getWxMaExpressDeliveryReturnService() {
    return this.wxMaExpressDeliveryReturnService;
  }

  @Override
  public WxMaPromotionService getWxMaPromotionService() {
    return this.wxMaPromotionService;
  }

  @Override
  public String postWithSignature(String url, Object obj) throws WxErrorException {
    Gson gson =
        new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();
    JsonObject jsonObject;
    if (obj == null) {
      jsonObject = gson.fromJson("{}", JsonObject.class);
    } else {
      jsonObject = gson.toJsonTree(obj).getAsJsonObject();
    }
    return this.postWithSignature(url, jsonObject);
  }

  private String generateNonce() {
    byte[] nonce = generateRandomBytes(16);
    return base64Encode(nonce).replace("=", "");
  }

  private byte[] generateRandomBytes(int length) {
    byte[] bytes = new byte[length];
    new SecureRandom().nextBytes(bytes);
    return bytes;
  }

  private String base64Encode(byte[] data) {
    return Base64.getEncoder().encodeToString(data);
  }

  @Override
  public String postWithSignature(String url, JsonObject jsonObject) throws WxErrorException {
    long timestamp = System.currentTimeMillis() / 1000;
    String appId = this.getWxMaConfig().getWechatMpAppid();
    String rndStr = UUID.randomUUID().toString().replace("-", "").substring(0, 30);
    String aesKey = this.getWxMaConfig().getApiSignatureAesKey();
    String aesKeySn = this.getWxMaConfig().getApiSignatureAesKeySn();

    jsonObject.addProperty("_n", rndStr);
    jsonObject.addProperty("_appid", appId);
    jsonObject.addProperty("_timestamp", timestamp);

    String plainText = jsonObject.toString();
    log.debug("URL:{}加密前请求数据:{}", url, plainText);
    String urlPath;
    if (url.contains("?")) {
      urlPath = url.substring(0, url.indexOf("?"));
    } else {
      urlPath = url;
    }
    String aad = urlPath + "|" + appId + "|" + timestamp + "|" + aesKeySn;
    byte[] realKey;
    try {
      realKey = Base64.getDecoder().decode(aesKey);
    } catch (Exception ex) {
      log.error("解析AESKEY失败 {}", aesKey, ex);
      throw new SecurityException("解析AES KEY失败，请检查ApiSignatureAesKey是否正确", ex);
    }
    byte[] realIv = generateRandomBytes(12);
    byte[] realAad = aad.getBytes(StandardCharsets.UTF_8);
    byte[] realPlainText = plainText.getBytes(StandardCharsets.UTF_8);

    try {
      // 加密内容 AES
      Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
      SecretKeySpec aesKeySpec = new SecretKeySpec(realKey, "AES");
      GCMParameterSpec parameterSpec = new GCMParameterSpec(128, realIv);
      cipher.init(Cipher.ENCRYPT_MODE, aesKeySpec, parameterSpec);
      cipher.updateAAD(realAad);

      byte[] ciphertext = cipher.doFinal(realPlainText);
      byte[] encryptedData = Arrays.copyOfRange(ciphertext, 0, ciphertext.length - 16);
      byte[] authTag = Arrays.copyOfRange(ciphertext, ciphertext.length - 16, ciphertext.length);

      JsonObject reqData = new JsonObject();
      reqData.addProperty("iv", base64Encode(realIv));
      reqData.addProperty("data", base64Encode(encryptedData));
      reqData.addProperty("authtag", base64Encode(authTag));
      String requestJson = reqData.toString();

      // 计算签名 RSA
      String payload = urlPath + "\n" + appId + "\n" + timestamp + "\n" + requestJson;
      byte[] dataBuffer = payload.getBytes(StandardCharsets.UTF_8);
      RSAPrivateKey priKey;
      try {
        String rsaPrivateKey = this.getWxMaConfig().getApiSignatureRsaPrivateKey();
        rsaPrivateKey = rsaPrivateKey.replace("-----BEGIN PRIVATE KEY-----", "");
        rsaPrivateKey = rsaPrivateKey.replace("-----END PRIVATE KEY-----", "");
        rsaPrivateKey = rsaPrivateKey.replaceAll("\\s+", "");
        byte[] decoded = Base64.getDecoder().decode(rsaPrivateKey.getBytes(StandardCharsets.UTF_8));
        PKCS8EncodedKeySpec rsaKeySpec = new PKCS8EncodedKeySpec(decoded);
        priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(rsaKeySpec);
      } catch (Exception ex) {
        log.error("解析RSA KEY失败 {}", aesKey, ex);
        throw new SecurityException("解析RSA KEY失败，请检查ApiSignatureRsaPrivateKey是否正确，需要PKCS8格式私钥", ex);
      }
      Signature signature = Signature.getInstance("RSASSA-PSS");
      // salt长度，需与SHA256结果长度(32)一致
      PSSParameterSpec pssParameterSpec =
          new PSSParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256, 32, 1);
      signature.setParameter(pssParameterSpec);
      signature.initSign(priKey);
      signature.update(dataBuffer);
      byte[] sigBuffer = signature.sign();
      String signatureString = base64Encode(sigBuffer);

      Map<String, String> header = new HashMap<>();
      header.put("Wechatmp-Signature", signatureString);
      header.put("Wechatmp-Appid", appId);
      header.put("Wechatmp-TimeStamp", String.valueOf(timestamp));
      log.debug("发送请求uri:{}, headers:{}, postData:{}", url, header, requestJson);
      WxMaApiResponse response =
          this.execute(ApiSignaturePostRequestExecutor.create(this), url, header, requestJson);
      String respTs = response.getHeaders().get("Wechatmp-TimeStamp");
      String respAad = urlPath + "|" + appId + "|" + respTs + "|" + aesKeySn;
      if (!appId.equals(response.getHeaders().get("Wechatmp-Appid"))) {
        throw new RuntimeException("响应的appId不符 " + response.getHeaders().get("Wechatmp-Appid"));
      }
      // 省略验证平台签名部分，直接解密内容，返回明文
      String decryptedData = aesDecodeResponse(response, respAad, aesKeySpec);
      log.debug("解密后的响应:{}", decryptedData);
      WxError error = WxError.fromJson(decryptedData, WxType.MiniApp);
      if (error.getErrorCode() != 0) {
        log.debug("调用API出错， uri:{}, postData:{}, response:{}", url, plainText, error);
        throw new WxErrorException(error);
      }
      return decryptedData;
    } catch (WxErrorException | SecurityException ex) {
      throw ex;
    } catch (Exception e) {
      log.error("postWithSignature", e);
      throw new RuntimeException(e);
    }
  }

  private String aesDecodeResponse(WxMaApiResponse response, String aad, SecretKeySpec aesKeySpec)
      throws Exception {
    Map<?, ?> map = GSON.fromJson(response.getContent(), Map.class);
    String iv = (String) map.get("iv");
    String data = (String) map.get("data");
    String authTag = (String) map.get("authtag");

    byte[] dataBytes = Base64.getDecoder().decode(data);
    byte[] authTagBytes = Base64.getDecoder().decode(authTag);
    byte[] newDataBytes = new byte[dataBytes.length + authTagBytes.length];
    System.arraycopy(dataBytes, 0, newDataBytes, 0, dataBytes.length);
    System.arraycopy(authTagBytes, 0, newDataBytes, dataBytes.length, authTagBytes.length);
    byte[] aadBytes = aad.getBytes(StandardCharsets.UTF_8);
    byte[] ivBytes = Base64.getDecoder().decode(iv);

    Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
    GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(128, ivBytes);
    cipher.init(Cipher.DECRYPT_MODE, aesKeySpec, gcmParameterSpec);
    cipher.updateAAD(aadBytes);
    byte[] decryptedBytes = cipher.doFinal(newDataBytes);

    return new String(decryptedBytes, StandardCharsets.UTF_8);
  }

  @Override
  public WxMaIntracityService getIntracityService() {
    return this.intracityService;
  }
}
