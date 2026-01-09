package com.github.binarywang.wxpay.service;

import com.github.binarywang.wxpay.bean.subscriptionbilling.*;
import com.github.binarywang.wxpay.exception.WxPayException;

/**
 * 微信支付-预约扣费服务 (连续包月功能)
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
public interface SubscriptionBillingService {

  /**
   * 预约扣费
   * <pre>
   *   商户可以通过该接口预约未来某个时间点的扣费。
   *   适用于连续包月、订阅服务等场景。
   *   
   *   文档详见: https://pay.weixin.qq.com/doc/v3/merchant/4012161105
   *   请求URL: https://api.mch.weixin.qq.com/v3/subscription-billing/schedule
   *   请求方式: POST
   *   是否需要证书: 是
   * </pre>
   *
   * @param request 预约扣费请求参数
   * @return 预约扣费结果
   * @throws WxPayException 微信支付异常
   */
  SubscriptionScheduleResult scheduleSubscription(SubscriptionScheduleRequest request) throws WxPayException;

  /**
   * 查询预约扣费
   * <pre>
   *   商户可以通过该接口查询已预约的扣费信息。
   *   
   *   文档详见: https://pay.weixin.qq.com/doc/v3/merchant/4012161105
   *   请求URL: https://api.mch.weixin.qq.com/v3/subscription-billing/schedule/{subscription_id}
   *   请求方式: GET
   * </pre>
   *
   * @param subscriptionId 预约扣费ID
   * @return 预约扣费查询结果
   * @throws WxPayException 微信支付异常
   */
  SubscriptionQueryResult querySubscription(String subscriptionId) throws WxPayException;

  /**
   * 取消预约扣费
   * <pre>
   *   商户可以通过该接口取消已预约的扣费。
   *   
   *   文档详见: https://pay.weixin.qq.com/doc/v3/merchant/4012161105
   *   请求URL: https://api.mch.weixin.qq.com/v3/subscription-billing/schedule/{subscription_id}/cancel
   *   请求方式: POST
   *   是否需要证书: 是
   * </pre>
   *
   * @param request 取消预约扣费请求参数
   * @return 取消预约扣费结果
   * @throws WxPayException 微信支付异常
   */
  SubscriptionCancelResult cancelSubscription(SubscriptionCancelRequest request) throws WxPayException;

  /**
   * 立即扣费
   * <pre>
   *   商户可以通过该接口立即执行扣费操作。
   *   通常用于补扣失败的费用或者特殊情况下的即时扣费。
   *   
   *   文档详见: https://pay.weixin.qq.com/doc/v3/merchant/4012161105
   *   请求URL: https://api.mch.weixin.qq.com/v3/subscription-billing/instant-billing
   *   请求方式: POST
   *   是否需要证书: 是
   * </pre>
   *
   * @param request 立即扣费请求参数
   * @return 立即扣费结果
   * @throws WxPayException 微信支付异常
   */
  SubscriptionInstantBillingResult instantBilling(SubscriptionInstantBillingRequest request) throws WxPayException;

  /**
   * 查询扣费记录
   * <pre>
   *   商户可以通过该接口查询扣费记录。
   *   
   *   文档详见: https://pay.weixin.qq.com/doc/v3/merchant/4012161105
   *   请求URL: https://api.mch.weixin.qq.com/v3/subscription-billing/transactions
   *   请求方式: GET
   * </pre>
   *
   * @param request 查询扣费记录请求参数
   * @return 扣费记录查询结果
   * @throws WxPayException 微信支付异常
   */
  SubscriptionTransactionQueryResult queryTransactions(SubscriptionTransactionQueryRequest request) throws WxPayException;
}