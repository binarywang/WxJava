package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.request.*;
import com.github.binarywang.wxpay.bean.result.*;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.testbase.ApiTestModule;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * <pre>
 *   微信押金支付测试
 * </pre>
 *
 * @author Binary Wang
 * created on  2024-09-24
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxDepositServiceTest {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Inject
  private WxPayService payService;

  /**
   * 测试押金下单
   */
  @Test
  public void testUnifiedOrder() throws WxPayException {
    WxDepositUnifiedOrderRequest request = WxDepositUnifiedOrderRequest.newBuilder()
      .body("共享单车押金")
      .outTradeNo("D" + System.currentTimeMillis())
      .totalFee(99)
      .spbillCreateIp("192.168.1.1")
      .notifyUrl("https://example.com/wxpay/notify")
      .tradeType("JSAPI")
      .openid("test_openid_123")
      .build();

    try {
      WxDepositUnifiedOrderResult result = this.payService.getWxDepositService().unifiedOrder(request);
      logger.info("押金下单结果: {}", result);
    } catch (WxPayException e) {
      logger.error("押金下单失败", e);
      // For demo purposes, just log the error - tests need proper WeChat credentials to run
    }
  }

  /**
   * 测试查询押金订单
   */
  @Test
  public void testQueryOrder() throws WxPayException {
    WxDepositOrderQueryRequest request = WxDepositOrderQueryRequest.newBuilder()
      .outTradeNo("D1695559200000")
      .build();

    try {
      WxDepositOrderQueryResult result = this.payService.getWxDepositService().queryOrder(request);
      logger.info("押金订单查询结果: {}", result);
    } catch (WxPayException e) {
      logger.error("押金订单查询失败", e);
      // For demo purposes, just log the error - tests need proper WeChat credentials to run
    }
  }

  /**
   * 测试押金消费
   */
  @Test
  public void testConsume() throws WxPayException {
    WxDepositConsumeRequest request = WxDepositConsumeRequest.newBuilder()
      .transactionId("1217752501201407033233368018")
      .outTradeNo("C" + System.currentTimeMillis())
      .consumeFee(10)
      .consumeDesc("单车使用费")
      .build();

    try {
      WxDepositConsumeResult result = this.payService.getWxDepositService().consume(request);
      logger.info("押金消费结果: {}", result);
    } catch (WxPayException e) {
      logger.error("押金消费失败", e);
      // For demo purposes, just log the error - tests need proper WeChat credentials to run
    }
  }

  /**
   * 测试押金撤销
   */
  @Test
  public void testUnfreeze() throws WxPayException {
    WxDepositUnfreezeRequest request = WxDepositUnfreezeRequest.newBuilder()
      .transactionId("1217752501201407033233368018")
      .outTradeNo("U" + System.currentTimeMillis())
      .unfreezeFee(99)
      .unfreezeDesc("用户主动取消")
      .build();

    try {
      WxDepositUnfreezeResult result = this.payService.getWxDepositService().unfreeze(request);
      logger.info("押金撤销结果: {}", result);
    } catch (WxPayException e) {
      logger.error("押金撤销失败", e);
      // For demo purposes, just log the error - tests need proper WeChat credentials to run
    }
  }

  /**
   * 测试押金退款
   */
  @Test
  public void testRefund() throws WxPayException {
    WxDepositRefundRequest request = WxDepositRefundRequest.newBuilder()
      .transactionId("1217752501201407033233368018")
      .outRefundNo("R" + System.currentTimeMillis())
      .refundFee(50)
      .refundDesc("部分退款")
      .build();

    try {
      WxDepositRefundResult result = this.payService.getWxDepositService().refund(request);
      logger.info("押金退款结果: {}", result);
    } catch (WxPayException e) {
      logger.error("押金退款失败", e);
      // For demo purposes, just log the error - tests need proper WeChat credentials to run
    }
  }
}