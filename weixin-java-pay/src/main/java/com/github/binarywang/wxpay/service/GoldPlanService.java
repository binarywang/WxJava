package com.github.binarywang.wxpay.service;

import com.github.binarywang.wxpay.bean.goldplan.GoldPlanResult;
import com.github.binarywang.wxpay.exception.WxPayException;

import java.util.List;

/**
 * 点金计划 接口
 * <p>
 * <a href="https://pay.weixin.qq.com/doc/v3/partner/4012072130">产品介绍</a>
 * </p>
 *
 * @author zhangyl
 * @since 2026-08-22
 */
public interface GoldPlanService {

  /**
   * 为特约商户开通点金计划
   * <p>
   * <a href="https://pay.weixin.qq.com/doc/v3/partner/4012473796">接口文档</a>
   * </p>
   *
   * @param subMchId          特约商户号
   * @param operationPayScene 支付场景，可选值为JSAPI_AND_MINIPROGRAM、JSAPI、MINIPROGRAM；不传时默认为JSAPI
   * @return 点金计划操作结果
   * @throws WxPayException 微信支付请求异常
   */
  GoldPlanResult openGoldPlan(String subMchId, String operationPayScene) throws WxPayException;

  /**
   * 为特约商户关闭点金计划
   * <p>
   * <a href="https://pay.weixin.qq.com/doc/v3/partner/4012473796">接口文档</a>
   * </p>
   *
   * @param subMchId          特约商户号
   * @param operationPayScene 支付场景，可选值为JSAPI_AND_MINIPROGRAM、JSAPI、MINIPROGRAM；不传时默认为JSAPI
   * @return 点金计划操作结果
   * @throws WxPayException 微信支付请求异常
   */
  GoldPlanResult closeGoldPlan(String subMchId, String operationPayScene) throws WxPayException;

  /**
   * 为特约商户开通商家小票
   * <p>
   * <a href="https://pay.weixin.qq.com/doc/v3/partner/4012473788">接口文档</a>
   * </p>
   *
   * @param subMchId 特约商户号
   * @return 商家小票操作结果
   * @throws WxPayException 微信支付请求异常
   */
  GoldPlanResult openCustomPage(String subMchId) throws WxPayException;

  /**
   * 为特约商户关闭商家小票
   * <p>
   * <a href="https://pay.weixin.qq.com/doc/v3/partner/4012473788">接口文档</a>
   * </p>
   *
   * @param subMchId 特约商户号
   * @return 商家小票操作结果
   * @throws WxPayException 微信支付请求异常
   */
  GoldPlanResult closeCustomPage(String subMchId) throws WxPayException;

  /**
   * 设置特约商户的点金计划同业过滤标签
   * <p>
   * <a href="https://pay.weixin.qq.com/doc/v3/partner/4012473784">接口文档</a>
   * </p>
   *
   * @param subMchId                   特约商户号
   * @param advertisingIndustryFilters 同业过滤标签，最少一个，最多三个
   * @throws WxPayException 微信支付请求异常
   */
  void setAdvertisingIndustryFilter(String subMchId, List<String> advertisingIndustryFilters) throws WxPayException;

  /**
   * 为特约商户的点金计划页面开通广告展示
   * <p>
   * <a href="https://pay.weixin.qq.com/doc/v3/partner/4012473794">接口文档</a>
   * </p>
   *
   * @param subMchId                   特约商户号
   * @param advertisingIndustryFilters 同业过滤标签，可选，最多三个
   * @throws WxPayException 微信支付请求异常
   */
  void openAdvertisingShow(String subMchId, List<String> advertisingIndustryFilters) throws WxPayException;

  /**
   * 为特约商户的点金计划页面关闭广告展示
   * <p>
   * <a href="https://pay.weixin.qq.com/doc/v3/partner/4012473781">接口文档</a>
   * </p>
   *
   * @param subMchId 特约商户号
   * @throws WxPayException 微信支付请求异常
   */
  void closeAdvertisingShow(String subMchId) throws WxPayException;

}
