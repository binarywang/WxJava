# 支持一个商户号对应多个 appId 的使用说明

## 背景

在实际业务中，经常会遇到一个微信支付商户号需要绑定多个小程序的场景。例如：
- 一个商家有多个小程序（主店、分店、活动小程序等）
- 所有小程序共用同一个支付商户号
- 支付配置（商户号、密钥、证书等）完全相同，只有 appId 不同

## 解决方案

WxJava 支持在配置多个相同商户号、不同 appId 的情况下，**可以仅通过商户号进行配置切换**，无需每次都指定 appId。

## 使用方式

### 1. 配置多个 appId

```java
WxPayService payService = new WxPayServiceImpl();

String mchId = "1234567890";  // 商户号

// 配置小程序1
WxPayConfig config1 = new WxPayConfig();
config1.setMchId(mchId);
config1.setAppId("wx1111111111111111");  // 小程序1的appId
config1.setMchKey("your_mch_key");
config1.setApiV3Key("your_api_v3_key");
// ... 其他配置

// 配置小程序2
WxPayConfig config2 = new WxPayConfig();
config2.setMchId(mchId);
config2.setAppId("wx2222222222222222");  // 小程序2的appId
config2.setMchKey("your_mch_key");
config2.setApiV3Key("your_api_v3_key");
// ... 其他配置

// 配置小程序3
WxPayConfig config3 = new WxPayConfig();
config3.setMchId(mchId);
config3.setAppId("wx3333333333333333");  // 小程序3的appId
config3.setMchKey("your_mch_key");
config3.setApiV3Key("your_api_v3_key");
// ... 其他配置

// 添加到配置映射
Map<String, WxPayConfig> configMap = new HashMap<>();
configMap.put(mchId + "_" + config1.getAppId(), config1);
configMap.put(mchId + "_" + config2.getAppId(), config2);
configMap.put(mchId + "_" + config3.getAppId(), config3);

payService.setMultiConfig(configMap);
```

### 2. 切换配置的方式

#### 方式一：精确切换（原有方式，向后兼容）

```java
// 切换到小程序1的配置
payService.switchover("1234567890", "wx1111111111111111");

// 切换到小程序2的配置
payService.switchover("1234567890", "wx2222222222222222");
```

#### 方式二：仅使用商户号切换（新功能）

```java
// 仅使用商户号切换，会自动匹配该商户号的某个配置
// 适用于不关心具体使用哪个 appId 的场景
boolean success = payService.switchover("1234567890");
```

**注意**：当使用仅商户号切换时，会按照以下逻辑查找配置：
1. 先尝试精确匹配商户号（针对只配置商户号、没有 appId 的情况）
2. 如果未找到，则尝试前缀匹配（查找以 `商户号_` 开头的配置）
3. 如果有多个匹配项，将返回其中任意一个匹配项，具体选择结果不保证稳定或可预测，如需确定性行为请使用精确匹配方式（同时指定商户号和 appId）

#### 方式三：链式调用

```java
// 精确切换，支持链式调用
WxPayUnifiedOrderResult result = payService
    .switchoverTo("1234567890", "wx1111111111111111")
    .unifiedOrder(request);

// 仅商户号切换，支持链式调用
WxPayUnifiedOrderResult result = payService
    .switchoverTo("1234567890")
    .unifiedOrder(request);
```

### 3. 动态添加配置

```java
// 运行时动态添加新的 appId 配置
WxPayConfig newConfig = new WxPayConfig();
newConfig.setMchId("1234567890");
newConfig.setAppId("wx4444444444444444");
// ... 其他配置

payService.addConfig("1234567890", "wx4444444444444444", newConfig);

// 切换到新添加的配置
payService.switchover("1234567890", "wx4444444444444444");
```

### 4. 移除配置

```java
// 移除特定的 appId 配置
payService.removeConfig("1234567890", "wx1111111111111111");
```

## 实际应用场景

### 场景1：根据用户来源切换 appId

```java
// 在支付前，根据订单来源切换到对应小程序的配置
String orderSource = order.getSource();  // 例如: "miniapp1", "miniapp2"
String appId = getAppIdBySource(orderSource);

// 精确切换到特定小程序
payService.switchover(mchId, appId);

// 创建订单
WxPayUnifiedOrderRequest request = new WxPayUnifiedOrderRequest();
// ... 设置订单参数
WxPayUnifiedOrderResult result = payService.unifiedOrder(request);
```

### 场景2：处理支付回调

```java
@PostMapping("/pay/notify")
public String handlePayNotify(@RequestBody String xmlData) {
    try {
        // 解析回调通知
        WxPayOrderNotifyResult notifyResult = payService.parseOrderNotifyResult(xmlData);
        
        // 注意：parseOrderNotifyResult 方法内部会自动调用 
        // switchover(notifyResult.getMchId(), notifyResult.getAppid())
        // 切换到正确的配置进行签名验证
        
        // 处理业务逻辑
        processOrder(notifyResult);
        
        return WxPayNotifyResponse.success("成功");
    } catch (WxPayException e) {
        log.error("支付回调处理失败", e);
        return WxPayNotifyResponse.fail("失败");
    }
}
```

### 场景3：不关心具体 appId 的场景

```java
// 某些场景下，只要是该商户号的配置即可，不关心具体是哪个 appId
// 例如：查询订单、退款等操作

// 仅使用商户号切换
payService.switchover(mchId);

// 查询订单
WxPayOrderQueryResult queryResult = payService.queryOrder(null, outTradeNo);

// 申请退款
WxPayRefundRequest refundRequest = new WxPayRefundRequest();
// ... 设置退款参数
WxPayRefundResult refundResult = payService.refund(refundRequest);
```

## 注意事项

1. **向后兼容**：所有原有的使用方式继续有效，不需要修改现有代码。

2. **配置隔离**：每个 `mchId + appId` 组合都是独立的配置，修改一个配置不会影响其他配置。

3. **线程安全**：配置切换使用 `WxPayConfigHolder`（基于 `ThreadLocal`），是线程安全的。

4. **自动切换**：在处理支付回调时，SDK 会自动根据回调中的 `mchId` 和 `appId` 切换到正确的配置。

5. **推荐实践**：
   - 如果知道具体的 appId，建议使用精确切换方式，避免歧义
   - 如果使用仅商户号切换，确保该商户号下至少有一个可用的配置

## 相关 API

| 方法 | 参数 | 返回值 | 说明 |
|-----|------|--------|------|
| `switchover(String mchId, String appId)` | 商户号, appId | boolean | 精确切换到指定配置 |
| `switchover(String mchId)` | 商户号 | boolean | 仅使用商户号切换 |
| `switchoverTo(String mchId, String appId)` | 商户号, appId | WxPayService | 精确切换，支持链式调用 |
| `switchoverTo(String mchId)` | 商户号 | WxPayService | 仅商户号切换，支持链式调用 |
| `addConfig(String mchId, String appId, WxPayConfig)` | 商户号, appId, 配置 | void | 动态添加配置 |
| `removeConfig(String mchId, String appId)` | 商户号, appId | void | 移除指定配置 |
