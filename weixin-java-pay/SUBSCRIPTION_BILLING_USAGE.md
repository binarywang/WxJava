# 微信支付预约扣费功能使用说明

## 概述

微信支付预约扣费功能（连续包月功能）允许商户在用户授权的情况下，按照约定的时间和金额，自动从用户的支付账户中扣取费用。主要适用于连续包月、订阅服务等场景。

## 功能特性

- **预约扣费**：创建未来某个时间点的扣费计划
- **查询预约**：查询已创建的扣费计划状态
- **取消预约**：取消已创建的扣费计划
- **立即扣费**：立即执行扣费操作
- **扣费记录查询**：查询历史扣费记录

## 快速开始

### 1. 获取服务实例

```java
// 通过 WxPayService 获取预约扣费服务
SubscriptionBillingService subscriptionService = wxPayService.getSubscriptionBillingService();
```

### 2. 创建预约扣费

```java
// 创建预约扣费请求
SubscriptionScheduleRequest request = new SubscriptionScheduleRequest();
request.setOutTradeNo("subscription_" + System.currentTimeMillis());
request.setOpenid("用户的openid");
request.setDescription("腾讯视频VIP会员");
request.setScheduleTime("2024-09-01T10:00:00+08:00");

// 设置扣费金额
SubscriptionAmount amount = new SubscriptionAmount();
amount.setTotal(3000); // 30元，单位为分
amount.setCurrency("CNY");
request.setAmount(amount);

// 设置扣费计划（可选）
BillingPlan billingPlan = new BillingPlan();
billingPlan.setPlanType("MONTHLY"); // 按月扣费
billingPlan.setPeriod(1); // 每1个月
billingPlan.setTotalCount(12); // 总共12次
request.setBillingPlan(billingPlan);

// 发起预约扣费
SubscriptionScheduleResult result = subscriptionService.scheduleSubscription(request);
System.out.println("预约扣费ID: " + result.getSubscriptionId());
```

### 3. 查询预约扣费

```java
// 通过预约扣费ID查询
String subscriptionId = "从预约扣费结果中获取的ID";
SubscriptionQueryResult queryResult = subscriptionService.querySubscription(subscriptionId);
System.out.println("预约状态: " + queryResult.getStatus());
```

### 4. 取消预约扣费

```java
// 创建取消请求
SubscriptionCancelRequest cancelRequest = new SubscriptionCancelRequest();
cancelRequest.setSubscriptionId(subscriptionId);
cancelRequest.setCancelReason("用户主动取消");

// 取消预约扣费
SubscriptionCancelResult cancelResult = subscriptionService.cancelSubscription(cancelRequest);
System.out.println("取消结果: " + cancelResult.getStatus());
```

### 5. 立即扣费

```java
// 创建立即扣费请求
SubscriptionInstantBillingRequest instantRequest = new SubscriptionInstantBillingRequest();
instantRequest.setOutTradeNo("instant_" + System.currentTimeMillis());
instantRequest.setOpenid("用户的openid");
instantRequest.setDescription("补扣上月会员费");

// 设置扣费金额
SubscriptionAmount instantAmount = new SubscriptionAmount();
instantAmount.setTotal(3000); // 30元
instantAmount.setCurrency("CNY");
instantRequest.setAmount(instantAmount);

// 执行立即扣费
SubscriptionInstantBillingResult instantResult = subscriptionService.instantBilling(instantRequest);
System.out.println("扣费结果: " + instantResult.getTradeState());
```

### 6. 查询扣费记录

```java
// 创建查询请求
SubscriptionTransactionQueryRequest queryRequest = new SubscriptionTransactionQueryRequest();
queryRequest.setOpenid("用户的openid");
queryRequest.setBeginTime("2024-08-01T00:00:00+08:00");
queryRequest.setEndTime("2024-08-31T23:59:59+08:00");
queryRequest.setLimit(20);
queryRequest.setOffset(0);

// 查询扣费记录
SubscriptionTransactionQueryResult transactionResult = subscriptionService.queryTransactions(queryRequest);
System.out.println("总记录数: " + transactionResult.getTotalCount());
for (SubscriptionTransactionQueryResult.SubscriptionTransaction transaction : transactionResult.getData()) {
    System.out.println("订单号: " + transaction.getOutTradeNo() + ", 状态: " + transaction.getTradeState());
}
```

## 扣费计划类型

- `MONTHLY`：按月扣费
- `WEEKLY`：按周扣费  
- `DAILY`：按日扣费
- `YEARLY`：按年扣费

## 预约状态说明

- `SCHEDULED`：已预约
- `CANCELLED`：已取消
- `EXECUTED`：已执行
- `FAILED`：执行失败

## 交易状态说明

- `SUCCESS`：支付成功
- `REFUND`：转入退款
- `NOTPAY`：未支付
- `CLOSED`：已关闭
- `REVOKED`：已撤销(刷卡支付)
- `USERPAYING`：用户支付中
- `PAYERROR`：支付失败

## 注意事项

1. **用户授权**：使用预约扣费功能前，需要用户在微信内完成签约授权
2. **商户资质**：需要具备相应的业务资质才能开通此功能
3. **金额限制**：扣费金额需要在签约模板规定的范围内
4. **频率限制**：API调用有频率限制，请注意控制调用频次
5. **异常处理**：建议对所有API调用进行异常处理

## 相关文档

- [微信支付预约扣费API文档](https://pay.weixin.qq.com/doc/v3/merchant/4012161105)
- [微信支付开发指南](https://pay.weixin.qq.com/wiki/doc/apiv3/index.shtml)

## 示例完整代码

```java
import com.github.binarywang.wxpay.service.SubscriptionBillingService;
import com.github.binarywang.wxpay.bean.subscriptionbilling.*;

public class SubscriptionBillingExample {
    
    private SubscriptionBillingService subscriptionService;
    
    public void example() throws Exception {
        // 1. 创建预约扣费
        SubscriptionScheduleRequest request = new SubscriptionScheduleRequest();
        request.setOutTradeNo("subscription_" + System.currentTimeMillis());
        request.setOpenid("用户openid");
        request.setDescription("VIP会员续费");
        request.setScheduleTime("2024-09-01T10:00:00+08:00");
        
        SubscriptionAmount amount = new SubscriptionAmount();
        amount.setTotal(3000);
        amount.setCurrency("CNY");
        request.setAmount(amount);
        
        BillingPlan plan = new BillingPlan();
        plan.setPlanType("MONTHLY");
        plan.setPeriod(1);
        plan.setTotalCount(12);
        request.setBillingPlan(plan);
        
        SubscriptionScheduleResult result = subscriptionService.scheduleSubscription(request);
        
        // 2. 查询预约状态
        SubscriptionQueryResult query = subscriptionService.querySubscription(result.getSubscriptionId());
        
        // 3. 如需取消
        if ("SCHEDULED".equals(query.getStatus())) {
            SubscriptionCancelRequest cancelReq = new SubscriptionCancelRequest();
            cancelReq.setSubscriptionId(result.getSubscriptionId());
            cancelReq.setCancelReason("用户取消");
            
            SubscriptionCancelResult cancelResult = subscriptionService.cancelSubscription(cancelReq);
        }
    }
}
```