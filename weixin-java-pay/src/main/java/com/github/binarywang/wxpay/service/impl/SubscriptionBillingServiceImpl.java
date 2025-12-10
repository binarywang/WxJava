package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.subscriptionbilling.*;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.SubscriptionBillingService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 微信支付-预约扣费服务实现 (连续包月功能)
 * <pre>
 *   微信支付预约扣费功能，支持商户在用户授权的情况下，
 *   按照约定的时间和金额，自动从用户的支付账户中扣取费用。
 *   主要用于连续包月、订阅服务等场景。
 *   
 *   文档详见: https://pay.weixin.qq.com/doc/v3/merchant/4012161105
 * </pre>
 *
 * @author Binary Wang
 * created on  2024-08-31
 */
@Slf4j
@RequiredArgsConstructor
public class SubscriptionBillingServiceImpl implements SubscriptionBillingService {

  private static final Gson GSON = new GsonBuilder().create();
  private final WxPayService payService;

  @Override
  public SubscriptionScheduleResult scheduleSubscription(SubscriptionScheduleRequest request) throws WxPayException {
    String url = String.format("%s/v3/subscription-billing/schedule", this.payService.getPayBaseUrl());
    String response = this.payService.postV3(url, GSON.toJson(request));
    return GSON.fromJson(response, SubscriptionScheduleResult.class);
  }

  @Override
  public SubscriptionQueryResult querySubscription(String subscriptionId) throws WxPayException {
    String url = String.format("%s/v3/subscription-billing/schedule/%s", this.payService.getPayBaseUrl(), subscriptionId);
    String response = this.payService.getV3(url);
    return GSON.fromJson(response, SubscriptionQueryResult.class);
  }

  @Override
  public SubscriptionCancelResult cancelSubscription(SubscriptionCancelRequest request) throws WxPayException {
    String url = String.format("%s/v3/subscription-billing/schedule/%s/cancel", 
        this.payService.getPayBaseUrl(), request.getSubscriptionId());
    String response = this.payService.postV3(url, GSON.toJson(request));
    return GSON.fromJson(response, SubscriptionCancelResult.class);
  }

  @Override
  public SubscriptionInstantBillingResult instantBilling(SubscriptionInstantBillingRequest request) throws WxPayException {
    String url = String.format("%s/v3/subscription-billing/instant-billing", this.payService.getPayBaseUrl());
    String response = this.payService.postV3(url, GSON.toJson(request));
    return GSON.fromJson(response, SubscriptionInstantBillingResult.class);
  }

  @Override
  public SubscriptionTransactionQueryResult queryTransactions(SubscriptionTransactionQueryRequest request) throws WxPayException {
    String url = String.format("%s/v3/subscription-billing/transactions", this.payService.getPayBaseUrl());
    
    StringBuilder queryString = new StringBuilder();
    if (request.getOpenid() != null) {
      queryString.append("openid=").append(request.getOpenid()).append("&");
    }
    if (request.getBeginTime() != null) {
      queryString.append("begin_time=").append(request.getBeginTime()).append("&");
    }
    if (request.getEndTime() != null) {
      queryString.append("end_time=").append(request.getEndTime()).append("&");
    }
    if (request.getLimit() != null) {
      queryString.append("limit=").append(request.getLimit()).append("&");
    }
    if (request.getOffset() != null) {
      queryString.append("offset=").append(request.getOffset()).append("&");
    }
    
    if (queryString.length() > 0) {
      // Remove trailing &
      queryString.setLength(queryString.length() - 1);
      url += "?" + queryString.toString();
    }
    
    String response = this.payService.getV3(url);
    return GSON.fromJson(response, SubscriptionTransactionQueryResult.class);
  }
}