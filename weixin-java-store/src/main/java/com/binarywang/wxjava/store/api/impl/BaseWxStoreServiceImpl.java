package com.binarywang.wxjava.store.api.impl;


import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import com.binarywang.wxjava.store.api.*;
import com.binarywang.wxjava.store.config.WxStoreConfig;
import com.binarywang.wxjava.store.util.JsonUtils;
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
public abstract class BaseWxStoreServiceImpl<H, P> implements WxStoreService, RequestHttp<H, P> {

  private final WxStoreBasicService basicService = new WxStoreBasicServiceImpl(this);
  private final WxStoreCategoryService categoryService = new WxStoreCategoryServiceImpl(this);
  private final WxStoreBrandService brandService = new WxStoreBrandServiceImpl(this);
  private final WxStoreGiftService giftService = new WxStoreGiftServiceImpl(this);
  private final WxStoreLimitedDiscountService limitedDiscountService =
    new WxStoreLimitedDiscountServiceImpl(this);
  private final WxStoreProductStockService productStockService = new WxStoreProductStockServiceImpl(this);
  private final WxStoreProductAssistantService productAssistantService =
    new WxStoreProductAssistantServiceImpl(this);
  private final WxStoreProductService productService = new WxStoreProductServiceImpl(
    this, giftService, limitedDiscountService, productStockService);
  private final WxStoreWarehouseService warehouseService = new WxStoreWarehouseServiceImpl(this);
  private final WxStoreOrderService orderService = new WxStoreOrderServiceImpl(this);
  private final WxStoreAfterSaleService afterSaleService = new WxStoreAfterSaleServiceImpl(this);
  private final WxStoreFreightTemplateService freightTemplateService =
    new WxStoreFreightTemplateServiceImpl(this);
  private final WxStoreAddressService addressService = new WxStoreAddressServiceImpl(this);
  private final WxStoreCouponService couponService = new WxStoreCouponServiceImpl(this);
  private final WxStoreSharerService sharerService = new WxStoreSharerServiceImpl(this);
  private final WxStoreFundService fundService = new WxStoreFundServiceImpl(this);
  private WxStoreHomePageService homePageService = null;
  private WxStoreCooperationService cooperationService = null;
  private WxStoreCompassShopService compassShopService = null;
  private WxStoreSupplierService supplierService = null;
  private WxStoreVipService vipService = null;
  private WxStoreQicService qicService = null;
  private WxTalentService talentService = null;
  private WxStoreFavoriteService favoriteService = null;
  private WxStoreEwaybillService ewaybillService = null;
  private WxStoreKfService kfService = null;

  protected WxStoreConfig config;
  private int retrySleepMillis = 1000;
  private int maxRetryTimes = 5;

  @Override
  public RequestHttp<H, P> getRequestHttp() {
    return this;
  }

  @Override
  public boolean checkSignature(String timestamp, String nonce, String signature) {
    try {
      return SHA1.gen(this.getConfig().getToken(), timestamp, nonce).equals(signature);
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

    WxStoreConfig config = this.getConfig();
    if (StringUtils.isNotEmpty(config.getApiHostUrl())) {
      uri = uri.replace("https://api.weixin.qq.com", config.getApiHostUrl());
    }

    String uriWithAccessToken = uri + (uri.contains("?") ? "&" : "?") + "access_token=" + accessToken;

    try {
      T result = executor.execute(uriWithAccessToken, data, WxType.Channel);
      log.debug("\n【请求地址】: {}\n【请求参数】：{}\n【响应数据】：{}", uri,
        printResult ? dataForLog : "...",
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
        log.warn("\n【请求地址】: {}\n【请求参数】：{}\n【错误信息】：{}", uri,
          printResult ? dataForLog : "...", error);
        throw new WxErrorException(error, e);
      }
      return null;
    } catch (IOException e) {
      log.warn("\n【请求地址】: {}\n【请求参数】：{}\n【异常信息】：{}", uri,
        printResult ? dataForLog : "...", e.getMessage());
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
    log.debug("access-token response received");
    WxStoreConfig config = this.getConfig();
    WxError error = WxError.fromJson(resultContent, WxType.Channel);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    }
    WxAccessToken accessToken = WxAccessToken.fromJson(resultContent);
    config.updateAccessToken(accessToken.getAccessToken(), accessToken.getExpiresIn());
    return accessToken.getAccessToken();
  }

  @Override
  public WxStoreConfig getConfig() {
    return config;
  }

  @Override
  public void setConfig(WxStoreConfig config) {
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
  public WxStoreBasicService getBasicService() {
    return basicService;
  }

  @Override
  public WxStoreCategoryService getCategoryService() {
    return categoryService;
  }

  @Override
  public WxStoreBrandService getBrandService() {
    return brandService;
  }

  @Override
  public WxStoreProductService getProductService() {
    return productService;
  }

  @Override
  public WxStoreGiftService getGiftService() {
    return giftService;
  }

  @Override
  public WxStoreLimitedDiscountService getLimitedDiscountService() {
    return limitedDiscountService;
  }

  @Override
  public WxStoreProductStockService getProductStockService() {
    return productStockService;
  }

  @Override
  public WxStoreProductAssistantService getProductAssistantService() {
    return productAssistantService;
  }

  @Override
  public WxStoreWarehouseService getWarehouseService() {
    return warehouseService;
  }

  @Override
  public WxStoreOrderService getOrderService() {
    return orderService;
  }

  @Override
  public WxStoreAfterSaleService getAfterSaleService() {
    return afterSaleService;
  }

  @Override
  public WxStoreFreightTemplateService getFreightTemplateService() {
    return freightTemplateService;
  }

  @Override
  public WxStoreAddressService getAddressService() {
    return addressService;
  }

  @Override
  public WxStoreCouponService getCouponService() {
    return couponService;
  }

  @Override
  public WxStoreSharerService getSharerService() {
    return sharerService;
  }

  @Override
  public WxStoreFundService getFundService() {
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
  public synchronized WxStoreCompassShopService getCompassShopService() {
    if (compassShopService == null) {
      compassShopService = new WxStoreCompassShopServiceImpl(this);
    }
    return compassShopService;
  }

  @Override
  public synchronized WxStoreSupplierService getSupplierService() {
    if (supplierService == null) {
      supplierService = new WxStoreSupplierServiceImpl(this);
    }
    return supplierService;
  }

  @Override
  public synchronized WxStoreVipService getVipService() {
    if (vipService == null) {
      vipService = new WxStoreVipServiceImpl(this);
    }
    return vipService;
  }

  @Override
  public synchronized WxStoreQicService getQicService() {
    if (qicService == null) {
      qicService = new WxStoreQicServiceImpl(this);
    }
    return qicService;
  }

  @Override
  public synchronized WxTalentService getTalentService() {
    if (talentService == null) {
      talentService = new WxTalentServiceImpl(this);
    }
    return talentService;
  }

  @Override
  public synchronized WxStoreFavoriteService getFavoriteService() {
    if (favoriteService == null) {
      favoriteService = new WxStoreFavoriteServiceImpl(this);
    }
    return favoriteService;
  }

  @Override
  public synchronized WxStoreEwaybillService getEwaybillService() {
    if (ewaybillService == null) {
      ewaybillService = new WxStoreEwaybillServiceImpl(this);
    }
    return ewaybillService;
  }

  @Override
  public synchronized WxStoreKfService getKfService() {
    if (kfService == null) {
      kfService = new WxStoreKfServiceImpl(this);
    }
    return kfService;
  }

}
