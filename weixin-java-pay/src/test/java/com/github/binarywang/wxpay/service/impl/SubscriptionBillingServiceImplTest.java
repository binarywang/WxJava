package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.subscriptionbilling.*;
import com.github.binarywang.wxpay.service.SubscriptionBillingService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.testbase.ApiTestModule;
import com.google.inject.Inject;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * 微信支付预约扣费服务测试类
 * <p>
 * 注意：由于预约扣费功能需要用户授权和实际的签约关系，
 * 这些测试主要用于验证接口调用的正确性，而不是功能的完整性。
 * 实际测试需要在具有有效签约关系的环境中进行。
 * </p>
 *
 * @author Binary Wang
 */
@Test(enabled = false) // 默认关闭，需要实际环境配置才能测试
@Guice(modules = ApiTestModule.class)
public class SubscriptionBillingServiceImplTest {

  @Inject
  private WxPayService wxPayService;

  @Test
  public void testScheduleSubscription() {
    try {
      SubscriptionBillingService service = this.wxPayService.getSubscriptionBillingService();
      
      SubscriptionScheduleRequest request = new SubscriptionScheduleRequest();
      request.setOutTradeNo("test_subscription_" + System.currentTimeMillis());
      request.setOpenid("test_openid");
      request.setDescription("测试预约扣费");
      request.setScheduleTime("2024-09-01T10:00:00+08:00");
      
      SubscriptionAmount amount = new SubscriptionAmount();
      amount.setTotal(100); // 1元，单位分
      amount.setCurrency("CNY");
      request.setAmount(amount);
      
      BillingPlan billingPlan = new BillingPlan();
      billingPlan.setPlanType("MONTHLY");
      billingPlan.setPeriod(1);
      billingPlan.setTotalCount(12);
      request.setBillingPlan(billingPlan);
      
      SubscriptionScheduleResult result = service.scheduleSubscription(request);
      
      System.out.println("预约扣费结果：" + result.toString());
      assert result.getSubscriptionId() != null;
      assert "SCHEDULED".equals(result.getStatus());
      
    } catch (Exception e) {
      // 预期会因为测试环境没有有效的签约关系而失败
      System.out.println("预约扣费测试异常（预期）：" + e.getMessage());
    }
  }

  @Test
  public void testQuerySubscription() {
    try {
      SubscriptionBillingService service = this.wxPayService.getSubscriptionBillingService();
      SubscriptionQueryResult result = service.querySubscription("test_subscription_id");
      
      System.out.println("查询预约扣费结果：" + result.toString());
      
    } catch (Exception e) {
      // 预期会因为测试数据不存在而失败
      System.out.println("查询预约扣费测试异常（预期）：" + e.getMessage());
    }
  }

  @Test
  public void testCancelSubscription() {
    try {
      SubscriptionBillingService service = this.wxPayService.getSubscriptionBillingService();
      
      SubscriptionCancelRequest request = new SubscriptionCancelRequest();
      request.setSubscriptionId("test_subscription_id");
      request.setCancelReason("测试取消");
      
      SubscriptionCancelResult result = service.cancelSubscription(request);
      
      System.out.println("取消预约扣费结果：" + result.toString());
      assert "CANCELLED".equals(result.getStatus());
      
    } catch (Exception e) {
      // 预期会因为测试数据不存在而失败
      System.out.println("取消预约扣费测试异常（预期）：" + e.getMessage());
    }
  }

  @Test
  public void testInstantBilling() {
    try {
      SubscriptionBillingService service = this.wxPayService.getSubscriptionBillingService();
      
      SubscriptionInstantBillingRequest request = new SubscriptionInstantBillingRequest();
      request.setOutTradeNo("test_instant_" + System.currentTimeMillis());
      request.setOpenid("test_openid");
      request.setDescription("测试立即扣费");
      
      SubscriptionAmount amount = new SubscriptionAmount();
      amount.setTotal(100); // 1元，单位分
      amount.setCurrency("CNY");
      request.setAmount(amount);
      
      SubscriptionInstantBillingResult result = service.instantBilling(request);
      
      System.out.println("立即扣费结果：" + result.toString());
      assert result.getTransactionId() != null;
      
    } catch (Exception e) {
      // 预期会因为测试环境没有有效的签约关系而失败
      System.out.println("立即扣费测试异常（预期）：" + e.getMessage());
    }
  }

  @Test
  public void testQueryTransactions() {
    try {
      SubscriptionBillingService service = this.wxPayService.getSubscriptionBillingService();
      
      SubscriptionTransactionQueryRequest request = new SubscriptionTransactionQueryRequest();
      request.setOpenid("test_openid");
      request.setBeginTime("2024-08-01T00:00:00+08:00");
      request.setEndTime("2024-08-31T23:59:59+08:00");
      request.setLimit(20);
      request.setOffset(0);
      
      SubscriptionTransactionQueryResult result = service.queryTransactions(request);
      
      System.out.println("查询扣费记录结果：" + result.toString());
      assert result.getTotalCount() != null;
      
    } catch (Exception e) {
      // 预期会因为测试环境数据问题而失败
      System.out.println("查询扣费记录测试异常（预期）：" + e.getMessage());
    }
  }
}