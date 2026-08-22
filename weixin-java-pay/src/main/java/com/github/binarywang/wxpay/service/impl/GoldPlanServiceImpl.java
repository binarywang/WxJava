package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.goldplan.GoldPlanResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.GoldPlanService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 点金计划 接口实现
 *
 * @author zhangyl
 * @since 2026-08-22
 */
@RequiredArgsConstructor
public class GoldPlanServiceImpl implements GoldPlanService {
  private static final Gson GSON = new GsonBuilder().create();
  private static final String OPEN = "OPEN";
  private static final String CLOSE = "CLOSE";

  private final WxPayService payService;

  @Override
  public GoldPlanResult openGoldPlan(String subMchId, String operationPayScene) throws WxPayException {
    return changeGoldPlanStatus(subMchId, OPEN, operationPayScene);
  }

  /**
   * 调用微信支付接口开通或关闭点金计划
   *
   * @param subMchId          特约商户号
   * @param operationType     操作类型
   * @param operationPayScene 支付场景
   * @return 点金计划操作结果
   * @throws WxPayException 微信支付请求异常
   */
  private GoldPlanResult changeGoldPlanStatus(String subMchId, String operationType,
                                              String operationPayScene) throws WxPayException {
    String url = String.format("%s/v3/goldplan/merchants/changegoldplanstatus", this.payService.getPayBaseUrl());
    Map<String, Object> request = new HashMap<>(4);
    request.put("sub_mchid", subMchId);
    request.put("operation_type", operationType);
    request.put("operation_pay_scene", operationPayScene);
    String result = this.payService.postV3(url, GSON.toJson(request));
    return GSON.fromJson(result, GoldPlanResult.class);
  }

  @Override
  public GoldPlanResult closeGoldPlan(String subMchId, String operationPayScene) throws WxPayException {
    return changeGoldPlanStatus(subMchId, CLOSE, operationPayScene);
  }

  @Override
  public GoldPlanResult openCustomPage(String subMchId) throws WxPayException {
    return changeCustomPageStatus(subMchId, OPEN);
  }

  /**
   * 调用微信支付接口开通或关闭商家小票
   *
   * @param subMchId      特约商户号
   * @param operationType 操作类型
   * @return 商家小票操作结果
   * @throws WxPayException 微信支付请求异常
   */
  private GoldPlanResult changeCustomPageStatus(String subMchId, String operationType) throws WxPayException {
    String url = String.format("%s/v3/goldplan/merchants/changecustompagestatus", this.payService.getPayBaseUrl());
    Map<String, String> request = new HashMap<>(2);
    request.put("sub_mchid", subMchId);
    request.put("operation_type", operationType);
    String result = this.payService.postV3(url, GSON.toJson(request));
    return GSON.fromJson(result, GoldPlanResult.class);
  }

  @Override
  public GoldPlanResult closeCustomPage(String subMchId) throws WxPayException {
    return changeCustomPageStatus(subMchId, CLOSE);
  }

  @Override
  public void setAdvertisingIndustryFilter(String subMchId, List<String> advertisingIndustryFilters)
    throws WxPayException {
    String url = String.format("%s/v3/goldplan/merchants/set-advertising-industry-filter",
      this.payService.getPayBaseUrl());
    Map<String, Object> request = new HashMap<>(2);
    request.put("sub_mchid", subMchId);
    request.put("advertising_industry_filters", advertisingIndustryFilters);
    this.payService.postV3(url, GSON.toJson(request));
  }

  @Override
  public void openAdvertisingShow(String subMchId, List<String> advertisingIndustryFilters) throws WxPayException {
    String url = String.format("%s/v3/goldplan/merchants/open-advertising-show", this.payService.getPayBaseUrl());
    Map<String, Object> request = new HashMap<>(2);
    request.put("sub_mchid", subMchId);
    request.put("advertising_industry_filters", advertisingIndustryFilters);
    this.payService.patchV3(url, GSON.toJson(request));
  }

  @Override
  public void closeAdvertisingShow(String subMchId) throws WxPayException {
    String url = String.format("%s/v3/goldplan/merchants/close-advertising-show", this.payService.getPayBaseUrl());
    Map<String, String> request = new HashMap<>(1);
    request.put("sub_mchid", subMchId);
    this.payService.postV3(url, GSON.toJson(request));
  }
}
