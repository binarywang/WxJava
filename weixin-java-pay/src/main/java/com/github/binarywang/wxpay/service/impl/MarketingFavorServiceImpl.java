package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.ecommerce.SignatureHeader;
import com.github.binarywang.wxpay.bean.marketing.*;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.MarketingFavorService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.v3.util.AesUtils;
import com.github.binarywang.wxpay.v3.util.RsaCryptoUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Objects;

/**
 * 微信支付-营销代金券接口
 *
 * @author thinsstar
 */
@Slf4j
@RequiredArgsConstructor
public class MarketingFavorServiceImpl implements MarketingFavorService {
  private static final Gson GSON = new GsonBuilder().create();
  private final WxPayService payService;

  @Override
  public FavorStocksCreateResult createFavorStocksV3(FavorStocksCreateRequest request) throws WxPayException {
    String url = String.format("%s/v3/marketing/favor/coupon-stocks", this.payService.getPayBaseUrl());
    RsaCryptoUtil.encryptFields(request, this.payService.getConfig().getVerifier().getValidCertificate());
    String result = this.payService.postV3WithWechatpaySerial(url, GSON.toJson(request));
    FavorStocksCreateResult favorStocksCreateResult = GSON.fromJson(result, FavorStocksCreateResult.class);
    favorStocksCreateResult.setRawJsonString(result);
    return favorStocksCreateResult;
  }

  @Override
  public FavorCouponsCreateResult createFavorCouponsV3(String openid, FavorCouponsCreateRequest request) throws WxPayException {
    String url = String.format("%s/v3/marketing/favor/users/%s/coupons", this.payService.getPayBaseUrl(), openid);
    RsaCryptoUtil.encryptFields(request, this.payService.getConfig().getVerifier().getValidCertificate());
    String result = this.payService.postV3WithWechatpaySerial(url, GSON.toJson(request));
    return GSON.fromJson(result, FavorCouponsCreateResult.class);
  }

  @Override
  public FavorStocksStartResult startFavorStocksV3(String stockId, FavorStocksSetRequest request) throws WxPayException {
    String url = String.format("%s/v3/marketing/favor/stocks/%s/start", this.payService.getPayBaseUrl(), stockId);
    RsaCryptoUtil.encryptFields(request, this.payService.getConfig().getVerifier().getValidCertificate());
    String result = this.payService.postV3WithWechatpaySerial(url, GSON.toJson(request));
    return GSON.fromJson(result, FavorStocksStartResult.class);
  }

  @Override
  public FavorStocksQueryResult queryFavorStocksV3(FavorStocksQueryRequest request) throws WxPayException {
    String url = String.format("%s/v3/marketing/favor/stocks", this.payService.getPayBaseUrl());
    String query = String.format("?offset=%s&limit=%s&stock_creator_mchid=%s", request.getOffset(), request.getLimit(), request.getStockCreatorMchid());
    if (StringUtils.isNotBlank(request.getCreateStartTime())) {
      query += "&create_start_time=" + request.getCreateStartTime();
    }
    if (StringUtils.isNotBlank(request.getCreateEndTime())) {
      query += "&create_end_time=" + request.getCreateEndTime();
    }
    if (StringUtils.isNotBlank(request.getStatus())) {
      query += "&status=" + request.getStatus();
    }
    String result = this.payService.getV3(url + query);
    return GSON.fromJson(result, FavorStocksQueryResult.class);
  }

  @Override
  public FavorStocksGetResult getFavorStocksV3(String stockId, String stockCreatorMchid) throws WxPayException {
    String url = String.format("%s/v3/marketing/favor/stocks/%s", this.payService.getPayBaseUrl(), stockId);
    String query = String.format("?stock_creator_mchid=%s", stockCreatorMchid);
    String result = this.payService.getV3(url + query);
    FavorStocksGetResult favorStocksGetResult = GSON.fromJson(result, FavorStocksGetResult.class);
    favorStocksGetResult.setRawJsonString(result);
    return favorStocksGetResult;
  }

  @Override
  public FavorCouponsGetResult getFavorCouponsV3(String couponId, String appid, String openid) throws WxPayException {
    String url = String.format("%s/v3/marketing/favor/users/%s/coupons/%s", this.payService.getPayBaseUrl(), openid, couponId);
    String query = String.format("?appid=%s", appid);
    String result = this.payService.getV3(url + query);
    FavorCouponsGetResult favorCouponsGetResult = GSON.fromJson(result, FavorCouponsGetResult.class);
    favorCouponsGetResult.setRawJsonString(result);
    return favorCouponsGetResult;
  }

  @Override
  public FavorStocksMerchantsGetResult getFavorStocksMerchantsV3(String stockId, String stockCreatorMchid, Integer offset, Integer limit) throws WxPayException {
    String url = String.format("%s/v3/marketing/favor/stocks/%s/merchants", this.payService.getPayBaseUrl(), stockId);
    String query = String.format("?stock_creator_mchid=%s&offset=%s&limit=%s", stockCreatorMchid, offset, limit);
    String result = this.payService.getV3(url + query);
    return GSON.fromJson(result, FavorStocksMerchantsGetResult.class);
  }

  @Override
  public FavorStocksItemsGetResult getFavorStocksItemsV3(String stockId, String stockCreatorMchid, Integer offset, Integer limit) throws WxPayException {
    String url = String.format("%s/v3/marketing/favor/stocks/%s/items", this.payService.getPayBaseUrl(), stockId);
    String query = String.format("?stock_creator_mchid=%s&offset=%s&limit=%s", stockCreatorMchid, offset, limit);
    String result = this.payService.getV3(url + query);
    return GSON.fromJson(result, FavorStocksItemsGetResult.class);
  }

  @Override
  public FavorCouponsQueryResult queryFavorCouponsV3(FavorCouponsQueryRequest request) throws WxPayException {
    String url = String.format("%s/v3/marketing/favor/users/%s/coupons", this.payService.getPayBaseUrl(), request.getOpenid());
    String query = String.format("?appid=%s", request.getAppid());
    if (StringUtils.isNotBlank(request.getStockId())) {
      query += "&stock_id=" + request.getStockId();
    }
    if (StringUtils.isNotBlank(request.getStatus())) {
      query += "&status=" + request.getStatus();
    }
    if (StringUtils.isNotBlank(request.getCreatorMchid())) {
      query += "&creator_mchid=" + request.getCreatorMchid();
    }
    if (StringUtils.isNotBlank(request.getSenderMchid())) {
      query += "&sender_mchid=" + request.getSenderMchid();
    }
    if (StringUtils.isNotBlank(request.getAvailableMchid())) {
      query += "&available_mchid=" + request.getAvailableMchid();
    }
    if (request.getOffset() != null) {
      query += "&offset=" + request.getOffset();
    }
    if (request.getLimit() != null) {
      query += "&limit=" + request.getLimit();
    }
    String result = this.payService.getV3(url + query);
    return GSON.fromJson(result, FavorCouponsQueryResult.class);
  }

  @Override
  public FavorStocksFlowGetResult getFavorStocksUseFlowV3(String stockId) throws WxPayException {
    String url = String.format("%s/v3/marketing/favor/stocks/%s/use-flow", this.payService.getPayBaseUrl(), stockId);
    String result = this.payService.getV3(url);
    return GSON.fromJson(result, FavorStocksFlowGetResult.class);
  }

  @Override
  public FavorStocksFlowGetResult getFavorStocksRefundFlowV3(String stockId) throws WxPayException {
    String url = String.format("%s/v3/marketing/favor/stocks/%s/refund-flow", this.payService.getPayBaseUrl(), stockId);
    String result = this.payService.getV3(url);
    return GSON.fromJson(result, FavorStocksFlowGetResult.class);
  }

  @Override
  public FavorCallbacksSaveResult saveFavorCallbacksV3(FavorCallbacksSaveRequest request) throws WxPayException {
    String url = String.format("%s/v3/marketing/favor/callbacks", this.payService.getPayBaseUrl());
    RsaCryptoUtil.encryptFields(request, this.payService.getConfig().getVerifier().getValidCertificate());
    String result = this.payService.postV3WithWechatpaySerial(url, GSON.toJson(request));
    return GSON.fromJson(result, FavorCallbacksSaveResult.class);
  }

  @Override
  public FavorStocksPauseResult pauseFavorStocksV3(String stockId, FavorStocksSetRequest request) throws WxPayException {
    String url = String.format("%s/v3/marketing/favor/stocks/%s/pause", this.payService.getPayBaseUrl(), stockId);
    RsaCryptoUtil.encryptFields(request, this.payService.getConfig().getVerifier().getValidCertificate());
    String result = this.payService.postV3WithWechatpaySerial(url, GSON.toJson(request));
    return GSON.fromJson(result, FavorStocksPauseResult.class);
  }

  @Override
  public FavorStocksRestartResult restartFavorStocksV3(String stockId, FavorStocksSetRequest request) throws WxPayException {
    String url = String.format("%s/v3/marketing/favor/stocks/%s/restart", this.payService.getPayBaseUrl(), stockId);
    RsaCryptoUtil.encryptFields(request, this.payService.getConfig().getVerifier().getValidCertificate());
    String result = this.payService.postV3WithWechatpaySerial(url, GSON.toJson(request));
    return GSON.fromJson(result, FavorStocksRestartResult.class);
  }

  /**
   * 校验通知签名
   *
   * @param header 通知头信息
   * @param data   通知数据
   * @return true:校验通过 false:校验不通过
   */
  private boolean verifyNotifySign(SignatureHeader header, String data) {
    String beforeSign = String.format("%s\n%s\n%s\n", header.getTimeStamp(), header.getNonce(), data);
    return payService.getConfig().getVerifier().verify(header.getSerialNo(),
      beforeSign.getBytes(StandardCharsets.UTF_8), header.getSigned());
  }

  @Override
  public UseNotifyData parseNotifyData(String data, SignatureHeader header) throws WxPayException {
    if (Objects.nonNull(header) && !this.verifyNotifySign(header, data)) {
      throw new WxPayException("非法请求，头部信息验证失败");
    }
    return GSON.fromJson(data, UseNotifyData.class);
  }

  @Override
  public FavorCouponsUseResult decryptNotifyDataResource(UseNotifyData data) throws WxPayException {
    UseNotifyData.Resource resource = data.getResource();
    String cipherText = resource.getCipherText();
    String associatedData = resource.getAssociatedData();
    String nonce = resource.getNonce();
    String apiV3Key = this.payService.getConfig().getApiV3Key();
    try {
      return GSON.fromJson(AesUtils.decryptToString(associatedData, nonce, cipherText, apiV3Key), FavorCouponsUseResult.class);
    } catch (GeneralSecurityException | IOException e) {
      throw new WxPayException("解析报文异常！", e);
    }
  }
}
