# 境外微信支付(Overseas WeChat Pay)支持

本次更新添加了境外微信支付的支持，解决了 [Issue #3618](https://github.com/binarywang/WxJava/issues/3618) 中提到的问题。

## 问题背景

境外微信支付需要使用新的API接口地址和额外的参数：
- 使用不同的基础URL: `https://apihk.mch.weixin.qq.com` 
- 需要额外的参数: `trade_type` 和 `merchant_category_code`
- 使用不同的API端点: `/global/v3/transactions/*`

## 新增功能

### 1. GlobalTradeTypeEnum
新的枚举类，定义了境外支付的交易类型和对应的API端点：
- `APP`: `/global/v3/transactions/app`
- `JSAPI`: `/global/v3/transactions/jsapi`
- `NATIVE`: `/global/v3/transactions/native`
- `H5`: `/global/v3/transactions/h5`

### 2. WxPayUnifiedOrderV3GlobalRequest
扩展的请求类，包含境外支付必需的额外字段：
- `trade_type`: 交易类型 (JSAPI, APP, NATIVE, H5)
- `merchant_category_code`: 商户类目代码（境外商户必填）

### 3. 新的服务方法
- `createOrderV3Global()`: 创建境外支付订单
- `unifiedOrderV3Global()`: 境外统一下单接口

## 使用示例

### JSAPI支付示例
```java
// 创建境外支付请求
WxPayUnifiedOrderV3GlobalRequest request = new WxPayUnifiedOrderV3GlobalRequest();
request.setOutTradeNo(RandomUtils.getRandomStr());
request.setDescription("境外商品购买");
request.setNotifyUrl("https://your-domain.com/notify");

// 设置金额
WxPayUnifiedOrderV3GlobalRequest.Amount amount = new WxPayUnifiedOrderV3GlobalRequest.Amount();
amount.setCurrency(WxPayConstants.CurrencyType.CNY);
amount.setTotal(100); // 1元，单位为分
request.setAmount(amount);

// 设置支付者
WxPayUnifiedOrderV3GlobalRequest.Payer payer = new WxPayUnifiedOrderV3GlobalRequest.Payer();
payer.setOpenid("用户的openid");
request.setPayer(payer);

// 设置境外支付必需的参数
request.setTradeType("JSAPI");
request.setMerchantCategoryCode("5812"); // 商户类目代码

// 调用境外支付接口
WxPayUnifiedOrderV3Result.JsapiResult result = payService.createOrderV3Global(
    GlobalTradeTypeEnum.JSAPI, 
    request
);
```

### APP支付示例
```java
WxPayUnifiedOrderV3GlobalRequest request = new WxPayUnifiedOrderV3GlobalRequest();
// ... 设置基础信息 ...

request.setTradeType("APP");
request.setMerchantCategoryCode("5812");
request.setPayer(new WxPayUnifiedOrderV3GlobalRequest.Payer()); // APP支付不需要openid

WxPayUnifiedOrderV3Result.AppResult result = payService.createOrderV3Global(
    GlobalTradeTypeEnum.APP, 
    request
);
```

### NATIVE支付示例
```java
WxPayUnifiedOrderV3GlobalRequest request = new WxPayUnifiedOrderV3GlobalRequest();
// ... 设置基础信息 ...

request.setTradeType("NATIVE");
request.setMerchantCategoryCode("5812");
request.setPayer(new WxPayUnifiedOrderV3GlobalRequest.Payer());

String codeUrl = payService.createOrderV3Global(
    GlobalTradeTypeEnum.NATIVE, 
    request
);
```

## 配置说明

境外支付使用相同的 `WxPayConfig` 配置，无需特殊设置：

```java
WxPayConfig config = new WxPayConfig();
config.setAppId("你的AppId");
config.setMchId("你的境外商户号");
config.setMchKey("你的商户密钥");
config.setNotifyUrl("https://your-domain.com/notify");

// V3相关配置
config.setPrivateKeyPath("你的私钥文件路径");
config.setCertSerialNo("你的商户证书序列号");
config.setApiV3Key("你的APIv3密钥");
```

**注意**: 境外支付会自动使用 `https://apihk.mch.weixin.qq.com` 作为基础URL，无需手动设置。

## 兼容性

- 完全向后兼容，不影响现有的国内支付功能
- 使用相同的配置类和结果类
- 遵循现有的代码风格和架构模式

## 参考文档

- [境外微信支付文档](https://pay.weixin.qq.com/doc/global/v3/zh/4013014223)
- [原始Issue #3618](https://github.com/binarywang/WxJava/issues/3618)