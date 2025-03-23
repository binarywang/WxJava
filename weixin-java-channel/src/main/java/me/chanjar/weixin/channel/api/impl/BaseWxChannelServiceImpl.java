package me.chanjar.weixin.channel.api.impl;


import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.channel.api.*;
import me.chanjar.weixin.channel.config.WxChannelConfig;
import me.chanjar.weixin.channel.util.JsonUtils;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.CommonUploadParam;
import me.chanjar.weixin.common.bean.ToJson;
import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.error.WxRuntimeException;
import me.chanjar.weixin.common.executor.CommonUploadRequestExecutor;
import me.chanjar.weixin.common.util.DataUtils;
import me.chanjar.weixin.common.util.crypto.SHA1;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.SimpleGetRequestExecutor;
import me.chanjar.weixin.common.util.http.SimplePostRequestExecutor;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @see #doGetAccessTokenRequest
 */
@Slf4j
public abstract class BaseWxChannelServiceImpl<H, P> implements WxChannelService, RequestHttp<H, P> {

  private final WxChannelBasicService basicService = new WxChannelBasicServiceImpl(this);
  private final WxChannelCategoryService categoryService = new WxChannelCategoryServiceImpl(this);
  private final WxChannelBrandService brandService = new WxChannelBrandServiceImpl(this);
  private final WxChannelProductService productService = new WxChannelProductServiceImpl(this);
  private final WxChannelWarehouseService warehouseService = new WxChannelWarehouseServiceImpl(this);
  private final WxChannelOrderService orderService = new WxChannelOrderServiceImpl(this);
  private final WxChannelAfterSaleService afterSaleService = new WxChannelAfterSaleServiceImpl(this);
  private final WxChannelFreightTemplateService freightTemplateService =
    new WxChannelFreightTemplateServiceImpl(this);
  private final WxChannelAddressService addressService = new WxChannelAddressServiceImpl(this);
  private final WxChannelCouponService couponService = new WxChannelCouponServiceImpl(this);
  private final WxChannelSharerService sharerService = new WxChannelSharerServiceImpl(this);
  private final WxChannelFundService fundService = new WxChannelFundServiceImpl(this);
  private WxStoreHomePageService homePageService = null;
  private WxStoreCooperationService cooperationService = null;
  private WxChannelCompassShopService compassShopService = null;
  private WxLeagueWindowService leagueWindowService = null;
  private WxLeagueSupplierService leagueSupplierService = null;
  private WxLeaguePromoterService leaguePromoterService = null;
  private WxLeagueProductService leagueProductService = null;
  private WxLeadComponentService leadComponentService = null;
  private WxFinderLiveService finderLiveService = null;
  private WxAssistantService assistantService = null;
  private WxChannelVipService vipService = null;
  private WxChannelCompassFinderService compassFinderService = null;
  private WxChannelLiveDashboardService liveDashboardService = null;

  protected WxChannelConfig config;
  private int retrySleepMillis = 1000;
  private int maxRetryTimes = 5;

  @Override
  public RequestHttp getRequestHttp() {
    return this;
  }

  @Override
  public boolean checkSignature(String timestamp, String nonce, String signature) {
    try {
      return SHA1.gen(this.getConfig().getToken(), timestamp, nonce).equals(signature);
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
    if (!forceRefresh && !this.getConfig().isAccessTokenExpired()) {
      return this.getConfig().getAccessToken();
    }

    Lock lock = this.getConfig().getAccessTokenLock();
    boolean locked = false;
    try {
      do {
        locked = lock.tryLock(100, TimeUnit.MILLISECONDS);
        if (!forceRefresh && !this.getConfig().isAccessTokenExpired()) {
          return this.getConfig().getAccessToken();
        }
      } while (!locked);
      String response;
      if (getConfig().isStableAccessToken()) {
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
   * @return AccessToken
   * @throws IOException IOException
   */
  protected abstract String doGetAccessTokenRequest() throws IOException;

  /**
   * 通过网络请求获取稳定版AccessToken
   *
   * @return Stable AccessToken
   * @throws IOException IOException
   */
  protected abstract String doGetStableAccessTokenRequest(boolean forceRefresh) throws IOException;

  @Override
  public String get(String url, String queryParam) throws WxErrorException {
    return execute(SimpleGetRequestExecutor.create(this), url, queryParam);
  }

  @Override
  public String post(String url, String postData) throws WxErrorException {
    return execute(SimplePostRequestExecutor.create(this), url, postData);
  }

  @Override
  public String post(String url, Object obj) throws WxErrorException {
    // 此处用JsonUtils.encode, 不用Gson
    return this.execute(SimplePostRequestExecutor.create(this), url, JsonUtils.encode(obj));
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
  public String post(String url, JsonObject jsonObject) throws WxErrorException {
    return this.post(url, jsonObject.toString());
  }

  /**
   * 向微信端发送请求，在这里执行的策略是当发生access_token过期时才去刷新，然后重新执行请求，而不是全局定时请求
   */
  @Override
  public <T, E> T execute(RequestExecutor<T, E> executor, String uri, E data) throws WxErrorException {
    return execute0(executor, uri, data, true);
  }

  @Override
  public <T, E> T executeWithoutLog(RequestExecutor<T, E> executor, String uri, E data) throws WxErrorException {
    return execute0(executor, uri, data, false);
  }

  protected <T, E> T execute0(RequestExecutor<T, E> executor, String uri, E data, boolean printResult)
    throws WxErrorException {
    int retryTimes = 0;
    do {
      try {
        return this.executeInternal(executor, uri, data, false, printResult);
      } catch (WxErrorException e) {
        if (retryTimes + 1 > this.maxRetryTimes) {
          log.warn("重试达到最大次数【{}】", maxRetryTimes);
          //最后一次重试失败后，直接抛出异常，不再等待
          throw new WxErrorException(WxError.builder()
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

  protected <T, E> T executeInternal(RequestExecutor<T, E> executor, String uri, E data, boolean doNotAutoRefreshToken,
                                     boolean printResult) throws WxErrorException {
    E dataForLog = DataUtils.handleDataWithSecret(data);

    if (uri.contains("access_token=")) {
      throw new IllegalArgumentException("uri参数中不允许有access_token: " + uri);
    }
    String accessToken = getAccessToken(false);

    WxChannelConfig config = this.getConfig();
    if (StringUtils.isNotEmpty(config.getApiHostUrl())) {
      uri = uri.replace("https://api.weixin.qq.com", config.getApiHostUrl());
    }

    String uriWithAccessToken = uri + (uri.contains("?") ? "&" : "?") + "access_token=" + accessToken;

    try {
      T result = executor.execute(uriWithAccessToken, data, WxType.Channel);
      log.debug("\n【请求地址】: {}\n【请求参数】：{}\n【响应数据】：{}", uriWithAccessToken, dataForLog,
        printResult ? result : "...");
      return result;
    } catch (WxErrorException e) {
      WxError error = e.getError();
      if (WxConsts.ACCESS_TOKEN_ERROR_CODES.contains(error.getErrorCode())) {
        // 强制设置WxMaConfig的access token过期了，这样在下一次请求里就会刷新access token
        Lock lock = config.getAccessTokenLock();
        lock.lock();
        try {
          if (StringUtils.equals(config.getAccessToken(), accessToken)) {
            config.expireAccessToken();
          }
        } catch (Exception ex) {
          config.expireAccessToken();
        } finally {
          lock.unlock();
        }
        if (config.autoRefreshToken() && !doNotAutoRefreshToken) {
          log.warn("即将重新获取新的access_token，错误代码：{}，错误信息：{}", error.getErrorCode(), error.getErrorMsg());
          //下一次不再自动重试
          //当小程序误调用第三方平台专属接口时,第三方无法使用小程序的access token,如果可以继续自动获取token会导致无限循环重试,直到栈溢出
          return this.executeInternal(executor, uri, data, true, printResult);
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
   * 设置当前的AccessToken
   *
   * @param resultContent 响应内容
   * @return access token
   * @throws WxErrorException 异常
   */
  protected String extractAccessToken(String resultContent) throws WxErrorException {
    log.debug("access-token response: " + resultContent);
    WxChannelConfig config = this.getConfig();
    WxError error = WxError.fromJson(resultContent, WxType.Channel);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    }
    WxAccessToken accessToken = WxAccessToken.fromJson(resultContent);
    config.updateAccessToken(accessToken.getAccessToken(), accessToken.getExpiresIn());
    return accessToken.getAccessToken();
  }

  @Override
  public WxChannelConfig getConfig() {
    return config;
  }

  @Override
  public void setConfig(WxChannelConfig config) {
    this.config = config;
    initHttp();
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
  public WxChannelBasicService getBasicService() {
    return basicService;
  }

  @Override
  public WxChannelCategoryService getCategoryService() {
    return categoryService;
  }

  @Override
  public WxChannelBrandService getBrandService() {
    return brandService;
  }

  @Override
  public WxChannelProductService getProductService() {
    return productService;
  }

  @Override
  public WxChannelWarehouseService getWarehouseService() {
    return warehouseService;
  }

  @Override
  public WxChannelOrderService getOrderService() {
    return orderService;
  }

  @Override
  public WxChannelAfterSaleService getAfterSaleService() {
    return afterSaleService;
  }

  @Override
  public WxChannelFreightTemplateService getFreightTemplateService() {
    return freightTemplateService;
  }

  @Override
  public WxChannelAddressService getAddressService() {
    return addressService;
  }

  @Override
  public WxChannelCouponService getCouponService() {
    return couponService;
  }

  @Override
  public WxChannelSharerService getSharerService() {
    return sharerService;
  }

  @Override
  public WxChannelFundService getFundService() {
    return fundService;
  }

  @Override
  public synchronized WxStoreHomePageService getHomePageService() {
    if (homePageService == null) {
      homePageService = new WxStoreHomePageServiceImpl(this);
    }
    return homePageService;
  }

  @Override
  public synchronized WxStoreCooperationService getCooperationService() {
    if (cooperationService == null) {
      cooperationService = new WxStoreCooperationServiceImpl(this);
    }
    return cooperationService;
  }

  @Override
  public synchronized WxChannelCompassShopService getCompassShopService() {
    if (compassShopService == null) {
      compassShopService = new WxChannelCompassShopServiceImpl(this);
    }
    return compassShopService;
  }

  @Override
  public synchronized WxLeagueWindowService getLeagueWindowService() {
    if (leagueWindowService == null) {
      leagueWindowService = new WxLeagueWindowServiceImpl(this);
    }
    return leagueWindowService;
  }

  @Override
  public synchronized WxLeagueSupplierService getLeagueSupplierService() {
    if (leagueSupplierService == null) {
      leagueSupplierService = new WxLeagueSupplierServiceImpl(this);
    }
    return leagueSupplierService;
  }

  @Override
  public synchronized WxLeaguePromoterService getLeaguePromoterService() {
    if (leaguePromoterService == null) {
      leaguePromoterService = new WxLeaguePromoterServiceImpl(this);
    }
    return leaguePromoterService;
  }

  @Override
  public synchronized WxLeagueProductService getLeagueProductService() {
    if (leagueProductService == null) {
      leagueProductService = new WxLeagueProductServiceImpl(this);
    }
    return leagueProductService;
  }

  @Override
  public synchronized WxLeadComponentService getLeadComponentService() {
    if (leadComponentService == null) {
      leadComponentService = new WxLeadComponentServiceImpl(this);
    }
    return leadComponentService;
  }

  @Override
  public synchronized WxFinderLiveService getFinderLiveService() {
    if (finderLiveService == null) {
      finderLiveService = new WxFinderLiveServiceImpl(this);
    }
    return finderLiveService;
  }

  @Override
  public synchronized WxAssistantService getAssistantService() {
    if (assistantService == null) {
      assistantService = new WxAssistantServiceImpl(this) {
      };
    }
    return assistantService;
  }

  @Override
  public synchronized WxChannelVipService getVipService() {
    if (vipService == null) {
      vipService = new WxChannelVipServiceImpl(this);
    }
    return vipService;
  }

  @Override
  public synchronized WxChannelCompassFinderService getCompassFinderService() {
    if (compassFinderService == null) {
      compassFinderService = new WxChannelCompassFinderServiceImpl(this);
    }
    return compassFinderService;
  }

  @Override
  public synchronized WxChannelLiveDashboardService getLiveDashboardService() {
    if (liveDashboardService == null) {
      liveDashboardService = new WxChannelLiveDashboardServiceImpl(this);
    }
    return liveDashboardService;
  }

}
