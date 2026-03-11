# 支持一个商户号对应多个 appId 及自定义配置键的使用说明

## 背景

在实际业务中，经常会遇到一个微信支付商户号需要绑定多个小程序的场景。例如：
- 一个商家有多个小程序（主店、分店、活动小程序等）
- 所有小程序共用同一个支付商户号
- 支付配置（商户号、密钥、证书等）完全相同，只有 appId 不同

此外，也存在多租户（SaaS）场景，需要以自定义唯一键（如租户ID）来管理多个不同商户的配置。

## 解决方案

WxJava 支持以下几种多配置管理方式：

1. **mchId + appId 精确匹配**：适用于一个商户号对应多个 appId 的场景
2. **仅使用商户号（mchId）匹配**：适用于一个商户号对应单个 appId 或不关心具体 appId 的场景
3. **自定义唯一键**：适用于多租户场景，使用任意字符串（如租户ID）作为配置键

## 使用方式

### 1. 配置多个 appId（按 mchId + appId 格式）

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

### 2. 使用自定义唯一键配置（多租户场景）

适用于多租户 SaaS 系统，使用任意唯一标识符（如租户ID）管理不同商户的配置：

```java
WxPayService payService = new WxPayServiceImpl();

// 使用租户ID作为配置键
WxPayConfig tenant1Config = new WxPayConfig();
tenant1Config.setMchId("1234567890");
tenant1Config.setAppId("wx1111111111111111");
tenant1Config.setMchKey("tenant1_mch_key");
// ... 其他配置

WxPayConfig tenant2Config = new WxPayConfig();
tenant2Config.setMchId("9876543210");
tenant2Config.setAppId("wx2222222222222222");
tenant2Config.setMchKey("tenant2_mch_key");
// ... 其他配置

// 方式一：通过 setMultiConfig 批量设置
Map<String, WxPayConfig> configMap = new HashMap<>();
configMap.put("tenant_001", tenant1Config);
configMap.put("tenant_002", tenant2Config);
payService.setMultiConfig(configMap);

// 方式二：通过 addConfig(key, config) 逐个添加（推荐，兼容单参数 switchover 方式）
payService.addConfig("tenant_001", tenant1Config);
payService.addConfig("tenant_002", tenant2Config);

// 切换配置：直接使用自定义键
payService.switchover("tenant_001");
WxPayConfig config = payService.getConfig();  // 获取 tenant_001 对应的配置

// 链式调用
WxPayUnifiedOrderResult result = payService
    .switchoverTo("tenant_001")
    .unifiedOrder(request);
```

### 3. 获取配置的方式

#### 方式一：直接获取配置（推荐，新功能）

直接通过商户号和 appId 获取配置，**不依赖 ThreadLocal**，适用于多商户管理场景：

```java
// 精确获取指定商户号和 appId 的配置
WxPayConfig config1 = payService.getConfig("1234567890", "wx1111111111111111");

// 仅使用商户号获取配置（会返回该商户号的任意一个配置）
// 注意：当存在多个 appId 时，返回结果基于内部存储顺序，不应依赖其稳定性
WxPayConfig config = payService.getConfig("1234567890");

// 使用获取的配置读取信息（仅用于读取配置，不用于执行支付操作）
if (config != null) {
  String appId = config.getAppId();
  String mchKey = config.getMchKey();
  String apiV3Key = config.getApiV3Key();
  // ... 使用配置信息进行业务逻辑判断或记录
}
```

**优势**：
- 不依赖 ThreadLocal，可以在任何上下文中使用
- 适合在异步场景、线程池等环境中使用
- 线程安全，不会因为线程切换导致配置丢失
- 可以同时获取多个不同的配置

**使用场景**：
- 仅需读取配置信息（如获取 mchKey、appId 等）
- 不需要执行 WxPayService 的支付相关方法
- 如需执行支付操作，请使用方式二的 switchover 方法

#### 方式二：切换配置后使用（原有方式）

通过切换配置，然后调用 `getConfig()` 获取当前配置或直接执行支付操作：

```java
// 精确切换到指定的配置
payService.switchover("1234567890", "wx1111111111111111");
WxPayConfig config = payService.getConfig();  // 获取当前切换的配置

// 仅使用商户号切换
payService.switchover("1234567890");
config = payService.getConfig();  // 获取切换后的配置

// 切换后可直接执行支付操作
WxPayUnifiedOrderResult result = payService.unifiedOrder(request);
```

**注意**：此方式依赖 ThreadLocal，需要注意线程上下文的问题。

**使用场景**：
- 需要执行 WxPayService 的支付相关方法（如 unifiedOrder、refund 等）
- 在同一线程中连续执行多个支付操作

### 3. 切换配置的方式

#### 方式一：精确切换（原有方式，向后兼容）

```java
// 切换到小程序1的配置
payService.switchover("1234567890", "wx1111111111111111");

// 切换到小程序2的配置
payService.switchover("1234567890", "wx2222222222222222");
```

#### 方式二：仅使用商户号切换

```java
// 仅使用商户号切换，会自动匹配该商户号的某个配置
// 适用于不关心具体使用哪个 appId 的场景
boolean success = payService.switchover("1234567890");
```

**注意**：当使用仅商户号切换时，会按照以下逻辑查找配置：
1. 先尝试精确匹配商户号（针对只配置商户号、没有 appId 的情况）
2. 如果未找到，则尝试前缀匹配（查找以 `商户号_` 开头的配置）
3. 如果有多个匹配项，将返回其中任意一个匹配项，具体选择结果不保证稳定或可预测，如需确定性行为请使用精确匹配方式（同时指定商户号和 appId）

#### 方式三：使用自定义唯一键切换（多租户场景）

```java
// 使用通过 addConfig(key, config) 或 setMultiConfig(map) 注册的自定义键切换
boolean success = payService.switchover("tenant_001");

// 链式调用
WxPayUnifiedOrderResult result = payService
    .switchoverTo("tenant_001")
    .unifiedOrder(request);
```

#### 方式四：链式调用

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

### 4. 动态添加配置

```java
// 方式一：使用 mchId + appId 添加配置
WxPayConfig newConfig = new WxPayConfig();
newConfig.setMchId("1234567890");
newConfig.setAppId("wx4444444444444444");
// ... 其他配置

payService.addConfig("1234567890", "wx4444444444444444", newConfig);

// 切换到新添加的配置
payService.switchover("1234567890", "wx4444444444444444");

// 方式二：使用自定义唯一键添加配置（多租户场景）
WxPayConfig tenantConfig = new WxPayConfig();
tenantConfig.setMchId("1234567890");
tenantConfig.setAppId("wx4444444444444444");
// ... 其他配置

payService.addConfig("tenant_003", tenantConfig);

// 使用自定义键切换
payService.switchover("tenant_003");
```

### 5. 移除配置

```java
// 方式一：使用 mchId + appId 移除配置
payService.removeConfig("1234567890", "wx1111111111111111");

// 方式二：使用自定义唯一键移除配置（多租户场景）
payService.removeConfig("tenant_001");
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
        // 若回调中 appId 为空，会自动降级为仅使用 mchId 匹配
        
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

### 场景4：多商户管理（推荐使用直接获取配置）

```java
// 在多商户管理系统中，可以直接获取指定商户的配置
// 这种方式不依赖 ThreadLocal，适合异步场景和线程池环境

public void processMerchantOrder(String mchId, String appId, Order order) {
    // 直接获取配置，无需切换
    WxPayConfig config = payService.getConfig(mchId, appId);
    
    if (config == null) {
        log.error("找不到商户配置：mchId={}, appId={}", mchId, appId);
        return;
    }
    
    // 使用配置信息
    String merchantKey = config.getMchKey();
    String apiV3Key = config.getApiV3Key();
    
    // ... 处理订单逻辑
}

// 或者在不确定 appId 的情况下，仅通过商户号发起退款
public void processRefund(String mchId, String outTradeNo) {
    // 直接根据商户号切换（内部会选择该商户号下的一个配置）
    if (!payService.switchover(mchId)) {
        log.error("商户配置切换失败：mchId={}", mchId);
        return;
    }
    
    // 在完成上下文切换后，执行退款操作
    WxPayRefundRequest request = new WxPayRefundRequest();
    request.setOutTradeNo(outTradeNo);
    // ... 设置其他退款参数
    WxPayRefundResult refundResult = payService.refund(request);
}
```

## 新增方法对比

| 方法 | 说明 | 是否依赖 ThreadLocal | 适用场景 |
|-----|------|---------------------|---------|
| `getConfig()` | 获取当前配置 | 是 | 单线程同步场景 |
| `getConfig(String mchId, String appId)` | 直接获取指定配置 | **否** | 多商户管理、异步场景、线程池 |
| `getConfig(String mchId)` | 根据商户号获取配置 | **否** | 不确定 appId 的场景 |
| `switchover(String mchId, String appId)` | 精确切换配置 | 是 | 需要切换上下文的场景 |
| `switchover(String mchIdOrKey)` | 根据商户号或自定义键切换 | 是 | 不关心 appId 或多租户场景 |

## 注意事项

1. **向后兼容**：所有原有的使用方式继续有效，不需要修改现有代码。

2. **自定义键支持**：可以通过 `addConfig(String configKey, WxPayConfig)` 或 `setMultiConfig(Map)` 注册任意键，
   然后直接用 `switchover(key)` 进行精确匹配切换，无需关心 mchId 或 appId 的格式。

3. **通知回调兼容**：当 `switchover(mchId, appId)` 的 appId 为空（通知回调中可能出现此情况）时，
   SDK 会自动降级为仅使用 mchId 进行匹配，避免因 appId 缺失导致的切换失败。

4. **配置隔离**：每个配置键对应独立的配置，修改一个配置不会影响其他配置。

5. **线程安全**：
   - 配置切换使用 `WxPayConfigHolder`（基于 `ThreadLocal`），是线程安全的
   - 直接获取配置方法（`getConfig(mchId, appId)`）不依赖 ThreadLocal，可以在任何上下文中安全使用

6. **自动切换**：在处理支付回调时，SDK 会自动根据回调中的 `mchId` 和 `appId` 切换到正确的配置。

7. **推荐实践**：
   - 多租户 SaaS 场景：使用自定义键（如租户ID）管理配置，通过 `switchover(tenantId)` 切换
   - 一商户多 appId 场景：使用 `mchId_appId` 格式的键，通过 `switchover(mchId, appId)` 精确切换
   - 在多商户管理、异步场景、线程池等环境中，建议使用 `getConfig(mchId, appId)` 直接获取配置

## 相关 API

| 方法 | 参数 | 返回值 | 说明 |
|-----|------|--------|------|
| `getConfig()` | 无 | WxPayConfig | 获取当前配置（依赖 ThreadLocal） |
| `getConfig(String mchId, String appId)` | 商户号, appId | WxPayConfig | 直接获取指定配置（不依赖 ThreadLocal） |
| `getConfig(String mchId)` | 商户号 | WxPayConfig | 根据商户号获取配置（不依赖 ThreadLocal） |
| `switchover(String mchId, String appId)` | 商户号, appId | boolean | 精确切换到指定配置；appId 为空时自动降级为仅 mchId 匹配 |
| `switchover(String mchIdOrKey)` | 商户号或自定义键 | boolean | 根据商户号或自定义键切换 |
| `switchoverTo(String mchId, String appId)` | 商户号, appId | WxPayService | 精确切换，支持链式调用 |
| `switchoverTo(String mchIdOrKey)` | 商户号或自定义键 | WxPayService | 根据商户号或自定义键切换，支持链式调用 |
| `addConfig(String mchId, String appId, WxPayConfig)` | 商户号, appId, 配置 | void | 动态添加配置（键为 mchId_appId 格式） |
| `addConfig(String configKey, WxPayConfig)` | 自定义键, 配置 | void | 动态添加配置（使用自定义键） |
| `removeConfig(String mchId, String appId)` | 商户号, appId | void | 移除指定配置（键为 mchId_appId 格式） |
| `removeConfig(String configKey)` | 自定义键 | void | 移除指定配置（使用自定义键） |
